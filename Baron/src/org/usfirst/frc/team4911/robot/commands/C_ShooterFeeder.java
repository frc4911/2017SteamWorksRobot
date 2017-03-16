package org.usfirst.frc.team4911.robot.commands;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_ShooterFeeder extends Command {
	boolean shooterSpinning;
	private final double TOLERANCE = 0.90;
	private final double DEFAULT_SPEED = 0.2;
	private final double INCREASE_RATE = 0.004;
	
    public C_ShooterFeeder() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_FuelFeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
//    	double speed = SmartDashboard.getNumber("FeederSpeed",1.1);
//    	if (speed == 1.1){
//    		SmartDashboard.putNumber("FeederSpeed",1.0);
//    	}
//    	Robot.ss_FuelFeeder.feederMotor.spin(speed);
    	
    	double input = Robot.oi.opGamepad.getRawAxis(5) * -1;
    	if(Math.abs(input) > TOLERANCE) {
    		Robot.ss_FuelFeeder.speed += Math.signum(input) * INCREASE_RATE;
    	} else if(Robot.oi.opGamepad.getRawButton(10)) {
    		Robot.ss_FuelFeeder.speed = DEFAULT_SPEED;
    	}
    	
    	if(Robot.ss_FuelFeeder.speed < DEFAULT_SPEED) {
    		Robot.ss_FuelFeeder.speed = DEFAULT_SPEED;
    	} else if(Robot.ss_FuelFeeder.speed > 1.0) {
    		Robot.ss_FuelFeeder.speed = 1.0;
    	}
    	
    	SmartDashboard.putNumber("Fuel Feeder Input", Robot.ss_FuelFeeder.speed);
    	Robot.ss_FuelFeeder.feederMotor.spin(Robot.ss_FuelFeeder.speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_FuelFeeder.feederMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
