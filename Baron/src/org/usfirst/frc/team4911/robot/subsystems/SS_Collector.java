package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Collector extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPort = 20;
	public DefaultMotor collectorMotor = new DefaultMotor(tPort, Robot.ss_Config.collectorConst, "Collector");

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

