package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.item.DyeColor;

@Environment(EnvType.CLIENT)
public class BarStoolBlockEntityRenderState extends BlockEntityRenderState {
    public float renderRot = 0f;
    public boolean initialized = false;
    public boolean hasPassenger = false;
    public float passengerBodyRot = 0f;
    public float passengerBodyRotVelocity = 0f;
    public float lastPassengerBodyRot = 0f;
    public float lastSampleTime = 0f;
    public boolean hasLastSample = false;
    public DyeColor color;
}
