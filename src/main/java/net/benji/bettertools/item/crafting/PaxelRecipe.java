package net.benji.bettertools.item.crafting;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class PaxelRecipe extends CustomRecipe {

    private final Ingredient pickaxe;
    private final Ingredient axe;
    private final Ingredient shovel;
    private final Ingredient stick1;
    private final Ingredient stick2;
    private final ItemStack result;

    public PaxelRecipe(ResourceLocation resourceLocation, Ingredient pickaxe, Ingredient axe, Ingredient shovel,
                       Ingredient stick1, Ingredient stick2, ItemStack result) {
        super(resourceLocation, CraftingBookCategory.EQUIPMENT);
        this.pickaxe = pickaxe;
        this.axe = axe;
        this.shovel = shovel;
        this.stick1 = stick1;
        this.stick2 = stick2;
        this.result = result;
    }

    @Override
    public boolean matches(@NotNull CraftingContainer container, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return (this.pickaxe.test(container.getItem(0)) && this.axe.test(container.getItem(1)) && this.shovel.test(container.getItem(2))
                && this.stick1.test(container.getItem(4)) && this.stick2.test(container.getItem(7)))
                || (this.shovel.test(container.getItem(0)) && this.axe.test(container.getItem(1)) && this.pickaxe.test(container.getItem(2))
                && this.stick1.test(container.getItem(4)) && this.stick2.test(container.getItem(7)));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer container, @NotNull RegistryAccess registryAccess) {
        ItemStack toReturn = new ItemStack(this.result.getItem());
        EnchantmentHelper.setEnchantments(combineEnchantments(container), toReturn);
        return toReturn;
    }

    private Map<Enchantment, Integer> combineEnchantments(CraftingContainer container) {
        Map<Enchantment, Integer> combined = EnchantmentHelper.deserializeEnchantments(container.getItem(0).getEnchantmentTags());
        List<Map<Enchantment, Integer>> mapsToCombine = List.of(
                EnchantmentHelper.deserializeEnchantments(container.getItem(1).getEnchantmentTags()),
                EnchantmentHelper.deserializeEnchantments(container.getItem(2).getEnchantmentTags())
        );

        for (Map<Enchantment, Integer> enchantmentMap : mapsToCombine) {
            for (Map.Entry<Enchantment, Integer> mapEntry : enchantmentMap.entrySet()) {
                Enchantment enchantment = mapEntry.getKey();
                Integer level = mapEntry.getValue();
                if (!combined.containsKey(enchantment)) {
                    combined.put(enchantment, level);
                }
                else {
                    if (combined.get(enchantment) < level) {
                        combined.remove(enchantment);
                        combined.put(enchantment, level);
                    }
                }
            }
        }

        return combined;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i >= 3 && j >= 3;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BetterToolsRecipeSerializers.PAXEL_RECIPE_SERIALIZER.get();
    }

    public static class PaxelRecipeJsonFormat {
        JsonObject pickaxe;
        JsonObject axe;
        JsonObject shovel;
        JsonObject stick1;
        JsonObject stick2;
        String result;
    }

    public static class PaxelRecipeSerializer implements RecipeSerializer<PaxelRecipe> {

        public PaxelRecipeSerializer() {}

        @Override
        public @NotNull PaxelRecipe fromJson(@NotNull ResourceLocation resourceLocation, @NotNull JsonObject jsonObject) {
            PaxelRecipeJsonFormat recipeJson = new Gson().fromJson(jsonObject, PaxelRecipeJsonFormat.class);

            Ingredient pickaxe = Ingredient.fromJson(recipeJson.pickaxe);
            Ingredient axe = Ingredient.fromJson(recipeJson.axe);
            Ingredient shovel = Ingredient.fromJson(recipeJson.shovel);
            Ingredient stick1 = Ingredient.fromJson(recipeJson.stick1);
            Ingredient stick2 = Ingredient.fromJson(recipeJson.stick2);

            Item resultItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(recipeJson.result));
            assert resultItem != null;
            ItemStack result = new ItemStack(resultItem);

            return new PaxelRecipe(resourceLocation, pickaxe, axe, shovel, stick1, stick2, result);
        }

        @Override
        public @NotNull PaxelRecipe fromNetwork(@NotNull ResourceLocation resourceLocation, @NotNull FriendlyByteBuf friendlyByteBuf) {
            Ingredient pickaxe = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient axe = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient shovel = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient stick1 = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient stick2 = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack result = friendlyByteBuf.readItem();
            return new PaxelRecipe(resourceLocation, pickaxe, axe, shovel, stick1, stick2, result);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf friendlyByteBuf, PaxelRecipe recipe) {
            recipe.pickaxe.toNetwork(friendlyByteBuf);
            recipe.axe.toNetwork(friendlyByteBuf);
            recipe.shovel.toNetwork(friendlyByteBuf);
            recipe.stick1.toNetwork(friendlyByteBuf);
            recipe.stick2.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(recipe.result);
        }
    }
}