/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

public class Utils {

	public static void setMetadata(final Entity entity, final String key, final Object value) {
		entity.setMetadata(key, new FixedMetadataValue(AllAssets.instance(), value));
	}

	public static Object getMetadata(final Entity entity, final String key) {
		return entity.getMetadata(key);
	}

	public static <T> Collection<T> add(final Collection<T> list, final T type) {
		list.add(type);
		return list;
	}

	public static <A, B> Map<B, A> reverse(final Map<A, B> map) {
		final Map<B, A> newMap = new HashMap<B, A>();
		for (final Entry<A, B> entry : map.entrySet())
			newMap.put(entry.getValue(), entry.getKey());
		return newMap;
	}

	public static <B, C, A> DoubleMap<B, C, A> reverseValue1(final DoubleMap<A, B, C> map) {
		final DoubleMap<B, C, A> tMap = new DoubleMap<B, C, A>();
		for (final A key : map.keySet())
			tMap.put(map.getValue1(key), map.getValue2(key), key);
		return tMap;
	}

	public static <C, B, A> DoubleMap<C, B, A> reverseValue2(final DoubleMap<A, B, C> map) {
		final DoubleMap<C, B, A> tMap = new DoubleMap<C, B, A>();
		for (final A key : map.keySet())
			tMap.put(map.getValue2(key), map.getValue1(key), key);
		return tMap;
	}
}
