/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
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