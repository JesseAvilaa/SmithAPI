package me.hadden.commons.bukkit.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hadden.commons.bukkit.SmithAPI;
import me.hadden.commons.bukkit.account.AccountPlayer;
import me.hadden.commons.bukkit.player.PlayerPerms;
import me.hadden.commons.bukkit.utils.Logaritimo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;

public class PlayerListeners implements Listener {

    public static void register() {

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), SmithAPI.getInstance());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmithAPI.getInstance(), () -> {
            writeCount("ALL");
            writeCount("hub-1");

        }, 0, 20);

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(!AccountPlayer.checkUser(event.getPlayer())) {

            AccountPlayer.registerUser(event.getPlayer());

            Logaritimo.createLogRegisterStatus("O jogador " + event.getPlayer().getName() + " entrou a primeira vez no servidor. seu ip: " + event.getPlayer().getAddress());
        }

        Logaritimo.createLogJoinAndQuitStatus("O jogador " + event.getPlayer().getName() + " entrou no servidor " + Bukkit.getServerName() + " "+ Bukkit.getServer().getOnlinePlayers().size());

        PlayerPerms pm = new PlayerPerms(PlayerPerms.getUUID(event.getPlayer().getName()));
        pm.setupPermission(event.getPlayer());

        PlayerPerms.adicionarPerms(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Logaritimo.createLogJoinAndQuitStatus("O jogador " + event.getPlayer().getName() + " saiu do servidor " + Bukkit.getServerName() + " "+ Bukkit.getServer().getOnlinePlayers().size());
    }

    @SuppressWarnings("unchecked")
    private static void writeCount(String server) {
        Iterator<Player> itr = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
        if (!itr.hasNext()) {
            return;
        }

        Player fake = itr.next();
        if (fake == null) {
            return;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        fake.sendPluginMessage(SmithAPI.getInstance(), "BungeeCord", out.toByteArray());
    }
}
