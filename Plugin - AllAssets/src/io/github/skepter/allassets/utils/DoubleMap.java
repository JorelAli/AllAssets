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
	private final Map<A, List<Object>> map = new HashMap<A, List<Object>>();

	public void put(final A key, final B value1, final C value2) {
		//TODO check CURRENT values to allow for custom 'overlapping'
		map.put(key, Arrays.asList(new Object[] { value1, value2 }));
		return;
	}

	public Set<A> keySet() {
		return map.keySet();
	}

	public Set<Entry<A, List<Object>>> entrySet() {
		return map.entrySet();
	}
	
	public DoubleMap<A, B, C> getDoubleMap() {
		return this;
	}

	/** Gets a list of values
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
	
	public Map<A, B> getValue1Map() {
		Map<A, B> tMap = new HashMap<A, B>();
		for(A a : keySet()) {
			tMap.put(a, getValue1(a));
		}
		return tMap;
	}
	
	public Map<A, C> getValue2Map() {
		Map<A, C> tMap = new HashMap<A, C>();
		for(A a : keySet()) {
			tMap.put(a, getValue2(a));
		}
		return tMap;
	}
	
	@SuppressWarnings("unchecked")
	public B getValue1(final A key) {
		return (B) map.get(key).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public C getValue2(final A key) {
		return (C) map.get(key).get(1);
	}

	public void clear() {
		map.clear();
		return;
	}

	public boolean containsKey(final A key) {
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
