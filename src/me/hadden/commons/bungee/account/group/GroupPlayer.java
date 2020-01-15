package me.hadden.commons.bungee.account.group;

import me.hadden.commons.bungee.Bungee;
import me.hadden.commons.bungee.account.AccountPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GroupPlayer {

    public static String get(ProxiedPlayer player) {

       if(AccountPlayer.getGrupo(player).equalsIgnoreCase("Diretor")) {
            return Bungee.color("&bDiretor");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Gerente")) {
           return Bungee.color("&4Gerente");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Dev")) {
           return Bungee.color("&3Desenvolvedor");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Construtor")) {
           return Bungee.color("&aConstrutor");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Admin")) {
           return Bungee.color("&cAdmin");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Moderador")) {
           return Bungee.color("&2Moderador");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Ajudante")) {
           return Bungee.color("&eAjudante");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Youtuber")) {
           return Bungee.color("&cYoutuber");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Fenix")) {
           return Bungee.color("&6Fênix");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Titan")) {
           return Bungee.color("&2Titan");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("End")) {
           return Bungee.color("&bEnd");
       } else if (AccountPlayer.getGrupo(player).equalsIgnoreCase("Membro")) {
           return Bungee.color("&7Membro");
       }

        return "Membro";
    }
}
