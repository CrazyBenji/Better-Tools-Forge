package net.benji.bettertools.block;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class BetterToolsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterToolsForge.MOD_ID);

    public static final RegistryObject<Block> SMASHED_BEDROCK = registerBlock("smashed_bedrock",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name,
                () -> function.apply(properties.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BetterToolsForge.MOD_ID, name)))));
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        return registerBlock(name, function, BlockBehaviour.Properties.of());
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        BetterToolsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BetterToolsForge.MOD_ID, name))).useBlockDescriptionPrefix()));
    }

    public static void registerBlocks(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
