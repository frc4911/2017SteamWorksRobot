package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestMotorByEncoder extends Command {
	Subsystem subsystem;
	
	DefaultMotor talon;
	double motorConst;
	double encoderConst;
	
	double duration;
	boolean direction;
	String dir;
	
	double targetPos;
	
	double totalBV = 0;
	int BVDataCount = 0;
	
	double startTime;
	double endTime;
	double distTraveled;
	double velocity;
	
	boolean hitTarget = false;
	
    public C_TestMotorByEncoder(Subsystem subsystem, DefaultMotor talon, boolean direction, double targetPos, double duration) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.subsystem = subsystem;
        this.direction = direction;
        this.talon = talon;
        this.targetPos = targetPos;
        this.duration = duration;
        
//        this.motorConst = motorConst;
//        this.encoderConst = encoderConst;
        
        if(direction)
        	dir = "forward";
        else
        	dir = "backward";
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hitTarget = false;
    	
    	talon.zeroEnc();
    	
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    double realEndTime;
    double encError;
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	encError = Math.abs(talon.getEncPos() - targetPos);
    	if(Timer.getFPGATimestamp() < endTime && 
    	   encError > Math.abs(targetPos * 0.1)) {
    				if(direction)
    					talon.spin(0.5);
    				else {
    					talon.spin(-0.5);
    				}
    				
    				Robot.ss_AutoTestStats.putData(subsystem, talon, direction, targetPos, encError);
		    	} else {
		    		talon.stop();
		    	}
		
		realEndTime = Timer.getFPGATimestamp();
		totalBV += talon.getOutputVoltage(false);
		BVDataCount++;
		SmartDashboard.putNumber("current draw "+ dir + " " + talon.getDescription(), talon.getOutputVoltage(false));
		SmartDashboard.putNumber("curr pos " + dir + " " + talon.getDescription(), talon.getEncPos());
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((Timer.getFPGATimestamp() < endTime) && 
 			encError > Math.abs(targetPos * 0.03)) {
    		return false;
    	}
    	
		distTraveled = talon.getEncPos();
		// 3% of error allowed
		if((Math.abs(distTraveled) + (targetPos * 0.03)) >= targetPos) {
			hitTarget = true;
		} else {
			hitTarget = false;
		}
		return true;
	}
    
    // Called once after isFinished returns true
    protected void end() {
    	talon.stop();
    	
    	Robot.ss_AutoTestStats.smartCompletion(hitTarget, (endTime - Timer.getFPGATimestamp()), distTraveled);
    	
    	String desc = talon.getDescription();
    	//ticks/millisecond
//    	velocity = ((distTraveled) / ((realEndTime - startTime) * 1000));
//    	SmartDashboard.putNumber("ave velocity " + dir + " " + desc, velocity);
//    	SmartDashboard.putNumber("dist " + dir + " " + desc, distTraveled);
    	
//    	SmartDashboard.putNumber("ave curr draw " + dir + " " + desc, (totalBV / BVDataCount));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
