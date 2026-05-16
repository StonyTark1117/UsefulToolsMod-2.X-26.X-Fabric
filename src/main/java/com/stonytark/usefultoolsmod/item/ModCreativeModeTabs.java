package com.stonytark.usefultoolsmod.item;

import com.stonytark.usefultoolsmod.Config;
import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab USEFUL_TOOLS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "useful_tools_tab"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).icon(() -> new ItemStack(ModItems.RGOLD))
                    .title(Component.translatable( "creativetab.usefultoolsmod.useful_tools"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // =============================================================
                        //  UTILITY
                        // =============================================================

                        if (Config.explosivesEnabled) {
                            output.accept(ModItems.DYNAMITE);
                            output.accept(ModItems.GRENADE);
                        }

                        // =============================================================
                        //  VANILLA VARIANT TOOLS (wood & stone tier reskins)
                        // =============================================================

                        // --- Leather ---
                        if (Config.leatherEnabled) {
                            output.accept(ModItems.LEATHER_SWORD);
                            output.accept(ModItems.LEATHER_PICKAXE);
                            output.accept(ModItems.LEATHER_SHOVEL);
                            output.accept(ModItems.LEATHER_AXE);
                            output.accept(ModItems.LEATHER_HOE);
                        }

                        // --- Wood Variants ---
                        if (Config.woodVariantsEnabled) {
                            output.accept(ModItems.OAK_SWORD);
                            output.accept(ModItems.OAK_PICKAXE);
                            output.accept(ModItems.OAK_SHOVEL);
                            output.accept(ModItems.OAK_AXE);
                            output.accept(ModItems.OAK_HOE);
                            output.accept(ModItems.SPRUCE_SWORD);
                            output.accept(ModItems.SPRUCE_PICKAXE);
                            output.accept(ModItems.SPRUCE_SHOVEL);
                            output.accept(ModItems.SPRUCE_AXE);
                            output.accept(ModItems.SPRUCE_HOE);
                            output.accept(ModItems.BIRCH_SWORD);
                            output.accept(ModItems.BIRCH_PICKAXE);
                            output.accept(ModItems.BIRCH_SHOVEL);
                            output.accept(ModItems.BIRCH_AXE);
                            output.accept(ModItems.BIRCH_HOE);
                            output.accept(ModItems.JUNGLE_SWORD);
                            output.accept(ModItems.JUNGLE_PICKAXE);
                            output.accept(ModItems.JUNGLE_SHOVEL);
                            output.accept(ModItems.JUNGLE_AXE);
                            output.accept(ModItems.JUNGLE_HOE);
                            output.accept(ModItems.ACACIA_SWORD);
                            output.accept(ModItems.ACACIA_PICKAXE);
                            output.accept(ModItems.ACACIA_SHOVEL);
                            output.accept(ModItems.ACACIA_AXE);
                            output.accept(ModItems.ACACIA_HOE);
                            output.accept(ModItems.DARK_OAK_SWORD);
                            output.accept(ModItems.DARK_OAK_PICKAXE);
                            output.accept(ModItems.DARK_OAK_SHOVEL);
                            output.accept(ModItems.DARK_OAK_AXE);
                            output.accept(ModItems.DARK_OAK_HOE);
                            output.accept(ModItems.MANGROVE_SWORD);
                            output.accept(ModItems.MANGROVE_PICKAXE);
                            output.accept(ModItems.MANGROVE_SHOVEL);
                            output.accept(ModItems.MANGROVE_AXE);
                            output.accept(ModItems.MANGROVE_HOE);
                            output.accept(ModItems.CHERRY_SWORD);
                            output.accept(ModItems.CHERRY_PICKAXE);
                            output.accept(ModItems.CHERRY_SHOVEL);
                            output.accept(ModItems.CHERRY_AXE);
                            output.accept(ModItems.CHERRY_HOE);
                            output.accept(ModItems.BAMBOO_SWORD);
                            output.accept(ModItems.BAMBOO_PICKAXE);
                            output.accept(ModItems.BAMBOO_SHOVEL);
                            output.accept(ModItems.BAMBOO_AXE);
                            output.accept(ModItems.BAMBOO_HOE);
                            output.accept(ModItems.CRIMSON_SWORD);
                            output.accept(ModItems.CRIMSON_PICKAXE);
                            output.accept(ModItems.CRIMSON_SHOVEL);
                            output.accept(ModItems.CRIMSON_AXE);
                            output.accept(ModItems.CRIMSON_HOE);
                            output.accept(ModItems.WARPED_SWORD);
                            output.accept(ModItems.WARPED_PICKAXE);
                            output.accept(ModItems.WARPED_SHOVEL);
                            output.accept(ModItems.WARPED_AXE);
                            output.accept(ModItems.WARPED_HOE);
                        }

                        // --- Stone Rock Variants ---
                        if (Config.stoneVariantsEnabled) {
                            output.accept(ModItems.ANDESITE_SWORD);
                            output.accept(ModItems.ANDESITE_PICKAXE);
                            output.accept(ModItems.ANDESITE_SHOVEL);
                            output.accept(ModItems.ANDESITE_AXE);
                            output.accept(ModItems.ANDESITE_HOE);
                            output.accept(ModItems.BASALT_SWORD);
                            output.accept(ModItems.BASALT_PICKAXE);
                            output.accept(ModItems.BASALT_SHOVEL);
                            output.accept(ModItems.BASALT_AXE);
                            output.accept(ModItems.BASALT_HOE);
                            output.accept(ModItems.BLACKSTONE_SWORD);
                            output.accept(ModItems.BLACKSTONE_PICKAXE);
                            output.accept(ModItems.BLACKSTONE_SHOVEL);
                            output.accept(ModItems.BLACKSTONE_AXE);
                            output.accept(ModItems.BLACKSTONE_HOE);
                            output.accept(ModItems.CALCITE_SWORD);
                            output.accept(ModItems.CALCITE_PICKAXE);
                            output.accept(ModItems.CALCITE_SHOVEL);
                            output.accept(ModItems.CALCITE_AXE);
                            output.accept(ModItems.CALCITE_HOE);
                            output.accept(ModItems.DEEPSLATE_SWORD);
                            output.accept(ModItems.DEEPSLATE_PICKAXE);
                            output.accept(ModItems.DEEPSLATE_SHOVEL);
                            output.accept(ModItems.DEEPSLATE_AXE);
                            output.accept(ModItems.DEEPSLATE_HOE);
                            output.accept(ModItems.DIORITE_SWORD);
                            output.accept(ModItems.DIORITE_PICKAXE);
                            output.accept(ModItems.DIORITE_SHOVEL);
                            output.accept(ModItems.DIORITE_AXE);
                            output.accept(ModItems.DIORITE_HOE);
                            output.accept(ModItems.END_STONE_SWORD);
                            output.accept(ModItems.END_STONE_PICKAXE);
                            output.accept(ModItems.END_STONE_SHOVEL);
                            output.accept(ModItems.END_STONE_AXE);
                            output.accept(ModItems.END_STONE_HOE);
                            output.accept(ModItems.GRANITE_SWORD);
                            output.accept(ModItems.GRANITE_PICKAXE);
                            output.accept(ModItems.GRANITE_SHOVEL);
                            output.accept(ModItems.GRANITE_AXE);
                            output.accept(ModItems.GRANITE_HOE);
                            output.accept(ModItems.NETHERRACK_SWORD);
                            output.accept(ModItems.NETHERRACK_PICKAXE);
                            output.accept(ModItems.NETHERRACK_SHOVEL);
                            output.accept(ModItems.NETHERRACK_AXE);
                            output.accept(ModItems.NETHERRACK_HOE);
                            output.accept(ModItems.SANDSTONE_SWORD);
                            output.accept(ModItems.SANDSTONE_PICKAXE);
                            output.accept(ModItems.SANDSTONE_SHOVEL);
                            output.accept(ModItems.SANDSTONE_AXE);
                            output.accept(ModItems.SANDSTONE_HOE);
                            output.accept(ModItems.SMOOTH_BASALT_SWORD);
                            output.accept(ModItems.SMOOTH_BASALT_PICKAXE);
                            output.accept(ModItems.SMOOTH_BASALT_SHOVEL);
                            output.accept(ModItems.SMOOTH_BASALT_AXE);
                            output.accept(ModItems.SMOOTH_BASALT_HOE);
                            output.accept(ModItems.TERRACOTTA_SWORD);
                            output.accept(ModItems.TERRACOTTA_PICKAXE);
                            output.accept(ModItems.TERRACOTTA_SHOVEL);
                            output.accept(ModItems.TERRACOTTA_AXE);
                            output.accept(ModItems.TERRACOTTA_HOE);
                            output.accept(ModItems.TUFF_SWORD);
                            output.accept(ModItems.TUFF_PICKAXE);
                            output.accept(ModItems.TUFF_SHOVEL);
                            output.accept(ModItems.TUFF_AXE);
                            output.accept(ModItems.TUFF_HOE);
                        }

                        // =============================================================
                        //  NATURAL MATERIAL SETS (flint, coal)
                        // =============================================================

                        // --- Flint ---
                        if (Config.flintEnabled) {
                            output.accept(ModItems.RFLINT_SWORD);
                            output.accept(ModItems.RFLINT_PICKAXE);
                            output.accept(ModItems.RFLINT_SHOVEL);
                            output.accept(ModItems.RFLINT_AXE);
                            output.accept(ModItems.RFLINT_HOE);
                        }

                        // --- Flint-Iron (FNI) ---
                        if (Config.fniEnabled) {
                            output.accept(ModItems.FNI_SWORD);
                            output.accept(ModItems.FNI_PICKAXE);
                            output.accept(ModItems.FNI_SHOVEL);
                            output.accept(ModItems.FNI_AXE);
                            output.accept(ModItems.FNI_HOE);
                            output.accept(ModItems.FNI_HELMET);
                            output.accept(ModItems.FNI_CHESTPLATE);
                            output.accept(ModItems.FNI_LEGGINGS);
                            output.accept(ModItems.FNI_BOOTS);
                        }

                        // --- Coal ---
                        if (Config.coalEnabled) {
                            output.accept(ModItems.COAL_DUST);
                            output.accept(ModBlocks.COAL_DUST_BLOCK);
                            output.accept(ModItems.HARDENED_COAL);
                            output.accept(ModBlocks.HARDENED_COAL_BLOCK);
                            output.accept(ModItems.COAL_SWORD);
                            output.accept(ModItems.COAL_PICKAXE);
                            output.accept(ModItems.COAL_SHOVEL);
                            output.accept(ModItems.COAL_AXE);
                            output.accept(ModItems.COAL_HOE);
                            output.accept(ModItems.COAL_HELMET);
                            output.accept(ModItems.COAL_CHESTPLATE);
                            output.accept(ModItems.COAL_LEGGINGS);
                            output.accept(ModItems.COAL_BOOTS);
                        }

                        // --- Raw Metal Rough ---
                        if (Config.rawMetalRoughEnabled) {
                            output.accept(ModItems.RRAW_GOLD_SWORD);
                            output.accept(ModItems.RRAW_GOLD_PICKAXE);
                            output.accept(ModItems.RRAW_GOLD_SHOVEL);
                            output.accept(ModItems.RRAW_GOLD_AXE);
                            output.accept(ModItems.RRAW_GOLD_HOE);
                            output.accept(ModItems.RRAW_COPPER_SWORD);
                            output.accept(ModItems.RRAW_COPPER_PICKAXE);
                            output.accept(ModItems.RRAW_COPPER_SHOVEL);
                            output.accept(ModItems.RRAW_COPPER_AXE);
                            output.accept(ModItems.RRAW_COPPER_HOE);
                            output.accept(ModItems.RRAW_IRON_SWORD);
                            output.accept(ModItems.RRAW_IRON_PICKAXE);
                            output.accept(ModItems.RRAW_IRON_SHOVEL);
                            output.accept(ModItems.RRAW_IRON_AXE);
                            output.accept(ModItems.RRAW_IRON_HOE);
                            output.accept(ModItems.RRAW_RGOLD_SWORD);
                            output.accept(ModItems.RRAW_RGOLD_PICKAXE);
                            output.accept(ModItems.RRAW_RGOLD_SHOVEL);
                            output.accept(ModItems.RRAW_RGOLD_AXE);
                            output.accept(ModItems.RRAW_RGOLD_HOE);
                            output.accept(ModItems.RSCRAP_SWORD);
                            output.accept(ModItems.RSCRAP_PICKAXE);
                            output.accept(ModItems.RSCRAP_SHOVEL);
                            output.accept(ModItems.RSCRAP_AXE);
                            output.accept(ModItems.RSCRAP_HOE);
                        }

                        // =============================================================
                        //  CRYSTAL & ICE SETS
                        // =============================================================

                        // --- Crystal material items ---
                        if (Config.roughCrystalEnabled || Config.polishedCrystalEnabled) {
                            output.accept(ModItems.CALCIFIED_AMETHYST);
                            output.accept(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
                            output.accept(ModItems.POLISHED_QUARTZ);
                            output.accept(ModBlocks.POLISHED_QUARTZ_BLOCK);
                        }
                        if (Config.iceEnabled || Config.snowEnabled) {
                            output.accept(ModItems.GLACIAL_SHARD);
                            output.accept(ModBlocks.GLACIAL_SHARD_BLOCK);
                        }
                        if (Config.roughCrystalEnabled || Config.pprismEnabled) {
                            output.accept(ModItems.POLISHED_PRISMARINE);
                            output.accept(ModBlocks.POLISHED_PRISMARINE_BLOCK);
                        }

                        // --- Rough Crystal ---
                        if (Config.roughCrystalEnabled) {
                            output.accept(ModItems.RAMETHYST_SWORD);
                            output.accept(ModItems.RAMETHYST_PICKAXE);
                            output.accept(ModItems.RAMETHYST_SHOVEL);
                            output.accept(ModItems.RAMETHYST_AXE);
                            output.accept(ModItems.RAMETHYST_HOE);
                            output.accept(ModItems.RQUARTZ_SWORD);
                            output.accept(ModItems.RQUARTZ_PICKAXE);
                            output.accept(ModItems.RQUARTZ_SHOVEL);
                            output.accept(ModItems.RQUARTZ_AXE);
                            output.accept(ModItems.RQUARTZ_HOE);
                            output.accept(ModItems.RPRISM_SWORD);
                            output.accept(ModItems.RPRISM_PICKAXE);
                            output.accept(ModItems.RPRISM_SHOVEL);
                            output.accept(ModItems.RPRISM_AXE);
                            output.accept(ModItems.RPRISM_HOE);
                        }

                        // --- Snow ---
                        if (Config.snowEnabled) {
                            output.accept(ModItems.SNOW_SWORD);
                            output.accept(ModItems.SNOW_PICKAXE);
                            output.accept(ModItems.SNOW_SHOVEL);
                            output.accept(ModItems.SNOW_AXE);
                            output.accept(ModItems.SNOW_HOE);
                        }

                        // --- Ice (Glacial) ---
                        if (Config.iceEnabled) {
                            output.accept(ModItems.ICE_SWORD);
                            output.accept(ModItems.ICE_PICKAXE);
                            output.accept(ModItems.ICE_SHOVEL);
                            output.accept(ModItems.ICE_AXE);
                            output.accept(ModItems.ICE_HOE);
                            output.accept(ModItems.ICE_HELMET);
                            output.accept(ModItems.ICE_CHESTPLATE);
                            output.accept(ModItems.ICE_LEGGINGS);
                            output.accept(ModItems.ICE_BOOTS);
                        }

                        // --- Polished Crystal ---
                        if (Config.polishedCrystalEnabled) {
                            output.accept(ModItems.CAMETHYST_SWORD);
                            output.accept(ModItems.CAMETHYST_PICKAXE);
                            output.accept(ModItems.CAMETHYST_SHOVEL);
                            output.accept(ModItems.CAMETHYST_AXE);
                            output.accept(ModItems.CAMETHYST_HOE);
                            output.accept(ModItems.CAMETHYST_HELMET);
                            output.accept(ModItems.CAMETHYST_CHESTPLATE);
                            output.accept(ModItems.CAMETHYST_LEGGINGS);
                            output.accept(ModItems.CAMETHYST_BOOTS);
                            output.accept(ModItems.PQUARTZ_SWORD);
                            output.accept(ModItems.PQUARTZ_PICKAXE);
                            output.accept(ModItems.PQUARTZ_SHOVEL);
                            output.accept(ModItems.PQUARTZ_AXE);
                            output.accept(ModItems.PQUARTZ_HOE);
                            output.accept(ModItems.PQUARTZ_HELMET);
                            output.accept(ModItems.PQUARTZ_CHESTPLATE);
                            output.accept(ModItems.PQUARTZ_LEGGINGS);
                            output.accept(ModItems.PQUARTZ_BOOTS);
                        }

                        // --- Polished Prismarine ---
                        if (Config.pprismEnabled) {
                            output.accept(ModItems.PPRISM_SWORD);
                            output.accept(ModItems.PPRISM_PICKAXE);
                            output.accept(ModItems.PPRISM_SHOVEL);
                            output.accept(ModItems.PPRISM_AXE);
                            output.accept(ModItems.PPRISM_HOE);
                            output.accept(ModItems.PPRISM_HELMET);
                            output.accept(ModItems.PPRISM_CHESTPLATE);
                            output.accept(ModItems.PPRISM_LEGGINGS);
                            output.accept(ModItems.PPRISM_BOOTS);
                        }

                        // =============================================================
                        //  METAL & GEM SETS
                        // =============================================================

                        // --- Ferrous Gold ---
                        if (Config.ferrousGoldEnabled) {
                            output.accept(ModBlocks.RGOLDBLOCK);
                            output.accept(ModBlocks.RAW_RGOLD_BLOCK);
                            output.accept(ModBlocks.RGOLDORE);
                            output.accept(ModBlocks.RGOLD_NETHER_ORE);
                            output.accept(ModBlocks.RGOLD_END_ORE);
                            output.accept(ModBlocks.RGOLD_DEEPSLATE_ORE);
                            output.accept(ModItems.RAW_RGOLD);
                            output.accept(ModItems.RGOLD);
                            output.accept(ModItems.RGOLD_AXE);
                            output.accept(ModItems.RGOLD_PICKAXE);
                            output.accept(ModItems.RGOLD_SWORD);
                            output.accept(ModItems.RGOLD_SHOVEL);
                            output.accept(ModItems.RGOLD_HOE);
                            output.accept(ModItems.RGOLD_HELMET);
                            output.accept(ModItems.RGOLD_CHESTPLATE);
                            output.accept(ModItems.RGOLD_LEGGINGS);
                            output.accept(ModItems.RGOLD_BOOTS);
                        }

                        // --- Lapis ---
                        if (Config.lapisEnabled) {
                            output.accept(ModBlocks.LBLOCK);
                            output.accept(ModItems.RLAPIS);
                            output.accept(ModItems.RLAPIS_AXE);
                            output.accept(ModItems.RLAPIS_PICKAXE);
                            output.accept(ModItems.RLAPIS_SWORD);
                            output.accept(ModItems.RLAPIS_SHOVEL);
                            output.accept(ModItems.RLAPIS_HOE);
                            output.accept(ModItems.RLAPIS_HELMET);
                            output.accept(ModItems.RLAPIS_CHESTPLATE);
                            output.accept(ModItems.RLAPIS_LEGGINGS);
                            output.accept(ModItems.RLAPIS_BOOTS);
                        }

                        // --- Hardened Redstone ---
                        if (Config.hardenedRedstoneEnabled) {
                            output.accept(ModItems.HRED);
                            output.accept(ModBlocks.HRBLOCK);
                            output.accept(ModItems.HREDSTONE_AXE);
                            output.accept(ModItems.HREDSTONE_PICKAXE);
                            output.accept(ModItems.HREDSTONE_SWORD);
                            output.accept(ModItems.HREDSTONE_SHOVEL);
                            output.accept(ModItems.HREDSTONE_HOE);
                            output.accept(ModItems.HRED_HELMET);
                            output.accept(ModItems.HRED_CHESTPLATE);
                            output.accept(ModItems.HRED_LEGGINGS);
                            output.accept(ModItems.HRED_BOOTS);
                        }

                        // --- Hardened Glowstone ---
                        if (Config.hardenedGlowstoneEnabled) {
                            output.accept(ModItems.HGLOW);
                            output.accept(ModBlocks.HGLOW_BLOCK);
                            output.accept(ModItems.HGLOWSTONE_AXE);
                            output.accept(ModItems.HGLOWSTONE_PICKAXE);
                            output.accept(ModItems.HGLOWSTONE_SWORD);
                            output.accept(ModItems.HGLOWSTONE_SHOVEL);
                            output.accept(ModItems.HGLOWSTONE_HOE);
                            output.accept(ModItems.HGLOW_HELMET);
                            output.accept(ModItems.HGLOW_CHESTPLATE);
                            output.accept(ModItems.HGLOW_LEGGINGS);
                            output.accept(ModItems.HGLOW_BOOTS);
                        }

                        // --- Emerald ---
                        if (Config.emeraldEnabled) {
                            output.accept(ModBlocks.SEMBLOCK);
                            output.accept(ModItems.SEM);
                            output.accept(ModItems.PEMERALD_AXE);
                            output.accept(ModItems.PEMERALD_PICKAXE);
                            output.accept(ModItems.PEMERALD_SWORD);
                            output.accept(ModItems.PEMERALD_SHOVEL);
                            output.accept(ModItems.PEMERALD_HOE);
                            output.accept(ModItems.REMERALD_AXE);
                            output.accept(ModItems.REMERALD_PICKAXE);
                            output.accept(ModItems.REMERALD_SWORD);
                            output.accept(ModItems.REMERALD_SHOVEL);
                            output.accept(ModItems.REMERALD_HOE);
                            output.accept(ModItems.EMERALD_HELMET);
                            output.accept(ModItems.EMERALD_CHESTPLATE);
                            output.accept(ModItems.EMERALD_LEGGINGS);
                            output.accept(ModItems.EMERALD_BOOTS);
                        }

                        // --- Obsidian ---
                        if (Config.obsidianEnabled) {
                            output.accept(ModItems.OBINGOT);
                            output.accept(ModItems.OBSHARD);
                            output.accept(ModBlocks.SOBLOCK);
                            output.accept(ModBlocks.OBSHARD_BLOCK);
                            output.accept(ModItems.ROBSIDIAN_AXE);
                            output.accept(ModItems.ROBSIDIAN_PICKAXE);
                            output.accept(ModItems.ROBSIDIAN_SWORD);
                            output.accept(ModItems.ROBSIDIAN_SHOVEL);
                            output.accept(ModItems.ROBSIDIAN_HOE);
                            output.accept(ModItems.POBSIDIAN_AXE);
                            output.accept(ModItems.POBSIDIAN_PICKAXE);
                            output.accept(ModItems.POBSIDIAN_SWORD);
                            output.accept(ModItems.POBSIDIAN_SHOVEL);
                            output.accept(ModItems.POBSIDIAN_HOE);
                            output.accept(ModItems.OBSIDIAN_HELMET);
                            output.accept(ModItems.OBSIDIAN_CHESTPLATE);
                            output.accept(ModItems.OBSIDIAN_LEGGINGS);
                            output.accept(ModItems.OBSIDIAN_BOOTS);
                        }

                        // =============================================================
                        //  GHOST & ECTOPLASM
                        // =============================================================

                        if (Config.ghostEnabled) {
                            output.accept(ModItems.GHOST_SPAWN_EGG);
                            output.accept(ModItems.ECTOPLASM);
                        }

                        if (Config.spectralInfuserEnabled) {
                            output.accept(ModBlocks.SPECTRAL_INFUSER);
                        }

                        if (Config.ectoplasmSetEnabled) {
                            output.accept(ModBlocks.ECTOPLASM_BLOCK);
                            output.accept(ModItems.RECTO_SWORD);
                            output.accept(ModItems.RECTO_PICKAXE);
                            output.accept(ModItems.RECTO_SHOVEL);
                            output.accept(ModItems.RECTO_AXE);
                            output.accept(ModItems.RECTO_HOE);
                            output.accept(ModItems.REFINED_ECTOPLASM);
                            output.accept(ModBlocks.REFINED_ECTOPLASM_BLOCK);
                            output.accept(ModItems.ECTO_SWORD);
                            output.accept(ModItems.ECTO_PICKAXE);
                            output.accept(ModItems.ECTO_SHOVEL);
                            output.accept(ModItems.ECTO_AXE);
                            output.accept(ModItems.ECTO_HOE);
                            output.accept(ModItems.ECTO_HELMET);
                            output.accept(ModItems.ECTO_CHESTPLATE);
                            output.accept(ModItems.ECTO_LEGGINGS);
                            output.accept(ModItems.ECTO_BOOTS);
                        }

                        // =============================================================
                        //  OVERPOWER (end-game)
                        // =============================================================

                        if (Config.overpowerEnabled) {
                            output.accept(ModItems.OVERPOWER_AXE);
                            output.accept(ModItems.OVERPOWER_PICKAXE);
                            output.accept(ModItems.OVERPOWER_SWORD);
                            output.accept(ModItems.OVERPOWER_SHOVEL);
                            output.accept(ModItems.OVERPOWER_HELMET);
                            output.accept(ModItems.OVERPOWER_CHESTPLATE);
                            output.accept(ModItems.OVERPOWER_LEGGINGS);
                            output.accept(ModItems.OVERPOWER_BOOTS);
                        }

                        // =============================================================
                        //  VANILLA MATERIAL SETS (25 sets, ordered by power tier)
                        // =============================================================

                        // --- Paper (tools only) ---
                        if (Config.paperEnabled) {
                            output.accept(ModItems.PAPER_SWORD); output.accept(ModItems.PAPER_PICKAXE);
                            output.accept(ModItems.PAPER_SHOVEL); output.accept(ModItems.PAPER_AXE); output.accept(ModItems.PAPER_HOE);
                        }

                        // --- Feather (tools only) ---
                        if (Config.featherEnabled) {
                            output.accept(ModItems.FEATHER_SWORD); output.accept(ModItems.FEATHER_PICKAXE);
                            output.accept(ModItems.FEATHER_SHOVEL); output.accept(ModItems.FEATHER_AXE); output.accept(ModItems.FEATHER_HOE);
                        }

                        // --- Glass (tools only) ---
                        if (Config.glassEnabled) {
                            output.accept(ModItems.GLASS_SWORD); output.accept(ModItems.GLASS_PICKAXE);
                            output.accept(ModItems.GLASS_SHOVEL); output.accept(ModItems.GLASS_AXE); output.accept(ModItems.GLASS_HOE);
                        }

                        // --- Rabbit Hide (armor only) ---
                        if (Config.rabbitHideEnabled) {
                            output.accept(ModItems.RABBIT_HIDE_HELMET); output.accept(ModItems.RABBIT_HIDE_CHESTPLATE);
                            output.accept(ModItems.RABBIT_HIDE_LEGGINGS); output.accept(ModItems.RABBIT_HIDE_BOOTS);
                        }

                        // --- Cactus (tools + armor) ---
                        if (Config.cactusEnabled) {
                            output.accept(ModItems.CACTUS_SWORD); output.accept(ModItems.CACTUS_PICKAXE);
                            output.accept(ModItems.CACTUS_SHOVEL); output.accept(ModItems.CACTUS_AXE); output.accept(ModItems.CACTUS_HOE);
                            output.accept(ModItems.CACTUS_HELMET); output.accept(ModItems.CACTUS_CHESTPLATE);
                            output.accept(ModItems.CACTUS_LEGGINGS); output.accept(ModItems.CACTUS_BOOTS);
                        }

                        // --- Sponge (tools only) ---
                        if (Config.spongeEnabled) {
                            output.accept(ModItems.SPONGE_SWORD); output.accept(ModItems.SPONGE_PICKAXE);
                            output.accept(ModItems.SPONGE_SHOVEL); output.accept(ModItems.SPONGE_AXE); output.accept(ModItems.SPONGE_HOE);
                        }

                        // --- Bone (tools + armor) ---
                        if (Config.boneEnabled) {
                            output.accept(ModItems.BONE_SWORD); output.accept(ModItems.BONE_PICKAXE);
                            output.accept(ModItems.BONE_SHOVEL); output.accept(ModItems.BONE_AXE); output.accept(ModItems.BONE_HOE);
                            output.accept(ModItems.BONE_HELMET); output.accept(ModItems.BONE_CHESTPLATE);
                            output.accept(ModItems.BONE_LEGGINGS); output.accept(ModItems.BONE_BOOTS);
                        }

                        // --- Clay (tools + armor) ---
                        if (Config.clayEnabled) {
                            output.accept(ModItems.CLAY_SWORD); output.accept(ModItems.CLAY_PICKAXE);
                            output.accept(ModItems.CLAY_SHOVEL); output.accept(ModItems.CLAY_AXE); output.accept(ModItems.CLAY_HOE);
                            output.accept(ModItems.CLAY_HELMET); output.accept(ModItems.CLAY_CHESTPLATE);
                            output.accept(ModItems.CLAY_LEGGINGS); output.accept(ModItems.CLAY_BOOTS);
                        }

                        // --- Nether Wart (tools only) ---
                        if (Config.netherWartEnabled) {
                            output.accept(ModItems.NETHER_WART_SWORD); output.accept(ModItems.NETHER_WART_PICKAXE);
                            output.accept(ModItems.NETHER_WART_SHOVEL); output.accept(ModItems.NETHER_WART_AXE); output.accept(ModItems.NETHER_WART_HOE);
                        }

                        // --- Brick (tools + armor) ---
                        if (Config.brickEnabled) {
                            output.accept(ModItems.BRICK_SWORD); output.accept(ModItems.BRICK_PICKAXE);
                            output.accept(ModItems.BRICK_SHOVEL); output.accept(ModItems.BRICK_AXE); output.accept(ModItems.BRICK_HOE);
                            output.accept(ModItems.BRICK_HELMET); output.accept(ModItems.BRICK_CHESTPLATE);
                            output.accept(ModItems.BRICK_LEGGINGS); output.accept(ModItems.BRICK_BOOTS);
                        }

                        // --- Nether Brick (tools + armor) ---
                        if (Config.netherBrickEnabled) {
                            output.accept(ModItems.NETHER_BRICK_SWORD); output.accept(ModItems.NETHER_BRICK_PICKAXE);
                            output.accept(ModItems.NETHER_BRICK_SHOVEL); output.accept(ModItems.NETHER_BRICK_AXE); output.accept(ModItems.NETHER_BRICK_HOE);
                            output.accept(ModItems.NETHER_BRICK_HELMET); output.accept(ModItems.NETHER_BRICK_CHESTPLATE);
                            output.accept(ModItems.NETHER_BRICK_LEGGINGS); output.accept(ModItems.NETHER_BRICK_BOOTS);
                        }

                        // --- Pointed Dripstone (tools only) ---
                        if (Config.dripstoneEnabled) {
                            output.accept(ModItems.POINTED_DRIPSTONE_SWORD); output.accept(ModItems.POINTED_DRIPSTONE_PICKAXE);
                            output.accept(ModItems.POINTED_DRIPSTONE_SHOVEL); output.accept(ModItems.POINTED_DRIPSTONE_AXE); output.accept(ModItems.POINTED_DRIPSTONE_HOE);
                        }

                        // --- Copper (tools + armor) ---
                        if (Config.copperEnabled) {
                            output.accept(ModItems.COPPER_SWORD); output.accept(ModItems.COPPER_PICKAXE);
                            output.accept(ModItems.COPPER_SHOVEL); output.accept(ModItems.COPPER_AXE); output.accept(ModItems.COPPER_HOE);
                            output.accept(ModItems.COPPER_HELMET); output.accept(ModItems.COPPER_CHESTPLATE);
                            output.accept(ModItems.COPPER_LEGGINGS); output.accept(ModItems.COPPER_BOOTS);
                        }

                        // --- Phantom Membrane (tools + armor) ---
                        if (Config.phantomEnabled) {
                            output.accept(ModItems.PHANTOM_SWORD); output.accept(ModItems.PHANTOM_PICKAXE);
                            output.accept(ModItems.PHANTOM_SHOVEL); output.accept(ModItems.PHANTOM_AXE); output.accept(ModItems.PHANTOM_HOE);
                            output.accept(ModItems.PHANTOM_HELMET); output.accept(ModItems.PHANTOM_CHESTPLATE);
                            output.accept(ModItems.PHANTOM_LEGGINGS); output.accept(ModItems.PHANTOM_BOOTS);
                        }

                        // --- Magma Cream (tools + armor) ---
                        if (Config.magmaCreamEnabled) {
                            output.accept(ModItems.MAGMA_CREAM_SWORD); output.accept(ModItems.MAGMA_CREAM_PICKAXE);
                            output.accept(ModItems.MAGMA_CREAM_SHOVEL); output.accept(ModItems.MAGMA_CREAM_AXE); output.accept(ModItems.MAGMA_CREAM_HOE);
                            output.accept(ModItems.MAGMA_CREAM_HELMET); output.accept(ModItems.MAGMA_CREAM_CHESTPLATE);
                            output.accept(ModItems.MAGMA_CREAM_LEGGINGS); output.accept(ModItems.MAGMA_CREAM_BOOTS);
                        }

                        // --- Slime (tools + armor) ---
                        if (Config.slimeEnabled) {
                            output.accept(ModItems.SLIME_SWORD); output.accept(ModItems.SLIME_PICKAXE);
                            output.accept(ModItems.SLIME_SHOVEL); output.accept(ModItems.SLIME_AXE); output.accept(ModItems.SLIME_HOE);
                            output.accept(ModItems.SLIME_HELMET); output.accept(ModItems.SLIME_CHESTPLATE);
                            output.accept(ModItems.SLIME_LEGGINGS); output.accept(ModItems.SLIME_BOOTS);
                        }

                        // --- Blaze Rod (tools + armor) ---
                        if (Config.blazeEnabled) {
                            output.accept(ModItems.BLAZE_SWORD); output.accept(ModItems.BLAZE_PICKAXE);
                            output.accept(ModItems.BLAZE_SHOVEL); output.accept(ModItems.BLAZE_AXE); output.accept(ModItems.BLAZE_HOE);
                            output.accept(ModItems.BLAZE_HELMET); output.accept(ModItems.BLAZE_CHESTPLATE);
                            output.accept(ModItems.BLAZE_LEGGINGS); output.accept(ModItems.BLAZE_BOOTS);
                        }

                        // --- Nautilus Shell (tools + armor) ---
                        if (Config.nautilusEnabled) {
                            output.accept(ModItems.NAUTILUS_SWORD); output.accept(ModItems.NAUTILUS_PICKAXE);
                            output.accept(ModItems.NAUTILUS_SHOVEL); output.accept(ModItems.NAUTILUS_AXE); output.accept(ModItems.NAUTILUS_HOE);
                            output.accept(ModItems.NAUTILUS_HELMET); output.accept(ModItems.NAUTILUS_CHESTPLATE);
                            output.accept(ModItems.NAUTILUS_LEGGINGS); output.accept(ModItems.NAUTILUS_BOOTS);
                        }

                        // --- Purpur (tools + armor) ---
                        if (Config.purpurEnabled) {
                            output.accept(ModItems.PURPUR_SWORD); output.accept(ModItems.PURPUR_PICKAXE);
                            output.accept(ModItems.PURPUR_SHOVEL); output.accept(ModItems.PURPUR_AXE); output.accept(ModItems.PURPUR_HOE);
                            output.accept(ModItems.PURPUR_HELMET); output.accept(ModItems.PURPUR_CHESTPLATE);
                            output.accept(ModItems.PURPUR_LEGGINGS); output.accept(ModItems.PURPUR_BOOTS);
                        }

                        // --- Ghast Tear (tools + armor) ---
                        if (Config.ghastTearEnabled) {
                            output.accept(ModItems.GHAST_TEAR_SWORD); output.accept(ModItems.GHAST_TEAR_PICKAXE);
                            output.accept(ModItems.GHAST_TEAR_SHOVEL); output.accept(ModItems.GHAST_TEAR_AXE); output.accept(ModItems.GHAST_TEAR_HOE);
                            output.accept(ModItems.GHAST_TEAR_HELMET); output.accept(ModItems.GHAST_TEAR_CHESTPLATE);
                            output.accept(ModItems.GHAST_TEAR_LEGGINGS); output.accept(ModItems.GHAST_TEAR_BOOTS);
                        }

                        // --- Eye of Ender (tools + armor) ---
                        if (Config.eyeOfEnderEnabled) {
                            output.accept(ModItems.EYE_OF_ENDER_SWORD); output.accept(ModItems.EYE_OF_ENDER_PICKAXE);
                            output.accept(ModItems.EYE_OF_ENDER_SHOVEL); output.accept(ModItems.EYE_OF_ENDER_AXE); output.accept(ModItems.EYE_OF_ENDER_HOE);
                            output.accept(ModItems.EYE_OF_ENDER_HELMET); output.accept(ModItems.EYE_OF_ENDER_CHESTPLATE);
                            output.accept(ModItems.EYE_OF_ENDER_LEGGINGS); output.accept(ModItems.EYE_OF_ENDER_BOOTS);
                        }

                        // --- Shulker Shell (tools + armor) ---
                        if (Config.shulkerEnabled) {
                            output.accept(ModItems.SHULKER_SWORD); output.accept(ModItems.SHULKER_PICKAXE);
                            output.accept(ModItems.SHULKER_SHOVEL); output.accept(ModItems.SHULKER_AXE); output.accept(ModItems.SHULKER_HOE);
                            output.accept(ModItems.SHULKER_HELMET); output.accept(ModItems.SHULKER_CHESTPLATE);
                            output.accept(ModItems.SHULKER_LEGGINGS); output.accept(ModItems.SHULKER_BOOTS);
                        }

                        // --- Turtle Scute (armor only) ---
                        if (Config.turtleScuteEnabled) {
                            output.accept(ModItems.TURTLE_SCUTE_HELMET); output.accept(ModItems.TURTLE_SCUTE_CHESTPLATE);
                            output.accept(ModItems.TURTLE_SCUTE_LEGGINGS); output.accept(ModItems.TURTLE_SCUTE_BOOTS);
                        }

                        // --- Echo Shard (tools + armor) ---
                        if (Config.echoShardEnabled) {
                            output.accept(ModItems.ECHO_SHARD_SWORD); output.accept(ModItems.ECHO_SHARD_PICKAXE);
                            output.accept(ModItems.ECHO_SHARD_SHOVEL); output.accept(ModItems.ECHO_SHARD_AXE); output.accept(ModItems.ECHO_SHARD_HOE);
                            output.accept(ModItems.ECHO_SHARD_HELMET); output.accept(ModItems.ECHO_SHARD_CHESTPLATE);
                            output.accept(ModItems.ECHO_SHARD_LEGGINGS); output.accept(ModItems.ECHO_SHARD_BOOTS);
                        }

                        // --- Dragon's Breath (tools + armor) ---
                        if (Config.dragonBreathEnabled) {
                            output.accept(ModItems.DRAGON_BREATH_SWORD); output.accept(ModItems.DRAGON_BREATH_PICKAXE);
                            output.accept(ModItems.DRAGON_BREATH_SHOVEL); output.accept(ModItems.DRAGON_BREATH_AXE); output.accept(ModItems.DRAGON_BREATH_HOE);
                            output.accept(ModItems.DRAGON_BREATH_HELMET); output.accept(ModItems.DRAGON_BREATH_CHESTPLATE);
                            output.accept(ModItems.DRAGON_BREATH_LEGGINGS); output.accept(ModItems.DRAGON_BREATH_BOOTS);
                        }

                        // =============================================================
                        //  FOOD SETS (edible novelty — weakest to strongest)
                        // =============================================================

                        // --- Cake ---
                        if (Config.cakeEnabled) {
                            output.accept(ModItems.CAKE_SWORD);
                            output.accept(ModItems.CAKE_PICKAXE);
                            output.accept(ModItems.CAKE_SHOVEL);
                            output.accept(ModItems.CAKE_AXE);
                            output.accept(ModItems.CAKE_HOE);
                            output.accept(ModItems.CAKE_HELMET);
                            output.accept(ModItems.CAKE_CHESTPLATE);
                            output.accept(ModItems.CAKE_LEGGINGS);
                            output.accept(ModItems.CAKE_BOOTS);
                        }

                        // --- Dried Kelp ---
                        if (Config.driedKelpEnabled) {
                            output.accept(ModItems.DRIED_KELP_SWORD);
                            output.accept(ModItems.DRIED_KELP_PICKAXE);
                            output.accept(ModItems.DRIED_KELP_SHOVEL);
                            output.accept(ModItems.DRIED_KELP_AXE);
                            output.accept(ModItems.DRIED_KELP_HOE);
                            output.accept(ModItems.DRIED_KELP_HELMET);
                            output.accept(ModItems.DRIED_KELP_CHESTPLATE);
                            output.accept(ModItems.DRIED_KELP_LEGGINGS);
                            output.accept(ModItems.DRIED_KELP_BOOTS);
                        }

                        // --- Rotten Flesh ---
                        if (Config.rottenFleshEnabled) {
                            output.accept(ModItems.ROTTEN_FLESH_SWORD);
                            output.accept(ModItems.ROTTEN_FLESH_PICKAXE);
                            output.accept(ModItems.ROTTEN_FLESH_SHOVEL);
                            output.accept(ModItems.ROTTEN_FLESH_AXE);
                            output.accept(ModItems.ROTTEN_FLESH_HOE);
                            output.accept(ModItems.ROTTEN_FLESH_HELMET);
                            output.accept(ModItems.ROTTEN_FLESH_CHESTPLATE);
                            output.accept(ModItems.ROTTEN_FLESH_LEGGINGS);
                            output.accept(ModItems.ROTTEN_FLESH_BOOTS);
                        }

                        // --- Bread ---
                        if (Config.breadEnabled) {
                            output.accept(ModItems.BREAD_SWORD);
                            output.accept(ModItems.BREAD_PICKAXE);
                            output.accept(ModItems.BREAD_SHOVEL);
                            output.accept(ModItems.BREAD_AXE);
                            output.accept(ModItems.BREAD_HOE);
                            output.accept(ModItems.BREAD_HELMET);
                            output.accept(ModItems.BREAD_CHESTPLATE);
                            output.accept(ModItems.BREAD_LEGGINGS);
                            output.accept(ModItems.BREAD_BOOTS);
                        }

                        // --- Sweet Berries ---
                        if (Config.sweetBerryEnabled) {
                            output.accept(ModItems.SWEET_BERRY_SWORD);
                            output.accept(ModItems.SWEET_BERRY_PICKAXE);
                            output.accept(ModItems.SWEET_BERRY_SHOVEL);
                            output.accept(ModItems.SWEET_BERRY_AXE);
                            output.accept(ModItems.SWEET_BERRY_HOE);
                            output.accept(ModItems.SWEET_BERRY_HELMET);
                            output.accept(ModItems.SWEET_BERRY_CHESTPLATE);
                            output.accept(ModItems.SWEET_BERRY_LEGGINGS);
                            output.accept(ModItems.SWEET_BERRY_BOOTS);
                        }

                        // --- Pumpkin Pie ---
                        if (Config.pumpkinPieEnabled) {
                            output.accept(ModItems.PUMPKIN_PIE_SWORD);
                            output.accept(ModItems.PUMPKIN_PIE_PICKAXE);
                            output.accept(ModItems.PUMPKIN_PIE_SHOVEL);
                            output.accept(ModItems.PUMPKIN_PIE_AXE);
                            output.accept(ModItems.PUMPKIN_PIE_HOE);
                            output.accept(ModItems.PUMPKIN_PIE_HELMET);
                            output.accept(ModItems.PUMPKIN_PIE_CHESTPLATE);
                            output.accept(ModItems.PUMPKIN_PIE_LEGGINGS);
                            output.accept(ModItems.PUMPKIN_PIE_BOOTS);
                        }

                        // --- Melon ---
                        if (Config.melonEnabled) {
                            output.accept(ModItems.MELON_SWORD);
                            output.accept(ModItems.MELON_PICKAXE);
                            output.accept(ModItems.MELON_SHOVEL);
                            output.accept(ModItems.MELON_AXE);
                            output.accept(ModItems.MELON_HOE);
                            output.accept(ModItems.MELON_HELMET);
                            output.accept(ModItems.MELON_CHESTPLATE);
                            output.accept(ModItems.MELON_LEGGINGS);
                            output.accept(ModItems.MELON_BOOTS);
                        }

                        // --- Mushroom ---
                        if (Config.mushroomEnabled) {
                            output.accept(ModItems.MUSHROOM_SWORD);
                            output.accept(ModItems.MUSHROOM_PICKAXE);
                            output.accept(ModItems.MUSHROOM_SHOVEL);
                            output.accept(ModItems.MUSHROOM_AXE);
                            output.accept(ModItems.MUSHROOM_HOE);
                            output.accept(ModItems.MUSHROOM_HELMET);
                            output.accept(ModItems.MUSHROOM_CHESTPLATE);
                            output.accept(ModItems.MUSHROOM_LEGGINGS);
                            output.accept(ModItems.MUSHROOM_BOOTS);
                        }

                        // --- Pufferfish ---
                        if (Config.pufferfishEnabled) {
                            output.accept(ModItems.PUFFERFISH_SWORD);
                            output.accept(ModItems.PUFFERFISH_PICKAXE);
                            output.accept(ModItems.PUFFERFISH_SHOVEL);
                            output.accept(ModItems.PUFFERFISH_AXE);
                            output.accept(ModItems.PUFFERFISH_HOE);
                            output.accept(ModItems.PUFFERFISH_HELMET);
                            output.accept(ModItems.PUFFERFISH_CHESTPLATE);
                            output.accept(ModItems.PUFFERFISH_LEGGINGS);
                            output.accept(ModItems.PUFFERFISH_BOOTS);
                        }

                        // --- Honey ---
                        if (Config.honeyEnabled) {
                            output.accept(ModItems.HONEY_SWORD);
                            output.accept(ModItems.HONEY_PICKAXE);
                            output.accept(ModItems.HONEY_SHOVEL);
                            output.accept(ModItems.HONEY_AXE);
                            output.accept(ModItems.HONEY_HOE);
                            output.accept(ModItems.HONEY_HELMET);
                            output.accept(ModItems.HONEY_CHESTPLATE);
                            output.accept(ModItems.HONEY_LEGGINGS);
                            output.accept(ModItems.HONEY_BOOTS);
                        }

                        // --- Chorus Fruit ---
                        if (Config.chorusFruitEnabled) {
                            output.accept(ModItems.CHORUS_FRUIT_SWORD);
                            output.accept(ModItems.CHORUS_FRUIT_PICKAXE);
                            output.accept(ModItems.CHORUS_FRUIT_SHOVEL);
                            output.accept(ModItems.CHORUS_FRUIT_AXE);
                            output.accept(ModItems.CHORUS_FRUIT_HOE);
                            output.accept(ModItems.CHORUS_FRUIT_HELMET);
                            output.accept(ModItems.CHORUS_FRUIT_CHESTPLATE);
                            output.accept(ModItems.CHORUS_FRUIT_LEGGINGS);
                            output.accept(ModItems.CHORUS_FRUIT_BOOTS);
                        }

                        // --- Golden Apple ---
                        if (Config.goldenAppleEnabled) {
                            output.accept(ModItems.GOLDEN_APPLE_SWORD);
                            output.accept(ModItems.GOLDEN_APPLE_PICKAXE);
                            output.accept(ModItems.GOLDEN_APPLE_SHOVEL);
                            output.accept(ModItems.GOLDEN_APPLE_AXE);
                            output.accept(ModItems.GOLDEN_APPLE_HOE);
                            output.accept(ModItems.GOLDEN_APPLE_HELMET);
                            output.accept(ModItems.GOLDEN_APPLE_CHESTPLATE);
                            output.accept(ModItems.GOLDEN_APPLE_LEGGINGS);
                            output.accept(ModItems.GOLDEN_APPLE_BOOTS);
                        }

                    }).build()
    );

    /** Force-loads the class so the static-init Registry.register call runs. Idempotent. */
    public static void register() {
        // no-op; touching the class is enough
    }
}
