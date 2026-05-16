package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.TapDripParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

@Environment(EnvType.CLIENT)
public final class ModParticleFactoryRegistry {

    public static void init() {
        ParticleProviderRegistry.getInstance().register(ModParticles.WATER_TAP_DRIP, TapDripParticle.WaterProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.LAVA_TAP_DRIP, TapDripParticle.LavaProvider::new);
    }
}
