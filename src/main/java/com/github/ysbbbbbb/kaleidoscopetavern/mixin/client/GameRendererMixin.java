package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Unique
    private static final float TIPSY_PRIMARY_PERIOD = 34.0F;
    @Unique
    private static final float TIPSY_SECONDARY_PERIOD = 71.0F;
    @Unique
    private static final float TIPSY_PRIMARY_ROLL = 1.15F;
    @Unique
    private static final float TIPSY_SECONDARY_ROLL = 0.35F;

    @Shadow
    @Final
    Minecraft minecraft;

    @WrapOperation(method = "renderLevel", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack$Pose;pose()Lorg/joml/Matrix4f;", ordinal = 0))
    private Matrix4f renderLevel(PoseStack.Pose instance, Operation<Matrix4f> original, float f, long l, PoseStack poseStack) {
        Matrix4f matrix = original.call(instance);
        float rollDegrees = this.getSlightlyTipsyRoll(f);
        if (rollDegrees == 0.0F) {
            return matrix;
        }

        return new Matrix4f(matrix).rotateZ((float)Math.toRadians(rollDegrees));
    }

    @Unique
    private float getSlightlyTipsyRoll(float f) {
        LocalPlayer player = this.minecraft.player;
        if (player == null || !player.hasEffect(ModEffects.SLIGHTLY_TIPSY.get())) {
            return 0.0F;
        }

        float screenEffectScale = this.minecraft.options.screenEffectScale().get().floatValue();
        if (screenEffectScale <= 0.0F) {
            return 0.0F;
        }

        MobEffectInstance effect = player.getEffect(ModEffects.SLIGHTLY_TIPSY.get());
        int amplifier = effect == null ? 0 : effect.getAmplifier();
        float intensity = Math.min(1.0F + amplifier * 0.18F, 1.45F);
        float time = player.tickCount + f;

        // 低频小角度摆动，避免反胃那种明显的画面扭曲。
        float roll = (float)Math.sin(time / TIPSY_PRIMARY_PERIOD) * TIPSY_PRIMARY_ROLL;
        roll += (float)Math.sin(time / TIPSY_SECONDARY_PERIOD + 1.4F) * TIPSY_SECONDARY_ROLL;
        return roll * intensity * screenEffectScale * screenEffectScale;
    }
}
