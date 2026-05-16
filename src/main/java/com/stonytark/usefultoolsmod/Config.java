package com.stonytark.usefultoolsmod;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Mod configuration — Fabric port.
 *
 * <p>The NeoForge sibling port uses {@code ModConfigSpec} + TOML; on Fabric we keep the same
 * public static field API but back it with a simple {@link Properties} file at
 * {@code <gameDir>/config/usefultoolsmod.properties}. Missing keys fall back to the in-class
 * defaults below, and the file is written out (with default values) on first launch so users
 * have a discoverable starting point. Cloth Config's in-game screen edits these same fields.
 */
public class Config {

    private static final String FILE_NAME = "usefultoolsmod.properties";

    // ── Set toggles (default true so everything works out of the box) ──────────

    public static boolean explosivesEnabled = true;
    public static boolean obsidianEnabled = true;
    public static boolean emeraldEnabled = true;
    public static boolean lapisEnabled = true;
    public static boolean ferrousGoldEnabled = true;
    public static boolean hardenedRedstoneEnabled = true;
    public static boolean hardenedGlowstoneEnabled = true;
    public static boolean overpowerEnabled = true;
    public static boolean ghostEnabled = true;
    public static boolean spectralInfuserEnabled = true;
    public static boolean infusedToolEffects = true;
    public static boolean rawMetalRoughEnabled = true;
    public static boolean roughCrystalEnabled = true;
    public static boolean snowEnabled = true;
    public static boolean polishedCrystalEnabled = true;
    public static boolean iceEnabled = true;
    public static boolean pprismEnabled = true;
    public static boolean flintEnabled = true;
    public static boolean fniEnabled = true;
    public static boolean woodVariantsEnabled = true;
    public static boolean stoneVariantsEnabled = true;
    public static boolean paperEnabled = true;
    public static boolean paperEffects = true;
    public static boolean featherEnabled = true;
    public static boolean featherEffects = true;
    public static boolean glassEnabled = true;
    public static boolean glassEffects = true;
    public static boolean rabbitHideEnabled = true;
    public static boolean rabbitHideEffects = true;
    public static boolean cactusEnabled = true;
    public static boolean cactusEffects = true;
    public static boolean spongeEnabled = true;
    public static boolean spongeEffects = true;
    public static boolean boneEnabled = true;
    public static boolean boneEffects = true;
    public static boolean clayEnabled = true;
    public static boolean clayEffects = true;
    public static boolean netherWartEnabled = true;
    public static boolean netherWartEffects = true;
    public static boolean brickEnabled = true;
    public static boolean netherBrickEnabled = true;
    public static boolean netherBrickEffects = true;
    public static boolean dripstoneEnabled = true;
    public static boolean dripstoneEffects = true;
    public static boolean copperEnabled = true;
    public static boolean copperEffects = true;
    public static boolean phantomEnabled = true;
    public static boolean phantomEffects = true;
    public static boolean magmaCreamEnabled = true;
    public static boolean magmaCreamEffects = true;
    public static boolean slimeEnabled = true;
    public static boolean slimeEffects = true;
    public static boolean blazeEnabled = true;
    public static boolean blazeEffects = true;
    public static boolean nautilusEnabled = true;
    public static boolean nautilusEffects = true;
    public static boolean purpurEnabled = true;
    public static boolean purpurEffects = true;
    public static boolean ghastTearEnabled = true;
    public static boolean ghastTearEffects = true;
    public static boolean eyeOfEnderEnabled = true;
    public static boolean eyeOfEnderEffects = true;
    public static boolean shulkerEnabled = true;
    public static boolean shulkerEffects = true;
    public static boolean turtleScuteEnabled = true;
    public static boolean turtleScuteEffects = true;
    public static boolean echoShardEnabled = true;
    public static boolean echoShardEffects = true;
    public static boolean dragonBreathEnabled = true;
    public static boolean dragonBreathEffects = true;
    public static boolean leatherEnabled = true;
    public static boolean coalEnabled = true;
    public static boolean cakeEnabled = true;
    public static boolean foodHungerDrain = true;
    public static boolean breadEnabled = true;
    public static boolean breadArmorEffects = true;
    public static boolean driedKelpEnabled = true;
    public static boolean driedKelpArmorEffects = true;
    public static boolean rottenFleshEnabled = true;
    public static boolean rottenFleshArmorEffects = true;
    public static boolean rottenFleshUndeadNeutral = true;
    public static boolean melonEnabled = true;
    public static boolean melonArmorEffects = true;
    public static boolean sweetBerryEnabled = true;
    public static boolean sweetBerryArmorEffects = true;
    public static boolean sweetBerryThorns = true;
    public static boolean pumpkinPieEnabled = true;
    public static boolean pumpkinPieArmorEffects = true;
    public static boolean pumpkinPieEndermanAvoidance = true;
    public static boolean mushroomEnabled = true;
    public static boolean mushroomArmorEffects = true;
    public static boolean mushroomSporeCloud = true;
    public static boolean pufferfishEnabled = true;
    public static boolean pufferfishArmorEffects = true;
    public static boolean pufferfishPoisonAura = true;
    public static boolean honeyEnabled = true;
    public static boolean honeyArmorEffects = true;
    public static boolean honeySticky = true;
    public static boolean chorusFruitEnabled = true;
    public static boolean chorusFruitArmorEffects = true;
    public static boolean chorusFruitTeleport = true;
    public static boolean goldenAppleEnabled = true;
    public static boolean goldenAppleArmorEffects = true;
    public static boolean ectoplasmSetEnabled = true;

    // ── Effect flags ───────────────────────────────────────────────────────────

    public static boolean opToolEffectsEnabled = true;
    public static boolean opArmorEffectsEnabled = true;
    public static double ghostSpawnChance = 0.15;
    public static boolean snowMeltEffects = true;
    public static boolean iceEffects = true;
    public static boolean pprismWaterEffects = true;
    public static boolean fniFireEffects = true;
    public static boolean coalFireEffects = true;
    public static boolean cakeHungerEffects = true;
    public static boolean cakeArmorEffects = true;
    public static boolean ectoplasmGhostAvoidance = true;
    public static boolean ectoplasmWallPhasing = true;

    /**
     * Read the on-disk properties file (if any) into the static fields. If the file does not
     * exist, write out the current defaults so users have a discoverable starting point.
     */
    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        Properties props = new Properties();
        if (Files.exists(file)) {
            try (var in = Files.newInputStream(file)) {
                props.load(in);
            } catch (IOException e) {
                UsefultoolsMod.LOGGER.error("Failed to read {}: {}", file, e.toString());
            }
            applyFrom(props);
        } else {
            try {
                Files.createDirectories(file.getParent());
                writeDefaults(file);
            } catch (IOException e) {
                UsefultoolsMod.LOGGER.error("Failed to write default {}: {}", file, e.toString());
            }
        }
    }

    /** Re-applies the current field values to the on-disk file. Called by the Cloth Config screen. */
    public static void save() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        try {
            Files.createDirectories(file.getParent());
            writeDefaults(file);
        } catch (IOException e) {
            UsefultoolsMod.LOGGER.error("Failed to save {}: {}", file, e.toString());
        }
    }

    private static void applyFrom(Properties p) {
        explosivesEnabled = bool(p, "explosivesEnabled", explosivesEnabled);
        obsidianEnabled = bool(p, "obsidianEnabled", obsidianEnabled);
        emeraldEnabled = bool(p, "emeraldEnabled", emeraldEnabled);
        lapisEnabled = bool(p, "lapisEnabled", lapisEnabled);
        ferrousGoldEnabled = bool(p, "ferrousGoldEnabled", ferrousGoldEnabled);
        hardenedRedstoneEnabled = bool(p, "hardenedRedstoneEnabled", hardenedRedstoneEnabled);
        hardenedGlowstoneEnabled = bool(p, "hardenedGlowstoneEnabled", hardenedGlowstoneEnabled);
        overpowerEnabled = bool(p, "overpowerEnabled", overpowerEnabled);
        ghostEnabled = bool(p, "ghostEnabled", ghostEnabled);
        spectralInfuserEnabled = bool(p, "spectralInfuserEnabled", spectralInfuserEnabled);
        infusedToolEffects = bool(p, "infusedToolEffects", infusedToolEffects);
        rawMetalRoughEnabled = bool(p, "rawMetalRoughEnabled", rawMetalRoughEnabled);
        roughCrystalEnabled = bool(p, "roughCrystalEnabled", roughCrystalEnabled);
        snowEnabled = bool(p, "snowEnabled", snowEnabled);
        polishedCrystalEnabled = bool(p, "polishedCrystalEnabled", polishedCrystalEnabled);
        iceEnabled = bool(p, "iceEnabled", iceEnabled);
        pprismEnabled = bool(p, "pprismEnabled", pprismEnabled);
        flintEnabled = bool(p, "flintEnabled", flintEnabled);
        fniEnabled = bool(p, "fniEnabled", fniEnabled);
        woodVariantsEnabled = bool(p, "woodVariantsEnabled", woodVariantsEnabled);
        stoneVariantsEnabled = bool(p, "stoneVariantsEnabled", stoneVariantsEnabled);
        paperEnabled = bool(p, "paperEnabled", paperEnabled);
        paperEffects = bool(p, "paperEffects", paperEffects);
        featherEnabled = bool(p, "featherEnabled", featherEnabled);
        featherEffects = bool(p, "featherEffects", featherEffects);
        glassEnabled = bool(p, "glassEnabled", glassEnabled);
        glassEffects = bool(p, "glassEffects", glassEffects);
        rabbitHideEnabled = bool(p, "rabbitHideEnabled", rabbitHideEnabled);
        rabbitHideEffects = bool(p, "rabbitHideEffects", rabbitHideEffects);
        cactusEnabled = bool(p, "cactusEnabled", cactusEnabled);
        cactusEffects = bool(p, "cactusEffects", cactusEffects);
        spongeEnabled = bool(p, "spongeEnabled", spongeEnabled);
        spongeEffects = bool(p, "spongeEffects", spongeEffects);
        boneEnabled = bool(p, "boneEnabled", boneEnabled);
        boneEffects = bool(p, "boneEffects", boneEffects);
        clayEnabled = bool(p, "clayEnabled", clayEnabled);
        clayEffects = bool(p, "clayEffects", clayEffects);
        netherWartEnabled = bool(p, "netherWartEnabled", netherWartEnabled);
        netherWartEffects = bool(p, "netherWartEffects", netherWartEffects);
        brickEnabled = bool(p, "brickEnabled", brickEnabled);
        netherBrickEnabled = bool(p, "netherBrickEnabled", netherBrickEnabled);
        netherBrickEffects = bool(p, "netherBrickEffects", netherBrickEffects);
        dripstoneEnabled = bool(p, "dripstoneEnabled", dripstoneEnabled);
        dripstoneEffects = bool(p, "dripstoneEffects", dripstoneEffects);
        copperEnabled = bool(p, "copperEnabled", copperEnabled);
        copperEffects = bool(p, "copperEffects", copperEffects);
        phantomEnabled = bool(p, "phantomEnabled", phantomEnabled);
        phantomEffects = bool(p, "phantomEffects", phantomEffects);
        magmaCreamEnabled = bool(p, "magmaCreamEnabled", magmaCreamEnabled);
        magmaCreamEffects = bool(p, "magmaCreamEffects", magmaCreamEffects);
        slimeEnabled = bool(p, "slimeEnabled", slimeEnabled);
        slimeEffects = bool(p, "slimeEffects", slimeEffects);
        blazeEnabled = bool(p, "blazeEnabled", blazeEnabled);
        blazeEffects = bool(p, "blazeEffects", blazeEffects);
        nautilusEnabled = bool(p, "nautilusEnabled", nautilusEnabled);
        nautilusEffects = bool(p, "nautilusEffects", nautilusEffects);
        purpurEnabled = bool(p, "purpurEnabled", purpurEnabled);
        purpurEffects = bool(p, "purpurEffects", purpurEffects);
        ghastTearEnabled = bool(p, "ghastTearEnabled", ghastTearEnabled);
        ghastTearEffects = bool(p, "ghastTearEffects", ghastTearEffects);
        eyeOfEnderEnabled = bool(p, "eyeOfEnderEnabled", eyeOfEnderEnabled);
        eyeOfEnderEffects = bool(p, "eyeOfEnderEffects", eyeOfEnderEffects);
        shulkerEnabled = bool(p, "shulkerEnabled", shulkerEnabled);
        shulkerEffects = bool(p, "shulkerEffects", shulkerEffects);
        turtleScuteEnabled = bool(p, "turtleScuteEnabled", turtleScuteEnabled);
        turtleScuteEffects = bool(p, "turtleScuteEffects", turtleScuteEffects);
        echoShardEnabled = bool(p, "echoShardEnabled", echoShardEnabled);
        echoShardEffects = bool(p, "echoShardEffects", echoShardEffects);
        dragonBreathEnabled = bool(p, "dragonBreathEnabled", dragonBreathEnabled);
        dragonBreathEffects = bool(p, "dragonBreathEffects", dragonBreathEffects);
        leatherEnabled = bool(p, "leatherEnabled", leatherEnabled);
        coalEnabled = bool(p, "coalEnabled", coalEnabled);
        cakeEnabled = bool(p, "cakeEnabled", cakeEnabled);
        foodHungerDrain = bool(p, "foodHungerDrain", foodHungerDrain);
        breadEnabled = bool(p, "breadEnabled", breadEnabled);
        breadArmorEffects = bool(p, "breadArmorEffects", breadArmorEffects);
        driedKelpEnabled = bool(p, "driedKelpEnabled", driedKelpEnabled);
        driedKelpArmorEffects = bool(p, "driedKelpArmorEffects", driedKelpArmorEffects);
        rottenFleshEnabled = bool(p, "rottenFleshEnabled", rottenFleshEnabled);
        rottenFleshArmorEffects = bool(p, "rottenFleshArmorEffects", rottenFleshArmorEffects);
        rottenFleshUndeadNeutral = bool(p, "rottenFleshUndeadNeutral", rottenFleshUndeadNeutral);
        melonEnabled = bool(p, "melonEnabled", melonEnabled);
        melonArmorEffects = bool(p, "melonArmorEffects", melonArmorEffects);
        sweetBerryEnabled = bool(p, "sweetBerryEnabled", sweetBerryEnabled);
        sweetBerryArmorEffects = bool(p, "sweetBerryArmorEffects", sweetBerryArmorEffects);
        sweetBerryThorns = bool(p, "sweetBerryThorns", sweetBerryThorns);
        pumpkinPieEnabled = bool(p, "pumpkinPieEnabled", pumpkinPieEnabled);
        pumpkinPieArmorEffects = bool(p, "pumpkinPieArmorEffects", pumpkinPieArmorEffects);
        pumpkinPieEndermanAvoidance = bool(p, "pumpkinPieEndermanAvoidance", pumpkinPieEndermanAvoidance);
        mushroomEnabled = bool(p, "mushroomEnabled", mushroomEnabled);
        mushroomArmorEffects = bool(p, "mushroomArmorEffects", mushroomArmorEffects);
        mushroomSporeCloud = bool(p, "mushroomSporeCloud", mushroomSporeCloud);
        pufferfishEnabled = bool(p, "pufferfishEnabled", pufferfishEnabled);
        pufferfishArmorEffects = bool(p, "pufferfishArmorEffects", pufferfishArmorEffects);
        pufferfishPoisonAura = bool(p, "pufferfishPoisonAura", pufferfishPoisonAura);
        honeyEnabled = bool(p, "honeyEnabled", honeyEnabled);
        honeyArmorEffects = bool(p, "honeyArmorEffects", honeyArmorEffects);
        honeySticky = bool(p, "honeySticky", honeySticky);
        chorusFruitEnabled = bool(p, "chorusFruitEnabled", chorusFruitEnabled);
        chorusFruitArmorEffects = bool(p, "chorusFruitArmorEffects", chorusFruitArmorEffects);
        chorusFruitTeleport = bool(p, "chorusFruitTeleport", chorusFruitTeleport);
        goldenAppleEnabled = bool(p, "goldenAppleEnabled", goldenAppleEnabled);
        goldenAppleArmorEffects = bool(p, "goldenAppleArmorEffects", goldenAppleArmorEffects);
        ectoplasmSetEnabled = bool(p, "ectoplasmSetEnabled", ectoplasmSetEnabled);
        opToolEffectsEnabled = bool(p, "opToolEffectsEnabled", opToolEffectsEnabled);
        opArmorEffectsEnabled = bool(p, "opArmorEffectsEnabled", opArmorEffectsEnabled);
        ghostSpawnChance = dbl(p, "ghostSpawnChance", ghostSpawnChance);
        snowMeltEffects = bool(p, "snowMeltEffects", snowMeltEffects);
        iceEffects = bool(p, "iceEffects", iceEffects);
        pprismWaterEffects = bool(p, "pprismWaterEffects", pprismWaterEffects);
        fniFireEffects = bool(p, "fniFireEffects", fniFireEffects);
        coalFireEffects = bool(p, "coalFireEffects", coalFireEffects);
        cakeHungerEffects = bool(p, "cakeHungerEffects", cakeHungerEffects);
        cakeArmorEffects = bool(p, "cakeArmorEffects", cakeArmorEffects);
        ectoplasmGhostAvoidance = bool(p, "ectoplasmGhostAvoidance", ectoplasmGhostAvoidance);
        ectoplasmWallPhasing = bool(p, "ectoplasmWallPhasing", ectoplasmWallPhasing);
    }

    private static void writeDefaults(Path file) throws IOException {
        // Insertion-ordered so the on-disk file keeps a stable, hand-friendly layout.
        Map<String, String> out = new LinkedHashMap<>();
        out.put("explosivesEnabled", String.valueOf(explosivesEnabled));
        out.put("obsidianEnabled", String.valueOf(obsidianEnabled));
        out.put("emeraldEnabled", String.valueOf(emeraldEnabled));
        out.put("lapisEnabled", String.valueOf(lapisEnabled));
        out.put("ferrousGoldEnabled", String.valueOf(ferrousGoldEnabled));
        out.put("hardenedRedstoneEnabled", String.valueOf(hardenedRedstoneEnabled));
        out.put("hardenedGlowstoneEnabled", String.valueOf(hardenedGlowstoneEnabled));
        out.put("overpowerEnabled", String.valueOf(overpowerEnabled));
        out.put("opToolEffectsEnabled", String.valueOf(opToolEffectsEnabled));
        out.put("opArmorEffectsEnabled", String.valueOf(opArmorEffectsEnabled));
        out.put("ghostEnabled", String.valueOf(ghostEnabled));
        out.put("ghostSpawnChance", String.valueOf(ghostSpawnChance));
        out.put("spectralInfuserEnabled", String.valueOf(spectralInfuserEnabled));
        out.put("infusedToolEffects", String.valueOf(infusedToolEffects));
        out.put("ectoplasmSetEnabled", String.valueOf(ectoplasmSetEnabled));
        out.put("ectoplasmGhostAvoidance", String.valueOf(ectoplasmGhostAvoidance));
        out.put("ectoplasmWallPhasing", String.valueOf(ectoplasmWallPhasing));
        out.put("rawMetalRoughEnabled", String.valueOf(rawMetalRoughEnabled));
        out.put("roughCrystalEnabled", String.valueOf(roughCrystalEnabled));
        out.put("snowEnabled", String.valueOf(snowEnabled));
        out.put("snowMeltEffects", String.valueOf(snowMeltEffects));
        out.put("polishedCrystalEnabled", String.valueOf(polishedCrystalEnabled));
        out.put("iceEnabled", String.valueOf(iceEnabled));
        out.put("iceEffects", String.valueOf(iceEffects));
        out.put("pprismEnabled", String.valueOf(pprismEnabled));
        out.put("pprismWaterEffects", String.valueOf(pprismWaterEffects));
        out.put("flintEnabled", String.valueOf(flintEnabled));
        out.put("fniEnabled", String.valueOf(fniEnabled));
        out.put("fniFireEffects", String.valueOf(fniFireEffects));
        out.put("woodVariantsEnabled", String.valueOf(woodVariantsEnabled));
        out.put("stoneVariantsEnabled", String.valueOf(stoneVariantsEnabled));
        out.put("paperEnabled", String.valueOf(paperEnabled));
        out.put("paperEffects", String.valueOf(paperEffects));
        out.put("featherEnabled", String.valueOf(featherEnabled));
        out.put("featherEffects", String.valueOf(featherEffects));
        out.put("glassEnabled", String.valueOf(glassEnabled));
        out.put("glassEffects", String.valueOf(glassEffects));
        out.put("rabbitHideEnabled", String.valueOf(rabbitHideEnabled));
        out.put("rabbitHideEffects", String.valueOf(rabbitHideEffects));
        out.put("cactusEnabled", String.valueOf(cactusEnabled));
        out.put("cactusEffects", String.valueOf(cactusEffects));
        out.put("spongeEnabled", String.valueOf(spongeEnabled));
        out.put("spongeEffects", String.valueOf(spongeEffects));
        out.put("boneEnabled", String.valueOf(boneEnabled));
        out.put("boneEffects", String.valueOf(boneEffects));
        out.put("clayEnabled", String.valueOf(clayEnabled));
        out.put("clayEffects", String.valueOf(clayEffects));
        out.put("netherWartEnabled", String.valueOf(netherWartEnabled));
        out.put("netherWartEffects", String.valueOf(netherWartEffects));
        out.put("brickEnabled", String.valueOf(brickEnabled));
        out.put("netherBrickEnabled", String.valueOf(netherBrickEnabled));
        out.put("netherBrickEffects", String.valueOf(netherBrickEffects));
        out.put("dripstoneEnabled", String.valueOf(dripstoneEnabled));
        out.put("dripstoneEffects", String.valueOf(dripstoneEffects));
        out.put("copperEnabled", String.valueOf(copperEnabled));
        out.put("copperEffects", String.valueOf(copperEffects));
        out.put("phantomEnabled", String.valueOf(phantomEnabled));
        out.put("phantomEffects", String.valueOf(phantomEffects));
        out.put("magmaCreamEnabled", String.valueOf(magmaCreamEnabled));
        out.put("magmaCreamEffects", String.valueOf(magmaCreamEffects));
        out.put("slimeEnabled", String.valueOf(slimeEnabled));
        out.put("slimeEffects", String.valueOf(slimeEffects));
        out.put("blazeEnabled", String.valueOf(blazeEnabled));
        out.put("blazeEffects", String.valueOf(blazeEffects));
        out.put("nautilusEnabled", String.valueOf(nautilusEnabled));
        out.put("nautilusEffects", String.valueOf(nautilusEffects));
        out.put("purpurEnabled", String.valueOf(purpurEnabled));
        out.put("purpurEffects", String.valueOf(purpurEffects));
        out.put("ghastTearEnabled", String.valueOf(ghastTearEnabled));
        out.put("ghastTearEffects", String.valueOf(ghastTearEffects));
        out.put("eyeOfEnderEnabled", String.valueOf(eyeOfEnderEnabled));
        out.put("eyeOfEnderEffects", String.valueOf(eyeOfEnderEffects));
        out.put("shulkerEnabled", String.valueOf(shulkerEnabled));
        out.put("shulkerEffects", String.valueOf(shulkerEffects));
        out.put("turtleScuteEnabled", String.valueOf(turtleScuteEnabled));
        out.put("turtleScuteEffects", String.valueOf(turtleScuteEffects));
        out.put("echoShardEnabled", String.valueOf(echoShardEnabled));
        out.put("echoShardEffects", String.valueOf(echoShardEffects));
        out.put("dragonBreathEnabled", String.valueOf(dragonBreathEnabled));
        out.put("dragonBreathEffects", String.valueOf(dragonBreathEffects));
        out.put("leatherEnabled", String.valueOf(leatherEnabled));
        out.put("coalEnabled", String.valueOf(coalEnabled));
        out.put("coalFireEffects", String.valueOf(coalFireEffects));
        out.put("cakeEnabled", String.valueOf(cakeEnabled));
        out.put("cakeHungerEffects", String.valueOf(cakeHungerEffects));
        out.put("cakeArmorEffects", String.valueOf(cakeArmorEffects));
        out.put("foodHungerDrain", String.valueOf(foodHungerDrain));
        out.put("breadEnabled", String.valueOf(breadEnabled));
        out.put("breadArmorEffects", String.valueOf(breadArmorEffects));
        out.put("driedKelpEnabled", String.valueOf(driedKelpEnabled));
        out.put("driedKelpArmorEffects", String.valueOf(driedKelpArmorEffects));
        out.put("rottenFleshEnabled", String.valueOf(rottenFleshEnabled));
        out.put("rottenFleshArmorEffects", String.valueOf(rottenFleshArmorEffects));
        out.put("rottenFleshUndeadNeutral", String.valueOf(rottenFleshUndeadNeutral));
        out.put("melonEnabled", String.valueOf(melonEnabled));
        out.put("melonArmorEffects", String.valueOf(melonArmorEffects));
        out.put("sweetBerryEnabled", String.valueOf(sweetBerryEnabled));
        out.put("sweetBerryArmorEffects", String.valueOf(sweetBerryArmorEffects));
        out.put("sweetBerryThorns", String.valueOf(sweetBerryThorns));
        out.put("pumpkinPieEnabled", String.valueOf(pumpkinPieEnabled));
        out.put("pumpkinPieArmorEffects", String.valueOf(pumpkinPieArmorEffects));
        out.put("pumpkinPieEndermanAvoidance", String.valueOf(pumpkinPieEndermanAvoidance));
        out.put("mushroomEnabled", String.valueOf(mushroomEnabled));
        out.put("mushroomArmorEffects", String.valueOf(mushroomArmorEffects));
        out.put("mushroomSporeCloud", String.valueOf(mushroomSporeCloud));
        out.put("pufferfishEnabled", String.valueOf(pufferfishEnabled));
        out.put("pufferfishArmorEffects", String.valueOf(pufferfishArmorEffects));
        out.put("pufferfishPoisonAura", String.valueOf(pufferfishPoisonAura));
        out.put("honeyEnabled", String.valueOf(honeyEnabled));
        out.put("honeyArmorEffects", String.valueOf(honeyArmorEffects));
        out.put("honeySticky", String.valueOf(honeySticky));
        out.put("chorusFruitEnabled", String.valueOf(chorusFruitEnabled));
        out.put("chorusFruitArmorEffects", String.valueOf(chorusFruitArmorEffects));
        out.put("chorusFruitTeleport", String.valueOf(chorusFruitTeleport));
        out.put("goldenAppleEnabled", String.valueOf(goldenAppleEnabled));
        out.put("goldenAppleArmorEffects", String.valueOf(goldenAppleArmorEffects));

        try (OutputStream os = Files.newOutputStream(file)) {
            Properties p = new Properties();
            p.putAll(out);
            p.store(os, "UsefulToolsMod config — edit values below or use the in-game Cloth Config screen.");
        }
    }

    private static boolean bool(Properties p, String key, boolean fallback) {
        String v = p.getProperty(key);
        if (v == null) return fallback;
        return Boolean.parseBoolean(v.trim());
    }

    private static double dbl(Properties p, String key, double fallback) {
        String v = p.getProperty(key);
        if (v == null) return fallback;
        try {
            return Double.parseDouble(v.trim());
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }
}
