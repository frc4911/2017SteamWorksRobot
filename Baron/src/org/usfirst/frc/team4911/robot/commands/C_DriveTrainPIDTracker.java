package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_DriveTrainPIDTracker extends Command {

	boolean pidsStarted;
    public C_DriveTrainPIDTracker() {
        // eg. requires(chassis);
    }

    protected void initialize() {
    	pidsStarted = false;
    	SmartDashboard.putBoolean("pids done", false);
    }

    protected void execute() {
    	if (Robot.activeDrivetrainPIDs>0)
    		pidsStarted = true;
    }

    protected boolean isFinished() {
    	if (pidsStarted && (Robot.activeDrivetrainPIDs==0))
    		return true;
    	
        return false;
    }

    protected void end() {
    	SmartDashboard.putBoolean("pids done", true);
    }

    protected void interrupted() {
    }
}
