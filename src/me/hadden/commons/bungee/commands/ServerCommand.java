package me.hadden.commons.bungee.commands;

import java.net.Socket;
import java.util.Random;

import me.hadden.commons.bukkit.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerCommand extends Command {

	public ServerCommand() {
		super("server");
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("smith.bungee.commands.server")) {
			if (args.length < 1 || args.length > 1) {
				if (!(sender instanceof ProxiedPlayer)) {
					sender.sendMessage(Utils.color("&cApenas jogadores in-game!"));
					return;
				}
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&2Servidores disponíveis:"));
				sender.sendMessage("");
				TextComponent text = new TextComponent();
				Random rand = new Random();
				ChatColor color = ChatColor.RESET;
				for (ServerInfo server : ProxyServer.getInstance().getServers().values()) {
					int randomNum = rand.nextInt(3);
					if (randomNum == 1) {
						color = ChatColor.YELLOW;
					} else if (randomNum == 2) {
						color = ChatColor.GRAY;
					}
					text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + server.getName()));
					text.setText(Utils.color(" &7" + color + server.getName().replaceAll("-", " ") + ": &f" + server.getPlayers().size()));
					text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&7Clique para entrar!")).create()));
					sender.sendMessage(text);
				}
				sender.sendMessage("");
				return;
			} else if (args.length == 1) {
				ProxiedPlayer p = (ProxiedPlayer) sender;
				ServerInfo server = ProxyServer.getInstance().getServerInfo(args[0]);
				if (server == p.getServer().getInfo()) {
					p.sendMessage(Utils.color("&cVocê já está conectado a este servidor!"));
					return;
				}
				if (server == null) {
					p.sendMessage(Utils.color("&cEste servidor não existe."));
					return;
				}
		        try {
					Socket s = new Socket(server.getAddress().getAddress().getHostAddress(), server.getAddress().getPort());
					s.close();
					p.connect(server);
					p.sendMessage(Utils.color("&eConectando-se ao servidor: &f" + server.getName().replaceAll(" ", "-")));
		        } catch (Exception ex) {
					sender.sendMessage(Utils.color("&cEste servidor está offline."));
		            return;
		        }
			}
		} else {
			sender.sendMessage(Utils.color("&cVocê precisa do grupo Moderador ou superior para executar este comando."));
			return;
		}
	}
}
