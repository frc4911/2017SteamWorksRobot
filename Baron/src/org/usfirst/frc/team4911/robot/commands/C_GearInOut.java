package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.command.Command;

public class C_GearInOut extends Command {

	double speed = .4;
	DefaultMotor motor = null;

	public C_GearInOut(boolean intake) {
        requires(Robot.ss_GearIntake);
        motor = Robot.ss_GearIntake.gearIntakeMotor;
        if (!intake)
        	speed = -speed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
   		motor.spin(speed);
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
