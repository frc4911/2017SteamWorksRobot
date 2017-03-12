package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon frontLeftMotor = new CANTalon(6);
	CANTalon rearLeftMotor = new CANTalon(7);
	
	CANTalon frontRightMotor = new CANTalon(9);
	CANTalon rearRightMotor = new CANTalon(10);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
    
    public void leftDriveTrain(double speed) {
    	frontLeftMotor.set(speed);
    	rearLeftMotor.set(speed);
    }
    
    public void rightDriveTrain(double speed) {
    	frontRightMotor.set(speed);
    	rearRightMotor.set(speed);
    }
}

