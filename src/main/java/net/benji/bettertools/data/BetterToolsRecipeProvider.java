package net.benji.bettertools.data;

import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BetterToolsRecipeProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
        super(registries, recipeOutput);
    }

    public static class RecipeProviderRunner extends RecipeProvider.Runner {
        public RecipeProviderRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput output) {
            return new BetterToolsRecipeProvider(provider, output);
        }

        @Override
        public @NotNull String getName() {
            return "BetterToolsRecipeProvider";
        }
    }

    public void generatePaxelRecipe(Item pickaxe, Item axe, Item shovel, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("abc")
                .pattern(" s ")
                .pattern(" s ")
                .define('a', pickaxe)
                .define('b', axe)
                .define('c', shovel)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(pickaxe), has(pickaxe))
                .save(this.output);
    }

    public void generateHammerRecipe(Item ingot, Item block, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mbm")
                .pattern("msm")
                .pattern(" s ")
                .define('m', ingot)
                .define('b', block)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(this.output);
    }

    public void generateSickleRecipe(Item ingot, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mmm")
                .pattern(" s ")
                .pattern("s  ")
                .define('m', ingot)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(this.output);
    }

    public void generateLumberAxeRecipe(Item ingot, Item block, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("ib")
                .pattern("is")
                .pattern(" s")
                .define('i', ingot)
                .define('b', block)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(this.output);
    }

    @Override
    public void buildRecipes() {
        generateHammerRecipe(Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_HAMMER.get());
        generateHammerRecipe(Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_HAMMER.get());
        generateHammerRecipe(Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_HAMMER.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_HAMMER.get());

        generateSickleRecipe(Items.IRON_INGOT, BetterToolsItems.IRON_SCYTHE.get());
        generateSickleRecipe(Items.GOLD_INGOT, BetterToolsItems.GOLDEN_SCYTHE.get());
        generateSickleRecipe(Items.DIAMOND, BetterToolsItems.DIAMOND_SCYTHE.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_SCYTHE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_SCYTHE.get());

        generatePaxelRecipe(Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, BetterToolsItems.IRON_PAXEL.get());
        generatePaxelRecipe(Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, BetterToolsItems.GOLDEN_PAXEL.get());
        generatePaxelRecipe(Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, BetterToolsItems.DIAMOND_PAXEL.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_PAXEL.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_PAXEL.get());

        generateLumberAxeRecipe(Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_LUMBER_AXE.get());
        generateLumberAxeRecipe(Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_LUMBER_AXE.get());
        generateLumberAxeRecipe(Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_LUMBER_AXE.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_LUMBER_AXE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_LUMBER_AXE.get());

        shaped(RecipeCategory.TOOLS, BetterToolsItems.GLASS_CHIPPER.get(), 1)
                .pattern(" i")
                .pattern("s ")
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output);

        shaped(RecipeCategory.TOOLS, BetterToolsItems.BEDROCK_SMASHER.get(), 1)
                .pattern("ini")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', Items.NETHERITE_INGOT)
                .define('n', Items.NETHER_STAR)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(this.output);
    }
}
