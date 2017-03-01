package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortFeeder = 8;
	int tPortShooter = 9;
	int tPortShooterF = 10;
	
	public DefaultMotor feederMotor = new DefaultMotor(tPortFeeder, -1 /*Robot.ss_Config.shooterFeederConst*/, "ShooterFeeder");
	public DefaultMotor shooterMotors = new DefaultMotor(tPortShooter, tPortShooterF, -1/*Robot.ss_Config.shooterFlywheelConst*/, "ShooterFlywheel");

	public SS_FuelShooter() {
		shooterMotors.setPowLimit(0.9);
		feederMotor.setPowLimit(0.7);
		
		shooterMotors.enablePowLimit(true);
		feederMotor.enablePowLimit(true);
		
		feederMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		feederMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		
		shooterMotors.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		shooterMotors.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		shooterMotors.getFollowerTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		shooterMotors.getFollowerTalon().ConfigRevLimitSwitchNormallyOpen(true);
//		shooterMotors.getFollowerTalon2().ConfigFwdLimitSwitchNormallyOpen(true); //not added yet
//		shooterMotors.getFollowerTalon2().ConfigRevLimitSwitchNormallyOpen(true);
		
	}
	
    public void initDefaultCommand() {
    }
}

