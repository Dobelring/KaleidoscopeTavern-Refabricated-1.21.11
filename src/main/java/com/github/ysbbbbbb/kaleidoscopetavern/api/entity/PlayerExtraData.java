package com.github.ysbbbbbb.kaleidoscopetavern.api.entity;

public interface PlayerExtraData {
    default int kaleidoscope_tavern$getPersistentData() {
        throw new RuntimeException("This should be overridden via mixin...");
    };

    default void kaleidoscope_tavern$setPersistentData(int compoundTag) {
        throw new RuntimeException("This should be overridden via mixin...");
    };
}
