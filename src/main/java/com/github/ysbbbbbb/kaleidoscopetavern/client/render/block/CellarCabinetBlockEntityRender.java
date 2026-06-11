package com.github.ysbbbbbb.kaleidoscopetavern.client.render.block;

import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.CellarCabinetBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.CellarCabinetBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.util.forge.ItemStackHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class CellarCabinetBlockEntityRender extends StorageBlockEntityRender<CellarCabinetBlockEntity> {
    public CellarCabinetBlockEntityRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CellarCabinetBlockEntity rack, float partialTick, PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Direction direction = rack.getBlockState().getValue(CellarCabinetBlock.FACING);
        ItemStackHandler items = rack.getItems();

        poseStack.pushPose();
        this.applyFacingRotation(direction, poseStack);
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack stack = items.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            int row = i / 3;
            int column = i % 3;
            double x = 0.825 - column * 0.325;
            double y = 0.78 - row * 0.29;

            this.renderStack(stack, poseStack, buffer, packedLight, packedOverlay,
                    x, y, 0.875, 1, 0, -90);
        }
        poseStack.popPose();
    }
}

