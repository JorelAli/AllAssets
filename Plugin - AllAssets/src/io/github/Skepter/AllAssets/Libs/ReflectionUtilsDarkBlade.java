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
package io.github.Skepter.AllAssets.Libs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

/** ReflectionUtil v1.1
 * 
 * You are welcome to use it, modify it and redistribute it under the condition
 * to not claim this class as your own
 * 
 * @author DarkBlade12 */
public abstract class ReflectionUtilsDarkBlade {
	private static final Map<Class<?>, Class<?>> CORRESPONDING_TYPES = new HashMap<Class<?>, Class<?>>();

	static {
		CORRESPONDING_TYPES.put(Byte.class, byte.class);
		CORRESPONDING_TYPES.put(Short.class, short.class);
		CORRESPONDING_TYPES.put(Integer.class, int.class);
		CORRESPONDING_TYPES.put(Long.class, long.class);
		CORRESPONDING_TYPES.put(Character.class, char.class);
		CORRESPONDING_TYPES.put(Float.class, float.class);
		CORRESPONDING_TYPES.put(Double.class, double.class);
		CORRESPONDING_TYPES.put(Boolean.class, boolean.class);
	}

	public enum DynamicPackage {
		MINECRAFT_SERVER {
			@Override
			public String toString() {
				return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().substring(23, 30);
			}
		},
		CRAFTBUKKIT {
			@Override
			public String toString() {
				return Bukkit.getServer().getClass().getPackage().getName();
			}
		};
	}

	public static class FieldEntry {
		String key;
		Object value;

		public FieldEntry(final String key, final Object value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return this.key;
		}

		public Object getValue() {
			return this.value;
		}
	}

	private static Class<?> getPrimitiveType(final Class<?> clazz) {
		return CORRESPONDING_TYPES.containsKey(clazz) ? CORRESPONDING_TYPES.get(clazz) : clazz;
	}

	private static Class<?>[] toPrimitiveTypeArray(final Object[] objects) {
		final int a = objects != null ? objects.length : 0;
		final Class<?>[] types = new Class<?>[a];
		for (int i = 0; i < a; i++)
			types[i] = getPrimitiveType(objects[i].getClass());
		return types;
	}

	private static Class<?>[] toPrimitiveTypeArray(final Class<?>[] classes) {
		final int a = classes != null ? classes.length : 0;
		final Class<?>[] types = new Class<?>[a];
		for (int i = 0; i < a; i++)
			types[i] = getPrimitiveType(classes[i]);
		return types;
	}

	private static boolean equalsTypeArray(final Class<?>[] a, final Class<?>[] o) {
		if (a.length != o.length)
			return false;
		for (int i = 0; i < a.length; i++)
			if (!a[i].equals(o[i]) && !a[i].isAssignableFrom(o[i]))
				return false;
		return true;
	}

	public static Class<?> getClass(final String name, final DynamicPackage pack, final String subPackage) throws Exception {
		return Class.forName(pack + ((subPackage != null) && (subPackage.length() > 0) ? "." + subPackage : "") + "." + name);
	}

	public static Class<?> getClass(final String name, final DynamicPackage pack) throws Exception {
		return getClass(name, pack, null);
	}

	public static Constructor<?> getConstructor(final Class<?> clazz, final Class<?>... paramTypes) {
		final Class<?>[] t = toPrimitiveTypeArray(paramTypes);
		for (final Constructor<?> c : clazz.getConstructors()) {
			final Class<?>[] types = toPrimitiveTypeArray(c.getParameterTypes());
			if (equalsTypeArray(types, t))
				return c;
		}
		return null;
	}

	public static Object newInstance(final Class<?> clazz, final Object... args) throws Exception {
		return getConstructor(clazz, toPrimitiveTypeArray(args)).newInstance(args);
	}

	public static Object newInstance(final String name, final DynamicPackage pack, final String subPackage, final Object... args) throws Exception {
		return newInstance(getClass(name, pack, subPackage), args);
	}

	public static Object newInstance(final String name, final DynamicPackage pack, final Object... args) throws Exception {
		return newInstance(getClass(name, pack, null), args);
	}

	public static Method getMethod(final String name, final Class<?> clazz, final Class<?>... paramTypes) {
		final Class<?>[] t = toPrimitiveTypeArray(paramTypes);
		for (final Method m : clazz.getMethods()) {
			final Class<?>[] types = toPrimitiveTypeArray(m.getParameterTypes());
			if (m.getName().equals(name) && equalsTypeArray(types, t))
				return m;
		}
		return null;
	}

	public static Object invokeMethod(final String name, final Class<?> clazz, final Object obj, final Object... args) throws Exception {
		return getMethod(name, clazz, toPrimitiveTypeArray(args)).invoke(obj, args);
	}

	public static Field getField(final String name, final Class<?> clazz) throws Exception {
		return clazz.getDeclaredField(name);
	}

	public static Object getValue(final String name, final Object obj) throws Exception {
		final Field f = getField(name, obj.getClass());
		f.setAccessible(true);
		return f.get(obj);
	}

	public static void setValue(final Object obj, final FieldEntry entry) throws Exception {
		final Field f = getField(entry.getKey(), obj.getClass());
		f.setAccessible(true);
		f.set(obj, entry.getValue());
	}

	public static void setValues(final Object obj, final FieldEntry... entrys) throws Exception {
		for (final FieldEntry f : entrys)
			setValue(obj, f);
	}
}
