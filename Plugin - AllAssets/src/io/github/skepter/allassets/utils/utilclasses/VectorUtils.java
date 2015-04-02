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
package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils {

	/** Creates a vector from 'from' to 'to' */
	public static Vector getVectorBetween(final Location from, final Location to) {
		return to.toVector().subtract(from.toVector());
	}

	/** Similar to getVectorBetween, but uses the crazy maths stuff to do it.
	 * More performance heavy hence the reason it's 'expensive' */
	public static Vector getVectorBetweenExpensive(final Location from, final Location to) {
		/* Find Delta of the coordinates (the change between the coords) */
		final double dX = from.getX() - to.getX();
		final double dY = from.getY() - to.getY();
		final double dZ = from.getZ() - to.getZ();

		/* Calculate the yaw and pitch using the delta values by turning
		 * the coords into polar coords and getting the angle from the
		 * locations */
		final double yaw = Math.atan2(dZ, dX);
		final double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

		final double x = Math.sin(pitch) * Math.cos(yaw);
		final double y = Math.sin(pitch) * Math.sin(yaw);
		final double z = Math.cos(pitch);

		return new Vector(x, z, y);

	}

}
