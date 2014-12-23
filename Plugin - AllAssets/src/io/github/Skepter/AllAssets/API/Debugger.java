package io.github.Skepter.AllAssets.API;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

public class Debugger {

	public static void printVariable(final String name, final Object o) {
		printBlank();
		System.out.println(name + ": " + o);
	}

	public static void printList(final Collection<?> list) {
		printBlank();
		for (final Object o : list)
			System.out.println(ChatColor.stripColor(o.toString()));
	}

	public static void printMap(final Map<?, ?> map) {
		printBlank();
		for (final Entry<?, ?> e : map.entrySet())
			if (e.getValue() instanceof Collection) {
				printBlank();
				for (final Object o : (Collection<?>) e.getValue())
					System.out.println(ChatColor.stripColor(e.getKey().toString() + " : " + o.toString()));
			} else
				System.out.println(ChatColor.stripColor(e.getKey().toString() + " : " + e.getValue().toString()));
	}

	private static void printBlank() {
		System.out.println(" ");
	}
}
