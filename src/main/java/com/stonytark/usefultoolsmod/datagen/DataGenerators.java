package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.worldgen.ModConfiguredFeatures;
import com.stonytark.usefultoolsmod.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

/**
 * Fabric datagen entry point. Re-emits the JSON shipped under
 * {@code src/main/generated/} from code instead of relying on the
 * imported snapshot from the NeoForge sibling port. Run with
 * {@code ./gradlew runDatagen} — output goes to {@code src/main/generated/}
 * and overwrites in place (see the {@code datagen} run config in
 * {@code build.gradle}).
 */
public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Server-data providers
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        // Client-asset providers (blockstates + item-models). Fabric runs both
        // client and server providers in the single runDatagen entry point;
        // the mixin pipeline on ModelProvider scopes validation to our mod-id.
        pack.addProvider(ModModelProvider::new);

        // Dynamic-registry (worldgen) provider — emits the configured/placed
        // feature JSON for our three reinforced-gold ores.
        pack.addProvider(ModDatapackEntries::new);

        // 847-node advancement tree.
        pack.addProvider(ModAdvancementProvider::new);
    }

    /**
     * Bootstrap our configured + placed feature registries into the lookup
     * provider that {@code FabricDynamicRegistryProvider#configure} receives.
     * Equivalent to the {@code RegistrySetBuilder} the NeoForge sibling hands
     * to {@code DatapackBuiltinEntriesProvider}.
     */
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
