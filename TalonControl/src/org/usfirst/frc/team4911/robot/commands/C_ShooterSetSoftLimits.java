package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterSetSoftLimits extends Command {

	boolean onOff;
	double forwardLimit;
	double reverseLimit;
	
    public C_ShooterSetSoftLimits(boolean onOff, double forwardLimit, double reverseLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.onOff = onOff;
    	this.forwardLimit = forwardLimit;
    	this.reverseLimit = reverseLimit;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Shooter3.setSoftLimits(onOff, forwardLimit, reverseLimit);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
