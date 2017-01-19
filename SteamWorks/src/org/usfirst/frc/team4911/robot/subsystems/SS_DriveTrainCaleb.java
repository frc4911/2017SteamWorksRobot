package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrainCaleb extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon talonFrontLeft = new CANTalon(9);
	CANTalon talonRearLeft = new CANTalon(7);
	CANTalon talonFrontRight = new CANTalon(10);
	CANTalon talonRearRight = new CANTalon(12);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
    
    public void driveByJoyStick(double yAxis, double xAxis) {
    	double leftSpeed = -yAxis * 0.96;
    	double rightSpeed = yAxis;
    	
    	if (xAxis > 0.1) {
    		rightSpeed -= xAxis;
    	}
    	if (xAxis < -0.1) {
    		leftSpeed -= xAxis;
    	}
    	
    	talonFrontLeft.set(leftSpeed);
    	talonRearLeft.set(leftSpeed);
    	talonFrontRight.set(rightSpeed);
    	talonRearRight.set(rightSpeed);
    }
    
    public void atonomousDrive(double leftSpeed, double rightSpeed) {
    	leftSpeed = -leftSpeed * 0.96;
    	//rightSpeed = rightSpeed;
    	
    	//SmartDashboard.putNumber("leftSpeed", -leftSpeed);
    	//SmartDashboard.putNumber("rightSpeed", rightSpeed);
    	
    	talonFrontLeft.set(leftSpeed);
    	talonRearLeft.set(leftSpeed);
    	talonFrontRight.set(rightSpeed);
    	talonRearRight.set(rightSpeed);
    }
}

