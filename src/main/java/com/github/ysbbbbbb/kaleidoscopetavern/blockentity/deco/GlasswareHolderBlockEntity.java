package com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.BaseBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopetavern.util.forge.ItemStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class GlasswareHolderBlockEntity extends BaseBlockEntity {
    private static final String ITEMS = "items";

    private final ItemStackHandler items = new ItemStackHandler(4) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    public GlasswareHolderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.GLASSWARE_HOLDER_BE, pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ITEMS)) {
            this.items.deserializeNBT(tag.getCompound(ITEMS));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ITEMS, this.items.serializeNBT());
    }

    public ItemStackHandler getItems() {
        return this.items;
    }

    public AABB getRenderBoundingBox() {
        BlockPos pos = this.getBlockPos();
        return new AABB(pos, pos.offset(1, 1, 1));
    }
}
