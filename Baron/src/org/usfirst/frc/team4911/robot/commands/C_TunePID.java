package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TunePID extends Command {
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
	
    public C_TunePID(Subsystem subsystem, DefaultMotor motor, int ticksPerRev, int encoderTicksPerRev) {
        // Use requires() here to declare subsystem dependencies
        //requires(subsystem);
        this.motor = motor;
        this.ticksPerRev = ticksPerRev;
        this.encoderTicksPerRev = encoderTicksPerRev;
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
    
    private void updatePIDInfo() {
        this.ticks = (int) SmartDashboard.getNumber("2PID Pos", 0);
    	this.kp = SmartDashboard.getNumber("2kp", 0);
    	this.kd = SmartDashboard.getNumber("2kd", 0);
    	this.ki = SmartDashboard.getNumber("2ki", 0);
    	this.kf = SmartDashboard.getNumber("2kf", 0);
    	this.rampRate = SmartDashboard.getNumber("2rampRate", 0);
    	this.iZone = (int) SmartDashboard.getNumber("2iZone", 0);
    	this.peakOutputVoltage = SmartDashboard.getNumber("2peakOutVolt", 0);
    	this.nominalOutputVoltage = SmartDashboard.getNumber("2nominalOutVolt", 0);
    	this.PIDType = stringToPIDType(SmartDashboard.getString("2PID Type", "Disabled"));
    }
    
    private void updateSmartDashboard() {
    	SmartDashboard.putNumber("2PID Pos", 0); 
		SmartDashboard.putNumber("2kp", 0);
		SmartDashboard.putNumber("2kd", 0);
		SmartDashboard.putNumber("2ki", 0); 
		SmartDashboard.putNumber("2kf", 0); 
		SmartDashboard.putNumber("2rampRate", 0);
		SmartDashboard.putNumber("2iZone", 0);
		SmartDashboard.putNumber("2peakOutVolt", 0); 
		SmartDashboard.putNumber("2nominalOutVolt", 0);
		SmartDashboard.putString("2PID Type", "");
		SmartDashboard.putBoolean("2End PID",  false);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//updateSmartDashboard();
    	updatePIDInfo();
    	
		SmartDashboard.putBoolean("2End PID",  false);
    	
    	motor.zeroEnc();
    	motor.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType);
    	counter = -1;
    	SmartDashboard.putNumber("2alive", counter++);
    }

    double counter = 0;
    private boolean end = false;
    protected void execute() {
    	switch(PIDType) {
	    	case Position: 
	    		SmartDashboard.putNumber("2Curr PID Pos(pos)", motor.getEncPos()); 
	    		break;
	    	case Speed: 
		    	SmartDashboard.putNumber("2Curr PID Pos(spd)", motor.getEncVelocity()); 
		    	break;
	    	case Current: 
	    		SmartDashboard.putNumber("2Curr PID Pos(cur)", motor.getOutputCurrent(false)); 
	    		break;
    		default:
    			break;
    	}
    	
    	SmartDashboard.putNumber("2alive", counter++);
    	end = SmartDashboard.getBoolean("2End PID",  true);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return end;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("2alive", 11111.0);

    	motor.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
