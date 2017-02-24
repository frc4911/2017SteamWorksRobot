package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelCollector extends Subsystem {

	int tPortCollector = 6;
	int tPortCollectorF = 12;

	public DefaultMotor collectorMotors = new DefaultMotor(tPortCollector, tPortCollectorF, Robot.ss_Config.fuelCollectorConst, "FuelCollector");
	
	public SS_FuelCollector() {
		collectorMotors.setPowLimit(0.7);
		collectorMotors.enablePowLimit(true);
	}
	
    public void initDefaultCommand() {
    }
}

