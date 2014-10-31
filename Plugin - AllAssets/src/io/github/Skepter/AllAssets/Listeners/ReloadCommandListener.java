package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ReloadCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		if (cmd.equals("reload") && event.getPlayer().hasPermission("AllAssets.reload")) {
			event.setCancelled(true);
			final String[] args = event.getMessage().split(" ");
			if (args.length == 1) {
				Bukkit.reload();
				event.getPlayer().sendMessage(AllAssets.title + "Reload complete");
				return;
			}
			if (args.length == 2) {
				if (args[1].equalsIgnoreCase("AllAssets")) {
					ErrorUtils.error(event.getPlayer(), "You cannot reload AllAssets. Use /allassets reload instead");
					return;
				}
				try {
					Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args[1]));
					Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args[1]));
					event.getPlayer().sendMessage(AllAssets.title + args[1] + " successfully reloaded");
				} catch (final Exception e) {
					ErrorUtils.pluginNotFound(event.getPlayer(), args[1]);
				}
			}
		}
	}
}
