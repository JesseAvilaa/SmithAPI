package me.hadden.commons.bungee.commands;

import me.hadden.commons.bukkit.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class FindCommand extends Command {
    public FindCommand() {
        super("find");
    }

	@SuppressWarnings("deprecation")
    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("smithh.bungee.commands.find")) {
            sender.sendMessage(Utils.color("&cVocê precisa do grupo Gerente ou superior para executar este comando."));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(Utils.color("&cUtilize /find <jogador>."));
            return;
        }
        final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
        if (player == null) {
        	sender.sendMessage(Utils.color("&cEste usuário não está online."));
        	return;
        }
        sender.sendMessage(Utils.color("&eO jogador '" + player.getName() + "' está conectado ao servidor '" + player.getServer().getInfo().getName() + "'."));
    }
}