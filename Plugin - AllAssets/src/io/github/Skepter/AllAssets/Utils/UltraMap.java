/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/** Because I wanted a really big map which can store 5 parts of data */
public class UltraMap<A, B, C, D, E, F> implements Cloneable, Serializable {

	private static final long serialVersionUID = -6951127285757902765L;
	private Map<Object, List<Object>> map = new HashMap<Object, List<Object>>();

	public UltraMap() {

	}

	public void put(final A key, final B value1, final C value2, final D value3, final E value4, final F value5) {
		//TODO check CURRENT values to allow for custom 'overlapping'
		map.put(key, Arrays.asList(new Object[] { value1, value2, value3, value4, value5 }));
		return;
	}

	public Set<Object> keySet() {
		return map.keySet();
	}

	public Set<Entry<Object, List<Object>>> entrySet() {
		return map.entrySet();
	}

	/** I hear you like lists, so I made you a list of lists */
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

	/** Value limit = 5 */
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

	public boolean containsKey(Object key) {
		if (keySet().contains(key))
			return true;
		else
			return false;
	}

}
