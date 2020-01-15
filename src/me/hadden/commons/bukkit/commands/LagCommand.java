package me.hadden.commons.bukkit.commands;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import me.hadden.commons.bukkit.SmithAPI;
import me.hadden.commons.bukkit.account.AccountPlayer;
import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class LagCommand extends CommandBase
{
	
	public static int TickCount = 0;
	public static long[] Ticks = new long[63];
	public static long LastTick = 0L;
	
    public LagCommand() {
        super("lag", new String[0]);
    }
    
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
    	Player p = (Player) sender;
    	if (!(sender instanceof Player)) {
			sender.sendMessage("&cComando apenas em jogo.");
			return true;
		}
		if(AccountPlayer.getGrupo(p).equalsIgnoreCase("Diretor") ||
				AccountPlayer.getGrupo(p).equalsIgnoreCase("Gerente")) {
				if(args.length == 0){
					int ping = ((CraftPlayer) p).getHandle().ping;
					final long RAM_TOTAL = Runtime.getRuntime().totalMemory();
					final long RAM_FREE = Runtime.getRuntime().freeMemory();
					final long RAM_USED = RAM_TOTAL - RAM_FREE;
					final double RAM_USED_PERCENTAGE = (RAM_USED * 100) / RAM_TOTAL;
					double tps = getTPS();
					double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
					double usedCPU = 0.0;
					try {
						usedCPU = getProcessCpuLoad();
					} catch (Exception e) {
					}
					p.sendMessage(Utils.color("&7 "));
					p.sendMessage(Utils.color("&7Coletando informações..."));
					p.sendMessage(Utils.color("&aJogadores online em Rede Smith: " + SmithAPI.all));
					p.sendMessage(Utils.color("  &8- &7Uso de processamento&8: &a"+usedCPU+" %&8, &7lag&8: &a"+(usedCPU >= 90? "sim":"nao")));
					p.sendMessage(Utils.color("  &8- &7Sobrecarregamento geral&8: &a"+RAM_USED_PERCENTAGE+" %&8, &7lag&8: &a"+(RAM_USED_PERCENTAGE >= 90? "sim":"nao")));
					p.sendMessage(Utils.color("  &8- &7Seu ping é de&8: &a"+ping+" ms&8, &7lag&8: &a"+(ping >= 250? "sim":"nao")));
					p.sendMessage(Utils.color("  &8- &7Servidor rodando em&8: &a"+getTPS()+" TPS&8, &7lag&8: &a"+lag+" %"));
					p.sendMessage(getString((RAM_USED_PERCENTAGE >= 90? "sim":"nao"),(ping >= 450? "sim":"nao"),(usedCPU >= 90? "sim":"nao")));
					p.sendMessage(Utils.color("&7 "));
				} else {
				p.sendMessage(Utils.color("&cVocê precisa ser Gerente ou superior para executar este comando."));
			}
			}
		
		return false;
    	
    }
	
	public static double getTPS() {
		return getTPS(100);
	}

	public static double getTPS(final int ticks) {
		if (TickCount < ticks) {
			return 20.0;
		}
		final int target = (TickCount - 1 - ticks) % Ticks.length;
		final long elapsed = System.currentTimeMillis() - Ticks[target];
		if (ticks / (elapsed / 1000.0) > 20.0) {
			return 20.0;
		}
		return ticks / (elapsed / 1000.0);
	}

	public static double getElapsed(final int tickID) {
		if (TickCount - tickID >= Ticks.length) {
			return (TickCount - tickID) * getTPS();
		}
		final long time = Ticks[tickID % Ticks.length];
		return System.currentTimeMillis() - time;
	}

	public void run() {
		Ticks[TickCount % Ticks.length] = System.currentTimeMillis();
		++TickCount;
	}

	public String getString(String lagram,String lagping,String lagprocess){
		if(lagram.equalsIgnoreCase("nao")&&lagping.equalsIgnoreCase("nao")&&lagprocess.equalsIgnoreCase("nao")){
			return "&aO servidor e sua internet estão em excelente estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("nao")&&lagprocess.equalsIgnoreCase("nao")){
			return "&2O servidor e sua internet estão em ótimo estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("nao")){
			return "&6O servidor e sua internet estão em bom estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("sim")){
			return "&4O servidor e sua internet estão em péssimo estado.";
		}
		if(lagram.equalsIgnoreCase("nao")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("nao")){
			return "&cSua internet não está em boas condições.";
		}
		return "";
	}

	public static double getProcessCpuLoad() throws Exception {

		MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
		ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
		AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

		if (list.isEmpty())     return Double.NaN;

		Attribute att = (Attribute)list.get(0);
		Double value  = (Double)att.getValue();

		// usually takes a couple of seconds before we get real values
		if (value == -1.0)      return Double.NaN;
		// returns a percentage value with 1 decimal point precision
		return ((int)(value * 1000) / 10.0);
	}
    	
}
