package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_DriveByJoystick extends Command {
	private boolean leftDriveTrain;
	
    public C_DriveByJoystick(Boolean leftDriveTrain) {
        if(leftDriveTrain) {
        	requires(Robot.ss_DriveTrainLeft);
        } else {
        	requires(Robot.ss_DriveTrainRight);
        }
        
        this.leftDriveTrain = leftDriveTrain;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	if (Robot.oi.stickR.getRawButton(3)){
    		// flip front to back
    		if(leftDriveTrain) {
    			Robot.ss_DriveTrainLeft.leftMotors.spin(Robot.oi.stickR.getY());
    		} else {
    			Robot.ss_DriveTrainRight.rightMotors.spin(Robot.oi.stickL.getY());
    		}
    	}
    	else {
    		// normal
    		if(leftDriveTrain) {
    			Robot.ss_DriveTrainLeft.leftMotors.spin(-Robot.oi.stickL.getY());
    		} else {
    			Robot.ss_DriveTrainRight.rightMotors.spin(-Robot.oi.stickR.getY());
    		}
    	}    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	if(leftDriveTrain) {
    		Robot.ss_DriveTrainLeft.leftMotors.stop();
		} else {
			Robot.ss_DriveTrainRight.rightMotors.stop();
		}
    }

    protected void interrupted() {
    	end();
    }
}
