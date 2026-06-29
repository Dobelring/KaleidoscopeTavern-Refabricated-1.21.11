package com.github.ysbbbbbb.kaleidoscopetavern.block.mixology;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.mixology.ShakerBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.github.ysbbbbbb.kaleidoscopetavern.util.ItemUtils;
import com.github.ysbbbbbb.kaleidoscopetavern.util.neo.ItemStackHandler;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

import static com.github.ysbbbbbb.kaleidoscopetavern.init.tag.TagMod.COCKTAIL_INGREDIENT;

public class ShakerBlock extends Block implements EntityBlock {
    public static final MapCodec<ShakerBlock> CODEC = simpleCodec(ShakerBlock::new);
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 16, 12);

    public ShakerBlock(Properties properties) {
        super(properties
                .noOcclusion()
                .instabreak()
                .pushReaction(PushReaction.DESTROY)
                .sound(SoundType.LANTERN)
        );
    }

    public ShakerBlock() {
        this(Properties.of());
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos,
                                                   @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }
        if (!(level.getBlockEntity(pos) instanceof ShakerBlockEntity shaker)) {
            return InteractionResult.PASS;
        }

        ItemStack itemInHand = player.getItemInHand(hand);
        // 绌烘墜鍙栦笅
        if (itemInHand.isEmpty() && level instanceof ServerLevel serverLevel) {
            getDrops(state, serverLevel, pos, shaker)
                    .forEach(s -> ItemUtils.giveItemToPlayer(player, s));
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
            level.playSound(null, pos, SoundEvents.LANTERN_BREAK, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }

        if (itemInHand.is(COCKTAIL_INGREDIENT)) {
            shaker.addIngredient(itemInHand, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setPlacedBy(@NonNull Level pLevel, @NonNull BlockPos pPos, @NonNull BlockState pState, @Nullable LivingEntity placer, @NonNull ItemStack pStack) {
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
    public @NotNull List<ItemStack> getDrops(@NonNull BlockState state, LootParams.@NonNull Builder params) {
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
    public BlockEntity newBlockEntity(@NonNull BlockPos pPos, @NonNull BlockState pState) {
        return new ShakerBlockEntity(pPos, pState);
    }

    @Override
    public @NotNull VoxelShape getShape(@NonNull BlockState pState, @NonNull BlockGetter pLevel, @NonNull BlockPos pPos, @NonNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }
}
