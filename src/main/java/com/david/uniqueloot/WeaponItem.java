package com.david.uniqueloot;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.network.chat.Component;
import java.util.*;

public class WeaponItem extends SwordItem {
    private static final Random RANDOM = new Random();
    
    public WeaponItem() {
        super(Tiers.NETHERITE, 8, -2.4F, new Item.Properties().durability(2031));
    }
    
    public static ItemStack createEnchantedWeapon(String name) {
        ItemStack weapon = new ItemStack(Items.NETHERITE_SWORD);
        weapon.setHoverName(Component.literal(name));
        
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantments.SHARPNESS, 3 + RANDOM.nextInt(5));
        enchantments.put(Enchantments.FIRE_ASPECT, 1 + RANDOM.nextInt(2));
        
        // Encantamientos adicionales (opcional)
        if (RANDOM.nextDouble() < 0.5) enchantments.put(Enchantments.KNOCKBACK, 1);
        if (RANDOM.nextDouble() < 0.3) enchantments.put(Enchantments.MENDING, 1);
        
        EnchantmentHelper.setEnchantments(enchantments, weapon);
        return weapon;
    }
}