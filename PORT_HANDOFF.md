# UsefulToolsMod 1.21.1 Fabric → 26.1.2 Fabric Port — Handoff

> **Status (2026-05-18):** `./gradlew build` produces a working
> `usefultoolsmod-2.2.2-26.1.2-fabric.jar` with zero compile warnings.
> `./gradlew runServer` reaches `Done (0.145s)!` with the mod loaded alongside
> 47 Fabric API modules and WTHIT. `./gradlew runDatagen` runs all 7 providers
> in ~200 ms and overwrites `src/main/generated/` to match the NeoForge baseline
> modulo 4 runtime-equivalent JSON drifts (see CHANGELOG). The Spectral Infuser
> GUI, JEI plugin, Cloth Config + Mod Menu screen, all 633 items, 21 blocks,
> 2 entities, ghost spawn rules, and the FinalizeSpawn / ChangeTarget /
> setAmount behavior mixins are wired and pass mod-load validation.

## Build environment

- **Gradle 9.4.0** (wrapper pinned)
- **Java 25** required to run Gradle — MC 26.1.2 class files are major version 25.
  `JAVA_HOME=/path/to/jdk-25 ./gradlew build`. Java 26 also works.
- **Fabric Loom 1.16.2**
- **Fabric Loader 0.19.2**, **Fabric API 0.149.0+26.1.2**

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew build
# → build/libs/usefultoolsmod-2.2.2-26.1.2-fabric.jar

JAVA_HOME=/path/to/jdk-25 ./gradlew runServer
# → [Server thread/INFO] (Minecraft) Done (1.576s)! For help, type "help"
```

## The MC 26.1.x mapping puzzle

Stuck for a while on `loom.officialMojangMappings()` failing with
`Failed to find official mojang mappings for 26.1.2`. The reason turned out
to be deeper than missing mappings:

**Mojang ships MC 26.1.x unobfuscated.** Verified by unzipping
`minecraft-client.jar` from `https://piston-data.mojang.com/v1/objects/.../client.jar`:
classes are already named `net/minecraft/world/level/block/Block.class`,
`com/mojang/blaze3d/audio/Channel.class`, etc. — no obfuscation pass on Mojang's side.
The version manifest has no `client_mappings` / `server_mappings` entries because
there's nothing to map.

Confirmed by inspecting Mojang's official 26.1.x version manifests:
```bash
$ curl -s https://piston-meta.mojang.com/v1/packages/.../26.1.2.json | jq '.downloads | keys'
[ "client", "server" ]   # no *_mappings entries
```

Fabric's published intermediary jar for 26.1.x is a 572-byte stub containing
only the `v1 official intermediary` header — no actual class mappings — because
MC ships already-named and there is nothing to bridge.

### The fix (3 moving parts)

1. **Custom identity mapping file at `libs/intermediary-26.1.2-v2.jar`** —
   a 489-byte jar whose only content is `mappings/mappings.tiny` with the single
   line `tiny\t2\t0\tofficial\tintermediary\tnamed`. No mapping entries: it's a
   3-namespace identity, telling Loom "obfuscated = intermediary = named for every
   class". Loom is satisfied; remap passes are no-ops.

2. **`build.gradle`** uses the local file via `mappings files("libs/intermediary-26.1.2-v2.jar")`
   instead of `loom.officialMojangMappings()`. (The published artifact has a broken
   pom — `<version>0.0.0</version>` regardless of the requested version — so gradle's
   dependency resolver rejects the maven coordinate. A local file dep sidesteps the
   metadata check entirely.)

3. **`loom.runs.configureEach { vmArg "-Dfabric.runtimeMappingNamespace=named" }`** —
   without this, Fabric Loader bombs at mod-load with
   `Namespace (named) does not match current runtime namespace (official)` when it
   reads the access wideners shipped by `fabric-loot-api-v3` / `fabric-resource-conditions-api-v1`
   / etc. The fabric-* modules declare their AWs as `named`; the property tells
   Loader to use `named` as its runtime namespace so the AW namespaces match.

Same trick should work for any future MC version that Mojang ships unobfuscated
without publishing a mappings file (i.e. 26.1.x and presumably 26.2+).

## Other gotchas worth recording

| Symptom | Cause | Fix |
|---|---|---|
| `Failed to find official mojang mappings for 26.1.2` | Mojang doesn't publish mappings for 26.1.x; classes are pre-named in client.jar | Custom identity mapping at `libs/intermediary-26.1.2-v2.jar`; see above |
| `Could not resolve net.fabricmc:intermediary:26.1.2 — inconsistent module metadata found. Descriptor: net.fabricmc:intermediary:0.0.0` | Fabric's published intermediary jar for 26.1.x has a placeholder pom version | Use `mappings files(...)` instead of a maven coordinate |
| `Could not find 'named' mapping position!` | Empty intermediary jar has only 2 namespaces (official, intermediary) — Loom wants 3 | Identity tiny v2 file with 3-namespace header |
| `Namespace (named) does not match current runtime namespace (official)` | Fabric Loader defaults to `official` for the runtime namespace when MC isn't being live-remapped | Pass `-Dfabric.runtimeMappingNamespace=named` to runs |
| `Some of your mods are incompatible with the game ... requires version [26.1,27) of 'Minecraft' (minecraft), but only the wrong version is present: 26.1.2!` | Fabric Loader uses semver syntax, not Maven brackets | `minecraft_version_range=>=26.1- <27-` instead of `[26.1,27)` |
| `ExtendedScreenHandlerType` / `ExtendedScreenHandlerFactory` not found | Fabric API renamed the module: `fabric-screen-handler-api-v1` → `fabric-menu-api-v1`; types renamed to `ExtendedMenuType` / `ExtendedMenuProvider` | Update imports + types in `ModMenuTypes` and `SpectralInfuserBlockEntity` |
| `EntityModelLayerRegistry` not found | Renamed to `ModelLayerRegistry` (same package) | Update import + reference in `ModEntityRenderers` |
| `CreativeModeTab.builder()` needs 2 args | Vanilla signature changed to `builder(Row, int)` | Pass `CreativeModeTab.Row.TOP, 0` |
| `BlockTags.create(Identifier)` / `ItemTags.create(Identifier)` not found | These helpers were removed | `TagKey.create(Registries.BLOCK / ITEM, id)` |
| `new BlockEntityType<>(supplier, set)` is private | Vanilla constructor no longer public; no `Builder` inner class | `FabricBlockEntityTypeBuilder.create(supplier, blocks...).build()` |
| `Block#openMenu(MenuProvider, BlockPos)` not found | NeoForge-only overload | `serverPlayer.openMenu(blockEntity)` where the block entity implements `ExtendedMenuProvider<BlockPos>` and supplies the pos via `getScreenOpeningData(ServerPlayer)` |

## What got translated

| Area | Status |
|---|---|
| Build infrastructure | ✅ `build.gradle`, `gradle.properties`, `settings.gradle`, `gradlew`, wrapper at Gradle 9.4.0, Fabric Loom 1.16.2, identity-mapping jar staged at `libs/intermediary-26.1.2-v2.jar` |
| `fabric.mod.json` | ✅ 5 entrypoints: `main`, `client`, `fabric-datagen`, `jei_mod_plugin`, `modmenu` (WTHIT uses `wthit_plugins.json` at the resources root, not entrypoints — see below) |
| Access widener | ✅ `usefultoolsmod.accesswidener` — widens `FireBlock#getBurnOdds`, `BlockModelGenerators#createTrivialCube`, `ItemModelGenerators#{generateFlatItem,declareCustomModelItem}`, and the private `ItemModelGenerators#itemModelOutput` field for the datagen pipeline |
| Mixins | ✅ `usefultoolsmod.mixins.json` — `MobMixin` (FinalizeSpawn + ChangeTarget cancellation), `LivingEntityMixin` (`@WrapMethod` on `hurtServer` for damage-amount mutation) |
| Java sources (85 files) | ✅ All translated NeoForge → Fabric idioms; zero compile warnings (deprecation flag on) |
| Datagen providers | ✅ All 7 restored as Fabric-API subclasses (`./gradlew runDatagen` runs in ~200 ms) — see CHANGELOG for the mapping |
| Assets (~3,800 files) | ✅ Carried over verbatim from the NeoForge sibling port |
| Datapack JSON | ✅ Re-emitted by datagen into `src/main/generated/`; matches the NeoForge baseline modulo 4 runtime-equivalent JSON drifts (documented in CHANGELOG) |
| Integration pins | ✅ JEI `29.5.0.28`, WTHIT `19.0.0`, badpackets `0.12.2`, Cloth Config `26.1.154`, Mod Menu `18.0.0-alpha.8` |
| In-game config screen | ✅ Cloth Config screen surfaced via Mod Menu (`modmenu` entrypoint + `compat/modmenu/UsefulToolsModMenuPlugin`); falls back to hand-editing `config/usefultoolsmod.properties` when Mod Menu is absent |
| `./gradlew build` | ✅ Produces `build/libs/usefultoolsmod-2.2.2-26.1.2-fabric.jar` with zero compile warnings |
| `./gradlew runServer` | ✅ Reaches `Done (0.145s)!` with 48 mods loaded |
| `./gradlew runClient` | ⚠️ Loads cleanly; full in-world gameplay-parity smoke test against the NeoForge sibling not yet run |

## What is still skipped or stubbed

| Area | Status | Notes |
|---|---|---|
| `compat/jer/**` | ⚠️ excluded from compile | No Fabric 26.1.x build of Just Enough Resources exists (same situation as the NeoForge sibling port). When upstream ships one, drop the exclude in `build.gradle` and add the runtime dep. |
| `runClient` gameplay-parity smoke test | ⚠️ not yet run end-to-end | Build/load path is verified — server reaches `Done`, all providers run, no Loader-level rejections. The post-load smoke test (place every block, fire each item, run a Spectral Infuser cycle, trigger the Ghost rules) is the last gate before release. |

*Previously listed here as TODO; resolved since 2026-05-16:*

- `ModEvents.onFinalizeSpawn` / `onLivingChangeTarget` → restored via `MobMixin` (see commits `00013c8`, `44acbe8`).
- `ModEvents.onLivingHurt` damage-amount mutation → `LivingEntityMixin` with MixinExtras `@WrapMethod` on `LivingEntity#hurtServer`.
- 7 datagen providers → all restored as Fabric-API subclasses (commit `a712d5a`).
- Cloth Config in-game screen → wired through Mod Menu `18.0.0-alpha.8` (commits `6126c80`, `2d47dd4`).
- JEI deprecation warnings → all 11 javac deprecations eliminated (commit `ad84d6b`).

## Translation map (NeoForge 26.1.2 → Fabric 26.1.2)

| NeoForge construct | Fabric replacement |
|---|---|
| `@Mod(MOD_ID) public class X` + ctor `IEventBus, ModContainer` | `class X implements ModInitializer` + `void onInitialize()` |
| `DeferredRegister<Block>` etc. | `Registry.register(BuiltInRegistries.X, id, instance)` in a static initializer |
| `DeferredRegister.Items#registerItem(name, p -> new Item(p))` | A local `register(String, Function<Properties, Item>)` helper that sets `Properties.setId(...)` and calls `Registry.register(BuiltInRegistries.ITEM, id, item)` |
| `DeferredHolder<X, Y> FOO` | Direct `Y FOO`; all `.get()` callsites swept via sed |
| `IMenuTypeExtension.create((id, inv, buf) -> new MyMenu(...))` | `new ExtendedMenuType<>(MyMenu::new, BlockPos.STREAM_CODEC)` + `MyMenu(int, Inventory, BlockPos)` client ctor |
| `MyBlockEntity implements MenuProvider` + `serverPlayer.openMenu(be, pos)` | `MyBlockEntity implements ExtendedMenuProvider<BlockPos>` + `getScreenOpeningData(ServerPlayer)` returning `getBlockPos()` + `serverPlayer.openMenu(be)` |
| `EntityRenderersEvent.RegisterRenderers` + `RegisterLayerDefinitions` | `EntityRendererRegistry.register(...)` + `ModelLayerRegistry.registerModelLayer(...)` (called from `ClientModInitializer`) |
| `EntityAttributeCreationEvent#put` | `FabricDefaultAttributeRegistry.register(entityType, attrs)` |
| `RegisterSpawnPlacementsEvent#register` | `SpawnPlacements.register(...)` in `onInitialize` |
| `BiomeModifier` JSON files | `BiomeModifications.addFeature(...)` / `.addSpawn(...)` in code |
| `ModConfigSpec` + TOML | Plain `java.util.Properties` + `FabricLoader.getInstance().getConfigDir()` |
| `@SubscribeEvent` `PlayerTickEvent.Post` | `ServerTickEvents.END_LEVEL_TICK.register(level -> level.players().forEach(...))` |
| `@SubscribeEvent` `LevelTickEvent.Post` | `ServerTickEvents.END_LEVEL_TICK.register((ServerLevel) -> ...)` |
| `@SubscribeEvent` `EntityJoinLevelEvent` | `ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> ...)` |
| `@SubscribeEvent` `LivingDeathEvent` | `ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> ...)` |
| `@SubscribeEvent` `LivingIncomingDamageEvent` | `ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> bool)` |
| `@SubscribeEvent` `PlayerInteractEvent.RightClickBlock` | `UseBlockCallback.EVENT.register(...)` |
| `@SubscribeEvent` `ItemTooltipEvent` | `ItemTooltipCallback.EVENT.register(...)` |
| `@SubscribeEvent` `FurnaceFuelBurnTimeEvent` | `FuelValueEvents.BUILD.register((builder, ctx) -> builder.add(item, ticks))` |
| `GatherDataEvent` + `DataGenerator#addProvider` | `DataGeneratorEntrypoint#onInitializeDataGenerator(FabricDataGenerator)` + `FabricDataGenerator.Pack#addProvider(::new)` |
| `META-INF/neoforge.mods.toml` | `fabric.mod.json` |

## Sibling repos

- 1.21.1 Fabric (yarn): `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-Fabric/`
- 26.1.2 NeoForge (working build): `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-26.X-Neoforge/`
- 26.1.2 Forge: `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-26.X-Forge/`
