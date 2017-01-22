package org.usfirst.frc.team4911.robot;

import java.io.FileNotFoundException;

import org.usfirst.frc.team4911.robot.commands.CG_Auto1;
import org.usfirst.frc.team4911.robot.commands.CG_Auto2;
import org.usfirst.frc.team4911.robot.commands.CG_Auto3;
import org.usfirst.frc.team4911.robot.commands.CG_AutoAll;
import org.usfirst.frc.team4911.robot.commands.C_BrakeByButtonArm;
import org.usfirst.frc.team4911.robot.commands.C_CompleteDriveByTime;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;
import org.usfirst.frc.team4911.robot.commands.C_DriveByMP;
import org.usfirst.frc.team4911.robot.commands.C_LiftShooterJoy;
import org.usfirst.frc.team4911.robot.commands.C_MoveArmByButton;
import org.usfirst.frc.team4911.robot.commands.C_ShooterClosedLoop;
import org.usfirst.frc.team4911.robot.commands.C_ShooterLiftHome;
import org.usfirst.frc.team4911.robot.commands.C_DrivetrainPID;
import org.usfirst.frc.team4911.robot.commands.C_DrivetrainPIDBySmartInput;
import org.usfirst.frc.team4911.robot.commands.C_ShooterSetBrakeMode;
import org.usfirst.frc.team4911.robot.commands.C_ShooterSetSoftLimitValues;
import org.usfirst.frc.team4911.robot.commands.C_ShooterSetSoftLimits;
import org.usfirst.frc.team4911.robot.commands.C_ShooterSetVoltageRampRate;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    public Joystick stick0 = new Joystick(0);
    public Joystick stick3 = new Joystick(1);
    public Joystick stick2 = new Joystick(2);
//    //public Joystick stick2 = new Joystick(1);
//    // Button button = new JoystickButton(stick, buttonNumber);
    Button trigger = new JoystickButton(stick0, 1);
    Button b2 = new JoystickButton(stick0, 2);    
    Button b3 = new JoystickButton(stick0, 3);
    Button b4 = new JoystickButton(stick0, 4);
    Button b5 = new JoystickButton(stick0, 5);
    Button b6 = new JoystickButton(stick0, 6);
    Button b7 = new JoystickButton(stick0, 7);
    Button b8 = new JoystickButton(stick0, 8);
    Button b9 = new JoystickButton(stick0, 9);
    Button b10 = new JoystickButton(stick0, 10);
    Button b11 = new JoystickButton(stick0, 11);
    
//    Button thumbButton = new JoystickButton(stick, 2);
//    Button side1 = new JoystickButton(stick, 7);
//    Button side2 = new JoystickButton(stick, 8);
//    Button side3 = new JoystickButton(stick, 9);
//    Button side4 = new JoystickButton(stick, 10);
//    
//    Button armUp = new JoystickButton(stick2, 2);
//    Button armDown = new JoystickButton(stick2, 4);
//    Button brakeArm = new JoystickButton(stick2, 1);
//    
    public OI() {
    	//brake.whileHeld(new C_BrakeByButton());
    	//brakeArm.whileHeld(new C_RollShooter());
    	
    	//brake.whenPressed(new C_BrakeByButton2());
    	//brakeRelease.whenPressed(new C_ReleaseBrakeButton());
    	
//    	armUp.whenPressed(new C_MoveArmByButton(1));
//    	armDown.whenPressed(new C_MoveArmByButton(-1));
    	
//    	trigger.whenPressed(new C_WriteToRoborio());
    	//trigger.whenPressed(new C_ShooterLiftHome());
    	trigger.whileHeld(new C_DriveByJoystick());
//    	b2.whenPressed(new C_ShooterSetBrakeMode(false));
//    	b3.whenPressed(new C_ShooterSetBrakeMode(true));
//    	b4.whenPressed(new C_ShooterSetSoftLimits(true,4300,1000));
//    	b5.whenPressed(new C_ShooterSetSoftLimits(false,0,0));
    	//b3.whenPressed(new CG_Auto3());
    	b4.whenPressed(new CG_Auto1());
    	//b5.whenPressed(new CG_Auto2());
    	
    	b5.whenPressed(new C_DrivetrainPIDBySmartInput());
    	b6.whenPressed(new CG_AutoAll());
    	b7.whenPressed(new C_DrivetrainPID(0,0,0)); // setup PID
    	b8.whenPressed(new C_DrivetrainPID(2,0,0));// stop PID
//    	b9.whenPressed(new C_DrivetrainPID(1,8000, 8000)); // goto 5000
    	b9.whenPressed(new C_DrivetrainPID(1,10000,10000));
    	b10.whenPressed(new C_DrivetrainPID(1,5300,-5300));    	
    	b6.whenPressed(new C_DrivetrainPID(1,-5300,5300));
    	//b10.whileHeld(new C_LiftShooterJoy());
    	
    	//b7.whenPressed(new C_ShooterSetVoltageRampRate(-1000.0));
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

