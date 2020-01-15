package me.hadden.commons.bukkit.commands;

import java.util.List;

import me.hadden.commons.bukkit.account.AccountPlayer;
import me.hadden.commons.bukkit.player.PlayerPerms;
import me.hadden.commons.bukkit.utils.Logaritimo;
import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import me.hadden.commons.bukkit.SmithAPI;

public class GroupCommand extends CommandBase {
	public GroupCommand() {
		super("group", new String[] { "cargo" });
	}

	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /group setar <nome> <grupo>&7 - Comando para setar grupo."));
				sender.sendMessage(Utils.color("&6 /group addperm <grupo> <permissão>&7 - Comando adicionar permiss&o a um grupo."));
				sender.sendMessage(Utils.color("&6 /group removeperm <grupo> <permissão>&7 - Comando remvoer permiss&o de um grupo."));
				sender.sendMessage(Utils.color("&6 /group reload&7 - Comando reiniciar o plugin."));
				sender.sendMessage("");
			return true;
		}
		if (args.length == 1) {
			String subComando = args[0];

			if (subComando.equalsIgnoreCase("addperm")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group addperm <grupo> <permissão>."));
				sender.sendMessage("");
				return true;
			}

			if (subComando.equalsIgnoreCase("removeperm")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group removeperm <grupo> <permissão>."));
				sender.sendMessage("");
				return true;
		}

			if (subComando.equalsIgnoreCase("setar")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group setar <nome> <grupo>."));
				sender.sendMessage("");
				return true;
			}

			if (subComando.equalsIgnoreCase("reload")) {
				SmithAPI.getInstance().grupos.reloadConfig();
				sender.sendMessage(Utils.color("&aConfiguração recarregada com sucesso."));
				return true;
			}
			return true;
		}
		if (args.length == 2)

		{

			if (args[0].equalsIgnoreCase("addperm")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group addperm <grupo> <permissão>."));
				sender.sendMessage("");
				return true;
			}

			if (args[0].equalsIgnoreCase("removeperm")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group removeperm <grupo> <permissão>."));
				sender.sendMessage("");
				return true;
			}

			if (args[0].equalsIgnoreCase("setar")) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&c /group setar <nome> <grupo>."));
				sender.sendMessage("");
				return true;
			}

			return true;
		}
		if (args.length == 3) {
			
			if (args[0].equalsIgnoreCase("addperm")) {

				List<String> perms = SmithAPI.getInstance().grupos.getConfig()
						.getStringList("Grupos." + args[1] + ".Permissoes");
				if (perms.contains(args[2])) {
					sender.sendMessage("Mensagens.Perm_Existente");
					return true;
				}
				perms.add(args[2]);
				sender.sendMessage("Mensagens.Perm_Adicionada + args[2]");

				SmithAPI.getInstance().grupos.set("Grupos." + args[1] + ".Permissoes", perms);
				SmithAPI.getInstance().grupos.saveConfig();

				return true;
			}

			if (args[0].equalsIgnoreCase("removeperm")) {

				List<String> perms = SmithAPI.getInstance().grupos.getConfig()
						.getStringList("Grupos." + args[1] + ".Permissoes");
				if (!perms.contains(args[2])) {
					sender.sendMessage("Mensagens.Perm_Nao_Existente");
					return true;
				}

				perms.remove(args[2]);
				sender.sendMessage("Mensagens.Perm_Removida+ args[2]");

				SmithAPI.getInstance().grupos.set("Grupos." + args[1] + ".Permissoes", perms);
				SmithAPI.getInstance().grupos.saveConfig();

				return true;
			}

			if (args[0].equalsIgnoreCase("setar")) {
				Player alvo = Bukkit.getPlayer(args[1]);
				if (alvo == null) {
					if(AccountPlayer.getGrupo(alvo).equalsIgnoreCase(args[2])) {
						sender.sendMessage(Utils.color("&cEsse jogador já é do grupo " + args[2]+ "."));
					} else {
						Logaritimo.createLogGrupo("O jogador " + alvo.getName() + " recebeu o cargo " + args[2] + " foi adicionado pelo" + sender);
					AccountPlayer.setGrupo(alvo,args[2]);
					PlayerPerms.adicionarPerms(alvo);
					sender.sendMessage(Utils.color("&eO grupo do jogador &f" + args[1] + "&e foi setado para &f" + args[2] + "&e."));
					}
				} else {

					if(AccountPlayer.getGrupo(alvo).equalsIgnoreCase(args[2])) {
						sender.sendMessage(Utils.color("&cEsse jogador j& & do grupo " + args[2]+ "."));
					} else {
						AccountPlayer.setGrupo(alvo,args[2]);
						Logaritimo.createLogGrupo("O jogador " + alvo.getName() + " recebeu o cargo " + args[2] + " foi adicionado pelo" + sender);
						PlayerPerms.adicionarPerms(alvo);
						for (Player todos : Bukkit.getOnlinePlayers())
							Utils.SubTitulo("&f" + args[1] + "&a tornou-se &f" + args[2] , todos);	
							alvo.kickPlayer(Utils.color("&c&lSMITH MC\n&c             Parece que você ganhou o cargo: &f" + args[2] + "&c!\n Você recebeu o cargo errado?\n&c Envie um suporte ao membro da equipe em:\n\n&ewww.smithmc.com.br/cargos"));
						sender.sendMessage(Utils.color("&eO grupo do jogador &f" + args[1] + "&e foi setado para &f" + args[2] + "&e."));
					}

				}
			}
		}
			return true;
		}
		final Player jogador = (Player) sender;
		if(AccountPlayer.getGrupo(jogador).equalsIgnoreCase("Diretor") || AccountPlayer.getGrupo(jogador).equalsIgnoreCase("Gerente")) {
		if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /group setar <nome> <grupo>&7 - Comando para setar grupo."));
				sender.sendMessage(Utils.color("&6 /group addperm <grupo> <permissão>&7 - Comando adicionar permissão a um grupo."));
				sender.sendMessage(Utils.color("&6 /group removeperm <grupo> <permissão>&7 - Comando remvoer permissão de um grupo."));
				sender.sendMessage(Utils.color("&6 /group reload&7 - Comando reiniciar o plugin."));
				sender.sendMessage("");
			return true;
		}
		if (args.length == 1) {
			String subComando = args[0];

			if (subComando.equalsIgnoreCase("addperm")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group addperm <grupo> <permiss&o>."));
				jogador.sendMessage("");
				return true;
			}

			if (subComando.equalsIgnoreCase("removeperm")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group removeperm <grupo> <permiss&o>."));
				jogador.sendMessage("");
				return true;
			}

			if (subComando.equalsIgnoreCase("setar")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group setar <nome> <grupo>."));
				jogador.sendMessage("");
				return true;
			}

			if (subComando.equalsIgnoreCase("reload")) {
				SmithAPI.getInstance().grupos.reloadConfig();
				jogador.sendMessage(Utils.color("&aConfiguraçao recarregada com sucesso."));
				return true;
			}
			return true;
		}
		if (args.length == 2)

		{

			if (args[0].equalsIgnoreCase("addperm")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group addperm <grupo> <permiss&o>."));
				jogador.sendMessage("");
				return true;
			}

			if (args[0].equalsIgnoreCase("removeperm")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group removeperm <grupo> <permiss&o>."));
				jogador.sendMessage("");
				return true;
			}

			if (args[0].equalsIgnoreCase("setar")) {
				jogador.sendMessage("");
				jogador.sendMessage(Utils.color("&c /group setar <nome> <grupo>."));
				jogador.sendMessage("");
				return true;
			}

			return true;
		}
		if (args.length == 3) {
			
			if (args[0].equalsIgnoreCase("addperm")) {

				List<String> perms = SmithAPI.getInstance().grupos.getConfig()
						.getStringList("Grupos." + args[1] + ".Permissoes");
				if (perms.contains(args[2])) {
					jogador.sendMessage("Mensagens.Perm_Existente");
					return true;
				}
				perms.add(args[2]);
				jogador.sendMessage("Mensagens.Perm_Adicionada + args[2]");

				SmithAPI.getInstance().grupos.set("Grupos." + args[1] + ".Permissoes", perms);
				SmithAPI.getInstance().grupos.saveConfig();

				return true;
			}

			if (args[0].equalsIgnoreCase("removeperm")) {

				List<String> perms = SmithAPI.getInstance().grupos.getConfig()
						.getStringList("Grupos." + args[1] + ".Permissoes");
				if (!perms.contains(args[2])) {
					jogador.sendMessage("Mensagens.Perm_Nao_Existente");
					return true;
				}

				perms.remove(args[2]);
				jogador.sendMessage("Mensagens.Perm_Removida+ args[2]");

				SmithAPI.getInstance().grupos.set("Grupos." + args[1] + ".Permissoes", perms);
				SmithAPI.getInstance().grupos.saveConfig();

				return true;
			}

			if (args[0].equalsIgnoreCase("setar")) {
				Player alvo = Bukkit.getPlayer(args[1]);
				if (alvo == null) {
					if(AccountPlayer.getGrupo(alvo).equalsIgnoreCase(args[2])) {
						sender.sendMessage(Utils.color("&cEsse jogador já é do grupo " + args[2]+ "."));
					} else {
						Logaritimo.createLogGrupo("O jogador " + alvo.getName() + " recebeu o cargo " + args[2] + " foi adicionado pelo" + sender);
						AccountPlayer.setGrupo(alvo,args[2]);
						PlayerPerms.adicionarPerms(alvo);
					sender.sendMessage(Utils.color("&eO grupo do jogador &f" + args[1] + "&e foi setado para &f" + args[2] + "&e."));
					}
				} else {

					if(AccountPlayer.getGrupo(alvo).equalsIgnoreCase(args[2])) {
						sender.sendMessage(Utils.color("&cEsse jogador já é do grupo " + args[2]+ "."));
					} else {
						AccountPlayer.setGrupo(alvo,args[2]);
						Logaritimo.createLogGrupo("O jogador " + alvo.getName() + " recebeu o cargo " + args[2] + " foi adicionado pelo" + sender);
						PlayerPerms.adicionarPerms(alvo);
						for (Player todos : Bukkit.getOnlinePlayers())
						Utils.SubTitulo("&f" + args[1] + "&a tornou-se &f" + args[2] , todos);	
						alvo.kickPlayer(Utils.color("&c&lSMITH MC\n&c             Parece que você ganhou o cargo: &f" + args[2] + "&c!\n Você recebeu o cargo errado?\n&c Envie um suporte ao membro da equipe em:\n\n&ewww.smithmc.com.br/cargos"));
						sender.sendMessage(Utils.color("&eO grupo do jogador &f" + args[1] + "&e foi setado para &f" + args[2] + "&e."));
					}

				}
			}
		}
		} else {
			jogador.sendMessage(Utils.color("&cVocê precisa do grupo Gerente ou superior para executar este comando."));
			return true;
		}
		return true;
	}

}
