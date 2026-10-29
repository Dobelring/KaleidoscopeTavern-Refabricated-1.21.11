package com.github.ysbbbbbb.kaleidoscopetavern.util.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

//森罗酒馆相关的各类事件专用的接口
public interface IKTEvent {

    default void post() {
        CALLBACK.invoker().post(this);
    };

    /**
     * 注册核心
     */
    Event<Callback<IKTEvent>> CALLBACK = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback<IKTEvent> callback : callbacks) {
            callback.post(event);
        }
    });

    @Deprecated
    static <T extends IKTEvent> void post(T event) {
        CALLBACK.invoker().post(event);
    }

    interface Callback<T extends IKTEvent> {
        void post(T event);
    }
}
