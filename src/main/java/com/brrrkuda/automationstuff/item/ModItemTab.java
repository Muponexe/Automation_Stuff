package com.brrrkuda.automationstuff.item;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.brrrkuda.automationstuff.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItemTab {
    public static final DeferredRegister<CreativeModeTab> ITEM_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AutomationStuff.MOD_ID);

    public static final Supplier<CreativeModeTab> ITEM_TAB = ITEM_TABS.register("items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SOME_BLOCK.asItem()))
                    .title(Component.translatable("creativetab.automationstuff.item_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SOME_BLOCK);
                        output.accept(ModBlocks.CUSTOM_BLOCK);

                    }).build());

    public static void register (IEventBus eventBus) {
        ITEM_TABS.register(eventBus);
    }
}
