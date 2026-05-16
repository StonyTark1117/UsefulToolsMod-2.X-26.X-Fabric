package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Recipe provider. Body ported verbatim from the NeoForge sibling — vanilla
 * {@link RecipeProvider} is the same on both loaders and exposes the same
 * builder DSL. The only structural difference is the wrapper: Fabric ships
 * {@link FabricRecipeProvider} which extends {@link RecipeProvider.Runner},
 * so the {@code Runner} inner class on the NeoForge sibling becomes the
 * outer class here and the recipe body is owned by a private inner
 * {@link RecipeProvider} subclass.
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new Recipes(registries, output);
    }

    @Override
    public String getName() {
        return "UsefulToolsMod Recipes";
    }

    /** Inner provider that owns the recipe DSL. */
    private static class Recipes extends RecipeProvider {
        private final RecipeOutput pRecipeOutput;

        Recipes(HolderLookup.Provider registries, RecipeOutput output) {
            super(registries, output);
            this.pRecipeOutput = output;
        }

    @Override
    public void buildRecipes() {
        List<ItemLike> RGOLD_SMELTABLES = List.of(ModItems.RAW_RGOLD, ModBlocks.RGOLDORE, ModBlocks.RGOLD_END_ORE, ModBlocks.RGOLD_NETHER_ORE, ModBlocks.RGOLD_DEEPSLATE_ORE);

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLDBLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.HRBLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HRED)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.SEMBLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SEM)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.SOBLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.OBINGOT)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.LBLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RLAPIS)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.SNOWBALL)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.RLAPIS)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET).define('B', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.HRED)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.CLAY_BALL).define('B', Items.REDSTONE)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(pRecipeOutput);

        // Hardened Glowstone: 4 glowstone dust in a + around a blaze rod
        this.shaped(RecipeCategory.MISC, ModItems.HGLOW)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.GLOWSTONE_DUST).define('B', Items.BLAZE_ROD)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.SEM)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT).define('B', Items.EMERALD)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.OBINGOT)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT).define('B', ModItems.OBSHARD)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.RGOLD)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET).define('B', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLDORE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.STONE).define('B', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgoldore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_END_ORE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.END_STONE).define('B', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_end_ore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_NETHER_ORE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.NETHERRACK).define('B', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_nether_ore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_DEEPSLATE_ORE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COBBLED_DEEPSLATE).define('B', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_deepslate_ore");

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.SEM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.SEM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_AXE)
                .pattern("AAE")
                .pattern("DBD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD).define('E', ModItems.SEM)
                .unlockedBy(getHasName(ModBlocks.SOBLOCK), has(ModBlocks.SOBLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_SHOVEL)
                .pattern("EAE")
                .pattern("DBD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD).define('E', ModItems.SEM)
                .unlockedBy(getHasName(ModBlocks.SOBLOCK), has(ModBlocks.SOBLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_PICKAXE)
                .pattern("AAA")
                .pattern("DBE")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD).define('E', ModItems.SEM)
                .unlockedBy(getHasName(ModBlocks.SOBLOCK), has(ModBlocks.SOBLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_SWORD)
                .pattern("EAE")
                .pattern("DAD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD).define('E', ModItems.SEM)
                .unlockedBy(getHasName(ModBlocks.SOBLOCK), has(ModBlocks.SOBLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HRED).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HRED).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SEM)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.SEM)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.SEM)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.SEM)
                .unlockedBy(getHasName(ModItems.SEM), has(ModItems.SEM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HRED)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HRED)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HRED)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HRED)
                .unlockedBy(getHasName(ModItems.HRED), has(ModItems.HRED)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HGLOW)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HGLOW)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HGLOW)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HGLOW)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.OBINGOT)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.OBINGOT)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.OBINGOT)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.OBINGOT)
                .unlockedBy(getHasName(ModItems.OBINGOT), has(ModItems.OBINGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.RGOLD)
                .unlockedBy(getHasName(ModItems.RGOLD), has(ModItems.RGOLD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RLAPIS)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RLAPIS)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RLAPIS)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.RLAPIS)
                .unlockedBy(getHasName(ModItems.RLAPIS), has(ModItems.RLAPIS)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_CHESTPLATE)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("ADB")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT).define('C', ModItems.SEM).define('D', ModItems.RGOLD)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_BOOTS)
                .pattern("ACA")
                .pattern("ABA")
                .pattern(" D ")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT).define('C', ModItems.SEM).define('D', ModItems.RGOLD)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_LEGGINGS)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("ACA")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT).define('C', ModItems.SEM)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_HELMET)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("CDC")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT).define('C', ModItems.SEM).define('D', ModItems.RGOLD)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shapeless(RecipeCategory.MISC, ModItems.RGOLD, 9)
                .requires(ModBlocks.RGOLDBLOCK)
                .unlockedBy(getHasName(ModBlocks.RGOLDBLOCK), has(ModBlocks.RGOLDBLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":rgold_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.HRED, 9)
                .requires(ModBlocks.HRBLOCK)
                .unlockedBy(getHasName(ModBlocks.HRBLOCK), has(ModBlocks.HRBLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hred_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.OBINGOT, 9)
                .requires(ModBlocks.SOBLOCK)
                .unlockedBy(getHasName(ModBlocks.SOBLOCK), has(ModBlocks.SOBLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":sobingot_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.RLAPIS, 9)
                .requires(ModBlocks.LBLOCK)
                .unlockedBy(getHasName(ModBlocks.LBLOCK), has(ModBlocks.LBLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":rlapis_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.OBSHARD, 3)
                .requires(Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":obshard_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.SEM, 9)
                .requires(ModBlocks.SEMBLOCK)
                .unlockedBy(getHasName(ModBlocks.SEMBLOCK), has(ModBlocks.SEMBLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":sem_from_block");

        // Hardened Glowstone Block
        this.shaped(RecipeCategory.MISC, ModBlocks.HGLOW_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.HGLOW)
                .unlockedBy(getHasName(ModItems.HGLOW), has(ModItems.HGLOW)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.HGLOW, 9)
                .requires(ModBlocks.HGLOW_BLOCK)
                .unlockedBy(getHasName(ModBlocks.HGLOW_BLOCK), has(ModBlocks.HGLOW_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hglow_from_block");

        // Raw Ferrous Gold Block
        this.shaped(RecipeCategory.MISC, ModBlocks.RAW_RGOLD_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.RAW_RGOLD)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.RAW_RGOLD, 9)
                .requires(ModBlocks.RAW_RGOLD_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_RGOLD_BLOCK), has(ModBlocks.RAW_RGOLD_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":raw_rgold_from_block");

        // Ectoplasm Block
        this.shaped(RecipeCategory.MISC, ModBlocks.ECTOPLASM_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.ECTOPLASM)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.ECTOPLASM, 9)
                .requires(ModBlocks.ECTOPLASM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ECTOPLASM_BLOCK), has(ModBlocks.ECTOPLASM_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ectoplasm_from_block");

        // Refined Ectoplasm Block
        this.shaped(RecipeCategory.MISC, ModBlocks.REFINED_ECTOPLASM_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.REFINED_ECTOPLASM)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.REFINED_ECTOPLASM, 9)
                .requires(ModBlocks.REFINED_ECTOPLASM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.REFINED_ECTOPLASM_BLOCK), has(ModBlocks.REFINED_ECTOPLASM_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":refined_ectoplasm_from_block");

        // Hardened Coal Block
        this.shaped(RecipeCategory.MISC, ModBlocks.HARDENED_COAL_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.HARDENED_COAL)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.HARDENED_COAL, 9)
                .requires(ModBlocks.HARDENED_COAL_BLOCK)
                .unlockedBy(getHasName(ModBlocks.HARDENED_COAL_BLOCK), has(ModBlocks.HARDENED_COAL_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hardened_coal_from_block");

        // Coal Dust Block
        this.shaped(RecipeCategory.MISC, ModBlocks.COAL_DUST_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.COAL_DUST)
                .unlockedBy(getHasName(ModItems.COAL_DUST), has(ModItems.COAL_DUST)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST, 9)
                .requires(ModBlocks.COAL_DUST_BLOCK)
                .unlockedBy(getHasName(ModBlocks.COAL_DUST_BLOCK), has(ModBlocks.COAL_DUST_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_block");

        // Obsidian Shard Block
        this.shaped(RecipeCategory.MISC, ModBlocks.OBSHARD_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.OBSHARD)
                .unlockedBy(getHasName(ModItems.OBSHARD), has(ModItems.OBSHARD)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.OBSHARD, 9)
                .requires(ModBlocks.OBSHARD_BLOCK)
                .unlockedBy(getHasName(ModBlocks.OBSHARD_BLOCK), has(ModBlocks.OBSHARD_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":obshard_from_obshard_block");

        // Calcified Amethyst Block
        this.shaped(RecipeCategory.MISC, ModBlocks.CALCIFIED_AMETHYST_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.CALCIFIED_AMETHYST)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.CALCIFIED_AMETHYST, 9)
                .requires(ModBlocks.CALCIFIED_AMETHYST_BLOCK)
                .unlockedBy(getHasName(ModBlocks.CALCIFIED_AMETHYST_BLOCK), has(ModBlocks.CALCIFIED_AMETHYST_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":calcified_amethyst_from_block");

        // Glacial Shard Block
        this.shaped(RecipeCategory.MISC, ModBlocks.GLACIAL_SHARD_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.GLACIAL_SHARD)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.GLACIAL_SHARD, 9)
                .requires(ModBlocks.GLACIAL_SHARD_BLOCK)
                .unlockedBy(getHasName(ModBlocks.GLACIAL_SHARD_BLOCK), has(ModBlocks.GLACIAL_SHARD_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":glacial_shard_from_block");

        // Polished Quartz Block
        this.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_QUARTZ_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_QUARTZ)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.POLISHED_QUARTZ, 9)
                .requires(ModBlocks.POLISHED_QUARTZ_BLOCK)
                .unlockedBy(getHasName(ModBlocks.POLISHED_QUARTZ_BLOCK), has(ModBlocks.POLISHED_QUARTZ_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":polished_quartz_from_block");

        // Polished Prismarine Block
        this.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_PRISMARINE_BLOCK)
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_PRISMARINE)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.POLISHED_PRISMARINE, 9)
                .requires(ModBlocks.POLISHED_PRISMARINE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.POLISHED_PRISMARINE_BLOCK), has(ModBlocks.POLISHED_PRISMARINE_BLOCK))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":polished_prismarine_from_block");

        oreSmeltingLocal(pRecipeOutput, RGOLD_SMELTABLES, RecipeCategory.MISC, ModItems.RGOLD, 0.25f, 200, "rgold");
        oreBlastingLocal(pRecipeOutput, RGOLD_SMELTABLES, RecipeCategory.MISC, ModItems.RGOLD, 0.25f, 100, "rgold");

        // -----------------------------------------------------------------
        // Raw metal rough tool recipes
        // -----------------------------------------------------------------

        // Rough Raw Gold tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);

        // Rough Raw Copper tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);

        // Rough Raw Iron tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);

        // Rough Raw Ferrous Gold tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD), has(ModItems.RAW_RGOLD)).save(pRecipeOutput);

        // Rough Netherite Scrap tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ghost breeding item
        // -----------------------------------------------------------------

        this.shapeless(RecipeCategory.MISC, ModItems.ECTOPLASM, 2)
                .requires(Items.PHANTOM_MEMBRANE)
                .requires(Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ectoplasm_from_membrane");

        // Spectral Infuser block
        this.shaped(RecipeCategory.MISC, ModBlocks.SPECTRAL_INFUSER)
                .pattern("EEE")
                .pattern("EBE")
                .pattern("SSS")
                .define('E', ModItems.ECTOPLASM)
                .define('B', Items.BLAZE_ROD)
                .define('S', Blocks.SMOOTH_STONE)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ectoplasm set
        // -----------------------------------------------------------------

        // Refined Ectoplasm: 4 ectoplasm (cross) + 1 diamond (center)
        this.shaped(RecipeCategory.MISC, ModItems.REFINED_ECTOPLASM)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', ModItems.ECTOPLASM)
                .define('B', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM))
                .save(pRecipeOutput);

        // Rough Ecto tools (raw ectoplasm)
        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM), has(ModItems.ECTOPLASM)).save(pRecipeOutput);

        // Ecto tools (refined ectoplasm)
        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        // Ecto armor
        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.REFINED_ECTOPLASM)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.REFINED_ECTOPLASM)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.REFINED_ECTOPLASM)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.REFINED_ECTOPLASM)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM), has(ModItems.REFINED_ECTOPLASM)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal material recipes
        // -----------------------------------------------------------------

        // 1 Coal → 4 Coal Dust
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST, 4)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_coal");

        // 1 Charcoal → 4 Coal Dust
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST, 4)
                .requires(Items.CHARCOAL)
                .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_charcoal");

        // Hardened Coal = Coal Dust surrounded by Clay Balls (follows HRED pattern)
        this.shaped(RecipeCategory.MISC, ModItems.HARDENED_COAL)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.CLAY_BALL)
                .define('B', ModItems.COAL_DUST)
                .unlockedBy(getHasName(ModItems.COAL_DUST), has(ModItems.COAL_DUST))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal tools
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal armor
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HARDENED_COAL)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HARDENED_COAL)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HARDENED_COAL)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HARDENED_COAL)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL), has(ModItems.HARDENED_COAL))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Leather tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Cake tools + armor
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_SWORD)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_PICKAXE)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_SHOVEL)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_AXE)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_HOE)
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_HELMET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_CHESTPLATE)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_LEGGINGS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_BOOTS)
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Food tool + armor sets (11 sets)
        // -----------------------------------------------------------------

        buildFoodSet(pRecipeOutput, Items.BREAD,
                ModItems.BREAD_SWORD, ModItems.BREAD_PICKAXE, ModItems.BREAD_SHOVEL,
                ModItems.BREAD_AXE, ModItems.BREAD_HOE,
                ModItems.BREAD_HELMET, ModItems.BREAD_CHESTPLATE,
                ModItems.BREAD_LEGGINGS, ModItems.BREAD_BOOTS);

        buildFoodSet(pRecipeOutput, Items.DRIED_KELP,
                ModItems.DRIED_KELP_SWORD, ModItems.DRIED_KELP_PICKAXE, ModItems.DRIED_KELP_SHOVEL,
                ModItems.DRIED_KELP_AXE, ModItems.DRIED_KELP_HOE,
                ModItems.DRIED_KELP_HELMET, ModItems.DRIED_KELP_CHESTPLATE,
                ModItems.DRIED_KELP_LEGGINGS, ModItems.DRIED_KELP_BOOTS);

        buildFoodSet(pRecipeOutput, Items.ROTTEN_FLESH,
                ModItems.ROTTEN_FLESH_SWORD, ModItems.ROTTEN_FLESH_PICKAXE, ModItems.ROTTEN_FLESH_SHOVEL,
                ModItems.ROTTEN_FLESH_AXE, ModItems.ROTTEN_FLESH_HOE,
                ModItems.ROTTEN_FLESH_HELMET, ModItems.ROTTEN_FLESH_CHESTPLATE,
                ModItems.ROTTEN_FLESH_LEGGINGS, ModItems.ROTTEN_FLESH_BOOTS);

        buildFoodSet(pRecipeOutput, Items.MELON_SLICE,
                ModItems.MELON_SWORD, ModItems.MELON_PICKAXE, ModItems.MELON_SHOVEL,
                ModItems.MELON_AXE, ModItems.MELON_HOE,
                ModItems.MELON_HELMET, ModItems.MELON_CHESTPLATE,
                ModItems.MELON_LEGGINGS, ModItems.MELON_BOOTS);

        buildFoodSet(pRecipeOutput, Items.SWEET_BERRIES,
                ModItems.SWEET_BERRY_SWORD, ModItems.SWEET_BERRY_PICKAXE, ModItems.SWEET_BERRY_SHOVEL,
                ModItems.SWEET_BERRY_AXE, ModItems.SWEET_BERRY_HOE,
                ModItems.SWEET_BERRY_HELMET, ModItems.SWEET_BERRY_CHESTPLATE,
                ModItems.SWEET_BERRY_LEGGINGS, ModItems.SWEET_BERRY_BOOTS);

        buildFoodSet(pRecipeOutput, Items.PUMPKIN_PIE,
                ModItems.PUMPKIN_PIE_SWORD, ModItems.PUMPKIN_PIE_PICKAXE, ModItems.PUMPKIN_PIE_SHOVEL,
                ModItems.PUMPKIN_PIE_AXE, ModItems.PUMPKIN_PIE_HOE,
                ModItems.PUMPKIN_PIE_HELMET, ModItems.PUMPKIN_PIE_CHESTPLATE,
                ModItems.PUMPKIN_PIE_LEGGINGS, ModItems.PUMPKIN_PIE_BOOTS);

        buildFoodSet(pRecipeOutput, Items.RED_MUSHROOM,
                ModItems.MUSHROOM_SWORD, ModItems.MUSHROOM_PICKAXE, ModItems.MUSHROOM_SHOVEL,
                ModItems.MUSHROOM_AXE, ModItems.MUSHROOM_HOE,
                ModItems.MUSHROOM_HELMET, ModItems.MUSHROOM_CHESTPLATE,
                ModItems.MUSHROOM_LEGGINGS, ModItems.MUSHROOM_BOOTS);

        buildFoodSet(pRecipeOutput, Items.PUFFERFISH,
                ModItems.PUFFERFISH_SWORD, ModItems.PUFFERFISH_PICKAXE, ModItems.PUFFERFISH_SHOVEL,
                ModItems.PUFFERFISH_AXE, ModItems.PUFFERFISH_HOE,
                ModItems.PUFFERFISH_HELMET, ModItems.PUFFERFISH_CHESTPLATE,
                ModItems.PUFFERFISH_LEGGINGS, ModItems.PUFFERFISH_BOOTS);

        buildFoodSet(pRecipeOutput, Items.HONEY_BOTTLE,
                ModItems.HONEY_SWORD, ModItems.HONEY_PICKAXE, ModItems.HONEY_SHOVEL,
                ModItems.HONEY_AXE, ModItems.HONEY_HOE,
                ModItems.HONEY_HELMET, ModItems.HONEY_CHESTPLATE,
                ModItems.HONEY_LEGGINGS, ModItems.HONEY_BOOTS);

        buildFoodSet(pRecipeOutput, Items.CHORUS_FRUIT,
                ModItems.CHORUS_FRUIT_SWORD, ModItems.CHORUS_FRUIT_PICKAXE, ModItems.CHORUS_FRUIT_SHOVEL,
                ModItems.CHORUS_FRUIT_AXE, ModItems.CHORUS_FRUIT_HOE,
                ModItems.CHORUS_FRUIT_HELMET, ModItems.CHORUS_FRUIT_CHESTPLATE,
                ModItems.CHORUS_FRUIT_LEGGINGS, ModItems.CHORUS_FRUIT_BOOTS);

        buildFoodSet(pRecipeOutput, Items.GOLDEN_APPLE,
                ModItems.GOLDEN_APPLE_SWORD, ModItems.GOLDEN_APPLE_PICKAXE, ModItems.GOLDEN_APPLE_SHOVEL,
                ModItems.GOLDEN_APPLE_AXE, ModItems.GOLDEN_APPLE_HOE,
                ModItems.GOLDEN_APPLE_HELMET, ModItems.GOLDEN_APPLE_CHESTPLATE,
                ModItems.GOLDEN_APPLE_LEGGINGS, ModItems.GOLDEN_APPLE_BOOTS);

        // -----------------------------------------------------------------
        // Vanilla material set recipes
        // -----------------------------------------------------------------

        // Tools-only sets
        stoneVariantTools(pRecipeOutput, ModItems.PAPER_SWORD, ModItems.PAPER_PICKAXE, ModItems.PAPER_SHOVEL, ModItems.PAPER_AXE, ModItems.PAPER_HOE, Items.PAPER);
        stoneVariantTools(pRecipeOutput, ModItems.FEATHER_SWORD, ModItems.FEATHER_PICKAXE, ModItems.FEATHER_SHOVEL, ModItems.FEATHER_AXE, ModItems.FEATHER_HOE, Items.FEATHER);
        stoneVariantTools(pRecipeOutput, ModItems.GLASS_SWORD, ModItems.GLASS_PICKAXE, ModItems.GLASS_SHOVEL, ModItems.GLASS_AXE, ModItems.GLASS_HOE, Items.GLASS_PANE);
        stoneVariantTools(pRecipeOutput, ModItems.SPONGE_SWORD, ModItems.SPONGE_PICKAXE, ModItems.SPONGE_SHOVEL, ModItems.SPONGE_AXE, ModItems.SPONGE_HOE, Items.SPONGE);
        stoneVariantTools(pRecipeOutput, ModItems.NETHER_WART_SWORD, ModItems.NETHER_WART_PICKAXE, ModItems.NETHER_WART_SHOVEL, ModItems.NETHER_WART_AXE, ModItems.NETHER_WART_HOE, Items.NETHER_WART);
        stoneVariantTools(pRecipeOutput, ModItems.POINTED_DRIPSTONE_SWORD, ModItems.POINTED_DRIPSTONE_PICKAXE, ModItems.POINTED_DRIPSTONE_SHOVEL, ModItems.POINTED_DRIPSTONE_AXE, ModItems.POINTED_DRIPSTONE_HOE, Items.POINTED_DRIPSTONE);

        // Armor-only sets
        // Rabbit Hide armor
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);

        // Turtle Scute armor
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);

        // Tools+Armor sets
        buildFoodSet(pRecipeOutput, Items.CACTUS,
                ModItems.CACTUS_SWORD, ModItems.CACTUS_PICKAXE, ModItems.CACTUS_SHOVEL,
                ModItems.CACTUS_AXE, ModItems.CACTUS_HOE,
                ModItems.CACTUS_HELMET, ModItems.CACTUS_CHESTPLATE,
                ModItems.CACTUS_LEGGINGS, ModItems.CACTUS_BOOTS);

        buildFoodSet(pRecipeOutput, Items.BONE,
                ModItems.BONE_SWORD, ModItems.BONE_PICKAXE, ModItems.BONE_SHOVEL,
                ModItems.BONE_AXE, ModItems.BONE_HOE,
                ModItems.BONE_HELMET, ModItems.BONE_CHESTPLATE,
                ModItems.BONE_LEGGINGS, ModItems.BONE_BOOTS);

        buildFoodSet(pRecipeOutput, Items.CLAY_BALL,
                ModItems.CLAY_SWORD, ModItems.CLAY_PICKAXE, ModItems.CLAY_SHOVEL,
                ModItems.CLAY_AXE, ModItems.CLAY_HOE,
                ModItems.CLAY_HELMET, ModItems.CLAY_CHESTPLATE,
                ModItems.CLAY_LEGGINGS, ModItems.CLAY_BOOTS);

        buildFoodSet(pRecipeOutput, Items.BRICK,
                ModItems.BRICK_SWORD, ModItems.BRICK_PICKAXE, ModItems.BRICK_SHOVEL,
                ModItems.BRICK_AXE, ModItems.BRICK_HOE,
                ModItems.BRICK_HELMET, ModItems.BRICK_CHESTPLATE,
                ModItems.BRICK_LEGGINGS, ModItems.BRICK_BOOTS);

        buildFoodSet(pRecipeOutput, Items.NETHER_BRICK,
                ModItems.NETHER_BRICK_SWORD, ModItems.NETHER_BRICK_PICKAXE, ModItems.NETHER_BRICK_SHOVEL,
                ModItems.NETHER_BRICK_AXE, ModItems.NETHER_BRICK_HOE,
                ModItems.NETHER_BRICK_HELMET, ModItems.NETHER_BRICK_CHESTPLATE,
                ModItems.NETHER_BRICK_LEGGINGS, ModItems.NETHER_BRICK_BOOTS);

        buildFoodSet(pRecipeOutput, Items.COPPER_INGOT,
                ModItems.COPPER_SWORD, ModItems.COPPER_PICKAXE, ModItems.COPPER_SHOVEL,
                ModItems.COPPER_AXE, ModItems.COPPER_HOE,
                ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE,
                ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS);

        buildFoodSet(pRecipeOutput, Items.PHANTOM_MEMBRANE,
                ModItems.PHANTOM_SWORD, ModItems.PHANTOM_PICKAXE, ModItems.PHANTOM_SHOVEL,
                ModItems.PHANTOM_AXE, ModItems.PHANTOM_HOE,
                ModItems.PHANTOM_HELMET, ModItems.PHANTOM_CHESTPLATE,
                ModItems.PHANTOM_LEGGINGS, ModItems.PHANTOM_BOOTS);

        buildFoodSet(pRecipeOutput, Items.MAGMA_CREAM,
                ModItems.MAGMA_CREAM_SWORD, ModItems.MAGMA_CREAM_PICKAXE, ModItems.MAGMA_CREAM_SHOVEL,
                ModItems.MAGMA_CREAM_AXE, ModItems.MAGMA_CREAM_HOE,
                ModItems.MAGMA_CREAM_HELMET, ModItems.MAGMA_CREAM_CHESTPLATE,
                ModItems.MAGMA_CREAM_LEGGINGS, ModItems.MAGMA_CREAM_BOOTS);

        buildFoodSet(pRecipeOutput, Items.SLIME_BALL,
                ModItems.SLIME_SWORD, ModItems.SLIME_PICKAXE, ModItems.SLIME_SHOVEL,
                ModItems.SLIME_AXE, ModItems.SLIME_HOE,
                ModItems.SLIME_HELMET, ModItems.SLIME_CHESTPLATE,
                ModItems.SLIME_LEGGINGS, ModItems.SLIME_BOOTS);

        buildFoodSet(pRecipeOutput, Items.BLAZE_ROD,
                ModItems.BLAZE_SWORD, ModItems.BLAZE_PICKAXE, ModItems.BLAZE_SHOVEL,
                ModItems.BLAZE_AXE, ModItems.BLAZE_HOE,
                ModItems.BLAZE_HELMET, ModItems.BLAZE_CHESTPLATE,
                ModItems.BLAZE_LEGGINGS, ModItems.BLAZE_BOOTS);

        buildFoodSet(pRecipeOutput, Items.NAUTILUS_SHELL,
                ModItems.NAUTILUS_SWORD, ModItems.NAUTILUS_PICKAXE, ModItems.NAUTILUS_SHOVEL,
                ModItems.NAUTILUS_AXE, ModItems.NAUTILUS_HOE,
                ModItems.NAUTILUS_HELMET, ModItems.NAUTILUS_CHESTPLATE,
                ModItems.NAUTILUS_LEGGINGS, ModItems.NAUTILUS_BOOTS);

        buildFoodSet(pRecipeOutput, Items.POPPED_CHORUS_FRUIT,
                ModItems.PURPUR_SWORD, ModItems.PURPUR_PICKAXE, ModItems.PURPUR_SHOVEL,
                ModItems.PURPUR_AXE, ModItems.PURPUR_HOE,
                ModItems.PURPUR_HELMET, ModItems.PURPUR_CHESTPLATE,
                ModItems.PURPUR_LEGGINGS, ModItems.PURPUR_BOOTS);

        buildFoodSet(pRecipeOutput, Items.GHAST_TEAR,
                ModItems.GHAST_TEAR_SWORD, ModItems.GHAST_TEAR_PICKAXE, ModItems.GHAST_TEAR_SHOVEL,
                ModItems.GHAST_TEAR_AXE, ModItems.GHAST_TEAR_HOE,
                ModItems.GHAST_TEAR_HELMET, ModItems.GHAST_TEAR_CHESTPLATE,
                ModItems.GHAST_TEAR_LEGGINGS, ModItems.GHAST_TEAR_BOOTS);

        buildFoodSet(pRecipeOutput, Items.ENDER_EYE,
                ModItems.EYE_OF_ENDER_SWORD, ModItems.EYE_OF_ENDER_PICKAXE, ModItems.EYE_OF_ENDER_SHOVEL,
                ModItems.EYE_OF_ENDER_AXE, ModItems.EYE_OF_ENDER_HOE,
                ModItems.EYE_OF_ENDER_HELMET, ModItems.EYE_OF_ENDER_CHESTPLATE,
                ModItems.EYE_OF_ENDER_LEGGINGS, ModItems.EYE_OF_ENDER_BOOTS);

        buildFoodSet(pRecipeOutput, Items.SHULKER_SHELL,
                ModItems.SHULKER_SWORD, ModItems.SHULKER_PICKAXE, ModItems.SHULKER_SHOVEL,
                ModItems.SHULKER_AXE, ModItems.SHULKER_HOE,
                ModItems.SHULKER_HELMET, ModItems.SHULKER_CHESTPLATE,
                ModItems.SHULKER_LEGGINGS, ModItems.SHULKER_BOOTS);

        buildFoodSet(pRecipeOutput, Items.ECHO_SHARD,
                ModItems.ECHO_SHARD_SWORD, ModItems.ECHO_SHARD_PICKAXE, ModItems.ECHO_SHARD_SHOVEL,
                ModItems.ECHO_SHARD_AXE, ModItems.ECHO_SHARD_HOE,
                ModItems.ECHO_SHARD_HELMET, ModItems.ECHO_SHARD_CHESTPLATE,
                ModItems.ECHO_SHARD_LEGGINGS, ModItems.ECHO_SHARD_BOOTS);

        buildFoodSet(pRecipeOutput, Items.DRAGON_BREATH,
                ModItems.DRAGON_BREATH_SWORD, ModItems.DRAGON_BREATH_PICKAXE, ModItems.DRAGON_BREATH_SHOVEL,
                ModItems.DRAGON_BREATH_AXE, ModItems.DRAGON_BREATH_HOE,
                ModItems.DRAGON_BREATH_HELMET, ModItems.DRAGON_BREATH_CHESTPLATE,
                ModItems.DRAGON_BREATH_LEGGINGS, ModItems.DRAGON_BREATH_BOOTS);

        // -----------------------------------------------------------------
        // Crystal / element material crafting
        // -----------------------------------------------------------------

        // Calcified Amethyst: + pattern with amethyst shards around calcite
        this.shaped(RecipeCategory.MISC, ModItems.CALCIFIED_AMETHYST)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', Blocks.CALCITE.asItem())
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pRecipeOutput);

        // Glacial Shard: packed ice surrounding blue ice
        this.shaped(RecipeCategory.MISC, ModItems.GLACIAL_SHARD)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.PACKED_ICE)
                .define('B', Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(pRecipeOutput);

        // Blue Ice → 9 Packed Ice (reverse of vanilla 9 packed → 1 blue)
        this.shapeless(RecipeCategory.MISC, Items.PACKED_ICE, 9)
                .requires(Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_ICE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":packed_ice_from_blue_ice");

        // Packed Ice → 9 Ice (reverse of vanilla 9 ice → 1 packed)
        this.shapeless(RecipeCategory.MISC, Items.ICE, 9)
                .requires(Items.PACKED_ICE)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ice_from_packed_ice");

        // Polished Quartz: + pattern with quartz around smooth quartz block
        this.shaped(RecipeCategory.MISC, ModItems.POLISHED_QUARTZ)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.QUARTZ)
                .define('B', Blocks.SMOOTH_QUARTZ.asItem())
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .save(pRecipeOutput);

        // Polished Prismarine: + pattern with prismarine shards around prismarine crystals
        this.shaped(RecipeCategory.MISC, ModItems.POLISHED_PRISMARINE)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.PRISMARINE_SHARD)
                .define('B', Items.PRISMARINE_CRYSTALS)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Amethyst tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Snow tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Quartz tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Prismarine tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Calcified Amethyst tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.CALCIFIED_AMETHYST)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.CALCIFIED_AMETHYST)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.CALCIFIED_AMETHYST)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.CALCIFIED_AMETHYST)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST), has(ModItems.CALCIFIED_AMETHYST)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ice (Glacial) tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.GLACIAL_SHARD)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.GLACIAL_SHARD)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.GLACIAL_SHARD)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.GLACIAL_SHARD)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD), has(ModItems.GLACIAL_SHARD)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Polished Quartz tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.POLISHED_QUARTZ)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_QUARTZ)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_QUARTZ)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_QUARTZ)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ), has(ModItems.POLISHED_QUARTZ)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Polished Prismarine tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_SWORD)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_PICKAXE)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_SHOVEL)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_AXE)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_HOE)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_HELMET)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.POLISHED_PRISMARINE)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_CHESTPLATE)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_PRISMARINE)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_LEGGINGS)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_PRISMARINE)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_BOOTS)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_PRISMARINE)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE), has(ModItems.POLISHED_PRISMARINE)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Flint Tools (rough)
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput,
                ModItems.RFLINT_SWORD, ModItems.RFLINT_PICKAXE,
                ModItems.RFLINT_SHOVEL, ModItems.RFLINT_AXE, ModItems.RFLINT_HOE,
                Items.FLINT);

        // -----------------------------------------------------------------
        // Flint-Iron (FNI) Tools
        // -----------------------------------------------------------------
        // Sword: flint-iron-stick vertically
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_SWORD)
                .pattern(" F ").pattern(" I ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Pickaxe: iron-flint-iron top, two sticks
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_PICKAXE)
                .pattern("IFI").pattern(" S ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Shovel: iron head, flint below, stick handle
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_SHOVEL)
                .pattern(" I ").pattern(" F ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Axe: flint-iron upper-left corner + stick column
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_AXE)
                .pattern("FI ").pattern("FS ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Hoe: flint-iron head, two sticks below centre
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_HOE)
                .pattern("FI ").pattern(" S ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Flint-Iron (FNI) Armor  — iron base with flint accent
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_HELMET)
                .pattern("IFI").pattern("I I").pattern("   ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_CHESTPLATE)
                .pattern("I I").pattern("IFI").pattern("III")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_LEGGINGS)
                .pattern("IFI").pattern("I I").pattern("I I")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_BOOTS)
                .pattern("   ").pattern("IFI").pattern("I I")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Stone Rock Variant Tools
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput, ModItems.ANDESITE_SWORD, ModItems.ANDESITE_PICKAXE, ModItems.ANDESITE_SHOVEL, ModItems.ANDESITE_AXE, ModItems.ANDESITE_HOE, Items.ANDESITE);
        stoneVariantTools(pRecipeOutput, ModItems.BASALT_SWORD, ModItems.BASALT_PICKAXE, ModItems.BASALT_SHOVEL, ModItems.BASALT_AXE, ModItems.BASALT_HOE, Items.BASALT);
        stoneVariantTools(pRecipeOutput, ModItems.BLACKSTONE_SWORD, ModItems.BLACKSTONE_PICKAXE, ModItems.BLACKSTONE_SHOVEL, ModItems.BLACKSTONE_AXE, ModItems.BLACKSTONE_HOE, Items.BLACKSTONE);
        stoneVariantTools(pRecipeOutput, ModItems.CALCITE_SWORD, ModItems.CALCITE_PICKAXE, ModItems.CALCITE_SHOVEL, ModItems.CALCITE_AXE, ModItems.CALCITE_HOE, Items.CALCITE);
        stoneVariantTools(pRecipeOutput, ModItems.DEEPSLATE_SWORD, ModItems.DEEPSLATE_PICKAXE, ModItems.DEEPSLATE_SHOVEL, ModItems.DEEPSLATE_AXE, ModItems.DEEPSLATE_HOE, Items.COBBLED_DEEPSLATE);
        stoneVariantTools(pRecipeOutput, ModItems.DIORITE_SWORD, ModItems.DIORITE_PICKAXE, ModItems.DIORITE_SHOVEL, ModItems.DIORITE_AXE, ModItems.DIORITE_HOE, Items.DIORITE);
        stoneVariantTools(pRecipeOutput, ModItems.END_STONE_SWORD, ModItems.END_STONE_PICKAXE, ModItems.END_STONE_SHOVEL, ModItems.END_STONE_AXE, ModItems.END_STONE_HOE, Items.END_STONE);
        stoneVariantTools(pRecipeOutput, ModItems.GRANITE_SWORD, ModItems.GRANITE_PICKAXE, ModItems.GRANITE_SHOVEL, ModItems.GRANITE_AXE, ModItems.GRANITE_HOE, Items.GRANITE);
        stoneVariantTools(pRecipeOutput, ModItems.NETHERRACK_SWORD, ModItems.NETHERRACK_PICKAXE, ModItems.NETHERRACK_SHOVEL, ModItems.NETHERRACK_AXE, ModItems.NETHERRACK_HOE, Items.NETHERRACK);
        stoneVariantTools(pRecipeOutput, ModItems.SANDSTONE_SWORD, ModItems.SANDSTONE_PICKAXE, ModItems.SANDSTONE_SHOVEL, ModItems.SANDSTONE_AXE, ModItems.SANDSTONE_HOE, Items.SANDSTONE);
        stoneVariantTools(pRecipeOutput, ModItems.SMOOTH_BASALT_SWORD, ModItems.SMOOTH_BASALT_PICKAXE, ModItems.SMOOTH_BASALT_SHOVEL, ModItems.SMOOTH_BASALT_AXE, ModItems.SMOOTH_BASALT_HOE, Items.SMOOTH_BASALT);
        stoneVariantTools(pRecipeOutput, ModItems.TERRACOTTA_SWORD, ModItems.TERRACOTTA_PICKAXE, ModItems.TERRACOTTA_SHOVEL, ModItems.TERRACOTTA_AXE, ModItems.TERRACOTTA_HOE, Items.TERRACOTTA);
        stoneVariantTools(pRecipeOutput, ModItems.TUFF_SWORD, ModItems.TUFF_PICKAXE, ModItems.TUFF_SHOVEL, ModItems.TUFF_AXE, ModItems.TUFF_HOE, Items.TUFF);

        // -----------------------------------------------------------------
        // Wood Variant Tools
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput, ModItems.OAK_SWORD, ModItems.OAK_PICKAXE, ModItems.OAK_SHOVEL, ModItems.OAK_AXE, ModItems.OAK_HOE, Items.OAK_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.SPRUCE_SWORD, ModItems.SPRUCE_PICKAXE, ModItems.SPRUCE_SHOVEL, ModItems.SPRUCE_AXE, ModItems.SPRUCE_HOE, Items.SPRUCE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.BIRCH_SWORD, ModItems.BIRCH_PICKAXE, ModItems.BIRCH_SHOVEL, ModItems.BIRCH_AXE, ModItems.BIRCH_HOE, Items.BIRCH_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.JUNGLE_SWORD, ModItems.JUNGLE_PICKAXE, ModItems.JUNGLE_SHOVEL, ModItems.JUNGLE_AXE, ModItems.JUNGLE_HOE, Items.JUNGLE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.ACACIA_SWORD, ModItems.ACACIA_PICKAXE, ModItems.ACACIA_SHOVEL, ModItems.ACACIA_AXE, ModItems.ACACIA_HOE, Items.ACACIA_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.DARK_OAK_SWORD, ModItems.DARK_OAK_PICKAXE, ModItems.DARK_OAK_SHOVEL, ModItems.DARK_OAK_AXE, ModItems.DARK_OAK_HOE, Items.DARK_OAK_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.MANGROVE_SWORD, ModItems.MANGROVE_PICKAXE, ModItems.MANGROVE_SHOVEL, ModItems.MANGROVE_AXE, ModItems.MANGROVE_HOE, Items.MANGROVE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.CHERRY_SWORD, ModItems.CHERRY_PICKAXE, ModItems.CHERRY_SHOVEL, ModItems.CHERRY_AXE, ModItems.CHERRY_HOE, Items.CHERRY_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.BAMBOO_SWORD, ModItems.BAMBOO_PICKAXE, ModItems.BAMBOO_SHOVEL, ModItems.BAMBOO_AXE, ModItems.BAMBOO_HOE, Items.BAMBOO_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.CRIMSON_SWORD, ModItems.CRIMSON_PICKAXE, ModItems.CRIMSON_SHOVEL, ModItems.CRIMSON_AXE, ModItems.CRIMSON_HOE, Items.CRIMSON_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.WARPED_SWORD, ModItems.WARPED_PICKAXE, ModItems.WARPED_SHOVEL, ModItems.WARPED_AXE, ModItems.WARPED_HOE, Items.WARPED_PLANKS);

        // -----------------------------------------------------------------
        // Grenade & Dynamite
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.COMBAT, ModItems.GRENADE)
                .pattern("ACA").pattern("ABA").pattern("ADA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.GUNPOWDER)
                .define('C', Items.REDSTONE)
                .define('D', Items.PAPER)
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.DYNAMITE)
                .pattern(" B ").pattern("CBC").pattern(" A ")
                .define('A', Items.PAPER)
                .define('B', Items.STRING)
                .define('C', ModItems.GRENADE)
                .unlockedBy(getHasName(ModItems.GRENADE), has(ModItems.GRENADE)).save(pRecipeOutput);
    }

    private void stoneVariantTools(RecipeOutput out, ItemLike sword, ItemLike pickaxe, ItemLike shovel, ItemLike axe, ItemLike hoe, ItemLike material) {
        this.shaped(RecipeCategory.COMBAT, sword)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
    }

    protected void oreSmeltingLocal(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCookingLocal(recipeOutput, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected void oreBlastingLocal(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCookingLocal(recipeOutput, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCookingLocal(RecipeOutput recipeOutput, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, CookingBookCategory.MISC, pResult, pExperience, pCookingTime, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, UsefultoolsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    private void buildFoodSet(RecipeOutput out, ItemLike material,
                              ItemLike sword, ItemLike pickaxe, ItemLike shovel, ItemLike axe, ItemLike hoe,
                              ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        String name = getHasName(material);
        this.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, boots)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
    }
    } // close inner class Recipes
}