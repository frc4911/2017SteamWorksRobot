package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_TestMotor extends Subsystem {

    public void initDefaultCommand() {
    }

    DefaultMotor[] motors = new DefaultMotor[9];
    
    public SS_TestMotor(){
    	
        motors[0] = Robot.ss_Climber.climberMotor;
        motors[1] = Robot.ss_DriveTrain.leftMotors;
        motors[2] = Robot.ss_DriveTrain.rightMotors;
        motors[3] = Robot.ss_FuelCollector.collectorMotors;
        motors[4] = Robot.ss_FuelHopper.hopperMotor;
        motors[5] = Robot.ss_FuelShooter.feederMotor;
        motors[6] = Robot.ss_FuelShooter.shooterMotors;
        motors[7] = Robot.ss_GearIntake.gearIntakeMotor;
        motors[8] = Robot.ss_GearLift.gearLiftMotor;
    }

    String key = "currTestMotor";
    public void runMotor() {
    	runMotor(motorSpeed);
    }
    
    public void runMotor(double speed) {
    	motors[currMotor].spin(speed);
    }
    
    int currMotor = 0;
    int maxMotor = motors.length - 1;
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
    	SmartDashboard.putString(key, motors[currMotor].getDescription());
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

