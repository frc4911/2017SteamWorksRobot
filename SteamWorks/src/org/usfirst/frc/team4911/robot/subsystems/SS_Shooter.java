package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_MoveShooterByJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	final int TPortShooterLiftMotor = 2;
	public CANTalon shooterLiftMotor = new CANTalon(TPortShooterLiftMotor);
	
	public Solenoid shooterPlunger = new Solenoid(0, 0);
	final double downPos = -4000;
	final double upPos = 4700;
	double shooterPos;
	double targetPos;
	
	//1% off = 40 ticks
	final double error = 40;
	//shooterPos = shooterLiftMotor.getEncPosition();
	public void moveShooter(boolean direction) {
		zeroEncoder();
		if(direction)
			targetPos = downPos;
		else
			targetPos = upPos;
		
		while(Math.abs(targetPos - shooterPos) > error) {
	    	SmartDashboard.putNumber("Shooter Pos", Robot.ss_Shooter.shooterLiftMotor.getEncPosition());
	    	SmartDashboard.putNumber("Shooter motor power", Robot.ss_Shooter.shooterLiftMotor.getBusVoltage());
			if(direction)
				shooterLiftMotor.set(0.5);
			else {
				shooterLiftMotor.set(-0.5);
			}
			
			shooterPos = shooterLiftMotor.getEncPosition();
		}
		
		shooterLiftMotor.set(0);
	}
	
	public void zeroEncoder() {
		shooterLiftMotor.setEncPosition(0);
	}
	
	public void moveShooterByJoystick(double input) {
		shooterLiftMotor.set(input);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_MoveShooterByJoystick());
    }
}

