package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_AutoTest extends Command {

	int state = 0;
	double startTime = 0;
	double endTime = 0;
    boolean firstTime = true;
	
    public C_AutoTest() {
    }

    protected void initialize() {
    	state = 0;
    	startTime = Timer.getFPGATimestamp();
        firstTime = true;

//		SmartDashboard.putString("stopped Motor","false");
    }

    boolean bbb = false;
    protected void execute() {
    }

    protected boolean isFinished() {
        return stateRun();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
    
    C_RunPID left = null;
    C_RunPID right = null;
    
    boolean stateRun(){
    	boolean allDone = false;
    	
    	switch (state){
    	case 0:
    		if (firstTime){
    			endTime = Timer.getFPGATimestamp()+4;
    			firstTime = false;
    			left = new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    	        		-7000, 2.0, 0, 0, 0, 0, 0, 6.0, 0);
    			left.start();
    			
    			right = new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    	        		-7000, 2.0, 0, 0, 0, 0, 0, 6.0, 0);
    			right.start();
    		}
    		
    		if ((Robot.ss_DriveTrainLeft.leftMotors.getOutputCurrent(false)>20) || (Robot.ss_DriveTrainRight.rightMotors.getOutputCurrent(false)>20)){
    			left.cancel();
    			right.cancel();
//    			SmartDashboard.putString("stopped Motor","true");
    		}
    		if (Timer.getFPGATimestamp()>endTime){
    			left.cancel();
    			right.cancel();
    			state++;    			
    		}
    		break;
    	case 1:
    		state++;
    		break;
    		default:
    			allDone = true;
    	}
    	return allDone;
    }
}
