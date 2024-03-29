package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_CompleteDriveByTime extends Command {
	double endTime;
	double secLength;
	double power, spin;

    public C_CompleteDriveByTime(double inSeconds, double inPower, double inSpin) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
        secLength = inSeconds;
        power = inPower;
        spin = inSpin;
    }

    // Called just before this Command runs the first time
    protected void initialize() { 
    	secLength = SmartDashboard.getNumber("spinTime", 2);
    	endTime = Timer.getFPGATimestamp() + secLength;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_DriveTrain.driveByJoystick(power, spin);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentTime = Timer.getFPGATimestamp();
    	
    	if(currentTime >= endTime) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
