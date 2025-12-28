package net.benji.bettertools.enchantment.custom;

import net.benji.bettertools.item.custom.ScytheItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReapingEnchantment extends Enchantment {

    public ReapingEnchantment(EnchantmentDefinition definition) {
        super(definition);
    }

    @Override
    public float getDamageBonus(int level, @Nullable EntityType<?> mobType, ItemStack enchantedItem) {
        return 1.5F * level;
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment other) {
        return !(other instanceof DamageEnchantment);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ScytheItem;
    }


}