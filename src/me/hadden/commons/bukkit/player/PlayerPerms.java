package me.hadden.commons.bukkit.player;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import me.hadden.commons.bukkit.account.AccountPlayer;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import me.hadden.commons.bukkit.SmithAPI;
import me.hadden.commons.bukkit.config.FilesUtils;

public class PlayerPerms {
	
	private String uuid; 
	public static HashMap<Player, PermissionAttachment> playerPerms = new HashMap<>();
	public static HashMap<String, PermissionAttachment> playerPermissions = new HashMap<String, PermissionAttachment>();
	
	public PlayerPerms(String uuid) {
		this.uuid = uuid;
	}
	
	public static void adicionarPerms(Player player) {

		PermissionAttachment permissionAttachment = player.addAttachment(SmithAPI.getInstance());
		playerPerms.put(player, permissionAttachment);
		setarPerms(player);

	}

	public static void setarPerms(final Player jogador) {
		PermissionAttachment permissionAttachment = playerPerms.get(jogador);
		
		for (String perms : SmithAPI.getInstance().grupos.getConfig()
				.getStringList("grupos." + AccountPlayer.getGrupo(jogador) + ".permissoes")) {

			permissionAttachment.setPermission(perms, true);

		}

	}
	
	public void addPermission(String perm) {
		List<String> permlist = FilesUtils.config.getStringList(uuid + ".permissions");
		if (!hasPermission(perm)) {
			permlist.add(perm);
			FilesUtils.config.set(uuid + ".name", PlayerPerms.getName(uuid));
			FilesUtils.config.set(uuid + ".permissions", permlist);
			FilesUtils.saveFile();
			
			Player p = Bukkit.getPlayer(PlayerPerms.getName(uuid));
			if (p != null) {
				setupPermission(p);
			}
			return;
		}
		return;
	}
	
	public void removePermission(String perm) {
		List<String> permlist = FilesUtils.config.getStringList(uuid + ".permissions");
		if (hasPermission(perm)) {
			permlist.remove(perm);
			FilesUtils.config.set(uuid + ".name", PlayerPerms.getName(uuid));
			FilesUtils.config.set(uuid + ".permissions", permlist);
			FilesUtils.saveFile();
			
			Player p = Bukkit.getPlayer(PlayerPerms.getName(uuid));
			if (p != null) {
				PermissionAttachment attachment = playerPermissions.get(uuid);
				attachment.remove();
				setupPermission(p);
			}
			return;
		}
		return;
	}
	
	public void setupPermission(Player p) {
        PermissionAttachment attachment = p.addAttachment(SmithAPI.getInstance());
        playerPermissions.put(PlayerPerms.getUUID(p.getName()), attachment);
        loadPermissions();
	}
	
	public void loadPermissions() {
		PermissionAttachment attachment = playerPermissions.get(uuid);
		for (String permissions : FilesUtils.config.getStringList(uuid + ".permissions")) {
			attachment.setPermission(permissions, true);
		}
	}
	
	public boolean hasPermission(String perm) {
		List<String> permlist = FilesUtils.config.getStringList(uuid + ".permissions");
		if (permlist.contains(perm)) {
			return true;
		}
		return false;
	}
	
	public String list(Player p) {
		for (String permissions : FilesUtils.config.getStringList(uuid + ".permissions")) {
			if (p.hasPermission(permissions)) {
				return permissions;
			}
		}
		return "";
	}
	
	public void reset() {
		Player p = Bukkit.getPlayer(PlayerPerms.getName(uuid));
		if (p != null) {
			PermissionAttachment attachment = playerPermissions.get(uuid);
			for (String permissions : FilesUtils.config.getStringList(uuid + ".permissions")) {
				attachment.unsetPermission(permissions);
			}
		}
		
		List<String> permlist = FilesUtils.config.getStringList(uuid + ".permissions");
		permlist.clear();
		FilesUtils.config.set(uuid + ".permissions", permlist);
		FilesUtils.saveFile();
	}
	
	public static String getUUID(String name) {
		try {
			String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
			String UUIDJson = IOUtils.toString(new URL(url));
			JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
			return UUIDObject.get("id").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getName(String uuid) {
		try {
			String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
			String nameJson = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameValue.get(nameValue.size() - 1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

}
