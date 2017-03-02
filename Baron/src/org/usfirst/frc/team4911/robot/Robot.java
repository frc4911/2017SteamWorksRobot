
package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static ConfigFile ss_Config = new ConfigFile();
	
	public static SS_Climber ss_Climber = null;
	public static SS_DriveTrainLeft ss_DriveTrainLeft = null;
	public static SS_DriveTrainRight ss_DriveTrainRight = null;
	public static SS_FuelCollector ss_FuelCollector = null;
	public static SS_FuelHopper ss_FuelHopper = null;
	public static SS_FuelShooter ss_FuelShooter = null;
	public static SS_GearIntake ss_GearIntake = null;
	public static SS_GearLift ss_GearLift = null;
	public static SS_Camera ss_Camera = null;
	
	public static SS_TestMotor ss_TestMotor = null;
	public static SS_AutoTestStats ss_AutoTestStats = null;
	
	public static SS_NAVX ss_NAVX = null;
	
	// all subsystems must be created before logging
	public static LoggingEngine ss_Logging = null;
	public static Logger ss_UpdateLog = null;
	
	public static OI oi;
	
	public static boolean pidTargetReached;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		ss_Config = new ConfigFile();
		
		ss_Climber = new SS_Climber();
		ss_DriveTrainLeft = new SS_DriveTrainLeft();
		ss_DriveTrainRight = new SS_DriveTrainRight();
		ss_FuelCollector = new SS_FuelCollector();
		ss_FuelHopper = new SS_FuelHopper();
		ss_FuelShooter = new SS_FuelShooter();
		ss_GearIntake = new SS_GearIntake();
		ss_GearLift = new SS_GearLift();
		ss_Camera = new SS_Camera();
		
		ss_TestMotor = new SS_TestMotor();
		ss_AutoTestStats = new SS_AutoTestStats();
		
		//ss_NAVX = new SS_NAVX();
		ss_Logging = new LoggingEngine();
		ss_UpdateLog = new Logger();
		oi = new OI();
		//chooser.addDefault("Default Auto", <insert command here>);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		cameraManager();
		//updateSDForPIDTuning();
		ss_Config.updateConfigFile("/c/config.txt");
	}
	
	private void cameraManager() {
//		CameraServer cameraServer = CameraServer.getInstance();
//		cameraServer.setQuality(50);
//		UsbCamera camera = cameraServer.startAutomaticCapture();
//		VideoMode[] vms = camera.enumerateVideoModes();
//		VideoMode vm = camera.getVideoMode();
//		
//		camera.setVideoMode(vm);
		
		CameraServer server1;
		
		server1 = CameraServer.getInstance();
        UsbCamera usbCamera = server1.startAutomaticCapture();
//        usbCamera.setResolution(640, 480);
//        usbCamera.setResolution(1280, 720);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}
	
	public void robotPeriodic() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		oi.feeder.start();
		oi.stopFlywheel.start();
		
//		oi.testDriveJoystick.start();
//		oi.testDriveSet.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int counter = 0;
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("I am alive", counter++);
		SmartDashboard.putNumber("Gear Pot", ss_GearLift.getGearLiftPot());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
