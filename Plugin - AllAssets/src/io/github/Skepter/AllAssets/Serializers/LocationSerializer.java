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
package io.github.Skepter.AllAssets.Serializers;

import io.github.Skepter.AllAssets.Utils.MathUtils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer {

	/** Converts a Location to a String */
	public static String LocToString(final Location loc) {
		final String world = loc.getWorld().getName();
		final double x = MathUtils.round(loc.getX(), 2);
		final double y = MathUtils.round(loc.getY(), 2);
		final double z = MathUtils.round(loc.getZ(), 2);
		final double pitch = MathUtils.round(loc.getPitch(), 2);
		final double yaw = MathUtils.round(loc.getYaw(), 2);
		return world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
	}

	/** Converts a String to a Location */
	public static Location LocFromString(final String s) {
		final String[] arg = s.split(":");
		final World world = Bukkit.getWorld(arg[0]);
		final double x = Double.parseDouble(arg[1]);
		final double y = Double.parseDouble(arg[2]);
		final double z = Double.parseDouble(arg[3]);
		final float pitch = Float.parseFloat(arg[4]);
		final float yaw = Float.parseFloat(arg[5]);
		final Location loc = new Location(world, x, y, z, yaw, pitch);
		return loc;
	}

	/** Converts a Location to a String, but doesn't do pitch and yaw */
	public static String SimpleLocToString(final Location loc) {
		final String world = loc.getWorld().getName();
		final double x = loc.getX();
		final double y = loc.getY();
		final double z = loc.getZ();
		return world + ":" + x + ":" + y + ":" + z;
	}

	/** Converts a String to a Location, but doesn't do pitch and yaw */
	public static Location SimpleLocFromString(final String s) {
		final String[] arg = s.split(":");
		final World world = Bukkit.getWorld(arg[0]);
		final double x = Double.parseDouble(arg[1]);
		final double y = Double.parseDouble(arg[2]);
		final double z = Double.parseDouble(arg[3]);
		final Location loc = new Location(world, x, y, z);
		return loc;
	}

}
