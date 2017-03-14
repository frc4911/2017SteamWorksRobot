package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class C_GearLiftUp extends Command {
	DefaultMotor motor;
	boolean stopNow;
	// lift gear and hold it up until told to stop
	// talon will stop when limit (based on encoder) is reached
    public C_GearLiftUp(boolean stopNow) {
        requires(Robot.ss_GearLift);
        motor = Robot.ss_GearLift.gearLiftMotor;
        this.stopNow = stopNow;
    }

    protected void initialize() {
    	if(stopNow)
    		Robot.ss_GearLift.keepGearUp = false; // set the stop flag
    	else
    		Robot.ss_GearLift.keepGearUp = true;
    }

    protected void execute() {

    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	if (!stopNow){
	    	motor.spin(.4);
	    }
    }

    protected boolean isFinished() {
    	if (stopNow)
    		return true;
    	else
    		return !Robot.ss_GearLift.keepGearUp;
    }

    protected void end() {
    	if(!stopNow)
    		motor.stop();
    }

    protected void interrupted() {
    	end();
    }
}
