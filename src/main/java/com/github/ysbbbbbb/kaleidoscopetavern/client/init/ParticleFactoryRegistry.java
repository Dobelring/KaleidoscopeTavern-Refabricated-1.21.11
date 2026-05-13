package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.TapDripParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;

@SuppressWarnings("UnstableApiUsage")
@Environment(EnvType.CLIENT)
public class ParticleFactoryRegistry {

    public static void init() {
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.WATER_TAP_DRIP, TapDripParticle::createWaterTapDripParticle);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.LAVA_TAP_DRIP, TapDripParticle::createLavaTapDripParticle);
    }
}
