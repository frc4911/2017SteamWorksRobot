package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoTest extends CommandGroup {
	private final int TARGET = 50000;
	
	private final double TIMEOUT = 2;
	
	private final double POWER = 0.5;
	
	private final double GEAR_LIFT_POWER = 0.3;
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
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, true, TARGET, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, false, -TARGET, TIMEOUT, POWER));
    	
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, true, -TARGET, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, false, TARGET,TIMEOUT, POWER));
    	
    	// fuel collector
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, true, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, false, TIMEOUT, POWER));
    	
    	// fuel hoppper
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, true, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, false, TIMEOUT, POWER));

    	// shooter feeder
    	addSequential(new C_TestMotorByEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.feederMotor, true, TARGET, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.feederMotor, false, TARGET, TIMEOUT, POWER));
    	
    	// shooter flywheel
    	addSequential(new C_TestMotorByEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, true, TARGET, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, false, TARGET, TIMEOUT, POWER));

    	// climber
    	addSequential(new C_TestMotorByTime(Robot.ss_Climber, Robot.ss_Climber.climberMotors, true, TIMEOUT, POWER));
    	
    	// gear collector
    	addSequential(new C_TestMotorByTime(Robot.ss_GearIntake, Robot.ss_GearIntake.gearIntakeMotor, true, TIMEOUT, POWER));
    	addSequential(new C_TestMotorByTime(Robot.ss_GearIntake, Robot.ss_GearIntake.gearIntakeMotor, false, TIMEOUT, POWER));
    	
    	// gear arm
    	addSequential(new C_TestMotorByTime(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, true, TIMEOUT, GEAR_LIFT_POWER));
    	addSequential(new C_TestMotorByTime(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, false, TIMEOUT, GEAR_LIFT_POWER));
    	
    	// camera
    	// TODO: add camera
    	
    	// NAVX
    	// TODO: add NAVX
    	
    	// LIDAR
    	// TODO: add LIDAR
    }
}
