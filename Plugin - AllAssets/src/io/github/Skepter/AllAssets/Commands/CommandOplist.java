package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class CommandOplist {

	public CommandOplist(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "oplist", aliases = { "ops" }, permission = "oplist", description = "Lists the players that have op", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final ArrayList<String> operators = new ArrayList<String>();
		for (final OfflinePlayer s : Bukkit.getOperators())
			operators.add(s.getName() + ":" + s.getUniqueId().toString());
		Collections.sort(operators);
		if (!operators.isEmpty())
			args.getSender().sendMessage(TextUtils.title("Operators"));
		for (final String operator : operators) {
			String[] op = operator.split(":");
			if (PlayerUtils.isOnline(op[0]))
				args.getSender().sendMessage(ChatColor.GREEN + "[Online] " + ChatColor.WHITE + op[0] + " (" + op[1] + ")");
			else
				args.getSender().sendMessage(ChatColor.RED + "[Offline] " + ChatColor.WHITE + (op[0] == null ? "Couldn't find username" : op[0]) + " (" + op[1] + ")");
		}

	}

}
