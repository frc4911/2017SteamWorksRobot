package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_TestMotor extends Subsystem {

    public void initDefaultCommand() {
    }

    DefaultMotor[] motors = new DefaultMotor[9];
    double maxCurrent = 0;
    String maxCurrStr = "maxCurrent";
    double maxSpeed = 0;
    String maxSpeedStr = "maxSpeed";
    double maxVoltage = 0;
    String maxVoltageStr = "maxVoltage";
    double minVoltage = 100;
    String minVoltageStr = "min batery Voltage";
    
    public SS_TestMotor(){
    	
        motors[0] = Robot.ss_Climber.climberMotors;
        motors[1] = Robot.ss_DriveTrainLeft.leftMotors;
        motors[2] = Robot.ss_DriveTrainRight.rightMotors;
        motors[3] = Robot.ss_FuelCollector.collectorMotors;
        motors[4] = Robot.ss_FuelHopper.hopperMotor;
        motors[5] = Robot.ss_FuelShooter.feederMotor;
        motors[6] = Robot.ss_FuelShooter.shooterMotors;
        motors[7] = Robot.ss_GearIntake.gearIntakeMotor;
        motors[8] = Robot.ss_GearLift.gearLiftMotor;
    }

    String key = "currTestMotor";
    public void runMotor() {
    	runMotor(motorSpeed);
    }
    
    public void runMotor(double speed) {
    	speed = motors[currMotor].spin(speed);
    	
    	double value = motors[currMotor].getOutputCurrent(false);
    	if (value > maxCurrent){
    		maxCurrent = value;
        	SmartDashboard.putString(maxCurrStr, ""+maxCurrent);
    	}
    	
    	value = motors[currMotor].getOutputVoltage(false);
    	if (value > maxVoltage){
    		maxVoltage = value;
        	SmartDashboard.putString(maxVoltageStr, ""+maxVoltage);
    	}
    	
    	if (speed > maxSpeed){
    		maxSpeed = speed;
        	SmartDashboard.putString(maxSpeedStr, ""+maxSpeed);
    	}
    	
    	value = motors[currMotor].getInputVoltage(false);
    	if (value < minVoltage){
    		minVoltage = value;
        	SmartDashboard.putString(minVoltageStr, ""+minVoltage);
    	}
    }
    
    int currMotor = 0;
    int maxMotor = motors.length - 1;
    public void advanceMotor(boolean forward) {
    	if(forward) {
    		currMotor++;
    		if(currMotor > maxMotor) {
    			currMotor = 0;
    		}
    	}
    	else {
    		currMotor--;
    		if(currMotor < 0) {
    			currMotor = maxMotor;
    		}
    	}
    	maxCurrent = 0;
    	SmartDashboard.putString(maxCurrStr, ""+maxCurrent);
    	maxVoltage = 0;
    	SmartDashboard.putString(maxVoltageStr, ""+maxVoltage);
    	maxSpeed = 0;
    	SmartDashboard.putString(maxSpeedStr, ""+maxSpeed);
    	minVoltage = 100;
    	SmartDashboard.putString(minVoltageStr, ""+minVoltage);
    	
    	SmartDashboard.putString(key, motors[currMotor].getDescription());
    }
    
    public void resetCurrMotor() {
    	currMotor = 0;
    }
    
    double motorSpeed = 0;
    public void bumpSpeed(boolean increase) {
    	if(increase) {
    		motorSpeed += 0.025;
    		if(motorSpeed > 1) {
    			motorSpeed = 1;
    		}
    	}
    	else {
    		motorSpeed -= 0.025;
    		if(motorSpeed < -1) {
    			motorSpeed = -1;
    		}
    	}
    	SmartDashboard.putNumber("motorTestSpeed", motorSpeed);
    }
    
    public int getNumMotors() {
    	return motors.length - 1;
    }
    
    public void zeroEnc() {
    	motors[currMotor].zeroEnc();
    }
    
    public double getEncPos() {
    	return motors[currMotor].getEncPos();
    }
    
    public void stopMotor() {
    	runMotor(0);
    }
}

