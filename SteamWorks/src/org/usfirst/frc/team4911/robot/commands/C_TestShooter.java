package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestShooter extends Command {
	CANTalon shooterLift = Robot.ss_Shooter.shooterLiftMotor;
	
	boolean direction;
	String dir;
	
	final double upPos = Robot.ss_Shooter.upPos;
	final double downPos = Robot.ss_Shooter.downPos;
	
	final double normBusVoltage = 12.5;
	final double percentErrorBV = 0.6; //5% error
	
	double startTime;
	double endTime;
	double distTravelled;
	double velocity;
	
    public C_TestShooter(boolean direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Shooter);
        this.direction = direction;
        
        if(direction)
        	dir = "up";
        else
        	dir = "down";
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterLift.setEncPosition(0);
    	
    	startTime = Timer.getFPGATimestamp();
    	endTime = Timer.getFPGATimestamp() + 2;
    }

    // Called repeatedly when this Command is scheduled to run
    private double getTargetPos() {
    	if(direction)
    		return upPos;
    	else
    		return downPos;
    }
    
    private boolean checkBusVoltage() {
    	if(shooterLift.getBusVoltage() > (percentErrorBV + normBusVoltage))
    		return false;
    	else if(shooterLift.getBusVoltage() < Math.abs(percentErrorBV - normBusVoltage))
    		return false;
    	else
    		return true;
    }
    
    double targetPos;
    protected void execute() {
    	targetPos = getTargetPos();
		
		while((Timer.getFPGATimestamp() < endTime) || 
			   Math.abs(shooterLift.getEncPosition() - targetPos) > Math.abs(targetPos * 0.03)){
			if(direction)
				shooterLift.set(1);
			else {
				shooterLift.set(-1);
			}
			
			SmartDashboard.putNumber("Motor current draw "+ dir + " shooter", shooterLift.getBusVoltage());
		}
		
		distTravelled = shooterLift.getEncPosition();
		shooterLift.set(0);
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
    	
    	//3% error allowed
    	if((Math.abs(distTravelled) + (targetPos * 0.03)) >= targetPos) {
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
