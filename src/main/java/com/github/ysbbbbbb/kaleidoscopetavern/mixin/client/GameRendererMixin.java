package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
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

    @WrapOperation(method = "renderLevel", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix4f;mul(Lorg/joml/Matrix4fc;)Lorg/joml/Matrix4f;"))
    private Matrix4f renderLevel(Matrix4f instance, Matrix4fc matrix, Operation<Matrix4f> original, DeltaTracker deltaTracker) {
        float rollDegrees = this.getSlightlyTipsyRoll(deltaTracker);
        if (rollDegrees == 0.0F) {
            return original.call(instance, matrix);
        }

        Matrix4f tipsyMatrix = new Matrix4f(matrix).rotateZ((float)Math.toRadians(rollDegrees));
        return original.call(instance, tipsyMatrix);
    }

    @Unique
    private float getSlightlyTipsyRoll(DeltaTracker deltaTracker) {
        LocalPlayer player = this.minecraft.player;
        if (player == null || !player.hasEffect(ModEffects.SLIGHTLY_TIPSY)) {
            return 0.0F;
        }

        float screenEffectScale = this.minecraft.options.screenEffectScale().get().floatValue();
        if (screenEffectScale <= 0.0F) {
            return 0.0F;
        }

        MobEffectInstance effect = player.getEffect(ModEffects.SLIGHTLY_TIPSY);
        int amplifier = effect == null ? 0 : effect.getAmplifier();
        float intensity = Math.min(1.0F + amplifier * 0.18F, 1.45F);
        float time = player.tickCount + deltaTracker.getGameTimeDeltaPartialTick(true);

        // 低频小角度摆动，避免反胃那种明显的画面扭曲。
        float roll = (float)Math.sin(time / TIPSY_PRIMARY_PERIOD) * TIPSY_PRIMARY_ROLL;
        roll += (float)Math.sin(time / TIPSY_SECONDARY_PERIOD + 1.4F) * TIPSY_SECONDARY_ROLL;
        return roll * intensity * screenEffectScale * screenEffectScale;
    }
}
