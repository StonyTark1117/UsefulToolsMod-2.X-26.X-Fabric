package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagProvider(FabricPackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RGOLDBLOCK)
                .add(ModBlocks.HRBLOCK)
                .add(ModBlocks.RGOLDORE)
                .add(ModBlocks.RGOLD_NETHER_ORE)
                .add(ModBlocks.RGOLD_END_ORE)
                .add(ModBlocks.RGOLD_DEEPSLATE_ORE)
                .add(ModBlocks.SEMBLOCK)
                .add(ModBlocks.SOBLOCK)
                .add(ModBlocks.LBLOCK)
                .add(ModBlocks.SPECTRAL_INFUSER)
                .add(ModBlocks.ECTOPLASM_BLOCK)
                .add(ModBlocks.HGLOW_BLOCK)
                .add(ModBlocks.RAW_RGOLD_BLOCK)
                .add(ModBlocks.REFINED_ECTOPLASM_BLOCK)
                .add(ModBlocks.HARDENED_COAL_BLOCK)
                .add(ModBlocks.OBSHARD_BLOCK)
                .add(ModBlocks.CALCIFIED_AMETHYST_BLOCK)
                .add(ModBlocks.GLACIAL_SHARD_BLOCK)
                .add(ModBlocks.POLISHED_QUARTZ_BLOCK)
                .add(ModBlocks.POLISHED_PRISMARINE_BLOCK);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.COAL_DUST_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RGOLDBLOCK)
                .add(ModBlocks.LBLOCK)
                .add(ModBlocks.HRBLOCK)
                .add(ModBlocks.HGLOW_BLOCK)
                .add(ModBlocks.OBSHARD_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SOBLOCK)
                .add(ModBlocks.SEMBLOCK);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RGOLDORE)
                .add(ModBlocks.RGOLD_NETHER_ORE)
                .add(ModBlocks.RGOLD_END_ORE)
                .add(ModBlocks.RGOLD_DEEPSLATE_ORE)
                .add(ModBlocks.SPECTRAL_INFUSER)
                .add(ModBlocks.ECTOPLASM_BLOCK)
                .add(ModBlocks.RAW_RGOLD_BLOCK)
                .add(ModBlocks.REFINED_ECTOPLASM_BLOCK)
                .add(ModBlocks.CALCIFIED_AMETHYST_BLOCK)
                .add(ModBlocks.GLACIAL_SHARD_BLOCK)
                .add(ModBlocks.POLISHED_QUARTZ_BLOCK)
                .add(ModBlocks.POLISHED_PRISMARINE_BLOCK);

        // Mirror each NEEDS_*_TOOL membership into the corresponding vanilla
        // INCORRECT_FOR_*_TOOL tag. Without this, an iron pickaxe would
        // successfully drop SEMBLOCK / SOBLOCK (those tags drive the
        // tool-correctness check at mining time, not NEEDS_*_TOOL), and
        // WTHIT can't establish a tier ordering between mod and vanilla
        // tags (logs "Unsolvable tier comparison" at startup). Adding mod
        // blocks at the highest tier they belong in is sufficient because
        // vanilla chains `incorrect_for_stone_tool` ⊃ `incorrect_for_iron_tool`
        // ⊃ `incorrect_for_diamond_tool` by tag reference.
        valueLookupBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .add(ModBlocks.SOBLOCK)
                .add(ModBlocks.SEMBLOCK);

        valueLookupBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .add(ModBlocks.RGOLDBLOCK)
                .add(ModBlocks.LBLOCK)
                .add(ModBlocks.HRBLOCK)
                .add(ModBlocks.HGLOW_BLOCK)
                .add(ModBlocks.OBSHARD_BLOCK);

        valueLookupBuilder(ModTags.Blocks.NEEDS_HRED_TOOL).addOptionalTag(BlockTags.NEEDS_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_HGLOW_TOOL).addOptionalTag(BlockTags.NEEDS_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_JEM_TOOL).addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_JOB_TOOL).addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_OP_TOOL).addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL).add(Blocks.BEDROCK);
        valueLookupBuilder(ModTags.Blocks.NEEDS_RGOLD_TOOL).addOptionalTag(BlockTags.NEEDS_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_RLAPIS_TOOL).addOptionalTag(BlockTags.NEEDS_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_SEM_TOOL).addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.NEEDS_SOB_TOOL).addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL).add(Blocks.BEDROCK);

        valueLookupBuilder(ModTags.Blocks.INCORRECT_HRED_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_HGLOW_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_JEM_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_JOB_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_OP_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_RGOLD_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_RLAPIS_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_SEM_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_SOB_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).add(Blocks.BEDROCK);

        valueLookupBuilder(ModTags.Blocks.NEEDS_ECTO_TOOL).addOptionalTag(BlockTags.NEEDS_IRON_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_ECTO_TOOL).addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        valueLookupBuilder(ModTags.Blocks.NEEDS_RECTO_TOOL);
        valueLookupBuilder(ModTags.Blocks.INCORRECT_RECTO_TOOL);

        // Magnetization addon: blocks the Magnetic Excavator can rip out.
        valueLookupBuilder(ModTags.Blocks.MAGNETIZATION_FERROMAGNETIC)
                .add(ModBlocks.RGOLDORE)
                .add(ModBlocks.RGOLD_DEEPSLATE_ORE)
                .add(ModBlocks.RGOLD_NETHER_ORE)
                .add(ModBlocks.RGOLD_END_ORE)
                .add(ModBlocks.RGOLDBLOCK)
                .add(ModBlocks.RAW_RGOLD_BLOCK)
                .add(ModBlocks.LBLOCK)
                .add(ModBlocks.SEMBLOCK)
                .add(ModBlocks.SOBLOCK);
    }
}
