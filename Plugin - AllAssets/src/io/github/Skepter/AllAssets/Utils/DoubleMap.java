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
package io.github.skepter.allassets.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/** Because I wanted a really big map which can store more data */
public class DoubleMap<A, B, C> implements Cloneable, Serializable {

	private static final long serialVersionUID = -6951127285757902765L;
	private final Map<Object, List<Object>> map = new HashMap<Object, List<Object>>();

	public void put(final A key, final B value1, final C value2) {
		//TODO check CURRENT values to allow for custom 'overlapping'
		map.put(key, Arrays.asList(new Object[] { value1, value2}));
		return;
	}

	public Set<Object> keySet() {
		return map.keySet();
	}

	public Set<Entry<Object, List<Object>>> entrySet() {
		return map.entrySet();
	}

	/** Gets a list of values
	 * 
	 * @return The list of values as a List of objects */
	public List<List<Object>> values() {
		final List<List<Object>> list = new ArrayList<List<Object>>();
		for (final Object key : keySet())
			list.add(map.get(key));
		return list;
	}

	public void remove(final A key) {
		map.remove(key);
		return;
	}

	/** Gets the object from a key and value
	 * 
	 * @param key - The key to 'search'
	 * @param value - The value to find. Max = 5
	 * @return The object stored in the UltraMap */
	public Object get(final A key, final int value) {
		if ((value > 5) || (value == 0))
			return null;
		else
			return map.get(key).get(value - 1);
	}

	public void clear() {
		map.clear();
		return;
	}

	public boolean containsKey(final Object key) {
		if (keySet().contains(key))
			return true;
		else
			return false;
	}

	/** Looks for a value - pretty normal. Enter the int value to lookup
	 * 
	 * @param value
	 * @return */
	public boolean containsValue(final Object objectToFind, final int value) {

		return false;
	}

	/** Similar to containsValue() but looks through the entire map
	 * 
	 * @param objectToFind
	 * @return */
	public boolean containsValueDeep(final Object objectToFind) {
		for (final List<Object> list : values())
			for (final Object object : list)
				if (objectToFind.equals(object))
					return true;
		return false;
	}

}
