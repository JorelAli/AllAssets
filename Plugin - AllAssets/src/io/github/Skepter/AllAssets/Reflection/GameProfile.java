package io.github.Skepter.AllAssets.Reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.entity.Player;

public class GameProfile {
	
	private final String name;
	private final UUID id;
	
	/** Gets the UUID of a player from the UserCache.json file
	 * Not reliable compared to .getUniqueID and UUIDData file */
	public GameProfile(final Player player) throws Exception {
		final ReflectionUtils utils = new ReflectionUtils(player);
		final Object usercache = utils.getNMSClass("UserCache").getConstructor(utils.minecraftServerClass, File.class).newInstance(utils.dedicatedServer, new File("usercache.json"));
		final Method method = usercache.getClass().getDeclaredMethod("a", utils.minecraftServerClass, String.class);
		method.setAccessible(true);
		final Object gameProfile = method.invoke(usercache, utils.dedicatedServer, player.getName());
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
