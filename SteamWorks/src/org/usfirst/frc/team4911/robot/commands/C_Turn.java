package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_Turn extends Command {
	double duration;
	double time;
	double endTime;
	boolean direction;

    public C_Turn(boolean inDirection, double inDuration) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
        direction = inDirection;
        duration = inDuration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//duration = SmartDashboard.getNumber("Turn Duration");
    	endTime = Timer.getFPGATimestamp() + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time = Timer.getFPGATimestamp();
    	Robot.ss_DriveTrain.turn(direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time >= endTime) {
    		return true;
    	} else {
    		return false; 
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_DriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
