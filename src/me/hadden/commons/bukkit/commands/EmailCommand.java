package me.hadden.commons.bukkit.commands;

import me.hadden.commons.bukkit.account.AccountPlayer;
import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class EmailCommand extends CommandBase {
	public EmailCommand() {
		super("email");
	}

	public boolean execute(final CommandSender sender, final String label, final String[] args) {

		final Player jogador = (Player) sender;
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Utils.color("&cComando apenas para jogadores."));
		}
		if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /email adicionar <email>&7 - Comando para setar seu email."));
				sender.sendMessage(Utils.color("&6 /email remover&7 - Comando para remover seu email."));
				sender.sendMessage("");
			return true;
		}
		if (args.length == 1) {
			String subComando = args[0];

			if (subComando.equalsIgnoreCase("adicionar") || subComando.equalsIgnoreCase("add")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /email adicionar <email>."));
				jogador.sendMessage("");
				return true;
			}
			
			if (subComando.equalsIgnoreCase("remover") || subComando.equalsIgnoreCase("remove")) {
				if(AccountPlayer.getEmail(jogador).equalsIgnoreCase("Nenhum")) {
					sender.sendMessage(Utils.color("&cVocê não tem nenhum email registrado."));
				} else {
				AccountPlayer.setEmail(jogador, "Nenhum");
				sender.sendMessage(Utils.color("&aSeu email foi apagado, agora sera: &cNenhum&a."));
				return true;
				}
			}
			
			return true;
		}
		if (args.length == 2) {
			
			
			if (args[0].equalsIgnoreCase("adicionar") || args[0].equalsIgnoreCase("add")) {

				if(AccountPlayer.getEmail(jogador).equalsIgnoreCase(args[1])){
						jogador.sendMessage(Utils.color("&cVocê já está com o email: "+ args[1]+ "."));
					} else {
					AccountPlayer.setEmail(jogador, args[1]);
					jogador.sendMessage(Utils.color("&aSeu email agora é: &e" + args[1] + "&a."));
					}

			}
		}
		
		return true;
	}

}
