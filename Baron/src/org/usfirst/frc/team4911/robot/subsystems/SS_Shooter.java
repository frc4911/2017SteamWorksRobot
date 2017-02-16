package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortIntake = 20;
	int tPortShooter = 20;
	int tPortShooterF = 20;
	
	public DefaultMotor intakeMotor = new DefaultMotor(tPortIntake, Robot.ss_Config.shooterIntakeConst, "Shooter Intake");
	public DefaultMotor shooterMotor = new DefaultMotor(tPortIntake, Robot.ss_Config.shooterConst, "Shooter Flywheel");

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

