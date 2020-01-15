package me.hadden.commons.bungee.events;

import com.snockmc.bungee.login.Methods;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatListener implements Listener {

	@EventHandler
	public void onChat(ChatEvent e){
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		if (!e.isCommand()) {
			if (!Methods.get().hasAccount(p.getName())
					|| !Methods.get().isLogged(p.getName().toLowerCase())) {
				e.setCancelled(true);
				return;
			}
		}
	}
}
