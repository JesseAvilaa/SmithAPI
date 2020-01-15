package me.hadden.commons.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerDisconnectEvent e){
		ProxiedPlayer p = e.getPlayer();
		Methods.get().setUnlogged(p.getName().toLowerCase());
	}
}
