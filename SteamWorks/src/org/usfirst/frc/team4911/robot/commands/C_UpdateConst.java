package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_UpdateConst extends Command {

    public C_UpdateConst() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Config);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Config.driveMotorConstFL = 1;
    	Robot.ss_Config.driveMotorConstFR = 1;
    	Robot.ss_Config.driveMotorConstRL = 1;
    	Robot.ss_Config.driveMotorConstRR = 1;
    	
    	Robot.ss_Config.driveEncoderConstL = 1;
    	Robot.ss_Config.driveEncoderConstR = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_Config.updateInfo();
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
