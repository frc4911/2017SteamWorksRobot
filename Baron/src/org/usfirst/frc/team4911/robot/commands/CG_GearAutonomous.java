package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearAutonomous extends CommandGroup {

    public CG_GearAutonomous() {
    	Command holdGear =  new C_HoldGear();
    	
    	//double targetDist, int ticksPerRev, int encoderTicksPerRev, double kp, double kd, double ki, double kf, int iZone, double peakOutputVoltage
        //addSequential(new C_MoveToPosInInches(71.5, 1024, 256, 0.5, 0.0, 0.0, 0.0, 0, 12.0));
    	// drive forward from wall to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-5850, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
    	addParallel(holdGear);
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-5850, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
    	// deploy gear
//    	addSequential(new CG_PlaceGearAuto());
//    	
//    	// backup to clear spike
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		900, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		900, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
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