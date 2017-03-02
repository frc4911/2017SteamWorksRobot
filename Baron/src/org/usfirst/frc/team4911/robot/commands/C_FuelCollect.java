package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_FuelCollect extends Command {
	boolean dir;
	
    public C_FuelCollect(boolean dir) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_FuelCollector);
        this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	if(dir) {
    		Robot.ss_FuelCollector.collectorMotors.spin(0.5);
    	} else {
    		Robot.ss_FuelCollector.collectorMotors.spin(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_FuelCollector.collectorMotors.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
