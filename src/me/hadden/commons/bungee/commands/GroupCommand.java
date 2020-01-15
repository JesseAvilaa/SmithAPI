package me.hadden.commons.bungee.commands;

import java.util.ArrayList;

import me.hadden.commons.bukkit.utils.Utils;
import me.hadden.commons.bungee.account.AccountPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GroupCommand extends Command {
	
	public GroupCommand() {
		super("grupo");
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("smith.bungee.commands.groups")) {
			sender.sendMessage(Utils.color("&cVocê precisa do grupo Gerente ou superior para executar este comando."));
		} else if (args.length == 0) {
			sender.sendMessage("");
			sender.sendMessage(Utils.color("&2Comandos disponíveis:"));
			sender.sendMessage("");
			sender.sendMessage(Utils.color("&a  /grupo adicionar <jogador> <grupo>"));
			sender.sendMessage(Utils.color("&a  /grupo remover <jogador> <grupo>"));
			sender.sendMessage("");
		} else {
			ProxiedPlayer player;
			String group;

			if (args[0].equalsIgnoreCase("adicionar")) {
				if (args.length < 3) {
					sender.sendMessage(Utils.color("&cUtilize /grupo adicionar <jogador> <grupo>"));
				} else {
					player = ProxyServer.getInstance().getPlayer(args[1]);
					if (player == null) {
						sender.sendMessage(Utils.color("&cEste usuário não está online."));
					} else {
						group = args[2];
						AccountPlayer.setGrupo(player, group);
						sender.sendMessage(Utils.color("&aJogador adicionado ao grupo: &f" + group));
						player.sendMessage(Utils.color("&aVocê foi promovido ao cargo de: &f" + group));
					}
				}
			} else if (args[0].equalsIgnoreCase("remover")) {
				if (args.length < 3) {
					sender.sendMessage(Utils.color("&cUtilize /grupo adicionar <jogador> <grupo>"));
				} else {
					player = ProxyServer.getInstance().getPlayer(args[1]);
					if (player == null) {
						sender.sendMessage(Utils.color("&cEste usuário não está online."));
					} else {
						group = args[2];
						AccountPlayer.setGrupo(player, group);
						sender.sendMessage(Utils.color("&aJogador removido do grupo: &f" + group));
					}
				}
			}
		}
	}
}