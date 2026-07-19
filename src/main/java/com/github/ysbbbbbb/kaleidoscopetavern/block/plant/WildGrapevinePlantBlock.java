package com.github.ysbbbbbb.kaleidoscopetavern.block.plant;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WildGrapevinePlantBlock extends GrowingPlantBodyBlock implements BonemealableBlock, SimpleWaterloggedBlock {
    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final Properties PROPERTIES = Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .instabreak()
            .sound(SoundType.CAVE_VINES)
            .pushReaction(PushReaction.DESTROY);

    public WildGrapevinePlantBlock() {
        super(PROPERTIES, Direction.DOWN, SHAPE, false);
        this.registerDefaultState(
                this.stateDefinition.any()
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.WILD_GRAPEVINE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);
        if (stateForPlacement == null)
            return null;
        boolean hasWater = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return stateForPlacement.setValue(WATERLOGGED, hasWater);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockPos relative = pos.relative(this.growthDirection.getOpposite());
        BlockState relativeState = level.getBlockState(relative);
        return relativeState.is(this.getHeadBlock())
                || relativeState.is(this.getBodyBlock())
                || this.canAttachTo(relativeState)
                || relativeState.isFaceSturdy(level, relative, this.growthDirection);
    }

    @Override
    protected boolean canAttachTo(BlockState state) {
        // 树叶不属于 SupportType.FULL，故需要特殊判断一下
        return state.is(BlockTags.LEAVES);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState2, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state, boolean isClient) {
        GrowingPlantHeadBlock headBlock = this.getHeadBlock();
        return BlockUtil.getTopConnectedBlock(level, pos, state.getBlock(), this.growthDirection, headBlock).map(headPos -> {
            BlockState blockState = level.getBlockState(headPos);
            return blockState.is(headBlock) && !blockState.getValue(WildGrapevineBlock.SHEARED);
        }).orElse(false);
    }
}
