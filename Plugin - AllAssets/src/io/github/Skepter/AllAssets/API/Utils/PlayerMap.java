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
package io.github.Skepter.AllAssets.API.Utils;

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
public class PlayerMap<K, V> implements Listener, Cloneable, Serializable {

	private static final long serialVersionUID = -8222232305054055527L;
	private final Map<UUID, V> map = new HashMap<UUID, V>();

	public PlayerMap(final Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		if (map.containsKey(event.getPlayer().getUniqueId()))
			remove(event.getPlayer());
	}

	public void put(final Player player, final V value) {
		map.put(player.getUniqueId(), value);
	}

	public V get(final Player player) {
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

	public Collection<V> values() {
		return map.values();
	}

	public Set<Entry<UUID, V>> entrySet() {
		return map.entrySet();
	}
}
