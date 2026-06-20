package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.*;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;
import net.minecraft.client.particle.CherryParticle;

@SuppressWarnings("UnstableApiUsage")
@Environment(EnvType.CLIENT)
public final class ModParticleFactoryRegistry {

    public static void init() {
        // 龙头粒子
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.WATER_TAP_DRIP, TapDripParticle.WaterProvider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.LAVA_TAP_DRIP, TapDripParticle.LavaProvider::new);

        // 小型香薰粒子
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.SAKURA_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.PINE_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.GINKGO_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.SPORE_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.CATNIP_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.SNOW_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.BUTTERFLY_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.FIREFLY_INCENSE_PARTICLE, IncenseParticle.Provider::new);

        // 大型香薰粒子
        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.PINE_INCENSE_LARGE_PARTICLE, spriteSet ->
                (particleOptions, clientLevel, d, e, f, g, h, i)
                        -> new CherryParticle(clientLevel, d, e, f, spriteSet));

        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.GINKGO_INCENSE_LARGE_PARTICLE, spriteSet ->
                (particleOptions, clientLevel, d, e, f, g, h, i) -> {
                    CherryParticle particle = new CherryParticle(clientLevel, d, e, f, spriteSet);
                    particle.scale(1.5f);
                    return particle;
                }
        );

        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.CATNIP_INCENSE_LARGE_PARTICLE, IncenseSuspendedParticle.Provider::new);

        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.SNOW_INCENSE_LARGE_PARTICLE, spriteSet ->
                (particleOptions, clientLevel, d, e, f, g, h, i)
                        -> new CherryParticle(clientLevel, d, e, f, spriteSet));

        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.BUTTERFLY_INCENSE_LARGE_PARTICLE, ButterflyIncenseLargeParticle.Provider::new);

        ParticleFactoryRegistryImpl.INSTANCE.register(ModParticles.FIREFLY_INCENSE_LARGE_PARTICLE, FireflyIncenseLargeParticle.Provider::new);
    }
}
