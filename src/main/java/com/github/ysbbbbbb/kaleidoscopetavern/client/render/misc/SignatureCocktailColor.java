package com.github.ysbbbbbb.kaleidoscopetavern.client.render.misc;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.mixology.SignatureCocktailBlockEntity;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockTintsFactory;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public final class SignatureCocktailColor {
    private static final int OPAQUE_MASK = 0xFE000000;

    private SignatureCocktailColor() {
    }

    private static int opaque(int color) {
        return OPAQUE_MASK | (color & 0xFFFFFF);
    }

    public static class Block implements BlockTintsFactory {
        @Override
        public void collect(@NotNull BlockState state, @NotNull BlockAndTintGetter level, @NotNull BlockPos pos,
                            @NotNull IntList tintValues) {
            if (level.getBlockEntity(pos) instanceof SignatureCocktailBlockEntity blockEntity) {
                tintValues.add(opaque(blockEntity.getColor()));
                return;
            }
            tintValues.add(opaque(0xFFFFFF));
        }
    }
}
