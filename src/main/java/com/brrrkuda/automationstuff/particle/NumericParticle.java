package com.brrrkuda.automationstuff.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.Random;

public class NumericParticle extends TextureSheetParticle {
    public float startQuadSize;
    public float rot;
    public static Random random = new Random();

    protected NumericParticle(ClientLevel level, double xCoord, double yCoord, double zCoord,
                              SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.8F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 1.25F;
        startQuadSize = this.quadSize;
        this.lifetime = 20;
        this.setSpriteFromAge(spriteSet);
        rot = random.nextFloat(0f, 2f)-1f;
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
        this.oRoll = this.roll;
        roll += rot/5f;
    }

    private void fadeOut() {
        this.quadSize = (-(1/(float)lifetime) * age + 1)*startQuadSize;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new NumericParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
