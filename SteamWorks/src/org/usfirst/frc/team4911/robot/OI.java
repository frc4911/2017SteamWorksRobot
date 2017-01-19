package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.C_DriveByPID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stickL = new Joystick(0);
	public Joystick stickR = new Joystick(1);
	public Button trigger = new JoystickButton(stickL, 1);
	public Button handle1 = new JoystickButton(stickL, 5);
	public Button handle2 = new JoystickButton(stickL, 6);
	
	public OI() {
		/*SmartDashboard.putNumber("Move Duration", 0);
		SmartDashboard.putNumber("Turn Duration", 0);
		SmartDashboard.putBoolean("Turn Direction", true);*/
		
		SmartDashboard.putNumber("Target Position", 0);
		
		trigger.whileHeld(new C_DriveByPID());
		//handle1.whenPressed(new C_Drive(true));
		//handle2.whenPressed(new C_Drive(false));
	}
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	public Joystick stick0 = new Joystick(0);
	public Joystick stick1 = new Joystick(1);
	Button bTrigger = new JoystickButton(stick0, 1);
	
	Button button5 = new JoystickButton(stick0, 5);
	Button button6 = new JoystickButton(stick0, 6);
	
	Button button7 = new JoystickButton(stick0, 7);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
}
