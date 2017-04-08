package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_6_CenterGear extends CommandGroup {

    public CG_Auto_6_CenterGear() {
    	addSequential(new C_ZeroPIDCount());
    	
    	// 138 inches from the wall
    	
    	// lift gear
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
    	
    	// drive forward to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(114 - 1.5 - 36), 8, 1000, 0, 0, 0.0, 0, 6.0, 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(114 - 1.5 - 36), 8, 1000, 0, 0, 0.0, 0, 6.0, 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());//addParallel
    	
    	// drive away from airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		dtl.inchesToTicks(36), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(6.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			dtr.inchesToTicks(36), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(6.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	
//    	// lift gear
//    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
//    	
//    	// drive forward from wall to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-5830, 2.0, 200, 0, 0, 0, 0, 6.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-5830, 2.0, 200, 0, 0, 0, 0, 6.0, 0));
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
//        		1500, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		1500, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
      }
}
