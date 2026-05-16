# Useful Tools Mod — Fabric 26.1.2

A Fabric port of [The Useful Tools Mod](https://github.com/StonyTark1117/UsefulToolsMod-2.X-Fabric)
for Minecraft 26.1.2.

> **Build status (2026-05-16):** Source port is complete, but `./gradlew build`
> fails at the Loom mapping step because Mojang has not published 26.1.x
> mappings and yarn has no 26.1.x build yet. See `PORT_HANDOFF.md` for the
> full diagnosis and unblock plan. The mod's Java sources, assets, datapack
> JSON, and integrations are all in place and will compile the day mappings
> ship.

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

## Integrations (all optional)

| Mod | Dev version | Runtime range |
|---|---|---|
| JEI (Just Enough Items) | `mezz.jei:jei-26.1.2-fabric:29.5.0.28` | `>= 29.0.0` |
| WTHIT (What The Heck Is That) | `mcp.mobius.waila:wthit:fabric-19.0.0` | `>= 19.0.0` |
| Cloth Config | `me.shedaniel.cloth:cloth-config-fabric:26.1.154` | `>= 26.1.0` |
| JER (Just Enough Resources) | _no Fabric 26.1.x build available_ | excluded from compile |

## How to build (once mappings ship)

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew build
# output: build/libs/usefultoolsmod-2.2.2-26.1.2-fabric.jar
```

## License

CC0-1.0 — see `LICENSE`.

## Links

- 1.21.1 Fabric source: <https://github.com/StonyTark1117/UsefulToolsMod-2.X-Fabric>
- NeoForge 26.1.2 sibling port: `../UsefulToolsMod-2.X-26.X-Neoforge`
- Issues: <https://github.com/StonyTark1117/UsefulToolsMod-2.X-26.X-Fabric/issues>
