package com.brrrkuda.automationstuff.render;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;


import static com.brrrkuda.automationstuff.render.RenderTypeHandler.PARTICLE_SHEET;
import static com.brrrkuda.automationstuff.render.RenderTypeHandler.TRANSPARENCY;

public class ModRenderTypes extends RenderType {
    public ModRenderTypes(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }
    public static final ShaderStateShard NUMERIC_PARTICLE_SHADER = new ShaderStateShard(
            ModCoreShaders::getNumericParticle);
    public static final RenderType NUMERIC_PARTICLE = RenderTypeHandler.registerRenderType(create(AutomationStuff.MOD_ID + ":shatter_particle",
            DefaultVertexFormat.PARTICLE,
            VertexFormat.Mode.QUADS,
            256,
            true,
            true,
            RenderType.CompositeState.builder().setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE).setLightmapState(RenderStateShard.LIGHTMAP).setTransparencyState(TRANSPARENCY).setTextureState(PARTICLE_SHEET).setShaderState(NUMERIC_PARTICLE_SHADER).createCompositeState(false)), true);
}
