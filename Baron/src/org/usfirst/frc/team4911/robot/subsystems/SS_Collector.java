package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Collector extends Subsystem {

	int tPort = 6;
	int tfPort = 9;
	public DefaultMotor collectorMotor = new DefaultMotor(tPort, tfPort, Robot.ss_Config.collectorConst, "Collector");

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
}

