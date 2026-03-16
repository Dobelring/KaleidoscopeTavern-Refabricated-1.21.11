package com.github.ysbbbbbb.kaleidoscopetavern.client.render.block;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco.BarStoolBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.BarStoolBodyModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.renderstate.BarStoolBlockEntityRenderState;
import com.github.ysbbbbbb.kaleidoscopetavern.entity.SitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BarStoolBlockEntityRender implements BlockEntityRenderer<BarStoolBlockEntity, BarStoolBlockEntityRenderState> {
    private static final float MIN_SMOOTH_FACTOR = 0.20F;
    private static final float MAX_SMOOTH_FACTOR = 0.58F;
    private static final float SENSITIVITY_SCALE = 0.007F;
    private static final float PASSENGER_VELOCITY_BLEND = 0.22F;
    private static final float PASSENGER_PREDICT_TICKS = 0.18F;
    private static final float PASSENGER_MIN_STEP = 2.0F;
    private static final float PASSENGER_STEP_SCALE = 0.26F;
    private final BarStoolBodyModel model;

    public BarStoolBlockEntityRender(BlockEntityRendererProvider.Context context) {
        this.model = new BarStoolBodyModel(context.bakeLayer(BarStoolBodyModel.LAYER_LOCATION));
    }

    @Override
    public BarStoolBlockEntityRenderState createRenderState() {
        return new BarStoolBlockEntityRenderState();
    }

    private static Identifier getTexture(DyeColor color) {
        return Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "textures/entity/deco/bar_stool/"+ color.getName() +".png");
    }

    @Override
    public void extractRenderState(BarStoolBlockEntity blockEntity, BarStoolBlockEntityRenderState blockEntityRenderState, float f, @NonNull Vec3 vec3, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, blockEntityRenderState, f, vec3, crumblingOverlay);
        float cachedRot = blockEntity.getCachedRot();
        boolean hadPassenger = blockEntityRenderState.hasPassenger;
        LivingEntity passenger = resolvePassenger(blockEntity);
        blockEntityRenderState.hasPassenger = passenger != null;
        float sampleTime = getSampleTime(blockEntity, f);
        if (passenger != null) {
            blockEntityRenderState.passengerBodyRot = Mth.wrapDegrees(passenger.yBodyRot);
            if (hadPassenger && blockEntityRenderState.hasLastSample) {
                float dt = Math.max(0.001F, sampleTime - blockEntityRenderState.lastSampleTime);
                float delta = Mth.wrapDegrees(blockEntityRenderState.passengerBodyRot - blockEntityRenderState.lastPassengerBodyRot);
                float instantVelocity = delta / dt;
                blockEntityRenderState.passengerBodyRotVelocity = Mth.lerp(PASSENGER_VELOCITY_BLEND, blockEntityRenderState.passengerBodyRotVelocity, instantVelocity);
            } else {
                blockEntityRenderState.passengerBodyRotVelocity = 0.0F;
            }
            blockEntityRenderState.lastPassengerBodyRot = blockEntityRenderState.passengerBodyRot;
            blockEntityRenderState.lastSampleTime = sampleTime;
            blockEntityRenderState.hasLastSample = true;
        } else {
            blockEntityRenderState.hasLastSample = false;
            blockEntityRenderState.passengerBodyRotVelocity = 0.0F;
        }
        float targetRot = blockEntityRenderState.hasPassenger ? blockEntityRenderState.passengerBodyRot : cachedRot;
        if (!blockEntityRenderState.initialized) {
            blockEntityRenderState.renderRot = targetRot;
            blockEntityRenderState.initialized = true;
        } else if (blockEntityRenderState.hasPassenger) {
            float predictedRot = Mth.wrapDegrees(targetRot + blockEntityRenderState.passengerBodyRotVelocity * PASSENGER_PREDICT_TICKS);
            blockEntityRenderState.renderRot = smoothPassengerRotation(blockEntityRenderState.renderRot, predictedRot, blockEntityRenderState.passengerBodyRotVelocity);
        } else {
            float delta = Mth.degreesDifferenceAbs(blockEntityRenderState.renderRot, targetRot);
            float smoothFactor = Mth.clamp(MIN_SMOOTH_FACTOR + delta * SENSITIVITY_SCALE, MIN_SMOOTH_FACTOR, MAX_SMOOTH_FACTOR);
            blockEntityRenderState.renderRot = Mth.rotLerp(smoothFactor, blockEntityRenderState.renderRot, targetRot);
        }
        blockEntityRenderState.color = blockEntity.getColor();
    }

    @Override
    public void submit(BarStoolBlockEntityRenderState blockEntityRenderState, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(180.0F));
        float bodyRot = blockEntityRenderState.renderRot;
        BarStoolBodyModel.State state = new BarStoolBodyModel.State(bodyRot + 180.0F);
        submitNodeCollector.submitModel(
                this.model,
                state,
                poseStack,
                RenderTypes.entityCutoutNoCull(getTexture(blockEntityRenderState.color)),
                blockEntityRenderState.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0,
                null
        );
        poseStack.popPose();
    }

    private static float getSampleTime(BarStoolBlockEntity blockEntity, float partialTick) {
        Level level = blockEntity.getLevel();
        if (level == null) {
            return partialTick;
        }
        return level.getGameTime() + partialTick;
    }

    private static float smoothPassengerRotation(float currentRot, float targetRot, float angularVelocity) {
        float velocityAbs = Math.abs(angularVelocity);
        float smoothFactor = Mth.clamp(0.24F + velocityAbs * 0.018F, 0.24F, 0.70F);
        float lerped = Mth.rotLerp(smoothFactor, currentRot, targetRot);
        float rawStep = Mth.wrapDegrees(lerped - currentRot);
        float maxStep = PASSENGER_MIN_STEP + velocityAbs * PASSENGER_STEP_SCALE;
        float clampedStep = Mth.clamp(rawStep, -maxStep, maxStep);
        return Mth.wrapDegrees(currentRot + clampedStep);
    }

    private static @Nullable LivingEntity resolvePassenger(BarStoolBlockEntity blockEntity) {
        Level level = blockEntity.getLevel();
        if (level == null) {
            return null;
        }
        for (SitEntity sitEntity : level.getEntitiesOfClass(SitEntity.class, new AABB(blockEntity.getBlockPos()))) {
            if (!sitEntity.isAlive() || sitEntity.getPassengers().isEmpty()) {
                continue;
            }
            Entity entity = sitEntity.getFirstPassenger();
            if (entity instanceof LivingEntity livingEntity) {
                return livingEntity;
            }
        }
        return null;
    }
}
