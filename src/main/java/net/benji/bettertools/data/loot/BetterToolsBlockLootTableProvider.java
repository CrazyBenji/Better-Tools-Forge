package net.benji.bettertools.data.loot;

import net.benji.bettertools.block.BetterToolsBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BetterToolsBlockLootTableProvider extends BlockLootSubProvider {
    public BetterToolsBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BetterToolsBlocks.SMASHED_BEDROCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BetterToolsBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}