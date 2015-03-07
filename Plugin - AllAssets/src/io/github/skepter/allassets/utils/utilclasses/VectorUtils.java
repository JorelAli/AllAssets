package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils {

	/** Creates a vector from 'from' to 'to' */
	public static Vector getVectorBetween(Location from, Location to) {
		return to.toVector().subtract(from.toVector());
	}
	
}
