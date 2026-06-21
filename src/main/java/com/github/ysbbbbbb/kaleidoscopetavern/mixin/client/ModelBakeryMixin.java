package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

import static com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern.MOD_ID;

@Environment(EnvType.CLIENT)
@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
    @Shadow
    protected abstract void loadSpecialItemModelAndDependencies(ModelResourceLocation modelLocation);

    @Unique
    private static final ModelResourceLocation SHAKER = ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(MOD_ID, "shaker_3d"));

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", shift = At.Shift.AFTER, ordinal = 1))
    private void loadSpecialItemModelAndDependencies(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources, Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStateResources, CallbackInfo ci) {
        loadSpecialItemModelAndDependencies(SHAKER);
    }
}
