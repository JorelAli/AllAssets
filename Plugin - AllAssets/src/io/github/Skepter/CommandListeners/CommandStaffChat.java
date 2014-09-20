package io.github.Skepter.CommandListeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Tasks.StaffChatTask;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandStaffChat implements Listener {

	public CommandStaffChat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private final List<UUID> players = new ArrayList<UUID>();

	@CommandHandler(name = "staffchat", aliases = { "sc", "adminchat", "ac", "a" }, permission = "staffchat", description = "Toggles the staff chat", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (players.contains(player.getUniqueId())) {
			players.remove(player.getUniqueId());
			player.sendMessage(AllAssets.title + "Staff chat is now off");
		} else {
			players.add(player.getUniqueId());
			player.sendMessage(AllAssets.title + "Staff chat is now on");
		}
	}

	@EventHandler
	public void onChat(final AsyncPlayerChatEvent event) {
		Bukkit.getScheduler().runTask(AllAssets.instance(), new StaffChatTask(event, event.getPlayer(), players, event.getMessage()));
	}

}
