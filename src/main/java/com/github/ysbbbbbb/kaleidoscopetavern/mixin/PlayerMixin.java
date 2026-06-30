package com.github.ysbbbbbb.kaleidoscopetavern.mixin;

import com.github.ysbbbbbb.kaleidoscopetavern.api.entity.PlayerExtraData;
import com.github.ysbbbbbb.kaleidoscopetavern.api.event.PlayerTickEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerExtraData {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @SuppressWarnings("all")
    @Unique
    private static final EntityDataAccessor<CompoundTag> PERSISTENT_DATA = SynchedEntityData.defineId(Player.class, EntityDataSerializers.COMPOUND_TAG);

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void defineSyncedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(PERSISTENT_DATA, new CompoundTag());
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickPre(CallbackInfo ci) {
        PlayerTickEvents.START.invoker().onStartOfPlayerTick((Player) (Object) this);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickPost(CallbackInfo ci) {
        PlayerTickEvents.END.invoker().onEndOfPlayerTick((Player) (Object) this);
    }

    @Override
    public void kaleidoscope_tavern$setPersistentData(CompoundTag compoundTag) {
        this.entityData.set(PERSISTENT_DATA, compoundTag);
    }

    @Override
    public CompoundTag kaleidoscope_tavern$getPersistentData() {
        return this.entityData.get(PERSISTENT_DATA);
    }
}
