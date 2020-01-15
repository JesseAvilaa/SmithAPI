package me.hadden.commons.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {

	@EventHandler
	public void onSwitch(ServerConnectEvent e){
		ProxiedPlayer player = e.getPlayer();
		String username = player.getName();
		if (e.getTarget().getName().toLowerCase().contains("lobby")) return;
		if (!Methods.get().hasAccount(username) || !Methods.get().isLogged(username.toLowerCase())) {
			e.setCancelled(true);
			return;
		}
	}
}
