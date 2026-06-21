package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class ChalkboardBlockEntityRenderState extends TextBlockEntityRenderState {
    public Direction facing = Direction.NORTH;
    public boolean large = false;
}
