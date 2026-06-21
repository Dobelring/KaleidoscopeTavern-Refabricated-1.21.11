package com.github.ysbbbbbb.kaleidoscopetavern.client.render.block;

import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.HolderBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco.HolderBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate.StorageBlockEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class HolderBlockEntityRender extends StorageBlockEntityRender<HolderBlockEntity> {
    public HolderBlockEntityRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacing(HolderBlockEntity blockEntity) {
        return blockEntity.getBlockState().getValue(HolderBlock.FACING);
    }

    @Override
    protected StorageBlockEntityRenderState.Entry createEntry(int slot) {
        StorageBlockEntityRenderState.Entry entry = new StorageBlockEntityRenderState.Entry();
        entry.x = 0.5;
        entry.y = 0.125;
        entry.z = 0.75;
        entry.scale = 0.95F;
        entry.xRot = -45F;
        return entry;
    }
}
