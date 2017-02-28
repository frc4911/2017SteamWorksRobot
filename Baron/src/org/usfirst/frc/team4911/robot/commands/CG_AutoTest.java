package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class CG_AutoTest extends CommandGroup {
	private final double TIMEOUT = 2;
	private final int TARGET = 5000;
	
    public CG_AutoTest() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
		
    	// drive train
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, true, TARGET, TIMEOUT));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, false, -TARGET, TIMEOUT));
    	
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, true, -TARGET, TIMEOUT));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, false, TARGET,TIMEOUT));
    	
    	// fuel collector
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, true, TIMEOUT));
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, false, TIMEOUT));
    	
    	// fuel hoppper
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, true, TIMEOUT));
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, false, TIMEOUT));

    	// shooter feeder
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.feederMotor, true, target, timeOut));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.feederMotor, false, target, timeOut));

    	
    	// shooter flywheel
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, true, target, timeOut));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, false, target, timeOut));

    	// climber
//    	addSequential(new C_TestMotorByTime(Robot.ss_Climber, Robot.ss_Climber.climberMotors, true, timeOut));
    	
    	// gear collector
    	// TODO: in
    	// TODO: out
    	
    	// gear arm
    	// TODO: up
    	// TODO: down
    }
}
