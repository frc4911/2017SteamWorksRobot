package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.CG_AutoTest;
import org.usfirst.frc.team4911.robot.commands.CG_FeedFuel;
import org.usfirst.frc.team4911.robot.commands.C_CameraUpDown;
import org.usfirst.frc.team4911.robot.commands.C_ShooterFeeder;
import org.usfirst.frc.team4911.robot.commands.C_SpinFlywheel;
import org.usfirst.frc.team4911.robot.commands.C_FuelCollect;
import org.usfirst.frc.team4911.robot.commands.C_GearInOut;
import org.usfirst.frc.team4911.robot.commands.C_ManualTestMotor;
import org.usfirst.frc.team4911.robot.commands.C_MoveToEncPos;
import org.usfirst.frc.team4911.robot.commands.C_RunAutoTest;
import org.usfirst.frc.team4911.robot.commands.C_GearLiftLower;
import org.usfirst.frc.team4911.robot.commands.C_GearSpit;
import org.usfirst.frc.team4911.robot.commands.C_HopperSpin;
import org.usfirst.frc.team4911.robot.commands.C_StopCommand;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveBySet;
import org.usfirst.frc.team4911.robot.commands.C_TestSetMotorSpeed;
import org.usfirst.frc.team4911.robot.commands.C_TestSetTalonNum;
import org.usfirst.frc.team4911.robot.commands.C_TriggerWhenPressed;
import org.usfirst.frc.team4911.robot.commands.C_TriggerWhileHeld;
import org.usfirst.frc.team4911.robot.commands.C_TunePID;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stickL = new Joystick(0);
	public Joystick stickR = new Joystick(1);
	public Joystick opGamepad = new Joystick(2);
	
	JoystickButton dtLeftTrigger = new JoystickButton(stickL, 1);
	JoystickButton dtRightTrigger = new JoystickButton(stickR, 1);
	JoystickButton dtRight2 = new JoystickButton(stickR, 2);
	JoystickButton dtRight3 = new JoystickButton(stickR, 3);
	
	
	
	JoystickButton btnA = new JoystickButton(opGamepad, 1);
	JoystickButton btnB = new JoystickButton(opGamepad, 2);
	JoystickButton btnX = new JoystickButton(opGamepad, 3);
	JoystickButton btnY = new JoystickButton(opGamepad, 4);
	JoystickButton btnStart = new JoystickButton(opGamepad, 8);
	JoystickButton btnSelect = new JoystickButton(opGamepad, 7);
	JoystickButton leftBumper = new JoystickButton(opGamepad, 5);
	JoystickButton rightBumper = new JoystickButton(opGamepad, 6);
	
	/**********Testing**********/
//	public Joystick autoTestStick = new Joystick(3);	

//	JoystickButton testStickBtn1 = new JoystickButton(autoTestStick, 1);
//	JoystickButton testStickBtn2 = new JoystickButton(autoTestStick, 2);
//	JoystickButton testStickBtn3 = new JoystickButton(autoTestStick, 3);
//	JoystickButton testStickBtn4 = new JoystickButton(autoTestStick, 4);
//	JoystickButton testStickBtn5 = new JoystickButton(autoTestStick, 5);
//	JoystickButton testStickBtn6 = new JoystickButton(autoTestStick, 6);
//	JoystickButton testStickBtn7 = new JoystickButton(autoTestStick, 7);
//	JoystickButton testStickBtn11 = new JoystickButton(autoTestStick, 11);
//	JoystickButton testStickBtn12 = new JoystickButton(autoTestStick, 12);
	
	public Joystick autoTestGamepad = new Joystick(3);
	

	JoystickButton testBtnA = new JoystickButton(autoTestGamepad, 1);
	JoystickButton testBtnB = new JoystickButton(autoTestGamepad, 2);
	JoystickButton testBtnX = new JoystickButton(autoTestGamepad, 3);
	JoystickButton testBtnY = new JoystickButton(autoTestGamepad, 4);
	public JoystickButton testBtnStart = new JoystickButton(autoTestGamepad, 8);
	public JoystickButton testBtnSelect = new JoystickButton(autoTestGamepad, 7);
	JoystickButton testLeftBumper = new JoystickButton(autoTestGamepad, 5);
	JoystickButton testRightBumper = new JoystickButton(autoTestGamepad, 6);
	
	/**********Commands*********/
	public Command feeder;
	public Command flywheel;
	public Command stopFlywheel;
	
	public Command testDriveJoystick;
	public Command testDriveSet;
	public Command testCmd;
	public Command autoTest;
	
	public OI() {
		/*******DriveJoysticks******/
		//btn2 = Reverse Drive
		
		dtRightTrigger.whileHeld(new C_FuelCollect(true));
		dtRight3.whileHeld(new C_FuelCollect(false));
		
		/*********OpGamepad*********/
		btnX.whileHeld(new C_GearInOut(false));
//		Command gColl = new C_GearIntake();
//		btnX.whenPressed(gColl);
//		btnX.whenReleased(new C_StopCommand(gColl));
		
		btnB.whileHeld(new C_GearInOut(true));
		//btnSelect.whileHeld(new C_HopperSpin(false));
		//btnY.whileHeld(new C_GearLiftLower(true));
//		Command gMoveUp = new C_GearLiftLower(true);
//		btnY.whenPressed(gMoveUp);
//		btnY.whenReleased(new C_StopCommand(gMoveUp));
		
		//btnA.whileHeld(new C_GearLiftLower(false));
		//Command gMoveDown = new C_GearLiftLower(false);
		//btnA.whenPressed(gMoveDown);
		//btnA.whenReleased(new C_StopCommand(gMoveDown));
		
		Command feedFuel = new CG_FeedFuel();
		Command hopperReverse = new C_HopperSpin(false);
		
		rightBumper.whileHeld(feedFuel);
		feeder = new C_TriggerWhileHeld(hopperReverse, opGamepad, false);
//		feeder.start();
		
		flywheel = new C_SpinFlywheel();
		leftBumper.whenPressed(flywheel);
		stopFlywheel = new C_TriggerWhenPressed(new C_StopCommand(flywheel), opGamepad, true);
//		stopFlywheel.start();
		
		//Command flywheel = new C_TriggerWhenPressed(shooterPID, opGamepad, true);
		//flywheel.start();
		//leftBumper.whenPressed(new C_StopCommand(shooterPID));
		
		//Command shooterPID = new C_MotorPID();
		
		//rightBumper.whileHeld(new C_GearOnPeg());
		
		/********TestingStick*******/
//		testCmd = new C_TunePID(Robot.ss_DriveTrain, Robot.ss_DriveTrain.rightMotors, 1024, 256, CANTalon.TalonControlMode.Position, false, true);
//		kp = ?
//		ticks = 10000
//		testCmd = new C_TunePID(Robot.ss_DriveTrain, Robot.ss_DriveTrain.rightMotors, 1024, 256, CANTalon.TalonControlMode.Speed, false, true);
//		kf = 1
//		ticks = 300
		testCmd = new C_TunePID(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, 1, 1, CANTalon.TalonControlMode.Position, false, false);
		autoTest = new CG_AutoTest();
		
		//kp 1.5 to 3.0

//		testStickBtn11.whileHeld(testCmd);
//		
//		//testBtn7.whenReleased(new CG_AutoTest());
//		
//		testStickBtn1.whileHeld(new C_TestDriveByJoystick());
//		testStickBtn2.whileHeld(new C_TestDriveBySet());
//		
//		// change the talon num
//		testStickBtn5.whenPressed(new C_TestSetTalonNum(false));
//		testStickBtn6.whenPressed(new C_TestSetTalonNum(true));
//		
//		// change the motor speed
//		testStickBtn3.whenPressed(new C_TestSetMotorSpeed(false));
//		testStickBtn4.whenPressed(new C_TestSetMotorSpeed(true));
		
		//testBtn11.whileHeld(testCmd);
		
		//testBtn7.whenReleased(new CG_AutoTest());
		
		testDriveJoystick = new C_TriggerWhileHeld(new C_TestDriveByJoystick(), autoTestGamepad, false);
		testDriveSet = new C_TriggerWhileHeld(new C_TestDriveBySet(), autoTestGamepad, true);
		
		// change the talon num
		testLeftBumper.whenPressed(new C_TestSetTalonNum(false));
		testRightBumper.whenPressed(new C_TestSetTalonNum(true));
		
		// change the motor speed

		testBtnA.whenPressed(new C_TestSetMotorSpeed(false));
		testBtnY.whenPressed(new C_TestSetMotorSpeed(true));
		
		testBtnSelect.whenPressed(new C_RunAutoTest());
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
