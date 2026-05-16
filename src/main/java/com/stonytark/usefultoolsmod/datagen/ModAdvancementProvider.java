package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.trigger.CoalToolIgnitedTrigger;
import com.stonytark.usefultoolsmod.trigger.GhostNearbyTrigger;
import com.stonytark.usefultoolsmod.trigger.ModTriggers;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Advancement provider. The NeoForge sibling wraps an
 * {@link net.minecraft.data.advancements.AdvancementSubProvider} list inside
 * a vanilla {@link net.minecraft.data.advancements.AdvancementProvider}; for
 * Fabric we collapse the structure because
 * {@link FabricAdvancementProvider#generateAdvancement} already exposes the
 * same {@code (HolderLookup.Provider, Consumer<AdvancementHolder>)}
 * signature directly. Body of {@code generateAdvancement} is ported verbatim
 * from the sibling's {@code UsefulToolsAdvancements#generate}, with the
 * mod-registry references stripped of their NeoForge {@code .get()} bridge.
 */
public class ModAdvancementProvider extends FabricAdvancementProvider {

    // 26.1: advancement backgrounds switched from "textures/<path>.png" file paths
    // to sprite identifiers under "gui/advancements/backgrounds/<name>" (the vanilla
    // adventure/nether/end/husbandry/stone set). "nether" is the rocky-red look that
    // the old "textures/block/netherrack.png" was reaching for.
    private static final Identifier BACKGROUND =
            Identifier.withDefaultNamespace("gui/advancements/backgrounds/nether");

    /** Captured from {@link #generateAdvancement} so the {@code static} helper
     *  methods that build per-set advancements (e.g. {@code buildFoodAdv}) can
     *  still resolve item holders for
     *  {@link ConsumeItemTrigger.TriggerInstance#usedItem}. */
    private static HolderGetter<Item> ITEMS_LOOKUP;

    public ModAdvancementProvider(FabricPackOutput output,
                                  CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    /**
     * 26.1: {@code usedItem(items, Item)} no longer exists.
     * The replacement takes either an {@link ItemPredicate.Builder} or a
     * {@code (HolderGetter<Item>, ItemLike)} pair; this helper wraps the latter so the
     * call sites read like the old single-arg form.
     */
    private static net.minecraft.advancements.Criterion<ConsumeItemTrigger.TriggerInstance> usedItem(
            HolderGetter<Item> items, ItemLike item) {
        return ConsumeItemTrigger.TriggerInstance.usedItem(items, item);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registries,
                                    Consumer<AdvancementHolder> saver) {
            HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);
            ITEMS_LOOKUP = items;

            // ==================================================================
            // ROOT
            // ==================================================================
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItems.RAW_RGOLD,
                            title("root"),
                            desc("root"),
                            BACKGROUND,
                            AdvancementType.TASK,
                            false, false, false
                    )
                    .addCriterion("has_raw_rgold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_RGOLD))
                    .save(saver, id("root"));

            // ==================================================================
            // RGOLD ingot (smelt raw ore → ingot) — bridges root to tools/armor
            // ==================================================================
            AdvancementHolder rgold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RGOLD, title("rgold"), desc("rgold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD))
                    .save(saver, id("rgold"));

            // ==================================================================
            // BRANCH: Ore Discovery (from root)
            // ==================================================================
            AdvancementHolder oreFound = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.RGOLDORE, title("ore_found"), desc("ore_found"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLDORE))
                    .save(saver, id("ore_found"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_NETHER_ORE, title("nether_ore"), desc("nether_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_nether_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_NETHER_ORE))
                    .save(saver, id("nether_ore"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_END_ORE, title("end_ore"), desc("end_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_end_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_END_ORE))
                    .save(saver, id("end_ore"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_DEEPSLATE_ORE, title("deepslate_ore"), desc("deepslate_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_deepslate_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_DEEPSLATE_ORE))
                    .save(saver, id("deepslate_ore"));

            // ==================================================================
            // BRANCH: Reinforced Gold Tools / Armor (from rgold ingot)
            // ==================================================================
            AdvancementHolder rgoldSword = Advancement.Builder.advancement()
                    .parent(rgold)
                    .display(ModItems.RGOLD_SWORD, title("rgold_tools"), desc("rgold_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD_SWORD))
                    .save(saver, id("rgold_tools"));

            Advancement.Builder.advancement()
                    .parent(rgoldSword)
                    .display(ModItems.RGOLD_HELMET, title("rgold_armor"), desc("rgold_armor"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD_HELMET))
                    .save(saver, id("rgold_armor"));

            // ==================================================================
            // BRANCH: Obsidian (from root)
            // ==================================================================
            AdvancementHolder obshard = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OBSHARD, title("obshard"), desc("obshard"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_obshard",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSHARD))
                    .save(saver, id("obshard"));

            AdvancementHolder obingot = Advancement.Builder.advancement()
                    .parent(obshard)
                    .display(ModItems.OBINGOT, title("obingot"), desc("obingot"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_obingot",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBINGOT))
                    .save(saver, id("obingot"));

            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.ROBSIDIAN_SWORD, title("robsidian"), desc("robsidian"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_robsidian_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROBSIDIAN_SWORD))
                    .save(saver, id("robsidian"));

            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.POBSIDIAN_SWORD, title("pobsidian"), desc("pobsidian"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pobsidian_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POBSIDIAN_SWORD))
                    .save(saver, id("pobsidian"));

            // Full Obsidian armor (CHALLENGE — requires all 4 pieces)
            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.OBSIDIAN_CHESTPLATE, title("obsidian_armor"), desc("obsidian_armor"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("obsidian_armor"));

            // ==================================================================
            // BRANCH: Polished Emerald (from root)
            // ==================================================================
            AdvancementHolder sem = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.SEM, title("sem"), desc("sem"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_sem",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SEM))
                    .save(saver, id("sem"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.REMERALD_SWORD, title("remerald"), desc("remerald"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_remerald_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REMERALD_SWORD))
                    .save(saver, id("remerald"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.PEMERALD_SWORD, title("pemerald"), desc("pemerald"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pemerald_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEMERALD_SWORD))
                    .save(saver, id("pemerald"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.EMERALD_HELMET, title("emerald_armor"), desc("emerald_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("emerald_armor"));

            // ==================================================================
            // BRANCH: Hardened Redstone (from root)
            // ==================================================================
            AdvancementHolder hred = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.HRED, title("hred"), desc("hred"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hred",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED))
                    .save(saver, id("hred"));

            Advancement.Builder.advancement()
                    .parent(hred)
                    .display(ModItems.HREDSTONE_SWORD, title("hredstone_tools"), desc("hredstone_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hredstone_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HREDSTONE_SWORD))
                    .save(saver, id("hredstone_tools"));

            Advancement.Builder.advancement()
                    .parent(hred)
                    .display(ModItems.HRED_HELMET, title("hred_armor"), desc("hred_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("hred_armor"));

            // ==================================================================
            // BRANCH: Hardened Glowstone (from root)
            // ==================================================================
            AdvancementHolder hglow = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.HGLOW, title("hglow"), desc("hglow"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hglow",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW))
                    .save(saver, id("hglow"));

            Advancement.Builder.advancement()
                    .parent(hglow)
                    .display(ModItems.HGLOWSTONE_SWORD, title("hglowstone_tools"), desc("hglowstone_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hglowstone_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOWSTONE_SWORD))
                    .save(saver, id("hglowstone_tools"));

            Advancement.Builder.advancement()
                    .parent(hglow)
                    .display(ModItems.HGLOW_HELMET, title("hglow_armor"), desc("hglow_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("hglow_armor"));

            // ==================================================================
            // BRANCH: Reinforced Lapis (from root)
            // ==================================================================
            AdvancementHolder rlapis = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RLAPIS, title("rlapis"), desc("rlapis"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rlapis",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS))
                    .save(saver, id("rlapis"));

            Advancement.Builder.advancement()
                    .parent(rlapis)
                    .display(ModItems.RLAPIS_SWORD, title("rlapis_tools"), desc("rlapis_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rlapis_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_SWORD))
                    .save(saver, id("rlapis_tools"));

            Advancement.Builder.advancement()
                    .parent(rlapis)
                    .display(ModItems.RLAPIS_HELMET, title("rlapis_armor"), desc("rlapis_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rlapis_armor"));

            // ==================================================================
            // BRANCH: Coal Tools / Armor (from root)
            // ==================================================================
            AdvancementHolder coalDust = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.COAL_DUST, title("coal_dust"), desc("coal_dust"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_coal_dust",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST))
                    .save(saver, id("coal_dust"));

            AdvancementHolder hardenedCoal = Advancement.Builder.advancement()
                    .parent(coalDust)
                    .display(ModItems.HARDENED_COAL, title("hardened_coal"), desc("hardened_coal"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hardened_coal",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HARDENED_COAL))
                    .save(saver, id("hardened_coal"));

            AdvancementHolder coalTools = Advancement.Builder.advancement()
                    .parent(hardenedCoal)
                    .display(ModItems.COAL_PICKAXE, title("coal_tools"), desc("coal_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_PICKAXE))
                    .save(saver, id("coal_tools"));

            // Trial by Fire — custom trigger (CHALLENGE)
            Advancement.Builder.advancement()
                    .parent(coalTools)
                    .display(ModItems.COAL_SWORD, title("coal_trial_by_fire"), desc("coal_trial_by_fire"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("coal_tool_ignited",
                            ModTriggers.COAL_TOOL_IGNITED.createCriterion(
                                    new CoalToolIgnitedTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("coal_trial_by_fire"));

            AdvancementHolder coalArmor = Advancement.Builder.advancement()
                    .parent(hardenedCoal)
                    .display(ModItems.COAL_HELMET, title("coal_armor"), desc("coal_armor"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_coal_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_HELMET))
                    .save(saver, id("coal_armor"));

            // Burning Commitment — full coal armor set (CHALLENGE)
            Advancement.Builder.advancement()
                    .parent(coalArmor)
                    .display(ModItems.COAL_CHESTPLATE, title("coal_full_set"), desc("coal_full_set"),
                            null, AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("coal_full_set"));

            // ==================================================================
            // BRANCH: Explosives (from root)
            // ==================================================================
            AdvancementHolder grenade = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GRENADE, title("grenade"), desc("grenade"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_grenade",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GRENADE))
                    .save(saver, id("grenade"));

            Advancement.Builder.advancement()
                    .parent(grenade)
                    .display(ModItems.DYNAMITE, title("dynamite"), desc("dynamite"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_dynamite",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DYNAMITE))
                    .save(saver, id("dynamite"));

            // ==================================================================
            // BRANCH: Ghost (from root)
            // ==================================================================
            AdvancementHolder ghostEncounter = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GHOST_SPAWN_EGG, title("ghost_encounter"), desc("ghost_encounter"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("ghost_nearby",
                            ModTriggers.GHOST_NEARBY.createCriterion(
                                    new GhostNearbyTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("ghost_encounter"));

            AdvancementHolder ghostCompanion = Advancement.Builder.advancement()
                    .parent(ghostEncounter)
                    .display(ModItems.GHOST_SPAWN_EGG, title("ghost_companion"), desc("ghost_companion"),
                            null, AdvancementType.GOAL, true, true, true)
                    .addCriterion("ghost_nearby_again",
                            ModTriggers.GHOST_NEARBY.createCriterion(
                                    new GhostNearbyTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("ghost_companion"));

            // ------------------------------------------------------------------
            // SUB-BRANCH: Ectoplasm → Refined Ectoplasm → Ecto tools/armor
            //                                            → Spectral Infuser
            // ------------------------------------------------------------------
            AdvancementHolder ectoplasmAdv = Advancement.Builder.advancement()
                    .parent(ghostCompanion)
                    .display(ModItems.ECTOPLASM, title("ectoplasm"), desc("ectoplasm"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ectoplasm",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTOPLASM))
                    .save(saver, id("ectoplasm"));

            Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModItems.RECTO_SWORD, title("recto"), desc("recto"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_recto_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RECTO_SWORD))
                    .save(saver, id("recto"));

            AdvancementHolder refinedEctoplasm = Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModItems.REFINED_ECTOPLASM, title("refined_ectoplasm"), desc("refined_ectoplasm"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_refined_ectoplasm",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REFINED_ECTOPLASM))
                    .save(saver, id("refined_ectoplasm"));

            AdvancementHolder ectoTools = Advancement.Builder.advancement()
                    .parent(refinedEctoplasm)
                    .display(ModItems.ECTO_SWORD, title("ecto_tools"), desc("ecto_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ecto_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SWORD))
                    .save(saver, id("ecto_tools"));

            Advancement.Builder.advancement()
                    .parent(ectoTools)
                    .display(ModItems.ECTO_HELMET, title("ecto_set"), desc("ecto_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_HOE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ecto_set"));

            // Spectral Infuser (from ectoplasm branch)
            Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModBlocks.SPECTRAL_INFUSER, title("spectral_infuser"), desc("spectral_infuser"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_spectral_infuser",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SPECTRAL_INFUSER))
                    .save(saver, id("spectral_infuser"));

            // ==================================================================
            // BRANCH: Raw Metal Rough Tools (all from root)
            // ==================================================================

            // Raw Gold → Rough Raw Gold tools → full set
            AdvancementHolder rrawGold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_GOLD_SWORD, title("rraw_gold"), desc("rraw_gold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_gold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SWORD))
                    .save(saver, id("rraw_gold"));

            Advancement.Builder.advancement()
                    .parent(rrawGold)
                    .display(ModItems.RRAW_GOLD_PICKAXE, title("rraw_gold_set"), desc("rraw_gold_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_gold_set"));

            // Raw Copper → Rough Raw Copper tools → full set
            AdvancementHolder rrawCopper = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_COPPER_SWORD, title("rraw_copper"), desc("rraw_copper"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_copper_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SWORD))
                    .save(saver, id("rraw_copper"));

            Advancement.Builder.advancement()
                    .parent(rrawCopper)
                    .display(ModItems.RRAW_COPPER_PICKAXE, title("rraw_copper_set"), desc("rraw_copper_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_copper_set"));

            // Raw Iron → Rough Raw Iron tools → full set
            AdvancementHolder rrawIron = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_IRON_SWORD, title("rraw_iron"), desc("rraw_iron"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_iron_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SWORD))
                    .save(saver, id("rraw_iron"));

            Advancement.Builder.advancement()
                    .parent(rrawIron)
                    .display(ModItems.RRAW_IRON_PICKAXE, title("rraw_iron_set"), desc("rraw_iron_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_iron_set"));

            // Raw Ferrous Gold → Rough Raw Ferrous Gold tools → full set
            AdvancementHolder rrawRgold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_RGOLD_SWORD, title("rraw_rgold"), desc("rraw_rgold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_rgold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SWORD))
                    .save(saver, id("rraw_rgold"));

            Advancement.Builder.advancement()
                    .parent(rrawRgold)
                    .display(ModItems.RRAW_RGOLD_PICKAXE, title("rraw_rgold_set"), desc("rraw_rgold_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_rgold_set"));

            // Netherite Scrap → Rough Scrap tools → full set
            AdvancementHolder rscrap = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RSCRAP_SWORD, title("rscrap"), desc("rscrap"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rscrap_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SWORD))
                    .save(saver, id("rscrap"));

            Advancement.Builder.advancement()
                    .parent(rscrap)
                    .display(ModItems.RSCRAP_PICKAXE, title("rscrap_set"), desc("rscrap_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rscrap_set"));

            // ==================================================================
            // BRANCH: Crystal / element sets (all from root)
            // ==================================================================

            // Rough Amethyst
            AdvancementHolder ramethyst = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RAMETHYST_SWORD, title("ramethyst"), desc("ramethyst"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ramethyst_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SWORD))
                    .save(saver, id("ramethyst"));

            Advancement.Builder.advancement()
                    .parent(ramethyst)
                    .display(ModItems.RAMETHYST_PICKAXE, title("ramethyst_set"), desc("ramethyst_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ramethyst_set"));

            // Snow tools
            AdvancementHolder snowTools = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.SNOW_SWORD, title("snow_tools"), desc("snow_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_snow_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SWORD))
                    .save(saver, id("snow_tools"));

            Advancement.Builder.advancement()
                    .parent(snowTools)
                    .display(ModItems.SNOW_PICKAXE, title("snow_set"), desc("snow_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("snow_set"));

            // Rough Quartz
            AdvancementHolder rquartz = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RQUARTZ_SWORD, title("rquartz"), desc("rquartz"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rquartz_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SWORD))
                    .save(saver, id("rquartz"));

            Advancement.Builder.advancement()
                    .parent(rquartz)
                    .display(ModItems.RQUARTZ_PICKAXE, title("rquartz_set"), desc("rquartz_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rquartz_set"));

            // Rough Prismarine
            AdvancementHolder rprism = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RPRISM_SWORD, title("rprism"), desc("rprism"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rprism_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SWORD))
                    .save(saver, id("rprism"));

            Advancement.Builder.advancement()
                    .parent(rprism)
                    .display(ModItems.RPRISM_PICKAXE, title("rprism_set"), desc("rprism_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rprism_set"));

            // Calcified Amethyst (polished) — material + tools/armor
            AdvancementHolder calciteAmethyst = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.CALCIFIED_AMETHYST, title("calcified_amethyst"), desc("calcified_amethyst"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_calcified_amethyst",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CALCIFIED_AMETHYST))
                    .save(saver, id("calcified_amethyst"));

            AdvancementHolder camethystTools = Advancement.Builder.advancement()
                    .parent(calciteAmethyst)
                    .display(ModItems.CAMETHYST_SWORD, title("camethyst_tools"), desc("camethyst_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_camethyst_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SWORD))
                    .save(saver, id("camethyst_tools"));

            Advancement.Builder.advancement()
                    .parent(camethystTools)
                    .display(ModItems.CAMETHYST_HELMET, title("camethyst_set"), desc("camethyst_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_HOE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("camethyst_set"));

            // Ice / Glacial (polished) — material + tools/armor
            AdvancementHolder glacialShard = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GLACIAL_SHARD, title("glacial_shard"), desc("glacial_shard"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_glacial_shard",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLACIAL_SHARD))
                    .save(saver, id("glacial_shard"));

            AdvancementHolder iceTools = Advancement.Builder.advancement()
                    .parent(glacialShard)
                    .display(ModItems.ICE_SWORD, title("ice_tools"), desc("ice_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ice_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SWORD))
                    .save(saver, id("ice_tools"));

            Advancement.Builder.advancement()
                    .parent(iceTools)
                    .display(ModItems.ICE_HELMET, title("ice_set"), desc("ice_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_HOE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ice_set"));

            // Polished Quartz — material + tools/armor
            AdvancementHolder polishedQuartz = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.POLISHED_QUARTZ, title("polished_quartz"), desc("polished_quartz"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_polished_quartz",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLISHED_QUARTZ))
                    .save(saver, id("polished_quartz"));

            AdvancementHolder pquartzTools = Advancement.Builder.advancement()
                    .parent(polishedQuartz)
                    .display(ModItems.PQUARTZ_SWORD, title("pquartz_tools"), desc("pquartz_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pquartz_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SWORD))
                    .save(saver, id("pquartz_tools"));

            Advancement.Builder.advancement()
                    .parent(pquartzTools)
                    .display(ModItems.PQUARTZ_HELMET, title("pquartz_set"), desc("pquartz_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_HOE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("pquartz_set"));

            // Polished Prismarine — material + tools/armor
            AdvancementHolder polishedPrismarine = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.POLISHED_PRISMARINE, title("polished_prismarine"), desc("polished_prismarine"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_polished_prismarine",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLISHED_PRISMARINE))
                    .save(saver, id("polished_prismarine"));

            AdvancementHolder pprismTools = Advancement.Builder.advancement()
                    .parent(polishedPrismarine)
                    .display(ModItems.PPRISM_SWORD, title("pprism_tools"), desc("pprism_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pprism_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SWORD))
                    .save(saver, id("pprism_tools"));

            Advancement.Builder.advancement()
                    .parent(pprismTools)
                    .display(ModItems.PPRISM_HELMET, title("pprism_set"), desc("pprism_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_HOE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("pprism_set"));

            // ==================================================================
            // BRANCH: Overpower (from root) — CHALLENGE
            // ==================================================================
            AdvancementHolder overpowerSword = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OVERPOWER_SWORD, title("overpower"), desc("overpower"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_overpower_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SWORD))
                    .save(saver, id("overpower"));

            // Full Overpower set (GOAL)
            Advancement.Builder.advancement()
                    .parent(overpowerSword)
                    .display(ModItems.OVERPOWER_HELMET, title("overpower_set"), desc("overpower_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_AXE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("overpower_set"));

            // ==================================================================
            // BRANCH: Flint / FNI (from root)
            // ==================================================================
            AdvancementHolder rflintSword = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RFLINT_SWORD, title("rflint"), desc("rflint"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rflint_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RFLINT_SWORD))
                    .save(saver, id("rflint"));

            AdvancementHolder fniSword = Advancement.Builder.advancement()
                    .parent(rflintSword)
                    .display(ModItems.FNI_SWORD, title("fni_tools"), desc("fni_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_fni_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SWORD))
                    .save(saver, id("fni_tools"));

            Advancement.Builder.advancement()
                    .parent(fniSword)
                    .display(ModItems.FNI_BOOTS, title("fni_set"), desc("fni_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_AXE))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("fni_set"));

            // ==================================================================
            // BRANCH: Stone Toolkit (from root) — CHALLENGE
            // Collect a pickaxe crafted from every rock variant
            // ==================================================================
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GRANITE_PICKAXE, title("stone_toolkit"), desc("stone_toolkit"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_andesite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ANDESITE_PICKAXE))
                    .addCriterion("has_basalt_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASALT_PICKAXE))
                    .addCriterion("has_blackstone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BLACKSTONE_PICKAXE))
                    .addCriterion("has_calcite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CALCITE_PICKAXE))
                    .addCriterion("has_deepslate_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_PICKAXE))
                    .addCriterion("has_diorite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIORITE_PICKAXE))
                    .addCriterion("has_end_stone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.END_STONE_PICKAXE))
                    .addCriterion("has_granite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GRANITE_PICKAXE))
                    .addCriterion("has_netherrack_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERRACK_PICKAXE))
                    .addCriterion("has_sandstone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SANDSTONE_PICKAXE))
                    .addCriterion("has_smooth_basalt_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMOOTH_BASALT_PICKAXE))
                    .addCriterion("has_terracotta_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TERRACOTTA_PICKAXE))
                    .addCriterion("has_tuff_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TUFF_PICKAXE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("stone_toolkit"));

            // ==================================================================
            // BRANCH: Wood Toolkit (from root) — CHALLENGE
            // Collect a sword crafted from every wood variant
            // ==================================================================
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OAK_SWORD, title("wood_toolkit"), desc("wood_toolkit"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_oak_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OAK_SWORD))
                    .addCriterion("has_spruce_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPRUCE_SWORD))
                    .addCriterion("has_birch_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIRCH_SWORD))
                    .addCriterion("has_jungle_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JUNGLE_SWORD))
                    .addCriterion("has_acacia_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ACACIA_SWORD))
                    .addCriterion("has_dark_oak_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DARK_OAK_SWORD))
                    .addCriterion("has_mangrove_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MANGROVE_SWORD))
                    .addCriterion("has_cherry_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHERRY_SWORD))
                    .addCriterion("has_bamboo_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BAMBOO_SWORD))
                    .addCriterion("has_crimson_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRIMSON_SWORD))
                    .addCriterion("has_warped_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WARPED_SWORD))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("wood_toolkit"));

            // ==================================================================
            // BRANCH: Vanilla Material Sets (from root)
            // ==================================================================

            // --- Tools-only sets (5 items each) ---
            buildVanillaToolAdv(saver, root, "paper_craft", "paper_set",
                    ModItems.PAPER_SWORD,
                    ModItems.PAPER_SWORD, ModItems.PAPER_PICKAXE,
                    ModItems.PAPER_AXE, ModItems.PAPER_SHOVEL, ModItems.PAPER_HOE);

            buildVanillaToolAdv(saver, root, "feather_craft", "feather_set",
                    ModItems.FEATHER_SWORD,
                    ModItems.FEATHER_SWORD, ModItems.FEATHER_PICKAXE,
                    ModItems.FEATHER_AXE, ModItems.FEATHER_SHOVEL, ModItems.FEATHER_HOE);

            buildVanillaToolAdv(saver, root, "glass_craft", "glass_set",
                    ModItems.GLASS_SWORD,
                    ModItems.GLASS_SWORD, ModItems.GLASS_PICKAXE,
                    ModItems.GLASS_AXE, ModItems.GLASS_SHOVEL, ModItems.GLASS_HOE);

            buildVanillaToolAdv(saver, root, "sponge_craft", "sponge_set",
                    ModItems.SPONGE_SWORD,
                    ModItems.SPONGE_SWORD, ModItems.SPONGE_PICKAXE,
                    ModItems.SPONGE_AXE, ModItems.SPONGE_SHOVEL, ModItems.SPONGE_HOE);

            buildVanillaToolAdv(saver, root, "nether_wart_craft", "nether_wart_set",
                    ModItems.NETHER_WART_SWORD,
                    ModItems.NETHER_WART_SWORD, ModItems.NETHER_WART_PICKAXE,
                    ModItems.NETHER_WART_AXE, ModItems.NETHER_WART_SHOVEL, ModItems.NETHER_WART_HOE);

            buildVanillaToolAdv(saver, root, "pointed_dripstone_craft", "pointed_dripstone_set",
                    ModItems.POINTED_DRIPSTONE_SWORD,
                    ModItems.POINTED_DRIPSTONE_SWORD, ModItems.POINTED_DRIPSTONE_PICKAXE,
                    ModItems.POINTED_DRIPSTONE_AXE, ModItems.POINTED_DRIPSTONE_SHOVEL, ModItems.POINTED_DRIPSTONE_HOE);

            // --- Armor-only sets (4 items each) ---
            AdvancementHolder rabbitHideCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RABBIT_HIDE_HELMET, title("rabbit_hide_craft"), desc("rabbit_hide_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("rabbit_hide_craft"));

            Advancement.Builder.advancement()
                    .parent(rabbitHideCraft)
                    .display(ModItems.RABBIT_HIDE_CHESTPLATE, title("rabbit_hide_set"), desc("rabbit_hide_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rabbit_hide_set"));

            AdvancementHolder turtleScuteCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.TURTLE_SCUTE_HELMET, title("turtle_scute_craft"), desc("turtle_scute_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("turtle_scute_craft"));

            Advancement.Builder.advancement()
                    .parent(turtleScuteCraft)
                    .display(ModItems.TURTLE_SCUTE_CHESTPLATE, title("turtle_scute_set"), desc("turtle_scute_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_HELMET))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_CHESTPLATE))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_LEGGINGS))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("turtle_scute_set"));

            // --- Tools+Armor sets (9 items each) ---
            buildVanillaSetAdv(saver, root, "cactus_craft", "cactus_set",
                    ModItems.CACTUS_SWORD,
                    ModItems.CACTUS_SWORD, ModItems.CACTUS_PICKAXE,
                    ModItems.CACTUS_AXE, ModItems.CACTUS_SHOVEL, ModItems.CACTUS_HOE,
                    ModItems.CACTUS_HELMET, ModItems.CACTUS_CHESTPLATE,
                    ModItems.CACTUS_LEGGINGS, ModItems.CACTUS_BOOTS);

            buildVanillaSetAdv(saver, root, "bone_craft", "bone_set",
                    ModItems.BONE_SWORD,
                    ModItems.BONE_SWORD, ModItems.BONE_PICKAXE,
                    ModItems.BONE_AXE, ModItems.BONE_SHOVEL, ModItems.BONE_HOE,
                    ModItems.BONE_HELMET, ModItems.BONE_CHESTPLATE,
                    ModItems.BONE_LEGGINGS, ModItems.BONE_BOOTS);

            buildVanillaSetAdv(saver, root, "clay_craft", "clay_set",
                    ModItems.CLAY_SWORD,
                    ModItems.CLAY_SWORD, ModItems.CLAY_PICKAXE,
                    ModItems.CLAY_AXE, ModItems.CLAY_SHOVEL, ModItems.CLAY_HOE,
                    ModItems.CLAY_HELMET, ModItems.CLAY_CHESTPLATE,
                    ModItems.CLAY_LEGGINGS, ModItems.CLAY_BOOTS);

            buildVanillaSetAdv(saver, root, "brick_craft", "brick_set",
                    ModItems.BRICK_SWORD,
                    ModItems.BRICK_SWORD, ModItems.BRICK_PICKAXE,
                    ModItems.BRICK_AXE, ModItems.BRICK_SHOVEL, ModItems.BRICK_HOE,
                    ModItems.BRICK_HELMET, ModItems.BRICK_CHESTPLATE,
                    ModItems.BRICK_LEGGINGS, ModItems.BRICK_BOOTS);

            buildVanillaSetAdv(saver, root, "nether_brick_craft", "nether_brick_set",
                    ModItems.NETHER_BRICK_SWORD,
                    ModItems.NETHER_BRICK_SWORD, ModItems.NETHER_BRICK_PICKAXE,
                    ModItems.NETHER_BRICK_AXE, ModItems.NETHER_BRICK_SHOVEL, ModItems.NETHER_BRICK_HOE,
                    ModItems.NETHER_BRICK_HELMET, ModItems.NETHER_BRICK_CHESTPLATE,
                    ModItems.NETHER_BRICK_LEGGINGS, ModItems.NETHER_BRICK_BOOTS);

            buildVanillaSetAdv(saver, root, "copper_craft", "copper_set",
                    ModItems.COPPER_SWORD,
                    ModItems.COPPER_SWORD, ModItems.COPPER_PICKAXE,
                    ModItems.COPPER_AXE, ModItems.COPPER_SHOVEL, ModItems.COPPER_HOE,
                    ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE,
                    ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS);

            buildVanillaSetAdv(saver, root, "phantom_craft", "phantom_set",
                    ModItems.PHANTOM_SWORD,
                    ModItems.PHANTOM_SWORD, ModItems.PHANTOM_PICKAXE,
                    ModItems.PHANTOM_AXE, ModItems.PHANTOM_SHOVEL, ModItems.PHANTOM_HOE,
                    ModItems.PHANTOM_HELMET, ModItems.PHANTOM_CHESTPLATE,
                    ModItems.PHANTOM_LEGGINGS, ModItems.PHANTOM_BOOTS);

            buildVanillaSetAdv(saver, root, "magma_cream_craft", "magma_cream_set",
                    ModItems.MAGMA_CREAM_SWORD,
                    ModItems.MAGMA_CREAM_SWORD, ModItems.MAGMA_CREAM_PICKAXE,
                    ModItems.MAGMA_CREAM_AXE, ModItems.MAGMA_CREAM_SHOVEL, ModItems.MAGMA_CREAM_HOE,
                    ModItems.MAGMA_CREAM_HELMET, ModItems.MAGMA_CREAM_CHESTPLATE,
                    ModItems.MAGMA_CREAM_LEGGINGS, ModItems.MAGMA_CREAM_BOOTS);

            buildVanillaSetAdv(saver, root, "slime_craft", "slime_set",
                    ModItems.SLIME_SWORD,
                    ModItems.SLIME_SWORD, ModItems.SLIME_PICKAXE,
                    ModItems.SLIME_AXE, ModItems.SLIME_SHOVEL, ModItems.SLIME_HOE,
                    ModItems.SLIME_HELMET, ModItems.SLIME_CHESTPLATE,
                    ModItems.SLIME_LEGGINGS, ModItems.SLIME_BOOTS);

            buildVanillaSetAdv(saver, root, "blaze_craft", "blaze_set",
                    ModItems.BLAZE_SWORD,
                    ModItems.BLAZE_SWORD, ModItems.BLAZE_PICKAXE,
                    ModItems.BLAZE_AXE, ModItems.BLAZE_SHOVEL, ModItems.BLAZE_HOE,
                    ModItems.BLAZE_HELMET, ModItems.BLAZE_CHESTPLATE,
                    ModItems.BLAZE_LEGGINGS, ModItems.BLAZE_BOOTS);

            buildVanillaSetAdv(saver, root, "nautilus_craft", "nautilus_set",
                    ModItems.NAUTILUS_SWORD,
                    ModItems.NAUTILUS_SWORD, ModItems.NAUTILUS_PICKAXE,
                    ModItems.NAUTILUS_AXE, ModItems.NAUTILUS_SHOVEL, ModItems.NAUTILUS_HOE,
                    ModItems.NAUTILUS_HELMET, ModItems.NAUTILUS_CHESTPLATE,
                    ModItems.NAUTILUS_LEGGINGS, ModItems.NAUTILUS_BOOTS);

            buildVanillaSetAdv(saver, root, "purpur_craft", "purpur_set",
                    ModItems.PURPUR_SWORD,
                    ModItems.PURPUR_SWORD, ModItems.PURPUR_PICKAXE,
                    ModItems.PURPUR_AXE, ModItems.PURPUR_SHOVEL, ModItems.PURPUR_HOE,
                    ModItems.PURPUR_HELMET, ModItems.PURPUR_CHESTPLATE,
                    ModItems.PURPUR_LEGGINGS, ModItems.PURPUR_BOOTS);

            buildVanillaSetAdv(saver, root, "ghast_tear_craft", "ghast_tear_set",
                    ModItems.GHAST_TEAR_SWORD,
                    ModItems.GHAST_TEAR_SWORD, ModItems.GHAST_TEAR_PICKAXE,
                    ModItems.GHAST_TEAR_AXE, ModItems.GHAST_TEAR_SHOVEL, ModItems.GHAST_TEAR_HOE,
                    ModItems.GHAST_TEAR_HELMET, ModItems.GHAST_TEAR_CHESTPLATE,
                    ModItems.GHAST_TEAR_LEGGINGS, ModItems.GHAST_TEAR_BOOTS);

            buildVanillaSetAdv(saver, root, "eye_of_ender_craft", "eye_of_ender_set",
                    ModItems.EYE_OF_ENDER_SWORD,
                    ModItems.EYE_OF_ENDER_SWORD, ModItems.EYE_OF_ENDER_PICKAXE,
                    ModItems.EYE_OF_ENDER_AXE, ModItems.EYE_OF_ENDER_SHOVEL, ModItems.EYE_OF_ENDER_HOE,
                    ModItems.EYE_OF_ENDER_HELMET, ModItems.EYE_OF_ENDER_CHESTPLATE,
                    ModItems.EYE_OF_ENDER_LEGGINGS, ModItems.EYE_OF_ENDER_BOOTS);

            buildVanillaSetAdv(saver, root, "shulker_craft", "shulker_set",
                    ModItems.SHULKER_SWORD,
                    ModItems.SHULKER_SWORD, ModItems.SHULKER_PICKAXE,
                    ModItems.SHULKER_AXE, ModItems.SHULKER_SHOVEL, ModItems.SHULKER_HOE,
                    ModItems.SHULKER_HELMET, ModItems.SHULKER_CHESTPLATE,
                    ModItems.SHULKER_LEGGINGS, ModItems.SHULKER_BOOTS);

            buildVanillaSetAdv(saver, root, "echo_shard_craft", "echo_shard_set",
                    ModItems.ECHO_SHARD_SWORD,
                    ModItems.ECHO_SHARD_SWORD, ModItems.ECHO_SHARD_PICKAXE,
                    ModItems.ECHO_SHARD_AXE, ModItems.ECHO_SHARD_SHOVEL, ModItems.ECHO_SHARD_HOE,
                    ModItems.ECHO_SHARD_HELMET, ModItems.ECHO_SHARD_CHESTPLATE,
                    ModItems.ECHO_SHARD_LEGGINGS, ModItems.ECHO_SHARD_BOOTS);

            buildVanillaSetAdv(saver, root, "dragon_breath_craft", "dragon_breath_set",
                    ModItems.DRAGON_BREATH_SWORD,
                    ModItems.DRAGON_BREATH_SWORD, ModItems.DRAGON_BREATH_PICKAXE,
                    ModItems.DRAGON_BREATH_AXE, ModItems.DRAGON_BREATH_SHOVEL, ModItems.DRAGON_BREATH_HOE,
                    ModItems.DRAGON_BREATH_HELMET, ModItems.DRAGON_BREATH_CHESTPLATE,
                    ModItems.DRAGON_BREATH_LEGGINGS, ModItems.DRAGON_BREATH_BOOTS);

            // ---------------------------------------------------------------
            // Leather tools (from root)
            // ---------------------------------------------------------------
            AdvancementHolder leatherCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.LEATHER_SWORD, title("leather_craft"), desc("leather_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_leather_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SWORD))
                    .addCriterion("has_leather_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_PICKAXE))
                    .addCriterion("has_leather_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_AXE))
                    .addCriterion("has_leather_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SHOVEL))
                    .addCriterion("has_leather_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_HOE))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("leather_craft"));

            Advancement.Builder.advancement()
                    .parent(leatherCraft)
                    .display(ModItems.LEATHER_PICKAXE, title("leather_set"), desc("leather_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SWORD))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_PICKAXE))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SHOVEL))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_AXE))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_HOE))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("leather_set"));

            // ---------------------------------------------------------------
            // Cake — novelty branch (from root)
            // ---------------------------------------------------------------

            AdvancementHolder cakeAdv = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.CAKE_SWORD, title("let_them_eat_cake"), desc("let_them_eat_cake"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_cake_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_SWORD))
                    .addCriterion("has_cake_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_PICKAXE))
                    .addCriterion("has_cake_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_AXE))
                    .addCriterion("has_cake_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_SHOVEL))
                    .addCriterion("has_cake_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_HOE))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("let_them_eat_cake"));

            Advancement.Builder.advancement()
                    .parent(cakeAdv)
                    .display(Items.CAKE, title("the_cake_is_a_lie"), desc("the_cake_is_a_lie"),
                            null, AdvancementType.TASK, true, true, true)
                    .addCriterion("eat_cake_sword",
                            usedItem(items, ModItems.CAKE_SWORD))
                    .addCriterion("eat_cake_pickaxe",
                            usedItem(items, ModItems.CAKE_PICKAXE))
                    .addCriterion("eat_cake_axe",
                            usedItem(items, ModItems.CAKE_AXE))
                    .addCriterion("eat_cake_shovel",
                            usedItem(items, ModItems.CAKE_SHOVEL))
                    .addCriterion("eat_cake_hoe",
                            usedItem(items, ModItems.CAKE_HOE))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("the_cake_is_a_lie"));

            // ---------------------------------------------------------------
            // Food sets — craft + eat advancements (from cake branch)
            // ---------------------------------------------------------------

            buildFoodAdv(saver, cakeAdv, "bread_craft", "bread_eat", "bread_full_set",
                    ModItems.BREAD_SWORD, Items.BREAD,
                    ModItems.BREAD_SWORD, ModItems.BREAD_PICKAXE,
                    ModItems.BREAD_AXE, ModItems.BREAD_SHOVEL, ModItems.BREAD_HOE,
                    ModItems.BREAD_HELMET, ModItems.BREAD_CHESTPLATE,
                    ModItems.BREAD_LEGGINGS, ModItems.BREAD_BOOTS);

            buildFoodAdv(saver, cakeAdv, "dried_kelp_craft", "dried_kelp_eat", "dried_kelp_full_set",
                    ModItems.DRIED_KELP_SWORD, Items.DRIED_KELP,
                    ModItems.DRIED_KELP_SWORD, ModItems.DRIED_KELP_PICKAXE,
                    ModItems.DRIED_KELP_AXE, ModItems.DRIED_KELP_SHOVEL, ModItems.DRIED_KELP_HOE,
                    ModItems.DRIED_KELP_HELMET, ModItems.DRIED_KELP_CHESTPLATE,
                    ModItems.DRIED_KELP_LEGGINGS, ModItems.DRIED_KELP_BOOTS);

            buildFoodAdv(saver, cakeAdv, "rotten_flesh_craft", "rotten_flesh_eat", "rotten_flesh_full_set",
                    ModItems.ROTTEN_FLESH_SWORD, Items.ROTTEN_FLESH,
                    ModItems.ROTTEN_FLESH_SWORD, ModItems.ROTTEN_FLESH_PICKAXE,
                    ModItems.ROTTEN_FLESH_AXE, ModItems.ROTTEN_FLESH_SHOVEL, ModItems.ROTTEN_FLESH_HOE,
                    ModItems.ROTTEN_FLESH_HELMET, ModItems.ROTTEN_FLESH_CHESTPLATE,
                    ModItems.ROTTEN_FLESH_LEGGINGS, ModItems.ROTTEN_FLESH_BOOTS);

            buildFoodAdv(saver, cakeAdv, "melon_craft", "melon_eat", "melon_full_set",
                    ModItems.MELON_SWORD, Items.MELON_SLICE,
                    ModItems.MELON_SWORD, ModItems.MELON_PICKAXE,
                    ModItems.MELON_AXE, ModItems.MELON_SHOVEL, ModItems.MELON_HOE,
                    ModItems.MELON_HELMET, ModItems.MELON_CHESTPLATE,
                    ModItems.MELON_LEGGINGS, ModItems.MELON_BOOTS);

            buildFoodAdv(saver, cakeAdv, "sweet_berry_craft", "sweet_berry_eat", "sweet_berry_full_set",
                    ModItems.SWEET_BERRY_SWORD, Items.SWEET_BERRIES,
                    ModItems.SWEET_BERRY_SWORD, ModItems.SWEET_BERRY_PICKAXE,
                    ModItems.SWEET_BERRY_AXE, ModItems.SWEET_BERRY_SHOVEL, ModItems.SWEET_BERRY_HOE,
                    ModItems.SWEET_BERRY_HELMET, ModItems.SWEET_BERRY_CHESTPLATE,
                    ModItems.SWEET_BERRY_LEGGINGS, ModItems.SWEET_BERRY_BOOTS);

            buildFoodAdv(saver, cakeAdv, "pumpkin_pie_craft", "pumpkin_pie_eat", "pumpkin_pie_full_set",
                    ModItems.PUMPKIN_PIE_SWORD, Items.PUMPKIN_PIE,
                    ModItems.PUMPKIN_PIE_SWORD, ModItems.PUMPKIN_PIE_PICKAXE,
                    ModItems.PUMPKIN_PIE_AXE, ModItems.PUMPKIN_PIE_SHOVEL, ModItems.PUMPKIN_PIE_HOE,
                    ModItems.PUMPKIN_PIE_HELMET, ModItems.PUMPKIN_PIE_CHESTPLATE,
                    ModItems.PUMPKIN_PIE_LEGGINGS, ModItems.PUMPKIN_PIE_BOOTS);

            buildFoodAdv(saver, cakeAdv, "mushroom_craft", "mushroom_eat", "mushroom_full_set",
                    ModItems.MUSHROOM_SWORD, Items.RED_MUSHROOM,
                    ModItems.MUSHROOM_SWORD, ModItems.MUSHROOM_PICKAXE,
                    ModItems.MUSHROOM_AXE, ModItems.MUSHROOM_SHOVEL, ModItems.MUSHROOM_HOE,
                    ModItems.MUSHROOM_HELMET, ModItems.MUSHROOM_CHESTPLATE,
                    ModItems.MUSHROOM_LEGGINGS, ModItems.MUSHROOM_BOOTS);

            buildFoodAdv(saver, cakeAdv, "pufferfish_craft", "pufferfish_eat", "pufferfish_full_set",
                    ModItems.PUFFERFISH_SWORD, Items.PUFFERFISH,
                    ModItems.PUFFERFISH_SWORD, ModItems.PUFFERFISH_PICKAXE,
                    ModItems.PUFFERFISH_AXE, ModItems.PUFFERFISH_SHOVEL, ModItems.PUFFERFISH_HOE,
                    ModItems.PUFFERFISH_HELMET, ModItems.PUFFERFISH_CHESTPLATE,
                    ModItems.PUFFERFISH_LEGGINGS, ModItems.PUFFERFISH_BOOTS);

            buildFoodAdv(saver, cakeAdv, "honey_craft", "honey_eat", "honey_full_set",
                    ModItems.HONEY_SWORD, Items.HONEY_BOTTLE,
                    ModItems.HONEY_SWORD, ModItems.HONEY_PICKAXE,
                    ModItems.HONEY_AXE, ModItems.HONEY_SHOVEL, ModItems.HONEY_HOE,
                    ModItems.HONEY_HELMET, ModItems.HONEY_CHESTPLATE,
                    ModItems.HONEY_LEGGINGS, ModItems.HONEY_BOOTS);

            buildFoodAdv(saver, cakeAdv, "chorus_fruit_craft", "chorus_fruit_eat", "chorus_fruit_full_set",
                    ModItems.CHORUS_FRUIT_SWORD, Items.CHORUS_FRUIT,
                    ModItems.CHORUS_FRUIT_SWORD, ModItems.CHORUS_FRUIT_PICKAXE,
                    ModItems.CHORUS_FRUIT_AXE, ModItems.CHORUS_FRUIT_SHOVEL, ModItems.CHORUS_FRUIT_HOE,
                    ModItems.CHORUS_FRUIT_HELMET, ModItems.CHORUS_FRUIT_CHESTPLATE,
                    ModItems.CHORUS_FRUIT_LEGGINGS, ModItems.CHORUS_FRUIT_BOOTS);

            buildFoodAdv(saver, cakeAdv, "golden_apple_craft", "golden_apple_eat", "golden_apple_full_set",
                    ModItems.GOLDEN_APPLE_SWORD, Items.GOLDEN_APPLE,
                    ModItems.GOLDEN_APPLE_SWORD, ModItems.GOLDEN_APPLE_PICKAXE,
                    ModItems.GOLDEN_APPLE_AXE, ModItems.GOLDEN_APPLE_SHOVEL, ModItems.GOLDEN_APPLE_HOE,
                    ModItems.GOLDEN_APPLE_HELMET, ModItems.GOLDEN_APPLE_CHESTPLATE,
                    ModItems.GOLDEN_APPLE_LEGGINGS, ModItems.GOLDEN_APPLE_BOOTS);
        }

        private static void buildFoodAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                          String craftKey, String eatKey, String fullSetKey,
                                          net.minecraft.world.item.Item displayItem, net.minecraft.world.level.ItemLike eatIcon,
                                          net.minecraft.world.item.Item sword, net.minecraft.world.item.Item pickaxe,
                                          net.minecraft.world.item.Item axe, net.minecraft.world.item.Item shovel,
                                          net.minecraft.world.item.Item hoe,
                                          net.minecraft.world.item.Item helmet, net.minecraft.world.item.Item chestplate,
                                          net.minecraft.world.item.Item leggings, net.minecraft.world.item.Item boots) {
            net.minecraft.world.item.Item[] tools = {sword, pickaxe, axe, shovel, hoe};

            // Craft advancement — OR of any tool
            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                craft.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            // Eat advancement — OR of consuming any of the 5 tools
            Advancement.Builder eat = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(eatIcon, title(eatKey), desc(eatKey),
                            null, AdvancementType.TASK, true, true, true);
            for (int i = 0; i < tools.length; i++) {
                eat.addCriterion("eat_tool_" + i,
                        usedItem(ITEMS_LOOKUP, tools[i]));
            }
            AdvancementHolder eatAdv = eat.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(eatKey));

            // Full set advancement — AND of all 9 items
            Advancement.Builder.advancement()
                    .parent(eatAdv)
                    .display(chestplate, title(fullSetKey), desc(fullSetKey),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword", InventoryChangeTrigger.TriggerInstance.hasItems(sword))
                    .addCriterion("has_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(pickaxe))
                    .addCriterion("has_axe", InventoryChangeTrigger.TriggerInstance.hasItems(axe))
                    .addCriterion("has_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(shovel))
                    .addCriterion("has_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(hoe))
                    .addCriterion("has_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(helmet))
                    .addCriterion("has_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(chestplate))
                    .addCriterion("has_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(leggings))
                    .addCriterion("has_boots", InventoryChangeTrigger.TriggerInstance.hasItems(boots))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(fullSetKey));
        }

        // -----------------------------------------------------------------------
        // Vanilla material set helpers
        // -----------------------------------------------------------------------

        /** Tools-only set: craft (OR any tool) + full set (AND all 5 tools). */
        private static void buildVanillaToolAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                                 String craftKey, String setKey,
                                                 net.minecraft.world.item.Item displayItem,
                                                 net.minecraft.world.item.Item... tools) {
            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                craft.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            Advancement.Builder set = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(tools[1], title(setKey), desc(setKey),
                            null, AdvancementType.GOAL, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                set.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            set.requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(setKey));
        }

        /** Tools+Armor set: craft (OR any of 9 items) + full set (AND all 9 items). */
        private static void buildVanillaSetAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                                String craftKey, String setKey,
                                                net.minecraft.world.item.Item displayItem,
                                                net.minecraft.world.item.Item sword, net.minecraft.world.item.Item pickaxe,
                                                net.minecraft.world.item.Item axe, net.minecraft.world.item.Item shovel,
                                                net.minecraft.world.item.Item hoe,
                                                net.minecraft.world.item.Item helmet, net.minecraft.world.item.Item chestplate,
                                                net.minecraft.world.item.Item leggings, net.minecraft.world.item.Item boots) {
            net.minecraft.world.item.Item[] all = {sword, pickaxe, axe, shovel, hoe, helmet, chestplate, leggings, boots};

            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < all.length; i++) {
                craft.addCriterion("has_item_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(all[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            Advancement.Builder set = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(chestplate, title(setKey), desc(setKey),
                            null, AdvancementType.GOAL, true, true, false);
            for (int i = 0; i < all.length; i++) {
                set.addCriterion("has_item_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(all[i]));
            }
            set.requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(setKey));
        }

        // -----------------------------------------------------------------------
        // Helpers
        // -----------------------------------------------------------------------

        private static String id(String path) {
            return UsefultoolsMod.MOD_ID + ":" + path;
        }

        private static Component title(String key) {
            return Component.translatable("advancements." + UsefultoolsMod.MOD_ID + "." + key + ".title");
        }

        private static Component desc(String key) {
            return Component.translatable("advancements." + UsefultoolsMod.MOD_ID + "." + key + ".description");
        }
}
