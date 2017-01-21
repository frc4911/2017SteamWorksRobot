package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_GetMotorValues extends Command {

	public double speed1 = 0;
	public double speed2 = 0;
	public double speed3 = 0;
	public double speed4 = 0;
	
	public int joystickTalon = 0;
	
    public C_GetMotorValues() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Motors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed1 = SmartDashboard.getNumber("motor1Speed", -1);
    	speed2 = SmartDashboard.getNumber("motor2Speed", -2);
    	speed3 = SmartDashboard.getNumber("motor3Speed", -3);
    	speed4 = SmartDashboard.getNumber("motor4Speed", -4);
    
    	joystickTalon = (int)Math.round(SmartDashboard.getNumber("joystickTalon", 0));
    	
    	Robot.ss_Motors.getSpeed(speed1, speed2, speed3, speed4);
    	
    	Robot.ss_Motors.driveMotors(Robot.oi.stick0, joystickTalon);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
