package org.usfirst.frc.team4911.robot.subsystems.vision;

public class RemoteTrackerMain {

	public static void main(String[] args) {
		RemoteTrackingListener tracker = new RemoteTrackingListener(RemoteTrackingListener.DEFAULT_PORT);
	}

}
