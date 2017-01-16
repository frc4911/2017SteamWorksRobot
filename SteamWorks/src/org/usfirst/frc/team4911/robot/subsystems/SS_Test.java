package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_Test;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Test extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//CANTalon testTalon = new CANTalon(4);
	Solenoid testSolenoid = new Solenoid(1);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new C_Test());
    }
    
    public void spin(double speed) {
    	//testTalon.set(speed);
    }
}

