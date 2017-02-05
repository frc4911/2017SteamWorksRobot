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
	
	int TPortMP = 9;
	CANTalon primeMotor = new CANTalon(TPortMP);
	CANTalon followMotor = new CANTalon(7);
	
	
	public DefaultMotorPair pMotor = new DefaultMotorPair(primeMotor, TPortMP, followMotor);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_TestFSMotor());
    }
}

