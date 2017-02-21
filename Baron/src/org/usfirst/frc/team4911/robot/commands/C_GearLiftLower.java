package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearLiftLower extends Command {
	boolean dir;
	double speed = .5;
	
    public C_GearLiftLower(boolean dir) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_GearLift);
        this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!dir) {
    		speed *= -1; 
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.ss_GearLift.spin(speed);
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
