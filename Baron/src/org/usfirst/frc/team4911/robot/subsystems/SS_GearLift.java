package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearLift extends Subsystem {
	
	int tPortLift = 5;
	
	final double topPotValue = 495.0;
	final double lowPotValue = 105.0;
	
	AnalogInput gearPot = new AnalogInput(0);

	public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, topPotValue, lowPotValue,"GearLift");
	
	public SS_GearLift() {
		gearLiftMotor.setPowLimit(0.7);
		gearLiftMotor.enablePowLimit(true);
		gearLiftMotor.setSensor(CANTalon.FeedbackDevice.AnalogPot);
	}

	public void initDefaultCommand() {
    }
	
	public double getGearLiftPot(){
		return gearLiftMotor.getSensorPosition();
	}
	
	public boolean spin(double speed){
		double pot = getGearLiftPot();
		
		if (((speed > 0) && (pot >= topPotValue)) ||
				((speed < 0) && (pot <= lowPotValue))){
			return true;
		}

		gearLiftMotor.spin(speed);
		
		return false;
	}

}

