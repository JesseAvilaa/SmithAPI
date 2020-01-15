package me.hadden.commons.bungee.commands;

import me.hadden.commons.bukkit.utils.Utils;
import me.hadden.commons.bungee.account.group.GroupPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {

	public AlertCommand(){
		super("alerta", "", "bgbc");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("smith.commands.bungee.alert")){
			if (args.length == 0){
				sender.sendMessage("§cUtilize /alerta <mensagem>.");
				return;
			} else if (args.length > 0){
				String message = "";
				for (int i = 0; i < args.length; i++){
					message = message + args[i] + " ";
				}

				ProxiedPlayer player = (ProxiedPlayer) sender;

				ProxyServer.getInstance().broadcast();
				ProxyServer.getInstance().broadcast(GroupPlayer.get(player) + "&f" + sender.getName() + ": " + message);
				ProxyServer.getInstance().broadcast();
			}
		} else {
			sender.sendMessage(Utils.color("&cVocê precisa do grupo Gerente ou superior para executar este comando."));
			return;
		}
	}
}
