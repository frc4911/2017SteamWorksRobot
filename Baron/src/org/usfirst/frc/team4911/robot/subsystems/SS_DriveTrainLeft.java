package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrainLeft extends Subsystem {

	
	
	int tPortDriveTrainFrontLeft = 3; //2;
	int tPortDriveTrainRearLeft = 2; //3;
	
	public DefaultMotor leftMotors = new DefaultMotor(tPortDriveTrainFrontLeft, tPortDriveTrainRearLeft, Robot.ss_Config.driveMotorsLeftConst, "DriveTrainLeft");
	
	public SS_DriveTrainLeft(){
		leftMotors.setBrakeMode(true);
		
		leftMotors.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		leftMotors.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		leftMotors.getFollowerTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		leftMotors.getFollowerTalon().ConfigRevLimitSwitchNormallyOpen(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick(true));
    }
    
    public int inchesToTicks(double inches) {
    	final double ticksPerRev = 1024;
        final double wheelCircum = 13;
    	final double ticksPerInch = ticksPerRev / wheelCircum;
    	
//    	final double skidFactor = 1.5 * ticksPerInch; // 0.0104 // 1.5
    	final double skidFactor = 1.0104;
    	final double resistFactor = 1.5 * ticksPerInch;
    	
//        final double dist = inches * ticksPerInch + resistFactor + skidFactor; 
        final double dist = inches * ticksPerInch * skidFactor + resistFactor; 
        
        
        
    	return (int) Math.round(dist);
    }
    
    public double degreesToTicks(double degrees) {
    	return inchesToTicks(degreesToInches(degrees));
    }
    
    public double degreesToInches(double degrees) {
    	final double rightTurnInches = 51;
    	final double rightTurnDegrees = 90;
    	final double inchesPerDegree = rightTurnInches / rightTurnDegrees;
    	
    	return degrees * inchesPerDegree;	
    }
    
    public double peakSpeedAdjust(double speed) {
    	return speed * 1;
    }
}

