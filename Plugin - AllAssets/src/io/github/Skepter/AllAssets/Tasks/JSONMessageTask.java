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
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;

import org.bukkit.entity.Player;

public class JSONMessageTask implements Runnable {

	private final Player player;
	private final String string;

	public static String testString = "{\"text\":\"\",\"extra\":[{\"text\":\"Hello\",\"color\":\"gray\",\"bold\":\"true\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://skepter.github.io/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"HAI\"}}}]}";

	public JSONMessageTask(final Player player, final String string) {
		this.player = player;
		this.string = string;
	}

	@Override
	public void run() {
		try {
			final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);
			Object packet = utils.emptyPacketPlayOutChat;
			packet = packet.getClass().getConstructor(utils.iChatBaseComponentClass).newInstance(utils.chatSerialize(string));
			utils.sendOutgoingPacket(packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}
}
