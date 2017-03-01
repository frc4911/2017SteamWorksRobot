package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drives given motor for given amount of time with a given speed
 */
public class C_MoveMotorByTime extends Command {
	Subsystem subsystem;
	
	DefaultMotor talon;
	
	double duration;
	boolean direction;
	
	double startTime;
	double endTime;
	
	double power;
	
    public C_MoveMotorByTime(Subsystem subsystem, DefaultMotor talon, boolean direction, double duration, double power) {
        // Use requires() here to declare subsystem dependencies
    	requires(subsystem);
        this.subsystem = subsystem;
    	this.direction = direction;
        this.talon = talon;
        this.duration = duration;
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	if(Timer.getFPGATimestamp() < endTime) {
			if(direction)
				talon.spin(power);
			else {
				talon.spin(-power);
			}
			
			Robot.ss_AutoTestStats.putData(subsystem, talon, direction);
    	} else {
    		talon.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Timer.getFPGATimestamp() < endTime) {
    		return false;
    	}
        	
		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	talon.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
