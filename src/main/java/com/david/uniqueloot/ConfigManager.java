package com.david.uniqueloot;  // Paquete renombrado

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraftforge.fml.loading.FMLPaths;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;

public class ConfigManager {
    private static final Gson GSON = new Gson();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("uniqueloot");  // Carpeta renombrada
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("weapon_drops.json");
    
    public static double DROP_CHANCE = 0.20; // 5% por defecto (ajustado de 20% a 5%)

    public static void loadConfig() {
        try {
            // Crear carpeta y archivo si no existen
            if (!Files.exists(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
                System.out.println("[Unique Loot] Carpeta de configuración creada: " + CONFIG_DIR);
            }

            if (!Files.exists(CONFIG_FILE)) {
                createDefaultConfig();
                System.out.println("[Unique Loot] Archivo de configuración creado: " + CONFIG_FILE);
            }

            // Leer el JSON
            try (FileReader reader = new FileReader(CONFIG_FILE.toFile())) {
                JsonObject config = GSON.fromJson(reader, JsonObject.class);
                if (config.has("drop_chance")) {
                    DROP_CHANCE = config.get("drop_chance").getAsDouble();
                    System.out.println("[Unique Loot] Probabilidad de drop establecida en: " + (DROP_CHANCE * 100) + "%");
                } else {
                    System.err.println("[Unique Loot] ¡Campo 'drop_chance' no encontrado! Usando 5% por defecto.");
                }
            }

        } catch (Exception e) {
            System.err.println("[Unique Loot] ERROR: No se pudo cargar la configuración. Usando 5% por defecto.");
            e.printStackTrace();
        }
    }

    private static void createDefaultConfig() throws Exception {
        JsonObject config = new JsonObject();
        config.addProperty("drop_chance", 0.20); // Valor por defecto 5%
        config.addProperty("description", "Probabilidad de drop de armas únicas (0.0 = 0%, 1.0 = 100%)");

        try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
            GSON.toJson(config, writer);
        }
    }
}