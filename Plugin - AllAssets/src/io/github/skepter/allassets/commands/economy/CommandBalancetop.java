/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.economy;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

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

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "balancetop", aliases = { "baltop" }, permission = "balancetop", description = "Displays the top balances")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
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
				balanceList.add(Strings.HOUSE_STYLE_COLOR + e.getKey() + ": " + e.getValue());
			if (!TextUtils.isInteger(args.getArgs()[0])) {
				ErrorUtils.notAnInteger(player);
				return;
			}
			TextUtils.paginate(player, balanceList, 10, Integer.parseInt(args.getArgs()[0]));
		}
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
