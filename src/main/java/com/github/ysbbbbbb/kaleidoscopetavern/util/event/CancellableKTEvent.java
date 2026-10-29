package com.github.ysbbbbbb.kaleidoscopetavern.util.event;

//森罗酒馆相关的各类可取消事件专用的类
public abstract class CancellableKTEvent implements IKTEvent {

    boolean isCanceled = false;

    public void setCanceled(boolean canceled) {
        this.isCanceled = canceled;
    }

    public boolean isCanceled() {
        return this.isCanceled;
    }

}
