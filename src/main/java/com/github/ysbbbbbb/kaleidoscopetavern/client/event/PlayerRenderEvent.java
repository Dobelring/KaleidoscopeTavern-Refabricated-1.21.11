package com.github.ysbbbbbb.kaleidoscopetavern.client.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static com.github.ysbbbbbb.kaleidoscopetavern.effect.GrassStealthEffect.notInGrassStealthPlant;

public class PlayerRenderEvent {
    public static boolean shouldCancelRender(@NotNull Avatar player) {
        if (!player.isAlive()) {
            return false;
        }
        if (player.isShiftKeyDown()) {
            Level level = player.level();
            BlockPos pos = player.blockPosition();
            BlockPos abovePos = pos.above();
            return !notInGrassStealthPlant(level, pos) || !notInGrassStealthPlant(level, abovePos);
        }
        return false;
    }
}
