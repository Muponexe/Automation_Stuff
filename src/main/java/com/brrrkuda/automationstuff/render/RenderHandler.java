package com.brrrkuda.automationstuff.render;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

@EventBusSubscriber(value = Dist.CLIENT, modid = AutomationStuff.MOD_ID)
public class RenderHandler {
    public static Matrix4f matrix4f;
    public static float fogStart;
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            matrix4f = new Matrix4f(RenderSystem.getModelViewMatrix());
            fogStart = RenderSystem.getShaderFogStart();
        }
    }
}