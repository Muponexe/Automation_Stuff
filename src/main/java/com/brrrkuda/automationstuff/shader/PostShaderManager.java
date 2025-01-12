package com.brrrkuda.automationstuff.shader;

import com.brrrkuda.automationstuff.AutomationStuff;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT, modid = AutomationStuff.MOD_ID)
public class PostShaderManager
{
    public static List<PostShaderInstance> instances = new ArrayList<>();
    public static void addShader(PostShaderInstance instance) {
        instances.add(instance);
    }
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        for (PostShaderInstance i : PostShaderManager.instances) {
            i.tick();
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderLevelStage(RenderLevelStageEvent event) throws IOException {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_LEVEL)) {
            for (PostShaderInstance i : instances) {
                i.process();
            }
        }
    }
}
