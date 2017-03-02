package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class C_TestMotorByEncoder extends Command {
	final double MAX_CURRENT = 30.0;
	boolean current;
	
	Subsystem subsystem;
	
	DefaultMotor talon;
	double motorConst;
	double encoderConst;
	
	double duration;
	boolean direction;
	
	double targetPos;
	
	double totalBV = 0;
	int BVDataCount = 0;
	
	double startTime;
	double endTime;
	double distTraveled;
	double velocity;
	
	boolean hitTarget = false;
	
	double power;
	
    public C_TestMotorByEncoder(Subsystem subsystem, 
    		DefaultMotor talon, 
    		boolean direction, 
    		double targetPos, 
    		double duration, 
    		double power) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.subsystem = subsystem;
        this.direction = direction;
        this.talon = talon;
        this.targetPos = targetPos;
        this.duration = duration;
        this.power = power;
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
    	
    	current = true;
//    	if(!talon.hasFollower()) {
//    		current = talon.getOutputCurrent(false) < MAX_CURRENT;
//    	} else {
//    		current = (talon.getOutputCurrent(false) < MAX_CURRENT) && (talon.getOutputCurrent(true) < MAX_CURRENT);
//    	}
    	
    	encError = Math.abs(talon.getEncPos() - targetPos);
    	if((Timer.getFPGATimestamp() < endTime) && 
    	   (encError > Math.abs(targetPos * 0.1)) &&
    	   current) {
			if(direction)
				talon.spin(power);
			else {
				talon.spin(-power);
			}
			
			Robot.ss_AutoTestStats.putData(subsystem, talon, direction, targetPos, encError);
    	} else {
    		talon.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((Timer.getFPGATimestamp() < endTime) && 
 			(encError > Math.abs(targetPos * 0.03)) &&
 			current) {
    		return false;
    	}
    	
		distTraveled = talon.getEncPos();
		// 3% of error allowed
		// TODO: test to see if the hitTarget completion stuff works
		if(((Math.abs(distTraveled) + (targetPos * 0.03)) <= targetPos) &&
				current) {
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
