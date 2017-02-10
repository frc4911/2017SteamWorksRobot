package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_MoveMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Motor extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	int tPort = 0;
	public CANTalon talon = new CANTalon(tPort);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new C_MoveMotor());
	}
}
