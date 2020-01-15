package me.hadden.commons.bukkit.commands;

import java.util.*;

import me.hadden.commons.bukkit.SmithAPI;
import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.command.*;

import org.bukkit.*;

public abstract class CommandBase extends Command
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CommandBase(final String name, final String... aliases) {
        super(name);
        this.setAliases((List)Arrays.asList(aliases));
        try {
            final SimpleCommandMap commandMap = (SimpleCommandMap)Bukkit.getServer().getClass().getDeclaredMethod("getCommandMap", (Class<?>[])new Class[0]).invoke(Bukkit.getServer(), new Object[0]);
            commandMap.register(name, "comandos", (Command)this);
        }
        catch (ReflectiveOperationException e) {
            Utils.info("&4[ERRO] Erro nos comandos");
        }
    }
    
    public static void register() {
        new PermsCommand();
        if(!SmithAPI.isBungeeMode()) {
            new GroupCommand();
        }
        new EmailCommand();
        new LimparCommand();
        new MaintenceCommand();
        new LagCommand();
    }
}
