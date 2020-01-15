package me.hadden.commons.bukkit.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.hadden.commons.bukkit.SmithAPI;

public class FilesUtils {
	
	public static FileConfiguration config;
	public static File file;

	public static void loadFile() {
		file = new File(SmithAPI.getInstance().getDataFolder(), "permissions.yml");
		if (!file.exists()) {
			SmithAPI.getInstance().saveResource("permissions.yml", true);
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void saveFile() {
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().info("Erro ao salvar o arquivo " + "permissions.yml" + ".");

		}
	}

}
