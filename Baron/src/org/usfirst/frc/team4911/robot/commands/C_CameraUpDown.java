package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_CameraUpDown extends Command {

    public C_CameraUpDown() {
        requires(Robot.ss_Camera);
    }

    protected void initialize() {
    }

    protected void execute() {
        final double bump = .25;
        
    	int povPosition = Robot.oi.stickR.getPOV(0);
    	
    	//not pressed
    	if (povPosition < 0)
    		return;
    	// 0 is at top 
    	if((povPosition > 270)||(povPosition < 90)){
    		//move up
        	Robot.ss_Camera.setPosition(Robot.ss_Camera.getPosition()+bump);
    	}
    	else{
    		// move dn
        	Robot.ss_Camera.setPosition(Robot.ss_Camera.getPosition()-bump);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
