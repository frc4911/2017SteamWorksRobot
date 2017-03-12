package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortShooter = 9;
	int tPortShooterF = 10;
	
	public DefaultMotor shooterMotors = new DefaultMotor(tPortShooter, tPortShooterF, -1/*Robot.ss_Config.shooterFlywheelConst*/, "ShooterFlywheel");

	public SS_FuelShooter() {
		shooterMotors.setPowLimit(0.77);
		shooterMotors.setBrakeMode(false);
		shooterMotors.enablePowLimit(true);
		
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

