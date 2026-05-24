package com.github.ysbbbbbb.kaleidoscopetavern.compat.trinkets.renderer;

import com.github.ysbbbbbb.kaleidoscopetavern.client.render.entity.StringLightsRenderer;
import com.github.ysbbbbbb.kaleidoscopetavern.init.tag.TagMod;
import com.github.ysbbbbbb.kaleidoscopetavern.item.StringLightsBlockItem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class StringLightTrinketRenderer implements TrinketRenderer {
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack poseStack, MultiBufferSource buffer, int packedLight, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (stack.getItem() instanceof StringLightsBlockItem && !(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof StringLightsBlockItem)) {
            StringLightsRenderer.render(stack, contextModel, entity, poseStack, buffer, packedLight);
        }
    }
}
