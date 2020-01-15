package me.hadden.commons.bungee.commands;

import me.hadden.commons.bungee.Bungee;
import me.hadden.commons.bungee.account.group.GroupPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WhoisCommand extends Command {

	public WhoisCommand() {
		super("info");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("smith.bungee.commands.whois")) {
			if (args.length == 0) {
				sender.sendMessage(Bungee.color("&cUtilize /info <jogador>."));
				return;
			} else if (args.length == 1) {
				ProxiedPlayer p = ProxyServer.getInstance().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(Bungee.color("&cEste usuário não existe."));
					return;
				}
				sender.sendMessage("");
				sender.sendMessage(Bungee.color("&2Informações do jogador:"));
				sender.sendMessage("");
				sender.sendMessage(Bungee.color("&7Nome: &f" + p.getName()));
				sender.sendMessage(Bungee.color("&7Servidor atual: &f" + p.getServer().getInfo().getName().replaceAll("-", " ")));
				sender.sendMessage(Bungee.color("&7IP: &f" + p.getAddress().getAddress().getHostAddress()));
				sender.sendMessage(Bungee.color("&7Cargo: &f" + GroupPlayer.get(p)));
				sender.sendMessage(Bungee.color("&7Ping: &f" + p.getPing() + "ms"));
				sender.sendMessage("");
				return;
			}
		} else {
			sender.sendMessage(Bungee.color("&cVocê precisa do grupo Gerente ou superior para executar este comando."));
			return;
		}
	}
	
}
