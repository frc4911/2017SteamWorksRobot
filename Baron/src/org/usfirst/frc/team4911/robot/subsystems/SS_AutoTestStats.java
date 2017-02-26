package org.usfirst.frc.team4911.robot.subsystems;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_AutoTestStats extends Subsystem {
	Subsystem subsystem = null;
	String desc = "";
	
	boolean direction;
	
	double targetPos;
	double encError;
	
	double timeOut;
	
	NetworkTable table;
	final String ip = "10.49.11.84";
	final String tableName = "AutoTest";
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	// TODO: this is not a Subsystem
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

//		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		table = NetworkTable.getTable(tableName);
    	
    }
    
    public void netTable() {
    }
    
    public void smartCompletion(boolean hitTarget, double timeOut, double distTraveled) {
		smart("DistTraveled " + desc, "" + distTraveled);
    	smart("HitTarget " + desc, "" + hitTarget);
    	smart("TimeOut " + desc, "" + timeOut);
    }
    
    public void smartCompletion(double timeOut) {
    	smart("TimeOut " + desc, "" + timeOut);
    }
    
    public void putData(Subsystem subsystem, DefaultMotor talon, boolean direction, double targetPos, double encError) {
    	this.subsystem = subsystem;
    	
    	this.direction = direction;
    	
    	this.targetPos = targetPos;
    	this.encError = encError;
    	
    	displaySmart(talon, talon.hasFollower(), true);
    }
    
    public void putData(Subsystem subsystem, DefaultMotor talon, boolean direction) {
    	this.subsystem = subsystem;
    	
    	this.direction = direction;
    	
    	displaySmart(talon, talon.hasFollower(), false);
    }
    
    private void displaySmart(DefaultMotor talon, boolean hasFollower, boolean hasEncoder) {
    	desc = talon.getDescription() + " " + direction;
		smartDefaultMotor(talon, hasFollower, hasEncoder);
    }
    
    private void smartDefaultMotor(DefaultMotor talon, boolean hasFollower, boolean hasEncoder) {
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
		table.putString(key, data);
    }
}

