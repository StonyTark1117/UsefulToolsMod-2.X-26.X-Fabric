package com.stonytark.usefultoolsmod.compat.modmenu;

import com.stonytark.usefultoolsmod.client.UsefulToolsConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/**
 * Surfaces UsefulToolsConfigScreen (Cloth Config-backed) on the Mod Menu
 * mod-list page when Mod Menu is installed. Without Mod Menu this class is
 * never loaded — the modmenu entrypoint is silently skipped by Fabric Loader.
 */
public class UsefulToolsModMenuPlugin implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return UsefulToolsConfigScreen::build;
    }
}
