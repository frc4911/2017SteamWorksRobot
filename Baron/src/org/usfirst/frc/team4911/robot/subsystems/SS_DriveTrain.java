package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
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
	
	public DefaultMotor driveTrainLeft = new DefaultMotor(tPortDriveTrainFrontLeft, Robot.ss_Config.driveMotorConstFL, Robot.ss_Config.driveEncoderConstL,
			tPortDriveTrainRearLeft, kpLeft, kdLeft, kiLeft, rampRateLeft, iZoneLeft, peakOutputVoltageLeft, nominalOutputVoltageLeft, PIDTypeLeft);
	
	public DefaultMotor driveTrainRight = new DefaultMotor(tPortDriveTrainFrontRight, Robot.ss_Config.driveMotorConstFL, Robot.ss_Config.driveEncoderConstL, 
			tPortDriveTrainRearRight, kpRight, kdRight, kiRight, rampRateRight, iZoneRight, peakOutputVoltageRight, nominalOutputVoltageRight, PIDTypeRight);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_DriveByJoystick());
    }
    
    public void updateLog() {
    	if (Robot.ss_Logging != null){
// driveTrainLeft
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX3, "" + this.driveTrainLeft.talon.get());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX4, "" + this.driveTrainLeft.talon.getOutputVoltage());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX5, "" + this.driveTrainLeft.talon.getOutputCurrent());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX6, "" + this.driveTrainLeft.talon.getSpeed());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX7, "" + this.driveTrainLeft.talon.getEncVelocity());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX8, "" + this.driveTrainLeft.talon.getEncPosition());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX9, "" + this.driveTrainLeft.talon.get());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX10, "" + this.driveTrainLeft.talon.get());
// driveTrainRight
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX11, "" + this.driveTrainRight.talon.get());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX12, "" + this.driveTrainRight.talon.getOutputVoltage());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX13, "" + this.driveTrainRight.talon.getOutputCurrent());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX14, "" + this.driveTrainRight.talon.getSpeed());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX15, "" + this.driveTrainRight.talon.getEncVelocity());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX16, "" + this.driveTrainRight.talon.getEncPosition());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX17, "" + this.driveTrainRight.talon.get());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX18, "" + this.driveTrainRight.talon.get());
    	}
    }
}

