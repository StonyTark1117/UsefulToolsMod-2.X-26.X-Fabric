package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Fabric block-loot-table provider. Equivalent to the NeoForge sibling's
 * {@code ModBlockLootTableProvider}: every mod-registered block drops itself,
 * except the four Ferrous Gold ores which use the silk-touch dispatch table.
 */
public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(FabricPackOutput output,
                                     CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.RGOLDBLOCK);
        dropSelf(ModBlocks.HRBLOCK);
        dropSelf(ModBlocks.SEMBLOCK);
        dropSelf(ModBlocks.SOBLOCK);
        dropSelf(ModBlocks.LBLOCK);
        dropSelf(ModBlocks.SPECTRAL_INFUSER);
        dropSelf(ModBlocks.HGLOW_BLOCK);
        dropSelf(ModBlocks.RAW_RGOLD_BLOCK);
        dropSelf(ModBlocks.ECTOPLASM_BLOCK);
        dropSelf(ModBlocks.REFINED_ECTOPLASM_BLOCK);
        dropSelf(ModBlocks.HARDENED_COAL_BLOCK);
        dropSelf(ModBlocks.COAL_DUST_BLOCK);
        dropSelf(ModBlocks.OBSHARD_BLOCK);
        dropSelf(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
        dropSelf(ModBlocks.GLACIAL_SHARD_BLOCK);
        dropSelf(ModBlocks.POLISHED_QUARTZ_BLOCK);
        dropSelf(ModBlocks.POLISHED_PRISMARINE_BLOCK);

        add(ModBlocks.RGOLDORE,
                block -> createOreDrop(ModBlocks.RGOLDORE, ModItems.RAW_RGOLD));
        add(ModBlocks.RGOLD_NETHER_ORE,
                block -> createOreDrop(ModBlocks.RGOLD_NETHER_ORE, ModItems.RAW_RGOLD));
        add(ModBlocks.RGOLD_END_ORE,
                block -> createOreDrop(ModBlocks.RGOLD_END_ORE, ModItems.RAW_RGOLD));
        add(ModBlocks.RGOLD_DEEPSLATE_ORE,
                block -> createOreDrop(ModBlocks.RGOLD_DEEPSLATE_ORE, ModItems.RAW_RGOLD));
    }
}
