package me.hadden.commons.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler
	public void onCommand(ChatEvent e){
		if (!(e.getSender() instanceof ProxiedPlayer)) {
			if (e.isCommand()) {
				System.out.println("CONSOLE: " + e.getMessage());
			}
		}
		ProxiedPlayer player = (ProxiedPlayer) e.getSender();
		System.out.println(player.getName() + " (" + player.getServer().getInfo().getName() + "): " + e.getMessage());
		String username = player.getName();
		if (e.isCommand()){
			if (e.getMessage().startsWith("/log") || e.getMessage().startsWith("/regist")) return;
			if (!Methods.get().hasAccount(username)
					|| !Methods.get().isLogged(username.toLowerCase())) {
				e.setCancelled(true);
				return;
			}
		}
	}
}
