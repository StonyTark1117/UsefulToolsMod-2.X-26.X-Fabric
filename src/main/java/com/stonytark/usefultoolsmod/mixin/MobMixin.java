package com.stonytark.usefultoolsmod.mixin;

import com.stonytark.usefultoolsmod.Config;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import com.stonytark.usefultoolsmod.event.ModEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Restores two behaviors that have no clean Fabric API equivalent:
 *   - GhostEntity is discarded at finalizeSpawn when Config.ghostEnabled = false,
 *     so disabling ghosts at runtime stops them from completing spawn (matches
 *     the NeoForge FinalizeSpawnEvent#setSpawnCancelled path).
 *   - setTarget(player) is cancelled when the player wears the right armor set,
 *     making certain mob types passive (matches NeoForge LivingChangeTargetEvent).
 */
@Mixin(Mob.class)
public abstract class MobMixin {

    @Inject(method = "finalizeSpawn", at = @At("HEAD"), cancellable = true)
    private void usefultoolsmod$cancelDisabledGhost(
            ServerLevelAccessor level, DifficultyInstance difficulty,
            EntitySpawnReason reason, SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir) {
        Mob self = (Mob) (Object) this;
        if (self instanceof GhostEntity && !Config.ghostEnabled) {
            self.discard();
            cir.setReturnValue(groupData);
        }
    }

    @Inject(method = "setTarget(Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At("HEAD"), cancellable = true)
    private void usefultoolsmod$armorPassivates(LivingEntity newTarget, CallbackInfo ci) {
        if (!(newTarget instanceof Player player)) return;
        Mob self = (Mob) (Object) this;

        // Rotten Flesh full set — undead mobs ignore player
        if (Config.rottenFleshEnabled && Config.rottenFleshUndeadNeutral
                && ModEvents.isWearingFullSet(player, ModEvents::isRottenFleshArmor)) {
            if (self instanceof Zombie || self instanceof AbstractSkeleton || self instanceof Phantom) {
                ci.cancel();
                return;
            }
        }

        // Pumpkin Pie helmet — endermen ignore player
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        if (Config.pumpkinPieEnabled && Config.pumpkinPieEndermanAvoidance
                && ModEvents.isPumpkinPieArmor(head)) {
            if (self instanceof EnderMan) {
                ci.cancel();
                return;
            }
        }

        // Bone helmet — undead reduced detection (>16 blocks)
        if (Config.boneEnabled && Config.boneEffects && ModEvents.isBoneArmor(head)) {
            if ((self instanceof Zombie || self instanceof AbstractSkeleton)
                    && self.distanceTo(player) > 16) {
                ci.cancel();
                return;
            }
        }

        // Phantom membrane full set — phantoms ignore
        if (Config.phantomEnabled && Config.phantomEffects
                && ModEvents.isWearingFullSet(player, ModEvents::isPhantomArmor)) {
            if (self instanceof Phantom) {
                ci.cancel();
                return;
            }
        }

        // Nautilus full set — aquatic mobs ignore
        if (Config.nautilusEnabled && Config.nautilusEffects
                && ModEvents.isWearingFullSet(player, ModEvents::isNautilusArmor)) {
            if (self instanceof Guardian || self instanceof Drowned) {
                ci.cancel();
                return;
            }
        }

        // Eye of Ender full set — endermen neutral
        if (Config.eyeOfEnderEnabled && Config.eyeOfEnderEffects
                && ModEvents.isWearingFullSet(player, ModEvents::isEyeOfEnderArmor)) {
            if (self instanceof EnderMan) {
                ci.cancel();
                return;
            }
        }

        // Echo Shard full set — warden neutral
        if (Config.echoShardEnabled && Config.echoShardEffects
                && ModEvents.isWearingFullSet(player, ModEvents::isEchoShardArmor)) {
            if (self instanceof Warden) {
                ci.cancel();
                return;
            }
        }

        // Turtle Scute full set — guardians ignore
        if (Config.turtleScuteEnabled && Config.turtleScuteEffects
                && ModEvents.isWearingFullSet(player, ModEvents::isTurtleScuteArmor)) {
            if (self instanceof Guardian) {
                ci.cancel();
            }
        }
    }
}
