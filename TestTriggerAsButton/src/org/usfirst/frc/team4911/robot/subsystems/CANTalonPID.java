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
			int ticks)
	{
		
    	int profile = 0;

    	this.talon = talon;
    	//talon.changeMotionControlFramePeriod(20);
    	
    	talon.setFeedbackDevice(encoderType);
		talon.configEncoderCodesPerRev(encoderCodesPerRev);
		talon.reverseSensor(reverseSensor);
    	talon.configPeakOutputVoltage(peakOutputVoltage, -peakOutputVoltage);
    	talon.configNominalOutputVoltage(nominalOutputVoltage,  -nominalOutputVoltage);
    	talon.setPID(kp, ki, kd, kf, iZone, rampRate, profile);
    	talon.changeControlMode(PIDType);
    	talon.setVoltageRampRate(rampRate);
    	talon.setCloseLoopRampRate(rampRate);
    	talon.setEncPosition(0);
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
    	talon.set(set);
    	SmartDashboard.putString("PID target",""+set);
	}
	
	public void setTicks(int newTicks){
			talon.set(newTicks);
			currTicks = newTicks;
			SmartDashboard.putString("PID target",""+newTicks);
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