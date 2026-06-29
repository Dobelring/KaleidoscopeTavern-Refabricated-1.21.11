package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModDataComponents;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SignatureCocktailBlockItem extends CocktailBlockItem {
    public SignatureCocktailBlockItem(Block block) {
        super(block);
    }

    public SignatureCocktailBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public static boolean hasEffects(ItemStack stack) {
        return stack.has(ModDataComponents.SIGNATURE_COCKTAIL_EFFECTS);
    }

    public static List<DrinkEffectData.Entry> getEffects(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.SIGNATURE_COCKTAIL_EFFECTS, List.of());
    }

    public static void setEffects(ItemStack stack, List<DrinkEffectData.Entry> effects) {
        stack.set(ModDataComponents.SIGNATURE_COCKTAIL_EFFECTS, List.copyOf(effects));
    }

    public static int getColor(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.SIGNATURE_COCKTAIL_COLOR, 0x5555ff);
    }

    public static void setColor(ItemStack stack, int color) {
        stack.set(ModDataComponents.SIGNATURE_COCKTAIL_COLOR, color);
        CustomModelData customModelData = stack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, CustomModelData.EMPTY);
        List<Integer> colors = new ArrayList<>(customModelData.colors());
        while (colors.isEmpty()) {
            colors.add(0xFFFFFF);
        }
        colors.set(0, color & 0xFFFFFF);
        stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(
                customModelData.floats(),
                customModelData.flags(),
                customModelData.strings(),
                List.copyOf(colors)
        ));
    }

    @Override
    protected void addDrinkEffect(ItemStack drink, Level level, LivingEntity entity) {
        for (DrinkEffectData.Entry entry : getEffects(drink)) {
            if (!level.isClientSide() && level.getRandom().nextFloat() < entry.probability()) {
                MobEffect effect = entry.effect().value();
                int amplifier = entry.amplifier();
                if (effect.isInstantaneous() && level instanceof ServerLevel serverLevel) {
                    effect.applyInstantaneousEffect(serverLevel, entity, entity, entity, amplifier, 1.0);
                } else {
                    int duration = entry.duration() * 20;
                    entity.addEffect(new MobEffectInstance(entry.effect(), duration, amplifier));
                }
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull TooltipDisplay display,
                                @NotNull Consumer<Component> tooltip, @NotNull TooltipFlag flag) {
        List<MobEffectInstance> effectsShow = Lists.newArrayList();
        for (DrinkEffectData.Entry entry : getEffects(stack)) {
            if (entry.probability() >= 1.0F) {
                int duration = entry.duration() * 20;
                int amplifier = entry.amplifier();
                effectsShow.add(new MobEffectInstance(entry.effect(), duration, amplifier));
            }
        }

        if (!effectsShow.isEmpty()) {
            tooltip.accept(CommonComponents.space());
            PotionContents.addPotionTooltip(effectsShow, tooltip, 1.0F, context.tickRate());
        }
    }
}
