package com.brrrkuda.automationstuff.item;

import com.brrrkuda.automationstuff.AutomationStuff;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AutomationStuff.MOD_ID);

    public static final DeferredItem<Item> PHONE = ITEMS.register("phone",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
