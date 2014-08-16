package io.github.Skepter.Listeners;

import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onHyperlinkPost(final AsyncPlayerChatEvent event) {
		final Player p = event.getPlayer();
		if (ConfigHandler.instance().features().getBoolean("AntiHyperlink")) {
			if (TextUtils.isHyperlink(event.getMessage()) && !p.hasPermission("AllInOne.hyperlink")) {
				CommandLog.addChatLog(ChatColor.BLUE + p.getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage());
				event.setCancelled(true);
			}
		}
		if(ConfigHandler.instance().features().getBoolean("AntiSwear")) {
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwear(event.getMessage())) && !p.hasPermission("AllInOne.swear")) {
				CommandLog.addChatLog(ChatColor.BLUE + p.getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage());
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void color(final AsyncPlayerChatEvent event) {
		if(ConfigHandler.instance().features().getBoolean("ChatColor")) {
			if (event.getPlayer().hasPermission("AllInOne.chatColor")) {
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
			}
		}
	}

	@EventHandler
	public void translatePlayer(final AsyncPlayerChatEvent event) {
		if(ConfigHandler.instance().features().getBoolean("PlayerTranslator")) {
			if (event.getMessage().contains("{player}") && event.getPlayer().hasPermission("AllInOne.playerChat")) {
				event.setCancelled(true);
				if (event.getPlayer().hasPermission("AllInOne.chatColor")) {
					event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
				}
				for (final Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(event.getMessage().replace("{player}", p.getName()));
				}
			}
		}
	}
}
