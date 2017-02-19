package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearLift extends Subsystem {
	
	int tPortLift = 5;
	
	final double topPotValue = 1.90;
	final double lowPotValue = .727;
	
	AnalogInput gearPot = new AnalogInput(0);

	public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, "GearLift");

	public void initDefaultCommand() {
    }
	
	public double getGearLiftPot(){
		return gearPot.getVoltage();
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

