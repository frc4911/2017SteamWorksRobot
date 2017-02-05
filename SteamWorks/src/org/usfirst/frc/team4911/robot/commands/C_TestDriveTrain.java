package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestDriveTrain extends Command {
	boolean direction;
	String dir;
	String name;
	CANTalon driveMotor;
	CANTalon encoder;
	
	double endTime;
	final double duration = 2;
	final double runDist = 22000;
	
	final double normBusVoltage = 11.8;
	final double percentErrorBV = 0.6; //5% error
	
    public C_TestDriveTrain(boolean dir, CANTalon driveMotor, String name, CANTalon encoder) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
        this.direction = dir;
        this.driveMotor = driveMotor;
        this.encoder = encoder;
        this.name = name;
        
        if(direction)
        	this.dir = "forward";
        else
        	this.dir = "backward";
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_DriveTrain.DriveMotorFrontLeft.enableBrakeMode(false);
    	Robot.ss_DriveTrain.DriveMotorFrontRight.enableBrakeMode(false);
    	Robot.ss_DriveTrain.DriveMotorRearLeft.enableBrakeMode(false);
    	Robot.ss_DriveTrain.DriveMotorRearRight.enableBrakeMode(false);
    	
    	encoder.setEncPosition(0);
    	
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    
    private boolean checkBusVoltage() {
    	if(driveMotor.getBusVoltage() > (percentErrorBV + normBusVoltage))
    		return false;
    	else if(driveMotor.getBusVoltage() < Math.abs(percentErrorBV - normBusVoltage))
    		return false;
    	else
    		return true;
    }
    
    double startTime;
    double distTravelled = 0;
    double velocity  = 0;
    protected void execute() {
    	while(Timer.getFPGATimestamp() < endTime) {
    		if(direction)
    			driveMotor.set(1);
    		else
    			driveMotor.set(-1);
    		
    		SmartDashboard.putNumber("Motor current draw "+ dir + " " + name, driveMotor.getBusVoltage());
    		//driveMotor.getStickybits();
    	}
    	distTravelled = encoder.getEncPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ticks/millisecond
    	velocity = ((distTravelled) / ((endTime - startTime) * 1000));
    	SmartDashboard.putNumber("DT ave velocity " + dir + " shooter", velocity);
    	SmartDashboard.putNumber("DT dist " + dir + " shooter", distTravelled);
    	
    	//1% error allowed
    	if((Math.abs(distTravelled) + (runDist * 0.01)) >= runDist) {
    		SmartDashboard.putString(("DT dist reached " + dir + " shooter"), "yes");
    	} else {
    		SmartDashboard.putString(("DT dist reached " + dir + " shooter"), "no");
    	}
    	
    	if(checkBusVoltage()) {
    		SmartDashboard.putString(("DT curr draw " + dir + " shooter"), "normal");
    	} else {
    		SmartDashboard.putString(("DT curr draw " + dir + " shooter"), "high/low");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
