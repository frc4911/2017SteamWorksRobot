package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DashboardDoubleValue;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_RunPID extends Command {

	DefaultMotor motor;
//	DashboardDoubleValue ddv_ticks = null;
//	DashboardDoubleValue ddv_ticksPerRev = null;
//	DashboardDoubleValue ddv_encoderTicksPerRev = null;
//	DashboardDoubleValue ddv_kp = null;
//	DashboardDoubleValue ddv_kd = null;
//	DashboardDoubleValue ddv_ki = null;
//	DashboardDoubleValue ddv_kf = null;
//	DashboardDoubleValue ddv_rampRate = null;
//	DashboardDoubleValue ddv_iZone = null;
//	DashboardDoubleValue ddv_peakOutputVoltage = null;	
//	DashboardDoubleValue ddv_nominalOutputVoltage = null;
	int ticks = 0;
	double kp = 0;
	double kd = 0;
	double ki = 0;
	double kf = 0;
	double rampRate = 0;
	int iZone = 0;
	double peakOutputVoltage = 0;
	double nominalOutputVoltage = 0;
	int ticksPerRev = 0;
	int encoderTicksPerRev = 0;
	boolean encoderFlip = false;
	boolean flipMotorDir = false;
	CANTalon.TalonControlMode PIDType = CANTalon.TalonControlMode.Position;
	int minDistance;
	
    public C_RunPID(
    		Subsystem subsystem, 
    		DefaultMotor motor, 
    		int ticksPerRev, 
    		int encoderTicksPerRev, 
    		CANTalon.TalonControlMode PIDType, 
    		boolean encoderFlip, 
    		boolean flipMotorDir,
    		int ticks,
    		double kp,
    		double kd,
    		double ki,
    		double kf,
    		double rampRate,
    		int iZone,
    		double peakOutputVoltage,
    		double nominalOutputVoltage
    		) {
        requires(subsystem);
        this.motor = motor;
        this.ticksPerRev = ticksPerRev;
        this.encoderTicksPerRev = encoderTicksPerRev;
        this.PIDType = PIDType;
        this.encoderFlip = encoderFlip;
        this.flipMotorDir = flipMotorDir;
        this.ticks = ticks;
        this.kp = kp;
        this.kd = kd;
        this.ki = ki;
        this.kf = kf;
        this.rampRate = rampRate;
        this.iZone = iZone;
        this.peakOutputVoltage = peakOutputVoltage;
        this.nominalOutputVoltage = nominalOutputVoltage;
        minDistance = Math.abs(ticks/2);
    }
    
    protected void initialize() {
        Robot.activeDrivetrainPIDs++;
    	Robot.pidTargetReached = false;
    	motor.zeroEnc();
    	motor.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType, encoderFlip, flipMotorDir);
    	SmartDashboard.putNumber("activeDrivetrainPIDs", Robot.activeDrivetrainPIDs);
    }

    protected void execute() {
    	SmartDashboard.putNumber("activeDrivetrainPIDs", Robot.activeDrivetrainPIDs);
    }

    final int LIMIT = 10;
    int lastValue = -1;
    int sameCount = LIMIT;
    
    boolean decrementPIDCount = true;
    
    protected boolean isFinished() {

    	int currentValue = (int)motor.getEncPos();
    	
    	if (Math.abs(currentValue)<minDistance)
    		return false; // make sure we get rolling first
    		
    	// kick out once target reached (not used)
//    	if ((ticks > 0) && (currentValue>ticks)){
//    		return true;
//    	}
//    	if ((ticks < 0) && (currentValue<ticks)){
//    		return true;
//    	}
    	
    	//kickout when settling close to target
    	if (Math.abs(currentValue - lastValue)<15){
    		sameCount--;
    	}
    	else{
    		sameCount = LIMIT;
    		lastValue = currentValue;
    	}
    	// kickout when close (not used)
//    	if (Math.abs(ticks - currentValue) < 20)
//    		return true;
    	
    	// kickout if dominate pid reaches end (not used)
//    	if (Robot.stopDrivetrainPIDs == true){
//    		return true;
//    	}
    	
    	if(sameCount <= 0 && decrementPIDCount) {
    		Robot.activeDrivetrainPIDs--;
    		decrementPIDCount = false;
    	}
    	
    	return Robot.activeDrivetrainPIDs <= 0;
    }

    protected void end() {
    	SmartDashboard.putNumber("activeDrivetrainPIDs", Robot.activeDrivetrainPIDs);
    	Robot.pidTargetReached = true;
    	motor.stopPID();
    }

    protected void interrupted() {
    	end();
    }
}