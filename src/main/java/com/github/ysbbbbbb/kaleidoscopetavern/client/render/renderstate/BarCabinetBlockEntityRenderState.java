package com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Environment(EnvType.CLIENT)
public class BarCabinetBlockEntityRenderState extends BlockEntityRenderState {
    public Direction facing;
    public boolean isSingle = true;
    public @MainModel BlockModelRenderState leftModel = new BlockModelRenderState();
    public BlockModelRenderState rightModel = new BlockModelRenderState();

    /**
     * 左边的物品承载渲染的主体
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.SOURCE)
    @interface MainModel {}
}
