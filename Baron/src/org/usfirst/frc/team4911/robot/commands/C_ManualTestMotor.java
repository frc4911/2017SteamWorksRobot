package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class C_ManualTestMotor extends Command {
	CANTalon motor;
	TalonControlMode oldMode;
	
    public C_ManualTestMotor(Subsystem subsystem, CANTalon motor) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.motor = motor;
        this.oldMode = this.motor.getControlMode();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	motor.set(Robot.oi.autoTestStick.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	motor.changeControlMode(oldMode);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
