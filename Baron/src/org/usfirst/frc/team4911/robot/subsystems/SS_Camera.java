package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_CameraUpDown;
import org.usfirst.frc.team4911.robot.commands.C_GearLiftLower;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_Camera extends Subsystem {

	Servo servo = new Servo(0);
	public final double topAngle = 105;
	public final double bottomAngle = 145;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new C_CameraUpDown());
    }
    
    public double getAngle(){
    	return servo.getAngle();
    }

    public void setAngle(double angle){
    		servo.setAngle(angle);
    }

    public double getPosition(){
    	return servo.getPosition();
    }

    public void setPosition(double pos){
    	double currentPos = servo.getPosition();
    	
    	if ((currentPos < pos) && (pos < .895)){
    		servo.set(pos);
    	}
    	else if ((currentPos > pos) && (pos > .555)){
    		servo.set(pos);
    	}
    }
}

