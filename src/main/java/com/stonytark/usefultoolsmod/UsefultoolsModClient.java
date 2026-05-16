package com.stonytark.usefultoolsmod;

import com.stonytark.usefultoolsmod.block.entity.ModMenuTypes;
import com.stonytark.usefultoolsmod.client.ModEntityRenderers;
import com.stonytark.usefultoolsmod.client.SpectralInfuserScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

/**
 * Client-side entry point. Registered via {@code entrypoints.client} in
 * {@code fabric.mod.json}. Mirrors what the NeoForge sibling port does inside
 * {@code @EventBusSubscriber(value = Dist.CLIENT)} static handlers.
 */
public class UsefultoolsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UsefultoolsMod.LOGGER.info("Initializing The Useful Tools Mod (Fabric client)");

        // Entity renderers + model layers
        ModEntityRenderers.register();

        // Spectral Infuser screen
        MenuScreens.register(ModMenuTypes.SPECTRAL_INFUSER_MENU, SpectralInfuserScreen::new);
    }
}
