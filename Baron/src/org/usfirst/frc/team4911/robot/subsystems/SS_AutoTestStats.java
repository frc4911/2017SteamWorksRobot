package org.usfirst.frc.team4911.robot.subsystems;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_AutoTestStats extends Subsystem {
	Subsystem subsystem = null;
	DefaultMotor talon = null;
	String desc = "";
	boolean hasFollower = false;
	
	boolean direction;
	
	boolean hasEncoder;
	double targetPos;
	double encError;
	
	double timeOut;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	// TODO: this is not a Subsystem
    }
    
    public void smartCompletion(boolean hitTarget, double timeOut) {
    	smart("HitTarget " + desc, "" + hitTarget);
    	smart("TimeOut " + desc, "" + timeOut);
    }
    
    public void smartCompletion(double timeOut) {
    	smart("TimeOut " + desc, "" + timeOut);
    }
    
    public void putData(Subsystem subsystem, DefaultMotor talon, boolean direction, double targetPos, double encError) {
    	this.subsystem = subsystem;
    	this.talon = talon;
    	
    	this.hasFollower = talon.hasFollower();
    	
    	this.direction = direction;
    	
    	this.hasEncoder = true;
    	this.targetPos = targetPos;
    	this.encError = encError;
    	
    	displaySmart();
    }
    
    public void putData(Subsystem subsystem, DefaultMotor talon, boolean direction) {
    	this.subsystem = subsystem;
    	this.talon = talon;
    	
    	this.hasFollower = talon.hasFollower();
    	
    	this.direction = direction;
    	
    	this.hasEncoder = false;
    	
    	displaySmart();
    }
    
    private void displaySmart() {
    	desc = talon.getDescription() + " " + direction;
		smartDefaultMotor();
    }
    
    private void smartDefaultMotor() {
    	smart("Current Subsystem", "" + subsystem.getName());
    	
    	smart("Direction " + desc, "" + direction);
    	
		smart("TalonValue " + desc, "" + talon.getTalonValue(false));
		smart("StickyFaults " + desc, "" + talon.checkStickyFaults(talon, false));
		smart("OutVolt " + desc, "" + talon.getOutputVoltage(false));
		smart("OutCurr " + desc, "" + talon.getOutputCurrent(false));
				
		if(hasFollower) {
			smart("StickyFaults " + "f" + desc, "" + talon.checkStickyFaults(talon, true));
			smart("OutVolt " + "f" + desc, "" + talon.getOutputVoltage(true));
			smart("OutCurr " + "f" + desc, "" + talon.getOutputCurrent(true));
		}
		
		smart("TalonSpeed " + desc, "" + talon.getTalonSpeed());
		
		if(hasEncoder) {
			smart("EncPos " + desc, "" + talon.getEncPos());
			smart("TargetPos " + desc, "" + targetPos);
			smart("EncError " + desc, "" + encError);
		}
    }
    
    private void smart(String key, String data) {
		SmartDashboard.putString(key, data);
    }
}

