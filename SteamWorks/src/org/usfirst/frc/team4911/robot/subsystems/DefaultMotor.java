package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DefaultMotor implements Motor {
	CANTalon talon;
	CANTalon encoder;
	double upLimit;
	double lowLimit;
	
	public DefaultMotor(CANTalon talon) {
		this.talon = talon;
		this.encoder = this.talon;
	}
	
	public DefaultMotor(CANTalon talon, CANTalon encoder) {
		this.talon = talon;
		this.encoder = encoder;
	}
	
	public DefaultMotor(CANTalon talon, double upLimit, double lowLimit) {
		this.talon = talon;
		this.encoder = this.talon;
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
	}
	
	public DefaultMotor(CANTalon talon, CANTalon encoder, double upLimit, double lowLimit) {
		this.talon = talon;
		this.encoder = encoder;
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
	}
	
	
	
	public void spin(double pow) {
		talon.set(pow);
	}
	
	public void spin(double pow, double upLimit, double lowLimit) {
		if(encoder.getEncPosition() <= upLimit && encoder.getEncPosition() >= lowLimit) {
			talon.set(pow);
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
		talon.enableBrakeMode(set);
	}
	
	public void zeroEnc() {
		encoder.setEncPosition(0);
	}
}

