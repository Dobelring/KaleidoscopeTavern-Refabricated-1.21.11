package com.github.ysbbbbbb.kaleidoscopetavern.event;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.PlayerTickEvents;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopetavern.init.tag.TagMod;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EffectEvent {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(EffectEvent::onLivingDeath);
        ServerLivingEntityEvents.AFTER_DAMAGE.register(EffectEvent::onLivingHurt);
        PlayerTickEvents.END.register(EffectEvent::playerTicking);
    }

    private static void playerTicking(Player player) {
        if (player.level().isClientSide()) {
            return;
        }

        MobEffectInstance ardentHeat = player.getEffect(ModEffects.ARDENT_HEAT);
        if (ardentHeat == null) {
            return;
        }

        // 饥饿值和饱和度都耗尽时，立刻移除醇热并给予 30 秒饥饿
        FoodData food = player.getFoodData();
        if (food.getFoodLevel() <= 0 && food.getSaturationLevel() <= 0.01F) {
            player.removeEffect(ModEffects.ARDENT_HEAT);
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
        }
    }

    private static void onLivingHurt(LivingEntity livingEntity, DamageSource damageSource, float v, float v1, boolean b) {
        if (livingEntity.level().isClientSide) {
            return;
        }

        @Nullable Entity source = damageSource.getEntity();
        if (!(source instanceof LivingEntity attacker) || !attacker.hasEffect(ModEffects.TOMB_RAIDER)) {
            return;
        }

        if (!livingEntity.getType().is(TagMod.TOMB_RAIDER_DISARMABLE)) {
            return;
        }

        ItemStack mainHand = livingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        if (mainHand.isEmpty()) {
            return;
        }

        // 将耐久降至1（如果物品有耐久度）
        if (mainHand.isDamageableItem()) {
            mainHand.setDamageValue(mainHand.getMaxDamage() - 1);
        }

        // 从主手卸下并掉落到地上
        livingEntity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        ItemEntity itemEntity = new ItemEntity(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), mainHand);
        itemEntity.setPickUpDelay(40);
        livingEntity.level().addFreshEntity(itemEntity);
    }

    private static boolean onLivingDeath(LivingEntity livingEntity, DamageSource damageSource, float v) {
        if (livingEntity.level().isClientSide) {
            return true;
        }

        @Nullable Entity source = damageSource.getEntity();

        if (!(source instanceof LivingEntity living) || !living.hasEffect(ModEffects.BLOODY_MARY)) {
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
