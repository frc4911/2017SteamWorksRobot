package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterLiftHome extends Command {

	boolean isZeroed = false;
    public C_ShooterLiftHome() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Shooter3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Shooter3.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//isZeroed = Robot.ss_Shooter3.zeroLiftMotorEncoder();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return isZeroed;
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
