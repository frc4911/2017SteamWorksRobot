package org.usfirst.frc.team4911.robot.subsystems.vision;

import java.net.InetSocketAddress;

public class RemoteTrackerMain {

	public static void main(String[] args) {
		RemoteTrackingListener tracker = new RemoteTrackingListener(new InetSocketAddress("10.49.11.3", RemoteTrackingListener.DEFAULT_PORT));
	}

}
