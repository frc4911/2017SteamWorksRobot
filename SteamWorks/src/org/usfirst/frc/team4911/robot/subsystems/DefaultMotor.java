package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DefaultMotor {
	CANTalon talon;
	boolean limited;
	double upLimit;
	double lowLimit;
	
	public DefaultMotor(CANTalon talon) {
		this.talon = talon;
		limited = false;
	}
	
	public DefaultMotor(CANTalon talon, double upLimit, double lowLimit) {
		this.talon = talon;
		this.upLimit = upLimit;
		this.lowLimit = lowLimit;
		limited = true;
	}
	
	
	
	public void spin(double pow) {
		talon.set(pow);
	}
	
	public void spin(double pow, double upLimit, double lowLimit) {
		if(talon.getEncPosition() <= upLimit && talon.getEncPosition() >= lowLimit) {
			talon.set(pow);
		} else {
			setBrakeMode(true);
			stop();
		}
	}
	
	public void moveToEncPos(double pos, double pow, double upLimit, double lowLimit) {
		pow = Math.abs(pow);
		
		if((Math.signum(pos) < 0 && getEncPos() < pos) || 
		   (Math.signum(pos) > 0 && getEncPos() > pos))
			stop();
		else
			if(limited)
				spin(Math.signum(pos) * pow, upLimit, lowLimit);
			else
				spin(Math.signum(pos) * pow);
	}

	public void stop() {
		spin(0);
	}
	
	public void setBrakeMode(boolean set) {
		talon.enableBrakeMode(set);
	}
	public double getEncPos() {
		return talon.getEncPosition();
	}
	
	public void zeroEnc() {
		talon.setEncPosition(0);
	}
}

