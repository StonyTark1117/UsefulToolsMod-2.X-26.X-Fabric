package com.stonytark.usefultoolsmod;

import com.mojang.logging.LogUtils;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.block.entity.ModBlockEntityTypes;
import com.stonytark.usefultoolsmod.block.entity.ModMenuTypes;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import com.stonytark.usefultoolsmod.event.ModEvents;
import com.stonytark.usefultoolsmod.item.ModArmorMaterials;
import com.stonytark.usefultoolsmod.item.ModCreativeModeTabs;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.trigger.ModTriggers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;

public class UsefultoolsMod implements ModInitializer {
    public static final String MOD_ID = "usefultoolsmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing The Useful Tools Mod (Fabric, MC {})", "26.1.2");

        // Config is plain Java; load before anything else can read it.
        Config.load();

        // Register in dependency order: tabs first so they can reference items,
        // then materials, then entities/items/blocks. Direct Registry.register
        // happens at class-init time, so calling the no-op register() methods
        // just force-loads the class.
        ModCreativeModeTabs.register();
        // ModArmorMaterials and ModToolTiers are plain constants — no register() needed;
        // they get class-loaded when ModItems references them.
        ModEntities.register();
        ModItems.register();
        ModBlocks.register();
        ModBlockEntityTypes.register();
        ModMenuTypes.register();
        ModTriggers.register();

        // Entity attributes (must happen after ModEntities registers the EntityType)
        FabricDefaultAttributeRegistry.register(ModEntities.GHOST, GhostEntity.createAttributes());

        // Spawn placement rules — Ghosts spawn floating (NoGravity), so NO_RESTRICTIONS
        // (matches the 1.21.1-Fabric and 26.1.2-NeoForge ports).
        SpawnPlacements.register(ModEntities.GHOST,
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GhostEntity::checkGhostSpawnRules);

        // Server-side event listeners (LivingEntity attacks, tick handlers, etc.)
        ModEvents.register();

        // Worldgen biome modifications (Fabric handles these in code, not JSON)
        registerWorldgen();
    }

    private void registerWorldgen() {
        // Overworld rgold ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE,
                        Identifier.fromNamespaceAndPath(MOD_ID, "rgold_ore_placed")));

        // Nether rgold ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE,
                        Identifier.fromNamespaceAndPath(MOD_ID, "nether_rgold_ore_placed")));

        // End rgold ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE,
                        Identifier.fromNamespaceAndPath(MOD_ID, "end_rgold_ore_placed")));

        // Ghost mob spawns
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                MobCategory.MONSTER,
                ModEntities.GHOST,
                5, 1, 3);
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInTheNether(),
                MobCategory.MONSTER,
                ModEntities.GHOST,
                3, 1, 2);
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInTheEnd(),
                MobCategory.MONSTER,
                ModEntities.GHOST,
                3, 1, 2);
    }
}
