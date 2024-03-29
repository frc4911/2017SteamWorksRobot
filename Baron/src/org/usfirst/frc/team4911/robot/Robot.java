
package org.usfirst.frc.team4911.robot;
import java.awt.*;

import org.usfirst.frc.team4911.robot.commands.*;
import org.usfirst.frc.team4911.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	public static SS_FuelFeeder ss_FuelFeeder = null;
	public static SS_GearIntake ss_GearIntake = null;
	public static SS_GearLift ss_GearLift = null;
	public static SS_Camera ss_Camera = null;
	
	public static SS_TestMotor ss_TestMotor = null;
	public static SS_AutoTestStats ss_AutoTestStats = null;
	
	public static SS_NAVX ss_NAVX = null;
	public static SS_Lidar ss_Lidar = null;
	
	// all subsystems must be created before logging
//	public static LoggingEngine ss_Logging = null;
	public static Logger ss_UpdateLog = null;
	
	public static OI oi;
	
	public static boolean pidTargetReached;

	public static double pid1 = 0;
	public static double pid2 = 0;
	
	Command autonomousCommand;
	//SendableChooser<Command> chooser = new SendableChooser<>();
	int chooser = 0;// default to do nothing
	
	static public int activeDrivetrainPIDs = 0;

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
		ss_FuelFeeder = new SS_FuelFeeder();
		ss_GearIntake = new SS_GearIntake();
		ss_GearLift = new SS_GearLift();
		ss_Camera = new SS_Camera();
		
		ss_TestMotor = new SS_TestMotor();
		ss_AutoTestStats = new SS_AutoTestStats();
		
		//ss_NAVX = new SS_NAVX();
		//ss_Lidar = new SS_Lidar();
		
//		ss_Logging = new LoggingEngine();
		ss_UpdateLog = new Logger();
		oi = new OI();
		
		SmartDashboard.putNumber("Auto choice", 0);
		//chooser.addDefault("Default Auto", <insert command here>);
		// chooser.addObject("My Auto", new MyAutoCommand());
//		int testRead = (int)SmartDashboard.getNumber("Auto mode", -654);
//		if (testRead == -654)
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
        UsbCamera usbCamera0 = server1.startAutomaticCapture(0);
        UsbCamera usbCamera1 = server1.startAutomaticCapture(1);
        usbCamera0.setFPS(15);
        usbCamera1.setFPS(15);

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
		//autonomousCommand = chooser.getSelected();
		chooser = (int) SmartDashboard.getNumber("Auto choice", 0);
		switch(chooser) {
		case 1:
			autonomousCommand = new CG_Auto_1_Line();
			break;
		case 5:
			autonomousCommand = new CG_Auto_5_LeftGear();
			break;
		case 6:
			autonomousCommand = new CG_Auto_6_CenterGear();
			break;
		case 7:
			autonomousCommand = new CG_Auto_7_RightGear();
			break;
		case 30:
			autonomousCommand = new CG_Auto_30_BRight_Line_Shoot();
			break;
		case 32:
			autonomousCommand = new CG_Auto_32_BRight_Gear_Shoot();
			break;
		case 40:
			autonomousCommand = new CG_Auto_40_BLeft_Line_Shoot();
			break;
		case 42:
			autonomousCommand = new CG_Auto_42_BLeft_Gear_Shoot();
			break;
		case 100:
			autonomousCommand = new CG_Auto_100_PreTest();
			break;
		default:
			//nothing;
			autonomousCommand = null;
			break;
		}
		
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
	double lastTime = 0;
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("I am alive", counter++);
		
//		double time = Timer.getFPGATimestamp();
//		System.out.println("delta "+(time-lastTime));
//		lastTime = time;
//		
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
