package com.github.ysbbbbbb.kaleidoscopetavern.util.event;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.CropStateChangeEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.api.event.LivingChangeTargetEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EventHooks {
    public static boolean onCropsGrowPre(LevelAccessor level, BlockPos pos, BlockState state, boolean shouldGrow) {
        if (!shouldGrow) {
            return false;
        }
        CropStateChangeEvent.Pre event = new CropStateChangeEvent.Pre(level, pos, state);
        event.post();
        return !event.isCanceled();
    }

    public static void onCropsGrowPost(Level level, BlockPos pos, BlockState originalState) {
        BlockState currentState = level.getBlockState(pos);
        CropStateChangeEvent.Post event = new CropStateChangeEvent.Post(level, pos, originalState, currentState);
        event.post();
    }

    public static LivingChangeTargetEvent onLivingChangeTarget(LivingEntity entity, @Nullable LivingEntity originalTarget, LivingChangeTargetEvent.ILivingTargetType targetType) {
        LivingChangeTargetEvent event = new LivingChangeTargetEvent(entity, originalTarget, targetType);
        event.post();
        return event;
    }
}
