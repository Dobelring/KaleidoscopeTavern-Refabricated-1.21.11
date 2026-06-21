package com.github.ysbbbbbb.kaleidoscopetavern.block.brew;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.PotionBottleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class PotionBottleBlock extends BottleBlock implements EntityBlock {
    public PotionBottleBlock(Properties properties) {
        super(properties, BottleBlock.SIMPLE_BOTTLE_SHAPE);
    }

    public PotionBottleBlock() {
        this(Properties.of());
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NonNull BlockState state, LootParams.@NonNull Builder params) {
        List<ItemStack> drops = super.getDrops(state, params);
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof PotionBottleBlockEntity be && !be.getPotionStack().isEmpty()) {
            drops.add(be.getPotionStack().copyWithCount(1));
        }
        return drops;
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(LevelReader level, @NonNull BlockPos pos, @NonNull BlockState state, boolean includeData) {
        if (level.getBlockEntity(pos) instanceof PotionBottleBlockEntity be && !be.getPotionStack().isEmpty()) {
            return be.getPotionStack().copyWithCount(1);
        }
        return super.getCloneItemStack(level, pos, state, includeData);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new PotionBottleBlockEntity(pos, state);
    }

    @Override
    public @NotNull VoxelShape getShape(@NonNull BlockState pState, @NonNull BlockGetter pLevel, @NonNull BlockPos pPos, @NonNull CollisionContext pContext) {
        return BottleBlock.SIMPLE_BOTTLE_SHAPE;
    }
}
