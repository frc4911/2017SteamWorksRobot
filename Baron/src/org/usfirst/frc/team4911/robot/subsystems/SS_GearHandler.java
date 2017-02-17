package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearHandler extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortCollector = 4;
	int tPortLift = 5;
	
	public DefaultMotor gearCollector = new DefaultMotor(tPortCollector, Robot.ss_Config.gearCollectorConst, "Gear Collector");
	public DefaultMotor gearLift = new DefaultMotor(tPortLift, Robot.ss_Config.gearLiftConst, "Gear Lift");
	
	private DigitalInput lsCollect = new DigitalInput(0);
	public boolean getLSCollect() {
		// false is when the limit switch is triggered
		// true is when the limit switch is not triggered
		return lsCollect.get();
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
	
    public void initDefaultCommand() {
    }
}

