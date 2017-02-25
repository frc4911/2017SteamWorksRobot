package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CANTalonPID{

	CANTalon talon;
	int currTicks = 0;
	
	public CANTalonPID(
			CANTalon talon, 
			CANTalon.FeedbackDevice encoderType, 
			int ticksPerRev, 
			int encoderCodesPerRev, 
			boolean reverseSensor, 
			double kp, double kd, double ki, double kf, 
			double rampRate, 
			int iZone, 
			double peakOutputVoltage, 
			double nominalOutputVoltage, 
			CANTalon.TalonControlMode PIDType,
			int ticks,
			boolean encoderFlip,
			boolean flipMotorDir)
	{
		
    	int profile = 0;

    	this.talon = talon;
    	//talon.changeMotionControlFramePeriod(20);
    	this.talon.reverseOutput(flipMotorDir);
    	this.talon.reverseSensor(encoderFlip);
    	this.talon.setFeedbackDevice(encoderType);
    	this.talon.configEncoderCodesPerRev(encoderCodesPerRev);
    	this.talon.reverseSensor(reverseSensor);
    	this.talon.configPeakOutputVoltage(peakOutputVoltage, -peakOutputVoltage);
    	this.talon.configNominalOutputVoltage(nominalOutputVoltage,  -nominalOutputVoltage);
    	this.talon.setPID(kp, ki, kd, kf, iZone, rampRate, profile);
    	this.talon.changeControlMode(PIDType);
    	this.talon.setVoltageRampRate(rampRate);
    	this.talon.setCloseLoopRampRate(rampRate);
    	this.talon.setEncPosition(0);
    	double set = 0;
    	if (encoderType == CANTalon.FeedbackDevice.QuadEncoder){
    		if (PIDType == CANTalon.TalonControlMode.Position){
    			set = ((double)ticks)/((double)ticksPerRev);
    		}
    		else if (PIDType == CANTalon.TalonControlMode.Speed){
    			set = ticks; //encoder rpm
    			currTicks = ticks;
    		}
    	}
    	else if (encoderType == CANTalon.FeedbackDevice.AnalogPot){
    		if (PIDType == CANTalon.TalonControlMode.Position){
    			set = ticks;
    		}
    		else if (PIDType == CANTalon.TalonControlMode.Speed){
    			set = ticks; //encoder rpm
    			currTicks = ticks;
    		}
    	}
    	 
    	this.talon.set(set);
    	SmartDashboard.putString("PID target",""+set);
	}
	
	public void setTicks(int newTicks){
			talon.set(newTicks);
			currTicks = newTicks;
			SmartDashboard.putString("PID target", "" + newTicks);
	}
	
	public int getTicks(){
		return currTicks;
	}
	
    public void stopPIDMode(){
    	//talon.changeMotionControlFramePeriod(100);
    	talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	talon.setVoltageRampRate(0);
    	talon.set(0);
    	SmartDashboard.putString("PID target","off" );
    }
}