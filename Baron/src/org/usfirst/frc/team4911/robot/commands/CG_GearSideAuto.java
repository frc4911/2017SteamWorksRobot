package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearSideAuto extends CommandGroup {

    public CG_GearSideAuto() {
    	// 58 inches from corner of boiler to bumper
    	// lift gear
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward from wall to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-8625, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-6350, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	
    	// drive forward to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1660, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-1660, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
    	
    	addSequential(new C_GearLiftUp(true));// stop holding gear up
    	
    	// deploy gear
    	addParallel(new CG_GearPlaceOnSpring());
    	
//    	// push gear on
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-625, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
//        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//        		-625, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

    	// backup to clear spike
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

    }
}
