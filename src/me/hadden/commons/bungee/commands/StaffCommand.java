package me.hadden.commons.bungee.commands;

import me.hadden.commons.bukkit.utils.Utils;
import me.hadden.commons.bungee.account.group.GroupPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.security.acl.Group;

public class StaffCommand extends Command {

	public StaffCommand() {
		super("s");
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("smith.bungee.commands.staffchat")) {
			sender.sendMessage(Utils.color("&cVocê precisa do grupo Ajudante ou superior para executar este comando."));
			return;
		} else {
			if (args.length == 0) {
				sender.sendMessage("&cUtilize /s <mensagem>.");
				return;
			} else if (args.length > 0) {
				ProxiedPlayer p = (ProxiedPlayer) sender;
				String message = "";
				for (int i = 0; i < args.length; ++i) {
					message = String.valueOf(message) + args[i] + " ";
				}
				for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
					if (all.hasPermission("smith.commands.staffchat")) {
						all.sendMessage(Utils.color("&d&l[S]&r &7(" + p.getServer().getInfo().getName() + ") " + GroupPlayer.get(p) + "&f" + sender.getName() + "&f: " + message));
					}
				}
			}
		}
	}

}