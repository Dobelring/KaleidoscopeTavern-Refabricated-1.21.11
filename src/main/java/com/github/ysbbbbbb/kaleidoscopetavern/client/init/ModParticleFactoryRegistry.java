package com.github.ysbbbbbb.kaleidoscopetavern.client.init;

import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.ButterflyIncenseLargeParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.FireflyIncenseLargeParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.IncenseParticle;
import com.github.ysbbbbbb.kaleidoscopetavern.client.particle.IncenseSuspendedParticle;
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
        ParticleProviderRegistry.getInstance().register(ModParticles.SAKURA_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.PINE_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.SPORE_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.CATNIP_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.SNOW_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.BUTTERFLY_INCENSE_PARTICLE, IncenseParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.FIREFLY_INCENSE_PARTICLE, IncenseParticle.Provider::new);

        ParticleProviderRegistry.getInstance().register(ModParticles.PINE_INCENSE_LARGE_PARTICLE, IncenseSuspendedParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.GINKGO_INCENSE_LARGE_PARTICLE, IncenseSuspendedParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.CATNIP_INCENSE_LARGE_PARTICLE, IncenseSuspendedParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.SNOW_INCENSE_LARGE_PARTICLE, IncenseSuspendedParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.BUTTERFLY_INCENSE_LARGE_PARTICLE, ButterflyIncenseLargeParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.FIREFLY_INCENSE_LARGE_PARTICLE, FireflyIncenseLargeParticle.Provider::new);
    }
}
