package io.github.skepter.allassets.utils.utilclasses;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimeUtils {

	private final long time;

	public TimeUtils(long time) {
		this.time = time;
	}

	public TimeUtils(long past, long present) {
		this.time = past - present;
	}

	public long getTime() {
		return time;
	}

	public long getDays() {
		return MILLISECONDS.toDays(time);
	}

	public long getHours() {
		return MILLISECONDS.toHours(time) - (getDays() * 86400);
	}

	public long getMinutes() {
		return MILLISECONDS.toMinutes(time) - (getDays() * 86400) - (getHours() * 3600);
	}

	public long getSeconds() {
		return MILLISECONDS.toSeconds(time) - (getDays() * 86400) - (getHours() * 3600) - (getMinutes() * 60);
	}

	public long getTotalDays() {
		return MILLISECONDS.toDays(time);
	}

	public long getTotalHours() {
		return MILLISECONDS.toHours(time);
	}

	public long getTotalMinutes() {
		return MILLISECONDS.toMinutes(time);
	}

	public long getTotalSeconds() {
		return MILLISECONDS.toSeconds(time);
	}

	/** Time difference between NOW and dateTime */
	public static String formatDateAtASpecificPointInTime(final long dateTime) {
		return formatDate(new TimeUtils(System.currentTimeMillis(), dateTime).getTime());
	}

	/** Formats date. Format it in the format of 'you have played since <time>'
	 * Doesn't count the 0's
	 * 
	 * @param date - The long date in milliseconds
	 * @return the formatted date in String form */
	public static String formatDate(final long date) {
		TimeUtils utils = new TimeUtils(date);
		final long days = utils.getDays();
		final long hours = utils.getHours();
		final long minutes = utils.getMinutes();
		final long seconds = utils.getSeconds();
		final StringBuilder builder = new StringBuilder();
		if (days != 0)
			builder.append(days + (days == 1 ? " day " : " days "));
		if (hours != 0)
			builder.append(hours + (hours == 1 ? " hour " : " hours "));
		if (minutes != 0)
			builder.append(minutes + (minutes == 1 ? " minute " : " minutes "));
		if (seconds != 0)
			builder.append(seconds + (seconds == 1 ? " second " : " seconds "));
		return builder.toString();
	}

}
