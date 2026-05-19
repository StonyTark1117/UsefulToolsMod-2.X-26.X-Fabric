# Changelog

## 2.2.2-26.1.2-fabric — 2026-05-18 (runClient pass)

In-game smoke test surfaced four issues, all fixed:

- **Mod icon not shown in the mods list.** `fabric.mod.json` pointed at
  `assets/usefultoolsmod/icon.png`, which doesn't exist in this port. The
  icon file actually ships at the resources root as `grenade.png` (matching
  the NeoForge sibling's `logoFile`). Fixed by updating the `icon` field to
  `grenade.png`.
- **WTHIT Spectral Infuser progress overlay missing.** WTHIT `fabric-19.0.0`
  loads plugins from `wthit_plugins.json` at the resources root, *not* from
  Fabric entrypoints — the `wthit` / `wthit-client` entrypoint convention
  was never recognized by this WTHIT version. The plugin classes were
  consequently never instantiated. Fixed by adding
  `src/main/resources/wthit_plugins.json` (same format as the NeoForge
  sibling's `waila_plugins.json`) and dropping the stale entrypoints from
  `fabric.mod.json`. WTHIT now logs `Initializing common plugin
  usefultoolsmod:plugin at ...UsefulToolsWthitPlugin` at startup.
- **WTHIT Ghost entity tooltip missing** — same root cause and same fix.
- **Iron pickaxes could correctly drop SEMBLOCK / SOBLOCK** (latent gameplay
  bug, also present on the NeoForge sibling). The mining-tier check relies
  on `INCORRECT_FOR_*_TOOL` membership, and the seven mod blocks that
  required iron-or-diamond tier were only listed in `NEEDS_*_TOOL` — never
  added to the corresponding incorrect-for tags. Fixed in
  `ModBlockTagProvider`: SEMBLOCK + SOBLOCK now extend
  `INCORRECT_FOR_IRON_TOOL`; the five iron-tier mod blocks extend
  `INCORRECT_FOR_STONE_TOOL`. Vanilla chains the lower-tier "incorrect"
  tags upward so the additions propagate.

The WTHIT `Unsolvable tier comparison` log warnings still fire on resource
reload — WTHIT's view of mod-tier-tool ordering is driven by a heuristic
that doesn't map cleanly onto `ToolMaterial` instances whose stats don't
match vanilla `Tiers` (Polished Obsidian: speed 10, enchant 18). Same noise
present on the NeoForge sibling; log-only, no gameplay impact.

Also:

- `build.gradle`: switched the publishing repo `url` to `url =` assignment
  (silences the lone Gradle-10 deprecation warning).
- `PORT_HANDOFF.md`: refreshed the status block and the "skipped/stubbed"
  table to reflect five commits of progress since 2026-05-16.

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
  page when Mod Menu is installed. Pinned at Mod Menu `18.0.0-alpha.8` — its
  `minecraft: ">1.26-"` constraint is satisfied by 26.1.2 (semver major
  `26 > 1`). Mod Menu `19.0.0-alpha.1` requires `>=26.2-` and is rejected by
  the loader against 26.1.2. The pom transitively pins a stale
  `fabric-resource-loader-v1` (2.0.10+11e1c9a34e) whose `MinecraftServer`
  ctor mixin no longer matches MC 26.1.2's 11-param constructor, so all
  `net.fabricmc.fabric-api` transitive deps are excluded in `build.gradle`
  to keep the fabric-api meta-jar's version (also 2.0.10, qualifier
  7c44c7324c) authoritative. With the correct pin Mod Menu now loads in the
  dev runtime classpath (`modLocalRuntime`) so the config screen is
  reachable from `runClient` without manual install. Users still edit
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

### Datagen providers (2026-05-16, follow-up)

The seven NeoForge-style provider classes that the initial port had dropped
are now back, ported to Fabric idioms. `./gradlew runDatagen` runs all six
to completion (~200 ms) and overwrites `src/main/generated/`:

| Provider                      | Fabric base class                     | Notes |
|-------------------------------|---------------------------------------|-------|
| `ModBlockLootTableProvider`   | `FabricBlockLootSubProvider`          | 17 `dropSelf` + 4 silk-touch ore drops. Output is missing the cosmetic `random_sequence` field NeoForge sets — runtime-equivalent. |
| `ModBlockTagProvider`         | `FabricTagsProvider.BlockTagsProvider` | Strict validator on `FabricPackOutput` rejects cross-tag refs to vanilla tags via `addTag(...)`; all such refs use `addOptionalTag(...)` instead. JSON shifts from `"#minecraft:foo"` bare string to `{"id":"...","required":false}`. |
| `ModItemTagProvider`          | `FabricTagsProvider.ItemTagsProvider`  | TRIMMABLE_ARMOR + Magnetization addon tags. |
| `ModModelProvider`            | `FabricModelProvider` (client-datagen) | Combines blockstate + item-model halves (NeoForge sibling splits these). `SpectralInfuser` block model + facing/lit blockstate live as hand-authored JSON under `src/main/resources/` — the provider only emits the `items/spectral_infuser.json` dispatcher that points back at the block model, driven directly through `ItemModelGenerators.itemModelOutput` since `declareCustomModelItem` defaults to the `item/...` location. |
| `ModDatapackEntries`          | `FabricDynamicRegistryProvider`        | Emits `worldgen/configured_feature/*` and `worldgen/placed_feature/*`. Biome modifiers stay code-driven in `ModBiomeModifications`. |
| `ModRecipeProvider`           | `FabricRecipeProvider` (Runner)        | 681-recipe body ported verbatim; `.get()` bridges stripped via sed. Recipe ids are re-scoped to `usefultoolsmod:` namespace by Fabric (vs. NeoForge keeping `minecraft:ice` etc.) — JSON path drifts but recipe lookup is unaffected. |
| `ModAdvancementProvider`      | `FabricAdvancementProvider`            | 847-node tree folded into a single class (the sibling's `AdvancementSubProvider` indirection is unnecessary since Fabric exposes `(HolderLookup.Provider, Consumer<AdvancementHolder>)` directly). |

Supporting infrastructure changes:

- `build.gradle` `runs.datagen` switched from `inherit server` to `inherit client` so the client-side `ModelProvider` and friends can be classloaded.
- `usefultoolsmod.accesswidener` widens `BlockModelGenerators#createTrivialCube`, `ItemModelGenerators#{generateFlatItem,declareCustomModelItem}`, and the private `ItemModelGenerators#itemModelOutput` field — all package-private in 26.1.2.
- `DataGenerators.buildRegistry` populates the dynamic-registry lookup with `CONFIGURED_FEATURE` and `PLACED_FEATURE` bootstrap methods so `ModDatapackEntries` sees our entries.
- Mod Menu 19.0.0-alpha.1 demoted from `modLocalRuntime` to `modCompileOnly` only — its hard `minecraft: ">=26.2-"` constraint blocks every client-classpath JVM launch (datagen included). End users install Mod Menu manually for the in-game config screen.
- `data/minecraft/{advancement,recipe}/` directories present in the imported NeoForge baseline are not regenerated under Fabric (Fabric forces all generated recipes/advancements into the mod-id namespace) — accepted as a structural difference.

### Files dropped vs the NeoForge port (continued)

(none new — the seven provider classes listed above have been restored)
