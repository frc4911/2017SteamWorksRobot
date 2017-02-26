package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestMotorByTime extends Command {
	Subsystem subsystem;
	
	DefaultMotor talon;
	double motorConst;
	double encoderConst;
	
	double duration;
	boolean direction;
	String dir;
		
	double totalBV = 0;
	int BVDataCount = 0;
	
	double startTime;
	double endTime;
	
    public C_TestMotorByTime(Subsystem subsystem, DefaultMotor talon, boolean direction, double duration) {
        // Use requires() here to declare subsystem dependencies
    	requires(subsystem);
        this.subsystem = subsystem;
    	this.direction = direction;
        this.talon = talon;
        this.duration = duration;
        
        if(direction)
        	dir = "forward";
        else
        	dir = "backward";
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
				talon.spin(0.5);
			else {
				talon.spin(-0.5);
			}
    	} else {
    		talon.stop();
    	}
		
		totalBV += talon.getOutputVoltage(false);
		BVDataCount++;
		SmartDashboard.putNumber("current draw "+ dir + " " + talon.getDescription(), talon.getOutputVoltage(false));
		
		Robot.ss_AutoTestStats.putData(subsystem, talon, direction, (endTime - Timer.getFPGATimestamp()));
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
