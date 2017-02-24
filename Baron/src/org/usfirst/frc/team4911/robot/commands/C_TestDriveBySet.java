package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_TestDriveBySet extends Command {

    public C_TestDriveBySet() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Climber);
        requires(Robot.ss_DriveTrain);
        requires(Robot.ss_FuelCollector);
        requires(Robot.ss_FuelHopper);
        requires(Robot.ss_FuelShooter);
        requires(Robot.ss_GearIntake);
        requires(Robot.ss_GearLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	Robot.ss_TestMotor.runMotor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_TestMotor.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
