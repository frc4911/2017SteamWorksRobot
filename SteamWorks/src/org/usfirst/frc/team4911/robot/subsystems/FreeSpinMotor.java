package org.usfirst.frc.team4911.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FreeSpinMotor implements Motor {
	public CANTalon talon;
	
	public FreeSpinMotor(CANTalon talon) {
		this.talon = talon;
	}
	
	public void spin(double pow) {
		talon.set(pow);
	}
	
	public void moveByInput(double input) {
		spin(input);
	}
	
	public void moveToEncPos(double pos, double pow) {
		pow = Math.abs(pow);
		talon.setEncPosition(0);
		
		if((Math.signum(pos) < 0 && talon.getEncPosition() < pos) || 
		   (Math.signum(pos) > 0 && talon.getEncPosition() > pos))
			stop();
		else
			spin(Math.signum(pos) * pow);
	}
	
	public void stop() {
		spin(0);
	}
	
	public void setBrakeMode(boolean set) {
		talon.enableBrakeMode(set);
	}

	public void zeroEnc() {
		talon.setEncPosition(0);
	}
}