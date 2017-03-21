package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MoveToEncPos extends Command {
	DefaultMotor motor;
	int ticks;
	int tickPerRev;
	int encoderTicksPerRev;
	double kp;
	double kd;
	double ki;
	double kf;
	double rampRate;
	int iZone;
	double peakOutputVoltage;
	double nominalOutputVoltage;
	CANTalon.TalonControlMode PIDType = CANTalon.TalonControlMode.Position;
	
    public C_MoveToEncPos(Subsystem subsystem, DefaultMotor motor, int ticks, int tickPerRev, int encoderTicksPerRev, double kp, double kd, double ki,
    		double kf, double rampRate, int iZone, double peakOutputVoltage, double nominalOutputVoltage) {
        // Use requires() here to declare subsystem dependencies
        requires(subsystem);
        this.motor = motor;
        this.ticks = ticks;
    	this.tickPerRev = tickPerRev;
    	this.encoderTicksPerRev = encoderTicksPerRev;
    	this.kp = kp;
    	this.kd = kd;
    	this.ki = ki;
    	this.kf = kf;
    	this.rampRate = rampRate;
    	this.iZone = iZone;
    	this.peakOutputVoltage = peakOutputVoltage;
    	this.nominalOutputVoltage = nominalOutputVoltage;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motor.zeroEnc();
    	curTime = Timer.getFPGATimestamp();
    	prevTime = curTime;
//    	motor.moveToEncPos(ticks, tickPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType);
    }
    
    private double prevTime = 0.0;
    private double curTime = 0.0;
    private double time = 0.0;
    private void timeToEnd() {
    	curTime = Timer.getFPGATimestamp();
    	time += (curTime - prevTime);
    	prevTime = curTime;
    }
    
    private final double TOLERANCE = 0.01;
    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	timeToEnd();
    	if((Math.abs(motor.getEncPos() - ticks)) > (ticks * TOLERANCE)) {
    		time = 0.0;
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    private final double DURATION = 2.0;
    protected boolean isFinished() {
//    	SmartDashboard.putNumber("time", time);
    	if(time >= DURATION) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	motor.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
