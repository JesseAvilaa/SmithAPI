package me.hadden.commons.bukkit.account;

import me.hadden.commons.bukkit.SmithAPI;
import me.hadden.commons.bukkit.utils.Utils;

import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQL {

	public static String host = SmithAPI.getInstance().getConfig().getString("Dados.host");
	public static String tipo = SmithAPI.getInstance().getConfig().getString("Dados.tipo");
	public static String porta = SmithAPI.getInstance().getConfig().getString("Dados.porta");
	public static String database = SmithAPI.getInstance().getConfig().getString("Dados.database");
	public static String usuario = SmithAPI.getInstance().getConfig().getString("Dados.usuario");
	public static String senha = SmithAPI.getInstance().getConfig().getString("Dados.senha");

	public static void conectar(){
		if (!AccountPlayer.jaConectado()) {
			try{
				AccountPlayer.con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + database + "?autoReconnect=true", usuario, senha);
				AccountPlayer.createTable();
				Utils.info("§aConexão com MySQL conectada com sucesso.");
			}
			catch (SQLException e){
				SQLite.conectar();
				Utils.info("§cErro ao conectar com o MySQL, conectando ao SQLite.");
			}
		}
	}
	
	public static void desconectar(){
		try{
			AccountPlayer.con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}