package com.github.ysbbbbbb.kaleidoscopetavern.mixin.compat.dragonlib;

import com.mojang.math.Transformation;
import de.mrjulsen.mcdragonlib.fabric.client.model.geometry.extensions.BlockModelExtensions;
import net.minecraft.client.renderer.block.model.BlockModel;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockModel.class, priority = 500)
public abstract class DragonLibBlockModelCompatMixin implements BlockModelExtensions {
    @SuppressWarnings("all")
    @Unique
    private Transformation kaleidoscope_tavern$rootTransform;

    @Override
    public void dragonlib$setRootTransform(Transformation rootTransform) {
        this.kaleidoscope_tavern$rootTransform = rootTransform;
    }
}
