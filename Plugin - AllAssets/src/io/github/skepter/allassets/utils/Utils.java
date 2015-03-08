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
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;

import java.util.HashMap;
import java.util.List;
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
	
	public static <T> List<T> add(List<T> list, T type) {
		list.add(type);
		return list;
	}
	
	public static <A, B> Map<B, A> reverse(Map<A, B> map) {
		Map<B, A> newMap = new HashMap<B, A>();
		for(Entry<A, B> entry : map.entrySet()) {
			newMap.put(entry.getValue(), entry.getKey());
		}
		return newMap;
	}
}
