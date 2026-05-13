package com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco;

import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.BarStoolBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.BaseBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.entity.SitEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BarStoolBlockEntity extends BaseBlockEntity {

    private static final String CACHE_ROT_KEY = "CacheRot";
    private static final String SIT_CACHE_KEY = "SitEntityId";
    private static final float ROTATE_SYNC_THRESHOLD = 0.35F;
    /**
     * 颜色，默认为白色，决定客户端渲染的材质
     */
    private final DyeColor color;
    /**
     * 缓存的 sit 实体，避免频繁查找实体导致的性能问题
     */
    private @Nullable SitEntity sitEntity = null;
    private float cachedRot;

    public BarStoolBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.BAR_STOOL_BE, pos, state);
        this.color = DyeColor.WHITE;
        this.cachedRot = getInitialRot(state);
    }

    public BarStoolBlockEntity(BlockPos pos, BlockState state, DyeColor color) {
        super(ModBlocks.BAR_STOOL_BE, pos, state);
        this.color = color;
        this.cachedRot = getInitialRot(state);
    }

    public boolean isIntactAngle(float tolerance) {
        float remainder = Math.abs(getCachedRot() % 90);
        return remainder < tolerance || remainder > 90 - tolerance;
    }

    public @Nullable Direction currentFacing(float tolerance, BlockState state) {
        if (isIntactAngle(tolerance) && state.hasProperty(BarStoolBlock.FACING)) {
            if (Mth.abs(getCachedRot() + 180F) <= tolerance || Mth.abs(getCachedRot() - 180F) <= tolerance)
                return Direction.NORTH;
            if (Mth.abs(getCachedRot()) <= tolerance)
                return Direction.SOUTH;
            if (Mth.abs(getCachedRot() - 90F) <= tolerance)
                return Direction.WEST;
            if (Mth.abs(getCachedRot() + 90F) <= tolerance)
                return Direction.EAST;
        }
        return null;
    }

    public float getCachedRot() {
        return cachedRot;
    }

    public DyeColor getColor() {
        return color;
    }

    @Nullable
    public SitEntity getSitEntity() {
        return sitEntity;
    }

    public void setSitEntity(@Nullable SitEntity sitEntity) {
        this.sitEntity = sitEntity;
        this.refresh();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BarStoolBlockEntity blockEntity) {
        blockEntity.serverTick(level, pos);
    }

    private void serverTick(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            return;
        }
        if (this.sitEntity == null) {
            return;
        }

        if (this.sitEntity.isRemoved()) {
            this.setSitEntity(null);
            return;
        }

        Entity passenger = this.sitEntity.getFirstPassenger();
        if (passenger == null) {
            return;
        }
        if (!(passenger instanceof LivingEntity)) {
            this.setSitEntity(null);
        }
        float targetRot = Mth.wrapDegrees(getBodyRot(passenger));
        float diff = Mth.degreesDifferenceAbs(this.cachedRot, targetRot);
        if (diff < ROTATE_SYNC_THRESHOLD) {
            return;
        }
        this.cachedRot = targetRot;
        this.refresh();
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        if (compoundTag.contains(CACHE_ROT_KEY, CompoundTag.TAG_FLOAT)) {
            this.cachedRot = Mth.wrapDegrees(compoundTag.getFloat(CACHE_ROT_KEY));
            return;
        }
        if (compoundTag.contains(CACHE_ROT_KEY, CompoundTag.TAG_INT)) {
            this.cachedRot = Mth.wrapDegrees(compoundTag.getInt(CACHE_ROT_KEY));
        }
        if (this.level != null) {
            int sitId = compoundTag.getInt(SIT_CACHE_KEY);
            if (level.getEntity(sitId) instanceof SitEntity sit
                    && sit.blockPosition().equals(this.worldPosition)
            ) {
                this.sitEntity = sit;
            } else {
                this.sitEntity = null;
            }
        } else {
            this.sitEntity = null;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putFloat(CACHE_ROT_KEY, Mth.wrapDegrees(this.cachedRot));
        if (this.sitEntity != null && this.sitEntity.isAlive()
                && this.sitEntity.blockPosition().equals(this.worldPosition)
        ) {
            compoundTag.putInt(SIT_CACHE_KEY, this.sitEntity.getId());
        }
    }

    private static float getInitialRot(BlockState state) {
        if (!state.hasProperty(BarStoolBlock.FACING)) {
            return 0.0F;
        }
        return state.getValue(BarStoolBlock.FACING).toYRot();
    }

    private static float getBodyRot(Entity passenger) {
        if (passenger instanceof LivingEntity livingEntity) {
            return livingEntity.yBodyRot;
        }
        return passenger.getYRot();
    }
}
