package com.github.ysbbbbbb.kaleidoscopetavern.util.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface IKTEvent {
    Event<Callback<IKTEvent>> CALLBACK = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback<IKTEvent> callback : callbacks) {
            callback.post(event);
        }
    });

    default void post() {
        CALLBACK.invoker().post(this);
    }

    interface Callback<T extends IKTEvent> {
        void post(T event);
    }
}
