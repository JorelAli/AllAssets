package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils {

	/** Creates a vector from 'from' to 'to' */
	public static Vector getVectorBetween(Location from, Location to) {
		return to.toVector().subtract(from.toVector());
	}

	/** Similar to getVectorBetween, but uses the crazy maths stuff to do it.
	 * More performance heavy hence the reason it's 'expensive' */
	public static Vector getVectorBetweenExpensive(Location from, Location to) {
		/* Find Delta of the coordinates (the change between the coords) */
		double dX = from.getX() - to.getX();
		double dY = from.getY() - to.getY();
		double dZ = from.getZ() - to.getZ();

		/* Calculate the yaw and pitch using the delta values by turning
		 * the coords into polar coords and getting the angle from the
		 * locations */
		double yaw = Math.atan2(dZ, dX);
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);

		return new Vector(x, z, y);

	}

}
