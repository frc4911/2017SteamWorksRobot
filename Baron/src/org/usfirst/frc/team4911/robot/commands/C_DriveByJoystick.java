package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_DriveByJoystick extends Command {

    public C_DriveByJoystick() {
        requires(Robot.ss_DriveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	if (Robot.oi.stickR.getRawButton(2)){
    		// flip front to back
        	Robot.ss_DriveTrain.leftMotors.spin(Robot.oi.stickR.getY());
        	Robot.ss_DriveTrain.rightMotors.spin(Robot.oi.stickL.getY());
    	}
    	else {
    		// normal
        	Robot.ss_DriveTrain.leftMotors.spin(-Robot.oi.stickL.getY());
        	Robot.ss_DriveTrain.rightMotors.spin(-Robot.oi.stickR.getY());
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ss_DriveTrain.leftMotors.stop();
    	Robot.ss_DriveTrain.rightMotors.stop();
    }

    protected void interrupted() {
    	end();
    }
}
