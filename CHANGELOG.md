# Changelog

## 2.2.2-26.1.2-fabric — 2026-05-16

Initial Fabric port targeting Minecraft 26.1.2. Forked from the 1.21.1 Fabric
port (`UsefulToolsMod-2.X-Fabric`) and the 26.1.2 NeoForge sibling port
(`UsefulToolsMod-2.X-26.X-Neoforge`).

**Status:** `./gradlew build` succeeds; `./gradlew runServer` reaches
`Done (1.576s)!` with the mod loaded.

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
  `UseBlockCallback`, `ItemTooltipCallback`, `FuelValueEvents`). Two events
  (`FinalizeSpawnEvent`, `LivingChangeTargetEvent`) have no Fabric equivalent
  and are preserved as commented blocks with mixin TODOs.
- `IConfigScreenFactory` (NeoForge in-game-config-screen extension point) → no-op
  on Fabric; Cloth Config screen still compiles, but exposing it would require
  a Mod Menu entrypoint (not currently wired). Users edit
  `config/usefultoolsmod.properties` directly.
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
