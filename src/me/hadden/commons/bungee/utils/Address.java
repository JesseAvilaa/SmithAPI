package me.hadden.commons.bungee.utils;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Address {

	public static String getAddres(ProxiedPlayer player) {
		PendingConnection pc = player.getPendingConnection();
		Object handshake;
		try {
			handshake = pc.getClass().getMethod("getHandshake").invoke(pc);
			String host = (String) handshake.getClass().getMethod("getHost").invoke(handshake);
			return host;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
