package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.*;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OI {
	public Joystick stickL = new Joystick(0);
	public Joystick stickR = new Joystick(1);
	public Joystick opGamepad = new Joystick(2);
	//public Joystick kStick = new Joystick(4);
	
	JoystickButton dtLeftTrigger = new JoystickButton(stickL, 1);
	JoystickButton dtLeft2 = new JoystickButton(stickL, 2);
	JoystickButton dtLeft3 = new JoystickButton(stickL, 3);
	JoystickButton dtLeft4 = new JoystickButton(stickL, 4);
	JoystickButton dtRightTrigger = new JoystickButton(stickR, 1);
	JoystickButton dtRight2 = new JoystickButton(stickR, 2);
	JoystickButton dtRight3 = new JoystickButton(stickR, 3);
	JoystickButton dtRight13 = new JoystickButton(stickR, 13);
	JoystickButton dtRight14 = new JoystickButton(stickR, 14);
	
	JoystickButton btnLeftStickPress = new JoystickButton(opGamepad, 9);
	JoystickButton btnA = new JoystickButton(opGamepad, 1);
	JoystickButton btnB = new JoystickButton(opGamepad, 2);
	JoystickButton btnX = new JoystickButton(opGamepad, 3);
	JoystickButton btnY = new JoystickButton(opGamepad, 4);
	JoystickButton btnStart = new JoystickButton(opGamepad, 8);
	JoystickButton btnSelect = new JoystickButton(opGamepad, 7);
	JoystickButton leftBumper = new JoystickButton(opGamepad, 5);
	JoystickButton rightBumper = new JoystickButton(opGamepad, 6);
	
	//JoystickButton k1 = new JoystickButton(kStick, 1);
	
	/**********Testing**********/
	boolean createTestController = false;
	public Joystick autoTestGamepad;

	JoystickButton testBtnA;
	JoystickButton testBtnB;
	JoystickButton testBtnX;
	JoystickButton testBtnY;
	public JoystickButton testBtnStart;
	public JoystickButton testBtnSelect;
	JoystickButton testLeftBumper;
	JoystickButton testRightBumper;
	JoystickButton testRightAxisPress;
	
	/**********Commands*********/
	public Command feeder;
	public Command flywheel;
	public Command stopFlywheel;
	
//	public Command testDriveJoystick;
//	public Command testDriveSet;
	public Command testCmd;
	public Command autoTest;
	
	public OI() {
		/*******DriveJoysticks******/
		//btn2 = Reverse Drive
		
		dtLeft2.whenPressed(new C_ZeroEncoders());
		dtRightTrigger.whileHeld(new C_FuelCollect(true));
		dtRight2.whileHeld(new C_FuelCollect(false));
		dtRight13.whenPressed(new C_GearLowerLimitOverride());
		dtRight14.whileHeld(new C_GearPickup());
		
		/*********OpGamepad*********/
		btnLeftStickPress.whileHeld(new C_GearPickup());
		btnX.whileHeld(new C_GearInOut(true)); // suck gear in
//		Command gColl = new C_GearIntake();
//		btnX.whenPressed(gColl);
//		btnX.whenReleased(new C_StopCommand(gColl));
		
		btnB.whileHeld(new C_GearInOut(false)); // spit gear out
		//btnSelect.whileHeld(new C_HopperSpin(false));
//		btnY.whileHeld(new C_GearLiftLower(true));
//		Command gMoveUp = new C_GearLiftLower(true);
//		btnY.whenPressed(gMoveUp);
//		btnY.whenReleased(new C_StopCommand(gMoveUp));
		
//		btnA.whileHeld(new C_GearLiftLower(false));
		//Command gMoveDown = new C_GearLiftLower(false);
		//btnA.whenPressed(gMoveDown);
		//btnA.whenReleased(new C_StopCommand(gMoveDown));
		
		Command feedFuel = new CG_FeedFuel(true);
		Command hopperReverse = new CG_FeedFuel(false);
		
		rightBumper.whileHeld(feedFuel);
		feeder = new C_TriggerWhileHeld(hopperReverse, opGamepad, false);
		
		flywheel = new C_SpinFlywheel();
		leftBumper.whenPressed(flywheel);
		stopFlywheel = new C_TriggerWhenPressed(new C_StopCommand(flywheel), opGamepad, true);
		
		//Command flywheel = new C_TriggerWhenPressed(shooterPID, opGamepad, true);
		//flywheel.start();
		//leftBumper.whenPressed(new C_StopCommand(shooterPID));
		
		//Command shooterPID = new C_MotorPID();
		
		//rightBumper.whileHeld(new C_GearOnPeg());
		
		/********TestingStick*******/
		
		if(createTestController) {
			testController();
			
			// change the talon num for individual motor group test with joystick axis
			testLeftBumper.whenPressed(new C_TestSetTalonNum(false)); //select next motor in list
			testRightBumper.whenPressed(new C_TestSetTalonNum(true)); //select previous motor in list
			testBtnA.whileHeld(new C_TestDriveOneMotorGroup());       //while held move lower left stick to drive motor
					
			// change the motor speed
//			testBtnY.whenPressed(new C_TestSetMotorSpeed(true)); // bump the speed up
//			testBtnA.whenPressed(new C_TestSetMotorSpeed(false)); // bump the speed down

		
			// while holding the right trigger, the left joystick can be used to move the motor
			Command testDriveJoystick = new C_TriggerWhileHeld(new C_TestDriveByJoystick(), autoTestGamepad, false);
			
			// Auto Test
			autoTest = new CG_AutoTest();
			
			// select and start buttons are reserved for auto test
			testBtnSelect.whenPressed(new C_RunAutoTest());
			testBtnSelect.whenReleased(new C_StopCommand(autoTest));
			
			// auto gear placement test
			Command placeGear = new CG_GearPlaceOnSpring();
			testBtnB.whenPressed(placeGear);
			testBtnB.whenReleased(new C_StopCommand(placeGear));
			
			testBtnX.whileHeld(new CG_CompleteShoot());
			
//			testCmd = new C_TunePID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainRight.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true);
//			kp = ?
//			ticks = 10000
//			testCmd = new C_TunePID(Robot.ss_DriveTrain, Robot.ss_DriveTrain.rightMotors, 1024, 256, CANTalon.TalonControlMode.Speed, false, true);
//			kf = 1
//			ticks = 300
//			testCmd = new C_TunePID(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, 1, 1, CANTalon.TalonControlMode.Position, false, false);
			//kp 1.5 to 3.0
					
			// test complete autonomous, release button to stop
		
			// test a single PID whileHeld
//			testBtnX.whileHeld(new C_TunePID(Robot.ss_DriveTrain, Robot.ss_DriveTrain.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true));
//			testBtnX.whileHeld(new C_TunePID(Robot.ss_DriveTrainLeft, Robot.ss_DriveTrainLeft.leftMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, false));
		}

		Command shooterPID = new C_TunePID(Robot.ss_FuelShooter, Robot.ss_FuelShooter.shooterMotors, 1440, 360, CANTalon.TalonControlMode.Speed, false, true);
		//dtLeft3.whenPressed(shooterPID);
		//dtLeft4.whenPressed(new C_StopCommand(shooterPID));
//		testBtnX.whileHeld(shooterPID);
//		testBtnB.whenPressed(new C_StopCommand(shooterPID));
//		testBtnB.whenPressed(new C_ZeroEncoders());
		
		// my own joystick
//		k1.whileHeld(new C_DriveByMotionProfile(Robot.ss_DriveTrainRight,Robot.ss_DriveTrainRight.rightMotors));
	}
	
	public void testController() {
		autoTestGamepad = new Joystick(3);
		
		 testBtnA = new JoystickButton(autoTestGamepad, 1);
		 testBtnB = new JoystickButton(autoTestGamepad, 2);
		 testBtnX = new JoystickButton(autoTestGamepad, 3);
		 testBtnY = new JoystickButton(autoTestGamepad, 4);
		 testBtnStart = new JoystickButton(autoTestGamepad, 8);
		 testBtnSelect = new JoystickButton(autoTestGamepad, 7);
		 testLeftBumper = new JoystickButton(autoTestGamepad, 5);
		 testRightBumper = new JoystickButton(autoTestGamepad, 6);
		 testRightAxisPress = new JoystickButton(autoTestGamepad, 10);
	}
}
