package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ResetEncoder extends Command {

    public C_ResetEncoder() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_TestFreeSpinMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_TestFreeSpinMotor.motor.zeroEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
