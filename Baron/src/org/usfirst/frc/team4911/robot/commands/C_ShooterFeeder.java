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
	private final double TOLERANCE = 0.03;
	private final double SHOOTERSPEED = 3000.0;
	private final double SPEED = 1.0;
	
    public C_ShooterFeeder() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_FuelFeeder);
    }
    
    private boolean isShooterUpToSpeed() {
    	if(Math.abs((Robot.ss_FuelShooter.shooterMotors.getTalonSpeed() - SHOOTERSPEED) / SHOOTERSPEED) <= TOLERANCE) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
//    	if(isShooterUpToSpeed()) {
//    		Robot.ss_FuelFeeder.feederMotor.spin(SPEED);
//    	} else if(!Objects.equals(Robot.ss_FuelShooter.shooterMotors.getPID(), null)) {
//    		Robot.oi.flywheel.start();
//    	}
//    	double speed = SmartDashboard.getNumber("FeederSpeed",1.1);
//    	if (speed == 1.1){
//    		SmartDashboard.putNumber("FeederSpeed",1.0);
//    	}
//    	Robot.ss_FuelFeeder.feederMotor.spin(speed);
    	Robot.ss_FuelFeeder.feederMotor.spin(SPEED);
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
