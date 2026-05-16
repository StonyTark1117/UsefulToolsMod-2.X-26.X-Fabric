package com.stonytark.usefultoolsmod.block.entity;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

/**
 * Spectral Infuser menu type — Fabric pattern.
 *
 * <p>Uses {@link ExtendedMenuType} with {@link BlockPos#STREAM_CODEC} so the server can ship
 * the block-entity position to the client at menu-open time. Mirrors what NeoForge's
 * {@code IMenuTypeExtension.create((id, inv, buf) -> ...)} does.
 *
 * <p>Note: in Fabric API 0.149.0+26.1.2 the old {@code fabric-screen-handler-api-v1}
 * module was replaced by {@code fabric-menu-api-v1} with the {@code ExtendedScreenHandlerType}
 * renamed to {@code ExtendedMenuType}.
 */
public class ModMenuTypes {

    public static final MenuType<SpectralInfuserMenu> SPECTRAL_INFUSER_MENU =
            Registry.register(
                    BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "spectral_infuser_menu"),
                    new ExtendedMenuType<>(SpectralInfuserMenu::new, BlockPos.STREAM_CODEC));

    public static void register() {
        // no-op; touching the class triggers static init
    }
}
