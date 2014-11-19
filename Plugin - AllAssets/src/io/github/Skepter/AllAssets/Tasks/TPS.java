/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.Utils.MathUtils;

public class TPS implements Runnable {
	public static int TICK_COUNT = 0;
	public static long[] TICKS = new long[600];
	public static long LAST_TICK = 0L;

	public static double getTPSperc() {
		final double tps = TPS.getTPS();
		final double lag = Math.round((1.0D - (tps / 20.0D)) * 100.0D);
		return lag;
	}

	public static int getTPSAsInt() {
		return MathUtils.toInt(getTPS());
	}

	public static double getTPS() {
		return getTPS(100);
	}

	public static double getTPS(final int ticks) {
		if (TICK_COUNT < ticks)
			return 20.0D;
		final int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
		long elapsed = 0L;
		try {
			elapsed = System.currentTimeMillis() - TICKS[target];
		} catch (Exception e) {
		}

		return ticks / (elapsed / 1000.0D);
	}

	public static long getElapsed(final int tickID) {
		if ((TICK_COUNT - tickID) >= TICKS.length) {
		}

		final long time = TICKS[(tickID % TICKS.length)];
		return System.currentTimeMillis() - time;
	}

	@Override
	public void run() {
		TICKS[(TICK_COUNT % TICKS.length)] = System.currentTimeMillis();

		TICK_COUNT += 1;
	}
}