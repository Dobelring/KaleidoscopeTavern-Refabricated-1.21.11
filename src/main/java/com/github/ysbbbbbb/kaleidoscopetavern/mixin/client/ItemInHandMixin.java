package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.client.animation.ShakerAnimation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    public abstract void renderItem(LivingEntity mob, ItemStack itemStack, ItemDisplayContext type,
                                    PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords);

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", shift = At.Shift.AFTER), cancellable = true)
    private void renderArmWithItem(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack,
                                   ItemStack itemStack, float inverseArmHeight, PoseStack poseStack,
                                   SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci,
                                   @Local(name = "arm") HumanoidArm arm) {
        if (ShakerAnimation.applyHandTransform(poseStack, this.minecraft.player, arm, frameInterp, itemStack)) {
            boolean rightArm = arm == HumanoidArm.RIGHT;
            this.renderItem(player, itemStack,
                    rightArm ? ItemDisplayContext.THIRD_PERSON_RIGHT_HAND : ItemDisplayContext.THIRD_PERSON_LEFT_HAND,
                    poseStack, submitNodeCollector, lightCoords);
            poseStack.popPose();
            ci.cancel();
        }
    }
}
