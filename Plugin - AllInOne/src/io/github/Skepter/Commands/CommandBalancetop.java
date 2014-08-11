package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;

import java.util.Comparator;
import java.util.HashMap;
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

	@CommandHandler(name = "balance", aliases = { "bal" }, permission = "AllInOne.balance", description = "Displays your balance", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		final HashMap<String, Double> map = new HashMap<String, Double>();
		if (AllInOne.instance().hasVault) {
			for (final OfflinePlayer p : Bukkit.getOfflinePlayers()) {
				map.put(p.getName(), AllInOne.instance().economy.getBalance(p.getName()));
			}
			final ValueComparator bvc = new ValueComparator(map);
			final TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(bvc);
			sortedMap.putAll(map);
			player.sendMessage(TextUtils.title("Top balances"));
			for (final Entry<String, Double> e : sortedMap.entrySet()) {
				player.sendMessage(e.getKey() + e.getValue());// paginate!
			}
		} else {
			ErrorUtils.pluginNotFound(player, "Vault");
		}
	}
}

class ValueComparator implements Comparator<String> {

	Map<String, Double> base;

	public ValueComparator(final Map<String, Double> base) {
		this.base = base;
	}

	@Override
	public int compare(final String a, final String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}
