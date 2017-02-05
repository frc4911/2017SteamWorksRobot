package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestArm extends Command {
	boolean direction;
	String dir;
	
	CANTalon armLift;
	
	double endTime;
	double startTime;
	final double duration = 2;
	final double upPos = Robot.ss_Arm.retracted;
	final double downPos = Robot.ss_Arm.extended;
	
	double distTravelled;
	double velocity;
	
	final double normBusVoltage = 0;
	final double percentErrorBV = 0;
	
    public C_TestArm(boolean direction, CANTalon armLift) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Arm);
        
        this.direction = direction;
        this.armLift = armLift;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	armLift.setEncPosition(0);
    	
    	startTime = Timer.getFPGATimestamp();
    	endTime = startTime + duration;
    }
    
    private boolean checkBusVoltage() {
    	if(armLift.getBusVoltage() > (percentErrorBV + normBusVoltage))
    		return false;
    	else if(armLift.getBusVoltage() < Math.abs(percentErrorBV - normBusVoltage))
    		return false;
    	else
    		return true;
    }

    // Called repeatedly when this Command is scheduled to run
    double targetPos;
    protected void execute() {
    	targetPos = getTargetPos();
    	
    	while((Timer.getFPGATimestamp() < endTime) || 
    		   Math.abs(armLift.getEncPosition() - targetPos) > Math.abs(targetPos * 0.03)){
			if(direction)
				armLift.set(-1);
			else 
				armLift.set(1);
			
			SmartDashboard.putNumber("Motor current draw "+ dir + " arm", armLift.getBusVoltage());
		}
		
		distTravelled = armLift.getEncPosition();
		armLift.set(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    
    private double getTargetPos() {
    	if(direction)
    		return upPos;
    	else
    		return downPos;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ticks/millisecond
    	velocity = ((distTravelled) / ((endTime - startTime) * 1000));
    	SmartDashboard.putNumber("DT ave velocity " + dir + " shooter", velocity);
    	SmartDashboard.putNumber("DT dist " + dir + " shooter", distTravelled);
    	
    	//1% error allowed
    	if((Math.abs(distTravelled) + (targetPos * 0.01)) >= targetPos) {
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
