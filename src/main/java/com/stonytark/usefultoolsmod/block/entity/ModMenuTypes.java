package com.stonytark.usefultoolsmod.block.entity;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

/**
 * Spectral Infuser menu type — Fabric pattern.
 *
 * <p>Uses {@link ExtendedScreenHandlerType} with {@link BlockPos#STREAM_CODEC} so the
 * server can ship the block-entity position to the client at menu-open time. Mirrors
 * what NeoForge's {@code IMenuTypeExtension.create((id, inv, buf) -> ...)} does.
 */
public class ModMenuTypes {

    public static final MenuType<SpectralInfuserMenu> SPECTRAL_INFUSER_MENU =
            Registry.register(
                    BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "spectral_infuser_menu"),
                    new ExtendedScreenHandlerType<>(SpectralInfuserMenu::new, BlockPos.STREAM_CODEC));

    public static void register() {
        // no-op; touching the class triggers static init
    }
}
