package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_MotorPID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	public Joystick stick3 = new Joystick(3);
	//public Joystick stick1 = new Joystick(1);
	
	Button trigger1 = new JoystickButton(stick3, 1);
	
	Button b5 = new JoystickButton(stick3, 5);
	Button b6 = new JoystickButton(stick3, 6);
	Button b7 = new JoystickButton(stick3, 7);
	Button b8 = new JoystickButton(stick3, 8);
	Button b9 = new JoystickButton(stick3, 9);
	Button b10 = new JoystickButton(stick3, 10);
	
	public OI() {
		trigger1.whileHeld(new C_DriveByJoystick());
		b5.whenPressed(new C_MotorPID(0));// stop 
		b6.whenPressed(new C_MotorPID(1)); // position
		b7.whenPressed(new C_MotorPID(2));// speed
		b8.whenPressed(new C_MotorPID(3));// speed from dashboard
//		b10.whenPressed(new C_MotorPID(1,5300));  
	}  	
	
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
