package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsBlockStateProviderForge extends BlockStateProvider {
    public BetterToolsBlockStateProviderForge(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BetterToolsForge.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BetterToolsBlocks.SMASHED_BEDROCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
