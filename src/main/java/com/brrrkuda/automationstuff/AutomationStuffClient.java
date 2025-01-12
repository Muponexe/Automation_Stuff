package com.brrrkuda.automationstuff;

import com.brrrkuda.automationstuff.particle.ModParticles;
import com.brrrkuda.automationstuff.particle.NumericParticle;
import com.brrrkuda.automationstuff.shader.NumericShader;
import com.brrrkuda.automationstuff.shader.PostShaderInstance;
import com.brrrkuda.automationstuff.shader.PostShaderManager;
import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.lwjgl.glfw.GLFW;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = AutomationStuff.MOD_ID)
public class AutomationStuffClient {

    public static PostShaderInstance numericShader;
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event)
    {
        numericShader = new NumericShader();
        PostShaderManager.addShader(numericShader);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.NUMERIC.get(),
                NumericParticle.Provider::new);
    }
}