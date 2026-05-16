# Changelog

## 2.2.2-26.1.2-fabric — 2026-05-16

Initial Fabric port targeting Minecraft 26.1.2. Forked from the 1.21.1 Fabric
port (`UsefulToolsMod-2.X-Fabric`) and the 26.1.2 NeoForge sibling port
(`UsefulToolsMod-2.X-26.X-Neoforge`).

**Status:** `./gradlew build` succeeds with zero compile warnings;
`./gradlew runServer` reaches `Done (1.576s)!` with the mod loaded.

### Deprecation cleanup pass

`build.gradle` now sets `options.deprecation = true` on all `JavaCompile`
tasks, so javac reports every deprecated-API call site by file:line. The
following call-site swaps eliminated all 11 outstanding warnings:

- `mezz.jei.api.recipe.RecipeType<T>` (class) → `mezz.jei.api.recipe.types.IRecipeType<T>` (interface, factory `IRecipeType.create(Identifier, Class)`).
- JEI `IIngredientAcceptor#addItemStack(ItemStack)` → the non-deprecated `add(ItemStack)` overload.
- JEI `IRecipeCatalystRegistration#addRecipeCatalyst(ItemStack, IRecipeType...)` → `addCraftingStation(IRecipeType, ItemStack...)` (the only non-deprecated catalyst method).
- Fabric `EntityRendererRegistry.register(...)` → vanilla `EntityRenderers.register(...)` (Fabric Transitive Access Wideners v1 opens vanilla's private method, removing the need for the wrapper).
- `BlockState#isSolid()` (used for cover-blocking line-of-sight check) → `isCollisionShapeFullBlock(level, pos)`.
- `Entity#hurt(DamageSource, float)` → `hurtServer((ServerLevel) entity.level(), source, amount)` at all 6 call sites; `hurtOrSimulate` is itself deprecated and not a viable replacement.

### Mapping-resolution workaround (the interesting part)

MC 26.1.x is shipped unobfuscated by Mojang — `minecraft-client.jar` already
contains classes named `net/minecraft/world/level/block/Block.class` etc. — but
no `client_mappings.txt` is published. This breaks `loom.officialMojangMappings()`
and also yarn, since yarn has no 26.1.x build either.

The port works around this with three steps documented in `PORT_HANDOFF.md`:

1. A custom identity tiny-v2 mappings jar at `libs/intermediary-26.1.2-v2.jar`
   (572 bytes, header-only — no entries = identity for all classes).
2. `build.gradle` uses `mappings files("libs/intermediary-26.1.2-v2.jar")` to
   sidestep the broken upstream pom (`<version>0.0.0</version>`).
3. `loom.runs.configureEach { vmArg "-Dfabric.runtimeMappingNamespace=named" }`
   so Fabric Loader's classTweaker reader accepts the `named`-namespaced ATs
   shipped by `fabric-loot-api-v3`, `fabric-resource-conditions-api-v1`, etc.

### Code-level changes vs the 1.21.1 Fabric port

- Switched mappings from yarn (`1.21.1+build.3`) to the identity mapping above.
  Every yarn identifier in the original Fabric sources was rewritten to its
  mojmap equivalent during the port.
- Bumped Java target from 21 to 25 (MC 26.1.2 class files are major version 25).
- Bumped Fabric Loader (`0.18.4` → `0.19.2`), Loom (`1.15-SNAPSHOT` → `1.16.2`),
  Gradle wrapper (unspecified → `9.4.0`), Fabric API (`0.116.9+1.21.1` → `0.149.0+26.1.2`).

### Code-level changes vs the NeoForge 26.1.2 port

- All `DeferredRegister` registries → direct `Registry.register(BuiltInRegistries.X, id, instance)`
  in static initializers.
- All `@Mod`/`@EventBusSubscriber`/`@SubscribeEvent` annotations dropped; main
  class implements `ModInitializer`, client class implements `ClientModInitializer`.
- `ModConfigSpec` (TOML) → `java.util.Properties` (`config/usefultoolsmod.properties`).
- 10 NeoForge event handlers in `ModEvents` ported to Fabric callbacks
  (`ServerTickEvents`, `ServerEntityEvents`, `ServerLivingEntityEvents`,
  `UseBlockCallback`, `ItemTooltipCallback`, `FuelValueEvents`). The three
  events that have no clean Fabric equivalent are restored via mixins
  wired through `usefultoolsmod.mixins.json`:
  - `FinalizeSpawnEvent` → `MobMixin` HEAD-injects `Mob#finalizeSpawn`
    to discard `GhostEntity` when `Config.ghostEnabled = false`.
  - `LivingChangeTargetEvent` → `MobMixin` HEAD-injects
    `Mob#setTarget(LivingEntity)` to cancel target-set when the player
    wears a passifying armor set (rotten flesh / pumpkin pie / bone /
    phantom / nautilus / eye-of-ender / echo shard / turtle scute).
  - `LivingIncomingDamageEvent#setAmount` (damage-amount mutation, which
    `ServerLivingEntityEvents.ALLOW_DAMAGE` cannot do — it is cancel-only)
    → `LivingEntityMixin` uses MixinExtras `@WrapMethod` on
    `LivingEntity#hurtServer` to invoke `ModEvents.processIncomingDamage`,
    cancel via early `return false`, or forward the modified amount to
    the original.
- `IConfigScreenFactory` (NeoForge in-game-config-screen extension point) →
  Mod Menu `ConfigScreenFactory` via the `modmenu` entrypoint pointing at
  `compat/modmenu/UsefulToolsModMenuPlugin`. The existing Cloth Config screen
  (`client/UsefulToolsConfigScreen`) is now reachable from Mod Menu's mod-list
  page when Mod Menu is installed. Mod Menu 19.0.0-alpha.1 is the only build
  currently targeting MC 26.1.x; its pom transitively pins a stale
  `fabric-resource-loader-v1` (2.0.10+11e1c9a34e) whose `MinecraftServer`
  ctor mixin no longer matches MC 26.1.2's 11-param constructor, so all
  `net.fabricmc.fabric-api` transitive deps are excluded in `build.gradle`
  to keep the fabric-api meta-jar's version (also 2.0.10, qualifier
  7c44c7324c) authoritative. Users still edit
  `config/usefultoolsmod.properties` directly when Mod Menu is absent.
- Datagen providers (8 classes) dropped; the pre-generated JSON they produced
  ships under `src/main/generated/` so the mod is self-contained.
- Compat plugins (JEI / WTHIT) are platform-agnostic. JEI uses the standard
  `@JeiPlugin` annotation; WTHIT plugins are registered via `wthit` /
  `wthit-client` entrypoints in `fabric.mod.json`.

### API surface changes (Fabric ↔ NeoForge for MC 26.1.2)

The MC API call sites match between this port and the NeoForge sibling, since
both target the same 26.1.2 bytecode. The Fabric-API-side adjustments needed:

- `fabric-screen-handler-api-v1` was renamed to `fabric-menu-api-v1`;
  `ExtendedScreenHandlerType` → `ExtendedMenuType`,
  `ExtendedScreenHandlerFactory<D>` → `ExtendedMenuProvider<D>`.
- `EntityModelLayerRegistry` → `ModelLayerRegistry` (same package).
- `CreativeModeTab.builder()` → `CreativeModeTab.builder(Row, int)`.
- `BlockTags.create(Identifier)` / `ItemTags.create(Identifier)` → `TagKey.create(Registries.X, id)`.
- `new BlockEntityType<>(supplier, blocks)` is private → use
  `FabricBlockEntityTypeBuilder.create(supplier, blocks...).build()`.

### Integration version pins (all verified 2026-05-15)

- JEI: `mezz.jei:jei-26.1.2-fabric:29.5.0.28`
- WTHIT: `mcp.mobius.waila:wthit:fabric-19.0.0`
- badpackets: `lol.bai:badpackets:fabric-0.12.2`
- Cloth Config: `me.shedaniel.cloth:cloth-config-fabric:26.1.154`
- JER: no Fabric 26.1.x build available; `compat/jer/**` is excluded from
  compile via `build.gradle`.

### Files dropped vs the NeoForge port

- `META-INF/neoforge.mods.toml` (replaced by `fabric.mod.json`)
- `waila_plugins.json` (NeoForge convention; Fabric uses `wthit` entrypoint)
- `data/usefultoolsmod/neoforge/biome_modifier/*.json` (Fabric uses code,
  see `UsefultoolsMod.registerWorldgen()`)
- `event/ModEventBusEvents.java` (split between `FabricDefaultAttributeRegistry`
  in main init and `SpawnPlacements.register` in main init)
- `worldgen/ModBiomeModifiers.java` (same — code-driven on Fabric)
- `datagen/Mod{BlockState,BlockLootTable,Recipe,Advancement,BlockTag,ItemTag,ItemModel}Provider.java`
  and `datagen/ModDatapackEntries.java` (deleted; JSON baked into `src/main/generated/`)
