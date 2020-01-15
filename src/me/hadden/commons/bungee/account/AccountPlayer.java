package me.hadden.commons.bungee.account;

import me.hadden.commons.bungee.Bungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountPlayer {

    public static Connection con;

    public static void conectar() {
        if(MySQL.tipo.equalsIgnoreCase("mysql")) {
            if(jaConectado()) {
            } else {
                MySQL.conectar();
            }
        }
    }

    public static boolean jaConectado(){
        return con != null;
    }

    public static PreparedStatement getStatement(String sql){
        if (AccountPlayer.jaConectado()) {
            try{
                return AccountPlayer.con.prepareStatement(sql);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ResultSet getResult(String sql){
        if (AccountPlayer.jaConectado()) {
            try{
                PreparedStatement ps = getStatement(sql);
                return ps.executeQuery();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean checkUser(ProxiedPlayer p){
        try{
            PreparedStatement ps = getStatement("SELECT * FROM Users WHERE uuid= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            boolean result = rs.next();
            rs.close();
            ps.close();
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    public static String getEmail(ProxiedPlayer p){
        try{
            PreparedStatement ps = getStatement("SELECT * FROM Users WHERE uuid= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String email = rs.getString("email");
            rs.close();
            ps.close();
            return email;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void setEmail(ProxiedPlayer p, String email){
        Bungee.getInstance().getProxy().getScheduler().runAsync(Bungee.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET email= ? WHERE uuid= ?");
                    ps.setString(1, email);
                    ps.setString(2, p.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static String getGrupo(ProxiedPlayer p){
        try{
            PreparedStatement ps = getStatement("SELECT * FROM Users WHERE uuid= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String grupo = rs.getString("grupo");
            rs.close();
            ps.close();
            return grupo;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void setGrupo(ProxiedPlayer p, String grupo){
          Bungee.getInstance().getProxy().getScheduler().runAsync(Bungee.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET grupo= ? WHERE uuid= ?");
                    ps.setString(1, grupo);
                    ps.setString(2, p.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static int getCash(ProxiedPlayer p){
        try{
            PreparedStatement ps = getStatement("SELECT * FROM Users WHERE uuid= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int cash = rs.getInt("cash");
            rs.close();
            ps.close();
            return cash;
        }
        catch(Exception e){

        }
        return -1;
    }

    public static void setCash(ProxiedPlayer p, int quantia){
          Bungee.getInstance().getProxy().getScheduler().runAsync(Bungee.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET cash= ? WHERE uuid= ?");
                    ps.setInt(1, quantia);
                    ps.setString(2, p.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static void removeCash(ProxiedPlayer p, int quantia){
          Bungee.getInstance().getProxy().getScheduler().runAsync(Bungee.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET cash= ? WHERE uuid= ?");
                    ps.setInt(1, getCash(p) - quantia);
                    ps.setString(2, p.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static void addCash(ProxiedPlayer p, int quantia){
          Bungee.getInstance().getProxy().getScheduler().runAsync(Bungee.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET cash= ? WHERE uuid= ?");
                    ps.setInt(1, getCash(p) + quantia);
                    ps.setString(2, p.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


}
