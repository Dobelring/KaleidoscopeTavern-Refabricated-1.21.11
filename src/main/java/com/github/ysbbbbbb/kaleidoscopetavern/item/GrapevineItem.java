package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class GrapevineItem extends BlockItem {
    public GrapevineItem(Properties properties) {
        super(ModBlocks.WILD_GRAPEVINE, properties.useItemDescriptionPrefix());
    }
    @SuppressWarnings("unused")
    public GrapevineItem() {
        this(new Properties());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("tooltip.kaleidoscope_tavern.grapevine.1").withStyle(ChatFormatting.GRAY));
        builder.accept(Component.translatable("tooltip.kaleidoscope_tavern.grapevine.2").withStyle(ChatFormatting.GRAY));
        builder.accept(Component.translatable("tooltip.kaleidoscope_tavern.grapevine.3").withStyle(ChatFormatting.GRAY));
    }
}
