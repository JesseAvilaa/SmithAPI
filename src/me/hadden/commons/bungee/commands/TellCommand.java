package me.hadden.commons.bungee.commands;

import java.util.HashMap;

import me.hadden.commons.bukkit.utils.Utils;
import me.hadden.commons.bungee.Bungee;
import me.hadden.commons.bungee.account.group.GroupPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TellCommand extends Command {

	public static HashMap<String, String> enviei  = new HashMap<String, String>();
	public static HashMap<String, String> tell  = new HashMap<String, String>();
	
	public TellCommand() {
		super("tell", "", new String[] { "t", "wmsg", "msg", "emsg" });
	}

	private String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0) {
				sender.sendMessage(Utils.color("&cUtilize /tell <jogador> <mensagem> para enviar uma mensagem privada."));
			} else if (args.length == 1) {
				sender.sendMessage(Utils.color("&cUtilize /tell <jogador> <mensagem> para enviar uma mensagem privada."));
			} else {
				if (args.length > 1) {
					if (Bungee.getInstance().getProxy().getPlayer(args[0]) == null) {
						p.sendMessage(Utils.color("&cEste jogador não está on-line."));
						return;
					}
					ProxiedPlayer todos = Bungee.getInstance().getProxy().getPlayer(args[0]);
					if (!todos.isConnected()) {
						p.sendMessage(Utils.color("&cEste usuário está offline."));
						return;
					}
					if (args[0].equalsIgnoreCase(p.getName())) {
						p.sendMessage(Utils.color("&cEvite enviar mensagens para si mesmo. "));
						return;
					}
					if (TellToggleCommand.toggled.containsKey(todos.getName()) && TellToggleCommand.toggled.get(todos.getName())) {
						p.sendMessage(Utils.color("&cEste usuário desabilitou o recebimento de mensagens privadas."));
						return;
					}
					String message = "";
					String[] var9 = args;
					int var8 = args.length;

					for (int var7 = 0; var7 < var8; ++var7) {
						String part = var9[var7];
						if (message != "") {
							message = message + " ";
						}
						message = message + part;
					}
					if (enviei.containsKey(p.getName()) && enviei.get(p.getName()).toLowerCase().contains(message.toLowerCase())){
						p.sendMessage(Utils.color("&cVocê não pode enviar uma mensagem tão parecida com a anterior."));
						return;
					}
					
					int tem = args[0].length();
					
					String send = message.substring(tem);
					
					tell.put(p.getName(), todos.getName());
					
					p.sendMessage(Utils.color("&8Mensagem para &7" + GroupPlayer.get(todos) + todos.getName() + "&8: &6" + send));
					todos.sendMessage(Utils.color("&8Mensagem de &7" + GroupPlayer.get(p) + p.getName() + "&8: &6" + send));
					enviei.put(p.getName(), message);
				}
			}
		}
	}
}