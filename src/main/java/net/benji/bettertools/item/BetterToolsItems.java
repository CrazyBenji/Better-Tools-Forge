package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class BetterToolsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BetterToolsForge.MOD_ID);

    public static final RegistryObject<Item> IRON_HAMMER = registerItem("iron_hammer",
            properties -> new HammerItem(ToolMaterial.IRON, properties));
    public static final RegistryObject<Item> GOLDEN_HAMMER = registerItem("golden_hammer",
            properties -> new HammerItem(ToolMaterial.GOLD, properties));
    public static final RegistryObject<Item> DIAMOND_HAMMER = registerItem("diamond_hammer",
            properties -> new HammerItem(ToolMaterial.DIAMOND, properties));
    public static final RegistryObject<Item> NETHERITE_HAMMER = registerItem("netherite_hammer",
            properties -> new HammerItem(ToolMaterial.NETHERITE, properties));

    public static final RegistryObject<Item> IRON_SCYTHE = registerItem("iron_scythe",
            properties -> new ScytheItem(ToolMaterial.IRON, properties));
    public static final RegistryObject<Item> GOLDEN_SCYTHE = registerItem("golden_scythe",
            properties -> new ScytheItem(ToolMaterial.GOLD, properties));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerItem("diamond_scythe",
            properties -> new ScytheItem(ToolMaterial.DIAMOND, properties));
    public static final RegistryObject<Item> NETHERITE_SCYTHE = registerItem("netherite_scythe",
            properties -> new ScytheItem(ToolMaterial.NETHERITE, properties));

    public static final RegistryObject<Item> IRON_PAXEL = registerItem("iron_paxel",
            properties -> new PaxelItem(ToolMaterial.IRON, properties));
    public static final RegistryObject<Item> GOLDEN_PAXEL = registerItem("golden_paxel",
            properties -> new PaxelItem(ToolMaterial.GOLD, properties));
    public static final RegistryObject<Item> DIAMOND_PAXEL = registerItem("diamond_paxel",
            properties -> new PaxelItem(ToolMaterial.DIAMOND, properties));
    public static final RegistryObject<Item> NETHERITE_PAXEL = registerItem("netherite_paxel",
            properties -> new PaxelItem(ToolMaterial.NETHERITE, properties));

    public static final RegistryObject<Item> IRON_LUMBER_AXE = registerItem("iron_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.IRON, properties, 32));
    public static final RegistryObject<Item> GOLDEN_LUMBER_AXE = registerItem("golden_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.GOLD, properties, 64));
    public static final RegistryObject<Item> DIAMOND_LUMBER_AXE = registerItem("diamond_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.DIAMOND, properties, 96));
    public static final RegistryObject<Item> NETHERITE_LUMBER_AXE = registerItem("netherite_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.NETHERITE, properties, 128));

    public static final RegistryObject<Item> GLASS_CHIPPER = registerItem("glass_chipper",
            properties -> new GlassChipperItem(ToolMaterial.IRON, properties));

    public static final RegistryObject<Item> BEDROCK_SMASHER = registerItem("bedrock_smasher",
            properties -> new BlockSmasherItem(() -> Blocks.BEDROCK, BetterToolsBlocks.SMASHED_BEDROCK, properties.durability(16)));

    public static RegistryObject<Item> registerItem(String name, Function<Item.Properties, Item> function) {
        return ITEMS.register(name, () -> function.apply(new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BetterToolsForge.MOD_ID, name)))));
    }

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
