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

	int tPortDriveTrainFrontRight = 1; //0;
	int tPortDriveTrainRearRight = 0; //1;
	
	int tPortDriveTrainFrontLeft = 3; //2;
	int tPortDriveTrainRearLeft = 2; //3;
	
	public DefaultMotor leftMotors = new DefaultMotor(tPortDriveTrainFrontLeft, tPortDriveTrainRearLeft, Robot.ss_Config.driveMotorsLeftConst, "DriveTrainLeft");
	public DefaultMotor rightMotors = new DefaultMotor(tPortDriveTrainFrontRight, tPortDriveTrainRearRight, Robot.ss_Config.driveMotorsRightConst, "DriveTrainRight");
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
}

