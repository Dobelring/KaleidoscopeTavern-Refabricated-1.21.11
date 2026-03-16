package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.item.DyeColor;

@Environment(EnvType.CLIENT)
public class BarStoolBlockEntityRenderState extends BlockEntityRenderState {
    public float cachedRot;
    public DyeColor color;
    public float partialTicks;
}
