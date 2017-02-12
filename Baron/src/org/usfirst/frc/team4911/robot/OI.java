package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.C_ManualTestMotor;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveBySet;
import org.usfirst.frc.team4911.robot.commands.C_TestSetMotorSpeed;
import org.usfirst.frc.team4911.robot.commands.C_TestSetTalonNum;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stickL = new Joystick(0);
	public Joystick stickR = new Joystick(1);
	public Joystick opGamepad = new Joystick(2);
	
	public Joystick autoTestStick = new Joystick(3);
//	JoystickButton testBtnDTFL = new JoystickButton(autoTestStick, 5);
//	JoystickButton testBtnDTRL = new JoystickButton(autoTestStick, 3);
//	JoystickButton testBtnDTFR = new JoystickButton(autoTestStick, 6);
//	JoystickButton testBtnDTRR = new JoystickButton(autoTestStick, 4);
	
	JoystickButton testBtn1 = new JoystickButton(autoTestStick, 1);
	JoystickButton testBtn2 = new JoystickButton(autoTestStick, 2);
	JoystickButton testBtn3 = new JoystickButton(autoTestStick, 3);
	JoystickButton testBtn4 = new JoystickButton(autoTestStick, 4);
	JoystickButton testBtn5 = new JoystickButton(autoTestStick, 5);
	JoystickButton testBtn6 = new JoystickButton(autoTestStick, 6);
	
	public OI() {
		testBtn1.whileHeld(new C_TestDriveByJoystick());
		testBtn2.whileHeld(new C_TestDriveBySet());
		
		// change the talon num
		testBtn5.whenPressed(new C_TestSetTalonNum(false));
		testBtn6.whenPressed(new C_TestSetTalonNum(true));
		
		// change the motor speed
		testBtn3.whenPressed(new C_TestSetMotorSpeed(false));
		testBtn4.whenPressed(new C_TestSetMotorSpeed(true));
		
//		testBtnDTFL.whileHeld(new C_ManualTestMotor(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainLeft.talon));
//		testBtnDTRL.whileHeld(new C_ManualTestMotor(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainLeft.fTalon));
//		testBtnDTFR.whileHeld(new C_ManualTestMotor(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainRight.talon));
//		testBtnDTRR.whileHeld(new C_ManualTestMotor(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainRight.fTalon));
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
