package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.TapDripParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;

@Environment(EnvType.CLIENT)
public final class ModParticleFactoryRegistry {

    public static void init() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.WATER_TAP_DRIP, TapDripParticle.WaterProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.LAVA_TAP_DRIP, TapDripParticle.LavaProvider::new);
    }
}
