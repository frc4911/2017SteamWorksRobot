package org.usfirst.frc.team4911.robot.subsystems.vision;

/**
 * Test program to listen for targeting errors coming from the tracking sender.
 * 
 * @author nathanieltroutman
 */
public class RemoteTrackerMain {

	public static void main(String[] args) {
		RemoteTrackingListener tracker = new RemoteTrackingListener(RemoteTrackingListener.DEFAULT_PORT);
	}

}
