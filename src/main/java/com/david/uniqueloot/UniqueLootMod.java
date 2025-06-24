package com.david.uniqueloot;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import com.david.uniqueloot.ConfigManager; // Import añadido

@Mod("uniqueloot")
public class UniqueLootMod {
    public UniqueLootMod() {
        MinecraftForge.EVENT_BUS.register(new WeaponDropsHandler());
        ConfigManager.loadConfig(); // Ahora reconocerá ConfigManager
        System.out.println("[Unique Loot] Mod cargado!");
        System.out.println("Ruta de configuración: " + FMLPaths.CONFIGDIR.get().resolve("uniqueloot"));
    }
}
