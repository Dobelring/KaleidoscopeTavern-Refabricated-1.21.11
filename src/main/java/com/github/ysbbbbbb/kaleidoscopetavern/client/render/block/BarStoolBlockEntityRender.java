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
    private static final float MIN_SMOOTH_FACTOR = 0.28F;
    private static final float MAX_SMOOTH_FACTOR = 0.78F;
    private static final float SENSITIVITY_SCALE = 0.012F;
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
        blockEntityRenderState.cachedRot = blockEntity.getCachedRot();
        LivingEntity passenger = resolvePassenger(blockEntity);
        blockEntityRenderState.hasPassenger = passenger != null;
        if (passenger != null) {
            blockEntityRenderState.passengerBodyRot = Mth.wrapDegrees(passenger.yBodyRot);
        }
        float targetRot = blockEntityRenderState.hasPassenger ? blockEntityRenderState.passengerBodyRot : blockEntityRenderState.cachedRot;
        if (!blockEntityRenderState.initialized) {
            blockEntityRenderState.renderRot = targetRot;
            blockEntityRenderState.initialized = true;
        } else if (blockEntityRenderState.hasPassenger) {
            blockEntityRenderState.renderRot = targetRot;
        } else {
            float delta = Mth.degreesDifferenceAbs(blockEntityRenderState.renderRot, targetRot);
            float smoothFactor = Mth.clamp(MIN_SMOOTH_FACTOR + delta * SENSITIVITY_SCALE, MIN_SMOOTH_FACTOR, MAX_SMOOTH_FACTOR);
            blockEntityRenderState.renderRot = Mth.rotLerp(smoothFactor, blockEntityRenderState.renderRot, targetRot);
        }
        blockEntityRenderState.targetRot = blockEntityRenderState.renderRot;
        blockEntityRenderState.color = blockEntity.getColor();
    }

    @Override
    public void submit(BarStoolBlockEntityRenderState blockEntityRenderState, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(180.0F));
        float bodyRot = blockEntityRenderState.targetRot;
        poseStack.mulPose(Axis.YP.rotationDegrees(bodyRot + 180.0F));
        BarStoolBodyModel.State state = new BarStoolBodyModel.State(null, false, blockEntityRenderState.cachedRot, 0.0F);
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
