package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_ClimberUp;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortClimber = 11;
	public DefaultMotor climberMotor = new DefaultMotor(tPortClimber, Robot.ss_Config.climberConst, "Climber");
	
	public SS_Climber() {
		climberMotor.setPowLimit(0.7);
		climberMotor.enablePowLimit(true);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new C_ClimberUp());
    }
}

