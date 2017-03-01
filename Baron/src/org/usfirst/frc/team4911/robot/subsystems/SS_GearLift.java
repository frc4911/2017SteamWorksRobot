package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_GearLiftLower;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearLift extends Subsystem {
	
	int tPortLift = 5;
	
	final double lowPotValue = 100;//230.0;
	public final double topPotValue = 880;//1000.0;

	
	AnalogInput gearPot = new AnalogInput(0);

	public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, topPotValue, lowPotValue,"GearLift");
	
	public SS_GearLift() {
		gearLiftMotor.setPowLimit(0.7);
		gearLiftMotor.enablePowLimit(true);
		gearLiftMotor.setSensor(CANTalon.FeedbackDevice.AnalogPot);
		gearLiftMotor.setBrakeMode(true);
		
		gearLiftMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true); //change when limit switch is added
		gearLiftMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true); //change when limit switch is added

	}

	public void initDefaultCommand() {
		setDefaultCommand(new C_GearLiftLower());
    }
	
	public double getGearLiftPot(){
		return gearLiftMotor.getSensorPosition();
	}
}

