package com.brrrkuda.automationstuff.render;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.mojang.blaze3d.pipeline.MainTarget;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.util.SequencedMap;

@EventBusSubscriber(value = Dist.CLIENT, modid = AutomationStuff.MOD_ID)
public class RenderEvents {
    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (Minecraft.useShaderTransparency()) {
            if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_LEVEL)) {
                RenderSystem.getModelViewStack().pushMatrix().set(RenderHandler.matrix4f);
                RenderSystem.applyModelViewMatrix();
                RenderSystem.setShaderFogStart(RenderHandler.fogStart);
                doEffectRendering(event);
                FogRenderer.setupNoFog();
                RenderSystem.getModelViewStack().popMatrix();
                RenderSystem.applyModelViewMatrix();
            }
        } else {
            if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_WEATHER)) {
                doEffectRendering(event);
            }
        }
    }
    private static void doEffectRendering(RenderLevelStageEvent event) {
        RenderSystem.depthMask(true);
        createShatterInsideBufferSource().endBatch();
        getNumericTarget().clear(Minecraft.ON_OSX);
        getNumericTarget().copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
        getNumericTarget().bindWrite(false);
        createShatterOutlineBufferSource().endBatch(ModRenderTypes.NUMERIC_PARTICLE);
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        RenderSystem.depthMask(false);
    }
    private static RenderTarget numericTarget;
    public static RenderTarget getNumericTarget() {
        if (numericTarget == null) {
            numericTarget = new MainTarget(Minecraft.getInstance().getMainRenderTarget().width, Minecraft.getInstance().getMainRenderTarget().height);
            numericTarget.setClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
        return numericTarget;
    }

    static MultiBufferSource.BufferSource shatterOutlineBufferSource = null;
    public static MultiBufferSource.BufferSource createShatterOutlineBufferSource() {
        if (shatterOutlineBufferSource == null) {
            SequencedMap<RenderType, ByteBufferBuilder> buffers = new Object2ObjectLinkedOpenHashMap<>();
            buffers.put(ModRenderTypes.NUMERIC_PARTICLE, new ByteBufferBuilder(ModRenderTypes.NUMERIC_PARTICLE.bufferSize));
            shatterOutlineBufferSource = MultiBufferSource.immediateWithBuffers(buffers, new ByteBufferBuilder(256));
        }
        return shatterOutlineBufferSource;
    }

    static MultiBufferSource.BufferSource shatterInsideBufferSource = null;
    public static MultiBufferSource.BufferSource createShatterInsideBufferSource() {
        if (shatterInsideBufferSource == null) {
            SequencedMap<RenderType, ByteBufferBuilder> buffers = new Object2ObjectLinkedOpenHashMap<>();
            buffers.put(ModRenderTypes.NUMERIC_PARTICLE, new ByteBufferBuilder(ModRenderTypes.NUMERIC_PARTICLE.bufferSize));
            shatterInsideBufferSource = MultiBufferSource.immediateWithBuffers(buffers, new ByteBufferBuilder(256));
        }
        return shatterInsideBufferSource;
    }
}