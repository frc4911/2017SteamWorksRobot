package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_40_BLeft_Line_Shoot extends CommandGroup {

    public CG_Auto_40_BLeft_Line_Shoot() {
    	addSequential(new C_ZeroPIDCount());

    	// 37.5 inches from the wall (corner to the boiler)
    	
        // drive across base line with a kick left at end
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
    	
    	// drive forward to baseline
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(127 - 36) + dtl.degreesToTicks(17), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(127 - 36), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// spin up flywheel
        addParallel(new C_SpinFlywheel());
    	
    	// drive back and kick to boiler
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		dtl.inchesToTicks(67) + dtr.degreesToTicks(30), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			dtr.inchesToTicks(67), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// shoot - start feeder and hopper simultaneously
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(true));
    	
    	
//        // drive across base line with a kick left at end
//    	addSequential(new C_ZeroEncoders());
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-5150, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
//        addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		-6100, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//        
//        // spin up flywheel
//        addParallel(new C_SpinFlywheel());
//        
//        // drive to boiler with kick left at end to align with boiler face
//        addSequential(new C_ZeroEncoders());
//        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		5650, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
//        addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		4210, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());
//        
//        // start feeder and hopper simultaneously
//    	addParallel(new C_ShooterFeeder());
//    	addParallel(new C_HopperSpin(true));
    }
}
