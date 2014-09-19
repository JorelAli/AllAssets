package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Tasks.DiscoArmorTask;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.UltraMap;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class CommandDiscoArmor {

	public static UltraMap<UUID, Integer, ItemStack[], String, String, String> map = new UltraMap<UUID, Integer, ItemStack[], String, String, String>();
	
	public CommandDiscoArmor(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "discoarmor", aliases = { "darmor", "partyarmor", "parmor" }, permission = "discoarmor", description = "Gives you flashing armor", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if(map.containsKey(player.getUniqueId())) {
			Bukkit.getScheduler().cancelTask((int) map.get(player.getUniqueId(), 1));
			player.getInventory().setArmorContents((ItemStack[]) map.get(player.getUniqueId(), 2));
			map.remove(player.getUniqueId());
			player.sendMessage(AllAssets.title + "Your disco armor was removed");
		} else {
			BukkitTask i = Bukkit.getScheduler().runTaskTimer(AllAssets.instance(), new DiscoArmorTask(player), 0L, 5L);
			map.put(player.getUniqueId(), i.getTaskId(), player.getInventory().getArmorContents(), null, null, null);
			player.sendMessage(AllAssets.title + "You are now wearing disco armor!");
		}
		return;
	}

}
