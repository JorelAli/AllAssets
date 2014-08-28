package io.github.Skepter.Tasks;

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
		return new Double(getTPS()).intValue();
	}

	public static double getTPS() {
		return getTPS(100);
	}

	public static double getTPS(final int ticks) {
		if (TICK_COUNT < ticks)
			return 20.0D;
		final int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
		final long elapsed = System.currentTimeMillis() - TICKS[target];

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