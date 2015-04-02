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
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.utils.utilclasses.MathUtils;

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
		} catch (final Exception e) {
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
