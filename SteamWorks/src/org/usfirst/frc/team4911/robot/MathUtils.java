package org.usfirst.frc.team4911.robot;

public final class MathUtils {
	public static double limit(double v, double min, double max) {
		if (v < min) {
			return min;
		} else if (v > max) {
			return max;
		} else {
			return v;
		}
	}
}
