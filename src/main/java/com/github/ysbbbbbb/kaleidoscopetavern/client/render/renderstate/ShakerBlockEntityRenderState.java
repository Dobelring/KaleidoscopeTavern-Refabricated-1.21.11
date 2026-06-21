package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class ShakerBlockEntityRenderState extends BlockEntityRenderState {
    public float animationAge;
    public AnimationState put = new AnimationState();
}
