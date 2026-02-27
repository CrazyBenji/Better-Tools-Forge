package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterToolsForge.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BETTER_TOOLS_TAB = CREATIVE_MODE_TABS.register("bettertools_itemgroup",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BetterToolsItems.IRON_HAMMER.get()))
                    .title(Component.translatable("itemgroup.bettertools"))
                    .displayItems((pParameters, output) -> {
                        output.accept(BetterToolsItems.IRON_HAMMER.get());
                        output.accept(BetterToolsItems.GOLDEN_HAMMER.get());
                        output.accept(BetterToolsItems.DIAMOND_HAMMER.get());
                        output.accept(BetterToolsItems.NETHERITE_HAMMER.get());

                        output.accept(BetterToolsItems.IRON_PAXEL.get());
                        output.accept(BetterToolsItems.GOLDEN_PAXEL.get());
                        output.accept(BetterToolsItems.DIAMOND_PAXEL.get());
                        output.accept(BetterToolsItems.NETHERITE_PAXEL.get());

                        output.accept(BetterToolsItems.IRON_SCYTHE.get());
                        output.accept(BetterToolsItems.GOLDEN_SCYTHE.get());
                        output.accept(BetterToolsItems.DIAMOND_SCYTHE.get());
                        output.accept(BetterToolsItems.NETHERITE_SCYTHE.get());

                        output.accept(BetterToolsItems.IRON_LUMBER_AXE.get());
                        output.accept(BetterToolsItems.GOLDEN_LUMBER_AXE.get());
                        output.accept(BetterToolsItems.DIAMOND_LUMBER_AXE.get());
                        output.accept(BetterToolsItems.NETHERITE_LUMBER_AXE.get());

                        output.accept(BetterToolsItems.IRON_MACHETE.get());
                        output.accept(BetterToolsItems.GOLDEN_MACHETE.get());
                        output.accept(BetterToolsItems.DIAMOND_MACHETE.get());
                        output.accept(BetterToolsItems.NETHERITE_MACHETE.get());

                        output.accept(BetterToolsItems.GLASS_CHIPPER.get());

                        output.accept(BetterToolsItems.BEDROCK_SMASHER.get());

                        output.accept(BetterToolsBlocks.SMASHED_BEDROCK.get());
                    })
                    .build());

    public static void registerCreativeModeTabs(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
