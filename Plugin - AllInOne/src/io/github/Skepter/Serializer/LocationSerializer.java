package io.github.Skepter.Serializer;

import org.bukkit.Location;

public class LocationSerializer {

	public static String LocToString(final org.bukkit.Location loc) {
		final String world = loc.getWorld().getName();
		final double x = loc.getX();
		final double y = loc.getY();
		final double z = loc.getZ();
		final float pitch = loc.getPitch();
		final float yaw = loc.getYaw();
		return world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
	}

	public static Location LocFromString(final String s) {
		final String[] arg = s.split(":");
		final org.bukkit.World world = org.bukkit.Bukkit.getWorld(arg[0]);
		final double x = Double.parseDouble(arg[1]);
		final double y = Double.parseDouble(arg[2]);
		final double z = Double.parseDouble(arg[3]);
		final float pitch = Float.parseFloat(arg[4]);
		final float yaw = Float.parseFloat(arg[5]);
		final Location loc = new org.bukkit.Location(world, x, y, z, yaw, pitch);
		return loc;
	}

}
