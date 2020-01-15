package me.hadden.commons.bukkit.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

    public static String color(String color) {
        return ChatColor.translateAlternateColorCodes('&', color);
    }

    public static void info(String info) {
        Bukkit.getConsoleSender().sendMessage(color(info));
    }

    public static void SubTitulo(final String subtitle, final Player p) {
        final PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        final IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subtitle + "'}");
        final PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
        connection.sendPacket((Packet<?>)subtitlePacket);
    }

}
