package org.usfirst.frc.team4911.robot.commands;

import java.io.FileNotFoundException;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DriveByJoystick extends Command {
	final String name = "C_DriveByJoystick";

    public C_DriveByJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("drive train enc pos", Robot.ss_DriveTrain.DriveMotorFrontLeft.getEncPosition() * Robot.ss_Config.driveEncoderConstL);
    	Robot.ss_DriveTrain.driveByJoystick(Robot.oi.stickL.getY(), Robot.oi.stickR.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
