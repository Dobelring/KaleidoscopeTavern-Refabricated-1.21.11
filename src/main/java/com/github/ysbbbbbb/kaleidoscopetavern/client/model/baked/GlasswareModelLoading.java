package com.github.ysbbbbbb.kaleidoscopetavern.client.model.baked;

import com.github.ysbbbbbb.kaleidoscopetavern.block.mixology.GlasswareBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public final class GlasswareModelLoading {
    private static final String ROTATION_PREFIX = GlasswareBlock.ROTATION.getName() + "=";

    public static void init() {
        ModelLoadingPlugin.register(pluginContext -> pluginContext.modifyModelAfterBake()
                .register(ModelModifier.WRAP_PHASE, (model, context) -> wrapModel(model, context.id())));
    }

    private static BakedModel wrapModel(BakedModel model, ResourceLocation id) {
        if (model == null || !(id instanceof ModelResourceLocation modelId)) {
            return model;
        }

        ResourceLocation blockId = new ResourceLocation(modelId.getNamespace(), modelId.getPath());
        if (!(BuiltInRegistries.BLOCK.get(blockId) instanceof GlasswareBlock)) {
            return model;
        }

        int rotation = getGlasswareRotation(modelId.getVariant());
        if (rotation <= 0) {
            return model;
        }

        return new RotatedGlasswareBakedModel(model, rotation);
    }

    private static int getGlasswareRotation(String variant) {
        int start = variant.indexOf(ROTATION_PREFIX);
        if (start < 0) {
            return -1;
        }

        int valueStart = start + ROTATION_PREFIX.length();
        int valueEnd = variant.indexOf(',', valueStart);
        if (valueEnd < 0) {
            valueEnd = variant.length();
        }

        try {
            return Integer.parseInt(variant.substring(valueStart, valueEnd));
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }

    private GlasswareModelLoading() {
    }
}
