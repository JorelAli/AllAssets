package io.github.skepter.allassets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageListenerImplementation implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		player.sendMessage(message.toString());
        Bukkit.getLogger().info("Got Plugin Message on " + channel + " from " + player.getName() + " messge was: " + message.toString());
	}

}
