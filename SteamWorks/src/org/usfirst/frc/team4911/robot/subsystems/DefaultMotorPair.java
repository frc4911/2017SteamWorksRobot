package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefaultMotorPair implements Motor {
	CANTalon pTalon;
	public int TPortPTalon;
	public CANTalon fTalon;
	CANTalon encoder;
	double upLimit;
	double lowLimit;
	
	public DefaultMotorPair(CANTalon pTalon, int TPortPTalon, CANTalon fTalon) {
		this.pTalon = pTalon;
		this.TPortPTalon = TPortPTalon;
		this.fTalon = fTalon;
		this.encoder = this.pTalon;
		
		fTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		fTalon.set(TPortPTalon);
	}
	
	public DefaultMotorPair(CANTalon pTalon, int TPortPTalon, CANTalon fTalon, CANTalon encoder) {
		this.pTalon = pTalon;
		this.TPortPTalon = TPortPTalon;
		this.fTalon = fTalon;
		this.encoder = encoder;
		
		fTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		fTalon.set(TPortPTalon);
	}
	
	public DefaultMotorPair(CANTalon pTalon, int TPortPTalon, CANTalon fTalon, double upLimit, double lowLimit) {
		this.pTalon = pTalon;
		this.TPortPTalon = TPortPTalon;
		this.fTalon = fTalon;
		this.encoder = this.pTalon;
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		
		fTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		fTalon.set(TPortPTalon);
	}
	
	public DefaultMotorPair(CANTalon pTalon, int TPortPTalon, CANTalon fTalon, CANTalon encoder, double upLimit, double lowLimit) {
		this.pTalon = pTalon;
		this.TPortPTalon = TPortPTalon;
		this.fTalon = fTalon;
		this.encoder = encoder;
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		
		fTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		fTalon.set(TPortPTalon);
	}
	
	
	
	public void spin(double pow) {
		pTalon.set(pow);
	}
	
	public void spin(double pow, double upLimit, double lowLimit) {
		if(encoder.getEncPosition() <= upLimit && encoder.getEncPosition() >= lowLimit) {
			pTalon.set(pow);
		} else {
			setBrakeMode(true);
			stop();
		}
	}
	
	public void moveByInput(double input) {
		spin(input);
	}

	public void moveByInput(double input, double upLimit, double lowLimit) {
		spin(input, upLimit, lowLimit);
	}
	
	public void moveToEncPos(double pos, double pow) {
		pow = Math.abs(pow);
		zeroEnc();
		
		if((Math.signum(pos) < 0 && encoder.getEncPosition() < pos) || 
		   (Math.signum(pos) > 0 && encoder.getEncPosition() > pos))
			stop();
		else
			spin(Math.signum(pos) * pow);
	}
	
	public void moveToEncPos(double pos, double pow, double upLimit, double lowLimit) {
		pow = Math.abs(pow);
		zeroEnc();
		
		if((Math.signum(pos) < 0 && encoder.getEncPosition() < pos) || 
		   (Math.signum(pos) > 0 && encoder.getEncPosition() > pos))
			stop();
		else
			spin(Math.signum(pos) * pow, upLimit, lowLimit);
	}

	public void stop() {
		spin(0);
	}
	
	public void setBrakeMode(boolean set) {
		pTalon.enableBrakeMode(set);
	}
	
	public void zeroEnc() {
		encoder.setEncPosition(0);
	}
}
