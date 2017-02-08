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
public class C_TestMotorEncoder extends Command {
	DefaultMotor talon;
	double motorConst;
	double encoderConst;
	
	double duration;
	boolean direction;
	String dir;
	String name;
	
	double targetPos;
	
	double normBusVoltage;
	final double percentErrorBV = 0.6; //5% error
	double totalBV = 0;
	int BVDataCount = 0;
	
	double startTime;
	double endTime;
	double distTravelled;
	double velocity;
	
	boolean driveTrain;
	
    public C_TestMotorEncoder(Subsystem subsystem, DefaultMotor talon, double motorConst, double encoderConst, boolean direction, 
    				   		  double targetPos, double duration, double normBusVoltage, String name, boolean driveTrain) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.direction = direction;
        this.talon = talon;
        this.targetPos = targetPos;
        this.normBusVoltage = normBusVoltage;
        this.duration = duration;
        this.name = name;
        
        this.motorConst = motorConst;
        this.encoderConst = encoderConst;
        
        this.driveTrain = driveTrain;
        
        if(direction)
        	dir = "forward";
        else
        	dir = "backward";
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	talon.zeroEnc();
    	
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + duration;
    	
    	if(driveTrain) {
//        	Robot.ss_DriveTrain.DriveMotorFrontLeft.enableBrakeMode(false);
//        	Robot.ss_DriveTrain.DriveMotorRearLeft.enableBrakeMode(false);
//        	Robot.ss_DriveTrain.DriveMotorFrontRight.enableBrakeMode(false);
//        	Robot.ss_DriveTrain.DriveMotorRearRight.enableBrakeMode(false);
        }
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
    	   Math.abs(talon.getEncPos() - targetPos) > Math.abs(targetPos * 0.1)) {
    				if(direction)
    					talon.spin(0.5);
    				else {
    					talon.spin(-0.5);
    				}
    	    	} else {
    	    		talon.stop();
    	    	}
		
		realEndTime = Timer.getFPGATimestamp();
		totalBV += talon.talon.getBusVoltage();
		BVDataCount++;
		SmartDashboard.putNumber("current draw "+ dir + " " + name, talon.talon.getBusVoltage());
		SmartDashboard.putNumber("curr pos " + dir + " " + name, talon.getEncPos());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((Timer.getFPGATimestamp() < endTime) && 
 			Math.abs(talon.getEncPos() - targetPos) > Math.abs(targetPos * 0.03))
    		return false;
    	else
    		distTravelled = talon.getEncPos();
			talon.stop();
    		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ticks/millisecond
    	velocity = ((distTravelled) / ((realEndTime - startTime) * 1000));
    	SmartDashboard.putNumber("ave velocity " + dir + " " + name, velocity);
    	SmartDashboard.putNumber("dist " + dir + " " + name, distTravelled);
    	
    	//3% error allowed
    	if((Math.abs(distTravelled) + (targetPos * 0.03)) >= targetPos) {
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
