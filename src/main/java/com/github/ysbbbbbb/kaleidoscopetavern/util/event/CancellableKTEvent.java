package com.github.ysbbbbbb.kaleidoscopetavern.util.event;

import org.jetbrains.annotations.ApiStatus;

public abstract class CancellableKTEvent implements IKTEvent {
    private boolean canceled;

    public final void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @ApiStatus.NonExtendable
    public boolean isCanceled() {
        return this.canceled;
    }
}
