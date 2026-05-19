# Useful Tools Mod — Fabric 26.1.2

A Fabric port of [The Useful Tools Mod](https://github.com/StonyTark1117/UsefulToolsMod-2.X-Fabric)
for Minecraft 26.1.2.

> **Build status (2026-05-16):** ✅ `./gradlew build` produces a working
> `usefultoolsmod-2.2.2-26.1.2-fabric.jar`. `./gradlew runServer` reaches
> `Done (1.576s)!` with the mod loaded alongside 47 Fabric API modules and WTHIT.
> See `PORT_HANDOFF.md` for the full porting notes — including how the project
> works around Mojang shipping MC 26.1.x without published mappings.

## What this mod does

Adds reinforced and themed variants of vanilla tools and armor across ~60
material lines (Ferrous Gold, Polished Obsidian, Coal, Cake, Ectoplasm,
Sweet Berry, …), plus:

- A Ghost mob and the Ectoplasm + Refined Ectoplasm crafting chain
- A Spectral Infuser block with custom GUI for infusing tools against ghosts
- Two thrown explosives: Dynamite and Grenade
- 633 items, 21 blocks, 2 entities, 681 recipes, 847 advancements
- Worldgen: 3 Ferrous Gold ore variants (overworld / nether / end deepslate)
- Fully toggleable per-set via `config/usefultoolsmod.properties`

## Requirements

| | |
|---|---|
| Minecraft | **26.1.2** |
| Fabric Loader | **0.19.2+** |
| Fabric API | **0.149.0+26.1.2** (hard requirement — the mod will not load without it) |
| Java | 25 or newer |

## Integrations (all optional)

| Mod | Dev version | Runtime range |
|---|---|---|
| JEI (Just Enough Items) | `mezz.jei:jei-26.1.2-fabric:29.5.0.28` | `>= 29.0.0` |
| WTHIT (What The Heck Is That) | `mcp.mobius.waila:wthit:fabric-19.0.0` | `>= 19.0.0` |
| Cloth Config | `me.shedaniel.cloth:cloth-config-fabric:26.1.154` | `>= 26.1.0` |
| Mod Menu | `com.terraformersmc:modmenu:18.0.0-alpha.8` | `>= 18.0.0-alpha.8` (19.x requires MC ≥ 26.2) |
| JER (Just Enough Resources) | _no Fabric 26.1.x build available_ | excluded from compile |

Cloth Config builds the actual config screen; Mod Menu adds the "Config"
button on the in-game mods list that opens it. Without Mod Menu, users edit
`config/usefultoolsmod.properties` directly.

## How to build

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew build
# output: build/libs/usefultoolsmod-2.2.2-26.1.2-fabric.jar
```

Java 25 is required because MC 26.1.2 class files are major version 25. Java 26
also works. The Gradle wrapper handles the rest of the toolchain.

## How to test in dev

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew runServer
# accept the EULA on first launch (creates run/eula.txt)
```

## License

CC0-1.0 — see `LICENSE`.

## Links

- 1.21.1 Fabric source: <https://github.com/StonyTark1117/UsefulToolsMod-2.X-Fabric>
- NeoForge 26.1.2 sibling port: `../UsefulToolsMod-2.X-26.X-Neoforge`
- Issues: <https://github.com/StonyTark1117/UsefulToolsMod-2.X-26.X-Fabric/issues>
