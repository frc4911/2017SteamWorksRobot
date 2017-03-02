package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class C_MoveToPosInInches extends Command {
	final double TICKSPERINCH = 81.48733; //Assumes 4in diameter wheel
	
	double targetDist = 0;
	int ticksPerRev = 0;
	int encoderTicksPerRev = 0;
	double kp = 0;
	double kd = 0;
	double ki = 0;
	double kf = 0;
	int iZone = 0;
	double peakOutputVoltage = 0;
	
    public C_MoveToPosInInches(double targetDist, int ticksPerRev, int encoderTicksPerRev, double kp, double kd, double ki,
    		double kf, int iZone, double peakOutputVoltage) {
    	requires(Robot.ss_DriveTrainLeft);
    	requires(Robot.ss_DriveTrainRight);
        this.targetDist = targetDist;
    	this.ticksPerRev = ticksPerRev;
    	this.encoderTicksPerRev = encoderTicksPerRev;
    	this.kp = kp;
    	this.kd = kd;
    	this.ki = ki;
    	this.kf = kf;
    	this.iZone = iZone;
    	this.peakOutputVoltage = peakOutputVoltage;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	int ticks = (int)(targetDist * TICKSPERINCH);
    	
    	Robot.ss_DriveTrainLeft.leftMotors.zeroEnc();
    	Robot.ss_DriveTrainRight.rightMotors.zeroEnc();
    	
    	Robot.ss_DriveTrainLeft.leftMotors.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, 0.0, iZone, peakOutputVoltage, 
    			0.0, CANTalon.TalonControlMode.Position, false, false);
    	Robot.ss_DriveTrainRight.rightMotors.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, 0.0, iZone, peakOutputVoltage, 
    			0.0, CANTalon.TalonControlMode.Position, false, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_DriveTrainLeft.leftMotors.stopPID();
    	Robot.ss_DriveTrainRight.rightMotors.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
