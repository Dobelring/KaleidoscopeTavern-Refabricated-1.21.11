package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.ViewportEvent;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("all")
@Environment(EnvType.CLIENT)
@Mixin(Camera.class)
public class CameraMixin {
    
    @WrapOperation(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FF)V", ordinal = 0))
    private void setup(Camera instance, float f, float g, Operation<Void> original, @Local(argsOnly = true, ordinal = 0) float ft) {
        var cameraSetup = new ViewportEvent.ComputeCameraAngles(
                ((Camera) (Object)this), ft,
                f,
                g,
                0
        );
        cameraSetup.post();
        original.call(instance, cameraSetup.getYaw(), cameraSetup.getPitch());
    }
}
