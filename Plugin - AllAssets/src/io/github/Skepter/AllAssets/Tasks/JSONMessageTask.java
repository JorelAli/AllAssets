/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.Reflection.ReflectionUtils;

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
			final ReflectionUtils utils = new ReflectionUtils(player);
			Object packet = utils.emptyPacketPlayOutChat;
			packet = packet.getClass().getConstructor(utils.iChatBaseComponentClass).newInstance(utils.chatSerialize(string));
			utils.getConnection.getClass().getMethod("sendPacket", utils.packetClass).invoke(utils.getConnection, packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}
}
