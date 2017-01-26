package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_MotorPID2 extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	int CANID;
	CANTalon talon;
	CANTalonPID pid = null;
	
	public SS_MotorPID2(int CANID, int follower1_CANID){

		CANTalon t2 = new CANTalon(follower1_CANID); 
		t2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	t2.set(CANID);
    	
    	construct(CANID);
	}

	public SS_MotorPID2(int CANID){
    	construct(CANID);
	}

	void construct(int CANID){
		this.CANID = CANID;
		talon = new CANTalon(CANID);
	}
	
	String params = "encoderType, ticksPerRev, codesPerRev, reverse, kp, kd, ki, kf, ramp, iZone, peak, nominal, mode, ticks, counter";
	int PIDCount = 0;
	
	public void createPID(int choice){
 
		SmartDashboard.putString("PID Params:",params);
		boolean create = false;
		stopPIDMode();
		
		CANTalon.FeedbackDevice encoderType = null; 
		int ticksPerRev = 0;
		int encoderCodesPerRev = 0; 
		boolean reverseSensor= false;
		double kp = 0;
		double kd = 0;
		double ki = 0;
		double kf = 0; 
		double rampRate =0; 
		int iZone = 0;
		double peakOutputVoltage = 0;
		double nominalOutputVoltage =0;
		CANTalon.TalonControlMode PIDType = null;
		int ticks = 0;
		
		String key = "PID"+CANID+":";
		String value = "unsupported choice "+choice;
		
		switch(choice){
		case 0:
			create = true;
			
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false;
			kp = .05;
			kd = 0;
			ki = 0;
			kf = 0; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 4;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Position;
			ticks = 15000;
			break;
		case 1:
			create = true;
			
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false;
			kp = 0;
			kd = 0;
			ki = 0;
			kf = 1.0; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 12;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Speed;
			ticks = 5000;
			break;
		case 2:
			create = true;
			
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false;
			kp = 0;
			kd = 0;
			ki = 0;
			kf = 1.0; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 12;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Speed;
			ticks = 5000;
			break;
		case 3:
			create = true;
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false;
			kp = 0;
			kd = 0;
			ki = 0;
			kf = 0; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 0;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Speed;
			ticks = 0;

	    	String start = "desired "+CANID;
	    	String params = " kp, kd, ki, kf, ramp, izone, peak, nominal, ticks";
	    	
	    	String pidValues = SmartDashboard.getString( start+params,"");

    		String[] split = pidValues.split(",");
			if (split.length == 9){
				kp = Double.parseDouble(split[0]);
				kd = Double.parseDouble(split[1]);
				ki = Double.parseDouble(split[2]);
				kf = Double.parseDouble(split[3]); 
				rampRate = Double.parseDouble(split[4]); 
				iZone = (int)Double.parseDouble(split[5]);
				peakOutputVoltage = Double.parseDouble(split[6]);
				nominalOutputVoltage = Double.parseDouble(split[7]);
				ticks = (int)Double.parseDouble(split[8]);
			}
			else
			{
		    	SmartDashboard.putString( start+params,"0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0");
			}
			break;
		}
		
		if (create){
			pid = new CANTalonPID(
					talon,
					encoderType,
					ticksPerRev,
					encoderCodesPerRev,
					reverseSensor,
					kp,
					kd,
					ki,
					kf,
					rampRate,
					iZone,
					peakOutputVoltage,
					nominalOutputVoltage,
					PIDType,
					ticks);
			value = ""+ typeToString(encoderType)+", "+ticksPerRev+", "+encoderCodesPerRev+", "+reverseSensor+", "+kp+", "+kd+", "+ki+", "+kf+", "+rampRate+", "+iZone+", "+peakOutputVoltage+", "+nominalOutputVoltage+", "+modeToString(PIDType)+", "+ticks+", "+PIDCount++;
		}

		SmartDashboard.putString(key, value);
	}

	String typeToString(CANTalon.FeedbackDevice type){
		if (type == CANTalon.FeedbackDevice.QuadEncoder)
			return "Quad";
		
		return "undefined";
	}
	
	String modeToString(CANTalon.TalonControlMode mode){
		if (mode == CANTalon.TalonControlMode.Position)
			return "Position";
		else if (mode == CANTalon.TalonControlMode.Speed)
			return "Speed";
		
		return "undefined";
	}
	
	
	public void stopPIDMode(){
    	if (pid == null) return;
    	pid.stopPIDMode();
    	pid = null;
    }

    public void driveByJoystick(double yInput) {
    	if (pid != null){
    		stopPIDMode();
    	}
		talon.set(yInput);
    }
    
    int counter=0;
    public void updateLog(){
    	
    	SmartDashboard.putNumber("alive"+CANID,counter++);

    	int currEncPos = talon.getEncPosition();    	
    	SmartDashboard.putNumber("encoder pos"+CANID, currEncPos);
    	SmartDashboard.putNumber("encoder speed (RPM)"+CANID,talon.getSpeed());
    	SmartDashboard.putNumber("encoder vel"+CANID,talon.getEncVelocity());
    	SmartDashboard.putNumber("encoder closed loop err"+CANID,talon.getClosedLoopError());
    	
    	Robot.ss_Logging2.logKeyOutput(5, ""+currEncPos);
    	Robot.ss_Logging2.logKeyOutput(6, ""+talon.getSpeed());
    	Robot.ss_Logging2.logKeyOutput(7, ""+talon.getEncVelocity());
    	Robot.ss_Logging2.logKeyOutput(8, ""+talon.getClosedLoopError());
    	Robot.ss_Logging2.logKeyOutput(9, ""+talon.getBusVoltage());
    }

}

