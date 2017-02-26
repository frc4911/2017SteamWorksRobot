package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_HopperSpin extends Command {

	double speed = 1;
	
    public C_HopperSpin(boolean direction) {
        requires(Robot.ss_FuelHopper);
        if (!direction){
        	speed = -speed;
        }
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	Robot.ss_FuelHopper.hopperMotor.spin(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ss_FuelHopper.hopperMotor.stop();
    }

    protected void interrupted() {
    	end();
    }
}
