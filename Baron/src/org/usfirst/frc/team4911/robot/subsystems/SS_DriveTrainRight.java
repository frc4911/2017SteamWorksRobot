package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrainRight extends Subsystem {

	int tPortDriveTrainFrontRight = 1; //0;
	int tPortDriveTrainRearRight = 0; //1;
	
	public DefaultMotor rightMotors = new DefaultMotor(tPortDriveTrainFrontRight, tPortDriveTrainRearRight, -1 /*Robot.ss_Config.driveMotorsRightConst*/, "DriveTrainRight");
	
	public SS_DriveTrainRight(){
		rightMotors.setBrakeMode(true);
		
		rightMotors.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		rightMotors.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		rightMotors.getFollowerTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		rightMotors.getFollowerTalon().ConfigRevLimitSwitchNormallyOpen(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new C_DriveByJoystick(false));
    }
    
    public int inchesToTicks(double inches) {
    	final double ticksPerRev = 1024;
        final double wheelCircum = 13;
    	final double ticksPerInch = ticksPerRev / wheelCircum;
    	
//    	final double skidFactor = 1.5 * ticksPerInch; // 0.0104 // 1.5
    	final double skidFactor = 1.0104;
    	
    	final double resistFactor = 0 * ticksPerInch;
    	
//        final double dist = inches * ticksPerInch + resistFactor + skidFactor;  
        final double dist = inches * ticksPerInch * skidFactor + resistFactor;  

    	
    	return (int) Math.round(dist);
    }
    
    public double degreesToTicks(double degrees) {
    	return inchesToTicks(degreesToInches(degrees));
    }
    
    public double degreesToInches(double degrees) {
    	final double rightTurnInches = 53;
    	final double rightTurnDegrees = 90;
    	final double inchesPerDegree = rightTurnInches / rightTurnDegrees;
    	
    	return degrees * inchesPerDegree;	
    }
    
    public double peakSpeedAdjust(double speed) {
    	return speed * 1.04375; // 8.35 / 8.0
    }
}

