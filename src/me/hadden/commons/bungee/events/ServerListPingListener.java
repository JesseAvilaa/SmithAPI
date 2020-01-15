package me.hadden.commons.bungee.events;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ServerListPingListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPing(ProxyPingEvent e){
		ServerPing ping = e.getResponse();
		if (MaintenanceCommand.getInstance().maintenance == true){
			ServerPing.Protocol protocol = ping.getVersion();
			protocol.setName("§cEm manutenção!");
			protocol.setProtocol(29000);
			ping.setVersion(protocol);
			ping.setDescription("§b§lSnockMC §7(1.8.x)\n§cServidor em manutenção, voltamos em breve!");
		} else {
			String motd = ChatColor.translateAlternateColorCodes('&', Settings.configuration.getString("motd"));
			ping.setDescription(motd);
		}
		e.setResponse(ping);
	}
}
