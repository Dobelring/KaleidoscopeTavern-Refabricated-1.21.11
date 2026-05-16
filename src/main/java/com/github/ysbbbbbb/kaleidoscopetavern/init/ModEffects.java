package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.effect.BaseEffect;
import com.github.ysbbbbbb.kaleidoscopetavern.effect.GrassStealthEffect;
import com.github.ysbbbbbb.kaleidoscopetavern.effect.HighHeelsEffect;
import com.github.ysbbbbbb.kaleidoscopetavern.effect.VisionEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public final class ModEffects {
    public static Holder<MobEffect> SLIGHTLY_TIPSY;
    public static Holder<MobEffect> HIGH_HEELS;
    public static Holder<MobEffect> GRASS_STEALTH;
    public static Holder<MobEffect> VISION;
    public static Holder<MobEffect> BLOODY_MARY;

    public static void registerEffects() {
        SLIGHTLY_TIPSY = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "slightly_tipsy"), new BaseEffect(MobEffectCategory.NEUTRAL, 0xFFD94A));
        HIGH_HEELS = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "high_heels"), new HighHeelsEffect(0xE85BAA));
        GRASS_STEALTH = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "grass_stealth"), new GrassStealthEffect(0x71BDE7));
        VISION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "vision"), new VisionEffect(0x408997));
        BLOODY_MARY = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bloody_mary"), new BaseEffect(0xF73A36));
    }
}
