package com.github.ysbbbbbb.kaleidoscopetavern.client.render.block;

import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.CellarCabinetBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.CellarCabinetBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate.StorageBlockEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class CellarCabinetBlockEntityRender extends StorageBlockEntityRender<CellarCabinetBlockEntity> {
    public CellarCabinetBlockEntityRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacing(CellarCabinetBlockEntity blockEntity) {
        return blockEntity.getBlockState().getValue(CellarCabinetBlock.FACING);
    }

    @Override
    protected StorageBlockEntityRenderState.Entry createEntry(int slot) {
        int row = slot / 3;
        int column = slot % 3;

        StorageBlockEntityRenderState.Entry entry = new StorageBlockEntityRenderState.Entry();
        entry.x = 0.825 - column * 0.325;
        entry.y = 0.78 - row * 0.29;
        entry.z = 0.875;
        entry.scale = 1.0F;
        entry.xRot = -90F;
        return entry;
    }
}
