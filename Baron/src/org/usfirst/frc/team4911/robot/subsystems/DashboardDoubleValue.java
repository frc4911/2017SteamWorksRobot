package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DashboardDoubleValue {

	String key;
	double defaultValue;
	
	public DashboardDoubleValue(String key, double defaultValue){
		this.key = key;
		this.defaultValue = defaultValue;
		
//		double value = SmartDashboard.getNumber(key, defaultValue);
//
//		if(value == defaultValue){
//			SmartDashboard.putNumber(key, defaultValue);
//		}
	}
	
	public void putDouble(double newValue){
		SmartDashboard.putNumber(key, newValue);
	}
	
	public double getNumber(){
		return SmartDashboard.getNumber(key,defaultValue);
	}
}

