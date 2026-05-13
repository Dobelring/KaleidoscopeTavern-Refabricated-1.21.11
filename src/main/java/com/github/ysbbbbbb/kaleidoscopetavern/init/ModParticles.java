package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public final class ModParticles {
    public static final SimpleParticleType WATER_TAP_DRIP = FabricParticleTypes.simple(true);
    public static final SimpleParticleType LAVA_TAP_DRIP = FabricParticleTypes.simple(true);

    public static void registerParticles() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "water_tap_drip"), WATER_TAP_DRIP);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "lava_tap_drip"), LAVA_TAP_DRIP);
    }

}
