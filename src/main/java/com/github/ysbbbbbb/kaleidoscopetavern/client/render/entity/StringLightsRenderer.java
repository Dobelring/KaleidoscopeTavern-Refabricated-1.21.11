package com.github.ysbbbbbb.kaleidoscopetavern.client.render.entity;

import com.github.ysbbbbbb.kaleidoscopetavern.item.StringLightsBlockItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public final class StringLightsRenderer {
    private StringLightsRenderer() {
    }

    public static void render(ItemStack stack, EntityModel<?> model, LivingEntity entity, PoseStack poseStack,
                              MultiBufferSource buffer, int packedLight) {
        if (!(stack.getItem() instanceof StringLightsBlockItem) || !(model instanceof HumanoidModel<?> humanoidModel)) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.gameRenderer == null) {
            return;
        }

        ItemInHandRenderer itemRenderer = minecraft.gameRenderer.itemInHandRenderer;
        poseStack.pushPose();
        humanoidModel.body.translateAndRotate(poseStack);
        poseStack.translate(0f, -0.1875f, -0.4375f);
        poseStack.mulPose(Axis.YP.rotationDegrees(180f));
        poseStack.scale(-0.625f, -0.625f, 0.625f);
        itemRenderer.renderItem(entity, stack, ItemDisplayContext.HEAD, false, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
