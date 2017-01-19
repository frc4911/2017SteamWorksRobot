package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_MoveArmByButton extends Command {
	
	int direction;
    public C_MoveArmByButton(int moveDirection) {
        // Use requires() here to declare subsystem dependencies
    	direction = moveDirection;
        requires(Robot.ss_Arm);
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double newPos = 0.0088 * direction;
    	if((newPos + Robot.ss_Arm.setPos) <= 0.48) {
    		newPos = 0;
    	} else if((newPos + Robot.ss_Arm.setPos) >= 0.524) {
    		newPos = 0;
    	}
    	
    	Robot.ss_Arm.setPos += newPos;
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
