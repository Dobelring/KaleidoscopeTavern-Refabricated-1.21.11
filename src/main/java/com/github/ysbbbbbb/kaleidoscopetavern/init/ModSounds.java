package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
    public static void registerSounds() {
    }

    public static final SoundEvent EFFECT_VISION = registerSound("effect.vision");

    public static final SoundEvent HOLDER_POP = registerSound("block.holder.pop");

    private static SoundEvent registerSound(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(KaleidoscopeTavern.MOD_ID, name), SoundEvent.createFixedRangeEvent(new ResourceLocation(KaleidoscopeTavern.MOD_ID, name), 16.0F));
    }
}