package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ZeroEncoders extends Command {

    public C_ZeroEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_DriveTrainLeft.leftMotors.zeroEnc();
    	Robot.ss_DriveTrainRight.rightMotors.zeroEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}