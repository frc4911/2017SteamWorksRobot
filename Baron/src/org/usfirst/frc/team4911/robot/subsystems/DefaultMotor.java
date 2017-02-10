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
	public CANTalon talon;
	public CANTalon fTalon;
	public CANTalonPID pid;
	public boolean limited;
	public double upLimit;
	public double lowLimit;
	
	final int TICKS_PER_REV = 1440;
	final int ENCODER_CODES_PER_REV = 360;
	double kf = 0;
	double kp;
	double kd;
	double ki;
	double rampRate;
	int iZone;
	double peakOutputVoltage; 
	double nominalOutputVoltage; 
	CANTalon.TalonControlMode PIDType;
	
	public DefaultMotor(int TPort) {
		this.talon = new CANTalon(TPort);
		
		limited = false;
	}
	
	public DefaultMotor(int TPort, double kp, double kd, double ki, double rampRate, int iZone, double peakOutputVoltage, 
						double nominalOutputVoltage, CANTalon.TalonControlMode PIDType) {
		this.talon = new CANTalon(TPort);
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.rampRate = rampRate;
		this.iZone = iZone;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;
		this.PIDType = PIDType;
		
		limited = false;
	}
	
	public DefaultMotor(int TPort, int TPortF, double kp, double kd, double ki, double rampRate, int iZone, double peakOutputVoltage, 
						double nominalOutputVoltage, CANTalon.TalonControlMode PIDType) {
		this.talon = new CANTalon(TPort);
		this.fTalon = new CANTalon(TPortF);
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.rampRate = rampRate;
		this.iZone = iZone;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;
		this.PIDType = PIDType;
		
		limited = false;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
	}
	
	public DefaultMotor(int TPort, double upLimit, double lowLimit, double kp, double kd, double ki, double rampRate, 
						int iZone, double peakOutputVoltage, double nominalOutputVoltage, CANTalon.TalonControlMode PIDType) {
		this.talon = new CANTalon(TPort);
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.rampRate = rampRate;
		this.iZone = iZone;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;
		this.PIDType = PIDType;
		
		limited = true;
		
		setSoftLimits(talon);
		enableSoftLimits(talon, true);
	}
	
	public DefaultMotor(int TPort, int TPortF, double upLimit, double lowLimit, double kp, double kd, double ki, double rampRate, int iZone, 
						double peakOutputVoltage, double nominalOutputVoltage, CANTalon.TalonControlMode PIDType) {
		this.talon = new CANTalon(TPort);
		this.fTalon = new CANTalon(TPortF);
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.rampRate = rampRate;
		this.iZone = iZone;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;
		this.PIDType = PIDType;
		
		limited = true;
		
		this.fTalon.changeControlMode(TalonControlMode.Follower);
		this.fTalon.set(TPort);
		
		setSoftLimits(talon);
		enableSoftLimits(talon, true);
		
		setSoftLimits(this.fTalon);
		enableSoftLimits(this.fTalon, true);
	}
	
	
	
	public void moveToEncPos(int ticks) {
		pid = new CANTalonPID(talon, CANTalon.FeedbackDevice.QuadEncoder, TICKS_PER_REV, ENCODER_CODES_PER_REV, false,
				   			  kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType, ticks);
	}
	
	public void stopPID() {
		pid.stopPIDMode();
	}
	
	public void spin(double pow, double constant) {
		pow *= constant;
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
		talon.enableBrakeMode(set);
	}
	
	public double getEncPos() {
		return (talon.getEncPosition());
	}
	
	public void zeroEnc() {
		talon.setEncPosition(0);
	}
}

