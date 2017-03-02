package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_TestDriveOneMotorGroup extends Command {

	int myCurrMotor = -1;
	Command req = null;
	
    public C_TestDriveOneMotorGroup() {
    }

    protected void initialize() {

    	myCurrMotor = Robot.ss_TestMotor.getCurrIndex();
    	req = new C_Requires(Robot.ss_TestMotor.getSubsystem());
    	req.start();
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	if (myCurrMotor != Robot.ss_TestMotor.getCurrIndex()){
    		req.cancel();

    		myCurrMotor = Robot.ss_TestMotor.getCurrIndex();
        	req = new C_Requires(Robot.ss_TestMotor.getSubsystem());
        	req.start();
    	}
    	
    	//invert Y so forward is positive
   		Robot.ss_TestMotor.runMotor(-Robot.oi.autoTestGamepad.getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ss_TestMotor.stopMotor();
    	req.cancel();
    }

    protected void interrupted() {
    	end();
    }
}
