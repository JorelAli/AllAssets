/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.entity.Player;

public class GameProfile {

	private final String name;
	private final UUID id;

	/** Gets the UUID of a player from the UserCache.json file Not reliable
	 * compared to .getUniqueID and UUIDData file */
	public GameProfile(final Player player) throws Exception {
		final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);
		final Object usercache = utils.getNMSClass("UserCache").getConstructor(utils.minecraftServerClass, File.class).newInstance(utils.dedicatedServer, new File("usercache.json"));
		final Method method = usercache.getClass().getDeclaredMethod("a", utils.minecraftServerClass, String.class);
		method.setAccessible(true);
		final Object gameProfile = method.invoke(usercache, utils.dedicatedServer, player.getName());
		id = (UUID) ReflectionUtils.getPrivateFieldValue(gameProfile, "id");
		name = player.getName();
	}

	public String getName() {
		return name;
	}

	public UUID getUUID() {
		return id;
	}
}
