package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelFeeder extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortFeeder = 8;
	
	public DefaultMotor feederMotor = new DefaultMotor(tPortFeeder, -1 /*Robot.ss_Config.shooterFeederConst*/, "ShooterFeeder");

	public SS_FuelFeeder() {
		feederMotor.setPowLimit(0.7);
		feederMotor.setBrakeMode(false);
		feederMotor.enablePowLimit(true);
		
		feederMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		feederMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		
	}
	
    public void initDefaultCommand() {
    }
}

