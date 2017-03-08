package org.usfirst.frc.team4911.steamworks.vision;

public class Timer {
	private long start = 0, stop = 0;

	public static Timer newStarted() {
		Timer timer = new Timer();
		return timer.start();
	}

	public Timer start() {
		start = System.currentTimeMillis();
		return this;
	}
	
	public long stop() {
		stop = System.currentTimeMillis();
		return elappsed();
	}
	
	public long elappsed() {
		return (stop - start);
	}
	
	
}
