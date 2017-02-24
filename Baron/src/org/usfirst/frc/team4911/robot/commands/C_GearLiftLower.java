package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearLiftLower extends Command {
	
	double speed = .4;
	
    public C_GearLiftLower(boolean dir) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_GearLift);
        if(!dir) {
    		speed = -speed; 
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_GearLift.gearLiftMotor.spin(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false; // whileheld
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_GearLift.gearLiftMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
