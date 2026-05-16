package com.github.ysbbbbbb.kaleidoscopetavern.event;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class EffectEvent {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(EffectEvent::onLivingDeath);
    }

    private static boolean onLivingDeath(LivingEntity livingEntity, DamageSource damageSource, float v) {
        if (livingEntity.level().isClientSide) {
            return true;
        }

        @Nullable Entity source = damageSource.getEntity();

        if (!(source instanceof LivingEntity living) || !living.hasEffect(ModEffects.BLOODY_MARY.get())) {
            return true;
        }
        if (livingEntity == living) {
            return true;
        }
        int healAmount = (int) Math.floor(livingEntity.getMaxHealth() / 3.0f);
        if (healAmount > 0) {
            living.heal(healAmount);
            return false;
        }
        return true;
    }
}
