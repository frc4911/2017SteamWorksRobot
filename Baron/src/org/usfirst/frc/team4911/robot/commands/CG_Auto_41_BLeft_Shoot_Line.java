package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_41_BLeft_Shoot_Line extends CommandGroup {

    public CG_Auto_41_BLeft_Shoot_Line() {
    	
        // spin up flywheel
        addParallel(new C_SpinFlywheel(6700)); // speed up pid due to gap from boiler
        addSequential(new C_Delay(1));
        
        // shoot - start feeder and hopper simultaneously
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(true));
        addSequential(new C_Delay(7));

    	// twist away from boiler
        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-2000, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		1000, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        
    	// drive to line
        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-4400, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		-4400, 2.0, 0, 0, 0, 0, 0, 6.0, 0));

    }
}
