package com.github.ysbbbbbb.kaleidoscopetavern.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

@Environment(EnvType.CLIENT)
public class TapDripParticle extends DripParticle {
    private final ParticleOptions fallingParticle;

    public TapDripParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions fallingParticle) {
        super(pLevel, pX, pY, pZ, pType);
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
        public TextureSheetParticle createParticle(
                SimpleParticleType pType, ClientLevel level,
                double pX, double pY, double pZ,
                double pXSpeed, double pYSpeed, double pZSpeed
        ) {
            DripParticle dripparticle = new TapDripParticle(level, pX, pY, pZ, Fluids.WATER, ParticleTypes.FALLING_DRIPSTONE_WATER);
            dripparticle.setColor(0.2F, 0.3F, 1.0F);
            dripparticle.pickSprite(this.sprites);
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
        public TextureSheetParticle createParticle(
                SimpleParticleType type, ClientLevel level,
                double pX, double pY, double pZ,
                double pXSpeed, double pYSpeed, double pZSpeed
        ) {
            TapDripParticle dripParticle = new TapDripParticle(level, pX, pY, pZ, Fluids.LAVA, ParticleTypes.FALLING_DRIPSTONE_LAVA);
            dripParticle.pickSprite(this.sprites);
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

