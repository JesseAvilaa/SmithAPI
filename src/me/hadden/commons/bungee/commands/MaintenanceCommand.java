package me.hadden.commons.bungee.commands;

import me.hadden.commons.bungee.Bungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {

	private static MaintenanceCommand i;
	
	public MaintenanceCommand() {
		super("manutencao");
		MaintenanceCommand.i = this;
	}
	
	public static MaintenanceCommand getInstance() {
		return i;
	}
	
	public boolean maintenance = false;
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("smith.bungee.commands.maintenance")) {
			if (maintenance == false) {
				maintenance = true;
				sender.sendMessage(Bungee.color("&cManuten��o ativada com sucesso, todos jogadores foram expulsos."));
				for (ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
					if (pp.hasPermission("smith.bungee.commands.staffchat")) {
						continue;
					}
					if (pp.isConnected()) {
						pp.disconnect(Bungee.color("&c&lMANUTEN��O\n&r\n&cO servidor iniciou uma manuten��o, voltamos em breve!\n&r\n&cFique por dentro de tudo que est� acontecendo!\n&cAcesse o nosso twitter @ServidoresSmith!"));
					}
				}
				return;
			} else {
				maintenance = false;
				sender.sendMessage(Bungee.color("&cManuten��o desativada com sucesso!"));
				return;
			}
		} else {
			sender.sendMessage(Bungee.color("&cVoc� precisa do grupo Gerente ou superior para executar este comando."));
			return;
		}
	}
}
