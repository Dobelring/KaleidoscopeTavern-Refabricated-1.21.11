package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.api.blockentity.IBarrel;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BottleBlockItem extends BlockItem {
    public static final String BREW_LEVEL_KEY = "BrewLevel";

    public BottleBlockItem(Block block) {
        this(block, new Properties()
                .stacksTo(16));
    }

    public BottleBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public static ItemStack getMaxLevelDrink(Item item) {
        ItemStack stack = item.getDefaultInstance();
        setBrewLevel(stack, IBarrel.BREWING_FINISHED);
        return stack;
    }

    public @NotNull InteractionResult placeForDispenser(@NotNull BlockPlaceContext placeContext, DispenserBlockEntity entity) {
        BlockState blockState = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
        if (blockState.is(this.getBlock()) && this.getBlock() instanceof DrinkBlock drinkBlock && drinkBlock.getMaxCount() > 1) {
            if (blockState.getValue(drinkBlock.getCountProperty()) < drinkBlock.getMaxCount()) {
                BlockPlaceContext updatedPlaceContext = this.updatePlacementContext(placeContext);
                if (updatedPlaceContext != null)
                    placeContext.getItemInHand().shrink(1);
                entity.setChanged();
                return drinkBlock.tryIncreaseCount(placeContext.getLevel(), placeContext.getClickedPos(), blockState, placeContext.getItemInHand()) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
            }
        }
        return super.place(placeContext);
    }

    public static void setBrewLevel(ItemStack stack, int brewLevel) {
        stack.getOrCreateTag().putInt(BREW_LEVEL_KEY, clampBrewLevel(brewLevel));
    }

    public static int getBrewLevel(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains(BREW_LEVEL_KEY)) {
            int brewLevel = stack.getTag().getInt(BREW_LEVEL_KEY);
            int clampedBrewLevel = clampBrewLevel(brewLevel);
            if (brewLevel != clampedBrewLevel) {
                stack.getOrCreateTag().putInt(BREW_LEVEL_KEY, clampedBrewLevel);
            }
            return clampedBrewLevel;
        }
        return 0;
    }

    public static int clampBrewLevel(int brewLevel) {
        return Math.max(IBarrel.BREWING_NOT_STARTED, Math.min(brewLevel, IBarrel.BREWING_FINISHED));
    }

    public ItemStack getFilledStack(int brewLevel) {
        ItemStack stack = new ItemStack(this);
        setBrewLevel(stack, brewLevel);
        return stack;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        int brewLevel = getBrewLevel(stack);
        if (0 < brewLevel) {
            Component brewLevelText = Component.translatable("message.kaleidoscope_tavern.barrel.brew_level.%d".formatted(brewLevel));
            tooltip.add(Component.translatable("tooltip.kaleidoscope_tavern.bottle_block.brew_level", brewLevelText).withStyle(ChatFormatting.GRAY));

            DrinkEffectData effectData = DrinkEffectDataReloadListener.INSTANCE.get(stack.getItem());
            if (effectData == null) {
                return;
            }
            var effects = effectData.effects();
            if (effects.isEmpty()) {
                return;
            }

            List<MobEffectInstance> effectsShow = Lists.newArrayList();
            for (DrinkEffectData.Entry entry : effects.get(brewLevel - 1)) {
                if (entry.probability() >= 1.0F) {
                    MobEffect effect = entry.effect();
                    int duration = entry.duration() * 20;
                    int amplifier = entry.amplifier();
                    effectsShow.add(new MobEffectInstance(effect, duration, amplifier));
                }
            }

            if (!effectsShow.isEmpty()) {
                PotionUtils.addPotionTooltip(effectsShow, tooltip, 1.0F);
            }
        }
    }
}
