package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ClimberUp extends Command {
    public C_ClimberUp() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double input = Robot.oi.opGamepad.getRawAxis(1);
    	if(input < 0) {
    		input = 0;
    	}
    	
    	Robot.ss_Climber.climberMotor.spin(input);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_Climber.climberMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
