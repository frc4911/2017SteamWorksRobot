package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int tPortDriveTrainFrontLeft = 9;
	int tPortDriveTrainRearLeft = 7;
	double upLimitLeft;
	double lowLimitLeft;
	double kpLeft = 0.0;
	double kdLeft = 0.0;
	double kiLeft = 0.0;
	double kfLeft = 0.0;
	double rampRateLeft = 0.0;
	int iZoneLeft = 0; 
	double peakOutputVoltageLeft = 0.0; 
	double nominalOutputVoltageLeft = 0.0; 
	CANTalon.TalonControlMode PIDTypeLeft = CANTalon.TalonControlMode.Position;
	
	int tPortDriveTrainFrontRight = 10;
	int tPortDriveTrainRearRight = 12;
	double upLimitRight;
	double lowLimitRight;
	double kpRight = 0.0;
	double kdRight = 0.0;
	double kiRight = 0.0;
	double kfRight = 0.0;
	double rampRateRight = 0.0;
	int iZoneRight = 0; 
	double peakOutputVoltageRight = 0.0; 
	double nominalOutputVoltageRight = 0.0; 
	CANTalon.TalonControlMode PIDTypeRight = CANTalon.TalonControlMode.Position;
	
	public DefaultMotor driveTrainLeft = new DefaultMotor(tPortDriveTrainFrontLeft, tPortDriveTrainRearLeft, kpLeft, kdLeft, kiLeft, rampRateLeft, 
														  iZoneLeft, peakOutputVoltageLeft, nominalOutputVoltageLeft, PIDTypeLeft);
	
	public DefaultMotor driveTrainRight = new DefaultMotor(tPortDriveTrainFrontRight, tPortDriveTrainRearRight, kpRight, kdRight, kiRight, rampRateRight, 
														   iZoneRight, peakOutputVoltageRight, nominalOutputVoltageRight, PIDTypeRight);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
}

