package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TriggerWhileHeld extends Command {
	Command cmd;
	Joystick gamepad;
	int axis;
	
	private final double TOLERANCE = 0.8;
	
    public C_TriggerWhileHeld(Command cmd, Joystick gamepad, boolean leftTrigger) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.cmd = cmd;
    	this.gamepad = gamepad;
    	
    	if(leftTrigger) {
    		axis = 2;
    	} else {
    		axis = 3;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    int counter=0;
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
//    	SmartDashboard.putNumber("C_TriggerWhileHeld execute", counter++);
//    	SmartDashboard.putNumber("C_TriggerWhileHeld axis", gamepad.getRawAxis(axis));
    	if(gamepad.getRawAxis(axis) > TOLERANCE) {
    		cmd.start();
//        	SmartDashboard.putNumber("C_TriggerWhileHeld start", counter++);
    	} else if(cmd.isRunning()) {
    		cmd.cancel();
//        	SmartDashboard.putNumber("C_TriggerWhileHeld cancel", counter++);
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
