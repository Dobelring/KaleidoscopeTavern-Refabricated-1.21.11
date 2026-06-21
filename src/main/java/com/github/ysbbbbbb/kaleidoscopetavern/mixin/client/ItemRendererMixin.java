package com.github.ysbbbbbb.kaleidoscopetavern.mixin.client;

import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern.MOD_ID;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    public abstract ItemModelShaper getItemModelShaper();

    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    public BakedModel use3DModel(
            BakedModel model, ItemStack stack,
            ItemDisplayContext renderMode,
            boolean leftHanded,
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
            int light,
            int overlay
    ) {
        boolean bl = renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.FIXED;
        if (!bl) {
            if (stack.getItem() instanceof ShakerItem) {
                return getItemModelShaper().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(MOD_ID, "shaker_3d"), "inventory"));
            }
        }
        return model;
    }
}
