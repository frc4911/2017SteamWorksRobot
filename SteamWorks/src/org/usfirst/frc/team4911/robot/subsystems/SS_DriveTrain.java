package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.CG_DriveToAndFrom;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {
	
	final int TPortDriveMotorFrontLeft = 9;
    //final int TPortDriveMotorMidLeft = 8;
    final int TPortDriveMotorRearLeft = 7;
    
    final int TPortDriveMotorFrontRight = 10;
    //final int TPortDriveMotorMidRight = 11;
    final int TPortDriveMotorRearRight = 12;
    
    public CANTalon DriveMotorFrontLeft = new CANTalon(TPortDriveMotorFrontLeft);
    public CANTalon DriveMotorRearLeft = new CANTalon(TPortDriveMotorRearLeft);
    
    public CANTalon DriveMotorFrontRight = new CANTalon(TPortDriveMotorFrontRight);
    public CANTalon DriveMotorRearRight = new CANTalon(TPortDriveMotorRearRight);
    
    
    
    public void driveByJoystick(double leftInput, double rightInput) {
    	DriveMotorFrontLeft.set(leftInput);
    	DriveMotorRearLeft.set(leftInput);
    	
		DriveMotorFrontRight.set(-rightInput);
		DriveMotorRearRight.set(-rightInput);
    }
    
	//true = forwards, lasts 2 seconds (50 cycles / second)
    public void drive(boolean direction) {
    	int sign = 0;
    	if(direction) {
    		sign = -1;
    	} else {
    		sign = 1;
    	}
    	
    	double leftInput = sign * 0.48;
    	double rightInput = sign * 0.5;
    	
    	
    	DriveMotorFrontLeft.set(leftInput);
    	DriveMotorRearLeft.set(leftInput);
    	
		DriveMotorFrontRight.set(-rightInput);
		DriveMotorRearRight.set(-rightInput);
    }
    
    public void turn(boolean direction) {
    	int sign = 0;
    	if(direction) {
    		sign = -1;
    	} else {
    		sign = 1;
    	}
    	
    	double leftInput = sign * 0.5;
    	double rightInput = sign * 0.5;
    	
    	
    	DriveMotorFrontLeft.set(leftInput);
    	DriveMotorRearLeft.set(leftInput);
    	
		DriveMotorFrontRight.set(rightInput);
		DriveMotorRearRight.set(rightInput);
    	
    }
    
    public void stop() {
    	DriveMotorFrontLeft.set(0);
    	DriveMotorRearLeft.set(0);
    	
		DriveMotorFrontRight.set(0);
		DriveMotorRearRight.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new C_DriveByJoystick());
    }
}

