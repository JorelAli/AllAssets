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
package io.github.skepter.allassets.utils.utilclasses;

import java.util.Random;

public class MathUtils {

	/** Checks if a number is between another number. E.g. between(5, 2, 10)
	 * would give true because 5 is between 2 and 10.
	 *
	 * @param i - The number to check
	 * @param min - The smaller number
	 * @param max - The larger number
	 * @return true/false depending if it is or is not */
	public static boolean between(final int i, final int min, final int max) {
		for (int x = min; min < max; x++)
			if (x == i)
				return true;
			else
				continue;
		return false;
	}

	/** Returns a random number between a minimum and maximum value
	 *
	 * @param min - The minimum value
	 * @param max - The maximum value
	 * @return a random integer between that number */
	public static int randomBetween(final int min, final int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

	/** Rounds up to next multiple
	 *
	 * @param value - Value to round up
	 * @param factor - Factor (e.g. factor of 10 rounds up to nearest 10)
	 * @return value. */
	public static double roundUp(final double value, final double factor) {
		return factor * Math.ceil(value / factor);
	}

	public static int toInt(final double value) {
		return (int) value;
	}

	public static boolean isInt(final double d) {
		return (d % 1 == 0);
	}

	/** Rounds a number to a certain amount of decimal places */
	public static double round(double value, final int places) {
		if (places < 0)
			return value;
		final long factor = (long) Math.pow(10, places);
		value = value * factor;
		final long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
