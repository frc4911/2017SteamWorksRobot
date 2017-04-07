package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Auto_5_LeftGear extends CommandGroup {

    public CG_Auto_5_LeftGear() {
    	addSequential(new C_ZeroPIDCount());

    	// 69 inches from wall to bumper
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
    	
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward and turn to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(103 + 5 - 36), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(103 + 5 - 36) - dtl.degreesToTicks(60), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // degrees lowered by 5
    	addSequential(new C_DriveTrainPIDTracker()); // 111.66
    	
    	// drive forward to airship and place gear
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(24.5), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(24.5), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());//addParallel
    	
    	// back up from airship and turn to boiler
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		dtl.inchesToTicks(13), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(8.0), 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			dtr.inchesToTicks(13) + dtl.degreesToTicks(18), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(8.0), 0)); // degrees decreased by 7
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	
//    	// 72 inches from wall to bumper
//
//    	// lift gear and hold it up
//    	addParallel(new C_GearLiftUp(false));
//    	
//    	// drive forward from wall and turn to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//    			-5530, 2.0, 200, 0, 0, 0, 0, 8.4, 0)); // shortened 3 inches
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-8460, 2.0, 200, 0, 0, 0, 0, 8.0, 0)); // shortened 1 inch
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
//        		1660, 2.0, 200, 0, 0, 0, 0, 5.3, 0)); // increaed distance by 160
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		1660, 2.0, 200, 0, 0, 0, 0, 5.0, 0)); // increaed distance by 160
//    	addSequential(new C_DriveTrainPIDTracker());
    }
}
