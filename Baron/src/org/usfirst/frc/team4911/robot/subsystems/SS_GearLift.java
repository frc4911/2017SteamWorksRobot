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
	// Positive input to move up
	// Negative input to move down
	
	int tPortLift = 5;
	
	public final double topEncoderValue = 650;
	
	//AnalogInput gearPot = new AnalogInput(0);
	
	public boolean keepGearUp = true;

	//public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, topPotValue, lowPotValue, "GearLift");
	//public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, "GearLift");
	public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, topEncoderValue, 0, "GearLift");
	
	public SS_GearLift() {
		gearLiftMotor.setPowLimit(0.6);
		gearLiftMotor.enablePowLimit(true);
		gearLiftMotor.setSensor(CANTalon.FeedbackDevice.QuadEncoder);
		gearLiftMotor.setBrakeMode(true);
		
		gearLiftMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true); //change when limit switch is added
		gearLiftMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true); //change when limit switch is added
		
		gearLiftMotor.zeroEnc(); // assume homed (down) when code starts

	}

	public void initDefaultCommand() {
		setDefaultCommand(new C_GearLiftLower());
    }
	
	public double getGearLiftPot(){
		return gearLiftMotor.getSensorPosition();
	}
}

