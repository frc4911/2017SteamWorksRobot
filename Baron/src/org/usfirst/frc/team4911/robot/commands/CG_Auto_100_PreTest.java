package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainLeft;
import org.usfirst.frc.team4911.robot.subsystems.SS_DriveTrainRight;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CG_Auto_100_PreTest extends CommandGroup {

    public CG_Auto_100_PreTest() {
    	final SS_DriveTrainLeft dtl = Robot.ss_DriveTrainLeft;
    	final SS_DriveTrainRight dtr = Robot.ss_DriveTrainRight;
   	
    	final double inches = 200;
    	
    	final double speed = 8.0;
    	

    	
    	// drive forward
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-dtl.inchesToTicks(inches), 8, 1000, 0, 0, 0.0, 0, speed, 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-dtr.inchesToTicks(inches), 8, 1000, 0, 0, 0.0, 0, speed, 0)); // right drive train 0.415 higher than left **for Baron**
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward 100 and turn 90 degrees left
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-dtl.inchesToTicks(inches + 52), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(speed), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-dtr.inchesToTicks(inches), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(speed), 0)); // right drive train 0.415 higher than left **for Baron**
//    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward 100 and turn 90 degrees right
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-dtl.inchesToTicks(inches), 8, 1000, 0, 0, 0.0, 0, dtl.peakSpeedAdjust(speed), 0)); // add 280 to both sides if numbers are 8500 or 17000 // 180 special number for left side
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-dtr.inchesToTicks(inches + 51), 8, 1000, 0, 0, 0.0, 0, dtr.peakSpeedAdjust(speed), 0)); // right drive train 0.415 higher than left **for Baron**
//    	addSequential(new C_DriveTrainPIDTracker());

    }
}
