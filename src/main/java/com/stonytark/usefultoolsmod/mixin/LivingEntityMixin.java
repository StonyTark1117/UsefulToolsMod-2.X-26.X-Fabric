package com.stonytark.usefultoolsmod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.stonytark.usefultoolsmod.event.ModEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Wraps LivingEntity#hurtServer so that the mod's incoming-damage rules
 * (in ModEvents.processIncomingDamage) can both cancel the damage AND
 * mutate its amount. Fabric's ServerLivingEntityEvents.ALLOW_DAMAGE is
 * cancel-only and cannot change the float amount, so the original
 * NeoForge LivingIncomingDamageEvent#setAmount semantics are restored
 * by computing the modified amount here and forwarding it.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @WrapMethod(method = "hurtServer")
    private boolean usefultoolsmod$applyIncomingDamageRules(
            ServerLevel level, DamageSource source, float amount,
            Operation<Boolean> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        ModEvents.IncomingDamageResult result =
                ModEvents.processIncomingDamage(self, source, amount);
        if (result.cancelled()) return false;
        return original.call(level, source, result.amount());
    }
}
