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
package io.github.skepter.allassets.api.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/** A class to easily debug variables and stuff */
public class Debugger {

	/** Prints the object value in the console. */
	public static void printVariable(final String name, final Object o) {
		printBlank();
		Bukkit.getLogger().info(name + ": " + o);
	}

	/** Prints a String (name). Used to check if a function is being executed
	 * correctly. */
	public static void print(final String name) {
		printBlank();
		Bukkit.getLogger().info(name);
	}

	/** Prints a list in the console. */
	public static void printList(final Collection<?> list) {
		printBlank();
		for (final Object o : list)
			Bukkit.getLogger().info(ChatColor.stripColor(o.toString()));
	}

	/** Prints a Map in the console. */
	public static void printMap(final Map<?, ?> map) {
		printBlank();
		for (final Entry<?, ?> e : map.entrySet())
			if (e.getValue() instanceof Collection) {
				printBlank();
				for (final Object o : (Collection<?>) e.getValue())
					Bukkit.getLogger().info(ChatColor.stripColor(e.getKey().toString() + " : " + o.toString()));
			} else
				Bukkit.getLogger().info(ChatColor.stripColor(e.getKey().toString() + " : " + e.getValue().toString()));
	}

	private static void printBlank() {
		Bukkit.getLogger().info(" ");
	}
}
