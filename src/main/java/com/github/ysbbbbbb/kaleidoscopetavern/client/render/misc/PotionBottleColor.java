package com.github.ysbbbbbb.kaleidoscopetavern.client.render.misc;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.PotionBottleBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.fabric.api.client.rendering.v1.BlockTintsFactory;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PotionBottleColor implements BlockTintsFactory {
    @Override
    public void collect(@NotNull BlockState state, @NotNull BlockAndTintGetter level, @NotNull BlockPos pos,
                        @NotNull IntList tintValues) {
        if (level.getBlockEntity(pos) instanceof PotionBottleBlockEntity blockEntity && !blockEntity.getPotionStack().isEmpty()) {
            PotionContents contents = blockEntity.getPotionStack().get(DataComponents.POTION_CONTENTS);
            if (contents != null) {
                tintValues.add(contents.getColor());
                return;
            }
        }
        tintValues.add(0xFFFFFF);
    }
}
