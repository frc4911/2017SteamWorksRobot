package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_DelayCaleb extends Command {

	public double currentTime = 0;
	public double endTime = 0;
	public double delayTime = 0;
	
    public C_DelayCaleb(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	delayTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTime = Timer.getFPGATimestamp() + delayTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currentTime = Timer.getFPGATimestamp();
    	
    	if(currentTime >= endTime) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
