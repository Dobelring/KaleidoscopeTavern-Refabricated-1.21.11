package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.api.client.IModelModifyRotationAfterBake;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.baked.RotatedBakedModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Contract;

@Environment(EnvType.CLIENT)
public final class CommonModelLoading {
    private static final String ROTATION_PREFIX = BlockStateProperties.ROTATION_16.getName() + "=";

    public static void init() {
        ModelLoadingPlugin.register(pluginContext -> pluginContext.modifyModelAfterBake()
                .register(ModelModifier.WRAP_PHASE, (model, context) -> wrapModel(model, context.id())));
    }

    private static BakedModel wrapModel(BakedModel model, ResourceLocation id) {
        if (model == null || !(id instanceof ModelResourceLocation modelId)) {
            return model;
        }

        ResourceLocation blockId = new ResourceLocation(modelId.getNamespace(), modelId.getPath());
        if (!(BuiltInRegistries.BLOCK.get(blockId) instanceof IModelModifyRotationAfterBake<?> bake)) {
            return model;
        }
        Property<Integer> rotationProperty = bake.getRotationProperty();

        if (rotationProperty != BlockStateProperties.ROTATION_16)
            return model;

        int rotation = getRotationIndex(modelId.getVariant());
        if (rotation <= 0) {
            return model;
        }
        return new RotatedBakedModel(model, rotation);
    }

    @Contract
    private static int getRotationIndex(String variant) {
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

    private CommonModelLoading() {
    }
}
