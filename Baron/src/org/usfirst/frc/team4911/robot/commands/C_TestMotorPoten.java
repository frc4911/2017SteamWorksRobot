package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestMotorPoten extends Command {
	CANTalon motor;
	AnalogPotentiometer encoder;
	
	double duration;
	boolean direction;
	String dir;
	String name;
	
	double targetPos;
	double range;
	
	double normBusVoltage;
	final double percentErrorBV = 0.6; //5% error
	double totalBV = 0;
	int BVDataCount = 0;
	
	double startTime;
	double endTime;
	double distTravelled;
	double velocity;
	
    public C_TestMotorPoten(Subsystem subsystem, CANTalon motor, AnalogPotentiometer encoder, boolean direction, 
    				   double targetPos, double duration, double normBusVoltage, String name, double range) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.direction = direction;
        this.motor = motor;
        this.encoder = encoder;
        this.targetPos = targetPos;
        this.normBusVoltage = normBusVoltage;
        this.duration = duration;
        this.name = name;
        this.range = range;
        
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
    
    private boolean checkBusVoltage() {
    	if((totalBV / BVDataCount) > (percentErrorBV + normBusVoltage))
    		return false;
    	else if((totalBV / BVDataCount) < Math.abs(percentErrorBV - normBusVoltage))
    		return false;
    	else
    		return true;
    }

    // Called repeatedly when this Command is scheduled to run
    double realEndTime;
    protected void execute() {
    	if(Timer.getFPGATimestamp() < endTime && 
    	   Math.abs(encoder.get() - targetPos) > Math.abs(range * 0.1)) {
			if(direction)
				motor.set(0.5);
			else {
				motor.set(-0.5);
			}
    	} else {
    		motor.set(0);
    	}
		
		realEndTime = Timer.getFPGATimestamp();
		totalBV += motor.getBusVoltage();
		BVDataCount++;
		SmartDashboard.putNumber("current draw "+ dir + " " + name, motor.getBusVoltage());
		SmartDashboard.putNumber("curr pos " + dir + " " + name, encoder.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(encoder.get() - targetPos) > Math.abs(range * 0.1))
    		return false;
    	else {
    		distTravelled = encoder.get();
    		motor.set(0);
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ticks/millisecond
    	velocity = ((distTravelled) / ((realEndTime - startTime) * 1000));
    	SmartDashboard.putNumber("ave velocity " + dir + " " + name, velocity);
    	SmartDashboard.putNumber("dist " + dir + " " + name, distTravelled);
    	
    	//3% error allowed
    	if((Math.abs(distTravelled) + (range * 0.03)) >= targetPos) {
    		SmartDashboard.putString(("dist reached " + dir + " " + name), "yes");
    	} else {
    		SmartDashboard.putString(("dist reached " + dir + " " + name), "no");
    	}
    	
    	SmartDashboard.putNumber("ave curr draw " + dir + " " + name, (totalBV / BVDataCount));
    	if(checkBusVoltage()) {
    		SmartDashboard.putString(("curr draw " + dir + " " + name), "normal");
    	} else {
    		SmartDashboard.putString(("curr draw " + dir + " " + name), "high/low");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
