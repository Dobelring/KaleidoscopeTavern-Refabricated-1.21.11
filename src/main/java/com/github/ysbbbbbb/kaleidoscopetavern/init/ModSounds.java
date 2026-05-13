package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public final class ModSounds {
    public static void registerSounds() {
    }

    public static final SoundEvent EFFECT_VISION = registerSound("effect.vision");

    private static SoundEvent registerSound(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, name), SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, name), 16.0F));
    }
}