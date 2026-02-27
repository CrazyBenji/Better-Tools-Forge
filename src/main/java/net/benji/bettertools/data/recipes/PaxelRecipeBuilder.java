package net.benji.bettertools.data.recipes;

import com.google.gson.JsonObject;
import net.benji.bettertools.item.crafting.BetterToolsRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class PaxelRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private final Ingredient pickaxe;
    private final Ingredient axe;
    private final Ingredient shovel;
    private final Ingredient stick1;
    private final Ingredient stick2;
    private final Item result;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public PaxelRecipeBuilder(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike stick1, ItemLike stick2, ItemLike result) {
        this.pickaxe = Ingredient.of(pickaxe);
        this.axe = Ingredient.of(axe);
        this.shovel = Ingredient.of(shovel);
        this.stick1 = Ingredient.of(stick1);
        this.stick2 = Ingredient.of(stick2);
        this.result = result.asItem();
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike result) {
        return paxel(pickaxe, axe, shovel, Items.STICK, result);
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike sticks, ItemLike result) {
        return paxel(pickaxe, axe, shovel, sticks, sticks, result);
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike stick1, ItemLike stick2, ItemLike result) {
        return new PaxelRecipeBuilder(pickaxe, axe, shovel, stick1, stick2, result);
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String string, @NotNull CriterionTriggerInstance criterionTriggerInstance) {
        this.advancement.addCriterion(string, criterionTriggerInstance);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String string) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation resourceLocation) {
        this.advancement
                .parent(ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(RequirementsStrategy.OR);
        consumer.accept(
                new Result(
                        resourceLocation,
                        this.pickaxe,
                        this.axe,
                        this.shovel,
                        this.stick1,
                        this.stick2,
                        this.result,
                        this.advancement,
                        resourceLocation.withPrefix("recipes/" + RecipeCategory.TOOLS.getFolderName() + "/")
                )
        );
    }

    public static class Result extends CraftingResult {
        private final ResourceLocation id;
        private final Ingredient pickaxe;
        private final Ingredient axe;
        private final Ingredient shovel;
        private final Ingredient stick1;
        private final Ingredient stick2;
        private final Item result;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        protected Result(ResourceLocation id, Ingredient pickaxe, Ingredient axe, Ingredient shovel, Ingredient stick1, Ingredient stick2,
                         Item result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(CraftingBookCategory.EQUIPMENT);
            this.id = id;
            this.pickaxe = pickaxe;
            this.axe = axe;
            this.shovel = shovel;
            this.stick1 = stick1;
            this.stick2 = stick2;
            this.result = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("pickaxe", this.pickaxe.toJson());
            jsonObject.add("axe", this.axe.toJson());
            jsonObject.add("shovel", this.shovel.toJson());
            jsonObject.add("stick1", this.stick1.toJson());
            jsonObject.add("stick2", this.stick2.toJson());

            jsonObject.addProperty("result", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)).toString());
        }

        @Override
        public @NotNull ResourceLocation getId() {
            return this.id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return BetterToolsRecipeSerializers.PAXEL_RECIPE_SERIALIZER.get();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
