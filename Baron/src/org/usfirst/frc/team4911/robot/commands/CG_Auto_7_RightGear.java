package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_7_RightGear extends CommandGroup {

    public CG_Auto_7_RightGear() {
    	// 72 inches from wall to bumper
    	// lift gear

    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward from wall and turn to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-8480, 2.0, 200, 0, 0, 0, 0, 8.4, 0)); // dropped by 320 or 4 inches
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-6110, 2.0, 200, 0, 0, 0, 0, 8.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1500, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-1500, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addSequential(new CG_GearPlaceOnSpring());//addParallel

    	// backup to clear spike
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1660, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		1660, 2.0, 200, 0, 0, 0, 0, 5.0, 0)); 
    	addSequential(new C_DriveTrainPIDTracker());    	
    }
}