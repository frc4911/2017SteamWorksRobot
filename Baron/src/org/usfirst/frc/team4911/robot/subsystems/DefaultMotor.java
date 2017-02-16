package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DefaultMotor {
	private CANTalon talon;
	private CANTalon fTalon;
	private CANTalonPID pid;
	private double constant;
	private boolean limited;
	private boolean motorPair;
	private double upLimit;
	private double lowLimit;
	
	private String description;
	
	public DefaultMotor(int TPort, double constant, String description) {
		limited = false;
		motorPair = false;
		
		construct(TPort, constant, description);
	}
	
	public DefaultMotor(int TPort, int TPortF, double constant, String description) {
		this.fTalon = new CANTalon(TPortF);
		
		limited = false;
		motorPair = true;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
		
		construct(TPort, constant, description);
	}
	
	public DefaultMotor(int TPort, double constant, double upLimit, double lowLimit, String description) {
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		this.description = description;
		
		limited = true;
		motorPair = false;
		
		construct(TPort, constant, description);
	}
	
	public DefaultMotor(int TPort, int TPortF, double constant, double upLimit, double lowLimit, String description) {
		this.fTalon = new CANTalon(TPortF);
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		
		limited = true;
		motorPair = true;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
		
		construct(TPort, constant, description);
	}
	
	private void construct(int TPort, double constant, String description) {
		this.talon = new CANTalon(TPort);
		this.constant = constant;
		this.description = description;
		
		if(limited) {
			setSoftLimits(talon);
			enableSoftLimits(talon, true);
		}
	}
	
	public void moveToEncPos(int ticks, int tickPerRev, int encoderTicksPerRev, double kp, double kd, double ki, double kf, double rampRate, int iZone, 
							 double peakOutputVoltage, double nominalOutputVoltage, CANTalon.TalonControlMode PIDType) {
		pid = new CANTalonPID(talon, CANTalon.FeedbackDevice.QuadEncoder, tickPerRev, encoderTicksPerRev, false,
				   			  kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType, ticks);
	}
	
	public void stopPID() {
		pid.stopPIDMode();
		zeroEnc();
	}
	
	public void spin(double pow) {
		pow *= constant;
		talon.set(pow);
	}

	public void stop() {
		spin(0);
	}
	
	public void setSoftLimits(CANTalon talon) {
		talon.setForwardSoftLimit(upLimit);
		talon.setReverseSoftLimit(lowLimit);
	}
	
	public void enableSoftLimits(CANTalon talon, boolean onOff) {
		talon.enableForwardSoftLimit(onOff);
		talon.enableReverseSoftLimit(onOff);
	}
	
	public void setBrakeMode(boolean set) {
		if(motorPair) {
			talon.enableBrakeMode(set);
			fTalon.enableBrakeMode(set);
		}
		else {
			talon.enableBrakeMode(set);
		}
	}
	
	public double getEncPos() {
		return (talon.getEncPosition());
	}
	
	public void zeroEnc() {
		talon.setEncPosition(0);
	}
	
	public CANTalon getTalon() {
		return talon;
	}
	
	public CANTalon getFollowerTalon() {
		return fTalon;
	}
	
	public double getOutputVoltage(boolean follower) {
		if(follower) {
			return fTalon.getOutputVoltage();
		} else {
			return talon.getOutputVoltage();
		}
	}
	
	public double getOutputCurrent(boolean follower) {
		if(follower) {
			return fTalon.getOutputCurrent();
		} else {
			return talon.getOutputCurrent();
		}
	}
	
	public double getTalonValue(boolean follower) {
		if(follower) {
			return fTalon.get();
		} else {
			return talon.get();
		}
	}
	
	public int getStickyFaultUnderVoltage(boolean follower) {
		if(follower) {
			return fTalon.getStickyFaultUnderVoltage();
		}
		else {
			return talon.getStickyFaultUnderVoltage();
		}
	}
	
	public int getStickyFaultOverTemp(boolean follower) {
		if(follower) {
			return fTalon.getStickyFaultOverTemp();
		}
		else {
			return talon.getStickyFaultOverTemp();
		}
	}
	
	public void clearStickyFaults(boolean follower) {
		if(follower) {
			fTalon.clearStickyFaults();
		}
		else {
			talon.clearStickyFaults();
		}
	}
	
	public String checkStickyFaults(DefaultMotor motor, boolean follower) {
    	int underVolt = getStickyFaultUnderVoltage(follower);
    	int overTemp = getStickyFaultOverTemp(follower);
    	
    	if((underVolt == 0) && (overTemp == 0)) {
    		return "none";
    	}
    	
    	clearStickyFaults(follower);
    	
    	String fault = "";
    	if(underVolt != 0) {
    		fault += "underVoltage ";
    	}
    	if(overTemp != 0) {
    		fault += "overTemp ";
    	}
    	
    	return fault;
    }
	
	public double getTalonSpeed() {
		return talon.getSpeed();
	}
	
	public double getEncVelocity() {
		return talon.getEncVelocity();
	}
	
	public String getDescription() {
		return description;
	}
}

