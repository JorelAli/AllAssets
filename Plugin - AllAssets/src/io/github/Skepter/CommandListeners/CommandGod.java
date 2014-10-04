package io.github.Skepter.CommandListeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CommandGod implements Listener {

	public List<UUID> godPlayers = new ArrayList<UUID>();

	public CommandGod(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "god", aliases = { "invincible", "invunerable" }, permission = "god", description = "Makes you invincible", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (godPlayers.contains(player.getUniqueId())) {
			godPlayers.remove(player.getUniqueId());
			player.sendMessage(AllAssets.title + "You suddenly feel much more vunerable");
		} else {
			godPlayers.add(player.getUniqueId());
			player.sendMessage(AllAssets.title + "A higher power falls upon you");
		}
		return;
	}

	@EventHandler
	public void playerHurt(final EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			if (godPlayers.contains(player.getUniqueId())) {
				event.setDamage(0);
				event.setCancelled(true);
			}
		}
	}
}
