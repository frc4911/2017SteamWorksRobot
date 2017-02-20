package org.usfirst.frc.team4911.steamworks.vision;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.opencv.core.Point;

/**
 * This server publishes tracking error over UDP to a listening client.
 * 
 * 
 * @author nathanieltroutman
 */
public class RemoteTrackingServer {
	private static final Logger log = Logger.getLogger(RemoteTrackingServer.class.getName());

	private InetAddress broadcastAddress;
	private TrackingPacketSender sender;
	private int broadcastPort;
	public static int DEFAULT_PORT = 1194;

	private LinkedBlockingDeque<TargetError> queue = new LinkedBlockingDeque<>();

	/**
	 * @param trackingHost
	 *            address of the host sending tracking commands
	 */
	public RemoteTrackingServer(InetAddress clientAddress, int broadcastPort) {
		this.broadcastAddress = clientAddress;
		this.broadcastPort = broadcastPort;

		this.sender = new TrackingPacketSender();
		this.sender.start();
	}

	public void sendError(Point error) {
		queue.add(new TargetError(error.x, error.y, 0));
	}

	public class TrackingPacketSender extends Thread {
		private DatagramSocket socket;

		public TrackingPacketSender() {
			super("TrackingSender-" + broadcastAddress + ":" + broadcastPort);
			log.info("Broadcasting on: " + broadcastAddress + ":" + broadcastPort + ", multi=" + broadcastAddress.isMulticastAddress());
			// don't let this listener keep the JVM alive
			this.setDaemon(true);

			try {
				this.socket = new DatagramSocket();
				this.socket.setBroadcast(true);
			} catch (SocketException e) {
				throw new RuntimeException(
						"Unable to create socket to listen to tracking host: " + broadcastAddress + ":" + broadcastPort,
						e);
			}
		}

		@Override
		public void run() {
			// will have a backing array we can use for the packet
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			// have the packet use the backing array
			DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(), broadcastAddress,
					broadcastPort);
			while (!isInterrupted()) {
				try {
					TargetError error = queue.poll(60, TimeUnit.SECONDS);
					if (error == null) {
						System.err.println("Faield to get a target error to send, exiting.");
						break;
					}

					// since the packet is using the buffer's backing array we
					// only need to pass the buffer around, not the packet
					buffer.rewind();
					writeError(buffer, error);
					buffer.rewind();

					socket.send(packet);
					System.out.println(error);
				} catch (IOException e) {
					// FIXME log the error
					// silently accept any error reading from the socket.
				} catch (InterruptedException e) {
					interrupt();
					break;
				}
			}
		}

		private void writeError(ByteBuffer buffer, TargetError error) {
			buffer.putInt(1); // message type
			buffer.putDouble(error.getX());
			buffer.putDouble(error.getY());
			buffer.putDouble(error.getZ());
		}
	}
}
