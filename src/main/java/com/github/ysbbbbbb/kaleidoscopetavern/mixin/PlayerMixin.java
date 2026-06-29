package com.github.ysbbbbbb.kaleidoscopetavern.mixin;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.PlayerTickEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickPre(CallbackInfo ci) {
        PlayerTickEvents.START.invoker().onStartOfPlayerTick((Player) (Object) this);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickPost(CallbackInfo ci) {
        PlayerTickEvents.END.invoker().onEndOfPlayerTick((Player) (Object) this);
    }
}
