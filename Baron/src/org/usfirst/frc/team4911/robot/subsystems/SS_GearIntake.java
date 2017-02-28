package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_GearIntake extends Subsystem {

	int tPortGearIntake = 4;
	
	public DefaultMotor gearIntakeMotor = new DefaultMotor(tPortGearIntake, Robot.ss_Config.gearIntakeConst, "GearIntake");
	
	public SS_GearIntake() {
		
		gearIntakeMotor.setPowLimit(0.7);
		gearIntakeMotor.enablePowLimit(true);		
		
		gearIntakeMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(false);
		//gearIntakeMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(false);

	}
	
	private DigitalInput lsIntake = new DigitalInput(0);
	public boolean getLSIntake() {
		// false is when the limit switch is triggered
		// true is when the limit switch is not triggered
		return lsIntake.get();
	}
	
    public void initDefaultCommand() {
    }
}

