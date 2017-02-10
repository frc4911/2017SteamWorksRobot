package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DashboardDoubleValue;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MotorPID extends Command {

	int action;
	double endTime = 0;
	double finalRamp = 0;
	DashboardDoubleValue ddv = null;
	
    public C_MotorPID(int action) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.action = action;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	switch (action){
    	case 0:
    		Robot.ss_MotorPID2.stopPIDMode();
    		break;
    	case 1:
    		Robot.ss_MotorPID2.createPID(0);
    		break;
    	case 2:
    		Robot.ss_MotorPID2.createPID(1);
    		break;
    	case 3:
    		ddv = new DashboardDoubleValue("final Ramp", 0);
    		SmartDashboard.putNumber("ramp restored",-1);
    		Robot.ss_MotorPID2.createPID(3);
    		break;
    	}
    	endTime = Timer.getFPGATimestamp()+1;
    	SmartDashboard.putNumber("PIDmode", action);
    	SmartDashboard.putBoolean("PIDmode running", true);
    }

    int distance=0;
    int counter=0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (action == 3){
    		if (Timer.getFPGATimestamp() < endTime){
    			return false;
    		}
    		Robot.ss_MotorPID2.setRamp(ddv.getNumber());
    		return true;
    	}
    	
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
