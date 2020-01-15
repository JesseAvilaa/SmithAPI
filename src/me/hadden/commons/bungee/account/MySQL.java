package me.hadden.commons.bungee.account;

import me.hadden.commons.bungee.Bungee;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	public static String host = Bungee.configuration.getString("Dados.host");
	public static String tipo = Bungee.configuration.getString("Dados.tipo");
	public static String porta = Bungee.configuration.getString("Dados.porta");
	public static String database = Bungee.configuration.getString("Dados.database");
	public static String usuario = Bungee.configuration.getString("Dados.usuario");
	public static String senha = Bungee.configuration.getString("Dados.senha");

	public static void conectar(){
		if (!AccountPlayer.jaConectado()) {
			try{
				AccountPlayer.con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + database + "?autoReconnect=true", usuario, senha);
				Bungee.info("§aConexão com MySQL conectada com sucesso.");
			}
			catch (SQLException e){
				Bungee.info("§cErro ao conectar com o MySQL.");
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