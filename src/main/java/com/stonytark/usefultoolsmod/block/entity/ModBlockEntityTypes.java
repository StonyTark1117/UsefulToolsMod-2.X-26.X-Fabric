package com.stonytark.usefultoolsmod.block.entity;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Block entity types — uses {@link FabricBlockEntityTypeBuilder} because the vanilla
 * {@code BlockEntityType} constructor is private and there is no public {@code Builder}
 * inner class on Fabric.
 */
public class ModBlockEntityTypes {
    public static final BlockEntityType<SpectralInfuserBlockEntity> SPECTRAL_INFUSER =
            Registry.register(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "spectral_infuser"),
                    FabricBlockEntityTypeBuilder.create(SpectralInfuserBlockEntity::new,
                            ModBlocks.SPECTRAL_INFUSER).build()
            );

    public static void register() {
        // no-op; touching the class triggers static init
    }
}
