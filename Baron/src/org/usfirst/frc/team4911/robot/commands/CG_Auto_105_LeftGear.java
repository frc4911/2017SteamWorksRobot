package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_105_LeftGear extends CommandGroup {

    public CG_Auto_105_LeftGear() {
    	// 72 inches from wall to bumper

    	// lift gear and hold it up
    	addParallel(new C_GearLiftUp(false));
    	
    	// drive forward from wall and turn to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-5530, 2.0, 200, 0, 0, 0, 0, 8.4, 0)); // shortened 3 inches
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-8460, 2.0, 200, 0, 0, 0, 0, 8.0, 0)); // shortened 1 inch
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1800, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-1800, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());

    	// backup to clear spike
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1500, 2.0, 200, 0, 0, 0, 0, 5.3, 0)); // increaed distance by 160
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		2400, 2.0, 200, 0, 0, 0, 0, 5.0, 0)); // increaed distance by 160
    	addSequential(new C_DriveTrainPIDTracker());
    }
}
