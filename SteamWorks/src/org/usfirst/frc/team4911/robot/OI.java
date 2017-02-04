package org.usfirst.frc.team4911.robot;

//import org.usfirst.frc.team4911.robot.commands.C_DriveByPID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4911.robot.commands.CG_AutoTest;
import org.usfirst.frc.team4911.robot.commands.CG_DriveCaleb;
import org.usfirst.frc.team4911.robot.commands.C_Drive;
import org.usfirst.frc.team4911.robot.commands.C_DriveToChairsCaleb;
import org.usfirst.frc.team4911.robot.commands.C_TestFSMotor;
import org.usfirst.frc.team4911.robot.commands.C_TurnCaleb;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stickL = new Joystick(3);
	public Joystick stickR = new Joystick(4);
	
	JoystickButton btnTest = new JoystickButton(stickR, 2);
	
	public Joystick stickTest = new Joystick(0);
	JoystickButton btn2 = new JoystickButton(stickTest, 2);
	JoystickButton btn3 = new JoystickButton(stickTest, 3);
	
	public OI() {
		btn2.whenReleased(new C_Drive(true, 2));
		btn3.whenReleased(new CG_AutoTest());
		
		btnTest.whileHeld(new C_TestFSMotor());
	}
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

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
