package com.stonytark.usefultoolsmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * Fabric datagen entry point.
 *
 * <p>The mod currently ships the JSON outputs of its 1.21.1 datagen pipeline under
 * {@code src/main/generated/} — these are baked into the jar as resources, so the
 * mod runs without re-running datagen. Implementing live Fabric providers
 * ({@code FabricBlockLootTableProvider}, {@code FabricRecipeProvider},
 * {@code FabricAdvancementProvider}, {@code FabricTagProvider}, etc.) is intentionally
 * left for a follow-up; until then, this entry point is a no-op so the
 * {@code fabric-datagen} entrypoint can be invoked without errors.
 *
 * <p>The sibling NeoForge port at
 * {@code UsefulToolsMod-2.X-26.X-Neoforge/src/main/java/.../datagen/} has the full
 * provider set if you want a reference for porting them. NeoForge providers don't run
 * on Fabric (different superclasses, different injection points), so each one needs
 * to be rewritten against {@code net.fabricmc.fabric.api.datagen.v1.provider.*}.
 */
public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        // FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        // pack.addProvider(ModBlockLootTableProvider::new);
        // pack.addProvider(ModRecipeProvider::new);
        // pack.addProvider(ModAdvancementProvider::new);
        // pack.addProvider(ModBlockTagProvider::new);
        // pack.addProvider(ModItemTagProvider::new);
        // pack.addProvider(ModModelProvider::new);
    }
}
