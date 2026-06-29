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
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import org.jetbrains.annotations.Contract;

@Environment(EnvType.CLIENT)
public final class ClientSetupEvent {

    @Contract
    public static void init() {
        BlockEntityRenderers.register(ModBlocks.CHALKBOARD_BE, ChalkboardBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.SANDWICH_BOARD_BE, SandwichBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.PRESSING_TUB_BE, PressingTubBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.BARREL_BE, BarrelBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.BAR_CABINET_BE, BarCabinetBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.BAR_STOOL_BE, BarStoolBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.CELLAR_CABINET_BE, CellarCabinetBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.TILTED_RACK_BE, TiltedRackBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.CIRCULAR_RACK_BE, CircularRackBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.HOLDER_BE, HolderBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.SHAKER_BE, ShakerBlockEntityRender::new);
        BlockEntityRenderers.register(ModBlocks.GLASSWARE_HOLDER_BE, GlasswareHolderBlockEntityRender::new);
        EntityRenderers.register(ModEntities.THROWN_MOLOTOV, ThrownMolotovRenderer::new);
        EntityRenderers.register(ModEntities.SIT, NoopRenderer::new);
        ModelLayerRegistry.registerModelLayer(SmallChalkboardModel.LAYER_LOCATION, SmallChalkboardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(LargeChalkboardModel.LAYER_LOCATION, LargeChalkboardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(BarrelModel.LAYER_LOCATION, BarrelModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(BarStoolBodyModel.LAYER_LOCATION, BarStoolBodyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(ShakerModel.LAYER_LOCATION, ShakerModel::createBodyLayer);
    }
}
