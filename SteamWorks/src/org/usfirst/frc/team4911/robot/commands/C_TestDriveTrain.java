package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestDriveTrain extends Command {
	boolean works;
	String dir;
	
    public C_TestDriveTrain(String dir) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
        this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Math.abs(Robot.ss_DriveTrain.DriveMotorFrontLeft.getEncPosition()) > 10500)
    		works = true;
    	else
    		works = false;
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
    	SmartDashboard.putBoolean(("Drive Train Test " + dir), works);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
