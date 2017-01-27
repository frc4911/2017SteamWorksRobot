package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_TriggerShooterPlunger extends Command {
	final String name = "C_TiggerShooterPlunger";
	boolean onOff;
	
    public C_TriggerShooterPlunger(boolean onOff) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Shooter);
        this.onOff = onOff;
        
        Robot.ss_Commands.commandNames[Robot.ss_Commands.numCommand] = name;
        Robot.ss_Commands.numCommand++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Commands.startCommand(name);
    	Robot.ss_Shooter.shooterPlunger.set(onOff);
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
    	Robot.ss_Commands.endCommand();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
