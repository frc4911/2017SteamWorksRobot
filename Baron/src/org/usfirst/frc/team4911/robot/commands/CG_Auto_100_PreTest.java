package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_100_PreTest extends CommandGroup {

    public CG_Auto_100_PreTest() {

    	// 80 inches from wall to bumper
    	addParallel(new C_GearLiftUp(false)); // lift gear and hold it up

    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-9900, 10.0, 1000, 0, 0, 8.3, 0, 8.0, 0)); // dropped by 320 or 4 inches
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-6910, 10.0, 1000, 0, 0, 8.3, 0, 8.3, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// drive forward to airship
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		-800, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
    			-800, 2.0, 200, 0, 0, 0, 0, 5.0, 0));
    	addSequential(new C_DriveTrainPIDTracker());
    	
    	// stop holding gear up
//    	addSequential(new C_GearLiftUp(true));
    	
    	// deploy gear
//    	addSequential(new CG_GearPlaceOnSpring());//addParallel

    	// backup to clear spike
    	addSequential(new C_ZeroEncoders());
    	addParallel(new C_GearLiftUp(true));
    	addParallel(new CG_GearPlaceOnSpring());//addParallel
    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
        		2400, 2.0, 200, 0, 0, 0, 0, 5.3, 0));
    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
        		1500, 2.0, 200, 0, 0, 0, 0, 5.0, 0)); 
    	addSequential(new C_DriveTrainPIDTracker()); 

    	
    	//76.75 ticks/inch
    	// drive forward from wall and turn to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-8425, 10.0, 1000, 0, 0, 8.3, 0, 8.0, 0)); // 8500 is 109 inches
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-8425, 10.0, 1000, 0, 0, 8.3, 0, 8.3, 0)); // higher ramp is faster
//    	addSequential(new C_DriveTrainPIDTracker());

    	//76.75 ticks/inch
    	// drive forward from wall and turn to airship
//    	addSequential(new C_ZeroEncoders());
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false,
//        		-8000, 10.0, 1000, 0, 0, 8.3, 0, 8.0, 0)); // 8500 is 109 inches
//    	addParallel(new C_RunPID(Robot.ss_DriveTrainRight, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true,
//    			-6425, 10.0, 1000, 0, 0, 8.3, 0, 8.3, 0)); // higher ramp is faster
//    	addSequential(new C_DriveTrainPIDTracker());

}
}
