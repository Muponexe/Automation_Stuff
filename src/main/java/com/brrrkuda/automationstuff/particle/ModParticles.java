package com.brrrkuda.automationstuff.particle;

import com.brrrkuda.automationstuff.AutomationStuff;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE,
            AutomationStuff.MOD_ID);
    public static final Supplier<SimpleParticleType> NUMERIC = register("numeric", () -> new SimpleParticleType(false));

    private static <T extends ParticleType<?>> Supplier<T> register(final String name, final Supplier<T> particle) {
        return PARTICLE_TYPES.register(name, particle);
    }
}
