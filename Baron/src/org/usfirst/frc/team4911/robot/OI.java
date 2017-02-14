package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.CG_AutoTest;
import org.usfirst.frc.team4911.robot.commands.C_CollectGear;
import org.usfirst.frc.team4911.robot.commands.C_ManualTestMotor;
import org.usfirst.frc.team4911.robot.commands.C_MotorToEncPos;
import org.usfirst.frc.team4911.robot.commands.C_SpitGear;
import org.usfirst.frc.team4911.robot.commands.C_StopCommand;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_TestDriveBySet;
import org.usfirst.frc.team4911.robot.commands.C_TestSetMotorSpeed;
import org.usfirst.frc.team4911.robot.commands.C_TestSetTalonNum;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stickL = new Joystick(0);
	public Joystick stickR = new Joystick(1);
	public Joystick opGamepad = new Joystick(2);
	
	JoystickButton btnA = new JoystickButton(opGamepad, 1);
	JoystickButton btnB = new JoystickButton(opGamepad, 2);
	JoystickButton btnX = new JoystickButton(opGamepad, 3);
	JoystickButton btnY = new JoystickButton(opGamepad, 4);
	
	/**********Testing**********/
	public Joystick autoTestStick = new Joystick(3);
	
	JoystickButton testBtn1 = new JoystickButton(autoTestStick, 1);
	JoystickButton testBtn2 = new JoystickButton(autoTestStick, 2);
	JoystickButton testBtn3 = new JoystickButton(autoTestStick, 3);
	JoystickButton testBtn4 = new JoystickButton(autoTestStick, 4);
	JoystickButton testBtn5 = new JoystickButton(autoTestStick, 5);
	JoystickButton testBtn6 = new JoystickButton(autoTestStick, 6);
	JoystickButton testBtn7 = new JoystickButton(autoTestStick, 7);
	JoystickButton testBtn11 = new JoystickButton(autoTestStick, 11);
	
	public OI() {
		Command gColl = new C_CollectGear();
		btnA.whenPressed(gColl);
		btnA.whenReleased(new C_StopCommand(gColl));
		btnY.whileHeld(new C_SpitGear());
		
		/**********Testing**********/
		testBtn11.whenReleased(new C_MotorToEncPos(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainLeft, 2000, 1440, 360, 
				4.0, 0.0, 0.0, 0.0, 0.8, 0, 1.4, 0.8, CANTalon.TalonControlMode.Position));
		
		testBtn7.whenReleased(new CG_AutoTest());
		
		testBtn1.whileHeld(new C_TestDriveByJoystick());
		testBtn2.whileHeld(new C_TestDriveBySet());
		
		// change the talon num
		testBtn5.whenPressed(new C_TestSetTalonNum(false));
		testBtn6.whenPressed(new C_TestSetTalonNum(true));
		
		// change the motor speed
		testBtn3.whenPressed(new C_TestSetMotorSpeed(false));
		testBtn4.whenPressed(new C_TestSetMotorSpeed(true));
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
