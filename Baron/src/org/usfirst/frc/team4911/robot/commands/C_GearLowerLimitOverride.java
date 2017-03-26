package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearLowerLimitOverride extends Command {

	CANTalon talon;
	
    public C_GearLowerLimitOverride() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	talon = Robot.ss_GearLift.gearLiftMotor.getTalon();
    	Robot.ss_GearLift.gearLiftMotor.setSoftLimits(Robot.ss_GearLift.gearLiftMotor.getTalon(), Robot.ss_GearLift.topEncoderValue, -Robot.ss_GearLift.topEncoderValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
