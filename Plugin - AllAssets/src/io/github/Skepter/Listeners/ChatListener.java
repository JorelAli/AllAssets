package io.github.Skepter.Listeners;

import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	//line length is around 42 characters

	@EventHandler
	public void onHyperlinkPost(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("AntiHyperlink"))
			if (TextUtils.isHyperlink(event.getMessage()) && !event.getPlayer().hasPermission("AllAssets.hyperlink")) {
				CommandLog.addChatLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage());
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void onSwear(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("AntiSwear"))
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwearUsingFilter(event.getMessage())) && !event.getPlayer().hasPermission("AllAssets.swear")) {
				CommandLog.addChatLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage());
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void color(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("ChatColor"))
			if (event.getPlayer().hasPermission("AllAssets.chatColor"))
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
	}
}
