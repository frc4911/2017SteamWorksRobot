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
	
	int tPort = 11;
	public DefaultMotor climberMotor = new DefaultMotor(tPort, Robot.ss_Config.climberConst, "Climber");
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new C_ClimberUp());
    }
}

