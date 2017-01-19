package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_LiftShooterJoy;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Shooter3 extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	final int TPortShooterLiftMotor = 1;
	final int TPortShooterRollerLeftMotor = 2;
	final int TPortShooterRollerRightMotor = 3;
	
	public CANTalon shooterLiftTalon = new CANTalon(TPortShooterLiftMotor);
	public CANTalon shooterRollerLeftTalon = new CANTalon(TPortShooterRollerLeftMotor);
	public CANTalon shooterRollerRightTalon = new CANTalon(TPortShooterRollerRightMotor);

	CANTalon.TalonControlMode mode;
	boolean firstTime = true;
	public SS_Shooter3(){
// follow mode
//		shooterRollerLeftTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
//		shooterRollerLeftTalon.set(shooterLiftTalon.getDeviceID());
//
//		shooterRollerRightTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
//		shooterRollerRightTalon.set(shooterLiftTalon.getDeviceID());
		
		shooterLiftTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterLiftTalon.reverseSensor(true);
		shooterLiftTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		//setVoltageRampRate(-1000);
		shooterLiftTalon.setPosition(0);
		setSoftLimits(true, 4300, 1000);
	}
	
    public void initDefaultCommand() {
        //setDefaultCommand(new C_LiftShooterJoy());
        
    }
    
    public void zeroEncoder() {
		shooterLiftTalon.setPosition(0);        
    }
    
    double voltageRate = 0;
    
    public void setVoltageRampRate(double rate){
    	
    	if(rate != -1000){
    		mode = CANTalon.TalonControlMode.Voltage;
	    	shooterLiftTalon.setVoltageCompensationRampRate(24);
    	} else {
    		mode = CANTalon.TalonControlMode.PercentVbus;
    	}
    	shooterLiftTalon.changeControlMode(mode);
    	//shooterLiftTalon.setVoltageRampRate(rate);
    	voltageRate = rate;
    }
    
//    public void enterMotionProfilingMode(){
//    	CANTalon.MotionProfileStatus mps = new CANTalon.MotionProfileStatus(); 
//    	shooterLiftTalon.getMotionProfileStatus(mps);
//    	
//    	CANTalon.TrajectoryPoint point = mps.activePoint;
//    	if (point != null){
//        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_isLastPoint", point.isLastPoint);
//        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_velocityOnly", point.velocityOnly);
//        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_zeroPos", point.zeroPos);
//        	SmartDashboard.putString("MotionProfileStatus_activePoint_position", ""+point.position);
//        	SmartDashboard.putString("MotionProfileStatus_activePoint_profileSlotSelect", ""+point.profileSlotSelect);
//        	SmartDashboard.putString("MotionProfileStatus_activePoint_timeDurMs", ""+point.timeDurMs);
//        	SmartDashboard.putString("MotionProfileStatus_activePoint_velocity", ""+point.velocity);    		
//    	}
//    	SmartDashboard.putBoolean("MotionProfileStatus_activePointValid", mps.activePointValid);
//    	SmartDashboard.putString("MotionProfileStatus_btmBufferCnt", ""+mps.btmBufferCnt);
//    	SmartDashboard.putBoolean("MotionProfileStatus_hasUnderrun", mps.hasUnderrun);
//    	SmartDashboard.putBoolean("MotionProfileStatus_isUnderrun", mps.isUnderrun);
//    	
//    	CANTalon.SetValueMotionProfile svmp = mps.outputEnable;
////    	SmartDashboard.putString("MotionProfileStatus_outputEnable", svmp.toString());
//    	SmartDashboard.putString("MotionProfileStatus_topBufferCnt", ""+mps.topBufferCnt);
//    	SmartDashboard.putString("MotionProfileStatus_topBufferRem", ""+mps.topBufferRem);
//    }
//    
//    public void exitMotionProfilingMode(){
//    	
//    }
    CANTalonEncoderControl pid=null;
    public void initializeClosedLoop(){
//    	pid = new CANTalonEncoderControl();
//    	pid.setupPIDMode(shooterLiftTalon,0.0,3.0,0.0,0.0,0.0,0,8.0,0.0);
    	pid.startPIDMode();
    }
    
    public void exitClosedLoop(){
   		pid.stopPIDMode();
    	//shooterLiftTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void setPositionGoal(int ticks){
    	pid.setPIDEncoderGoal(ticks);
    	//shooterLiftTalon.set(pos/4096.0);
    }

    public void setSoftLimits(boolean onOff, double forwardLimit, double reverseLimit){

    	// setForwardSoftLimit expects rotations not ticks so need to /4096 to convert
    	shooterLiftTalon.setForwardSoftLimit(forwardLimit/4096.0);
    	shooterLiftTalon.enableForwardSoftLimit(onOff);
    	
    	shooterLiftTalon.setReverseSoftLimit(reverseLimit/4096.0);
    	shooterLiftTalon.enableReverseSoftLimit(onOff);    		
    }

    public void setBrakeMode(boolean onOff){
    	shooterLiftTalon.enableBrakeMode(onOff);
    }
    
    public void liftShooter(double speed){
    	
    	if (firstTime){
    		//Robot.ss_DriveTrain.setFollowMode(shooterLiftTalon.getDeviceID());
    		firstTime = false;
    	}
    	
    	if ((speed > -.1) && (speed < .1))
    		speed = 0;
    	
//    	if (mode == CANTalon.TalonControlMode.Voltage){
//    		speed *= voltageRate;
//    	}
    	shooterLiftTalon.set(speed);
    }
    
    boolean liftMotorHomed = false;
    int counter=0;
    double maxCurrent = 0;
    public boolean zeroLiftMotorEncoder(){
    	double curCurrent = shooterLiftTalon.getOutputCurrent();
    	
    	if (curCurrent > maxCurrent)
    		maxCurrent = curCurrent;
    	
    	if (!liftMotorHomed){
    		shooterLiftTalon.set(-0.5);
    		
    		if (curCurrent > 30){
    			liftMotorHomed = true;
    			shooterLiftTalon.set(0);
    			shooterLiftTalon.setPosition(0);
    		}
    	}
    	
    	SmartDashboard.putNumber("lift motor home alive", counter++);
    	SmartDashboard.putBoolean("lift motor home state", liftMotorHomed);
    	SmartDashboard.putNumber("lift motor home max current", maxCurrent);
    	
    	return liftMotorHomed;
    }
    
    public void updateLog(){
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX3,""+shooterLiftTalon.getBusVoltage());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX4,""+shooterLiftTalon.getClosedLoopError());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX5,""+shooterLiftTalon.getCloseLoopRampRate());
    	
    	int pos = shooterLiftTalon.getEncPosition();
    	SmartDashboard.putNumber("encoder", pos);

    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX6,""+pos);
    	
    	SmartDashboard.putNumber("voltage rate", voltageRate);
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX7,""+shooterLiftTalon.getEncVelocity());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX8,""+shooterLiftTalon.getDeviceID());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX9,""+shooterLiftTalon.getError());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX10,""+shooterLiftTalon.getFaultForLim());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX11,""+shooterLiftTalon.getFaultForSoftLim());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX12,""+shooterLiftTalon.getFaultHardwareFailure());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX13,""+shooterLiftTalon.getFaultOverTemp());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX14,""+shooterLiftTalon.getFaultRevLim());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX15,""+shooterLiftTalon.getFaultUnderVoltage());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX16,""+shooterLiftTalon.GetFirmwareVersion());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX17,""+shooterLiftTalon.getOutputCurrent());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX18,""+shooterLiftTalon.getOutputVoltage());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX19,""+shooterLiftTalon.getPosition());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX20,""+shooterLiftTalon.getSetpoint());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX21,""+shooterLiftTalon.getSpeed());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX22,""+shooterLiftTalon.getTemperature());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX23,""+shooterLiftTalon.getControlMode());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX24,""+shooterLiftTalon.isAlive());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX25,""+shooterLiftTalon.isControlEnabled());
    	Robot.ss_Logging2.logKeyOutput(Robot.ss_Logging2.KEYINDEX26,""+shooterLiftTalon.isEnabled());
    }
}

