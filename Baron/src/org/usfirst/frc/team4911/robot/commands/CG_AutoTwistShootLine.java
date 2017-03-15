package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoTwistShootLine extends CommandGroup {

    public CG_AutoTwistShootLine() {
    	
    	// 41 inches from corner of boiler to corner of bumpers
        // spin up flywheel
        addParallel(new C_SpinFlywheel());
        
        // twist to boiler
        addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		-600, 2.0, 0, 0, 0, 0, 0, 3.0, 0));

        addSequential(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		8525, 2.0, 0, 0, 0, 0, 0, 6.0, 0));
        
        // start feeder and hopper simultaneously
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(true));

    }
}
