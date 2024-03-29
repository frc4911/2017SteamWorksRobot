package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_FeederBumpSpeed;
import org.usfirst.frc.team4911.robot.commands.C_FeederStop;
import org.usfirst.frc.team4911.robot.commands.C_MotorPID;
import org.usfirst.frc.team4911.robot.commands.C_PIDBumpSpeed;
import org.usfirst.frc.team4911.robot.commands.C_ValueAdjuster;

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
	

	Button b3 = new JoystickButton(stick3, 3);
	Button b4 = new JoystickButton(stick3, 4);
	Button b5 = new JoystickButton(stick3, 5);
	Button b6 = new JoystickButton(stick3, 6);
	Button b7 = new JoystickButton(stick3, 7);
	Button b8 = new JoystickButton(stick3, 8);
	Button b9 = new JoystickButton(stick3, 9);
	Button b10 = new JoystickButton(stick3, 10);
	Button b11 = new JoystickButton(stick3, 11);
	Button b12 = new JoystickButton(stick3, 12);
	Button b13 = new JoystickButton(stick3, 13);
	Button b14 = new JoystickButton(stick3, 14);
	Button b15 = new JoystickButton(stick3, 15);
	Button b16 = new JoystickButton(stick3, 16);
	
	public OI() {
		trigger1.whileHeld(new C_DriveByJoystick());
		
		b3.whenPressed(new C_ValueAdjuster("Kd",0,-.01));
		b4.whenPressed(new C_ValueAdjuster("Kd",0,.01));
		
		b5.whenPressed(new C_MotorPID(0));// stop 
		b10.whenPressed(new C_MotorPID(3));// speed from dashboard
		
		b6.whenPressed(new C_FeederStop());
		b9.whenPressed(new C_FeederBumpSpeed(0)); // funky way to start
		
		b7.whenPressed(new C_FeederBumpSpeed(-.05));
		b8.whenPressed(new C_FeederBumpSpeed(.05));

		b13.whenPressed(new C_ValueAdjuster("Kp",0,-.01));
		b14.whenPressed(new C_ValueAdjuster("Kp",0,.01));
		
		b12.whenPressed(new C_ValueAdjuster("Kf",0,-.001));
		b15.whenPressed(new C_ValueAdjuster("Kf",0,.001));
		
		b11.whenPressed(new C_ValueAdjuster("ticks",0,-50));
		b16.whenPressed(new C_ValueAdjuster("ticks",0,50));
		
		//b11.whenPressed(new C_PIDBumpSpeed(-50));// dec
		//b16.whenPressed(new C_PIDBumpSpeed(50));// inc 
		
		
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
