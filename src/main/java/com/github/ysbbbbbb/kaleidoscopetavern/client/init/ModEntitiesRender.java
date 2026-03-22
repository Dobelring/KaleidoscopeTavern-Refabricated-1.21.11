package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.model.brew.BarrelModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.BarStoolBodyModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.LargeChalkboardModel;
import com.github.ysbbbbbb.kaleidoscopetavern.client.model.deco.SmallChalkboardModel;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;

@Environment(EnvType.CLIENT)
public class ModEntitiesRender {
    public static void init() {
        EntityRenderers.register(ModEntities.SIT, NoopRenderer::new);
        ModelLayerRegistry.registerModelLayer(SmallChalkboardModel.LAYER_LOCATION, SmallChalkboardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(LargeChalkboardModel.LAYER_LOCATION, LargeChalkboardModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(BarrelModel.LAYER_LOCATION, BarrelModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(BarStoolBodyModel.LAYER_LOCATION, BarStoolBodyModel::createBodyLayer);
    }
}
