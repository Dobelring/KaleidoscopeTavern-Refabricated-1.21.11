package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.api.blockentity.IBarrel;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class BottleBlockItem extends BlockItem {

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

    public BottleBlockItem(Block block, Properties properties) {
        super(block, properties.stacksTo(16));
    }

    public static void setBrewLevel(ItemStack stack, int brewLevel) {
        stack.set(ModDataComponents.BREW_LEVEL, brewLevel);
    }

    public static int getBrewLevel(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.BREW_LEVEL, 0);
    }

    public ItemStack getFilledStack(int brewLevel) {
        ItemStack stack = new ItemStack(this);
        setBrewLevel(stack, brewLevel);
        return stack;
    }
    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
        int brewLevel = getBrewLevel(itemStack);
        if (0 < brewLevel) {
            brewLevel = Math.min(brewLevel, IBarrel.BREWING_FINISHED);
            Component brewLevelText = Component.translatable("message.kaleidoscope_tavern.barrel.brew_level.%d".formatted(brewLevel));
            consumer.accept(Component.translatable("tooltip.kaleidoscope_tavern.bottle_block.brew_level", brewLevelText).withStyle(ChatFormatting.GRAY));
        }
    }
}
