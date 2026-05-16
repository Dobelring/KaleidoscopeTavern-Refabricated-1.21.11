package com.github.ysbbbbbb.kaleidoscopetavern.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class TapDripParticle extends DripParticle {
    private final ParticleOptions fallingParticle;

    public TapDripParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions fallingParticle, TextureAtlasSprite sprite) {
        super(pLevel, pX, pY, pZ, pType, sprite);
        this.fallingParticle = fallingParticle;
        this.gravity *= 0.02F;
        this.lifetime = 18;
    }


    public static class WaterProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public WaterProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        @Override
        @SuppressWarnings("unused")
        public @Nullable Particle createParticle(
                @NonNull SimpleParticleType pType, @NonNull ClientLevel level,
                double pX, double pY, double pZ,
                double pXSpeed, double pYSpeed, double pZSpeed, @NonNull RandomSource randomSource
        ) {
            DripParticle dripparticle = new TapDripParticle(level, pX, pY, pZ, Fluids.WATER, ParticleTypes.FALLING_DRIPSTONE_WATER, this.sprites.get(randomSource));
            dripparticle.setColor(0.2F, 0.3F, 1.0F);
            dripparticle.setSpriteFromAge(this.sprites);
            return dripparticle;
        }
    }



    public static class LavaProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public LavaProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        @Override
        @SuppressWarnings("unused")
        public @Nullable Particle createParticle(
                @NonNull SimpleParticleType type, @NonNull ClientLevel level,
                double pX, double pY, double pZ,
                double pXSpeed, double pYSpeed, double pZSpeed, @NonNull RandomSource randomSource
        ) {
            TapDripParticle dripParticle = new TapDripParticle(level, pX, pY, pZ, Fluids.LAVA, ParticleTypes.FALLING_DRIPSTONE_LAVA, this.sprites.get(randomSource));
            dripParticle.setSpriteFromAge(this.sprites);
            return dripParticle;
        }
    }



    @Override
    protected void preMoveUpdate() {
        super.preMoveUpdate();
        this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
    }

    @Override
    protected void postMoveUpdate() {
        this.xd *= 0.02;
        this.yd *= 0.02;
        this.zd *= 0.02;
    }
}
