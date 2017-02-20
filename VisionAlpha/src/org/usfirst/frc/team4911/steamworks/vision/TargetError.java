package org.usfirst.frc.team4911.steamworks.vision;

/**
 * This immutable class holds the error between a tracker's target and the
 * robot's current state.
 * 
 * @author nathanieltroutman
 */
public class TargetError {
	@Override
	public String toString() {
		return "TargetError[x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	private final double x;
	private final double y;
	private final double z;

	public TargetError(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}