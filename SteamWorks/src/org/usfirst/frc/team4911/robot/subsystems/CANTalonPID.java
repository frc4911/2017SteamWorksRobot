package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

/**
 *
 */
public class CANTalonPID {
    
	CANTalon talon;
	double kf, kp, kd, ki, rampRate;
	int iZone;
	double peakOutputVoltage, nominalOutputVoltage;
	
	public CANTalonPID(CANTalon talon, double kf, double kp, double kd, double ki, double rampRate, int iZone, double peakOutputVoltage, double nominalOutputVoltage){
		this.talon = talon;
		this.kf = kf;
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.iZone = iZone;
		this.rampRate = rampRate;
		this.peakOutputVoltage = peakOutputVoltage;
		this.nominalOutputVoltage = nominalOutputVoltage;
	}
	
    public void enterClosedLoop(){
    	int profile = 0;
    	talon.configPeakOutputVoltage(peakOutputVoltage, -peakOutputVoltage);
    	talon.configNominalOutputVoltage(nominalOutputVoltage,  -nominalOutputVoltage);
    	talon.setPID(kp, ki, kd, kf, iZone, rampRate, profile);
    	talon.changeControlMode(CANTalon.TalonControlMode.Position);
    }
    
    public void exitClosedLoop(){
    	talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void setPositionGoal(double pos){
    	//talon.set(pos/4096.0); For mag encoders
    	talon.set(pos/1440.0); // For quad encoders with 1440 ticks/revolution
    }


}

//double kp = 3;
//double kd = 0;
//double ki = 0;
//double kf = 0;
//int iZone = 0;
//double rampRate = 0;
//int profile = 0;
//talon.configPeakOutputVoltage(8.0, -8.0);
//talon.configNominalOutputVoltage(0.0,  -0.0);