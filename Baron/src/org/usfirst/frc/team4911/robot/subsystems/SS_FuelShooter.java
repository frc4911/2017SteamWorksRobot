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
	int tPortShooterF2 = 20;
	
	public DefaultMotor feederMotor = new DefaultMotor(tPortFeeder, Robot.ss_Config.shooterFeederConst, "ShooterFeeder");
	public DefaultMotor shooterMotors = new DefaultMotor(tPortShooter, tPortShooterF, tPortShooterF2, Robot.ss_Config.shooterFlywheelConst, "ShooterFlywheel");

	public SS_FuelShooter() {
		shooterMotors.setPowLimit(0.7);
		feederMotor.setPowLimit(0.7);
		
		shooterMotors.enablePowLimit(false);
		feederMotor.enablePowLimit(false);
	}
	
    public void initDefaultCommand() {
    }
}

