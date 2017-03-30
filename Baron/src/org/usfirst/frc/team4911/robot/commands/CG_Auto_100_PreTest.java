package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_100_PreTest extends CommandGroup {

    public CG_Auto_100_PreTest() {

    	// drive forward from wall and turn to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-8524, 3.0, 200, 0, 0, 0, 0, 8.0, 0)); // 8480
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-8500, 3.0, 200, 0, 0, 0, 0, 8.0, 0)); // 6110
    	addSequential(new C_DriveTrainPIDTracker());

    }
}
