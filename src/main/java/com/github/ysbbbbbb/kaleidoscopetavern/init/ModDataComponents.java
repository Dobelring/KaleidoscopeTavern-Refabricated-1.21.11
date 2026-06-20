package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public final class ModDataComponents {

    public static final DataComponentType<Integer> BREW_LEVEL = DataComponentType.<Integer>builder()
            .persistent(Codec.INT)
            .networkSynchronized(ByteBufCodecs.VAR_INT)
            .build();

    public static final DataComponentType<ShakerItem.Result> SHAKER_RESULT =
            DataComponentType.<ShakerItem.Result>builder()
                    .persistent(ShakerItem.Result.CODEC)
                    .networkSynchronized(ShakerItem.Result.STREAM_CODEC)
                    .build();

    public static final DataComponentType<List<DrinkEffectData.Entry>> SIGNATURE_COCKTAIL_EFFECTS =
            DataComponentType.<List<DrinkEffectData.Entry>>builder()
                    .persistent(Codec.list(DrinkEffectData.Entry.ENTRY_CODEC))
                    .networkSynchronized(DrinkEffectData.Entry.STREAM_CODEC.apply(ByteBufCodecs.list()))
                    .build();

    public static final DataComponentType<Integer> SIGNATURE_COCKTAIL_COLOR =
            DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build();

    public static void register() {
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "brew_level"), BREW_LEVEL);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "shaker_result"), SHAKER_RESULT);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "signature_cocktail_effects"), SIGNATURE_COCKTAIL_EFFECTS);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "signature_cocktail_color"), SIGNATURE_COCKTAIL_COLOR);
    }
}
