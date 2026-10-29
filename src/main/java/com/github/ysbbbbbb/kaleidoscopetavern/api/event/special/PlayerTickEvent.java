package com.github.ysbbbbbb.kaleidoscopetavern.api.event.special;

import io.github.fabricators_of_create.porting_lib.entity.events.PlayerTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

public final class PlayerTickEvent {
    public static final Event<Start> START = EventFactory.createArrayBacked(Start.class, callbacks -> player -> {
        for (Start callback : callbacks) {
            callback.onStartOfPlayerTick(player);
        }
    });

    public static final Event<End> END = EventFactory.createArrayBacked(End.class, callbacks -> player -> {
        for (End callback : callbacks) {
            callback.onEndOfPlayerTick(player);
        }
    });

    private PlayerTickEvent() {
    }

    public static void register() {
        PlayerTickEvents.START.register(player -> START.invoker().onStartOfPlayerTick(player));
        PlayerTickEvents.END.register(player -> END.invoker().onEndOfPlayerTick(player));
    }

    @FunctionalInterface
    public interface End {
        void onEndOfPlayerTick(Player player);
    }

    @FunctionalInterface
    public interface Start {
        void onStartOfPlayerTick(Player player);
    }
}
