package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class C_RunAutoTest extends Command {
	NetworkTable table;
	final String IP = "10.49.11.84";
	final String TABLENAME = "AutoTest";
	
    public C_RunAutoTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	NetworkTable.setIPAddress(IP);
    	table = NetworkTable.getTable(TABLENAME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.testBtnStart.get() && !Robot.oi.autoTest.isRunning()) {
    		table.putBoolean("start", true);
    		Robot.oi.autoTest.start();
    	}
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
