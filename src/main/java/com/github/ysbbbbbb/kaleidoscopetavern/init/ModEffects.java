package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.effect.*;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
public final class ModEffects {
    static final List<Supplier<?>> EFFECTS = new ArrayList<>();

    public static Supplier<MobEffect> SLIGHTLY_TIPSY = register("slightly_tipsy",() -> new BaseEffect(MobEffectCategory.NEUTRAL, 0xFFD94A));
    public static Supplier<MobEffect> HIGH_HEELS = register("high_heels",() -> new HighHeelsEffect(0xE85BAA));
    public static Supplier<MobEffect> GRASS_STEALTH = register("grass_stealth",() -> new GrassStealthEffect(0x71BDE7));
    public static Supplier<MobEffect> VISION = register("vision",() -> new VisionEffect(0x408997));
    public static Supplier<MobEffect> BLOODY_MARY = register("bloody_mary",() -> new BaseEffect(0xF73A36));
    public static Supplier<MobEffect> ARDENT_HEAT = register("ardent_heat",() -> new ArdentHeatEffect(0xFF6B35));
    public static Supplier<MobEffect> LONG_REACH = register("long_reach",() -> new LongReachEffect(0x8B6914));
    public static Supplier<MobEffect> TOMB_RAIDER = register("tomb_raider",() -> new TombRaiderEffect(0xDAA520));
    public static Supplier<MobEffect> XP_DRAIN = register("xp_drain",() -> new XpDrainEffect(0x7CFC00));
    public static Supplier<MobEffect> UPSIDE_DOWN = register("upside_down",() -> new UpsideDownEffect(0x9B59B6));
    public static Supplier<MobEffect> ZENITH = register("zenith",() -> new ZenithEffect(0x87CEEB));
    public static Supplier<MobEffect> SHRIEK_ATTACK = register("shriek_attack",() -> new  ShriekAttackEffect(0x0D4C4A));

    @NotNull
    private static <T extends MobEffect> Supplier<T> register(String id, Supplier<T> supplier) {
        var v = Suppliers.memoize(() ->
                Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeTavern.MOD_ID, id), supplier.get()));
        EFFECTS.add(v);
        return v;
    }

    public static void registerEffects() {
        EFFECTS.forEach(Supplier::get);
        EFFECTS.clear();
    }
}
