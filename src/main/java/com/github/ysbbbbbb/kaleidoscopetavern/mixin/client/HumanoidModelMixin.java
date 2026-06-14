package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> {
    @Shadow
    @Final
    public ModelPart rightArm;

    @Shadow
    @Final
    public ModelPart leftArm;

    @Unique
    private float preSet(T entity) {
        int remainingTicks = entity.getUseItemRemainingTicks();
        if (remainingTicks == 0) {
            return 0f;
        }
        Minecraft instance = Minecraft.getInstance();
        float totalTicks = entity.tickCount + (instance.isPaused() ? 0f : instance.timer.partialTick);
        return  (float) Math.sin(totalTicks * 1.5f) * 0.25f;
    }


    @Inject(method = "poseRightArm", at = @At("HEAD"), cancellable = true)
    public void poseRightArmShaking(T livingEntity, CallbackInfo ci) {
        boolean bl3 = livingEntity.getMainArm() == HumanoidArm.RIGHT;
        if (livingEntity instanceof AbstractClientPlayer player) {
            if (!player.getItemInHand(bl3 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND).isEmpty() && player.getItemInHand(bl3 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND).getItem() instanceof ShakerItem) {
                float rot = preSet(livingEntity);
                if (rot != 0f) {
                    rightArm.xRot = 1.375f * Mth.PI - Mth.PI * rot;
                    rightArm.zRot = -Mth.PI * 0.05f;
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "poseLeftArm", at = @At("HEAD"), cancellable = true)
    public void poseLeftArmShaking(T livingEntity, CallbackInfo ci) {
        boolean bl3 = livingEntity.getMainArm() == HumanoidArm.LEFT;
        if (livingEntity instanceof AbstractClientPlayer player) {
            if (!player.getItemInHand(bl3 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND).isEmpty() && player.getItemInHand(bl3 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND).getItem() instanceof ShakerItem) {
                float rot = preSet(livingEntity);
                if (rot != 0f) {
                    leftArm.xRot = 1.375f * Mth.PI + Mth.PI * rot;
                    leftArm.zRot = Mth.PI * 0.05f;
                    ci.cancel();
                }
            }
        }
    }
}
