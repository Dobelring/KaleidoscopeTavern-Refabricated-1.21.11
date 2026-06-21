package com.github.ysbbbbbb.kaleidoscopetavern.item;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import net.minecraft.world.item.BlockItem;

public class GrapevineItem extends BlockItem {
    public GrapevineItem(Properties properties) {
        super(ModBlocks.WILD_GRAPEVINE, properties.useItemDescriptionPrefix());
    }

    public GrapevineItem() {
        this(new Properties());
    }
}
