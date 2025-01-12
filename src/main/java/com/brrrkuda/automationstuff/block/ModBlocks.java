package com.brrrkuda.automationstuff.block;

import com.brrrkuda.automationstuff.AutomationStuff;
import com.brrrkuda.automationstuff.block.custom.CustomBlock;
import com.brrrkuda.automationstuff.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.time.temporal.TemporalUnit;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AutomationStuff.MOD_ID);

    public static final DeferredBlock<Block> SOME_BLOCK = registerBlock("some_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1).requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> CUSTOM_BLOCK = registerBlock("custom_block",
            () -> new CustomBlock(BlockBehaviour.Properties.of().strength(1).requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static <T extends Block> DeferredBlock<T> registerBlock (String name, Supplier<T> block) {
        DeferredBlock<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result);
        return result;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
