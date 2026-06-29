package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

import java.util.Collections;
import java.util.List;

@Environment(EnvType.CLIENT)
public class StorageBlockEntityRenderState extends BlockEntityRenderState {
    public static class Entry {
        public final BlockModelRenderState model = new BlockModelRenderState();
        public double x;
        public double y;
        public double z;
        public float scale = 1.0F;
        public float yRot;
        public float xRot;
    }

    public Direction facing = Direction.NORTH;
    public List<Entry> entries = Collections.emptyList();
}
