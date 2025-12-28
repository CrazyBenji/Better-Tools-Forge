package net.benji.bettertools;

import com.mojang.logging.LogUtils;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.data.loot.BetterToolsLootModifiers;
import net.benji.bettertools.enchantment.BetterToolsEnchantments;
import net.benji.bettertools.item.BetterToolsCreativeModeTabs;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BetterToolsForge.MOD_ID)
public class BetterToolsForge
{
    public static final String MOD_ID = "bettertools";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BetterToolsForge(FMLJavaModLoadingContext loadingContext) {
        IEventBus modEventBus = loadingContext.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BetterToolsBlocks.registerBlocks(modEventBus);
        BetterToolsItems.registerItems(modEventBus);
        BetterToolsCreativeModeTabs.registerCreativeModeTabs(modEventBus);
        BetterToolsEnchantments.registerEnchantment(modEventBus);
        BetterToolsLootModifiers.registerLootModifiers(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
