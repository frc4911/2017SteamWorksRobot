package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrain3.PeriodicRunnable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MotionProfiler {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon talon;
	public double [][]points;
	double kf, kp, kd, ki;
	String encoderName;
	double periodicUpdate;

	
	public MotionProfiler(CANTalon talon, double [][]newPoints, double kf, double kp, double kd, double ki, String encoderName){
		this.talon = talon;
		this.kf = kf;
		this.kp = kp;
		this.kd = kd;
		this.ki = ki;
		this.encoderName = encoderName;
		
		points = new double[newPoints.length][3];
		
		for (int i=0; i<points.length; i++){
			points[i][0] = newPoints[i][0];
			points[i][1] = newPoints[i][1];
			points[i][2] = newPoints[i][2];
		}
		
		periodicUpdate = newPoints[0][2];
		
	}

	public void enterMPMode() {
		_notifier.startPeriodic(periodicUpdate/2000.0); // convert point duration to milliseconds and make 1/2 
		talon.setPosition(0);
		talon.clearMotionProfileTrajectories();
		talon.clearMotionProfileHasUnderrun();
		talon.changeControlMode(CANTalon.TalonControlMode.MotionProfile);
		talon.setF(kf);// see calculation in section 6 of ctre motion profile doc
		talon.setP(kp);
		talon.setD(kd);
		talon.setI(ki);
		
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		int totalPoints = points.length;
		
		for (int i = 0; i < totalPoints; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = points[i][0];
			point.velocity = points[i][1];
			point.timeDurMs = (int) points[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* brian set true to not do any position
										 * servo, just velocity feedforward
										 */
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */
		
			point.isLastPoint = false;
			if ((i + 1) == totalPoints)
				point.isLastPoint = true; /* set this to true on the last point  */
		
			talon.pushMotionProfileTrajectory(point);
		}
		
		talon.changeMotionControlFramePeriod((int)(periodicUpdate/2.0));
		talon.processMotionProfileBuffer();
		talon.processMotionProfileBuffer();
		talon.set(CANTalon.SetValueMotionProfile.Enable.value);
	}

	public void exitMPMode() {
		_notifier.stop();
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		talon.clearMotionProfileTrajectories();
	}

	void displayMotionProfileStatus(CANTalon.MotionProfileStatus mps){
		
		CANTalon.TrajectoryPoint point = mps.activePoint;
		if (point != null){
			SmartDashboard.putBoolean("MotionProfileStatus_activePoint_isLastPoint", point.isLastPoint);
			SmartDashboard.putBoolean("MotionProfileStatus_activePoint_velocityOnly", point.velocityOnly);
			SmartDashboard.putBoolean("MotionProfileStatus_activePoint_zeroPos", point.zeroPos);
			SmartDashboard.putString("MotionProfileStatus_activePoint_position", ""+point.position);
			SmartDashboard.putString("MotionProfileStatus_activePoint_profileSlotSelect", ""+point.profileSlotSelect);
			SmartDashboard.putString("MotionProfileStatus_activePoint_timeDurMs", ""+point.timeDurMs);
			SmartDashboard.putString("MotionProfileStatus_activePoint_velocity", ""+point.velocity);    		
		}
		SmartDashboard.putBoolean("MotionProfileStatus_activePointValid", mps.activePointValid);
		SmartDashboard.putString("MotionProfileStatus_btmBufferCnt", ""+mps.btmBufferCnt);
		SmartDashboard.putBoolean("MotionProfileStatus_hasUnderrun", mps.hasUnderrun);
		SmartDashboard.putBoolean("MotionProfileStatus_isUnderrun", mps.isUnderrun);
		
		CANTalon.SetValueMotionProfile svmp = mps.outputEnable;
		SmartDashboard.putString("MotionProfileStatus_outputEnable", ""+svmp.value);
		SmartDashboard.putString("MotionProfileStatus_topBufferCnt", ""+mps.topBufferCnt);
		SmartDashboard.putString("MotionProfileStatus_topBufferRem", ""+mps.topBufferRem);
	}



	CANTalon.MotionProfileStatus mps = new CANTalon.MotionProfileStatus();
	
	void driveByMP() {
		talon.getMotionProfileStatus(mps);
		displayMotionProfileStatus(mps);
	}

	class PeriodicRunnable implements java.lang.Runnable {
		public void run() {  
			talon.processMotionProfileBuffer();
			SmartDashboard.putNumber(encoderName, talon.getEncPosition());
		}
	}
	Notifier _notifier = new Notifier(new PeriodicRunnable());
}

