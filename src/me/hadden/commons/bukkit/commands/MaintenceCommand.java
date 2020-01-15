package me.hadden.commons.bukkit.commands;

import me.hadden.commons.bukkit.account.AccountPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hadden.commons.bukkit.SmithAPI;

public class MaintenceCommand extends CommandBase
{
    public MaintenceCommand() {
        super("manutencao", new String[0]);
    }
    
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
    	Player p = (Player) sender;
    	if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando apenas em jogo.");
			return true;
		}
			if(p.hasPermission("hadden.cmd.manutencao")){
				if(args.length == 0){
					p.sendMessage("§cUse: /manutencao [on ou off]");
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("on")){
						Bukkit.broadcastMessage("§aO servidor entrou em manutenção. Todos os jogadores serão kikados automaticamente.\n§aVoltamos em breve.");
						Bukkit.setWhitelist(true);
						Bukkit.dispatchCommand(sender, "whitelist on");

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SmithAPI.getInstance(), new Runnable() {
							public void run() {
								for(Player x : Bukkit.getOnlinePlayers()){
									
									if(AccountPlayer.getGrupo(p).equalsIgnoreCase("Diretor") ||
											AccountPlayer.getGrupo(p).equalsIgnoreCase("Gerente")||
											AccountPlayer.getGrupo(p).equalsIgnoreCase("Dev")) {
									} else {
										x.kickPlayer("§c§lREDE SMITH\n\n§cO servidor entrou em manutenção.\n§cVoltamos em breve.\n\n§7Acompanhe-nos pelo twitter: §e@Wakenetwork");
									}
								}
							}
						}, 80L);
					}
					if(args[0].equalsIgnoreCase("off")){
						Bukkit.setWhitelist(false);
						Bukkit.dispatchCommand(sender, "whitelist off");
						Bukkit.broadcastMessage("§cO servidor saiu da manutenção.");
					}
				}
			} else {
				p.sendMessage("§cVocê precisa ser §4Gerente§c ou superior para executar este comando.");
			}
		
		return false;
    	
    }
    	
}
