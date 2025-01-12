package com.brrrkuda.automationstuff.render;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(value = Dist.CLIENT, modid = AutomationStuff.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModCoreShaders {
    public static ShaderInstance NUMERIC_PARTICLE;
    public static ShaderInstance getNumericParticle() {
        return NUMERIC_PARTICLE;
    }
    @SubscribeEvent
    public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                ResourceLocation.fromNamespaceAndPath(
                AutomationStuff.MOD_ID,
                "shatter"), DefaultVertexFormat.PARTICLE), shader -> { NUMERIC_PARTICLE = shader; });
    }
}