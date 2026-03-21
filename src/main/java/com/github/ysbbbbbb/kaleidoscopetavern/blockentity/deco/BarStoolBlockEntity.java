package com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.BaseBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.BarStoolBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.entity.SitEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopetavern.util.SitUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BarStoolBlockEntity extends BaseBlockEntity {

    private static final String CACHE_ROT_KEY = "CacheRot";
    private static final float ROTATE_SYNC_THRESHOLD = 0.35F;
    /**
     * 颜色，默认为白色，决定客户端渲染的材质
     */
    private final DyeColor color;
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

    public static void tick(Level level, BlockPos pos, BlockState state, BarStoolBlockEntity blockEntity) {
        blockEntity.serverTick(level, pos);
    }

    private void serverTick(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            return;
        }
        SitEntity sitEntity = SitUtil.getSitEntity(level, pos);
        if (sitEntity == null || !sitEntity.isAlive() || sitEntity.getPassengers().isEmpty()) {
            return;
        }
        Entity passenger = sitEntity.getFirstPassenger();
        float targetRot = Mth.wrapDegrees(getBodyRot(passenger));
        float diff = Mth.degreesDifferenceAbs(this.cachedRot, targetRot);
        if (diff < ROTATE_SYNC_THRESHOLD) {
            return;
        }
        this.cachedRot = targetRot;
        this.refresh();
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.cachedRot = Mth.wrapDegrees(valueInput.getIntOr(CACHE_ROT_KEY, Math.round(this.cachedRot)));
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.putInt(CACHE_ROT_KEY, Math.round(this.cachedRot));
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
