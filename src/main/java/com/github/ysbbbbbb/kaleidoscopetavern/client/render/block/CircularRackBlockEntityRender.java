package com.github.ysbbbbbb.kaleidoscopetavern.client.render.block;

import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.CircularRackBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco.CircularRackBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate.StorageBlockEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class CircularRackBlockEntityRender extends StorageBlockEntityRender<CircularRackBlockEntity> {
    public CircularRackBlockEntityRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacing(CircularRackBlockEntity blockEntity) {
        return blockEntity.getBlockState().getValue(CircularRackBlock.FACING);
    }

    @Override
    protected StorageBlockEntityRenderState.Entry createEntry(int slot) {
        StorageBlockEntityRenderState.Entry entry = new StorageBlockEntityRenderState.Entry();
        entry.y = 0.125;
        entry.scale = 0.82F;
        switch (slot) {
            case 0 -> {
                entry.x = 0.5;
                entry.z = 0.125;
            }
            case 1 -> {
                entry.x = 0.875;
                entry.z = 0.3125;
                entry.yRot = 22.5F;
            }
            case 2 -> {
                entry.x = 0.875;
                entry.z = 0.6875;
                entry.yRot = -22.5F;
            }
            case 3 -> {
                entry.x = 0.5;
                entry.z = 0.875;
                entry.yRot = 180F;
            }
            case 4 -> {
                entry.x = 0.125;
                entry.z = 0.6875;
                entry.yRot = 157.5F;
            }
            case 5 -> {
                entry.x = 0.125;
                entry.z = 0.3125;
                entry.yRot = -157.5F;
            }
            default -> {
            }
        }
        return entry;
    }
}
