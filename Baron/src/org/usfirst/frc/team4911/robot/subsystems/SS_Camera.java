package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_CameraUpDown;
import org.usfirst.frc.team4911.robot.commands.C_GearLiftLower;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Camera extends Subsystem {

	Servo servo = new Servo(0);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new C_CameraUpDown());
    }
    
    public void setAngle(double angle){
    	servo.setAngle(angle);
    }

    public double getPosition(){
    	return servo.getPosition();
    }

    public void setPosition(double pos){
    	servo.set(pos);
    }
}

