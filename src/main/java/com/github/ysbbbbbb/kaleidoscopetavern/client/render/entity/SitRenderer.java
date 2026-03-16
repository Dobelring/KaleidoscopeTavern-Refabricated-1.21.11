package com.github.ysbbbbbb.kaleidoscopetavern.client.render.entity;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.entity.SitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Environment(EnvType.CLIENT)
public class SitRenderer extends EntityRenderer<SitEntity> {
    private static final ResourceLocation EMPTY = new ResourceLocation(KaleidoscopeTavern.MOD_ID, "textures/entity/empty.png");

    public SitRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull SitEntity entitySit, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SitEntity entitySit) {
        return EMPTY;
    }
}
