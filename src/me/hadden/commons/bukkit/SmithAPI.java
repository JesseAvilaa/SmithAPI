package me.hadden.commons.bukkit;

import lombok.Getter;
import lombok.Setter;
import me.hadden.commons.bukkit.commands.CommandBase;
import me.hadden.commons.bukkit.config.FilesUtils;
import me.hadden.commons.bukkit.config.ConfigUtils;
import me.hadden.commons.bukkit.account.AccountPlayer;
import me.hadden.commons.bukkit.account.MySQL;
import me.hadden.commons.bukkit.events.PlayerListeners;
import me.hadden.commons.bukkit.player.PlayerPerms;
import me.hadden.commons.bukkit.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class SmithAPI extends JavaPlugin implements PluginMessageListener {

    @Getter
    public boolean bungeeMode = true;

    @Getter
    public int all, hub1;

    public static final ModuleLogger LOGGER = new ModuleLogger("SmithAPI");

    public ConfigUtils grupos = new ConfigUtils(this, "grupos.yml");

    @Override
    public void onEnable() {

        grupos.saveDefaultConfig();

        FilesUtils.loadFile();

        saveDefaultConfig();

        AccountPlayer.conectar();

        PlayerListeners.register();

        CommandBase.register();

        for(Player alvo : Bukkit.getOnlinePlayers()) {
            PlayerPerms.adicionarPerms(alvo);
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");
        getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", this);

        LOGGER.info("O plugin foi ativado.");

    }

    @Override
    public void onDisable() {

        MySQL.desconectar();

        LOGGER.info("O plugin foi desativado.");

    }

    public static SmithAPI getInstance() {

        return SmithAPI.getPlugin(SmithAPI.class);

    }


    public static void sendServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(getInstance(), "BungeeCord", out.toByteArray());
    }

    int count = 1;

    @Override
    public void onPluginMessageReceived(String channel, Player receiver, byte[] msg) {
        if (channel.equals("WDL|INIT")) {
            receiver.kickPlayer(
                    Utils.color("&c&lSMITH MC\n \n&cVocê está usando um mod que não é\n&cPermitido na nossa rede de servidores.\n \n&cModificação detectada: &7World Downloader"));
        } else if (channel.equals("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(msg);

            String subChannel = in.readUTF();
            if (subChannel.equals("PlayerCount")) {
                try {
                    String server = in.readUTF();
                    if (server.equals("ALL")) {
                        all = in.readInt();
                    } else if (server.equals("hub-1")) {
                        hub1 = in.readInt();
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
