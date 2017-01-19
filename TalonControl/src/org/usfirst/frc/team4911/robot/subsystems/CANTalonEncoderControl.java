package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

/**
 *
 */
public class CANTalonEncoderControl {
    
	CANTalon talon;
	double kf, kp, kd, ki, rampRate;
	int iZone;
	double peakOutputVoltage, nominalOutputVoltage;
	CANTalon.FeedbackDevice encoderType;
	int ticksPerRev;
	int encoderCodesPerRev;
	boolean reverseSensor;
	
	public CANTalonEncoderControl(CANTalon talon, CANTalon.FeedbackDevice encoderType, int ticksPerRev, int encoderCodesPerRev, boolean reverseSensor){
		this.encoderType = encoderType;
		this.ticksPerRev = ticksPerRev;
		this.talon = talon;
		this.encoderCodesPerRev = encoderCodesPerRev;
		this.reverseSensor = reverseSensor;
		
		talon.setFeedbackDevice(encoderType);
		talon.configEncoderCodesPerRev(encoderCodesPerRev);
		talon.reverseSensor(reverseSensor);
	}
	
	public void setEncoderPosition(int ticks){
		talon.setEncPosition(ticks);
	}
	
	public void setupPIDMode(double kp, double kd, double ki, double kf, double rampRate, int iZone, double peakOutputVoltage, double nominalOutputVoltage){
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.kf = kf;
		this.iZone = iZone;
		this.rampRate = rampRate;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;		
	}
	
    public void startPIDMode(){
    	int profile = 0;
    	talon.configPeakOutputVoltage(peakOutputVoltage, -peakOutputVoltage);
    	talon.configNominalOutputVoltage(nominalOutputVoltage,  -nominalOutputVoltage);
    	talon.setPID(kp, ki, kd, kf, iZone, rampRate, profile);
    	talon.changeControlMode(CANTalon.TalonControlMode.Position);
    	talon.setVoltageRampRate(rampRate);
    	talon.setEncPosition(0);
    	talon.set(0); //doc says to do this after changing mode but I don't always want to go to 0
    }
    
    public void stopPIDMode(){
    	talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	talon.set(0);
    }

    public void setPIDEncoderGoal(int ticks){
    	talon.set(ticksToRotations(ticks));
    }

    double ticksToRotations(int ticks){
    	return ((double)ticks)/((double)ticksPerRev);
    }
    
    public void setSoftLimits(boolean onOff, int forwardLimit, int reverseLimit){
    	
    	talon.setForwardSoftLimit(ticksToRotations(forwardLimit));
    	talon.enableForwardSoftLimit(onOff);
    	
    	talon.setReverseSoftLimit(ticksToRotations(reverseLimit));
    	talon.enableReverseSoftLimit(onOff);    		
    }
}