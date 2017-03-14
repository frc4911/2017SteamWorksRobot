package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoTwistAndShoot extends CommandGroup {

    public CG_AutoTwistAndShoot() {
        // drive across base line with a kick left at end
        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-5880, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		-5150, 2.0, 0, 0, 0, 0, 0, 5.0, 0));
        
        // spin up flywheel
        addParallel(new C_SpinFlywheel());
        
        // drive to boiler with kick left at end to align with boiler face
        addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		4450, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        addSequential(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		5720, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        
        // start feeder and hopper simultaneously
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(true));
    }
}
