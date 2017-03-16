package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelFeeder extends Subsystem {
	public double speed = 0.2;
	
	int tPortFeeder = 8;
	
	public DefaultMotor feederMotor = new DefaultMotor(tPortFeeder, -1 /*Robot.ss_Config.shooterFeederConst*/, "ShooterFeeder");

	public SS_FuelFeeder() {
		feederMotor.setPowLimit(0.55);
		feederMotor.setBrakeMode(false);
		feederMotor.enablePowLimit(true);
		
		feederMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		feederMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		
	}
	
    public void initDefaultCommand() {
    }
}

