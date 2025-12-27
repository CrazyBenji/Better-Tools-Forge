package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BetterToolsAdvancementProviderForge extends ForgeAdvancementProvider {

    public BetterToolsAdvancementProviderForge(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new BetterToolsAdvancementGenerator()));
    }

    public static class BetterToolsAdvancementGenerator implements AdvancementGenerator {
        public AdvancementHolder getAdvancement(String id) {
            return Advancement.Builder.advancement().build(new ResourceLocation(id));
        }

        @Override
        public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
            AdvancementHolder getHammer = Advancement.Builder.advancement()
                    .parent(getAdvancement("story/iron_tools"))
                    .display(
                            BetterToolsItems.IRON_HAMMER.get(),
                            Component.translatable("advancements.story.get_hammer.title"),
                            Component.translatable("advancements.story.get_hammer.description"),
                            null,
                            FrameType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("hammer", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.IRON_HAMMER.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":story/get_hammer");

            AdvancementHolder getPaxel = Advancement.Builder.advancement()
                    .parent(getAdvancement("story/iron_tools"))
                    .display(
                            BetterToolsItems.IRON_PAXEL.get(),
                            Component.translatable("advancements.story.get_paxel.title"),
                            Component.translatable("advancements.story.get_paxel.description"),
                            null,
                            FrameType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("got_paxel", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.IRON_PAXEL.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":story/get_paxel");

            AdvancementHolder getScythe = Advancement.Builder.advancement()
                    .parent(getAdvancement("story/iron_tools"))
                    .display(
                            BetterToolsItems.IRON_SCYTHE.get(),
                            Component.translatable("advancements.story.get_scythe.title"),
                            Component.translatable("advancements.story.get_scythe.description"),
                            null,
                            FrameType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("got_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.IRON_SCYTHE.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":story/get_scythe");

            AdvancementHolder getNetheriteScythe = Advancement.Builder.advancement()
                    .parent(getAdvancement("husbandry/obtain_netherite_hoe"))
                    .display(
                            BetterToolsItems.NETHERITE_SCYTHE.get(),
                            Component.translatable("advancements.husbandry.get_netherite_scythe.title"),
                            Component.translatable("advancements.husbandry.get_netherite_scythe.description"),
                            null,
                            FrameType.CHALLENGE,
                            true,
                            true,
                            true
                    )
                    .rewards(
                            new AdvancementRewards.Builder().addExperience(100).build()
                    )
                    .addCriterion("got_netherite_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.NETHERITE_SCYTHE.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":husbandry/get_netherite_scythe");

            AdvancementHolder getGlassChipper = Advancement.Builder.advancement()
                    .parent(getAdvancement("story/iron_tools"))
                    .display(BetterToolsItems.GLASS_CHIPPER.get(),
                            Component.translatable("advancements.story.get_glass_chipper.title"),
                            Component.translatable("advancements.story.get_glass_chipper.description"),
                            null,
                            FrameType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("got_glass_chipper", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.GLASS_CHIPPER.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":story/get_glass_chipper");

            AdvancementHolder getBedrockSmasher = Advancement.Builder.advancement()
                    .parent(getAdvancement("nether/summon_wither"))
                    .display(BetterToolsItems.BEDROCK_SMASHER.get(),
                            Component.translatable("advancements.nether.get_bedrock_smasher.title"),
                            Component.translatable("advancements.nether.get_bedrock_smasher.description"),
                            null,
                            FrameType.CHALLENGE,
                            true,
                            true,
                            true
                    )
                    .addCriterion("got_bedrock_smasher", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.BEDROCK_SMASHER.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":nether/get_bedrock_smasher");

            AdvancementHolder getLumberAxe = Advancement.Builder.advancement()
                    .parent(getAdvancement("story/iron_tools"))
                    .display(BetterToolsItems.IRON_LUMBER_AXE.get(),
                            Component.translatable("advancements.story.get_lumber_axe.title"),
                            Component.translatable("advancements.story.get_lumber_axe.description"),
                            null,
                            FrameType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("got_lumber_axe", InventoryChangeTrigger.TriggerInstance.hasItems(BetterToolsItems.IRON_LUMBER_AXE.get()))
                    .save(consumer, BetterToolsForge.MOD_ID + ":story/get_lumber_axe");
        }
    }
}
