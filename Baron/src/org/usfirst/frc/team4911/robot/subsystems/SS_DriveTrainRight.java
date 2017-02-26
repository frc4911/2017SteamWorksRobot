package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrainRight extends Subsystem {

	int tPortDriveTrainFrontRight = 1; //0;
	int tPortDriveTrainRearRight = 0; //1;
	
	public DefaultMotor rightMotors = new DefaultMotor(tPortDriveTrainFrontRight, tPortDriveTrainRearRight, Robot.ss_Config.driveMotorsRightConst, "DriveTrainRight");
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new C_DriveByJoystick(false));
    }
}

