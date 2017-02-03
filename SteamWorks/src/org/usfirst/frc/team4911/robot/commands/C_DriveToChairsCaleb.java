package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DriveToChairsCaleb extends Command {

	public double currentTime = 0;
	public double endTime = 0;
	public double driveTime = 0;
	public boolean forward = true;
	
	public double speed1 = -0.6;
	public double speed2 = -0.3;
	
	public double speed1Time = 0;
	//public double speed2Time = 0;
	
    public C_DriveToChairsCaleb(double driveTime, boolean forward) {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.ss_DriveTrainCaleb);
        
        //this.driveTime = driveTime;
        //this.forward = forward;
    }

    // Called just before this Command runs the first time
	@SuppressWarnings("deprecation")
	protected void initialize() {
		driveTime = SmartDashboard.getNumber("driveTime");
		forward = SmartDashboard.getBoolean("forward");
		
    	endTime = Timer.getFPGATimestamp() + driveTime;
    	
    	speed1Time = Timer.getFPGATimestamp() + driveTime - (driveTime/4);
    	//speed2Time = Timer.getFPGATimestamp() + driveTime - (driveTime/2);
    	
    	if(!forward) {
    		speed1 = -0.6;
    		speed2 = -0.3;
    	}
    	else {
    		speed1 = 0.6;
    		speed2 = 0.3;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTime = Timer.getFPGATimestamp();
    	
    	if(currentTime <= speed1Time) {
    		//Robot.ss_DriveTrainCaleb.atonomousDrive(speed1, speed1);
    	}
    	else {
    		//Robot.ss_DriveTrainCaleb.atonomousDrive(speed2, speed2);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(currentTime >= endTime) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.ss_DriveTrainCaleb.atonomousDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
