package net.benji.bettertools.item.custom;

import com.google.common.collect.BiMap;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class PaxelItem extends DiggerItem {
    public static final Component DESC = Component.translatable("desc.bettertools.paxel").withStyle(ChatFormatting.BLUE);

    public PaxelItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(attackDamageModifier, attackSpeedModifier, tier, BetterToolsTags.Blocks.PAXEL_MINEABLE, properties);
    }

    public PaxelItem(Tier tier, Properties properties) {
        this(tier, 2.0f, -2.8f, properties);
    }

    @Override
    public @Nonnull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);

        // Axe Logic
        Optional<BlockState> optional = Optional.ofNullable(blockState.getToolModifiedState(context, ToolActions.AXE_STRIP, false));
        Optional<BlockState> optional1 = optional.isPresent() ? Optional.empty() : Optional.ofNullable(blockState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false));
        Optional<BlockState> optional2 = optional.isEmpty() && optional1.isEmpty() ? Optional.ofNullable(blockState.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false)) : Optional.empty();
        ItemStack itemstack = context.getItemInHand();
        Optional<BlockState> optional3 = Optional.empty();
        if (optional.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            optional3 = optional;
        } else if (optional1.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3005, blockPos, 0);
            optional3 = optional1;
        } else if (optional2.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, blockPos, 0);
            optional3 = optional2;
        }

        if (optional3.isPresent()) {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemstack);
            }

            level.setBlock(blockPos, optional3.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, optional3.get()));
            if (player != null) {
                itemstack.hurtAndBreak(1, player, (p_150686_) -> p_150686_.broadcastBreakEvent(context.getHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        // Shovel Logic
        if (context.getClickedFace() != Direction.DOWN) {
            BlockState blockstate1 = blockState.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
            BlockState blockstate2 = null;
            if (blockstate1 != null && level.isEmptyBlock(blockPos.above())) {
                level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.getValue(CampfireBlock.LIT)) {
                if (!level.isClientSide()) {
                    level.levelEvent((Player)null, 1009, blockPos, 0);
                }

                CampfireBlock.dowse(context.getPlayer(), level, blockPos, blockState);
                blockstate2 = blockState.setValue(CampfireBlock.LIT, false);
            }

            if (blockstate2 != null) {
                if (!level.isClientSide) {
                    level.setBlock(blockPos, blockstate2, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate2));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> p_43122_.broadcastBreakEvent(context.getHand()));
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction)
                || ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction)
                || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
