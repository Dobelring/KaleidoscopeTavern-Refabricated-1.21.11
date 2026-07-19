package com.github.ysbbbbbb.kaleidoscopetavern.block.plant;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.util.event.EventHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class GrapeCropBlock extends Block implements BonemealableBlock, SimpleWaterloggedBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final int MAX_AGE = BlockStateProperties.MAX_AGE_5;
    public static final VoxelShape SHAPE = Block.box(2, 6, 2, 14, 16, 14);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final GrowPerTickProbability probability;
    private final Supplier<ItemStack> shearResult;

    public GrapeCropBlock(Properties properties, GrowPerTickProbability probability, Supplier<ItemStack> shearResult) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                    .setValue(AGE, 0)
                    .setValue(WATERLOGGED, false));
        this.probability = probability;
        this.shearResult = shearResult;
    }

    public GrapeCropBlock(GrowPerTickProbability probability, Supplier<ItemStack> shearResult) {
        this(Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .offsetType(OffsetType.XYZ)
                .pushReaction(PushReaction.DESTROY), probability, shearResult);
    }

    @Deprecated(since = "1.1.0")
    public GrapeCropBlock() {
        this(
                (state, level, pos, random) -> 0.25F,
                () -> new ItemStack(ModItems.GRAPE, 3)
        );
    }

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
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player,
                                          @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        // 只有成熟的葡萄才可以被剪刀收获
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.is(Items.SHEARS) && isMaxAge(state)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            Block.popResource(level, pos, this.shearResult.get());

            // 有 30% 强制额外掉落 1-2 青提葡萄
            if (level.random.nextFloat() < 0.3F) {
                int count = level.random.nextInt(1, 3);
                Block.popResource(level, pos, new ItemStack(ModItems.GREEN_GRAPE, count));
            }

            heldItem.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            player.playSound(SoundEvents.BEEHIVE_SHEAR);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        if (EventHooks.onCropsGrowPre(level, pos, state, random.nextDouble() < this.probability.getProbability(state, level, pos, random))) {
            int nextAge = state.getValue(AGE) + random.nextInt(1, 3);
            level.setBlockAndUpdate(pos, state.setValue(AGE, Math.min(nextAge, MAX_AGE)));
            EventHooks.onCropsGrowPost(level, pos, state);
        }
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState,
                                           @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (state.canSurvive(level, pos)) {
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        // 上方必须是葡萄藤架
        var aboveState = level.getBlockState(pos.above());
        if (aboveState.getBlock() instanceof GrapevineTrellisBlock trellis) {
            return trellis.isMaxAge(aboveState);
        }
        return false;
    }

    public boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, @NotNull BlockPos pos, BlockState state) {
        int newAge = Math.min(state.getValue(AGE) + random.nextInt(1, 3), MAX_AGE);
        level.setBlockAndUpdate(pos, state.setValue(AGE, newAge));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, WATERLOGGED);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder lootParamsBuilder) {
        // 只有成熟的葡萄才会掉落物品
        if (isMaxAge(state)) {
            return super.getDrops(state, lootParamsBuilder);
        }
        return Collections.emptyList();
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return ModItems.GRAPE.getDefaultInstance();
    }
}
