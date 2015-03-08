/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
		return new Double(value).intValue();
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
