package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DashboardDoubleValue;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ReadAndSetSmartDashboardValues extends Command {

	DashboardDoubleValue ddvPID1 = null;	
	DashboardDoubleValue ddvPID2 = null;
	
    public C_ReadAndSetSmartDashboardValues() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ddvPID1 = new DashboardDoubleValue("pid1",0);
    	Robot.pid1 = ddvPID1.getNumber();

    	ddvPID2 = new DashboardDoubleValue("pid2",0);
    	Robot.pid2 = ddvPID2.getNumber();
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
