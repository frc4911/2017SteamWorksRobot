package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Test extends Command {

	public double currentTime = 0;
	public double endTime = 0;
	public double testTime = 1;
	
    public C_Test() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Test);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTime = Timer.getFPGATimestamp() + testTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_Test.spin(0.5);
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
    	Robot.ss_Test.spin(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
