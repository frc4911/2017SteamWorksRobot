package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_34_BRight_Shoot_Hopper extends CommandGroup {

    public CG_Auto_34_BRight_Shoot_Hopper() {
    	// angled toward boiler, collector in front
    	// outer corner of bot (not bumper) 61.5 from side wall
    	// bumper against end wall 77 from side wall
    	
    	// lift gear
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	// spin up flywheel
    	addParallel(new C_SpinFlywheel());
    	
    	// twist forward from wall to hopper
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		4300, 2.0, 200, 0, 0, 0, 0, 7.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			1150, 2.0, 200, 0, 0, 0, 0, 4.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// shot for 5 seconds
    	addParallel(new C_ShooterFeeder());
	  	addParallel(new C_HopperSpin(true));

	  	addSequential(new C_Delay(5.0));
    	
    	// twist away
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-700, 2.0, 200, 0, 0, 0, 0, 3.0, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-3000, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive to hopper
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-4000, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-4000, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	addSequential(new C_ZeroEncoders());

    	// twist to hopper
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1950, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			1950, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	addSequential(new C_ZeroEncoders());

    	// drive to hopper
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		2860, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			2780, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	addSequential(new C_ZeroEncoders());
    }
}
