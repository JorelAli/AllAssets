/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
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
