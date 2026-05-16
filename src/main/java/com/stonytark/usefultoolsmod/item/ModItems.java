package com.stonytark.usefultoolsmod.item;


import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.item.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

/**
 * Items registry — migrated from 1.21.1 Fabric to Fabric 26.1.2.
 *
 * <p>Key shape changes vs 1.21.1:
 * <ul>
 *   <li>Sword/Pickaxe/Shovel/Axe/Hoe/ArmorItem classes were removed in 1.21.5. We use plain
 *       {@link Item} with {@code Item.Properties#sword/.pickaxe/.shovel/.axe/.hoe/.humanoidArmor}
 *       to apply tool/armor shape.</li>
 *   <li>{@code Item.Properties} requires an id (1.21.2+); the {@link #register(String, Function)}
 *       helper sets it via {@link Identifier#fromNamespaceAndPath} → {@link ResourceKey}.</li>
 *   <li>Custom subclasses (CoalSwordItem, EctoSwordItem, EdibleSwordItem, ModArmorItem, etc.)
 *       now extend plain {@code Item} with a single-arg {@code (Item.Properties)} constructor.</li>
 *   <li>{@code ArmorItem.Type} -> {@link ArmorType}.</li>
 * </ul>
 */
public class ModItems {

    private static FoodProperties food(int nutrition) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(0.1f).build();
    }

    // ── Material items ─────────────────────────────────────────────────────

    public static final Item RGOLD = register("rgold",
            p -> new Item(p.stacksTo(64)));
    public static final Item RAW_RGOLD = register("raw_rgold",
            p -> new Item(p.stacksTo(64)));
    public static final Item OBSHARD = register("obshard",
            p -> new Item(p.stacksTo(64)));
    public static final Item SEM = register("sem",
            p -> new Item(p.stacksTo(64)));
    public static final Item OBINGOT = register("obingot",
            p -> new Item(p.stacksTo(64)));
    public static final Item GRENADE = register("grenade",
            p -> new Grenade(p.stacksTo(16)));
    public static final Item HRED = register("hred",
            p -> new Item(p.stacksTo(64)));
    public static final Item HGLOW = register("hglow",
            p -> new Item(p.stacksTo(64)));
    public static final Item RLAPIS = register("rlapis",
            p -> new Item(p.stacksTo(64)));
    public static final Item DYNAMITE = register("dynamite",
            p -> new Dynamite(p.stacksTo(16).fireResistant()));

    // ── Emerald-line tools ─────────────────────────────────────────────────
    public static final Item REMERALD_SWORD = register("remerald_sword",
            p -> new Item(p.sword(ModToolTiers.REMERALD, 3, -2.4f)));
    public static final Item REMERALD_PICKAXE = register("remerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.REMERALD, 1, -2.8f)));
    public static final Item REMERALD_SHOVEL = register("remerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.REMERALD, 1.5f, -3f)));
    public static final Item REMERALD_AXE = register("remerald_axe",
            p -> new Item(p.axe(ModToolTiers.REMERALD, 6, -3.2f)));
    public static final Item REMERALD_HOE = register("remerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.REMERALD, 0, -3f)));

    public static final Item PEMERALD_SWORD = register("pemerald_sword",
            p -> new Item(p.sword(ModToolTiers.PEMERALD, 3, -2.4f)));
    public static final Item PEMERALD_PICKAXE = register("pemerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PEMERALD, 1, -2.8f)));
    public static final Item PEMERALD_SHOVEL = register("pemerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.PEMERALD, 1.5f, -3f)));
    public static final Item PEMERALD_AXE = register("pemerald_axe",
            p -> new Item(p.axe(ModToolTiers.PEMERALD, 6, -3.2f)));
    public static final Item PEMERALD_HOE = register("pemerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.PEMERALD, 0, -3f)));

    public static final Item ROBSIDIAN_SWORD = register("robsidian_sword",
            p -> new Item(p.sword(ModToolTiers.ROBSIDIAN, 3, -2.4f)));
    public static final Item ROBSIDIAN_PICKAXE = register("robsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ROBSIDIAN, 1, -2.8f)));
    public static final Item ROBSIDIAN_SHOVEL = register("robsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.ROBSIDIAN, 1.5f, -3f)));
    public static final Item ROBSIDIAN_AXE = register("robsidian_axe",
            p -> new Item(p.axe(ModToolTiers.ROBSIDIAN, 6, -3.2f)));
    public static final Item ROBSIDIAN_HOE = register("robsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.ROBSIDIAN, 0, -3f)));

    public static final Item POBSIDIAN_SWORD = register("pobsidian_sword",
            p -> new Item(p.sword(ModToolTiers.POBSIDIAN, 3, -2.4f)));
    public static final Item POBSIDIAN_PICKAXE = register("pobsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POBSIDIAN, 1, -2.8f)));
    public static final Item POBSIDIAN_SHOVEL = register("pobsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.POBSIDIAN, 1.5f, -3f)));
    public static final Item POBSIDIAN_AXE = register("pobsidian_axe",
            p -> new Item(p.axe(ModToolTiers.POBSIDIAN, 6, -3.2f)));
    public static final Item POBSIDIAN_HOE = register("pobsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.POBSIDIAN, 0, -3f)));

    public static final Item OVERPOWER_SWORD = register("overpower_sword",
            p -> new Item(p.sword(ModToolTiers.OVERPOWER, 3, -2.4f)));
    public static final Item OVERPOWER_PICKAXE = register("overpower_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.OVERPOWER, 1, -2.8f)));
    public static final Item OVERPOWER_SHOVEL = register("overpower_shovel",
            p -> new Item(p.shovel(ModToolTiers.OVERPOWER, 1.5f, -3f)));
    public static final Item OVERPOWER_AXE = register("overpower_axe",
            p -> new Item(p.axe(ModToolTiers.OVERPOWER, 6, -3.2f)));

    public static final Item HREDSTONE_SWORD = register("hredstone_sword",
            p -> new Item(p.sword(ModToolTiers.HREDSTONE, 3, -2.4f)));
    public static final Item HREDSTONE_PICKAXE = register("hredstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HREDSTONE, 1, -2.8f)));
    public static final Item HREDSTONE_SHOVEL = register("hredstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HREDSTONE, 1.5f, -3f)));
    public static final Item HREDSTONE_AXE = register("hredstone_axe",
            p -> new Item(p.axe(ModToolTiers.HREDSTONE, 6, -3.2f)));
    public static final Item HREDSTONE_HOE = register("hredstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HREDSTONE, 0, -3f)));

    public static final Item HGLOWSTONE_SWORD = register("hglowstone_sword",
            p -> new Item(p.sword(ModToolTiers.HGLOWSTONE, 3, -2.4f)));
    public static final Item HGLOWSTONE_PICKAXE = register("hglowstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HGLOWSTONE, 1, -2.8f)));
    public static final Item HGLOWSTONE_SHOVEL = register("hglowstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HGLOWSTONE, 1.5f, -3f)));
    public static final Item HGLOWSTONE_AXE = register("hglowstone_axe",
            p -> new Item(p.axe(ModToolTiers.HGLOWSTONE, 6, -3.2f)));
    public static final Item HGLOWSTONE_HOE = register("hglowstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HGLOWSTONE, 0, -3f)));

    public static final Item RGOLD_SWORD = register("rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RGOLD, 3, -2.4f)));
    public static final Item RGOLD_PICKAXE = register("rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RGOLD, 1, -2.8f)));
    public static final Item RGOLD_SHOVEL = register("rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RGOLD, 1.5f, -3f)));
    public static final Item RGOLD_AXE = register("rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RGOLD, 6, -3.2f)));
    public static final Item RGOLD_HOE = register("rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RGOLD, 0, -3f)));

    public static final Item RLAPIS_SWORD = register("rlapis_sword",
            p -> new Item(p.sword(ModToolTiers.RLAPIS, 3, -2.4f)));
    public static final Item RLAPIS_PICKAXE = register("rlapis_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RLAPIS, 1, -2.8f)));
    public static final Item RLAPIS_SHOVEL = register("rlapis_shovel",
            p -> new Item(p.shovel(ModToolTiers.RLAPIS, 1.5f, -3f)));
    public static final Item RLAPIS_AXE = register("rlapis_axe",
            p -> new Item(p.axe(ModToolTiers.RLAPIS, 6, -3.2f)));
    public static final Item RLAPIS_HOE = register("rlapis_hoe",
            p -> new Item(p.hoe(ModToolTiers.RLAPIS, 0, -3f)));

    // ── Armor pieces ───────────────────────────────────────────────────────
    public static final Item EMERALD_HELMET = register("emerald_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item EMERALD_CHESTPLATE = register("emerald_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item EMERALD_LEGGINGS = register("emerald_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item EMERALD_BOOTS = register("emerald_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item HRED_HELMET = register("hred_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item HRED_CHESTPLATE = register("hred_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item HRED_LEGGINGS = register("hred_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item HRED_BOOTS = register("hred_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item HGLOW_HELMET = register("hglow_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item HGLOW_CHESTPLATE = register("hglow_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item HGLOW_LEGGINGS = register("hglow_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item HGLOW_BOOTS = register("hglow_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item OBSIDIAN_HELMET = register("obsidian_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item OBSIDIAN_CHESTPLATE = register("obsidian_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item OBSIDIAN_LEGGINGS = register("obsidian_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item OBSIDIAN_BOOTS = register("obsidian_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item RGOLD_HELMET = register("rgold_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item RGOLD_CHESTPLATE = register("rgold_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item RGOLD_LEGGINGS = register("rgold_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item RGOLD_BOOTS = register("rgold_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item RLAPIS_HELMET = register("rlapis_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item RLAPIS_CHESTPLATE = register("rlapis_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item RLAPIS_LEGGINGS = register("rlapis_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item RLAPIS_BOOTS = register("rlapis_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // OVERPOWER armor — helmet uses ModArmorItem to drive set-effects.
    public static final Item OVERPOWER_HELMET = register("overpower_helmet",
            p -> new ModArmorItem(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item OVERPOWER_CHESTPLATE = register("overpower_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item OVERPOWER_LEGGINGS = register("overpower_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item OVERPOWER_BOOTS = register("overpower_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // 1.21.5+: SpawnEggItem(Properties) only; the entity type is carried by the
    // ENTITY_DATA component set via Properties#spawnEgg(EntityType).
    public static final Item GHOST_SPAWN_EGG = register("ghost_spawn_egg",
            p -> new SpawnEggItem(p.spawnEgg(ModEntities.GHOST)));

    public static final Item ECTOPLASM = register("ectoplasm",
            p -> new Item(p.stacksTo(64)));

    // ── Rough Ectoplasm tools (RECTO, stone-tier) ──────────────────────────
    public static final Item RECTO_SWORD = register("recto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.RECTO, 3, -2.4f)));
    public static final Item RECTO_PICKAXE = register("recto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.RECTO, 1, -2.8f)));
    public static final Item RECTO_SHOVEL = register("recto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.RECTO, 1.5f, -3f)));
    public static final Item RECTO_AXE = register("recto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.RECTO, 6, -3.2f)));
    public static final Item RECTO_HOE = register("recto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.RECTO, 0, -3f)));

    // ── Refined Ectoplasm + Ectoplasm tools/armor ──────────────────────────
    public static final Item REFINED_ECTOPLASM = register("refined_ectoplasm",
            p -> new Item(p.stacksTo(64)));

    public static final Item ECTO_SWORD = register("ecto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.ECTOPLASM, 3, -2.4f)));
    public static final Item ECTO_PICKAXE = register("ecto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.ECTOPLASM, 1, -2.8f)));
    public static final Item ECTO_SHOVEL = register("ecto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.ECTOPLASM, 1.5f, -3f)));
    public static final Item ECTO_AXE = register("ecto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.ECTOPLASM, 6, -3.1f)));
    public static final Item ECTO_HOE = register("ecto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.ECTOPLASM, 0, -3f)));

    public static final Item ECTO_HELMET = register("ecto_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item ECTO_CHESTPLATE = register("ecto_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item ECTO_LEGGINGS = register("ecto_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item ECTO_BOOTS = register("ecto_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Coal material items + tools + armor ────────────────────────────────
    public static final Item COAL_DUST = register("coal_dust",
            p -> new Item(p.stacksTo(64)));
    public static final Item HARDENED_COAL = register("hardened_coal",
            p -> new Item(p.stacksTo(64)));

    public static final Item COAL_SWORD = register("coal_sword",
            p -> new CoalSwordItem(p.sword(ModToolTiers.COAL_TOOL, 2, -2.4f)));
    public static final Item COAL_PICKAXE = register("coal_pickaxe",
            p -> new CoalPickaxeItem(p.pickaxe(ModToolTiers.COAL_TOOL, 1, -2.8f)));
    public static final Item COAL_SHOVEL = register("coal_shovel",
            p -> new CoalShovelItem(p.shovel(ModToolTiers.COAL_TOOL, 1.5f, -3f)));
    public static final Item COAL_AXE = register("coal_axe",
            p -> new CoalAxeItem(p.axe(ModToolTiers.COAL_TOOL, 5, -3.2f)));
    public static final Item COAL_HOE = register("coal_hoe",
            p -> new CoalHoeItem(p.hoe(ModToolTiers.COAL_TOOL, 0, -3f)));

    public static final Item COAL_HELMET = register("coal_helmet",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item COAL_CHESTPLATE = register("coal_chestplate",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item COAL_LEGGINGS = register("coal_leggings",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item COAL_BOOTS = register("coal_boots",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Raw metal rough tool sets ──────────────────────────────────────────
    public static final Item RRAW_GOLD_SWORD = register("rraw_gold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_GOLD, 3, -2.4f)));
    public static final Item RRAW_GOLD_PICKAXE = register("rraw_gold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_GOLD, 1, -2.8f)));
    public static final Item RRAW_GOLD_SHOVEL = register("rraw_gold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_GOLD, 1.5f, -3f)));
    public static final Item RRAW_GOLD_AXE = register("rraw_gold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_GOLD, 6, -3.2f)));
    public static final Item RRAW_GOLD_HOE = register("rraw_gold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_GOLD, 0, -3f)));

    public static final Item RRAW_COPPER_SWORD = register("rraw_copper_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_COPPER, 3, -2.4f)));
    public static final Item RRAW_COPPER_PICKAXE = register("rraw_copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_COPPER, 1, -2.8f)));
    public static final Item RRAW_COPPER_SHOVEL = register("rraw_copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_COPPER, 1.5f, -3f)));
    public static final Item RRAW_COPPER_AXE = register("rraw_copper_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_COPPER, 6, -3.2f)));
    public static final Item RRAW_COPPER_HOE = register("rraw_copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_COPPER, 0, -3f)));

    public static final Item RRAW_IRON_SWORD = register("rraw_iron_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_IRON, 3, -2.4f)));
    public static final Item RRAW_IRON_PICKAXE = register("rraw_iron_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_IRON, 1, -2.8f)));
    public static final Item RRAW_IRON_SHOVEL = register("rraw_iron_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_IRON, 1.5f, -3f)));
    public static final Item RRAW_IRON_AXE = register("rraw_iron_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_IRON, 6, -3.2f)));
    public static final Item RRAW_IRON_HOE = register("rraw_iron_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_IRON, 0, -3f)));

    public static final Item RRAW_RGOLD_SWORD = register("rraw_rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_RGOLD, 3, -2.4f)));
    public static final Item RRAW_RGOLD_PICKAXE = register("rraw_rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_RGOLD, 1, -2.8f)));
    public static final Item RRAW_RGOLD_SHOVEL = register("rraw_rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_RGOLD, 1.5f, -3f)));
    public static final Item RRAW_RGOLD_AXE = register("rraw_rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_RGOLD, 6, -3.2f)));
    public static final Item RRAW_RGOLD_HOE = register("rraw_rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_RGOLD, 0, -3f)));

    public static final Item RSCRAP_SWORD = register("rscrap_sword",
            p -> new Item(p.sword(ModToolTiers.RSCRAP, 3, -2.4f)));
    public static final Item RSCRAP_PICKAXE = register("rscrap_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RSCRAP, 1, -2.8f)));
    public static final Item RSCRAP_SHOVEL = register("rscrap_shovel",
            p -> new Item(p.shovel(ModToolTiers.RSCRAP, 1.5f, -3f)));
    public static final Item RSCRAP_AXE = register("rscrap_axe",
            p -> new Item(p.axe(ModToolTiers.RSCRAP, 6, -3.2f)));
    public static final Item RSCRAP_HOE = register("rscrap_hoe",
            p -> new Item(p.hoe(ModToolTiers.RSCRAP, 0, -3f)));

    // ── Crystal / element materials ────────────────────────────────────────
    public static final Item CALCIFIED_AMETHYST = register("calcified_amethyst",
            p -> new Item(p.stacksTo(64)));
    public static final Item GLACIAL_SHARD = register("glacial_shard",
            p -> new Item(p.stacksTo(64)));
    public static final Item POLISHED_QUARTZ = register("polished_quartz",
            p -> new Item(p.stacksTo(64)));
    public static final Item POLISHED_PRISMARINE = register("polished_prismarine",
            p -> new Item(p.stacksTo(64)));

    public static final Item RAMETHYST_SWORD = register("ramethyst_sword",
            p -> new Item(p.sword(ModToolTiers.RAMETHYST, 3, -2.4f)));
    public static final Item RAMETHYST_PICKAXE = register("ramethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RAMETHYST, 1, -2.8f)));
    public static final Item RAMETHYST_SHOVEL = register("ramethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.RAMETHYST, 1.5f, -3f)));
    public static final Item RAMETHYST_AXE = register("ramethyst_axe",
            p -> new Item(p.axe(ModToolTiers.RAMETHYST, 6, -3.2f)));
    public static final Item RAMETHYST_HOE = register("ramethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.RAMETHYST, 0, -3f)));

    public static final Item SNOW_SWORD = register("snow_sword",
            p -> new Item(p.sword(ModToolTiers.SNOW_TOOL, 3, -2.4f)));
    public static final Item SNOW_PICKAXE = register("snow_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SNOW_TOOL, 1, -2.8f)));
    public static final Item SNOW_SHOVEL = register("snow_shovel",
            p -> new Item(p.shovel(ModToolTiers.SNOW_TOOL, 1.5f, -3f)));
    public static final Item SNOW_AXE = register("snow_axe",
            p -> new Item(p.axe(ModToolTiers.SNOW_TOOL, 6, -3.2f)));
    public static final Item SNOW_HOE = register("snow_hoe",
            p -> new Item(p.hoe(ModToolTiers.SNOW_TOOL, 0, -3f)));

    public static final Item RQUARTZ_SWORD = register("rquartz_sword",
            p -> new Item(p.sword(ModToolTiers.RQUARTZ, 3, -2.4f)));
    public static final Item RQUARTZ_PICKAXE = register("rquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RQUARTZ, 1, -2.8f)));
    public static final Item RQUARTZ_SHOVEL = register("rquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.RQUARTZ, 1.5f, -3f)));
    public static final Item RQUARTZ_AXE = register("rquartz_axe",
            p -> new Item(p.axe(ModToolTiers.RQUARTZ, 6, -3.2f)));
    public static final Item RQUARTZ_HOE = register("rquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.RQUARTZ, 0, -3f)));

    public static final Item RPRISM_SWORD = register("rprism_sword",
            p -> new Item(p.sword(ModToolTiers.RPRISM, 3, -2.4f)));
    public static final Item RPRISM_PICKAXE = register("rprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RPRISM, 1, -2.8f)));
    public static final Item RPRISM_SHOVEL = register("rprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.RPRISM, 1.5f, -3f)));
    public static final Item RPRISM_AXE = register("rprism_axe",
            p -> new Item(p.axe(ModToolTiers.RPRISM, 6, -3.2f)));
    public static final Item RPRISM_HOE = register("rprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.RPRISM, 0, -3f)));

    public static final Item CAMETHYST_SWORD = register("camethyst_sword",
            p -> new Item(p.sword(ModToolTiers.CAMETHYST, 3, -2.4f)));
    public static final Item CAMETHYST_PICKAXE = register("camethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CAMETHYST, 1, -2.8f)));
    public static final Item CAMETHYST_SHOVEL = register("camethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.CAMETHYST, 1.5f, -3f)));
    public static final Item CAMETHYST_AXE = register("camethyst_axe",
            p -> new Item(p.axe(ModToolTiers.CAMETHYST, 6, -3.2f)));
    public static final Item CAMETHYST_HOE = register("camethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.CAMETHYST, 0, -3f)));
    public static final Item CAMETHYST_HELMET = register("camethyst_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item CAMETHYST_CHESTPLATE = register("camethyst_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item CAMETHYST_LEGGINGS = register("camethyst_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item CAMETHYST_BOOTS = register("camethyst_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item ICE_SWORD = register("ice_sword",
            p -> new Item(p.sword(ModToolTiers.ICE_TOOL, 3, -2.4f)));
    public static final Item ICE_PICKAXE = register("ice_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ICE_TOOL, 1, -2.8f)));
    public static final Item ICE_SHOVEL = register("ice_shovel",
            p -> new Item(p.shovel(ModToolTiers.ICE_TOOL, 1.5f, -3f)));
    public static final Item ICE_AXE = register("ice_axe",
            p -> new Item(p.axe(ModToolTiers.ICE_TOOL, 6, -3.2f)));
    public static final Item ICE_HOE = register("ice_hoe",
            p -> new Item(p.hoe(ModToolTiers.ICE_TOOL, 0, -3f)));
    public static final Item ICE_HELMET = register("ice_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item ICE_CHESTPLATE = register("ice_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item ICE_LEGGINGS = register("ice_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item ICE_BOOTS = register("ice_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item PQUARTZ_SWORD = register("pquartz_sword",
            p -> new Item(p.sword(ModToolTiers.PQUARTZ, 3, -2.4f)));
    public static final Item PQUARTZ_PICKAXE = register("pquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PQUARTZ, 1, -2.8f)));
    public static final Item PQUARTZ_SHOVEL = register("pquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.PQUARTZ, 1.5f, -3f)));
    public static final Item PQUARTZ_AXE = register("pquartz_axe",
            p -> new Item(p.axe(ModToolTiers.PQUARTZ, 6, -3.2f)));
    public static final Item PQUARTZ_HOE = register("pquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.PQUARTZ, 0, -3f)));
    public static final Item PQUARTZ_HELMET = register("pquartz_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item PQUARTZ_CHESTPLATE = register("pquartz_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item PQUARTZ_LEGGINGS = register("pquartz_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item PQUARTZ_BOOTS = register("pquartz_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item PPRISM_SWORD = register("pprism_sword",
            p -> new Item(p.sword(ModToolTiers.PPRISM, 3, -2.4f)));
    public static final Item PPRISM_PICKAXE = register("pprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PPRISM, 1, -2.8f)));
    public static final Item PPRISM_SHOVEL = register("pprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.PPRISM, 1.5f, -3f)));
    public static final Item PPRISM_AXE = register("pprism_axe",
            p -> new Item(p.axe(ModToolTiers.PPRISM, 6, -3.2f)));
    public static final Item PPRISM_HOE = register("pprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.PPRISM, 0, -3f)));
    public static final Item PPRISM_HELMET = register("pprism_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item PPRISM_CHESTPLATE = register("pprism_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item PPRISM_LEGGINGS = register("pprism_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item PPRISM_BOOTS = register("pprism_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Flint + FNI ────────────────────────────────────────────────────────
    public static final Item RFLINT_SWORD = register("rflint_sword",
            p -> new Item(p.sword(ModToolTiers.RFLINT, 3, -2.4f)));
    public static final Item RFLINT_PICKAXE = register("rflint_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RFLINT, 1, -2.8f)));
    public static final Item RFLINT_SHOVEL = register("rflint_shovel",
            p -> new Item(p.shovel(ModToolTiers.RFLINT, 1.5f, -3f)));
    public static final Item RFLINT_AXE = register("rflint_axe",
            p -> new Item(p.axe(ModToolTiers.RFLINT, 6, -3.2f)));
    public static final Item RFLINT_HOE = register("rflint_hoe",
            p -> new Item(p.hoe(ModToolTiers.RFLINT, 0, -3f)));

    public static final Item FNI_SWORD = register("fni_sword",
            p -> new Item(p.sword(ModToolTiers.FNI_TOOLS, 3, -2.4f)));
    public static final Item FNI_PICKAXE = register("fni_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FNI_TOOLS, 1, -2.8f)));
    public static final Item FNI_SHOVEL = register("fni_shovel",
            p -> new Item(p.shovel(ModToolTiers.FNI_TOOLS, 1.5f, -3f)));
    public static final Item FNI_AXE = register("fni_axe",
            p -> new Item(p.axe(ModToolTiers.FNI_TOOLS, 6, -3.2f)));
    public static final Item FNI_HOE = register("fni_hoe",
            p -> new Item(p.hoe(ModToolTiers.FNI_TOOLS, 0, -3f)));
    public static final Item FNI_HELMET = register("fni_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item FNI_CHESTPLATE = register("fni_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item FNI_LEGGINGS = register("fni_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item FNI_BOOTS = register("fni_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Stone-rock variants (13 sets × 5 tools) ────────────────────────────
    public static final Item ANDESITE_SWORD = register("andesite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_ANDESITE, 3, -2.4f)));
    public static final Item ANDESITE_PICKAXE = register("andesite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_ANDESITE, 1, -2.8f)));
    public static final Item ANDESITE_SHOVEL = register("andesite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_ANDESITE, 1.5f, -3f)));
    public static final Item ANDESITE_AXE = register("andesite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_ANDESITE, 6, -3.2f)));
    public static final Item ANDESITE_HOE = register("andesite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_ANDESITE, 0, -3f)));

    public static final Item BASALT_SWORD = register("basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BASALT, 3, -2.5f)));
    public static final Item BASALT_PICKAXE = register("basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BASALT, 1, -2.9f)));
    public static final Item BASALT_SHOVEL = register("basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BASALT, 2.0f, -3.1f)));
    public static final Item BASALT_AXE = register("basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BASALT, 7, -3.3f)));
    public static final Item BASALT_HOE = register("basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BASALT, 0, -3.1f)));

    public static final Item BLACKSTONE_SWORD = register("blackstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BLACKSTONE, 4, -2.5f)));
    public static final Item BLACKSTONE_PICKAXE = register("blackstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BLACKSTONE, 1, -2.9f)));
    public static final Item BLACKSTONE_SHOVEL = register("blackstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BLACKSTONE, 2.0f, -3.1f)));
    public static final Item BLACKSTONE_AXE = register("blackstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BLACKSTONE, 7, -3.35f)));
    public static final Item BLACKSTONE_HOE = register("blackstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BLACKSTONE, 0, -3.1f)));

    public static final Item CALCITE_SWORD = register("calcite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_CALCITE, 2, -2.2f)));
    public static final Item CALCITE_PICKAXE = register("calcite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_CALCITE, 1, -2.6f)));
    public static final Item CALCITE_SHOVEL = register("calcite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_CALCITE, 1.0f, -2.8f)));
    public static final Item CALCITE_AXE = register("calcite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_CALCITE, 5, -3.0f)));
    public static final Item CALCITE_HOE = register("calcite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_CALCITE, 0, -2.6f)));

    public static final Item DEEPSLATE_SWORD = register("deepslate_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DEEPSLATE, 4, -2.55f)));
    public static final Item DEEPSLATE_PICKAXE = register("deepslate_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DEEPSLATE, 1, -2.95f)));
    public static final Item DEEPSLATE_SHOVEL = register("deepslate_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DEEPSLATE, 2.0f, -3.15f)));
    public static final Item DEEPSLATE_AXE = register("deepslate_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DEEPSLATE, 7, -3.4f)));
    public static final Item DEEPSLATE_HOE = register("deepslate_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DEEPSLATE, 0, -3.1f)));

    public static final Item DIORITE_SWORD = register("diorite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DIORITE, 3, -2.4f)));
    public static final Item DIORITE_PICKAXE = register("diorite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DIORITE, 1, -2.8f)));
    public static final Item DIORITE_SHOVEL = register("diorite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DIORITE, 1.5f, -3f)));
    public static final Item DIORITE_AXE = register("diorite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DIORITE, 6, -3.2f)));
    public static final Item DIORITE_HOE = register("diorite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DIORITE, 0, -2.9f)));

    public static final Item END_STONE_SWORD = register("end_stone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_END_STONE, 3, -2.35f)));
    public static final Item END_STONE_PICKAXE = register("end_stone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_END_STONE, 1, -2.75f)));
    public static final Item END_STONE_SHOVEL = register("end_stone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_END_STONE, 1.5f, -2.95f)));
    public static final Item END_STONE_AXE = register("end_stone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_END_STONE, 6, -3.15f)));
    public static final Item END_STONE_HOE = register("end_stone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_END_STONE, 0, -2.8f)));

    public static final Item GRANITE_SWORD = register("granite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_GRANITE, 3, -2.5f)));
    public static final Item GRANITE_PICKAXE = register("granite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_GRANITE, 1, -2.9f)));
    public static final Item GRANITE_SHOVEL = register("granite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_GRANITE, 2.0f, -3.1f)));
    public static final Item GRANITE_AXE = register("granite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_GRANITE, 7, -3.3f)));
    public static final Item GRANITE_HOE = register("granite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_GRANITE, 0, -3.1f)));

    public static final Item NETHERRACK_SWORD = register("netherrack_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_NETHERRACK, 2, -2.2f)));
    public static final Item NETHERRACK_PICKAXE = register("netherrack_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_NETHERRACK, 1, -2.6f)));
    public static final Item NETHERRACK_SHOVEL = register("netherrack_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_NETHERRACK, 1.0f, -2.8f)));
    public static final Item NETHERRACK_AXE = register("netherrack_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_NETHERRACK, 5, -3.0f)));
    public static final Item NETHERRACK_HOE = register("netherrack_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_NETHERRACK, 0, -2.5f)));

    public static final Item SANDSTONE_SWORD = register("sandstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SANDSTONE, 2, -2.3f)));
    public static final Item SANDSTONE_PICKAXE = register("sandstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SANDSTONE, 1, -2.7f)));
    public static final Item SANDSTONE_SHOVEL = register("sandstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SANDSTONE, 1.0f, -2.9f)));
    public static final Item SANDSTONE_AXE = register("sandstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SANDSTONE, 5, -3.1f)));
    public static final Item SANDSTONE_HOE = register("sandstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SANDSTONE, 0, -2.7f)));

    public static final Item SMOOTH_BASALT_SWORD = register("smooth_basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SMOOTH_BASALT, 3, -2.45f)));
    public static final Item SMOOTH_BASALT_PICKAXE = register("smooth_basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SMOOTH_BASALT, 1, -2.85f)));
    public static final Item SMOOTH_BASALT_SHOVEL = register("smooth_basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SMOOTH_BASALT, 1.5f, -3.05f)));
    public static final Item SMOOTH_BASALT_AXE = register("smooth_basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SMOOTH_BASALT, 6, -3.25f)));
    public static final Item SMOOTH_BASALT_HOE = register("smooth_basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SMOOTH_BASALT, 0, -3.0f)));

    public static final Item TERRACOTTA_SWORD = register("terracotta_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TERRACOTTA, 3, -2.35f)));
    public static final Item TERRACOTTA_PICKAXE = register("terracotta_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TERRACOTTA, 1, -2.75f)));
    public static final Item TERRACOTTA_SHOVEL = register("terracotta_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TERRACOTTA, 1.5f, -2.95f)));
    public static final Item TERRACOTTA_AXE = register("terracotta_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TERRACOTTA, 6, -3.15f)));
    public static final Item TERRACOTTA_HOE = register("terracotta_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TERRACOTTA, 0, -2.8f)));

    public static final Item TUFF_SWORD = register("tuff_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TUFF, 2, -2.35f)));
    public static final Item TUFF_PICKAXE = register("tuff_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TUFF, 1, -2.75f)));
    public static final Item TUFF_SHOVEL = register("tuff_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TUFF, 1.5f, -2.95f)));
    public static final Item TUFF_AXE = register("tuff_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TUFF, 5, -3.15f)));
    public static final Item TUFF_HOE = register("tuff_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TUFF, 0, -2.8f)));

    // ── Wood variants (11 × 5) ─────────────────────────────────────────────
    public static final Item OAK_SWORD = register("oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_OAK, 3, -2.4f)));
    public static final Item OAK_PICKAXE = register("oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_OAK, 1, -2.8f)));
    public static final Item OAK_SHOVEL = register("oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_OAK, 1.5f, -3f)));
    public static final Item OAK_AXE = register("oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_OAK, 6, -3.2f)));
    public static final Item OAK_HOE = register("oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_OAK, 0, -3f)));

    public static final Item SPRUCE_SWORD = register("spruce_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_SPRUCE, 3, -2.4f)));
    public static final Item SPRUCE_PICKAXE = register("spruce_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_SPRUCE, 1, -2.8f)));
    public static final Item SPRUCE_SHOVEL = register("spruce_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_SPRUCE, 1.5f, -3f)));
    public static final Item SPRUCE_AXE = register("spruce_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_SPRUCE, 6, -3.2f)));
    public static final Item SPRUCE_HOE = register("spruce_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_SPRUCE, 0, -3f)));

    public static final Item BIRCH_SWORD = register("birch_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BIRCH, 3, -2.4f)));
    public static final Item BIRCH_PICKAXE = register("birch_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BIRCH, 1, -2.8f)));
    public static final Item BIRCH_SHOVEL = register("birch_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BIRCH, 1.5f, -3f)));
    public static final Item BIRCH_AXE = register("birch_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BIRCH, 6, -3.2f)));
    public static final Item BIRCH_HOE = register("birch_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BIRCH, 0, -3f)));

    public static final Item JUNGLE_SWORD = register("jungle_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_JUNGLE, 3, -2.4f)));
    public static final Item JUNGLE_PICKAXE = register("jungle_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_JUNGLE, 1, -2.8f)));
    public static final Item JUNGLE_SHOVEL = register("jungle_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_JUNGLE, 1.5f, -3f)));
    public static final Item JUNGLE_AXE = register("jungle_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_JUNGLE, 6, -3.2f)));
    public static final Item JUNGLE_HOE = register("jungle_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_JUNGLE, 0, -3f)));

    public static final Item ACACIA_SWORD = register("acacia_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_ACACIA, 3, -2.4f)));
    public static final Item ACACIA_PICKAXE = register("acacia_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_ACACIA, 1, -2.8f)));
    public static final Item ACACIA_SHOVEL = register("acacia_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_ACACIA, 1.5f, -3f)));
    public static final Item ACACIA_AXE = register("acacia_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_ACACIA, 6, -3.2f)));
    public static final Item ACACIA_HOE = register("acacia_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_ACACIA, 0, -3f)));

    public static final Item DARK_OAK_SWORD = register("dark_oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_DARK_OAK, 3, -2.4f)));
    public static final Item DARK_OAK_PICKAXE = register("dark_oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_DARK_OAK, 1, -2.8f)));
    public static final Item DARK_OAK_SHOVEL = register("dark_oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_DARK_OAK, 1.5f, -3f)));
    public static final Item DARK_OAK_AXE = register("dark_oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_DARK_OAK, 6, -3.2f)));
    public static final Item DARK_OAK_HOE = register("dark_oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_DARK_OAK, 0, -3f)));

    public static final Item MANGROVE_SWORD = register("mangrove_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_MANGROVE, 3, -2.4f)));
    public static final Item MANGROVE_PICKAXE = register("mangrove_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_MANGROVE, 1, -2.8f)));
    public static final Item MANGROVE_SHOVEL = register("mangrove_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_MANGROVE, 1.5f, -3f)));
    public static final Item MANGROVE_AXE = register("mangrove_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_MANGROVE, 6, -3.2f)));
    public static final Item MANGROVE_HOE = register("mangrove_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_MANGROVE, 0, -3f)));

    public static final Item CHERRY_SWORD = register("cherry_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CHERRY, 3, -2.4f)));
    public static final Item CHERRY_PICKAXE = register("cherry_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CHERRY, 1, -2.8f)));
    public static final Item CHERRY_SHOVEL = register("cherry_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CHERRY, 1.5f, -3f)));
    public static final Item CHERRY_AXE = register("cherry_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CHERRY, 6, -3.2f)));
    public static final Item CHERRY_HOE = register("cherry_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CHERRY, 0, -3f)));

    public static final Item BAMBOO_SWORD = register("bamboo_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BAMBOO, 3, -2.4f)));
    public static final Item BAMBOO_PICKAXE = register("bamboo_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BAMBOO, 1, -2.8f)));
    public static final Item BAMBOO_SHOVEL = register("bamboo_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BAMBOO, 1.5f, -3f)));
    public static final Item BAMBOO_AXE = register("bamboo_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BAMBOO, 6, -3.2f)));
    public static final Item BAMBOO_HOE = register("bamboo_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BAMBOO, 0, -3f)));

    public static final Item CRIMSON_SWORD = register("crimson_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CRIMSON, 3, -2.4f)));
    public static final Item CRIMSON_PICKAXE = register("crimson_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CRIMSON, 1, -2.8f)));
    public static final Item CRIMSON_SHOVEL = register("crimson_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CRIMSON, 1.5f, -3f)));
    public static final Item CRIMSON_AXE = register("crimson_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CRIMSON, 6, -3.2f)));
    public static final Item CRIMSON_HOE = register("crimson_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CRIMSON, 0, -3f)));

    public static final Item WARPED_SWORD = register("warped_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_WARPED, 3, -2.4f)));
    public static final Item WARPED_PICKAXE = register("warped_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_WARPED, 1, -2.8f)));
    public static final Item WARPED_SHOVEL = register("warped_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_WARPED, 1.5f, -3f)));
    public static final Item WARPED_AXE = register("warped_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_WARPED, 6, -3.2f)));
    public static final Item WARPED_HOE = register("warped_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_WARPED, 0, -3f)));

    // ── Leather tools ──────────────────────────────────────────────────────
    public static final Item LEATHER_SWORD = register("leather_sword",
            p -> new Item(p.sword(ModToolTiers.LEATHER, 3, -2.4f)));
    public static final Item LEATHER_PICKAXE = register("leather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.LEATHER, 1, -2.8f)));
    public static final Item LEATHER_SHOVEL = register("leather_shovel",
            p -> new Item(p.shovel(ModToolTiers.LEATHER, 1.5f, -3f)));
    public static final Item LEATHER_AXE = register("leather_axe",
            p -> new Item(p.axe(ModToolTiers.LEATHER, 6, -3.2f)));
    public static final Item LEATHER_HOE = register("leather_hoe",
            p -> new Item(p.hoe(ModToolTiers.LEATHER, 0, -3f)));

    // ── Vanilla material sets ──────────────────────────────────────────────

    public static final Item PAPER_SWORD = register("paper_sword",
            p -> new Item(p.sword(ModToolTiers.PAPER, 3, -2.4f)));
    public static final Item PAPER_PICKAXE = register("paper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PAPER, 1, -2.8f)));
    public static final Item PAPER_SHOVEL = register("paper_shovel",
            p -> new Item(p.shovel(ModToolTiers.PAPER, 1.5f, -3f)));
    public static final Item PAPER_AXE = register("paper_axe",
            p -> new Item(p.axe(ModToolTiers.PAPER, 6, -3.2f)));
    public static final Item PAPER_HOE = register("paper_hoe",
            p -> new Item(p.hoe(ModToolTiers.PAPER, 0, -3f)));

    public static final Item FEATHER_SWORD = register("feather_sword",
            p -> new Item(p.sword(ModToolTiers.FEATHER, 3, -2.4f)));
    public static final Item FEATHER_PICKAXE = register("feather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FEATHER, 1, -2.8f)));
    public static final Item FEATHER_SHOVEL = register("feather_shovel",
            p -> new Item(p.shovel(ModToolTiers.FEATHER, 1.5f, -3f)));
    public static final Item FEATHER_AXE = register("feather_axe",
            p -> new Item(p.axe(ModToolTiers.FEATHER, 6, -3.2f)));
    public static final Item FEATHER_HOE = register("feather_hoe",
            p -> new Item(p.hoe(ModToolTiers.FEATHER, 0, -3f)));

    public static final Item GLASS_SWORD = register("glass_sword",
            p -> new Item(p.sword(ModToolTiers.GLASS, 3, -2.4f)));
    public static final Item GLASS_PICKAXE = register("glass_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GLASS, 1, -2.8f)));
    public static final Item GLASS_SHOVEL = register("glass_shovel",
            p -> new Item(p.shovel(ModToolTiers.GLASS, 1.5f, -3f)));
    public static final Item GLASS_AXE = register("glass_axe",
            p -> new Item(p.axe(ModToolTiers.GLASS, 6, -3.2f)));
    public static final Item GLASS_HOE = register("glass_hoe",
            p -> new Item(p.hoe(ModToolTiers.GLASS, 0, -3f)));

    public static final Item RABBIT_HIDE_HELMET = register("rabbit_hide_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item RABBIT_HIDE_CHESTPLATE = register("rabbit_hide_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item RABBIT_HIDE_LEGGINGS = register("rabbit_hide_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item RABBIT_HIDE_BOOTS = register("rabbit_hide_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item CACTUS_SWORD = register("cactus_sword",
            p -> new Item(p.sword(ModToolTiers.CACTUS, 3, -2.4f)));
    public static final Item CACTUS_PICKAXE = register("cactus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CACTUS, 1, -2.8f)));
    public static final Item CACTUS_SHOVEL = register("cactus_shovel",
            p -> new Item(p.shovel(ModToolTiers.CACTUS, 1.5f, -3f)));
    public static final Item CACTUS_AXE = register("cactus_axe",
            p -> new Item(p.axe(ModToolTiers.CACTUS, 6, -3.2f)));
    public static final Item CACTUS_HOE = register("cactus_hoe",
            p -> new Item(p.hoe(ModToolTiers.CACTUS, 0, -3f)));
    public static final Item CACTUS_HELMET = register("cactus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item CACTUS_CHESTPLATE = register("cactus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item CACTUS_LEGGINGS = register("cactus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item CACTUS_BOOTS = register("cactus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item SPONGE_SWORD = register("sponge_sword",
            p -> new Item(p.sword(ModToolTiers.SPONGE, 3, -2.4f)));
    public static final Item SPONGE_PICKAXE = register("sponge_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SPONGE, 1, -2.8f)));
    public static final Item SPONGE_SHOVEL = register("sponge_shovel",
            p -> new Item(p.shovel(ModToolTiers.SPONGE, 1.5f, -3f)));
    public static final Item SPONGE_AXE = register("sponge_axe",
            p -> new Item(p.axe(ModToolTiers.SPONGE, 6, -3.2f)));
    public static final Item SPONGE_HOE = register("sponge_hoe",
            p -> new Item(p.hoe(ModToolTiers.SPONGE, 0, -3f)));

    public static final Item BONE_SWORD = register("bone_sword",
            p -> new Item(p.sword(ModToolTiers.BONE, 3, -2.4f)));
    public static final Item BONE_PICKAXE = register("bone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BONE, 1, -2.8f)));
    public static final Item BONE_SHOVEL = register("bone_shovel",
            p -> new Item(p.shovel(ModToolTiers.BONE, 1.5f, -3f)));
    public static final Item BONE_AXE = register("bone_axe",
            p -> new Item(p.axe(ModToolTiers.BONE, 6, -3.2f)));
    public static final Item BONE_HOE = register("bone_hoe",
            p -> new Item(p.hoe(ModToolTiers.BONE, 0, -3f)));
    public static final Item BONE_HELMET = register("bone_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item BONE_CHESTPLATE = register("bone_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item BONE_LEGGINGS = register("bone_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item BONE_BOOTS = register("bone_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item CLAY_SWORD = register("clay_sword",
            p -> new Item(p.sword(ModToolTiers.CLAY, 3, -2.4f)));
    public static final Item CLAY_PICKAXE = register("clay_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CLAY, 1, -2.8f)));
    public static final Item CLAY_SHOVEL = register("clay_shovel",
            p -> new Item(p.shovel(ModToolTiers.CLAY, 1.5f, -3f)));
    public static final Item CLAY_AXE = register("clay_axe",
            p -> new Item(p.axe(ModToolTiers.CLAY, 6, -3.2f)));
    public static final Item CLAY_HOE = register("clay_hoe",
            p -> new Item(p.hoe(ModToolTiers.CLAY, 0, -3f)));
    public static final Item CLAY_HELMET = register("clay_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item CLAY_CHESTPLATE = register("clay_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item CLAY_LEGGINGS = register("clay_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item CLAY_BOOTS = register("clay_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item NETHER_WART_SWORD = register("nether_wart_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_WART, 3, -2.4f)));
    public static final Item NETHER_WART_PICKAXE = register("nether_wart_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_WART, 1, -2.8f)));
    public static final Item NETHER_WART_SHOVEL = register("nether_wart_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_WART, 1.5f, -3f)));
    public static final Item NETHER_WART_AXE = register("nether_wart_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_WART, 6, -3.2f)));
    public static final Item NETHER_WART_HOE = register("nether_wart_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_WART, 0, -3f)));

    public static final Item BRICK_SWORD = register("brick_sword",
            p -> new Item(p.sword(ModToolTiers.BRICK, 3, -2.4f)));
    public static final Item BRICK_PICKAXE = register("brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BRICK, 1, -2.8f)));
    public static final Item BRICK_SHOVEL = register("brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.BRICK, 1.5f, -3f)));
    public static final Item BRICK_AXE = register("brick_axe",
            p -> new Item(p.axe(ModToolTiers.BRICK, 6, -3.2f)));
    public static final Item BRICK_HOE = register("brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.BRICK, 0, -3f)));
    public static final Item BRICK_HELMET = register("brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item BRICK_CHESTPLATE = register("brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item BRICK_LEGGINGS = register("brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item BRICK_BOOTS = register("brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item NETHER_BRICK_SWORD = register("nether_brick_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_BRICK, 3, -2.4f)));
    public static final Item NETHER_BRICK_PICKAXE = register("nether_brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_BRICK, 1, -2.8f)));
    public static final Item NETHER_BRICK_SHOVEL = register("nether_brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_BRICK, 1.5f, -3f)));
    public static final Item NETHER_BRICK_AXE = register("nether_brick_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_BRICK, 6, -3.2f)));
    public static final Item NETHER_BRICK_HOE = register("nether_brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_BRICK, 0, -3f)));
    public static final Item NETHER_BRICK_HELMET = register("nether_brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item NETHER_BRICK_CHESTPLATE = register("nether_brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item NETHER_BRICK_LEGGINGS = register("nether_brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item NETHER_BRICK_BOOTS = register("nether_brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item POINTED_DRIPSTONE_SWORD = register("pointed_dripstone_sword",
            p -> new Item(p.sword(ModToolTiers.POINTED_DRIPSTONE, 3, -2.4f)));
    public static final Item POINTED_DRIPSTONE_PICKAXE = register("pointed_dripstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POINTED_DRIPSTONE, 1, -2.8f)));
    public static final Item POINTED_DRIPSTONE_SHOVEL = register("pointed_dripstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.POINTED_DRIPSTONE, 1.5f, -3f)));
    public static final Item POINTED_DRIPSTONE_AXE = register("pointed_dripstone_axe",
            p -> new Item(p.axe(ModToolTiers.POINTED_DRIPSTONE, 6, -3.2f)));
    public static final Item POINTED_DRIPSTONE_HOE = register("pointed_dripstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.POINTED_DRIPSTONE, 0, -3f)));

    public static final Item COPPER_SWORD = register("copper_sword",
            p -> new Item(p.sword(ModToolTiers.COPPER, 3, -2.4f)));
    public static final Item COPPER_PICKAXE = register("copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.COPPER, 1, -2.8f)));
    public static final Item COPPER_SHOVEL = register("copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.COPPER, 1.5f, -3f)));
    public static final Item COPPER_AXE = register("copper_axe",
            p -> new Item(p.axe(ModToolTiers.COPPER, 6, -3.2f)));
    public static final Item COPPER_HOE = register("copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.COPPER, 0, -3f)));
    public static final Item COPPER_HELMET = register("copper_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item COPPER_CHESTPLATE = register("copper_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item COPPER_LEGGINGS = register("copper_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item COPPER_BOOTS = register("copper_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item PHANTOM_SWORD = register("phantom_sword",
            p -> new Item(p.sword(ModToolTiers.PHANTOM_MEMBRANE, 3, -2.4f)));
    public static final Item PHANTOM_PICKAXE = register("phantom_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PHANTOM_MEMBRANE, 1, -2.8f)));
    public static final Item PHANTOM_SHOVEL = register("phantom_shovel",
            p -> new Item(p.shovel(ModToolTiers.PHANTOM_MEMBRANE, 1.5f, -3f)));
    public static final Item PHANTOM_AXE = register("phantom_axe",
            p -> new Item(p.axe(ModToolTiers.PHANTOM_MEMBRANE, 6, -3.2f)));
    public static final Item PHANTOM_HOE = register("phantom_hoe",
            p -> new Item(p.hoe(ModToolTiers.PHANTOM_MEMBRANE, 0, -3f)));
    public static final Item PHANTOM_HELMET = register("phantom_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item PHANTOM_CHESTPLATE = register("phantom_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item PHANTOM_LEGGINGS = register("phantom_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item PHANTOM_BOOTS = register("phantom_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item MAGMA_CREAM_SWORD = register("magma_cream_sword",
            p -> new Item(p.sword(ModToolTiers.MAGMA_CREAM, 3, -2.4f)));
    public static final Item MAGMA_CREAM_PICKAXE = register("magma_cream_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.MAGMA_CREAM, 1, -2.8f)));
    public static final Item MAGMA_CREAM_SHOVEL = register("magma_cream_shovel",
            p -> new Item(p.shovel(ModToolTiers.MAGMA_CREAM, 1.5f, -3f)));
    public static final Item MAGMA_CREAM_AXE = register("magma_cream_axe",
            p -> new Item(p.axe(ModToolTiers.MAGMA_CREAM, 6, -3.2f)));
    public static final Item MAGMA_CREAM_HOE = register("magma_cream_hoe",
            p -> new Item(p.hoe(ModToolTiers.MAGMA_CREAM, 0, -3f)));
    public static final Item MAGMA_CREAM_HELMET = register("magma_cream_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item MAGMA_CREAM_CHESTPLATE = register("magma_cream_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item MAGMA_CREAM_LEGGINGS = register("magma_cream_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item MAGMA_CREAM_BOOTS = register("magma_cream_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item SLIME_SWORD = register("slime_sword",
            p -> new Item(p.sword(ModToolTiers.SLIME, 3, -2.4f)));
    public static final Item SLIME_PICKAXE = register("slime_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SLIME, 1, -2.8f)));
    public static final Item SLIME_SHOVEL = register("slime_shovel",
            p -> new Item(p.shovel(ModToolTiers.SLIME, 1.5f, -3f)));
    public static final Item SLIME_AXE = register("slime_axe",
            p -> new Item(p.axe(ModToolTiers.SLIME, 6, -3.2f)));
    public static final Item SLIME_HOE = register("slime_hoe",
            p -> new Item(p.hoe(ModToolTiers.SLIME, 0, -3f)));
    public static final Item SLIME_HELMET = register("slime_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item SLIME_CHESTPLATE = register("slime_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item SLIME_LEGGINGS = register("slime_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item SLIME_BOOTS = register("slime_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item BLAZE_SWORD = register("blaze_sword",
            p -> new Item(p.sword(ModToolTiers.BLAZE_ROD, 3, -2.4f)));
    public static final Item BLAZE_PICKAXE = register("blaze_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BLAZE_ROD, 1, -2.8f)));
    public static final Item BLAZE_SHOVEL = register("blaze_shovel",
            p -> new Item(p.shovel(ModToolTiers.BLAZE_ROD, 1.5f, -3f)));
    public static final Item BLAZE_AXE = register("blaze_axe",
            p -> new Item(p.axe(ModToolTiers.BLAZE_ROD, 6, -3.2f)));
    public static final Item BLAZE_HOE = register("blaze_hoe",
            p -> new Item(p.hoe(ModToolTiers.BLAZE_ROD, 0, -3f)));
    public static final Item BLAZE_HELMET = register("blaze_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item BLAZE_CHESTPLATE = register("blaze_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item BLAZE_LEGGINGS = register("blaze_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item BLAZE_BOOTS = register("blaze_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item NAUTILUS_SWORD = register("nautilus_sword",
            p -> new Item(p.sword(ModToolTiers.NAUTILUS_SHELL, 3, -2.4f)));
    public static final Item NAUTILUS_PICKAXE = register("nautilus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NAUTILUS_SHELL, 1, -2.8f)));
    public static final Item NAUTILUS_SHOVEL = register("nautilus_shovel",
            p -> new Item(p.shovel(ModToolTiers.NAUTILUS_SHELL, 1.5f, -3f)));
    public static final Item NAUTILUS_AXE = register("nautilus_axe",
            p -> new Item(p.axe(ModToolTiers.NAUTILUS_SHELL, 6, -3.2f)));
    public static final Item NAUTILUS_HOE = register("nautilus_hoe",
            p -> new Item(p.hoe(ModToolTiers.NAUTILUS_SHELL, 0, -3f)));
    public static final Item NAUTILUS_HELMET = register("nautilus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item NAUTILUS_CHESTPLATE = register("nautilus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item NAUTILUS_LEGGINGS = register("nautilus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item NAUTILUS_BOOTS = register("nautilus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item PURPUR_SWORD = register("purpur_sword",
            p -> new Item(p.sword(ModToolTiers.PURPUR, 3, -2.4f)));
    public static final Item PURPUR_PICKAXE = register("purpur_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PURPUR, 1, -2.8f)));
    public static final Item PURPUR_SHOVEL = register("purpur_shovel",
            p -> new Item(p.shovel(ModToolTiers.PURPUR, 1.5f, -3f)));
    public static final Item PURPUR_AXE = register("purpur_axe",
            p -> new Item(p.axe(ModToolTiers.PURPUR, 6, -3.2f)));
    public static final Item PURPUR_HOE = register("purpur_hoe",
            p -> new Item(p.hoe(ModToolTiers.PURPUR, 0, -3f)));
    public static final Item PURPUR_HELMET = register("purpur_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item PURPUR_CHESTPLATE = register("purpur_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item PURPUR_LEGGINGS = register("purpur_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item PURPUR_BOOTS = register("purpur_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item GHAST_TEAR_SWORD = register("ghast_tear_sword",
            p -> new Item(p.sword(ModToolTiers.GHAST_TEAR, 3, -2.4f)));
    public static final Item GHAST_TEAR_PICKAXE = register("ghast_tear_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GHAST_TEAR, 1, -2.8f)));
    public static final Item GHAST_TEAR_SHOVEL = register("ghast_tear_shovel",
            p -> new Item(p.shovel(ModToolTiers.GHAST_TEAR, 1.5f, -3f)));
    public static final Item GHAST_TEAR_AXE = register("ghast_tear_axe",
            p -> new Item(p.axe(ModToolTiers.GHAST_TEAR, 6, -3.2f)));
    public static final Item GHAST_TEAR_HOE = register("ghast_tear_hoe",
            p -> new Item(p.hoe(ModToolTiers.GHAST_TEAR, 0, -3f)));
    public static final Item GHAST_TEAR_HELMET = register("ghast_tear_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item GHAST_TEAR_CHESTPLATE = register("ghast_tear_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item GHAST_TEAR_LEGGINGS = register("ghast_tear_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item GHAST_TEAR_BOOTS = register("ghast_tear_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item EYE_OF_ENDER_SWORD = register("eye_of_ender_sword",
            p -> new Item(p.sword(ModToolTiers.EYE_OF_ENDER, 3, -2.4f)));
    public static final Item EYE_OF_ENDER_PICKAXE = register("eye_of_ender_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.EYE_OF_ENDER, 1, -2.8f)));
    public static final Item EYE_OF_ENDER_SHOVEL = register("eye_of_ender_shovel",
            p -> new Item(p.shovel(ModToolTiers.EYE_OF_ENDER, 1.5f, -3f)));
    public static final Item EYE_OF_ENDER_AXE = register("eye_of_ender_axe",
            p -> new Item(p.axe(ModToolTiers.EYE_OF_ENDER, 6, -3.2f)));
    public static final Item EYE_OF_ENDER_HOE = register("eye_of_ender_hoe",
            p -> new Item(p.hoe(ModToolTiers.EYE_OF_ENDER, 0, -3f)));
    public static final Item EYE_OF_ENDER_HELMET = register("eye_of_ender_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item EYE_OF_ENDER_CHESTPLATE = register("eye_of_ender_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item EYE_OF_ENDER_LEGGINGS = register("eye_of_ender_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item EYE_OF_ENDER_BOOTS = register("eye_of_ender_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item SHULKER_SWORD = register("shulker_sword",
            p -> new Item(p.sword(ModToolTiers.SHULKER_SHELL, 3, -2.4f)));
    public static final Item SHULKER_PICKAXE = register("shulker_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SHULKER_SHELL, 1, -2.8f)));
    public static final Item SHULKER_SHOVEL = register("shulker_shovel",
            p -> new Item(p.shovel(ModToolTiers.SHULKER_SHELL, 1.5f, -3f)));
    public static final Item SHULKER_AXE = register("shulker_axe",
            p -> new Item(p.axe(ModToolTiers.SHULKER_SHELL, 6, -3.2f)));
    public static final Item SHULKER_HOE = register("shulker_hoe",
            p -> new Item(p.hoe(ModToolTiers.SHULKER_SHELL, 0, -3f)));
    public static final Item SHULKER_HELMET = register("shulker_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item SHULKER_CHESTPLATE = register("shulker_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item SHULKER_LEGGINGS = register("shulker_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item SHULKER_BOOTS = register("shulker_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item TURTLE_SCUTE_HELMET = register("turtle_scute_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item TURTLE_SCUTE_CHESTPLATE = register("turtle_scute_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item TURTLE_SCUTE_LEGGINGS = register("turtle_scute_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item TURTLE_SCUTE_BOOTS = register("turtle_scute_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item ECHO_SHARD_SWORD = register("echo_shard_sword",
            p -> new Item(p.sword(ModToolTiers.ECHO_SHARD, 3, -2.4f)));
    public static final Item ECHO_SHARD_PICKAXE = register("echo_shard_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ECHO_SHARD, 1, -2.8f)));
    public static final Item ECHO_SHARD_SHOVEL = register("echo_shard_shovel",
            p -> new Item(p.shovel(ModToolTiers.ECHO_SHARD, 1.5f, -3f)));
    public static final Item ECHO_SHARD_AXE = register("echo_shard_axe",
            p -> new Item(p.axe(ModToolTiers.ECHO_SHARD, 6, -3.2f)));
    public static final Item ECHO_SHARD_HOE = register("echo_shard_hoe",
            p -> new Item(p.hoe(ModToolTiers.ECHO_SHARD, 0, -3f)));
    public static final Item ECHO_SHARD_HELMET = register("echo_shard_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item ECHO_SHARD_CHESTPLATE = register("echo_shard_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item ECHO_SHARD_LEGGINGS = register("echo_shard_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item ECHO_SHARD_BOOTS = register("echo_shard_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item DRAGON_BREATH_SWORD = register("dragon_breath_sword",
            p -> new Item(p.sword(ModToolTiers.DRAGON_BREATH, 3, -2.4f)));
    public static final Item DRAGON_BREATH_PICKAXE = register("dragon_breath_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.DRAGON_BREATH, 1, -2.8f)));
    public static final Item DRAGON_BREATH_SHOVEL = register("dragon_breath_shovel",
            p -> new Item(p.shovel(ModToolTiers.DRAGON_BREATH, 1.5f, -3f)));
    public static final Item DRAGON_BREATH_AXE = register("dragon_breath_axe",
            p -> new Item(p.axe(ModToolTiers.DRAGON_BREATH, 6, -3.2f)));
    public static final Item DRAGON_BREATH_HOE = register("dragon_breath_hoe",
            p -> new Item(p.hoe(ModToolTiers.DRAGON_BREATH, 0, -3f)));
    public static final Item DRAGON_BREATH_HELMET = register("dragon_breath_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item DRAGON_BREATH_CHESTPLATE = register("dragon_breath_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item DRAGON_BREATH_LEGGINGS = register("dragon_breath_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item DRAGON_BREATH_BOOTS = register("dragon_breath_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Edible food-themed sets ────────────────────────────────────────────
    // Cake
    public static final Item CAKE_SWORD = register("cake_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CAKE, 3, -2.4f).food(food(4))));
    public static final Item CAKE_PICKAXE = register("cake_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CAKE, 1, -2.8f).food(food(6))));
    public static final Item CAKE_SHOVEL = register("cake_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CAKE, 1.5f, -3f).food(food(2))));
    public static final Item CAKE_AXE = register("cake_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CAKE, 6, -3.2f).food(food(6))));
    public static final Item CAKE_HOE = register("cake_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CAKE, 0, -3f).food(food(4))));
    public static final Item CAKE_HELMET = register("cake_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item CAKE_CHESTPLATE = register("cake_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item CAKE_LEGGINGS = register("cake_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item CAKE_BOOTS = register("cake_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Bread
    public static final Item BREAD_SWORD = register("bread_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.BREAD, 3, -2.4f).food(food(4))));
    public static final Item BREAD_PICKAXE = register("bread_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.BREAD, 1, -2.8f).food(food(6))));
    public static final Item BREAD_SHOVEL = register("bread_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.BREAD, 1.5f, -3f).food(food(2))));
    public static final Item BREAD_AXE = register("bread_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.BREAD, 6, -3.2f).food(food(6))));
    public static final Item BREAD_HOE = register("bread_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.BREAD, 0, -3f).food(food(4))));
    public static final Item BREAD_HELMET = register("bread_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item BREAD_CHESTPLATE = register("bread_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item BREAD_LEGGINGS = register("bread_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item BREAD_BOOTS = register("bread_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Dried Kelp
    public static final Item DRIED_KELP_SWORD = register("dried_kelp_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.DRIED_KELP, 3, -2.4f).food(food(4))));
    public static final Item DRIED_KELP_PICKAXE = register("dried_kelp_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.DRIED_KELP, 1, -2.8f).food(food(6))));
    public static final Item DRIED_KELP_SHOVEL = register("dried_kelp_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.DRIED_KELP, 1.5f, -3f).food(food(2))));
    public static final Item DRIED_KELP_AXE = register("dried_kelp_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.DRIED_KELP, 6, -3.2f).food(food(6))));
    public static final Item DRIED_KELP_HOE = register("dried_kelp_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.DRIED_KELP, 0, -3f).food(food(4))));
    public static final Item DRIED_KELP_HELMET = register("dried_kelp_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item DRIED_KELP_CHESTPLATE = register("dried_kelp_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item DRIED_KELP_LEGGINGS = register("dried_kelp_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item DRIED_KELP_BOOTS = register("dried_kelp_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Rotten Flesh
    public static final Item ROTTEN_FLESH_SWORD = register("rotten_flesh_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.ROTTEN_FLESH, 3, -2.4f).food(food(4))));
    public static final Item ROTTEN_FLESH_PICKAXE = register("rotten_flesh_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.ROTTEN_FLESH, 1, -2.8f).food(food(6))));
    public static final Item ROTTEN_FLESH_SHOVEL = register("rotten_flesh_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.ROTTEN_FLESH, 1.5f, -3f).food(food(2))));
    public static final Item ROTTEN_FLESH_AXE = register("rotten_flesh_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.ROTTEN_FLESH, 6, -3.2f).food(food(6))));
    public static final Item ROTTEN_FLESH_HOE = register("rotten_flesh_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.ROTTEN_FLESH, 0, -3f).food(food(4))));
    public static final Item ROTTEN_FLESH_HELMET = register("rotten_flesh_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item ROTTEN_FLESH_CHESTPLATE = register("rotten_flesh_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item ROTTEN_FLESH_LEGGINGS = register("rotten_flesh_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item ROTTEN_FLESH_BOOTS = register("rotten_flesh_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Melon
    public static final Item MELON_SWORD = register("melon_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MELON, 3, -2.4f).food(food(4))));
    public static final Item MELON_PICKAXE = register("melon_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MELON, 1, -2.8f).food(food(6))));
    public static final Item MELON_SHOVEL = register("melon_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MELON, 1.5f, -3f).food(food(2))));
    public static final Item MELON_AXE = register("melon_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MELON, 6, -3.2f).food(food(6))));
    public static final Item MELON_HOE = register("melon_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MELON, 0, -3f).food(food(4))));
    public static final Item MELON_HELMET = register("melon_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item MELON_CHESTPLATE = register("melon_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item MELON_LEGGINGS = register("melon_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item MELON_BOOTS = register("melon_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Sweet Berries
    public static final Item SWEET_BERRY_SWORD = register("sweet_berry_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.SWEET_BERRIES, 3, -2.4f).food(food(4))));
    public static final Item SWEET_BERRY_PICKAXE = register("sweet_berry_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.SWEET_BERRIES, 1, -2.8f).food(food(6))));
    public static final Item SWEET_BERRY_SHOVEL = register("sweet_berry_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.SWEET_BERRIES, 1.5f, -3f).food(food(2))));
    public static final Item SWEET_BERRY_AXE = register("sweet_berry_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.SWEET_BERRIES, 6, -3.2f).food(food(6))));
    public static final Item SWEET_BERRY_HOE = register("sweet_berry_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.SWEET_BERRIES, 0, -3f).food(food(4))));
    public static final Item SWEET_BERRY_HELMET = register("sweet_berry_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item SWEET_BERRY_CHESTPLATE = register("sweet_berry_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item SWEET_BERRY_LEGGINGS = register("sweet_berry_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item SWEET_BERRY_BOOTS = register("sweet_berry_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pumpkin Pie
    public static final Item PUMPKIN_PIE_SWORD = register("pumpkin_pie_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUMPKIN_PIE, 3, -2.4f).food(food(4))));
    public static final Item PUMPKIN_PIE_PICKAXE = register("pumpkin_pie_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUMPKIN_PIE, 1, -2.8f).food(food(6))));
    public static final Item PUMPKIN_PIE_SHOVEL = register("pumpkin_pie_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUMPKIN_PIE, 1.5f, -3f).food(food(2))));
    public static final Item PUMPKIN_PIE_AXE = register("pumpkin_pie_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUMPKIN_PIE, 6, -3.2f).food(food(6))));
    public static final Item PUMPKIN_PIE_HOE = register("pumpkin_pie_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUMPKIN_PIE, 0, -3f).food(food(4))));
    public static final Item PUMPKIN_PIE_HELMET = register("pumpkin_pie_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item PUMPKIN_PIE_CHESTPLATE = register("pumpkin_pie_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item PUMPKIN_PIE_LEGGINGS = register("pumpkin_pie_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item PUMPKIN_PIE_BOOTS = register("pumpkin_pie_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Mushroom
    public static final Item MUSHROOM_SWORD = register("mushroom_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MUSHROOM, 3, -2.4f).food(food(4))));
    public static final Item MUSHROOM_PICKAXE = register("mushroom_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MUSHROOM, 1, -2.8f).food(food(6))));
    public static final Item MUSHROOM_SHOVEL = register("mushroom_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MUSHROOM, 1.5f, -3f).food(food(2))));
    public static final Item MUSHROOM_AXE = register("mushroom_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MUSHROOM, 6, -3.2f).food(food(6))));
    public static final Item MUSHROOM_HOE = register("mushroom_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MUSHROOM, 0, -3f).food(food(4))));
    public static final Item MUSHROOM_HELMET = register("mushroom_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item MUSHROOM_CHESTPLATE = register("mushroom_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item MUSHROOM_LEGGINGS = register("mushroom_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item MUSHROOM_BOOTS = register("mushroom_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pufferfish
    public static final Item PUFFERFISH_SWORD = register("pufferfish_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUFFERFISH, 3, -2.4f).food(food(4))));
    public static final Item PUFFERFISH_PICKAXE = register("pufferfish_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUFFERFISH, 1, -2.8f).food(food(6))));
    public static final Item PUFFERFISH_SHOVEL = register("pufferfish_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUFFERFISH, 1.5f, -3f).food(food(2))));
    public static final Item PUFFERFISH_AXE = register("pufferfish_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUFFERFISH, 6, -3.2f).food(food(6))));
    public static final Item PUFFERFISH_HOE = register("pufferfish_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUFFERFISH, 0, -3f).food(food(4))));
    public static final Item PUFFERFISH_HELMET = register("pufferfish_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item PUFFERFISH_CHESTPLATE = register("pufferfish_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item PUFFERFISH_LEGGINGS = register("pufferfish_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item PUFFERFISH_BOOTS = register("pufferfish_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Honey
    public static final Item HONEY_SWORD = register("honey_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.HONEY, 3, -2.4f).food(food(4))));
    public static final Item HONEY_PICKAXE = register("honey_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.HONEY, 1, -2.8f).food(food(6))));
    public static final Item HONEY_SHOVEL = register("honey_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.HONEY, 1.5f, -3f).food(food(2))));
    public static final Item HONEY_AXE = register("honey_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.HONEY, 6, -3.2f).food(food(6))));
    public static final Item HONEY_HOE = register("honey_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.HONEY, 0, -3f).food(food(4))));
    public static final Item HONEY_HELMET = register("honey_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item HONEY_CHESTPLATE = register("honey_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item HONEY_LEGGINGS = register("honey_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item HONEY_BOOTS = register("honey_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Chorus Fruit
    public static final Item CHORUS_FRUIT_SWORD = register("chorus_fruit_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CHORUS_FRUIT, 3, -2.4f).food(food(4))));
    public static final Item CHORUS_FRUIT_PICKAXE = register("chorus_fruit_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CHORUS_FRUIT, 1, -2.8f).food(food(6))));
    public static final Item CHORUS_FRUIT_SHOVEL = register("chorus_fruit_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CHORUS_FRUIT, 1.5f, -3f).food(food(2))));
    public static final Item CHORUS_FRUIT_AXE = register("chorus_fruit_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CHORUS_FRUIT, 6, -3.2f).food(food(6))));
    public static final Item CHORUS_FRUIT_HOE = register("chorus_fruit_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CHORUS_FRUIT, 0, -3f).food(food(4))));
    public static final Item CHORUS_FRUIT_HELMET = register("chorus_fruit_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item CHORUS_FRUIT_CHESTPLATE = register("chorus_fruit_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item CHORUS_FRUIT_LEGGINGS = register("chorus_fruit_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item CHORUS_FRUIT_BOOTS = register("chorus_fruit_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Golden Apple
    public static final Item GOLDEN_APPLE_SWORD = register("golden_apple_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.GOLDEN_APPLE, 3, -2.4f).food(food(4))));
    public static final Item GOLDEN_APPLE_PICKAXE = register("golden_apple_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.GOLDEN_APPLE, 1, -2.8f).food(food(6))));
    public static final Item GOLDEN_APPLE_SHOVEL = register("golden_apple_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.GOLDEN_APPLE, 1.5f, -3f).food(food(2))));
    public static final Item GOLDEN_APPLE_AXE = register("golden_apple_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.GOLDEN_APPLE, 6, -3.2f).food(food(6))));
    public static final Item GOLDEN_APPLE_HOE = register("golden_apple_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.GOLDEN_APPLE, 0, -3f).food(food(4))));
    public static final Item GOLDEN_APPLE_HELMET = register("golden_apple_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final Item GOLDEN_APPLE_CHESTPLATE = register("golden_apple_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final Item GOLDEN_APPLE_LEGGINGS = register("golden_apple_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final Item GOLDEN_APPLE_BOOTS = register("golden_apple_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    private static Item register(String name, Function<Item.Properties, Item> factory) {
        Identifier id = Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        Item item = factory.apply(new Item.Properties().setId(key));
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    /**
     * Force-loads this class so its static initializers run and all items get registered
     * with the vanilla registry. Idempotent.
     */
    public static void register() {
        // no-op; touching the class is enough
    }
}
