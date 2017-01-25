package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_ConstantDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Motors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon follower = new CANTalon(4);
	CANTalon motor = new CANTalon(2);
	
	public double motorSpeed = 0;
	public double yAxis = 0;

	public SS_Motors() {
		follower.changeControlMode(CANTalon.TalonControlMode.Follower);
		follower.set(motor.getDeviceID());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_ConstantDrive());
    }
    
    public void driveByJoystick(double yAxis) {
    	motor.set(yAxis);
    }
    
    public double getSpeed() {
    	return motorSpeed;
    }
    
    public void changeSpeed(double change) {
    	motorSpeed += change;
    }
    
    
    public void updateLog() {
    	SmartDashboard.putNumber("motorSpeed", motorSpeed);
    	SmartDashboard.putNumber("encoderPos", motor.getEncPosition());
    	SmartDashboard.putNumber("encoderVel", motor.getEncVelocity());
    	
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX3, ""+motor.get());
    	//Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX4, ""+motor.getEncPosition());
    	//Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX5, ""+motor.getEncVelocity());
    }
}

