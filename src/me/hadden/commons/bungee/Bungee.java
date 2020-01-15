package me.hadden.commons.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Bungee extends Plugin {

    private static Bungee i;
    private File file;
    public static Configuration configuration;

    @Override
    public void onEnable() {
        file = new File(ProxyServer.getInstance().getPluginsFolder()+ "/config.yml");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String color(String color) {
        return ChatColor.translateAlternateColorCodes('&', color);
    }

    public static void info(String info) {
        ProxyServer.getInstance().broadcast(color(info));
    }

    public static Bungee getInstance() {
        return i;
    }


}
