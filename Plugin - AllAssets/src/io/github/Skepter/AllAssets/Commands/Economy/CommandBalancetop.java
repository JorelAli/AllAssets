/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Economy;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

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
			balanceList.add(AllAssets.houseStyleColor + e.getKey() + ": " + e.getValue());
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