package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class CommandBalancetop {

	public CommandBalancetop(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "balancetop", aliases = { "baltop" }, permission = "balancetop", description = "Displays the top balances", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length != 1) {
			ErrorUtils.notEnoughArguments(player);
			return;
		}
		/* I'm certain that there's a MUCH MORE simple method of doing this -.- */
		final Map<String, Double> map = new HashMap<String, Double>();
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
			map.put(p.getName(), AllAssets.instance().economy.getBalance(p.getName()));
		final ValueComparator bvc = new ValueComparator(map);
		final TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(bvc);
		sortedMap.putAll(map);
		player.sendMessage(TextUtils.title("Top balances"));
		final List<String> balanceList = new ArrayList<String>();
		for (final Entry<String, Double> e : sortedMap.entrySet())
			balanceList.add(AllAssets.instance().houseStyleColor + e.getKey() + ": " + e.getValue());
		if (!TextUtils.isInteger(args.getArgs()[0])) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		TextUtils.paginate(player, balanceList, 10, Integer.parseInt(args.getArgs()[0]));
	}

	class ValueComparator implements Comparator<String> {

		Map<String, Double> base;

		public ValueComparator(final Map<String, Double> base) {
			this.base = base;
		}

		@Override
		public int compare(final String a, final String b) {
			if (base.get(a) >= base.get(b))
				return -1;
			else
				return 1;
		}
	}
}