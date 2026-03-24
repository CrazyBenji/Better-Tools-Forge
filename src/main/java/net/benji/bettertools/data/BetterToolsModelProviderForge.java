package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsModelProviderForge extends ItemModelProvider {

    public BetterToolsModelProviderForge(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BetterToolsForge.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateFlatItem(BetterToolsItems.IRON_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.GOLDEN_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.DIAMOND_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.NETHERITE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.IRON_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.GOLDEN_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.DIAMOND_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.NETHERITE_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.IRON_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.GOLDEN_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.DIAMOND_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.NETHERITE_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.GLASS_CHIPPER, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.BEDROCK_SMASHER, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.IRON_LUMBER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.GOLDEN_LUMBER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.DIAMOND_LUMBER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.NETHERITE_LUMBER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(BetterToolsItems.IRON_MACHETE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.GOLDEN_MACHETE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.DIAMOND_MACHETE, ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(BetterToolsItems.NETHERITE_MACHETE, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    public void generateFlatItem(RegistryObject<Item> item, ModelTemplate modelTemplate) {
        ResourceLocation parent;
        if (modelTemplate.equals(ModelTemplates.FLAT_HANDHELD_ITEM)) {
            parent = ResourceLocation.parse("item/handheld");
        }
        else {
            parent = ResourceLocation.parse("item/generated");
        }

        assert item.getId() != null;
        withExistingParent(item.getId().getPath(),
                parent)
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(BetterToolsForge.MOD_ID, "item/" + item.getId().getPath()));
    }
}