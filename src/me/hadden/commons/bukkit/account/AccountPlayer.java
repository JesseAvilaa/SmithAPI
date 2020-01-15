package me.hadden.commons.bukkit.account;

import me.hadden.commons.bukkit.SmithAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AccountPlayer {

    public static Connection con;

    public static void conectar() {
        if(MySQL.tipo.equalsIgnoreCase("mysql")) {
            if(jaConectado()) {
            } else {
                MySQL.conectar();
            }
        } else {
            SQLite.conectar();
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

    public static void createTable(){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("CREATE TABLE IF NOT EXISTS Users ( "
                            + "`uuid` varchar(50) NOT NULL, "
                            + "`nick` varchar(16) NOT NULL, "
                            + "`grupo` varchar(50) NOT NULL, "
                            + "`firstLogin` varchar(255) NOT NULL, "
                            + "`lastLogin` varchar(255) NOT NULL, "
                            + "`twitter` varchar(50) NOT NULL, "
                            + "`email` varchar(50) NOT NULL, "
                            + "`cash` int (11) NOT NULL)");
                    ps.executeUpdate();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void registerUser(Player p){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("INSERT INTO Users ("
                            + "uuid, "
                            + "nick, "
                            + "grupo, "
                            + "firstLogin, "
                            + "lastLogin, "
                            + "twitter, "
                            + "email, "
                            + "cash) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    ps.setString(1, p.getUniqueId().toString());
                    ps.setString(2, p.getName());
                    ps.setString(3, "Membro");
                    ps.setString(4, new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(p.getFirstPlayed()).replace("-", "ás"));
                    ps.setString(5, new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(p.getFirstPlayed()).replace("-", "ás"));
                    ps.setString(6, "Nenhum");
                    ps.setString(7, "Nenhum");
                    ps.setString(8, "0");
                    ps.executeUpdate();
                    ps.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static boolean checkUser(Player p){
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


    public static String getEmail(Player p){
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

    public static void setEmail(Player p, String email){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
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

    public static String getGrupo(Player p){
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

    public static void setGrupo(Player p, String grupo){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
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

    public static void setGrupo(OfflinePlayer offp, String grupo){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                try{
                    PreparedStatement ps = getStatement("UPDATE Users SET grupo= ? WHERE uuid= ?");
                    ps.setString(1, grupo);
                    ps.setString(2, offp.getUniqueId().toString());
                    ps.executeUpdate();
                    ps.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static int getCash(Player p){
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

    public static void setCash(Player p, int quantia){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
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

    public static void removeCash(Player p, int quantia){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
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

    public static void addCash(Player p, int quantia){
        Bukkit.getScheduler().runTaskAsynchronously(SmithAPI.getInstance(), new Runnable() {
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
