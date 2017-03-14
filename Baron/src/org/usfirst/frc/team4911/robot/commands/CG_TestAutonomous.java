package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_TestAutonomous extends CommandGroup {

    public CG_TestAutonomous() {
    	//Command holdGear =  new C_GearLiftUp();
    	// lift gear
    	//addParallel(holdGear);
    	// while driving forward from wall to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-5450, 2.0, 0, 0, 0, 0, 0, 2.9, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			5450, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());
    	
    	// backup to clear spike
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		811, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		-811, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
//    	
//        // turn towards boiler
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-1900, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		-1900, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//
//        //drive until perpendicular to boiler face
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		6500, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		-6500, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        
//        // turn 45 degrees to face boiler
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		1024, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		1024, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//                
//        // drive to boiler 
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		3030, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		-3030, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
//        

        // shoot
    }
}
