package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import java.util.Collections;
import java.util.List;

@Environment(EnvType.CLIENT)
public class GlasswareHolderBlockEntityRenderState extends BlockEntityRenderState {
    public static class Entry {
        public final BlockModelRenderState model = new BlockModelRenderState();
        public int slot;
    }

    public List<Entry> entries = Collections.emptyList();
}
