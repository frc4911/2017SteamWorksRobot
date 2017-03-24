package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_33_BRight_Hopper_Shoot extends CommandGroup {

    public CG_Auto_33_BRight_Hopper_Shoot() {
    	// hopper faces out, corner near boiler, right side aimed at hopper corner

    	// lift gear
    	//addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward from wall to hopper
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		9200, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			4700, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1400, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			1400, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	

    	addSequential(new C_Delay(2.5));
    	
    	// twist away
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-700, 2.0, 200, 0, 0, 0, 0, 3.0, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-4000, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	addParallel(new C_FuelCollect(true));

    	// spin up flywheel
    	addParallel(new C_SpinFlywheel());

    	// drive to boiler
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		4900, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			6600, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	addSequential(new C_ZeroEncoders());

    	addParallel(new C_ShooterFeeder());
	  	addParallel(new C_HopperSpin(true));
    }
}
