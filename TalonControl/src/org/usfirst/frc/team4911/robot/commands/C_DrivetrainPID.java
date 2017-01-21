package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DrivetrainPID extends Command {

	int action=0;
	int posLeft;
	int posRight;
	
    public C_DrivetrainPID(int action, int posLeft, int posRight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.action = action;
    	this.posLeft = posLeft;
    	this.posRight = posRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	switch (action){
    	case 0:
    		Robot.ss_DriveTrain.setupPIDMode();
    		Robot.ss_DriveTrain.startPIDMode();
    		break;
    	case 1:
    		Robot.ss_DriveTrain.setPIDEncoderGoal(posLeft,posRight);
    		break;
    	case 2:
    		Robot.ss_DriveTrain.stopPIDMode();
    		break;
    	case 3:
    		Robot.ss_DriveTrain.clearEncoders();
    		Robot.ss_DriveTrain.setPIDEncoderGoal(posLeft,posRight);
    		break;
    	}
    	
    	SmartDashboard.putNumber("PIDmode", action);
    	SmartDashboard.putNumber("PID position left", posLeft);
    	SmartDashboard.putNumber("PID position right", -posRight);
    	SmartDashboard.putBoolean("PIDmode running", true);
    }

    int distance=0;
    int counter=0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int currentDistance = Robot.ss_DriveTrain.PIDPosition();
    	
    	if (Math.abs(currentDistance - distance) > 5)
    	{
    		distance = currentDistance;
    		counter = 0;
    	}
    	else
    		counter ++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((action != 1) && (action != 3))
			return true;

    	if (counter > 100)
    		return true;
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("PIDmode running", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ss_DriveTrain.stopPIDMode();
    	SmartDashboard.putBoolean("PIDmode running", false);
    }
}
