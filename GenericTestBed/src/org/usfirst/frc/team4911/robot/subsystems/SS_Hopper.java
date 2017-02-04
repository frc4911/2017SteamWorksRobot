package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Hopper extends Subsystem {
	CANTalon talon;
	double speed = 0;
	int CANID;
	boolean started = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public SS_Hopper(int CANID){
    	this.CANID = CANID;
    	talon = new CANTalon(CANID);
    	talon.enableBrakeMode(false);
    }
    
    public void startMotor(){
    	speed = SmartDashboard.getNumber("motor"+CANID+" speed", 0);
    	
    	if (speed == 0){
    		SmartDashboard.putNumber("motor"+CANID+" speed", 0);
    	}
    	talon.set(speed);
    	started = true;
    }
    
    public void stopMotor(){
    	talon.set(0);
    	started = false;
    }
    
    public void bumpSpeed(double bump){
    	if (!started){
    		startMotor();
    		return;
    	}
    	
    	speed += bump;
    	
    	if (speed > 1)
    		speed = 1;
    	else if (speed < -1)
    		speed = -1;
    	
    	talon.set(speed);
    	SmartDashboard.putNumber("motor"+CANID+" speed", speed);
    }
}

