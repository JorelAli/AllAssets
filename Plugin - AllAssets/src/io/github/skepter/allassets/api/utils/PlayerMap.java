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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

/** Custom Map<K,V> class used to manage players.
 * 
 * @author Skepter */
public class PlayerMap<T> implements Listener, Cloneable, Serializable {

	private static final long serialVersionUID = -8222232305054055527L;
	private final Map<UUID, T> map = new HashMap<UUID, T>();

	public PlayerMap(final Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		if (map.containsKey(event.getPlayer().getUniqueId()))
			remove(event.getPlayer());
	}

	public void put(final Player player, final T value) {
		map.put(player.getUniqueId(), value);
	}

	public T get(final Player player) {
		return map.get(player.getUniqueId());
	}

	public void remove(final Player player) {
		map.remove(player.getUniqueId());
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(final Object key) {
		if (map.containsKey(key))
			return true;
		else
			return false;
	}

	public Set<UUID> keySet() {
		return map.keySet();
	}

	public Collection<T> values() {
		return map.values();
	}

	public Set<Entry<UUID, T>> entrySet() {
		return map.entrySet();
	}
}
