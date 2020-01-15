package me.hadden.commons.bungee.commands;

import me.hadden.commons.bukkit.utils.Utils;
import me.hadden.commons.bungee.account.group.GroupPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.security.acl.Group;

public class RespCommand extends Command {
	public RespCommand() {
		super("r");
	}

	private String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0) {
				sender.sendMessage(Utils.color("&cUtilize /r <mensagem> para enviar uma mensagem privada."));
			} else {
				if (args.length > 0) {
					if (TellCommand.tell.get(p.getName()) == null) {
						p.sendMessage(Utils.color("&cVocê não tem uma conversa recente."));
						return;
					}

					String nome = (String) TellCommand.tell.get(p.getName());
					if (ProxyServer.getInstance().getPlayer(nome) == null) {
						p.sendMessage(Utils.color("&cEste jogador não está on-line."));
						return;
					}

					ProxiedPlayer todos = ProxyServer.getInstance().getPlayer(nome);
					if (args[0].equalsIgnoreCase(p.getName())) {
						p.sendMessage(Utils.color("&cEvite enviar mensagens para si mesmo."));
						return;
					}

					String message = "";
					String[] var10 = args;
					int var9 = args.length;

					for (int var8 = 0; var8 < var9; ++var8) {
						String part = var10[var8];
						if (message != "") {
							message = message + " ";
						}

						message = message + part;
					}
					if (todos == null) {
						p.sendMessage(Utils.color("&cEste usuário não está online."));
						return;
					}
					if (TellToggleCommand.toggled.containsKey(todos.getName()) && TellToggleCommand.toggled.get(todos.getName())) {
						p.sendMessage(Utils.color("&cEste usuário desabilitou o recebimento de mensagens privadas."));
						return;
					}

					p.sendMessage(Utils.color("&8Mensagem para &7" + GroupPlayer.get(todos) + todos.getName() + "&8: &6" + message));
					todos.sendMessage(Utils.color("&8Mensagem de &7" + GroupPlayer.get(p) + p.getName() + "&8: &6" + message));
				}
			}
		}
	}
}