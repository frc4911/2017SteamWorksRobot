package org.usfirst.frc.team4911.robot.commands;

import static org.usfirst.frc.team4911.robot.MathUtils.limit;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.vision.RemoteTrackingListener;
import org.usfirst.frc.team4911.robot.subsystems.vision.TargetError;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_AutoVisionFollow extends Command {

	/**
	 * If the error is less than this the robot won't turn, this avoids bouncing
	 * back and forth (ultimately a PID or something could be used to handle
	 * turning to a target)
	 */
	private static final double MIN_ERROR_TO_MOVE = 0;

	private boolean isInterrupted;
	private RemoteTrackingListener tracker;

	public C_AutoVisionFollow(double turnRate) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ss_DriveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Intializing Remote Tracker");
		tracker = new RemoteTrackingListener(RemoteTrackingListener.DEFAULT_PORT);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// isInterrupted = false;
		// while (!isInterrupted) {
		TargetError error = tracker.getCurrentError();
		turn(error.getX());
		// }
	}

	private void turn(double amount) {
		if (amount < Math.abs(MIN_ERROR_TO_MOVE)) {
			return;
		}

		// start with zero-point turns
		amount = limit(amount/255.0, -1, 1);

		Robot.ss_DriveTrain.drive(amount, amount);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isInterrupted;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.ss_DriveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		isInterrupted = true;
	}
}
