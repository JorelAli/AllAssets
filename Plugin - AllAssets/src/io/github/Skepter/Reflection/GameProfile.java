package io.github.Skepter.Reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.entity.Player;

public class GameProfile {
	
	private String name;
	private UUID id;
	
	/** Gets the UUID of a player from the UserCache.json file
	 * Not reliable compared to .getUniqueID and UUIDData file */
	public GameProfile(Player player) throws Exception {
		ReflectionUtils utils = new ReflectionUtils(player);
		Object usercache = Class.forName(utils.packageName + ".UserCache").getConstructor(utils.minecraftServerClass, File.class).newInstance(utils.dedicatedServer, new File("usercache.json"));
		Method method = usercache.getClass().getDeclaredMethod("a", utils.minecraftServerClass, String.class);
		method.setAccessible(true);
		Object gameProfile = method.invoke(usercache, utils.dedicatedServer, player.getName());
		id = (UUID) utils.getPrivateField(gameProfile, "id");
		name = player.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public UUID getUUID() {
		return id;
	}
}
