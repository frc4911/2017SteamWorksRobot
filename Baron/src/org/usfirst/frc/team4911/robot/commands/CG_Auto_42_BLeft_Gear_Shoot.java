package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_42_BLeft_Gear_Shoot extends CommandGroup {

    public CG_Auto_42_BLeft_Gear_Shoot() {
    	// 37.5 inches from wall to bumper (cornered with the boiler)
    	
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
    	
    	addSequential(new CG_Auto_5_LeftGear());
    	
    	// spin up flywheel
        addParallel(new C_SpinFlywheel());
    	
    	// finish driving to boiler and kick
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		dtl.inchesToTicks(96.5) + dtr.degreesToTicks(11), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			dtr.inchesToTicks(96.5), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
        
        // shoot - start feeder and hopper simultaneously
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(true));
    	
//    	// 72 inches from wall to bumper
//
//    	// lift gear and hold it up
//    	addParallel(new C_GearLiftUp(false));
//    	
//    	// drive forward from wall and turn to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//    			-5770, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-8540, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//    	
//    	// drive forward to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-1800, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-1800, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//    	
//    	// stop holding gear up
//    	addSequential(new C_GearLiftUp(true));
//    	
//    	// deploy gear
//    	addSequential(new CG_GearPlaceOnSpring());
//
//    	// backup to clear spike
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		1500, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		2400, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//
//    	// spin up flywheel
//    	addParallel(new C_SpinFlywheel());
//
//    	addSequential(new C_ZeroEncoders());
//      	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//      			7260, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
//      	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//  				7070, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//  	
//	  	addParallel(new C_ShooterFeeder());
//	  	addParallel(new C_HopperSpin(true));
    }
}
