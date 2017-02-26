package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.subsystems.DashboardDoubleValue;
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
	DashboardDoubleValue ddv_ticks = null;
	DashboardDoubleValue ddv_ticksPerRev = null;
	DashboardDoubleValue ddv_encoderTicksPerRev = null;
	DashboardDoubleValue ddv_kp = null;
	DashboardDoubleValue ddv_kd = null;
	DashboardDoubleValue ddv_ki = null;
	DashboardDoubleValue ddv_kf = null;
	DashboardDoubleValue ddv_rampRate = null;
	DashboardDoubleValue ddv_iZone = null;
	DashboardDoubleValue ddv_peakOutputVoltage = null;	
	DashboardDoubleValue ddv_nominalOutputVoltage = null;
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
	double startTime=0;
	CANTalon.TalonControlMode PIDType = CANTalon.TalonControlMode.Position;
	
    public C_TunePID(Subsystem subsystem, DefaultMotor motor, int ticksPerRev, int encoderTicksPerRev, CANTalon.TalonControlMode PIDType, boolean encoderFlip, boolean flipMotorDir) {
        // Use requires() here to declare subsystem dependencies
        //requires(subsystem);
        this.motor = motor;
        this.ticksPerRev = ticksPerRev;
        this.encoderTicksPerRev = encoderTicksPerRev;
        this.PIDType = PIDType;
        this.encoderFlip = encoderFlip;
        this.flipMotorDir = flipMotorDir;
    }
    
//    private CANTalon.TalonControlMode stringToPIDType(String PIDType) {
//    	switch(PIDType) {
//    		case "Position": return CANTalon.TalonControlMode.Position;
//    		case "Speed": return CANTalon.TalonControlMode.Speed;
//    		case "Current": return CANTalon.TalonControlMode.Current;
//    		default: 
//    			motor.stopPID();
//    			return CANTalon.TalonControlMode.PercentVbus;
//    	}
//    }
//    
    private String PIDToString(CANTalon.TalonControlMode PIDType) {
    	if (PIDType == CANTalon.TalonControlMode.Position)
			return "Position";
	
    	if (PIDType == CANTalon.TalonControlMode.Speed)
			return "Speed";
	
    	if (PIDType == CANTalon.TalonControlMode.Current)
			return "Current";
	
    	return "unknown";
    }
    
    private void updatePIDInfo() {
    	
    	if (ddv_ticks == null){
	    	ddv_ticks = new DashboardDoubleValue("PID ticks", 0);
	    	ddv_kp = new DashboardDoubleValue("PID kp", 0);
	    	ddv_kd = new DashboardDoubleValue("PID kd", 0);
	    	ddv_ki = new DashboardDoubleValue("PID ki", 0);
	    	ddv_kf = new DashboardDoubleValue("PID kf", 0);
	    	ddv_rampRate = new DashboardDoubleValue("PID rampRate", 0);
	    	ddv_iZone = new DashboardDoubleValue("PID iZone", 0);
	    	ddv_peakOutputVoltage = new DashboardDoubleValue("PID peakVoltage", 12.0);
	    	ddv_nominalOutputVoltage = new DashboardDoubleValue("PID nominalVoltage", 0);
    	}
    	
        ticks = (int) ddv_ticks.getNumber();
    	kp = ddv_kp.getNumber();
    	kd = ddv_kd.getNumber();
    	ki = ddv_ki.getNumber();
    	kf = ddv_kf.getNumber();
    	rampRate = ddv_rampRate.getNumber();
    	iZone = (int) ddv_iZone.getNumber();
    	peakOutputVoltage = ddv_peakOutputVoltage.getNumber();
    	nominalOutputVoltage = ddv_nominalOutputVoltage.getNumber();
    }

    int counter=0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	//updateSmartDashboard();
    	updatePIDInfo();
    	
    	motor.zeroEnc();
    	SmartDashboard.putString("Current PID", PIDToString(PIDType)+" "+ticksPerRev+" "+ encoderFlip + " " +flipMotorDir+" running " + ++counter);
    	startTime = Timer.getFPGATimestamp();
    	motor.moveToEncPos(ticks, ticksPerRev, encoderTicksPerRev, kp, kd, ki, kf, rampRate, iZone, peakOutputVoltage, nominalOutputVoltage, PIDType, encoderFlip, flipMotorDir);
    }

    protected void execute() {
    	switch(PIDType) {
	    	case Position: 
	    		SmartDashboard.putNumber("Tuning Curr PID Pos(pos)", motor.getEncPos()); 
	        	SmartDashboard.putNumber("PID Err",motor.getTalon().getClosedLoopError());
	    		break;
	    	case Speed: 
		    	SmartDashboard.putNumber("PID getEncVelocity",    motor.getTalon().getEncVelocity()); 
		    	SmartDashboard.putNumber("PID getClosedLoopError",motor.getTalon().getClosedLoopError());
		    	SmartDashboard.putNumber("PID getSpeed",          motor.getTalon().getSpeed());//motor.getEncVelocity()); 
		    	SmartDashboard.putNumber("PID getErr",            motor.getTalon().getError());
		    	break;
	    	case Current: 
	    		SmartDashboard.putNumber("Tuning Curr PID Pos(cur)", motor.getOutputCurrent(false)); 
	    		break;
    		default:
    			break;
    	}
    	
    	SmartDashboard.putNumber("PID Err",motor.getTalon().getClosedLoopError()/4);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	double duration = Timer.getFPGATimestamp()-startTime;
    	SmartDashboard.putNumber("calculated enc velocity", motor.getEncPos()/duration);
    	SmartDashboard.putString("Current PID", PIDToString(PIDType)+" "+ticksPerRev+" "+ encoderFlip + " " +flipMotorDir+" stopped " + counter);
    	motor.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
