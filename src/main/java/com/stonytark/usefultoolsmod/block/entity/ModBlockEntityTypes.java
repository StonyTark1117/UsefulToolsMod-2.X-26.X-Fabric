package com.stonytark.usefultoolsmod.block.entity;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class ModBlockEntityTypes {
    public static final BlockEntityType<SpectralInfuserBlockEntity> SPECTRAL_INFUSER =
            Registry.register(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "spectral_infuser"),
                    new BlockEntityType<>(SpectralInfuserBlockEntity::new,
                            Set.of(ModBlocks.SPECTRAL_INFUSER))
            );

    public static void register() {
        // no-op; touching the class triggers static init
    }
}
