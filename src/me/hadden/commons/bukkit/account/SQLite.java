package me.hadden.commons.bukkit.account;

import me.hadden.commons.bukkit.utils.Utils;

import java.io.File;
import java.sql.DriverManager;

public class SQLite {
	
	public static void conectar() {
		File file = new File("plugins/SmithAPI","accounts.db");
		
		String url = "jdbc:sqlite:" + file;
		
		try {
			Class.forName("org.sqlite.JDBC");
			AccountPlayer.con = DriverManager.getConnection(url);
			AccountPlayer.createTable();
			Utils.info("&aConectado ao SQLite com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}