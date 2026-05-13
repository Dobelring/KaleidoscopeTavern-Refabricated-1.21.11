package com.github.ysbbbbbb.kaleidoscopetavern.api.event;

import com.github.ysbbbbbb.kaleidoscopetavern.game.grape.GoldGrapePlant;
import com.github.ysbbbbbb.kaleidoscopetavern.game.grape.IceGrapePlant;
import com.github.ysbbbbbb.kaleidoscopetavern.game.grape.NormalGrapePlant;
import com.github.ysbbbbbb.kaleidoscopetavern.util.event.CancellableEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class PlantGrapeEvent extends CancellableEvent {
    private final BlockState state;
    private final Level level;
    private final BlockPos pos;
    private final Player player;
    private final InteractionHand hand;
    private final BlockHitResult hitResult;

    public static void register() {
        CALLBACK.register(event -> {
            if (event instanceof PlantGrapeEvent plantGrapeEvent) {
                GoldGrapePlant.onPlant(plantGrapeEvent);
                IceGrapePlant.onPlant(plantGrapeEvent);
                NormalGrapePlant.onPlant(plantGrapeEvent);
            }
        });
    }

    public PlantGrapeEvent(BlockState state, Level level, BlockPos pos, Player player,
                           InteractionHand hand, BlockHitResult hitResult) {
        this.state = state;
        this.level = level;
        this.pos = pos;
        this.player = player;
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public BlockState state() {
        return state;
    }

    public Level level() {
        return level;
    }

    public BlockPos pos() {
        return pos;
    }

    public Player player() {
        return player;
    }

    public InteractionHand hand() {
        return hand;
    }

    public BlockHitResult hitResult() {
        return hitResult;
    }

    @Override
    public void post() {
        CALLBACK.invoker().post(this);
    }
}
