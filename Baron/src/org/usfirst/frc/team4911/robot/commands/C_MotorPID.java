package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class C_MotorPID extends Command {
	DefaultMotor motor;
	int ticks = 0;
	int ticksPerRev = 0;
	int encoderTicksPerRev = 0;
	double kp = 0;
	double kd = 0;
	double ki = 0;
	double kf = 0;
	double rampRate = 0;
	int iZone = 0;
	double peakOutputVoltage = 0;
	double nominalOutputVoltage = 0;
	CANTalon.TalonControlMode PIDType = CANTalon.TalonControlMode.Disabled;
	
    public C_MotorPID(Subsystem subsystem, DefaultMotor motor, int ticks, int ticksPerRev, int encoderTicksPerRev, double kp, double kd, double ki,
    		double kf, double rampRate, int iZone, double peakOutputVoltage, double nominalOutputVoltage, String PIDType) {
    	requires(subsystem);
        this.motor = motor;
        this.ticks = ticks;
    	this.ticksPerRev = ticksPerRev;
    	this.encoderTicksPerRev = encoderTicksPerRev;
    	this.kp = kp;
    	this.kd = kd;
    	this.ki = ki;
    	this.kf = kf;
    	this.rampRate = rampRate;
    	this.iZone = iZone;
    	this.peakOutputVoltage = peakOutputVoltage;
    	this.nominalOutputVoltage = nominalOutputVoltage;
    	this.PIDType = stringToPIDType(PIDType);
    }
    
    private CANTalon.TalonControlMode stringToPIDType(String PIDType) {
    	switch(PIDType) {
    		case "Position": return CANTalon.TalonControlMode.Position;
    		case "Speed": return CANTalon.TalonControlMode.Speed;
    		case "Current": return CANTalon.TalonControlMode.Current;
    		default: 
    			motor.stopPID();
    			return CANTalon.TalonControlMode.PercentVbus;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motor.zeroEnc();
    	motor.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	motor.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
