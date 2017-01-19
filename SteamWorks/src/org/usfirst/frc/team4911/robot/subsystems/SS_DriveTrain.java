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
    CANTalon DriveMotorRearLeft = new CANTalon(TPortDriveMotorRearLeft);
    
    public CANTalon DriveMotorFrontRight = new CANTalon(TPortDriveMotorFrontRight);
    CANTalon DriveMotorRearRight = new CANTalon(TPortDriveMotorRearRight);
    
    public CANTalonPID drivePIDLeft;
    public CANTalonPID drivePIDRight;
    
    final double kp = 0.5;
    final double ki = 0.01;
    final double rampRate = 0;
    final double peakOutputVoltage = 12;
    final double nominalOutputVoltage = 2.2;
    final int iZone = 1;
    
    public SS_DriveTrain(){
    	DriveMotorRearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
    	DriveMotorRearLeft.set(TPortDriveMotorFrontLeft);
    	DriveMotorFrontLeft.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	DriveMotorFrontLeft.setPosition(0);
    	DriveMotorFrontLeft.configEncoderCodesPerRev(360);
  	
    	DriveMotorRearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
    	DriveMotorRearRight.set(TPortDriveMotorFrontRight);
    	DriveMotorFrontRight.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	DriveMotorFrontRight.setPosition(0);
    	DriveMotorFrontRight.configEncoderCodesPerRev(360);
    }
	
    public void driveByJoystick(double leftInput, double rightInput) {
    	DriveMotorFrontLeft.set(leftInput);
    	DriveMotorRearLeft.set(leftInput);
    	
		DriveMotorFrontRight.set(-rightInput);
		DriveMotorRearRight.set(-rightInput);
    }
    
    public void startClosedLoop() {
    	drivePIDLeft = new CANTalonPID(DriveMotorFrontLeft, 0, kp, 0, ki, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage);
    	drivePIDRight = new CANTalonPID(DriveMotorFrontRight, 0, kp, 0, ki, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage);
    	
    	drivePIDLeft.enterClosedLoop();
    	drivePIDRight.enterClosedLoop();
    	
    	DriveMotorFrontRight.setPosition(0);
    	DriveMotorFrontLeft.setPosition(0);
    }
    
    public void driveByPID(double pos) {
    	drivePIDLeft.setPositionGoal(pos);
    	drivePIDRight.setPositionGoal(pos);
    }
    
    public void endClosedLoop() {
    	drivePIDLeft.exitClosedLoop();
    	drivePIDRight.exitClosedLoop();
    }
    
	//true = forwards
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

