package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_7_RightGear extends CommandGroup {

    public CG_Auto_7_RightGear() {
    	addSequential(new C_ZeroPIDCount());
    	
    	// 69 inches from wall to bumper
    	// lift gear
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
    	
    	// drive forward and turn to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(111.66 + 5 - 36) - dtl.degreesToTicks(58), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(111.66 + 5 - 36), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(18.5), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(18.5), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());//addParallel
    	
    	// drive away from airship and turn to boiler
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		dtl.inchesToTicks(13) + dtl.degreesToTicks(15), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			dtr.inchesToTicks(13), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	
//    	// 72 inches from wall to bumper
//    	// lift gear
//
//    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
//    	
//    	// drive forward from wall and turn to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-8480, 2.0, 200, 0, 0, 0, 0, 8.4, 0)); // dropped by 320 or 4 inches
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-6110, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//    	
//    	// drive forward to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-1500, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-1500, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//    	
//    	// stop holding gear up
//    	addSequential(new C_GearLiftUp(true));
//    	
//    	// deploy gear
//    	addSequential(new CG_GearPlaceOnSpring());//addParallel
//
//    	// backup to clear spike
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		1660, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		1660, 2.0, 200, 0, 0, 0, 0, 5.0, 0)); 
//    	addSequential(new C_DriveTrainPIDTracker());    	
    }
}