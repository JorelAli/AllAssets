package io.github.Skepter.Utils;

public class MathUtils {

	/**
	 * Checks if a number is between another number. E.g. between(5, 2, 10) would give true because 5 is between 2 and 10.
	 * @param i - The number to check
	 * @param min - The smaller number
	 * @param max - The larger number
	 * @return true/false depending if it is or is not
	 */
	public static boolean between(final int i, final int min, final int max) {
		for (int x = min; min < max; x++)
			if (x == i)
				return true;
			else
				continue;
		return false;
	}

	/**
	 * Rounds a number to a certain amount of decimal places
	 * @param d - The number
	 * @param n - The amount of 0's to put
	 * @return a number
	 */
	public static double round(final double d, final int n) {
		final StringBuilder b = new StringBuilder("1");
		for (int i = 0; i < n; i++)
			b.append("0");
		final int dbl = Integer.valueOf(b.toString());
		final double result = Math.round(d * dbl) / dbl;
		return result;
	}
}
