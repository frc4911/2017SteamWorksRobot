package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Auto_5_LeftGear extends CommandGroup {

    public CG_Auto_5_LeftGear() {
    	// 58 inches from corner of boiler to bumper

    	// lift gear and hold it up
    	addParallel(new C_GearLiftUp(false));
    	
    	// drive forward from wall and turn to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-6410, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-9185, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	
    	// drive forward to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1360, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-1360, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	
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
