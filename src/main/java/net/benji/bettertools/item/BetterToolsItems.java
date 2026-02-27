package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BetterToolsForge.MOD_ID);

    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()));

    public static final RegistryObject<Item> IRON_SCYTHE = ITEMS.register("iron_scythe",
            () -> new ScytheItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SCYTHE = ITEMS.register("golden_scythe",
            () -> new ScytheItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SCYTHE = ITEMS.register("netherite_scythe",
            () -> new ScytheItem(Tiers.NETHERITE, new Item.Properties()));

    public static final RegistryObject<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
            () -> new PaxelItem(Tiers.IRON, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
            () -> new PaxelItem(Tiers.GOLD, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            () -> new PaxelItem(Tiers.DIAMOND, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
            () -> new PaxelItem(Tiers.NETHERITE, new Item.Properties()));

    public static final RegistryObject<Item> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe",
            () -> new LumberAxeItem(Tiers.IRON, new Item.Properties(), 32));
    public static final RegistryObject<Item> GOLDEN_LUMBER_AXE = ITEMS.register("golden_lumber_axe",
            () -> new LumberAxeItem(Tiers.GOLD, new Item.Properties(), 64));
    public static final RegistryObject<Item> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe",
            () -> new LumberAxeItem(Tiers.DIAMOND, new Item.Properties(), 96));
    public static final RegistryObject<Item> NETHERITE_LUMBER_AXE = ITEMS.register("netherite_lumber_axe",
            () -> new LumberAxeItem(Tiers.NETHERITE, new Item.Properties(), 128));

    public static final RegistryObject<Item> IRON_MACHETE = ITEMS.register("iron_machete",
            () -> new MacheteItem(Tiers.IRON, new Item.Properties(), 16));
    public static final RegistryObject<Item> GOLDEN_MACHETE = ITEMS.register("golden_machete",
            () -> new MacheteItem(Tiers.GOLD, new Item.Properties(), 32));
    public static final RegistryObject<Item> DIAMOND_MACHETE = ITEMS.register("diamond_machete",
            () -> new MacheteItem(Tiers.DIAMOND, new Item.Properties(), 48));
    public static final RegistryObject<Item> NETHERITE_MACHETE = ITEMS.register("netherite_machete",
            () -> new MacheteItem(Tiers.NETHERITE, new Item.Properties(), 64));

    public static final RegistryObject<Item> GLASS_CHIPPER = ITEMS.register("glass_chipper",
            () -> new GlassChipperItem(Tiers.IRON, new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_SMASHER = ITEMS.register("bedrock_smasher",
            () -> new BlockSmasherItem(() -> Blocks.BEDROCK, BetterToolsBlocks.SMASHED_BEDROCK, new Item.Properties().durability(16)));

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
