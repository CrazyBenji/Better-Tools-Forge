package net.benji.bettertools.enchantment;

import net.benji.bettertools.BetterToolsForge;
import net.benji.bettertools.enchantment.custom.ReapingEnchantment;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BetterToolsEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, BetterToolsForge.MOD_ID);

    public static final RegistryObject<Enchantment> REAPING = ENCHANTMENTS.register(
            "reaping",
            () -> new ReapingEnchantment(
                    Enchantment.definition(
                            BetterToolsTags.Items.SCYTHES,
                            5,
                            4,
                            Enchantment.dynamicCost(10, 8),
                            Enchantment.dynamicCost(18, 8),
                            1,
                            EquipmentSlot.MAINHAND)
            )
    );

    public static void registerEnchantments(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
