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
