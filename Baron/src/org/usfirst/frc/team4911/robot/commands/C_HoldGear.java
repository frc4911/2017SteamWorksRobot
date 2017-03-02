package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_HoldGear extends Command {
	DefaultMotor motor;
	
    public C_HoldGear() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_GearLift);
        motor = Robot.ss_GearLift.gearLiftMotor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	int gearPos = (int)Robot.ss_GearLift.topPotValue;
    	motor.moveToEncPos(gearPos, 1, 1, 2.0, 0, 0, 0, 0, 0, 12.0, 0, CANTalon.TalonControlMode.Position, false, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.pidTargetReached;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.ss_GearLift.gearLiftMotor.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
