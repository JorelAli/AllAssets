/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

	/** Rounds a number to a certain amount of decimal places */
	public static double round(double value, int places) {
		if (places < 0)
			return value;
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	/** Formats date. Format it in the format of 'you have played since <time>'
	 * Doesn't count the 0's
	 * 
	 * @param date - The long date in milliseconds
	 * @return the formatted date in String form */
	public static String formatDate(final long date) {
		final long days = TimeUnit.MILLISECONDS.toDays(date);
		final long hours = TimeUnit.MILLISECONDS.toHours(date) - (days * 86400);
		final long minutes = TimeUnit.MILLISECONDS.toMinutes(date) - (days * 86400) - (hours * 3600);
		final long seconds = TimeUnit.MILLISECONDS.toSeconds(date) - (days * 86400) - (hours * 3600) - (minutes * 60);
		StringBuilder builder = new StringBuilder();
		if(days != 0)
			builder.append(days + " days ");
		if(hours != 0)
			builder.append(hours + " hours ");
		if(minutes != 0)
			builder.append(minutes + " minutes ");
		if(seconds != 0)
			builder.append(seconds + " seconds ");
		return builder.toString();
	}

	public static String formatDateAtASpecificPointInTime(final long dateTime) {
		final Date date = new Date();
		final long l = date.getTime() - dateTime;
		return formatDate(l);
	}
}
