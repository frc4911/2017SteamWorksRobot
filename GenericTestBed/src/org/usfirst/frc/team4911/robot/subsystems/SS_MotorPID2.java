package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
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
	int follower1_CANID = 0;
	CANTalon talon;
	CANTalonPID pid = null;
	CANTalon talonFollower = null;
	double kp_val;
	double kf_val;
	double kd_val;
	int ticks_val;
	
	public SS_MotorPID2(int CANID, int follower1_CANID){

		this.follower1_CANID = follower1_CANID;
		talonFollower = new CANTalon(follower1_CANID); 
		talonFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		talonFollower.set(CANID);
		talonFollower.enableBrakeMode(false);
    	construct(CANID);
	}

	public SS_MotorPID2(int CANID){
    	construct(CANID);
	}

	void construct(int CANID){
		this.CANID = CANID;
		talon = new CANTalon(CANID);
		talon.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 20);
		talon.enableBrakeMode(false);
	}
	
	String params = "encoderType, ticksPerRev, codesPerRev, reverse, kp, kd, ki, kf, ramp, iZone, peak, nominal, mode, ticks, counter";
	int PIDCount = 0;
	boolean firstTime = true;
	
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
			kp = 0.23;
			kd = 0;
			ki = 0;
			kf = 0.051; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 12;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Speed;
			ticks = 3100;	// rpm of encoder
			break;
		case 2:
			create = true;
			
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false;
			kp = 0.35;
			kd = 0;
			ki = 0;
			kf = 0.051; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 12;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Speed;
			ticks = 3150;
			break;
		case 3:
			// best values for Ian's shooter
			// encoderType, ticksPerRev, codesPerRev, reverse, kp, kd, ki, kf, ramp, iZone, peak, nominal, mode, ticks
			// Quad, 2400, 600, false, 0.23, 0.0, 0.0, 0.051, 0.01, 0, 0.0, 0.0, Speed, 3100, 1
			create = true;
			encoderType = CANTalon.FeedbackDevice.QuadEncoder; 
			ticksPerRev = 2400;
			encoderCodesPerRev = 600; 
			reverseSensor= false; //marshall's is flipped, ian's is not flipped
			kp = 0;
			kd = 0;
			ki = 0;
			kf = 0; 
			rampRate = 0; 
			iZone = 0;
			peakOutputVoltage = 0;
			nominalOutputVoltage = 0;
			PIDType = CANTalon.TalonControlMode.Current;
			ticks = 0;

	    	String start = "desired "+CANID;
	    	String params = " ki, ramp, izone, peak, nominal";
	    	
	    	String pidValues = SmartDashboard.getString( start+params,"");
	    	
    		String[] split = pidValues.split(",");
			if (split.length == 5){
				kp = SmartDashboard.getNumber("Kp",0);
				kd = SmartDashboard.getNumber("Kd",0);
				ki = Double.parseDouble(split[0]);
				kf = SmartDashboard.getNumber("Kf",0); 
				rampRate = Double.parseDouble(split[1]); 
				iZone = (int)Double.parseDouble(split[2]);
				peakOutputVoltage = Double.parseDouble(split[3]);
				nominalOutputVoltage = Double.parseDouble(split[4]);
				ticks = (int)SmartDashboard.getNumber("ticks",0);
			}
			else
			{
		    	SmartDashboard.putString( start+params,"0.0, 0.0, 0.0, 0.0, 0.0");
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
			kp_val = kp;
			kd_val = kd;
			kf_val = kf;
			ticks_val = ticks;
			//talon.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 20);
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
		else if (mode == CANTalon.TalonControlMode.Current)
			return "Current";
		
		return "undefined";
	}
	
	public void bumpTicks(int bump){
		if (pid != null){
			pid.setTicks(bump+pid.getTicks());
			SmartDashboard.putNumber("variable ticks",pid.getTicks());
		}
	}
	
	public void stopPIDMode(){
    	if (pid == null) return;
    	pid.stopPIDMode();
    	pid = null;
    	//talon.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 100);
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
    	double current = talon.getOutputCurrent();
    	double voltage = talon.getOutputVoltage();
    	double speedRPM = talon.getSpeed();
    	double error = talon.getClosedLoopError()/4;
    	int tickGoal = 0;
    	if (pid != null){
    		tickGoal = pid.getTicks();
    	}
    	
    	if (Robot.ss_Logging2 != null){
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX3, ""+currEncPos);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX4, ""+current);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX5, ""+voltage);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX6, ""+speedRPM);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX7, ""+error);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX8, ""+tickGoal);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX9, ""+talon.getPosition());
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX10, ""+talon.getEncVelocity());
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX11, ""+kp_val);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX12, ""+kd_val);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX13, ""+kf_val);
    		Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX14, ""+ticks_val);
    	}
    	SmartDashboard.putNumber("current"+CANID, current);
    	SmartDashboard.putNumber("get"+CANID, talon.get());
    	SmartDashboard.putNumber("voltage"+CANID, voltage);
    	SmartDashboard.putNumber("encoder speed (RPM)"+CANID,speedRPM);
    	SmartDashboard.putNumber("encoder vel"+CANID,talon.getEncVelocity());
    	SmartDashboard.putNumber("encoder closed loop err"+CANID,error);// 4 is based on comparing output to .getSpeed - .set
    	SmartDashboard.putNumber("talon err"+CANID,talon.getError());
    	SmartDashboard.putNumber("getSetpoint"+CANID,talon.getSetpoint());
    	if (talonFollower != null){
        	SmartDashboard.putNumber("current"+follower1_CANID, talonFollower.getOutputCurrent());
        	SmartDashboard.putNumber("voltage"+follower1_CANID, talonFollower.getOutputVoltage());
    	}
    }

}

