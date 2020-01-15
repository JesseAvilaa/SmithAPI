package me.hadden.commons.bukkit.commands;

import me.hadden.commons.bukkit.account.AccountPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LimparCommand extends CommandBase
{
    public LimparCommand() {
        super("limpar", new String[0]);
    }
    
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
    	Player p = (Player) sender;
    	if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando apenas em jogo.");
			return true;
		}
		if(AccountPlayer.getGrupo(p).equalsIgnoreCase("Diretor") ||
				AccountPlayer.getGrupo(p).equalsIgnoreCase("Gerente")||
				AccountPlayer.getGrupo(p).equalsIgnoreCase("Dev")) {
				if(args.length == 0){
					Bukkit.broadcastMessage("§eExecutando processo de limpeza no servidor. Pode ser que cause um pouco de lag, mas em instantes irá normalizar.");
					final Runtime r2 = Runtime.getRuntime();
					final long Lused2 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
					System.gc();
					final long Lused3 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
					for(Player s : Bukkit.getOnlinePlayers()){
						if(AccountPlayer.getGrupo(p).equalsIgnoreCase("Dono") ||
								AccountPlayer.getGrupo(p).equalsIgnoreCase("Gerente")||
								AccountPlayer.getGrupo(p).equalsIgnoreCase("Admin")||
								AccountPlayer.getGrupo(p).equalsIgnoreCase("Dev")) {
							s.sendMessage("§bForam removidos "+Long.toString(Lused2 - Lused3) +"§bM RAM do Servidor");	
						}
					}
					Bukkit.broadcastMessage("§aLimpeza concluída!");
				}
			} else {
				p.sendMessage("§cVocê precisa ser §4Gerente§c ou superior para executar este comando.");
			}
		
		return false;
    	
    }
    	
}
