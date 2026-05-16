package com.stonytark.usefultoolsmod.client;

import com.stonytark.usefultoolsmod.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Cloth Config-backed in-game config screen. Hand-wires each public static field in
 * {@link Config} as a boolean/double entry. Saving triggers {@link Config#save()} which
 * writes the {@code usefultoolsmod.properties} file under {@code config/}.
 *
 * <p>This class only compiles against {@code cloth-config-fabric}; callers must gate
 * construction behind a {@code FabricLoader.getInstance().isModLoaded("cloth-config")}
 * check (see {@link ClientConfigRegistration}).
 */
public final class UsefulToolsConfigScreen {
    private UsefulToolsConfigScreen() {}

    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("The Useful Tools Mod"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder eb = builder.entryBuilder();

        ConfigCategory sets = builder.getOrCreateCategory(Component.literal("Tool/Armor sets"));
        sets.addEntry(toggle(eb, "Explosives", Config.explosivesEnabled, v -> Config.explosivesEnabled = v));
        sets.addEntry(toggle(eb, "Obsidian", Config.obsidianEnabled, v -> Config.obsidianEnabled = v));
        sets.addEntry(toggle(eb, "Emerald", Config.emeraldEnabled, v -> Config.emeraldEnabled = v));
        sets.addEntry(toggle(eb, "Lapis", Config.lapisEnabled, v -> Config.lapisEnabled = v));
        sets.addEntry(toggle(eb, "Ferrous Gold", Config.ferrousGoldEnabled, v -> Config.ferrousGoldEnabled = v));
        sets.addEntry(toggle(eb, "Hardened Redstone", Config.hardenedRedstoneEnabled, v -> Config.hardenedRedstoneEnabled = v));
        sets.addEntry(toggle(eb, "Hardened Glowstone", Config.hardenedGlowstoneEnabled, v -> Config.hardenedGlowstoneEnabled = v));
        sets.addEntry(toggle(eb, "Overpower", Config.overpowerEnabled, v -> Config.overpowerEnabled = v));
        sets.addEntry(toggle(eb, "Ghost (spawn)", Config.ghostEnabled, v -> Config.ghostEnabled = v));
        sets.addEntry(toggle(eb, "Spectral Infuser", Config.spectralInfuserEnabled, v -> Config.spectralInfuserEnabled = v));
        sets.addEntry(toggle(eb, "Ectoplasm set", Config.ectoplasmSetEnabled, v -> Config.ectoplasmSetEnabled = v));
        sets.addEntry(toggle(eb, "Raw Metal Rough", Config.rawMetalRoughEnabled, v -> Config.rawMetalRoughEnabled = v));
        sets.addEntry(toggle(eb, "Rough Crystal", Config.roughCrystalEnabled, v -> Config.roughCrystalEnabled = v));
        sets.addEntry(toggle(eb, "Snow", Config.snowEnabled, v -> Config.snowEnabled = v));
        sets.addEntry(toggle(eb, "Polished Crystal", Config.polishedCrystalEnabled, v -> Config.polishedCrystalEnabled = v));
        sets.addEntry(toggle(eb, "Ice / Glacial", Config.iceEnabled, v -> Config.iceEnabled = v));
        sets.addEntry(toggle(eb, "Polished Prismarine", Config.pprismEnabled, v -> Config.pprismEnabled = v));
        sets.addEntry(toggle(eb, "Flint", Config.flintEnabled, v -> Config.flintEnabled = v));
        sets.addEntry(toggle(eb, "Flint-Iron (FNI)", Config.fniEnabled, v -> Config.fniEnabled = v));
        sets.addEntry(toggle(eb, "Wood variants", Config.woodVariantsEnabled, v -> Config.woodVariantsEnabled = v));
        sets.addEntry(toggle(eb, "Stone variants", Config.stoneVariantsEnabled, v -> Config.stoneVariantsEnabled = v));
        sets.addEntry(toggle(eb, "Leather", Config.leatherEnabled, v -> Config.leatherEnabled = v));
        sets.addEntry(toggle(eb, "Coal", Config.coalEnabled, v -> Config.coalEnabled = v));
        sets.addEntry(toggle(eb, "Cake", Config.cakeEnabled, v -> Config.cakeEnabled = v));

        ConfigCategory food = builder.getOrCreateCategory(Component.literal("Food sets"));
        food.addEntry(toggle(eb, "Food hunger drain (global)", Config.foodHungerDrain, v -> Config.foodHungerDrain = v));
        food.addEntry(toggle(eb, "Bread", Config.breadEnabled, v -> Config.breadEnabled = v));
        food.addEntry(toggle(eb, "Dried Kelp", Config.driedKelpEnabled, v -> Config.driedKelpEnabled = v));
        food.addEntry(toggle(eb, "Rotten Flesh", Config.rottenFleshEnabled, v -> Config.rottenFleshEnabled = v));
        food.addEntry(toggle(eb, "Melon", Config.melonEnabled, v -> Config.melonEnabled = v));
        food.addEntry(toggle(eb, "Sweet Berry", Config.sweetBerryEnabled, v -> Config.sweetBerryEnabled = v));
        food.addEntry(toggle(eb, "Pumpkin Pie", Config.pumpkinPieEnabled, v -> Config.pumpkinPieEnabled = v));
        food.addEntry(toggle(eb, "Mushroom", Config.mushroomEnabled, v -> Config.mushroomEnabled = v));
        food.addEntry(toggle(eb, "Pufferfish", Config.pufferfishEnabled, v -> Config.pufferfishEnabled = v));
        food.addEntry(toggle(eb, "Honey", Config.honeyEnabled, v -> Config.honeyEnabled = v));
        food.addEntry(toggle(eb, "Chorus Fruit", Config.chorusFruitEnabled, v -> Config.chorusFruitEnabled = v));
        food.addEntry(toggle(eb, "Golden Apple", Config.goldenAppleEnabled, v -> Config.goldenAppleEnabled = v));

        ConfigCategory vanilla = builder.getOrCreateCategory(Component.literal("Vanilla material sets"));
        vanilla.addEntry(toggle(eb, "Paper", Config.paperEnabled, v -> Config.paperEnabled = v));
        vanilla.addEntry(toggle(eb, "Feather", Config.featherEnabled, v -> Config.featherEnabled = v));
        vanilla.addEntry(toggle(eb, "Glass", Config.glassEnabled, v -> Config.glassEnabled = v));
        vanilla.addEntry(toggle(eb, "Rabbit Hide", Config.rabbitHideEnabled, v -> Config.rabbitHideEnabled = v));
        vanilla.addEntry(toggle(eb, "Cactus", Config.cactusEnabled, v -> Config.cactusEnabled = v));
        vanilla.addEntry(toggle(eb, "Sponge", Config.spongeEnabled, v -> Config.spongeEnabled = v));
        vanilla.addEntry(toggle(eb, "Bone", Config.boneEnabled, v -> Config.boneEnabled = v));
        vanilla.addEntry(toggle(eb, "Clay", Config.clayEnabled, v -> Config.clayEnabled = v));
        vanilla.addEntry(toggle(eb, "Nether Wart", Config.netherWartEnabled, v -> Config.netherWartEnabled = v));
        vanilla.addEntry(toggle(eb, "Brick", Config.brickEnabled, v -> Config.brickEnabled = v));
        vanilla.addEntry(toggle(eb, "Nether Brick", Config.netherBrickEnabled, v -> Config.netherBrickEnabled = v));
        vanilla.addEntry(toggle(eb, "Pointed Dripstone", Config.dripstoneEnabled, v -> Config.dripstoneEnabled = v));
        vanilla.addEntry(toggle(eb, "Copper", Config.copperEnabled, v -> Config.copperEnabled = v));
        vanilla.addEntry(toggle(eb, "Phantom Membrane", Config.phantomEnabled, v -> Config.phantomEnabled = v));
        vanilla.addEntry(toggle(eb, "Magma Cream", Config.magmaCreamEnabled, v -> Config.magmaCreamEnabled = v));
        vanilla.addEntry(toggle(eb, "Slime", Config.slimeEnabled, v -> Config.slimeEnabled = v));
        vanilla.addEntry(toggle(eb, "Blaze Rod", Config.blazeEnabled, v -> Config.blazeEnabled = v));
        vanilla.addEntry(toggle(eb, "Nautilus Shell", Config.nautilusEnabled, v -> Config.nautilusEnabled = v));
        vanilla.addEntry(toggle(eb, "Purpur", Config.purpurEnabled, v -> Config.purpurEnabled = v));
        vanilla.addEntry(toggle(eb, "Ghast Tear", Config.ghastTearEnabled, v -> Config.ghastTearEnabled = v));
        vanilla.addEntry(toggle(eb, "Eye of Ender", Config.eyeOfEnderEnabled, v -> Config.eyeOfEnderEnabled = v));
        vanilla.addEntry(toggle(eb, "Shulker Shell", Config.shulkerEnabled, v -> Config.shulkerEnabled = v));
        vanilla.addEntry(toggle(eb, "Turtle Scute", Config.turtleScuteEnabled, v -> Config.turtleScuteEnabled = v));
        vanilla.addEntry(toggle(eb, "Echo Shard", Config.echoShardEnabled, v -> Config.echoShardEnabled = v));
        vanilla.addEntry(toggle(eb, "Dragon's Breath", Config.dragonBreathEnabled, v -> Config.dragonBreathEnabled = v));

        ConfigCategory effects = builder.getOrCreateCategory(Component.literal("Effect flags"));
        effects.addEntry(toggle(eb, "Overpower tool effects", Config.opToolEffectsEnabled, v -> Config.opToolEffectsEnabled = v));
        effects.addEntry(toggle(eb, "Overpower armor effects", Config.opArmorEffectsEnabled, v -> Config.opArmorEffectsEnabled = v));
        effects.addEntry(eb.startDoubleField(Component.literal("Ghost spawn chance"), Config.ghostSpawnChance)
                .setDefaultValue(0.15)
                .setMin(0.0).setMax(1.0)
                .setSaveConsumer(v -> Config.ghostSpawnChance = v).build());
        effects.addEntry(toggle(eb, "Snow melt effects", Config.snowMeltEffects, v -> Config.snowMeltEffects = v));
        effects.addEntry(toggle(eb, "Ice effects", Config.iceEffects, v -> Config.iceEffects = v));
        effects.addEntry(toggle(eb, "Prismarine water effects", Config.pprismWaterEffects, v -> Config.pprismWaterEffects = v));
        effects.addEntry(toggle(eb, "FNI fire effects", Config.fniFireEffects, v -> Config.fniFireEffects = v));
        effects.addEntry(toggle(eb, "Coal fire effects", Config.coalFireEffects, v -> Config.coalFireEffects = v));
        effects.addEntry(toggle(eb, "Infused tool effects", Config.infusedToolEffects, v -> Config.infusedToolEffects = v));
        effects.addEntry(toggle(eb, "Ectoplasm ghost avoidance", Config.ectoplasmGhostAvoidance, v -> Config.ectoplasmGhostAvoidance = v));
        effects.addEntry(toggle(eb, "Ectoplasm wall phasing", Config.ectoplasmWallPhasing, v -> Config.ectoplasmWallPhasing = v));

        return builder.build();
    }

    private static me.shedaniel.clothconfig2.api.AbstractConfigListEntry<?> toggle(
            ConfigEntryBuilder eb, String label, boolean current,
            java.util.function.Consumer<Boolean> setter) {
        return eb.startBooleanToggle(Component.literal(label), current)
                .setDefaultValue(true)
                .setSaveConsumer(setter::accept)
                .build();
    }
}
