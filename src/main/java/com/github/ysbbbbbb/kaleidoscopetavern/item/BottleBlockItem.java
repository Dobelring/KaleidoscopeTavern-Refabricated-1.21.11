package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.api.blockentity.IBarrel;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModDataComponents;
import com.github.ysbbbbbb.kaleidoscopetavern.util.ColorUtils;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BottleBlockItem extends BlockItem {
    public static final String BREW_LEVEL_KEY = "BrewLevel";
    /**
     * 调酒所需的最低酿造等级（优质，即等级 4 以上）。
     */
    public static final int MIN_BREW_LEVEL_FOR_SHAKER = 4;

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

    public static void setBrewLevel(ItemStack stack, int brewLevel) {
        stack.set(ModDataComponents.BREW_LEVEL, clampBrewLevel(brewLevel));
    }

    public static int getBrewLevel(ItemStack stack) {
        int brewLevel = stack.getOrDefault(ModDataComponents.BREW_LEVEL, 0);
        int clampedBrewLevel = clampBrewLevel(brewLevel);
        if (brewLevel != clampedBrewLevel) {
            stack.set(ModDataComponents.BREW_LEVEL, clampedBrewLevel);
        }
        return clampedBrewLevel;
    }

    public static int clampBrewLevel(int brewLevel) {
        return Math.clamp(brewLevel, IBarrel.BREWING_NOT_STARTED, IBarrel.BREWING_FINISHED);
    }

    public @NotNull InteractionResult placeForDispenser(@NotNull BlockPlaceContext placeContext, DispenserBlockEntity entity) {
        BlockState blockState = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
        if (blockState.is(this.getBlock()) && this.getBlock() instanceof DrinkBlock drinkBlock && drinkBlock.getMaxCount() > 1) {
            if (blockState.getValue(drinkBlock.getCountProperty()) < drinkBlock.getMaxCount()) {
                BlockPlaceContext updatedPlaceContext = this.updatePlacementContext(placeContext);
                if (updatedPlaceContext != null)
                    placeContext.getItemInHand().consume(1, updatedPlaceContext.getPlayer());
                entity.applyComponentsFromItemStack(placeContext.getItemInHand());
                entity.setChanged();
                return drinkBlock.tryIncreaseCount(placeContext.getLevel(), placeContext.getClickedPos(), blockState, placeContext.getItemInHand()) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
            }
        }
        return super.place(placeContext);
    }

    /**
     * 判断酒瓶物品是否满足调酒品质要求（brewLevel >= {@link #MIN_BREW_LEVEL_FOR_SHAKER}）。
     * 非 BottleBlockItem 的物品直接返回 true（药水等其他原料不受此限制）。
     */
    public static boolean isValidForShaker(ItemStack stack) {
        if (!(stack.getItem() instanceof BottleBlockItem)) {
            return true;
        }
        return getBrewLevel(stack) >= MIN_BREW_LEVEL_FOR_SHAKER;
    }

    public ItemStack getFilledStack(int brewLevel) {
        ItemStack stack = new ItemStack(this);
        setBrewLevel(stack, brewLevel);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        // 添加颜色说明
        ChatFormatting applied = ColorUtils.ITEM_COLOR_CACHE.apply(stack.getItem());
        if (applied != ChatFormatting.RESET) {
            String key = "color.kaleidoscope_tavern.%s".formatted(applied.getName());
            Component text = Component.translatable("color.kaleidoscope_tavern.prefix")
                    .withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(key).withStyle(applied));
            tooltip.add(text);
        }
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
                    Holder<MobEffect> effect = entry.effect();
                    int duration = entry.duration() * 20;
                    int amplifier = entry.amplifier();
                    effectsShow.add(new MobEffectInstance(effect, duration, amplifier));
                }
            }

            if (!effectsShow.isEmpty()) {
                tooltip.add(CommonComponents.space());
                PotionContents.addPotionTooltip(effectsShow, tooltip::add, 1.0F, context.tickRate());
            }
        }
    }
}
