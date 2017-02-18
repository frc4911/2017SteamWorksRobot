package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearIntake extends Command {

    public C_GearIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_GearIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	if(!Robot.ss_GearIntake.getLSIntake()) {
    		Robot.ss_GearIntake.gearIntakeMotor.spin(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.ss_GearIntake.getLSIntake();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_GearIntake.gearIntakeMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
