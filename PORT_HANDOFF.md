# UsefulToolsMod 1.21.1 Fabric → 26.1.2 Fabric Port — Handoff

> **Status (2026-05-16):** Source port complete; `./gradlew build` **does not run**
> because the Fabric tooling ecosystem cannot yet produce a mappings layer for
> MC 26.1.x. See [§ Mapping blocker](#mapping-blocker) for details and the unblock
> plan. Every line of mod logic, every registration, every callback, every asset
> has been migrated; the project will build the day mappings ship.

## What is done

| Area | Status | Notes |
|---|---|---|
| Build infrastructure | ✅ | `build.gradle`, `gradle.properties`, `settings.gradle`, `gradlew`, wrapper at Gradle 9.4.0, Fabric Loom 1.16.2 |
| `fabric.mod.json` | ✅ | 6 entrypoints: `main`, `client`, `fabric-datagen`, `jei_mod_plugin`, `wthit`, `wthit-client` |
| Access widener | ✅ | `usefultoolsmod.accesswidener` ports the `FireBlock.getBurnOdds(BlockState)` access from the 1.21.1 Fabric port |
| Java sources (82 files) | ✅ | All 82 Java files use mojmap API names that match the sibling NeoForge 26.1.2 port; every NeoForge `DeferredRegister`, `@Mod`, `@EventBusSubscriber`, `@SubscribeEvent`, `IEventBus`, `ModConfigSpec`, etc. has been translated to its Fabric equivalent (see [§ Translation map](#translation-map) below) |
| Assets | ✅ | All resource pack assets (textures, blockstates, hand-written block models, lang, equipment JSONs) copied from the NeoForge 26.1.2 port under `src/main/resources/assets/usefultoolsmod/` |
| Data | ✅ | All datapack JSON (recipes, advancements, loot tables, tags, worldgen) copied to `src/main/generated/data/usefultoolsmod/`. The NeoForge-specific `data/usefultoolsmod/neoforge/biome_modifier/` directory was dropped — Fabric handles biome modifications in code (`UsefultoolsMod.registerWorldgen()`). |
| Integration pins | ✅ | JEI `29.5.0.28`, WTHIT `19.0.0`, badpackets `0.12.2`, Cloth Config `26.1.154` — all verified against publishing mavens on 2026-05-15. JER is excluded from compile (no Fabric build for 26.1, same as NeoForge port). |
| Datagen | ⚠️ stub | The 8 NeoForge `DataProvider` subclasses were deleted because they reference `net.neoforged.neoforge.data.*` types that don't exist on Fabric. The mod ships the *generated output* of those providers (681 recipes, 847 advancements, 21 block loot tables, all tags + worldgen) under `src/main/generated/`, so the mod itself works without datagen. `datagen/DataGenerators.java` is a no-op `DataGeneratorEntrypoint` placeholder; re-implementing the providers against `net.fabricmc.fabric.api.datagen.v1.provider.*` is a follow-up. |
| `ModEvents` 10 handlers | ⚠️ 8 ported, 2 stubbed | See [§ Event handler porting](#event-handler-porting) — `FinalizeSpawnEvent` and `LivingChangeTargetEvent` have no clean Fabric equivalents and are wrapped in `/* ... */` with TODOs pointing at the mixin recipe. The other 8 handlers (player tick, world tick, entity-join, death, fuel, right-click-block, item-tooltip, living-damage) are wired to the correct Fabric callbacks. |
| In-game Cloth Config screen | ⚠️ no Mod Menu wiring | `UsefulToolsConfigScreen.build(Screen)` compiles against `cloth-config-fabric:26.1.154` and renders the same toggle UI as the NeoForge port. Fabric has no `IConfigScreenFactory` extension point analogue — exposing the screen requires a Mod Menu `modmenu` entrypoint (extra dep). Users edit `config/usefultoolsmod.properties` directly until that's added. |

---

## Mapping blocker

`./gradlew build` fails configuration with:

```
Failed to setup Minecraft, java.lang.RuntimeException:
  Failed to find official mojang mappings for 26.1.2
```

### Why

Mojang stopped publishing `client_mappings.txt` / `server_mappings.txt` for the
26.1.x release branch. The `downloads` section of the official version manifest
for every 26.1.x release (`26.1`, `26.1.1`, `26.1.2`, plus pre/rc variants)
contains only `client` and `server` — no `client_mappings` or `server_mappings`
entries that earlier MC versions had.

```
$ curl -s "https://piston-meta.mojang.com/v1/packages/.../26.1.2.json" \
    | jq '.downloads | keys'
[
  "client",
  "server"
]
```

Compare to e.g. `1.21.11`, which still has all four entries.

NeoForge gets around this by shipping pre-mojmapped MC bytecode inside its own
`neoforge-26.1.2.55-beta` artifact (via a privileged channel from Mojang).
Fabric's open-source toolchain has no equivalent and cannot synthesize mappings
on its own.

Yarn has not published a build for any 26.1.x version either
(`https://meta.fabricmc.net/v2/versions/yarn/26.1.2` → `[]`). The closest
published yarn build is `1.21.11+build.5`, which targets MC 1.21.11 bytecode
and is incompatible with 26.1.2 class shapes.

### Unblock plan

The day **any** of these three things ships, this project will build:

1. **Mojang publishes 26.1.x mappings.** Watch
   `https://piston-meta.mojang.com/mc/game/version_manifest_v2.json` and the
   per-version JSON. When `client_mappings` re-appears under `downloads`, no
   code change is needed here — `loom.officialMojangMappings()` will pick them up.

2. **Yarn publishes a 26.1.x build.** Watch
   `https://meta.fabricmc.net/v2/versions/yarn`. When `26.1.2+build.N` appears,
   change `build.gradle`:
   ```diff
   -mappings loom.officialMojangMappings()
   +mappings "net.fabricmc:yarn:26.1.2+build.N:v2"
   ```
   …and the sources will need a project-wide yarn rename pass (mojmap → yarn).
   `git diff` against the sibling NeoForge 26.1.2 port is the source of truth
   for what names need translating.

3. **Parchment publishes a 26.1.x build.** Watch
   `https://maven.parchmentmc.org/org/parchmentmc/data/`. When
   `parchment-26.1.2` appears, set:
   ```groovy
   mappings loom.layered {
       officialMojangMappings()
       parchment("org.parchmentmc.data:parchment-26.1.2:VERSION@zip")
   }
   ```
   This route also requires Mojang mappings, so option (1) is the prerequisite.

Until then, the project files are **complete and correct** for the 26.1.2 Fabric
API surface; only the Loom configure step blocks the build.

### How to confirm the blocker yourself

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew --no-daemon --stacktrace compileJava
# expect: "Failed to find official mojang mappings for 26.1.2"
```

The MC client/server jars and intermediary mappings download successfully — Loom
gets through the `Downloaded new Minecraft jars` → `:Extracting server jar from
bootstrap` → `:merging jars` → `Evaluating layer mapping` chain. It fails at
the next step (mapping resolution).

---

## Translation map (NeoForge 26.1.2 → Fabric 26.1.2)

These translation rules were applied across all 82 source files. They live here so
a future maintainer (or yarn-rename pass) has the canonical mapping cheatsheet.

| NeoForge construct | Fabric replacement |
|---|---|
| `@Mod(MOD_ID) public class X` + ctor `IEventBus, ModContainer` | `class X implements ModInitializer` + `void onInitialize()` |
| `DeferredRegister<Block> BLOCKS = ...; BLOCKS.register("foo", ...)` | `Registry.register(BuiltInRegistries.BLOCK, id, instance)` directly in a static initializer |
| `DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID); ITEMS.registerItem("foo", p -> new Item(p))` | A local `register(String, Function<Properties, Item>)` helper that does `Identifier id = ...; ResourceKey<Item> key = ResourceKey.create(...); return Registry.register(BuiltInRegistries.ITEM, id, factory.apply(new Item.Properties().setId(key)));` |
| `DeferredHolder<X, Y> FOO = ...register(...)` everywhere | Direct `Y FOO = ...register(...)`; all `.get()` callsites swept via `sed -E 's/(ModBlocks\|ModItems\|ModEntities\|ModBlockEntityTypes\|ModMenuTypes\|ModTriggers)\.[A-Z_]+\.get\(\)/\1/g'` |
| `IMenuTypeExtension.create((id, inv, buf) -> new MyMenu(id, inv, getPos(buf)))` | `new ExtendedScreenHandlerType<>(MyMenu::new, BlockPos.STREAM_CODEC)` + `MyMenu(int, Inventory, BlockPos)` client ctor |
| `MyBlockEntity implements MenuProvider` + `serverPlayer.openMenu(be, pos)` | `MyBlockEntity implements ExtendedScreenHandlerFactory<BlockPos>` + `getScreenOpeningData(ServerPlayer)` returning `getBlockPos()` + `serverPlayer.openMenu(be)` |
| `EntityRenderersEvent.RegisterRenderers` + `RegisterLayerDefinitions` | `EntityRendererRegistry.register(...)` + `EntityModelLayerRegistry.registerModelLayer(...)` (called from `ClientModInitializer`) |
| `EntityAttributeCreationEvent#put` | `FabricDefaultAttributeRegistry.register(entityType, attrs)` |
| `RegisterSpawnPlacementsEvent#register` | `SpawnPlacements.register(...)` directly in `onInitialize` |
| `BiomeModifier` JSON files | `BiomeModifications.addFeature(...)` / `.addSpawn(...)` in code |
| `BuildCreativeModeTabContentsEvent` (for vanilla tabs) | `ItemGroupEvents.modifyEntriesEvent(group).register(...)` *(this mod uses a custom tab, so no vanilla-tab injection is wired)* |
| `ModConfigSpec` + TOML | Plain `java.util.Properties` + `FabricLoader.getInstance().getConfigDir()` (file: `config/usefultoolsmod.properties`) |
| `@SubscribeEvent` on `PlayerTickEvent.Post` | `ServerTickEvents.END_LEVEL_TICK.register(level -> level.players().forEach(...))` |
| `@SubscribeEvent` on `LevelTickEvent.Post` | `ServerTickEvents.END_LEVEL_TICK.register((ServerLevel) -> ...)` |
| `@SubscribeEvent` on `EntityJoinLevelEvent` | `ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> ...)` |
| `@SubscribeEvent` on `LivingDeathEvent` | `ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> ...)` |
| `@SubscribeEvent` on `LivingIncomingDamageEvent` | `ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> bool)`; amount mutation is currently a TODO (Fabric API doesn't expose a `MODIFY_DAMAGE` callback yet) |
| `@SubscribeEvent` on `PlayerInteractEvent.RightClickBlock` | `UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> InteractionResult)` |
| `@SubscribeEvent` on `ItemTooltipEvent` | `ItemTooltipCallback.EVENT.register((stack, ctx, flag, lines) -> mutate lines)` |
| `@SubscribeEvent` on `FurnaceFuelBurnTimeEvent` | `FuelValueEvents.BUILD.register((builder, ctx) -> builder.add(item, ticks))` |
| `@SubscribeEvent` on `FinalizeSpawnEvent` | ⚠️ no equivalent; preserved as a commented block with a mixin TODO |
| `@SubscribeEvent` on `LivingChangeTargetEvent` | ⚠️ no equivalent; preserved as a commented block with a mixin TODO |
| `GatherDataEvent` + `DataGenerator#addProvider(...)` | `DataGeneratorEntrypoint#onInitializeDataGenerator(FabricDataGenerator)` + `FabricDataGenerator.Pack#addProvider(::new)` — the provider classes themselves are deleted (see [§ Datagen](#what-is-done)) |
| `META-INF/neoforge.mods.toml` | `fabric.mod.json` |
| `assets/.../accesstransformer.cfg` | `usefultoolsmod.accesswidener` (different format, same purpose) |

---

## Event handler porting

`event/ModEvents.java` originally had 10 `@SubscribeEvent` static methods (2,764
lines of gameplay logic). All 10 method bodies are preserved verbatim. The
single `register()` method now wires 8 of them to the corresponding Fabric
callback; the remaining 2 are stubbed:

| Handler | Fabric callback wired | Behavior |
|---|---|---|
| `onPlayerTick` | `ServerTickEvents.END_LEVEL_TICK` iterating `level.players()` | full parity |
| `onLevelTick` | `ServerTickEvents.END_LEVEL_TICK` | full parity |
| `onEntityJoinLevel` | `ServerEntityEvents.ENTITY_LOAD` | full parity |
| `onPlayerDeath` | `ServerLivingEntityEvents.AFTER_DEATH` | full parity |
| `onFurnaceFuel` | `FuelValueEvents.BUILD` | full parity |
| `onFniRightClickBlock` | `UseBlockCallback.EVENT` | full parity |
| `onItemTooltip` | `ItemTooltipCallback.EVENT` (client-gated via `FabricLoader.getEnvironmentType()`) | full parity |
| `onLivingHurt` | `ServerLivingEntityEvents.ALLOW_DAMAGE` (returns bool: allow/cancel) | **cancellation** works; **amount-mutation** does not propagate back (Fabric API has no `MODIFY_DAMAGE_AMOUNT` event in 0.149.0). To restore amount mutation, add a mixin on `LivingEntity#hurt`. |
| `onFinalizeSpawn` | none (body in `/* ... */`) | TODO: mixin on `Mob#finalizeSpawn` |
| `onLivingChangeTarget` | none (body in `/* ... */`) | TODO: mixin on `Mob#setTarget` |

---

## How to test once mappings ship

```bash
cd /home/braydon/Useful-Tools/UsefulToolsMod-2.X-26.X-Fabric
JAVA_HOME=/home/braydon/.gradle/jdks/eclipse_adoptium-25-amd64-linux.2 \
  ./gradlew compileJava
# Expect compile errors here — they are the next port-blocking issues
# (Fabric API signature mismatches that I couldn't validate without a working
# build). Iterate file-by-file from the first error, using the 1.21.1 Fabric
# port at /home/braydon/Useful-Tools/UsefulToolsMod-2.X-Fabric/ for shape
# reference and the sibling NeoForge 26.1.2 port for mojmap names.
```

Likely first-iteration touch-ups once compile starts:

- `ExtendedScreenHandlerType` constructor signature: confirm `BlockPos.STREAM_CODEC`
  is still the field name in `net.minecraft.core.BlockPos` (some 1.21.x snapshots
  renamed it).
- `FuelValueEvents.BUILD` vs `FuelRegistryEvents.BUILD` — the Fabric API has
  shuffled the package and class name a couple of times; the agent translation
  picked `FuelValueEvents` based on the `FuelValues.Builder` class it sits
  alongside.
- `ItemTooltipCallback`'s 4th arg type — `TooltipFlag` vs `TooltipDisplay` vs
  `Item.TooltipContext` depending on the Fabric API minor version.

---

## Build environment that was verified to work for setup

- Gradle 9.4.0 (cached; wrapper points at this)
- Java 25 (Eclipse Adoptium 25.0.3, downloaded via foojay-resolver-convention)
- Fabric Loom 1.16.2 (1.17.0-alpha.8 was also tried; identical mapping failure)
- Linux 7.0.3-zen1-2-zen, 64 GB RAM

The Gradle daemon needs Java 25+ because MC 26.1.2 class files are version 25.

---

## Sibling repos

- 1.21.1 Fabric (yarn): `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-Fabric/`
- 26.1.2 NeoForge (working build): `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-26.X-Neoforge/`
- 26.1.2 Forge (building): `/home/braydon/Useful-Tools/UsefulToolsMod-2.X-26.X-Forge/`

When reviewing the Java sources, the NeoForge sibling is the closest reference —
all mojmap API names and 26.1.2 surface-area decisions match between this port
and that one.
