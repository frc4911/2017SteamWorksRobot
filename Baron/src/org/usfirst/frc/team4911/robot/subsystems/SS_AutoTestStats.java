package org.usfirst.frc.team4911.robot.subsystems;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_AutoTestStats extends Subsystem {

    String[] dtLeft;
    String[] dtRight;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void displaySmart() {
    	if(Robot.oi.autoTest.isRunning()) {
    		
    		
    	}
    }
    
    private void driveTrainLeft(String key, String[] data) {
    	if(Objects.equals(key, "dtLeft"))
    		dtLeft = data;
    }
    
    private void logDefaultMotor(DefaultMotor motor, boolean hasFollower, boolean hasEncoder, int index) {
    	boolean smart = false;
//		smart(smart, log, index++, "" + motor.getTalonValue(false));
//		smart(smart, log, index++,motor.checkStickyFaults(motor, false));
//		smart(smart, log, index++,"" + motor.getOutputVoltage(false));
//		smart(true, log, index++, "" + motor.getOutputCurrent(false));// hardcode to dashboard for debug 
				
		if(hasFollower) {
//			smart(smart, log, index++,motor.checkStickyFaults(motor, true));
//			smart(smart, log, index++,"" + motor.getOutputVoltage(true));
//			smart(smart, log, index++,"" + motor.getOutputCurrent(true));
		}
//		smart(smart, log, index++, "" + motor.getTalonSpeed());
		if(hasEncoder) {
//			smart(true, log, index++,"" + motor.getEncPos());
		}
    }
    
    private void smart(String key, String data) {
		SmartDashboard.putString(key, data);
    }
}

