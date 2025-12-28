package net.benji.bettertools.enchantment;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.enchantment.custom.ReapingEnchantment;
import net.benji.bettertools.item.custom.ScytheItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, BetterToolsForge.MOD_ID);

    public static final EnchantmentCategory SCYTHES = EnchantmentCategory.create("scythes", item -> item instanceof ScytheItem);

    public static final RegistryObject<Enchantment> REAPING = ENCHANTMENTS.register(
            "reaping",
            () -> new ReapingEnchantment(Enchantment.Rarity.VERY_RARE, SCYTHES, EquipmentSlot.MAINHAND)
    );

    public static void registerEnchantment(IEventBus modEventBus) {
        ENCHANTMENTS.register(modEventBus);
    }
}
