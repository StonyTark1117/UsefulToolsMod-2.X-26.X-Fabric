package com.stonytark.usefultoolsmod.trigger;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

/**
 * Registers all custom criterion triggers for UsefulToolsMod advancements.
 * Static initializer registers directly against {@link BuiltInRegistries#TRIGGER_TYPES};
 * touching this class via {@link #register()} forces the static init to run.
 */
public class ModTriggers {

    public static final GhostNearbyTrigger GHOST_NEARBY = Registry.register(
            BuiltInRegistries.TRIGGER_TYPES,
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "ghost_nearby"),
            new GhostNearbyTrigger());

    public static final CoalToolIgnitedTrigger COAL_TOOL_IGNITED = Registry.register(
            BuiltInRegistries.TRIGGER_TYPES,
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "coal_tool_ignited"),
            new CoalToolIgnitedTrigger());

    public static void register() {
        // no-op; touching the class triggers static init
    }
}
