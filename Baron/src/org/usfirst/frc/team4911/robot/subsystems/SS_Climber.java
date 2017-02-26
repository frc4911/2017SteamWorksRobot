package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_ClimberUp;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Climber extends Subsystem {
	
	int tPortClimber = 11;
	int tPortClimberF = 20;
	
	public DefaultMotor climberMotors = new DefaultMotor(tPortClimber, tPortClimberF, Robot.ss_Config.climberConst, "Climber");
	
	public SS_Climber() {
		climberMotors.setPowLimit(0.7);
		climberMotors.enablePowLimit(true);
		climberMotors.setBrakeMode(true);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new C_ClimberUp());
    }
}

