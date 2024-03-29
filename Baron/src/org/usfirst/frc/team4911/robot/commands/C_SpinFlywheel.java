package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_SpinFlywheel extends Command {

	final int DEFAULTTICKS = 6500;
	int ticks = 6500;
	
    public C_SpinFlywheel() {
    	ticks = DEFAULTTICKS;
    }
    
    public C_SpinFlywheel(int ticks) {
    	this.ticks = ticks;
    }
    
    boolean usePID = true;

    // Called just before this Command runs the first time
    protected void initialize() {

    	if (usePID){
                                                      // ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType, encoderFlip, flipMotorDir);
    		Robot.ss_FuelShooter.shooterMotors.moveToEncPos(ticks, 1440, 360, 0.22, 5.0, 0, 0.052, 0, 0, 12.0, 0, CANTalon.TalonControlMode.Speed, false, true);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());

    	if (usePID){
    		
    	}
    	else{
	    	double speed = SmartDashboard.getNumber("FlyWheelSpeed",1.1);
	    	if (speed == 1.1){
	    		SmartDashboard.putNumber("FlyWheelSpeed",1);
	    		speed = 1;
	    	}
	    		
	    	Robot.ss_FuelShooter.shooterMotors.spin(speed);	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (usePID){
    		Robot.ss_FuelShooter.shooterMotors.stopPID();
    	}
    	else{
    		Robot.ss_FuelShooter.shooterMotors.stop();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}