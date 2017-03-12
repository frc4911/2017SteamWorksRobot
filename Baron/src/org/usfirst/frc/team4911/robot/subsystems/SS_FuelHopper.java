package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelHopper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPortHopper = 7;
	public DefaultMotor hopperMotor = new DefaultMotor(tPortHopper, -1 /*Robot.ss_Config.hopperConst*/, "Hopper");
	
	public SS_FuelHopper() {
		hopperMotor.setPowLimit(0.7);
		hopperMotor.enablePowLimit(true);
		hopperMotor.getTalon().ConfigFwdLimitSwitchNormallyOpen(true);
		hopperMotor.getTalon().ConfigRevLimitSwitchNormallyOpen(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

