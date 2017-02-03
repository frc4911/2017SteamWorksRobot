package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.subsystems.DashboardDoubleValue;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ValueAdjuster extends Command {

	String key;
	double defaultValue;
	DashboardDoubleValue ddv = null;
	double bump;
	
    public C_ValueAdjuster(String key, double defaultValue, double bump) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.key = key;
    	this.defaultValue = defaultValue;
    	this.bump = bump;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ddv = new DashboardDoubleValue(key, defaultValue);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ddv.putValue(ddv.getNumber()+bump);
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
