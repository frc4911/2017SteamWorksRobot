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
	
	private int tPort = 4;
	public DefaultMotor gearCollector = new DefaultMotor(tPort, Robot.ss_Config.gearCollectorConst, "Gear Collector");
	
	private DigitalInput limitSwitch = new DigitalInput(0);
	public boolean getLimitSwitch() {
		// true is when the limit switch is triggered
		// false is when the limit switch is not triggered
		return limitSwitch.get();
	}
	
    public void initDefaultCommand() {
    }
}

