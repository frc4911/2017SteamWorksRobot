package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_GetMotorValues;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Motors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon motor1 = new CANTalon(1);
	CANTalon motor2 = new CANTalon(2);
	CANTalon motor3 = new CANTalon(3);
	CANTalon motor4 = new CANTalon(4);
	
	public double speed1 = 0;
	public double speed2 = 0;
	public double speed3 = 0;
	public double speed4 = 0;
	public double yAxis = 0;
	
	public double counter = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_GetMotorValues());
    }
    
    public void driveMotors(Joystick stick, int joystickTalon) {
    	yAxis = -stick.getY();
    	
    	//motor1
    	if((joystickTalon == 1) && (stick.getY() != 0)) {
    		motor1.set(yAxis);
    	}
    	else if(stick.getRawButton(5)) {
			motor1.set(speed1);
		}
    	else {
    		motor1.set(0);
    	}
    	
    	//motor2
    	if((joystickTalon == 2) && (stick.getY() != 0)) {
    		motor2.set(yAxis);
    	}
    	else if(stick.getRawButton(6)) {
    		motor2.set(speed2);
    	}
    	else {
    		motor2.set(0);
    	}
    	
    	//motor3
    	if((joystickTalon == 3) && (stick.getY() != 0)) {
    		motor3.set(yAxis);
    	}
    	else if(stick.getRawButton(7)) {
    		motor3.set(speed3);
    	}
    	else {
    		motor3.set(0);
    	}
    	
    	//motor4
    	if((joystickTalon == 4) && (stick.getY() != 0)) {
    		motor4.set(yAxis);
    		SmartDashboard.putNumber("stick0YAxis", yAxis);
    	}
    	else if(stick.getRawButton(8)) {
    		motor4.set(speed4);
    	}
    	else {
    		motor4.set(0);
    	}
    }
    
    public void getSpeed(double speed1, double speed2, double speed3, double speed4) {
    	this.speed1 = speed1;
    	this.speed2 = speed2;
    	this.speed3 = speed3;
    	this.speed4 = speed4;
    }
    
    public void updateLog() {
    	SmartDashboard.putNumber("motor1.get()", motor1.get());
    	
    	SmartDashboard.putNumber("SS_Motors.updateLog()", counter++);
    	
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX3, ""+motor1.get());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX4, ""+motor2.get());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX5, ""+motor3.get());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX6, ""+motor4.get());
    }
}

