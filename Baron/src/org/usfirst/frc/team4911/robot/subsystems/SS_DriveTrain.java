package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

	int tPortDriveTrainFrontRight = 0;
	int tPortDriveTrainRearRight = 1;
	
	int tPortDriveTrainFrontLeft = 2;
	int tPortDriveTrainRearLeft = 3;
	
	public DefaultMotor driveTrainLeft = new DefaultMotor(tPortDriveTrainFrontLeft, tPortDriveTrainRearLeft, Robot.ss_Config.driveMotorConstL, "DriveTrain Left");
	public DefaultMotor driveTrainRight = new DefaultMotor(tPortDriveTrainFrontRight, tPortDriveTrainRearRight, Robot.ss_Config.driveMotorConstR, "DriveTrain Right");
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
}

