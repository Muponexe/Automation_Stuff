package com.brrrkuda.automationstuff.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(PostChain.class)
public class PostChainMixin {
    @Shadow
    private final List<PostPass> passes = Lists.newArrayList();

    @Unique
    public List<PostPass> getPasses () {
        return this.passes;
    }
}