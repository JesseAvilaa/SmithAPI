package me.hadden.commons.bukkit.commands;

import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hadden.commons.bukkit.player.PlayerPerms;

public class PermsCommand extends CommandBase
{
    public PermsCommand() {
    	super("perm", new String[] { "pex" });
    }
    
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
				sender.sendMessage("");
				return true;
			} else if (args.length == 4) {
				if (args[0].equalsIgnoreCase("user")) {
					if (args[2].equalsIgnoreCase("add")) {
						String player = args[1];
						String permission = args[3];
						PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
						if (!pm.hasPermission(permission)) {
							pm.addPermission(permission);
							sender.sendMessage(Utils.color("&ePermiss�o \"" + permission + "\" adicionada ao jogador " + player + "."));
							pm.loadPermissions();
							return true;
						} else {
							sender.sendMessage(Utils.color("&c" + player + " j� possui a permiss�o \"" + permission + "\"."));
							return true;
						}
					} else if (args[2].equalsIgnoreCase("remove")) {
						String player = args[1];
						String permission = args[3];
						PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
						if (pm.hasPermission(permission)) {
							pm.removePermission(permission);
							sender.sendMessage(Utils.color("&ePermiss�o \"" + permission + "\" removida do jogador " + player + "."));
							pm.loadPermissions();
							return true;
						} else {
							sender.sendMessage(Utils.color("&c" + player + " j� n�o possui a permiss�o \"" + permission + "\"."));
							return true;
						}
					} else {
						sender.sendMessage("");
						sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
						sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
						sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
						sender.sendMessage("");
						return true;
					}
				} else {
					sender.sendMessage("");
					sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
					sender.sendMessage("");
					return true;
				}
			} else if (args.length == 3) {
				if (args[2].equalsIgnoreCase("reset")) {
					String player = args[1];
					PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
					pm.reset();
					sender.sendMessage(Utils.color("&e" + player + " teve suas permiss�es resetadas."));
					pm.loadPermissions();
				} else {
					sender.sendMessage("");
					sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
					sender.sendMessage("");
					return true;
				}
			} else {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
				sender.sendMessage("");
				return true;
		}
			return false;
		}
		Player jogador = (Player) sender;
		
		if (jogador.hasPermission("smith.cmd.perms")) {
			if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
				sender.sendMessage("");
				return true;
			} else if (args.length == 4) {
				if (args[0].equalsIgnoreCase("user")) {
					if (args[2].equalsIgnoreCase("add")) {
						String player = args[1];
						String permission = args[3];
						PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
						if (!pm.hasPermission(permission)) {
							pm.addPermission(permission);
							sender.sendMessage(Utils.color("&ePermiss�o \"" + permission + "\" adicionada ao jogador " + player + "."));
							pm.loadPermissions();
							return true;
						} else {
							sender.sendMessage(Utils.color("&c" + player + " j� possui a permiss�o \"" + permission + "\"."));
							return true;
						}
					} else if (args[2].equalsIgnoreCase("remove")) {
						String player = args[1];
						String permission = args[3];
						PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
						if (pm.hasPermission(permission)) {
							pm.removePermission(permission);
							sender.sendMessage(Utils.color("&ePermiss�o \"" + permission + "\" removida do jogador " + player + "."));
							pm.loadPermissions();
							return true;
						} else {
							sender.sendMessage(Utils.color("&c" + player + " j� n�o possui a permiss�o \"" + permission + "\"."));
							return true;
						}
					} else {
						sender.sendMessage("");
						sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
						sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
						sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
						sender.sendMessage("");
						return true;
					}
				} else {
					sender.sendMessage("");
					sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
					sender.sendMessage("");
					return true;
				}
			} else if (args.length == 3) {
				if (args[2].equalsIgnoreCase("reset")) {
					String player = args[1];
					PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(player));
					pm.reset();
					sender.sendMessage(Utils.color("&e" + player + " teve suas permiss�es resetadas."));
					pm.loadPermissions();
				} else {
					sender.sendMessage("");
					sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
					sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
					sender.sendMessage("");
					return true;
				}
			} else {
				sender.sendMessage("");
				sender.sendMessage(Utils.color("&6 /perm user <player> add <permissao>�7 - Comando para adicionar permiss�o a um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> remove <permissao>�7 - Comando para remover permiss�o de um jogador."));
				sender.sendMessage(Utils.color("&6 /perm user <player> reset�7 - Comando para resetar as permiss�o de um jogador."));
				sender.sendMessage("");
				return true;
			}
	    return true;
	  }else {
			jogador.sendMessage(Utils.color("&cVoc� precisa do grupo Gerente ou superior para executar este comando."));
			return true;
	  }
	} 
}
