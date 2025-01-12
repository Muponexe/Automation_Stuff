package com.brrrkuda.automationstuff.shader;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.util.List;

import static com.mojang.blaze3d.platform.GlConst.GL_DRAW_FRAMEBUFFER;

public abstract class PostShaderInstance {

    public abstract ResourceLocation getShaderLocation();
    private PostChain postChain;

    public void process() throws IOException {
        if (postChain == null) {
                postChain = new PostChain(Minecraft.getInstance().getTextureManager(),
                        Minecraft.getInstance().getResourceManager(),
                        Minecraft.getInstance().getMainRenderTarget(),
                        getShaderLocation());

                postChain.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());
        }
        if (postChain != null) {
            beforeProcess();
            postChain.process(Minecraft.getInstance().getTimer().getGameTimeDeltaTicks());
            GlStateManager._glBindFramebuffer(GL_DRAW_FRAMEBUFFER, Minecraft.getInstance().getMainRenderTarget().frameBufferId);
            afterProcess();
        }
    }

    public void tick() {

    }
    public void setUniforms(PostPass instance) {}
    public void beforeProcess() {}
    public void afterProcess() {}
}
