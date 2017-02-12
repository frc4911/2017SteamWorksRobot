package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_TestMotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_TestDriveByJoystick());
    }
    
    String[] keys = { 
    		"driveTrainLeft",
    		"driveTrainRight",
    		"fullDriveTrain"
    };
    String key = "currTestMotor";
    public void runMotor() {
    	runMotor(motorSpeed);
    }
    
    public void runMotor(double speed) {
    	switch(currMotor) {
    	case 0:
    		Robot.ss_DriveTrain.driveTrainLeft.spin(speed, -1);
    		break;
    	case 1:
    		Robot.ss_DriveTrain.driveTrainRight.spin(speed, 1);
    		break;
    	case 2:
    		Robot.ss_DriveTrain.driveTrainLeft.spin(speed, -1);
    		Robot.ss_DriveTrain.driveTrainRight.spin(speed, 1);
    		break;
    	}
    }
    
    int currMotor = 0;
    int maxMotor = keys.length - 1;
    public void advanceMotor(boolean forward) {
    	if(forward) {
    		currMotor++;
    		if(currMotor > maxMotor) {
    			currMotor = 0;
    		}
    	}
    	else {
    		currMotor--;
    		if(currMotor < 0) {
    			currMotor = maxMotor;
    		}
    	}
    	SmartDashboard.putString(key, keys[currMotor]);
    }
    
    double motorSpeed = 0;
    public void bumpSpeed(boolean increase) {
    	if(increase) {
    		motorSpeed += 0.025;
    		if(motorSpeed > 1) {
    			motorSpeed = 1;
    		}
    	}
    	else {
    		motorSpeed -= 0.025;
    		if(motorSpeed < -1) {
    			motorSpeed = -1;
    		}
    	}
    	SmartDashboard.putNumber("motorTestSpeed", motorSpeed);
    }
    
    public void stopMotor() {
    	runMotor(0);
    }
}

