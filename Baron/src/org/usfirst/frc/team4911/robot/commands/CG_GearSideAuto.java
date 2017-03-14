package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearSideAuto extends CommandGroup {

    public CG_GearSideAuto() {
    	// lift gear
//    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up
    	
    	// drive forward from wall to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-7000, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			7000, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	
    	// twist toward airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1350, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			-1000, 2.0, 0, 0, 0, 0, 0, 6.0, 0));

    	// drive forward to airship
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1100, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
    			1100, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
    	
//    	addSequential(new C_GearLiftUp(true));// stop holding gear up
    	
    	// push gear on
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-625, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		625, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

    	// deploy gear
//    	addSequential(new CG_GearPlaceOnSpring());
    	
    	// backup to clear spike
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-1500, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

    }
}
