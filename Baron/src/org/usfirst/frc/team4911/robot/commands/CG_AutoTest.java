package org.usfirst.frc.team4911.robot.commands;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_AutoTest extends CommandGroup {
    public CG_AutoTest() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
//    	addSequential(new C_TestDriveTrain(true, Robot.ss_DriveTrain.DriveMotorFrontLeft, "front left", Robot.ss_DriveTrain.DriveMotorFrontLeft));
//    	addSequential(new C_TestDriveTrain(true, Robot.ss_DriveTrain.DriveMotorRearLeft, "rear left", Robot.ss_DriveTrain.DriveMotorFrontLeft));
//    	addSequential(new C_TestDriveTrain(true, Robot.ss_DriveTrain.DriveMotorFrontRight, "front right", Robot.ss_DriveTrain.DriveMotorFrontRight));
//    	addSequential(new C_TestDriveTrain(true, Robot.ss_DriveTrain.DriveMotorRearRight, "rear right", Robot.ss_DriveTrain.DriveMotorFrontRight));
//    	
//    	addSequential(new C_TestShooter(false));
//    	if(Objects.equals(SmartDashboard.getString("dist reached down shooter", ""), )) {
//    		
//    	}
    	
//    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorFrontLeft, Robot.ss_DriveTrain.DriveMotorFrontLeft, 
//    			  Robot.ss_Config.driveMotorConstFL, Robot.ss_Config.driveEncoderConstL, true, 10500, 2, 11.5, "drive train front left", true));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorFrontRight, Robot.ss_DriveTrain.DriveMotorFrontRight, 
//				  true, 10500, 2, 11.5, "drive train front right", true));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorRearLeft, Robot.ss_DriveTrain.DriveMotorFrontLeft, 
//				  true, 10500, 2, 11.5, "drive train rear left", true));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorRearRight, Robot.ss_DriveTrain.DriveMotorFrontRight, 
//				  true, 10500, 2, 11.5, "drive train rear right", true));
//    	
//    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorFrontLeft, Robot.ss_DriveTrain.DriveMotorFrontLeft, 
//				  false, -10500, 2, 11.5, "drive train front left", true));
//		addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorFrontRight, Robot.ss_DriveTrain.DriveMotorFrontRight, 
//				  false, -10500, 2, 11.5, "drive train front right", true));
//		addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorRearLeft, Robot.ss_DriveTrain.DriveMotorFrontLeft, 
//				  false, -10500, 2, 11.5, "drive train rear left", true));
//		addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.DriveMotorRearRight, Robot.ss_DriveTrain.DriveMotorFrontRight, 
//				  false, -10500, 2, 11.5, "drive train rear right", true));
//    	
//    	addSequential(new C_TestMotorPoten(Robot.ss_Arm, Robot.ss_Arm.armLiftMotor, Robot.ss_Arm.armPotentiometer, false, Robot.ss_Arm.extended, 2.0, 11.3,
//			   	  "arm", (Robot.ss_Arm.retracted - Robot.ss_Arm.extended)));
//	  	addSequential(new C_TestMotorPoten(Robot.ss_Arm, Robot.ss_Arm.armLiftMotor, Robot.ss_Arm.armPotentiometer, true, Robot.ss_Arm.retracted, 2.0, 11.0,
//	  			  "arm", (Robot.ss_Arm.retracted - Robot.ss_Arm.extended)));
//    	
//    	addSequential(new C_TestMotorEncoder(Robot.ss_Shooter, Robot.ss_Shooter.shooterLiftMotor, Robot.ss_Shooter.shooterLiftMotor, true, Robot.ss_Shooter.upPos, 3.0, 11.6,
//    			  "shooter", false));
//    	addSequential(new C_TestMotorEncoder(Robot.ss_Shooter, Robot.ss_Shooter.shooterLiftMotor, Robot.ss_Shooter.shooterLiftMotor, false, Robot.ss_Shooter.downPos, 3.0, 11.6,
//			      "shooter", false));
    	
//    	addSequential(new C_TestSolenoid(Robot.ss_Shooter, Robot.ss_Shooter.shooterPlunger, "plunger", true));
//    	addSequential(new C_Delay(2));
//    	addSequential(new C_TestSolenoid(Robot.ss_Shooter, Robot.ss_Shooter.shooterPlunger, "plunger", false));
    	
    }
}
