package com.david.uniqueloot;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.*;
import com.david.uniqueloot.ConfigManager;

public class WeaponDropsHandler {
    private static final List<String> WEAPON_NAMES = List.of(
        "Phantomguard Greatsword", "Heavenly Partisan", "Amethyst Greatblade",
        "Valhakyra", "Black Iron Greatsword", "Talonbrand", "Berserker's Cleaver",
        "Revenant's Gravecleaver", "Zenith", "Crystal Frostblade", "Lycanbane",
        "Grand Claymore", "Solstice", "Gloomsteel Katana", "Gloomsteel Knife",
        "Vesper", "Calamity Blade", "Emerald Greatcleaver", "Ancient Greatslab",
        "Demon's Blood Blade", "Demonslayer's Greatsword", "Sacrificial Cleaver",
        "Moonlight", "Oculus", "Azure Greatsword", "Azure Dagger", "Azure Sabre",
        "Crimson Cleaver", "Dragon Sword", "Spider Sword", "Piercer", "Runic Piercer",
        "Demonic Sword", "Nature Sword", "Vengeance Blade", "Wooden Bludgeon",
        "Iron Mace", "Claymore", "Flamberge", "Iron Broadsword",
        "Gilded Phoenix Greataxe", "Black Iron Greataxe", "Viridian Greataxe",
        "Azure Greataxe", "Berserker's Greataxe", "Crescent Greataxe",
        "Gloomsteel Greataxe", "Skeleton Axe", "Vindicator", "Treacherous Axe",
        "Iron Greataxe", "Iron Battle Axe", "Iron Halberd",
        "Revenant's Gravescepter", "Windreaper", "Crystal Frostscythe",
        "Azure Scythe", "Iron Hay Sickle", "Nocturne"
    );
    
    private static List<String> availableNames = new ArrayList<>(WEAPON_NAMES);
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public void onMobDeath(LivingDropsEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Monster)) return;

        // Verifica la probabilidad (20% por defecto)
        if (RANDOM.nextDouble() >= ConfigManager.DROP_CHANCE) {
            return; // No dropea nada (80% de los casos)
        }

        // Si no hay nombres disponibles, reinicia la lista
        if (availableNames.isEmpty()) {
            availableNames = new ArrayList<>(WEAPON_NAMES);
        }

        // Selecciona un arma aleatoria
        String weaponName = availableNames.remove(RANDOM.nextInt(availableNames.size()));
        ItemStack weapon = WeaponItem.createEnchantedWeapon(weaponName);

        // Añade el drop
        event.getDrops().add(new ItemEntity(
            entity.level(), 
            entity.getX(), 
            entity.getY(), 
            entity.getZ(), 
            weapon
        ));
    }
}