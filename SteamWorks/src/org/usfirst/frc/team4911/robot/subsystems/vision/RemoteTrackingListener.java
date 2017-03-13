package org.usfirst.frc.team4911.robot.subsystems.vision;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A RemoteTrackingListener is a generic concept for a system that listens to a
 * remote host for tracking updates for a "target" and updates the error
 * estimates between the current state and the target state. Note that the
 * tracker's target is determined by the tracker. There is no way to set the
 * target. The tracker implements a polling model where by calls to
 * getCurrentError() are non-blocking with updated error returned when data has
 * been received from the remote host.
 * 
 * For example: A Vision Tracker would be remote host, such as a RaspberryPi,
 * that is using its camera to track the fuel loader and give an error in the X
 * dimension (yaw) between the robots current orientation and the target
 * orientation of having the shooter pointer at the target. The RaspberryPi
 * would handle the vision processing and then simply send the error to the
 * robot and let the robot decide if it wants to try and aim itself to reduce
 * the error.
 * 
 * @author nathanieltroutman
 */
public class RemoteTrackingListener {
	public static int DEFAULT_PORT = 1194;

	public volatile TargetError currentError;
	private TrackingPacketListener listener;
	private int trackingPort;

	public RemoteTrackingListener(int port) {
		// default to zero error on startup
		this.currentError = new TargetError(0, 0, 0);
		this.trackingPort = port;

		this.listener = new TrackingPacketListener();
		this.listener.start();
	}

	public TargetError getCurrentError() {
		return currentError;
	}

	/**
	 * This listener is a separate thread since DatagramSocket.recieve() is a
	 * blocking call. This allows the listener to wait for packets without
	 * blocking the main program flow. Whenever a new packet is received the
	 * error is read from the packet and the tracker's current error is set to a
	 * new value.
	 * 
	 * @author nathanieltroutman
	 */
	private class TrackingPacketListener extends Thread {
		private DatagramSocket socket;

		public TrackingPacketListener() {
			super("TrackingListener-" + trackingPort);
			// don't let this listener keep the JVM alive
			this.setDaemon(false);

			try {
				this.socket = new DatagramSocket(trackingPort);
				SmartDashboard.putNumber("Tracking Port", trackingPort);
			} catch (SocketException e) {
				throw new RemoteTrackerException("Unable to create socket to listen to tracking host on port: " + trackingPort,
						e);
			}
		}

		@Override
		public void run() {
			// will have a backing array we can use for the packet
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			// have the packet use the backing array
			SmartDashboard.putString("Received", "Running...");

			DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
			while (!isInterrupted()) {
				try {
					// reset the buffer before receiving the next packet
					buffer.rewind();
					socket.receive(packet);
					SmartDashboard.putString("Received", "Packet!");
					// since the packet is using the buffer's backing array we
					// only need to pass the buffer around, not the packet
					handlePacket(buffer);
				} catch (IOException e) {
					// FIXME log the error
					// silently accept any error reading from the socket.
				}
			}
		}

		private void handlePacket(ByteBuffer buffer) {
			// TODO Auto-generated method stub
			int messageType = buffer.getInt();
			SmartDashboard.putString("Received", "messageType=" + messageType);
			switch (messageType) {
			case 1:
				handleSimpleTargetErrorPacket(buffer);
				break;
			default:
				// FIXME Log unknown packet type.
				// silently accept the bad packet and do nothing
			}
		}

		private void handleSimpleTargetErrorPacket(ByteBuffer buffer) {
			// read in the X, Y, Z error from the buffer replace the current
			// error object
			TargetError error = new TargetError(buffer.getDouble(), buffer.getDouble(), buffer.getDouble());
			SmartDashboard.putString("Error", error.toString());
			currentError = error;
		}
	}
}
