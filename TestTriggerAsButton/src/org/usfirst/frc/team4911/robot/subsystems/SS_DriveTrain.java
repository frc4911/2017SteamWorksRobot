package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int tPort = 7;
	int tPortF = 9;
	
	public DefaultMotor leftDriveTrain = new DefaultMotor(tPort, tPortF, 1, "DriveTrainLeft");

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

