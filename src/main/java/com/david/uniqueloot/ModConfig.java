package com.david.uniqueloot;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "uniqueloot", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig {
    public static final ForgeConfigSpec.ConfigValue<String> CONFIG_FILE;

    public static final ForgeConfigSpec SPEC;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        CONFIG_FILE = builder
            .comment("Ruta del archivo JSON de configuración (relativa a /config)")
            .define("config_file", "uniqueloot/weapon_drops.json");
            
        SPEC = builder.build();
    }
}