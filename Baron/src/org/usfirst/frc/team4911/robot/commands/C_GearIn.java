package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_GearInOut extends Command {

	double speed = 0.6;
	DefaultMotor motor = null;

	public C_GearInOut(boolean intake) {
        requires(Robot.ss_GearIntake);
        motor = Robot.ss_GearIntake.gearIntakeMotor;
        if (!intake)
        	speed = -speed;
    }

	double currentThreshold = 0.0;
	final int TIMEOUT = 25;
	int startupCounter = TIMEOUT;

    protected void initialize() {
    	currentThreshold = Robot.ss_GearIntake.currentThreshold;
    	startupCounter = TIMEOUT;
    }
	
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());

    	if (speed <= 0){
    		motor.spin(speed);
    		Robot.ss_GearIntake.gearIn = false;
    	}
    	else{
    		if (!Robot.ss_GearIntake.gearIn){
    			if (startupCounter > 0){
        			motor.spin(speed);
    			}    				
    			else {	
    				if (motor.getOutputCurrent(false) < currentThreshold){
    	    			motor.spin(speed);
    				}
    				else{
    	    			Robot.ss_GearIntake.gearIn = true;
    				}
    			}
    		}
    		else {
    			motor.stop();
    		}
    	}
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
		motor.stop();
    }

    protected void interrupted() {
    	end();
    }
}
