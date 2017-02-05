package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_TestFSMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_TestDefaultMotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DefaultMotor motor = new DefaultMotor(10, 4, 0, 0, 0.5, 0, 3.5, 1.0, CANTalon.TalonControlMode.Position);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_TestFSMotor());
    }
}

