package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TurnCaleb extends Command {

	public double currentTime = 0;
	public double endTime = 0;
	public double turnTime = 0;
	
	boolean turnLeft = true;
	
	double speed = 1;
	
    public C_TurnCaleb(double driveTime, boolean turnLeft) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrainCaleb);
        
        //this.turnTime = driveTime;
        //this.turnLeft = turnLeft;
    }

    // Called just before this Command runs the first time
    @SuppressWarnings("deprecation")
	protected void initialize() {
    	turnTime = SmartDashboard.getNumber("turnTime");
    	turnLeft = SmartDashboard.getBoolean("turnLeft");
    	
    	endTime = Timer.getFPGATimestamp() + turnTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnLeft) {
    		Robot.ss_DriveTrainCaleb.atonomousDrive(-speed, speed);
    	}
    	else {
    		Robot.ss_DriveTrainCaleb.atonomousDrive(speed, -speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currentTime = Timer.getFPGATimestamp();
    	
    	if(currentTime >= endTime) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
