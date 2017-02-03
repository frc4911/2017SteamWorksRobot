package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int TPortArmLiftMotor = 14;
	public CANTalon armLiftMotor = new CANTalon(TPortArmLiftMotor);
	
	public AnalogPotentiometer armPotentiometer = new AnalogPotentiometer(1);
	
	public final double retracted = 0.48;
	public final double extended = 0.523;
	final double error = 0.00215;
	
	//5% error = 0.00215
	public void moveArm(boolean direction) {
		double pos = armPotentiometer.get();
		double targetPos;
		if(direction)
			targetPos = extended;
		else
			targetPos = retracted;
		
		while(Math.abs(pos - targetPos) > error) {
			pos = armPotentiometer.get();
			SmartDashboard.putNumber("Arm Pos", Robot.ss_Arm.armPotentiometer.get());
	    	SmartDashboard.putNumber("Arm motor power", Robot.ss_Arm.armLiftMotor.get());
			if(direction)
				armLiftMotor.set(0.5);
			else
				armLiftMotor.set(-0.5);
		}
		
		armLiftMotor.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

