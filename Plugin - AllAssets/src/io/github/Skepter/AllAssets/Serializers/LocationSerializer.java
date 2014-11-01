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
