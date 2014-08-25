package io.github.Skepter.Utils;

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

	/** Rounds a number to a certain amount of decimal places
	 * 
	 * @param d - The number
	 * @param n - The amount of 0's to put
	 * @return a number */
	public static double round(final double d, final int n) {
		final StringBuilder b = new StringBuilder("1");
		for (int i = 0; i < n; i++)
			b.append("0");
		final int dbl = Integer.valueOf(b.toString());
		final double result = Math.round(d * dbl) / dbl;
		return result;
	}

	/** Formats date. Currently (other methods haven't been tested), it can
	 * format it in the format of 'you have played since <time>'
	 * @param date - The long date in milliseconds
	 * @return the formatted date in String form */
	public static String formatDate(long date) {
		final long days = TimeUnit.MILLISECONDS.toDays(date);
		final long hours = TimeUnit.MILLISECONDS.toHours(date) - (days * 86400);
		final long minutes = TimeUnit.MILLISECONDS.toMinutes(date) - (days * 86400) - (hours * 3600);
		final long seconds = TimeUnit.MILLISECONDS.toSeconds(date) - (days * 86400) - (hours * 3600) - (minutes * 60);
		return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds";
	}
	
	public static String formatDateAtASpecificPointInTime(long dateTime) {
		Date date = new Date();
		long l =  date.getTime() - dateTime;
		return formatDate(l);
	}
}
