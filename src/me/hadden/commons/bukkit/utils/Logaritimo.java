package me.hadden.commons.bukkit.utils;

import me.hadden.commons.bukkit.SmithAPI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logaritimo {

	public static void createLogGrupo(String log) {
		try {
            final File logFile = new File(SmithAPI.getInstance().getDataFolder(), "groupLogs.json");
            final FileWriter fw = new FileWriter(logFile, true);
            final PrintWriter pw = new PrintWriter(fw);
            Date data = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            formater.format( data );
            pw.println("["+data.toString()+"] "+ log);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
		    e.printStackTrace();
        }
	}
	
	public static void createLogRegisterStatus(String log) {
		try {
            final File logFile = new File(SmithAPI.getInstance().getDataFolder(), "statusRegisterLogs.json");
            final FileWriter fw = new FileWriter(logFile, true);
            final PrintWriter pw = new PrintWriter(fw);
            Date data = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            formater.format( data );
            pw.println("["+data.toString()+"] "+ log);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
		    e.printStackTrace();
        }
	}

    public static void createLogJoinAndQuitStatus(String log) {
        try {
            final File logFile = new File(SmithAPI.getInstance().getDataFolder(), "statusJoinAndQuitLogs.json");
            final FileWriter fw = new FileWriter(logFile, true);
            final PrintWriter pw = new PrintWriter(fw);
            Date data = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            formater.format( data );
            pw.println("["+data.toString()+"] "+ log);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
