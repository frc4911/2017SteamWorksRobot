package org.usfirst.frc.team4911.robot.subsystems.vision;

/**
 * Exception thrown when there is a problem with a remote tracker.
 * 
 * @author nathanieltroutman
 */
public class RemoteTrackerException extends RuntimeException {
	private static final long serialVersionUID = -3599519929909605296L;

	public RemoteTrackerException(String message) {
		super(message);
	}

	public RemoteTrackerException(String message, Throwable cause) {
		super(message, cause);
	}
}
