package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.PlayerUtils;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class CommandOplist {

	public CommandOplist(final CommandFramework framework) {
		framework.registerCommands(this);
	}
	
	@CommandHandler(name = "oplist", permission = "AllInOne.oplist", description = "Lists the players that have op", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
			final Player player = args.getPlayer();
			final Set<OfflinePlayer> ops = Bukkit.getOperators();
			final ArrayList<String> operators = new ArrayList<String>();
			for (final OfflinePlayer s : ops)
				if (s.toString().contains("CraftPlayer")) {
					final String onlinePlayerName = s.toString().substring(17, s.toString().length() - 1);
					operators.add(onlinePlayerName);
				} else if (s.toString().contains("CraftOfflinePlayer")) {
					final String offlinePlayerName = s.toString().substring(24, s.toString().length() - 1);
					operators.add(offlinePlayerName);
				}
			Collections.sort(operators);
			player.sendMessage(TextUtils.title("Operators"));
			for (final String s : operators) {
				final String u = PlayerUtils.getPlayernameFromUUID(UUID.fromString(s));
				if(PlayerUtils.isOnline(u))
					player.sendMessage(ChatColor.GREEN + "[Online] " + ChatColor.WHITE + u + " (" + s + ")");
				else
					player.sendMessage(ChatColor.RED + "[Offline] " + ChatColor.WHITE + (u == null ? "Couldn't find username" : u) + " (" + s + ")");
			}

	}

}
