package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_7_RightGear extends CommandGroup {

    public CG_Auto_7_RightGear() {
    	// 58 inches from corner of boiler to bumper
    	// lift gear
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward from wall and turn to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-8385, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-6110, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	
    	// drive forward to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1380, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-1380, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	
    	// stop holding gear up
    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
    	addParallel(new CG_GearPlaceOnSpring());

    	// backup to clear spike
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

    }
}
