package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MoveArm extends Command {
	final String name = "C_MoveArm";
	boolean direction;
	double time;
	double endTime;
	
	boolean shooterDown;
	
    public C_MoveArm(boolean direction, boolean shooterDown) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Arm);
        this.direction = direction;
        this.shooterDown = shooterDown;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(shooterDown)
    		Robot.ss_Arm.moveArm(direction);
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
