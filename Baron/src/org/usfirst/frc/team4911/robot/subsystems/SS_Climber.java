package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_ClimberUp;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Climber extends Subsystem {
	
	int tPortClimber = 11;
	int tPortClimberF = 13;
	
	public DefaultMotor climberMotors = new DefaultMotor(tPortClimber, tPortClimberF, Robot.ss_Config.climberConst, "Climber");
	
	public SS_Climber() {
		climberMotors.setPowLimit(0.8);
		climberMotors.enablePowLimit(true);
		climberMotors.setBrakeMode(true);
		climberMotors.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		climberMotors.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
		climberMotors.getFollowerTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		climberMotors.getFollowerTalon().ConfigRevLimitSwitchNormallyOpen(true);
		
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new C_ClimberUp());
    }
}

