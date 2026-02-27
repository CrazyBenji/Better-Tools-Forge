package net.benji.bettertools.item.crafting;

import net.benji.bettertools.BetterToolsForge;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BetterToolsForge.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> PAXEL_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            "paxel",
            PaxelRecipe.PaxelRecipeSerializer::new
    );

    public static void registerRecipeSerializers(IEventBus modEventBus) {
        BetterToolsForge.LOGGER.info("Registering recipe serializers for " + BetterToolsForge.MOD_ID);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
