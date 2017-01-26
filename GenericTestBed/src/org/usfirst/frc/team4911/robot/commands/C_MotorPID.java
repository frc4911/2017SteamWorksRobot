package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MotorPID extends Command {

	int action=0;
	boolean running = false;
	
    public C_MotorPID(int action) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.action = action;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	switch (action){
    	case 0:
    		Robot.ss_MotorPID2.stopPIDMode();
    		break;
    	case 1:
    		Robot.ss_MotorPID2.createPID(0);
    		break;
    	case 2:
    		Robot.ss_MotorPID2.createPID(1);
    		break;
    	case 3:
    		Robot.ss_MotorPID2.createPID(3);
    		break;
    	}
    	
    	SmartDashboard.putNumber("PIDmode", action);
    	running = true;
    	SmartDashboard.putBoolean("PIDmode running", running);
    }

    int distance=0;
    int counter=0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	int combinedDistanceFromGoal = Math.abs(Robot.ss_MotorPID.PIDPosition());
//    	
//    	if (distance == combinedDistanceFromGoal){
//    		counter++;
//    	}
//    	else if (combinedDistanceFromGoal < 15)
//    	{
//    		counter++;
//    	}
//    	else
//    		counter=0;
//    	
//    	distance = combinedDistanceFromGoal;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
//    	if ((action == 1) || (action == 3))
//			return true;
//
//    	if (counter > 500)
//    		return true;
//    	
//    	//return false;
//    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	running = false;
    	SmartDashboard.putBoolean("PIDmode running", running);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ss_MotorPID2.stopPIDMode();
    	end();
    }
    
    public void updateLog() {
    	Robot.ss_Logging2.logKeyOutput(3, ""+action);
    	Robot.ss_Logging2.logKeyOutput(4, ""+running);
    }
}
