package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveMotor extends Subsystem {
	int talonNum = 5;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon talon = new CANTalon(talonNum);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
        
        talon.enableBrakeMode(false);
        
        talon.setForwardSoftLimit(0);
        talon.setReverseSoftLimit(0);
        talon.enableForwardSoftLimit(false);
        talon.enableReverseSoftLimit(false);
        
        talon.ConfigFwdLimitSwitchNormallyOpen(true);
        talon.ConfigRevLimitSwitchNormallyOpen(true);
    }
    
    public void drive(double speed) {
    		talon.set(speed);
    }
}

