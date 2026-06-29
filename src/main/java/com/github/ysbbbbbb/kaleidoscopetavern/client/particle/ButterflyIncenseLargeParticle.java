package com.github.ysbbbbbb.kaleidoscopetavern.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ButterflyIncenseLargeParticle extends SingleQuadParticle {
    private final SpriteSet sprites;

    protected ButterflyIncenseLargeParticle(ClientLevel level, SpriteSet sprites, double x, double y, double z,
                                            double xSpeed, double ySpeed, double zSpeed, RandomSource randomSource) {
        super(level, x, y - 0.125, z, xSpeed, ySpeed, zSpeed, sprites.first());
        this.sprites = sprites;
        this.setSize(0.01F, 0.01F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = Mth.randomBetweenInclusive(randomSource, 500, 1000);
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.01F;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            int frame = (this.age / 5) % 3;
            this.setSprite(this.sprites.get(frame, 2));
        }
    }

    @Override
    protected @NotNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level,
                                                 double x, double y, double z,
                                                 double xSpeed, double ySpeed, double zSpeed,
                                                 @NotNull RandomSource randomSource) {
            return new ButterflyIncenseLargeParticle(level, this.sprites, x, y, z, 0, -0.8, 0, randomSource);
        }
    }
}
