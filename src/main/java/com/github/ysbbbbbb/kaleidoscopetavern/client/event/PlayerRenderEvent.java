package com.github.ysbbbbbb.kaleidoscopetavern.client.event;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static com.github.ysbbbbbb.kaleidoscopetavern.effect.GrassStealthEffect.notInGrassStealthPlant;

public class PlayerRenderEvent {
    public static boolean shouldCancelRender(Player player) {
        if (!player.isAlive()) {
            return false;
        }
        if (player.hasEffect(ModEffects.GRASS_STEALTH.get()) && player.isShiftKeyDown()) {
            Level level = player.level();
            BlockPos pos = player.blockPosition();
            BlockPos abovePos = pos.above();
            return !notInGrassStealthPlant(level, pos) || !notInGrassStealthPlant(level, abovePos);
        }
        return false;
    }
}