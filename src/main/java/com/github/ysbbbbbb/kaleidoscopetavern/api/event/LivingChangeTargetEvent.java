package com.github.ysbbbbbb.kaleidoscopetavern.api.event;

import com.github.ysbbbbbb.kaleidoscopetavern.event.ChangeTargetEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.util.event.CancellableEvent;
import net.minecraft.world.entity.LivingEntity;

public class LivingChangeTargetEvent extends CancellableEvent {
    private final LivingEntity entity;
    private final ILivingTargetType targetType;
    private final LivingEntity originalTarget;
    private LivingEntity newTarget;


    public LivingChangeTargetEvent(LivingEntity entity, LivingEntity originalTarget, ILivingTargetType targetType)
    {
        this.entity = entity;
        this.originalTarget = originalTarget;
        this.newTarget = originalTarget;
        this.targetType = targetType;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public LivingEntity getNewTarget()
    {
        return newTarget;
    }

    public void setNewTarget(LivingEntity newTarget)
    {
        this.newTarget = newTarget;
    }

    public ILivingTargetType getTargetType()
    {
        return targetType;
    }

    public LivingEntity getOriginalTarget()
    {
        return originalTarget;
    }

    public static void register() {
        CALLBACK.register(event -> {
            if (event instanceof LivingChangeTargetEvent livingChangeTargetEvent) {
                ChangeTargetEvent.onTarget(livingChangeTargetEvent);
            }
        });
    }

    @Override
    public void post() {
        CALLBACK.invoker().post(this);
    }

    public interface ILivingTargetType { }

    public enum LivingTargetType implements ILivingTargetType
    {

        MOB_TARGET,

        BEHAVIOR_TARGET;
    }
}
