package me.hadden.commons.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLogin(PostLoginEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if (MaintenanceCommand.getInstance().maintenance == true) {
			if (p.hasPermission("snockmc.bungee.commands.stafflist")) {
				return;
			}
			p.disconnect("§c§lMANUTENÇÃO\n§r\n§cO servidor está em uma manutenção\n§r\n§cVoltaremos em breve!\n§r\n§cFique por dentro de tudo que está acontecendo!\n§cAcesse o nosso twitter @SuporteSnock!");
		}
		if (!TellToggleCommand.toggled.containsKey(p.getName())) {
			TellToggleCommand.toggled.put(p.getName(), false);
		}
	}
}
