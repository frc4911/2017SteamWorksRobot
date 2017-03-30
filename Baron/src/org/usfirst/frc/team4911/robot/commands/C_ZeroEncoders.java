package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_ZeroEncoders extends Command {

    public C_ZeroEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_DriveTrainLeft.leftMotors.zeroEnc();
    	Robot.ss_DriveTrainRight.rightMotors.zeroEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    int count = 0;
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((Robot.ss_DriveTrainLeft.leftMotors.getEncPos()==0) && (Robot.ss_DriveTrainRight.rightMotors.getEncPos()==0)){
    		return true;
    	}

    	Robot.ss_DriveTrainLeft.leftMotors.zeroEnc();
    	Robot.ss_DriveTrainRight.rightMotors.zeroEnc();
    	count++;
    	
    	if (count > 25){
    		return true;
    	}
    	
   		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("zero encoder loop", count);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
