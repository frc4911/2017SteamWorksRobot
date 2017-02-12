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
	private boolean limited;
	private boolean motorPair;
	private double upLimit;
	private double lowLimit;
	
	private String description;
	
	public DefaultMotor(int TPort, String description) {
		limited = false;
		motorPair = false;
		
		construct(TPort, description);
	}
	
	public DefaultMotor(int TPort, int TPortF, String description) {
		this.fTalon = new CANTalon(TPortF);
		
		limited = false;
		motorPair = true;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
		
		construct(TPort, description);
	}
	
	public DefaultMotor(int TPort, double upLimit, double lowLimit, String description) {
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		this.description = description;
		
		limited = true;
		motorPair = false;
		
		construct(TPort, description);
	}
	
	public DefaultMotor(int TPort, int TPortF, double upLimit, double lowLimit, String description) {
		this.fTalon = new CANTalon(TPortF);
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		
		limited = true;
		motorPair = true;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
		
		construct(TPort, description);
	}
	
	private void construct(int TPort, String description) {
		this.talon = new CANTalon(TPort);
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
	}
	
	public void spin(double pow, double constant) {
		pow *= constant;
		talon.set(pow);
	}

	public void stop() {
		spin(0, 0);
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

