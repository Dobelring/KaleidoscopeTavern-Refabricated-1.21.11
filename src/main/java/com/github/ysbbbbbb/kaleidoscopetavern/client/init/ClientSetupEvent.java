package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.model.brew.BarrelModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.BarStoolBodyModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.LargeChalkboardModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.SmallChalkboardModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.mixology.ShakerModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.block.*;
import com.github.ysbbbbbb.kaleidoscopetavern.client.render.entity.ThrownMolotovRenderer;
import com.github.ysbbbbbb.kaleidoscopetavern.entity.ThrownMolotovEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;

@Environment(EnvType.CLIENT)
public final class ClientSetupEvent {

    public static void init() {
        BlockEntityRenderers.register(ModBlocks.BAR_CABINET_BE, BarCabinetBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.CHALKBOARD_BE, ChalkboardBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.SANDWICH_BOARD_BE, SandwichBoardBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.PRESSING_TUB_BE, PressingTubBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.BARREL_BE, BarrelBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.BAR_STOOL_BE, BarStoolBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.TILTED_RACK_BE, TiltedRackBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.CIRCULAR_RACK_BE, CircularRackBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.CELLAR_CABINET_BE, CellarCabinetBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.HOLDER_BE, HolderBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.SHAKER_BE, ShakerBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.GLASSWARE_HOLDER_BE, GlasswareHolderBlockEntityRender::new);
        EntityRendererRegistry.register(ThrownMolotovEntity.TYPE, ThrownMolotovRenderer::new);
        EntityRendererRegistry.register(ModEntities.SIT, NoopRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SmallChalkboardModel.LAYER_LOCATION, SmallChalkboardModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(LargeChalkboardModel.LAYER_LOCATION, LargeChalkboardModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BarrelModel.LAYER_LOCATION, BarrelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BarStoolBodyModel.LAYER_LOCATION, BarStoolBodyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ShakerModel.LAYER_LOCATION, ShakerModel::createBodyLayer);
    }
}
