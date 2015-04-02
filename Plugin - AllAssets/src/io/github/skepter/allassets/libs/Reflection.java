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
package io.github.skepter.allassets.libs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;

public class Reflection {

	public static String getVersion() {
		final String name = Bukkit.getServer().getClass().getPackage().getName();
		final String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}

	public static Class<?> getNMSClass(final String className) {
		final String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static Class<?> getOBCClass(final String className) {
		final String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static Object getHandle(final Object obj) {
		try {
			return getMethod(obj.getClass(), "getHandle").invoke(obj);
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Field getField(final Class<?> clazz, final String name) {
		try {
			final Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Method getMethod(final Class<?> clazz, final String name, final Class<?>... args) {
		for (final Method m : clazz.getMethods())
			if (m.getName().equals(name) && ((args.length == 0) || ClassListEqual(args, m.getParameterTypes()))) {
				m.setAccessible(true);
				return m;
			}
		return null;
	}

	public static boolean ClassListEqual(final Class<?>[] l1, final Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length)
			return false;
		for (int i = 0; i < l1.length; i++)
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		return equal;
	}

}
