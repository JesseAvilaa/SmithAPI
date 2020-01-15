package me.hadden.commons.bungee.commands;

import java.util.HashMap;

import me.hadden.commons.bukkit.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class TellToggleCommand extends Command {

	public TellToggleCommand() {
		super("telltoggle");
	}

	public static HashMap<String, Boolean> toggled;
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (toggled.containsKey(sender.getName())) {
			if (toggled.get(sender.getName())) {
				toggled.put(sender.getName(), false);
				sender.sendMessage(Utils.color("&aRecebimento de mensagens privadas ativado!"));
			} else {
				toggled.put(sender.getName(), true);
				sender.sendMessage(Utils.color("&cRecebimento de mensagens privadas desativado!"));
			}
		} else {
			if (toggled.containsKey(sender.getName())) {
				toggled.put(sender.getName(), true);
				sender.sendMessage(Utils.color("&cRecebimento de mensagens privadas desativado!"));
			} else {
				toggled.put(sender.getName(), false);
				sender.sendMessage(Utils.color("&aRecebimento de mensagens privadas ativado!"));
			}
		}
	}
}
