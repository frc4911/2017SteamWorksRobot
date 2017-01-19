package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_VerticleMoveArm;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Arm extends Subsystem {
    
	final int TPortArmLiftMotor = 14;
	public CANTalon armLiftMotor = new CANTalon(TPortArmLiftMotor);
	
	public Solenoid armBrakeSolenoid = new Solenoid(1, 6);
	public AnalogPotentiometer armPotentiometer = new AnalogPotentiometer(1);
	
	public PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public double setPos = armPotentiometer.get();
	double pError = 0;
	double iError = 0;
	double dError = 0;
	double prevError = 0;
    
    public void verticleMove(double input) {
    	input *= -1;
    	double position = armPotentiometer.get();
    	double motorPower = 0;
    	double iConst = 0; //0.01;
    	
    	
    	double pConst = 150;
    	pError = setPos - position;
    	
    	double dConst = 0;
    	dError = 0;
    	
    	if(!(input >= -0.1 && input <= 0.1)) {
    		setPos += (input * 0.0005);
    	}
    	
    	//0.480 = retracted; 0.523 = extended
    	if(setPos <= 0.483) {
    		setPos = 0.483;
    	} else if(setPos >= 0.523) {
    		setPos = 0.523;
    	}
    	//0.0012 = 3% of range
		if(Math.abs(position - setPos) <= 0.01) {
			iError = 0;
		}
		
		dError = ((pError - prevError) / 0.02);
		iError += pError;
		motorPower = (pConst * pError) + (iConst * iError) + (dConst * dError);
    	
    	//positive = extend; negative = retract
    	armLiftMotor.set(motorPower);
    	
    	prevError = pError;
    	
    	Robot.logData[0] = pError;
    	Robot.logData[1] = iError;
    	Robot.logData[2] = dError;
    	Robot.logData[3] = setPos;
    	Robot.logData[4] = input;
    	
    	
    	SmartDashboard.putNumber("setPos", setPos);
    	SmartDashboard.putNumber("motor power", motorPower);
    	SmartDashboard.putNumber("pError", pError);
    	SmartDashboard.putNumber("iError", iError);
    	SmartDashboard.putNumber("Arm Input", input);
    	
    	for(int index = 0; index <= 15; index++) {
    		SmartDashboard.putNumber("Talon ID: " + index + " Current Draw", PDP.getCurrent(index));
    		Robot.logData[index + 8] = PDP.getCurrent(index);
    	}
    }

    public void initDefaultCommand() {
        setDefaultCommand(new C_VerticleMoveArm());
    }
}

