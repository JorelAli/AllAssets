package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtils {

	public enum GravityType {
		POTION(0.115D), PLAYER(0.667D), FALLING_BLOCK(0.03999999910593033D), ITEM(0.03999999910593033D), SNOWBALL(0.075D);

		private double g;

		private GravityType(double s) {
			g = s;
		}

		public double getValue() {
			return g;
		}
	}

	/** Creates a vector from 'from' to 'to' */
	public static Vector getVectorBetween(Location from, Location to) {
		return to.toVector().subtract(from.toVector());
	}

	/** Calculates the parabolic velocity. Thanks to Hellsing, Sethbling and
	 * Chipdev */
	public static Vector calculateParabolicVelocity(GravityType type, Location from, Location to, int heightGain) {
		// Gravity of a potion
		double gravity = type.getValue();

		// Block locations
		int endGain = to.getBlockY() - from.getBlockY();
		double horizDist = Math.sqrt(distanceSquared(from.toVector(), to.toVector()));

		// Height gain
		int gain = heightGain;
		double maxGain = gain > (endGain + gain) ? gain : (endGain + gain);

		// Solve quadratic equation for velocity
		double a = -horizDist * horizDist / (4 * maxGain);
		double b = horizDist;
		double c = -endGain;
		double slope = -b / (2 * a) - Math.sqrt(b * b - 4 * a * c) / (2 * a);

		// Vertical velocity
		double vy = Math.sqrt(maxGain * gravity);

		// Horizontal velocity
		double vh = vy / slope;

		// Calculate horizontal direction
		int dx = to.getBlockX() - from.getBlockX();
		int dz = to.getBlockZ() - from.getBlockZ();
		double mag = Math.sqrt(dx * dx + dz * dz);
		double dirx = dx / mag;
		double dirz = dz / mag;

		// Horizontal velocity components
		double vx = vh * dirx;
		double vz = vh * dirz;
		return new Vector(vx, vy, vz);
	}

	private static double distanceSquared(Vector from, Vector to) {
		double dx = to.getBlockX() - from.getBlockX();
		double dz = to.getBlockZ() - from.getBlockZ();
		return dx * dx + dz * dz;
	}

}
