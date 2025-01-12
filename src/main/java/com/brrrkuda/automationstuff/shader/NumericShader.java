package com.brrrkuda.automationstuff.shader;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.brrrkuda.automationstuff.render.RenderEvents;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;

public class NumericShader extends PostShaderInstance {
    @Override
    public ResourceLocation getShaderLocation() {
        return ResourceLocation.fromNamespaceAndPath(
                AutomationStuff.MOD_ID,
                "shaders/post/shatter_post.json");
    }

    @Override
    public void setUniforms(PostPass instance) {
        super.setUniforms(instance);
        instance.getEffect().setSampler("ShatterSampler", RenderEvents.getNumericTarget()::getColorTextureId);
    }
}
