package org.usfirst.frc.team4911.robot.commands;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_AutoTest extends CommandGroup {
	private final double timeOut = 2;
	
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
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, true, 5000, timeOut));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, false, -5000, timeOut));
    	
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, true, -5000, timeOut));
    	addSequential(new C_TestMotorByEncoder(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, false, 5000,timeOut));
    	
    	// fuel collector
//    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, true, timeOut));
//    	addSequential(new C_TestMotorByTime(Robot.ss_FuelCollector, Robot.ss_FuelCollector.collectorMotors, false, timeOut));
    	
    	// fuel hoppper
//    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, true, timeOut));
//    	addSequential(new C_TestMotorByTime(Robot.ss_FuelHopper, Robot.ss_FuelHopper.hopperMotor, false, timeOut));

    	// shooter feeder
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.feederMotor, true, 10500, 4));
    	
    	// shooter flywheel
//    	addSequential(new C_TestMotorEncoder(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, true, 10500, 4));
    	
    	// climber
//    	addSequential(new C_TestMotorByTime(Robot.ss_Climber, Robot.ss_Climber.climberMotors, true, 2));
    }
}
