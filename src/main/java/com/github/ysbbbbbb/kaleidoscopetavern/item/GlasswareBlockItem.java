package com.github.ysbbbbbb.kaleidoscopetavern.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.block.Block;

public class GlasswareBlockItem extends BlockItem {
    public GlasswareBlockItem(Block block) {
        this(block, new Properties().stacksTo(16).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK));
    }

    public GlasswareBlockItem(Block block, Properties properties) {
        super(block, properties.stacksTo(16).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK));
    }
}
