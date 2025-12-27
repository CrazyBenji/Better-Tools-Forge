package net.benji.bettertools;

import net.benji.bettertools.data.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = BetterToolsForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BetterToolsRecipeProviderForge(packOutput));
        generator.addProvider(event.includeServer(), new BetterToolsGlobalLootModifierProviderForge(packOutput));
        generator.addProvider(event.includeServer(), new BetterToolsAdvancementProviderForge(packOutput, lookupProvider, existingFileHelper));

        BetterToolsBlockTagProviderForge blockTagGenerator = generator.addProvider(event.includeServer(),
                new BetterToolsBlockTagProviderForge(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new BetterToolsItemTagProviderForge(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeClient(), new BetterToolsModelProviderForge(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new BetterToolsBlockStateProviderForge(packOutput, existingFileHelper));
    }
}
