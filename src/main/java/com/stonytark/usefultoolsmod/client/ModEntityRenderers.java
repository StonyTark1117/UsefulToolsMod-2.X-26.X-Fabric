package com.stonytark.usefultoolsmod.client;

import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.entity.client.GhostModel;
import com.stonytark.usefultoolsmod.entity.client.GhostRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public final class ModEntityRenderers {
    private ModEntityRenderers() {}

    public static void register() {
        // Entities
        EntityRendererRegistry.register(ModEntities.GRENADE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.GHOST, GhostRenderer::new);

        // Model layers
        ModelLayerRegistry.registerModelLayer(GhostModel.LAYER_LOCATION, GhostModel::createBodyLayer);
    }
}
