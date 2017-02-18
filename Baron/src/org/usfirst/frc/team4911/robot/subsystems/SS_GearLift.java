package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearLift extends Subsystem {
	
	int tPortLift = 5;

	public DefaultMotor gearLiftMotor = new DefaultMotor(tPortLift, Robot.ss_Config.gearIntakeConst, "GearLift");

	public void initDefaultCommand() {
    }
	
	private DigitalInput lsUp = new DigitalInput(1);
	public boolean getLSUp() {
		// false is when the limit switch is triggered
		// true is when the limit switch is not triggered
		return lsUp.get();
	}
	
	private DigitalInput lsDown = new DigitalInput(2);
	public boolean getLSDown() {
		// false is when the limit switch is triggered
		// true is when the limit switch is not triggered
		return lsDown.get();
	}
		

}

