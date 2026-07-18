package com.github.ysbbbbbb.kaleidoscopetavern.block.mixology;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.mixology.ShakerBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.github.ysbbbbbb.kaleidoscopetavern.util.forge.ItemHandlerHelper;
import com.github.ysbbbbbb.kaleidoscopetavern.util.forge.ItemStackHandler;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.github.ysbbbbbb.kaleidoscopetavern.init.tag.TagMod.COCKTAIL_INGREDIENT;
import static net.minecraft.world.InteractionResult.PASS;

@SuppressWarnings("deprecation")
public class ShakerBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 16, 12);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ShakerBlock() {
        super(Properties.of()
                .noOcclusion()
                .instabreak()
                .pushReaction(PushReaction.DESTROY)
                .sound(SoundType.LANTERN)
        );
        this.defaultBlockState().setValue(WATERLOGGED, false);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState2, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player,
                                          @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (hand != InteractionHand.MAIN_HAND) {
            return PASS;
        }
        if (!(level.getBlockEntity(pos) instanceof ShakerBlockEntity shaker)) {
            return PASS;
        }

        ItemStack itemInHand = player.getItemInHand(hand);
        // 空手取下
        if (itemInHand.isEmpty() && level instanceof ServerLevel serverLevel) {
            getDrops(state, serverLevel, pos, shaker)
                    .forEach(stack -> ItemHandlerHelper.giveItemToPlayer(player, stack));
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
            level.playSound(null, pos, SoundEvents.LANTERN_BREAK, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }

        if (itemInHand.is(COCKTAIL_INGREDIENT)) {
            shaker.addIngredient(itemInHand, player);
            return InteractionResult.SUCCESS;
        }
        return PASS;
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity placer, @NotNull ItemStack pStack) {
        if (ShakerItem.hasStorage(pStack) && pLevel.getBlockEntity(pPos) instanceof ShakerBlockEntity shaker) {
            ItemStackHandler storage = ShakerItem.getStorage(pStack);
            shaker.setStorage(storage);
            if (ShakerItem.hasResult(pStack)) {
                shaker.setResult(ShakerItem.getResult(pStack));
            }
            shaker.refresh();
        }
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        List<ItemStack> stacks = Lists.newArrayList(super.getDrops(state, params));
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof ShakerBlockEntity be) {
            ItemStack instance = ModItems.SHAKER.getDefaultInstance();
            ItemStackHandler storage = be.getStorage();
            ShakerItem.setStorage(instance, storage);
            if (!be.getResult().isEmpty()) {
                ShakerItem.setResult(instance, be.getResult());
            }
            stacks.add(instance);
        }
        return stacks;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean hasWater = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, hasWater);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ShakerBlockEntity(pPos, pState);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }
}
