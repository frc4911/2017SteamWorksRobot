package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_100_PreTest extends CommandGroup {

    public CG_Auto_100_PreTest() {

    	// drive forward from wall and turn to airship
    	//ramprate
    	// 1.0 no diff
    	// 1.3 didn't move
    	// 1.5 huge diff
    	// 2.0 huge diff
    	// 3.0 huge diff
    	// 4.0 oscillates
    	// 5.0 oscillates
    	// 6.0 oscillates    	
    	// 8.0 oscillates
    	// 8.15 oscillates
    	// 8.2 oscillates
    	// 8.225 no diff
    	// 8.3 no diff
    	// 8.5 no diff
    	// 9.0 no diff
    	// 10.0 no diff
    	
    	// 8.3 12.0
    	
    	// drive straight
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//    			-10000, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
//    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-10000, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));

    	// straight backwards
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-4000, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-4000, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());

    	// 1/4 turn right
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-1950, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			1950, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());

    	// straight forwards
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			4000, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			4000, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());

    	// 1/8 turn left
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-975, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			975, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());

    	// straight forwards
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-6000, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-4000, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());

//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//    			7800, 2.0, 200, 0, 0, 0.0, 0, 8.4, 0));
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-7800, 2.0, 200, 0, 0, 0.0, 0, 8.0, 0));
//    	addSequential(new C_DriveTrainPIDTracker());

}
}
