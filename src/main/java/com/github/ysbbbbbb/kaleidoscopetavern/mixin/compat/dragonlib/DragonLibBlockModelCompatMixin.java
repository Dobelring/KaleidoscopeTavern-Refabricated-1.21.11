package com.github.ysbbbbbb.kaleidoscopetavern.mixin.compat.dragonlib;

import com.mojang.math.Transformation;
import de.mrjulsen.mcdragonlib.fabric.client.model.geometry.extensions.BlockModelExtensions;
import net.minecraft.client.renderer.block.model.BlockModel;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockModel.class, priority = 500)
public abstract class DragonLibBlockModelCompatMixin implements BlockModelExtensions {
    @Unique
    private Transformation kaleidoscope_tavern$rootTransform;

    @Override
    public void dragonlib$setRootTransform(Transformation rootTransform) {
        this.kaleidoscope_tavern$rootTransform = rootTransform;
        ((io.github.fabricators_of_create.porting_lib.models.geometry.extensions.BlockModelExtensions) (Object) this)
                .setRootTransform(rootTransform);
    }

    @Dynamic("Provided by Porting Lib's BlockModel mixin")
    @Inject(method = "getRootTransform", at = @At("HEAD"), cancellable = true, remap = false)
    private void kaleidoscope_tavern$useSharedPortingRootTransform(CallbackInfoReturnable<Transformation> cir) {
        this.kaleidoscope_tavern$useSharedRootTransform(cir);
    }

    @Dynamic("Provided by DragonLib's BlockModel mixin")
    @Inject(method = "dragonlib$getRootTransform", at = @At("HEAD"), cancellable = true, remap = false)
    private void kaleidoscope_tavern$useSharedDragonLibRootTransform(CallbackInfoReturnable<Transformation> cir) {
        this.kaleidoscope_tavern$useSharedRootTransform(cir);
    }

    @Unique
    private void kaleidoscope_tavern$useSharedRootTransform(CallbackInfoReturnable<Transformation> cir) {
        if (this.kaleidoscope_tavern$rootTransform != null) {
            cir.setReturnValue(this.kaleidoscope_tavern$rootTransform);
        }
    }
}
