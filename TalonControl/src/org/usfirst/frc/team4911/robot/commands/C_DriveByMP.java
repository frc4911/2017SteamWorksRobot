package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_DriveByMP extends Command {

    public C_DriveByMP() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_DriveTrain.enterMPMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_DriveTrain.driveByMP();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_DriveTrain.exitMPMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
