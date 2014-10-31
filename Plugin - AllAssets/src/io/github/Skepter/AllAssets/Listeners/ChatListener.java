package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.CommandLog;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void playerHyperlinkChatEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("AntiHyperlink"))
			if (TextUtils.isHyperlink(event.getMessage()) && !event.getPlayer().hasPermission("AllAssets.hyperlink")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void playerSwearEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("AntiSwear"))
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwearUsingFilter(event.getMessage())) && !event.getPlayer().hasPermission("AllAssets.swear")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void playerUseColorEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("ChatColor"))
			if (event.getPlayer().hasPermission("AllAssets.chatColor"))
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
	}
}
