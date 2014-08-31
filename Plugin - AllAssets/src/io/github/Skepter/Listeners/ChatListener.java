package io.github.Skepter.Listeners;

import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	//line length is around 42 characters

	@EventHandler
	public void onHyperlinkPost(final AsyncPlayerChatEvent event) {
		final Player p = event.getPlayer();
		if (ConfigHandler.instance().features().getBoolean("AntiHyperlink"))
			if (TextUtils.isHyperlink(event.getMessage()) && !p.hasPermission("AllAssets.hyperlink")) {
				CommandLog.addChatLog(ChatColor.BLUE + p.getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage());
				event.setCancelled(true);
			}
		if (ConfigHandler.instance().features().getBoolean("AntiSwear"))
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwearUsingFilter(event.getMessage())) && !p.hasPermission("AllAssets.swear")) {
				CommandLog.addChatLog(ChatColor.BLUE + p.getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage());
				event.setCancelled(true);
			}
	}

	@EventHandler
	public void color(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.instance().features().getBoolean("ChatColor"))
			if (event.getPlayer().hasPermission("AllAssets.chatColor"))
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
	}
}
