package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DefaultMotor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_GearLiftLower extends Command {

	double speed = .4;
	DefaultMotor motor = null;
	boolean pidRunning = false;

	public C_GearLiftLower() {
        requires(Robot.ss_GearLift);
        motor = Robot.ss_GearLift.gearLiftMotor;
    }

    protected void initialize() {
    	pidRunning = false;
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	if (Robot.oi.opGamepad.getRawButton(4)){
    		if (pidRunning){
        		pidRunning = false;
        		motor.stopPID();
    		}
    		motor.spin(speed);
    	}
    	else if(Robot.oi.opGamepad.getRawButton(1)){
    		if (pidRunning){
        		pidRunning = false;
        		motor.stopPID();
    		}
    		motor.spin(-speed);    		
    	}
    	else if(!pidRunning){
    		int gearPos = (int)motor.getEncPos();
//    		SmartDashboard.putNumber("gearLift PID Target", gearPos);
        	motor.moveToEncPosDontResetEncoder(gearPos, 1024, 256, 4.0, 0, 0, 0, 0, 0, 12.0, 0, CANTalon.TalonControlMode.Position, false, false);
    		pidRunning = true;
    	}
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
		if (pidRunning){
    		pidRunning = false;
    		motor.stopPID();
		}

		motor.stop();
    }

    protected void interrupted() {
    	end();
    }
}
