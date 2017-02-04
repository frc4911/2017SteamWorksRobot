package org.usfirst.frc.team4911.robot.subsystems;

public interface Motor {
	void spin(double pow);
	void moveByInput(double input);
	void moveToEncPos(double pos, double pow);
	void zeroEnc();
	void setBrakeMode(boolean set);
	void stop();
}
