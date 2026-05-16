package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.worldgen.ModConfiguredFeatures;
import com.stonytark.usefultoolsmod.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

/**
 * Datapack-registry provider — emits the JSON for our configured + placed
 * features. Fabric handles biome injection programmatically via
 * {@code BiomeModifications.create(...)} (see
 * {@code com.stonytark.usefultoolsmod.worldgen.ModBiomeModifications}), so
 * unlike the NeoForge sibling there is no biome-modifier registry to
 * dispatch here.
 */
public class ModDatapackEntries extends FabricDynamicRegistryProvider {

    public ModDatapackEntries(FabricPackOutput output,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "Useful Tools Datapack Entries";
    }
}
