package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_132_BRight_Gear_Shoot extends CommandGroup {

    public CG_Auto_132_BRight_Gear_Shoot() {
    	// 72 inches from wall to bumper
    	// lift gear

    	addSequential(new CG_Auto_107_RightGear());

    	// spin up flywheel
    	addParallel(new C_SpinFlywheel());

    	addSequential(new C_ZeroEncoders());
      	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
      			7310, 2.0, 200, 0, 0, 0, 0, 8.4, 0));
      	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
      			7660, 2.0, 200, 0, 0, 0, 0, 8.0, 0)); // bumped distance by 160
    	addSequential(new C_DriveTrainPIDTracker());
  	
	  	addParallel(new C_ShooterFeeder());
	  	addParallel(new C_HopperSpin(true));

    }
}
