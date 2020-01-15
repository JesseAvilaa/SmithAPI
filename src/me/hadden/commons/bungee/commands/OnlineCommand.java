package me.hadden.commons.bungee.commands;

import me.hadden.commons.bungee.Bungee;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class OnlineCommand extends Command {
    public OnlineCommand() {
        super("online");
    }
    
    @SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
    	int all = ProxyServer.getInstance().getOnlineCount();
    	if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("");
            sender.sendMessage(Bungee.color("&eTotal de jogadores online &f" + all + "&e."));
    		sender.sendMessage("");
    		return;
    	}
        ServerInfo server = ProxyServer.getInstance().getPlayer(sender.getName()).getServer().getInfo();
        int current = server.getPlayers().size();
        sender.sendMessage("");
        sender.sendMessage(Bungee.color("&eTotal de jogadores online &f" + all + "&e."));
        sender.sendMessage(Bungee.color("&eJogadores no " + server.getName().replaceAll("-", " ") + "&e: &f" + current + "&e."));
        sender.sendMessage("");
    }
}