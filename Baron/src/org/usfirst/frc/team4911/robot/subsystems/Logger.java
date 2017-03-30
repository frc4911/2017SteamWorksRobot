package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_UpdateLog;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Logger extends Subsystem {
	// a divider for splitting up strings
//	public String div = "*";
//	
//	private DriverStation ds = DriverStation.getInstance();
//	private PowerDistributionPanel pdp = new PowerDistributionPanel();
//	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateLog());
    }
    
//    private String driveMode() {
//    	String mode = "";
//    	
//    	if(ds.isOperatorControl()) {
//    		mode = "teleOp";
//    	}
//    	else if(ds.isAutonomous()) {
//    		mode = "auto";
//    	}
//    	else if(ds.isTest()) {
//    		mode = "test";
//    	}
//    	else {
//    		mode = "disabled";
//    	}
//    	
//    	return mode;
//    }
//    
//    int talonConstIndex = 0;
//    
//    int runningCommandsIndex = 0;
//    int stoppedCommandsIndex = 0;
//    
//    int driveModeIndex = 0;
//    int fmsConnectionIndex = 0;
//    int brownoutIndex = 0;
////	int DSBatteryVoltageIndex = 0;
////	int allianceIndex = 0;
////	int allianceLocationIndex = 0;
////	int fmsMatchTimeIndex = 0;
//
//    int pdpVoltIndex = 0;
//    int pdpCurrentIndex = 0;
////    int pdpPortCurrentIndex = 0;
//    
//    int leftJoystickYIndex = 0;
//    int rightJoystickYIndex = 0;
//    
//    int dtLStartIndex = 0;
//    int dtRStartIndex = 0;
//    
//    int fCollStartIndex = 0;
//    int fHopStartIndex = 0;
//    
//    int fFeederStartIndex = 0;
//    int fShooterStartIndex = 0;
//    int fShooterGetSpeedIndex = 0;
//    
//    int gCollLimitSwitchIndex = 0;
//    int gCollStartIndex = 0;
//    int gLiftStartIndex = 0;
//    
////    int gPotIndex = 0;
//    
//    int navXStartIndex = 0;
//    
//    int climberStartIndex = 0;
//    
//    int cameraIndex = 0;
//    
//    int lidarIndex = 0;
//    
//    public Logger() {
//    	// talon constants
//    	talonConstIndex = Robot.ss_Logging.addColumn("talonConstants");
//    	
//    	// commands
//    	runningCommandsIndex = Robot.ss_Logging.addColumn("currCommands");
//    	stoppedCommandsIndex = Robot.ss_Logging.addColumn("stoppedCommands");
//    	
//    	// DriverStation
//    	driveModeIndex = Robot.ss_Logging.addColumn("driveMode");
//    	fmsConnectionIndex = Robot.ss_Logging.addColumn("fmsConnection");
//    	brownoutIndex = Robot.ss_Logging.addColumn("brownout");
////    	DSBatteryVoltageIndex = Robot.ss_Logging.addColumn("dsBatteryVoltage");
////    	allianceIndex = Robot.ss_Logging.addColumn("Alliance");
////    	allianceLocationIndex = Robot.ss_Logging.addColumn("AllianceLocation");
////		fmsMatchTimeIndex = Robot.ss_Logging.addColumn("fmsMatchTime");
//
//    	// PDP
//    	pdpVoltIndex = Robot.ss_Logging.addColumn("pdpVolt");
//    	pdpCurrentIndex = Robot.ss_Logging.addColumn("pdpCurr");
//    	//pdpPortCurrentIndex = addPDPPortIndices();
//    	
//    	// joystickY values
//    	leftJoystickYIndex = Robot.ss_Logging.addColumn("leftStickY");
//    	rightJoystickYIndex = Robot.ss_Logging.addColumn("rightStickY");
//    	
//    	// driveTrainLeft
//    	dtLStartIndex = addMotorIndices(Robot.ss_DriveTrainLeft.leftMotors.getDescription(), true, true);
//    	
//    	// driveTrainRight
//    	dtRStartIndex = addMotorIndices(Robot.ss_DriveTrainRight.rightMotors.getDescription(), true, true);
//    	
//    	// fuelCollector
//		fCollStartIndex = addMotorIndices(Robot.ss_FuelCollector.collectorMotors.getDescription(), false, false);
//    	
//    	// fuelHopper
//    	fHopStartIndex = addMotorIndices(Robot.ss_FuelHopper.hopperMotor.getDescription(), false, false);
//    	
//		// fuelShooter
//    	fFeederStartIndex = addMotorIndices(Robot.ss_FuelFeeder.feederMotor.getDescription(), false, true);
//		fShooterStartIndex = addMotorIndices(Robot.ss_FuelShooter.shooterMotors.getDescription(), true, true);
//		fShooterGetSpeedIndex = Robot.ss_Logging.addColumn(Robot.ss_FuelShooter.shooterMotors.getDescription()+" getSpeed");
//		
//		// gear assembly
//    	gCollLimitSwitchIndex = Robot.ss_Logging.addColumn(Robot.ss_GearIntake.gearIntakeMotor.getDescription() + " limitSwitch");
//		gCollStartIndex = addMotorIndices(Robot.ss_GearIntake.gearIntakeMotor.getDescription(), false , false);
//		gLiftStartIndex = addMotorIndices(Robot.ss_GearLift.gearLiftMotor.getDescription(), false , true);
////		gPotIndex = Robot.ss_Logging.addColumn(" gearPotentiometer");
//		
//		// climber
//    	climberStartIndex = addMotorIndices(Robot.ss_Climber.climberMotors.getDescription(), false, false);
//		
//		navXStartIndex = addNAVXIndices();
//		
//		cameraIndex = Robot.ss_Logging.addColumn("Camera angle");
//		
//		lidarIndex = Robot.ss_Logging.addColumn("lidar ");
//    }
//
//    boolean firstTime = true;
//    
//    public void log() {
//    	if (Robot.ss_Logging != null){
//
////    		SmartDashboard.putBoolean("SS_Logging present", true);
//
//    		// talon constants
//    		if(firstTime) {
//	    		smartLog(false, true, talonConstIndex, Robot.ss_Config.getTalonConstants(div));
//    		}
//    		
//    		// current running command 
//    		boolean cmdSmart = false;
//    		boolean cmdLog = true;
//    		smartLog(cmdSmart, cmdLog, runningCommandsIndex, runningCommands);
//    		smartLog(cmdSmart, cmdLog, stoppedCommandsIndex, stoppedCommands);
//    		
//    		// DriverStation
//    		boolean dsSmart = false;
//    		boolean dsLog = true;
//    		smartLog(dsSmart, dsLog, driveModeIndex, driveMode());
//    		smartLog(dsSmart, dsLog, fmsConnectionIndex, "" + ds.isFMSAttached());
//    		smartLog(dsSmart, dsLog, brownoutIndex, "" + ds.isBrownedOut());
////    		smartLog(dsSmart, dsLog, DSBatteryVoltageIndex, "" + ds.getBatteryVoltage());
//    		
////    		if(firstTime) {
////	    		if (ds.getAlliance() == DriverStation.Alliance.Blue){
////	    			smartLog(dsSmart, dsLog, allianceIndex, "Blue");	
////	    		}
////	    		else{
////	    			smartLog(dsSmart, dsLog, allianceIndex, "Red");
////	    		}
////	    		smartLog(dsSmart, dsLog, allianceLocationIndex, "" + ds.getLocation());
////	    		smartLog(dsSmart, dsLog, fmsMatchTimeIndex, "" + ds.getMatchTime());
////    		}   
////    		
////        	allianceIndex = Robot.ss_Logging.addColumn("Alliance");
////        	allianceLocationIndex = Robot.ss_Logging.addColumn("Alliance Location");
////    		fmsMatchTimeIndex = Robot.ss_Logging.addColumn("FMS Match Time");
//
//    		// PDP
//    		boolean pdpSmart = false;
//    	    boolean pdpLog = true;
//    		smartLog(pdpSmart, pdpLog, pdpVoltIndex, "" + pdp.getVoltage());
//    		smartLog(pdpSmart, pdpLog, pdpCurrentIndex, "" + pdp.getTotalCurrent());
//    		//logPDPPorts(pdpSmart, pdpLog,pdpPortCurrentIndex);
//    		
//    		// joystickY values
//    		boolean joySmart = false;
//    		boolean joyLog = true;
//    		smartLog(joySmart, joyLog, leftJoystickYIndex, "" + Robot.oi.stickL.getY());
//    		smartLog(joySmart, joyLog, rightJoystickYIndex, "" + Robot.oi.stickR.getY());
//    		
//    		// driveTrainLeft
//    		logDefaultMotor(Robot.ss_DriveTrainLeft.leftMotors, true, true, dtLStartIndex);
//    		
//    		// driveTrainRight
//    		logDefaultMotor(Robot.ss_DriveTrainRight.rightMotors, true, true, dtRStartIndex);
//
//    		// fuelCollector
//    		logDefaultMotor(Robot.ss_FuelCollector.collectorMotors, false, false, fCollStartIndex);
//    		
//    		// fuelHopper
//    		logDefaultMotor(Robot.ss_FuelHopper.hopperMotor, false, false, fHopStartIndex);
//    		
//    		// fuelShooter
//    		logDefaultMotor(Robot.ss_FuelFeeder.feederMotor, false, true, fFeederStartIndex);
//    		logDefaultMotor(Robot.ss_FuelShooter.shooterMotors, true, true, fShooterStartIndex);
//    		smartLog(true, true, fShooterGetSpeedIndex, ""+Robot.ss_FuelShooter.shooterMotors.getTalon().getSpeed());
//    		
//    		// gear assembly
//    		logDefaultMotor(Robot.ss_GearIntake.gearIntakeMotor, false, false, gCollStartIndex);
//    		logDefaultMotor(Robot.ss_GearLift.gearLiftMotor, false, true, gLiftStartIndex);
//    		//smartLog(true, true, gPotIndex, ""+Robot.ss_GearLift.getGearLiftPot());
//    		// climber
//    		logDefaultMotor(Robot.ss_Climber.climberMotors, false, false, climberStartIndex);
//    		
//    		//logNAVX(navXStartIndex);
//        	smartLog(true, true, cameraIndex, ""+Robot.ss_Camera.getAngle());
//        	//smartLog(true, true, lidarIndex, ""+Robot.ss_Lidar.getDistance());
//    		// flush
//    		Robot.ss_Logging.logFlush();
//    	}
//    	else {
////    		SmartDashboard.putBoolean("SS_Logging present", false);
//    	}
//    	
//    	runningCommands = "";
//    	stoppedCommands = "";
//    	firstTime = false;
//    }
//    
//    private int addPDPPortIndices(){
//    	int startIndex = Robot.ss_Logging.addColumn("PDPCurrentPort 0");
//
//    	for (int i=1; i<16; i++){
//    		Robot.ss_Logging.addColumn("PDPCurrentPort "+i);
//    	}
//    	
//    	return startIndex;
//    }
//
//    private int addMotorIndices(String desc, boolean hasFollower, boolean hasEncoder) {
//    	int startIndex = Robot.ss_Logging.addColumn(desc + " speed");
//    	Robot.ss_Logging.addColumn(desc + " TalonStickyFaultsUnderVolt");
//    	Robot.ss_Logging.addColumn(desc + " TalonVoltage");
//    	Robot.ss_Logging.addColumn(desc + " TalonCurrent");
//    	if(hasFollower) {
//	    	Robot.ss_Logging.addColumn(desc + " FTalonStickyFaultsUnderVolt");
//	    	Robot.ss_Logging.addColumn(desc + " FTalonVoltage");
//	    	Robot.ss_Logging.addColumn(desc + " FTalonCurrrent");
//    	}
//    	Robot.ss_Logging.addColumn(desc + " RPM");
//    	if(hasEncoder) {
//    		Robot.ss_Logging.addColumn(desc + " currEncPos");
//    	}
//    	return startIndex;
//    }
//    
//    private void logPDPPorts(boolean smart, boolean log, int index){
//    	for(int i=0; i< 16; i++){
//    		smartLog(smart, log, index++, "" + pdp.getCurrent(i));
//    	}
//    }
//	
//    private void logDefaultMotor(DefaultMotor motor, boolean hasFollower, boolean hasEncoder, int index) {
//    	boolean smart = false;
//		boolean log = true;
//		smartLog(smart, log, index++, "" + motor.getTalonValue(false));
//		smartLog(smart, log, index++, "" +motor.checkStickyFaults(motor, false));
//		smartLog(smart, log, index++, "" + motor.getOutputVoltage(false));
//		smartLog(true, log, index++, "" + motor.getOutputCurrent(false)); // hardcode to dashboard for debug 
//				
//		if(hasFollower) {
//			smartLog(smart, log, index++, "" +motor.checkStickyFaults(motor, true));
//			smartLog(smart, log, index++, "" + motor.getOutputVoltage(true));
//			smartLog(smart, log, index++, "" + motor.getOutputCurrent(true));
//		}
//		smartLog(smart, log, index++, "" + motor.getTalonSpeed());
//		if(hasEncoder) {
//			smartLog(true, log, index++, "" + motor.getEncPos());
//		}
//    }
//    
//    private void smartLog(boolean smart, boolean log, int keyIndex, String value) {
//    	if(smart) {
//    		SmartDashboard.putString(Robot.ss_Logging.getHeader(keyIndex), value);
//    	}
//    	if(log) {
//    		Robot.ss_Logging.logKeyOutput(keyIndex, value);
//    	}
//    }
//    
//    public String runningCommands = "";
    public void logRunningCommands(String commandName) {
//    	runningCommands += commandName + div;
    }
//    
//    public String stoppedCommands = "";
//    // hammer is the command that is stopping the stoppedCommand
//    public void logStoppedCommands(String hammer, String stoppedCommand) {
//    	stoppedCommands += hammer + "STOPPED" + stoppedCommand + div;
//    }
//    
//    private int addNAVXIndices(){
//    	String desc = "NavX";
//    	
//    	int startIndex = Robot.ss_Logging.addColumn(desc + " ");
//        Robot.ss_Logging.addColumn(desc + " IMU_Connected");
//        Robot.ss_Logging.addColumn(desc + " IMU_Yaw");
//        Robot.ss_Logging.addColumn(desc + " IMU_Pitch");
//        Robot.ss_Logging.addColumn(desc + " IMU_Roll");
//        Robot.ss_Logging.addColumn(desc + " IMU_CompassHeading");
//        Robot.ss_Logging.addColumn(desc + " IMU_Update_Count");
//        Robot.ss_Logging.addColumn(desc + " IMU_Byte_Count");
//
//        /* These functions are compatible w/the WPI Gyro Class */
//        Robot.ss_Logging.addColumn(desc + " IMU_TotalYaw");
//        Robot.ss_Logging.addColumn(desc + " IMU_YawRateDPS");
//
//        Robot.ss_Logging.addColumn(desc + " IMU_Accel_X");
//        Robot.ss_Logging.addColumn(desc + " IMU_Accel_Y");
//        Robot.ss_Logging.addColumn(desc + " IMU_IsMoving");
//        Robot.ss_Logging.addColumn(desc + " IMU_Temp_C");
//        Robot.ss_Logging.addColumn(desc + " IMU_IsCalibrating");
//
//        Robot.ss_Logging.addColumn(desc + " Velocity_X");
//        Robot.ss_Logging.addColumn(desc + " Velocity_Y");
//        Robot.ss_Logging.addColumn(desc + " Displacement_X");
//        Robot.ss_Logging.addColumn(desc + " Displacement_Y");
//
//        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
//        /* NOTE:  These values are not normally necessary, but are made available   */
//        /* for advanced users.  Before using this data, please consider whether     */
//        /* the processed data (see above) will suit your needs.                     */
//
//        Robot.ss_Logging.addColumn(desc + " RawGyro_X");
//        Robot.ss_Logging.addColumn(desc + " RawGyro_Y");
//        Robot.ss_Logging.addColumn(desc + " RawGyro_Z");
//        Robot.ss_Logging.addColumn(desc + " RawAccel_X");
//        Robot.ss_Logging.addColumn(desc + " RawAccel_Y");
//        Robot.ss_Logging.addColumn(desc + " RawAccel_Z");
//        Robot.ss_Logging.addColumn(desc + " RawMag_X");
//        Robot.ss_Logging.addColumn(desc + " RawMag_Y");
//        Robot.ss_Logging.addColumn(desc + " RawMag_Z");
//        Robot.ss_Logging.addColumn(desc + " IMU_Temp_C");
//        /* Omnimount Yaw Axis Information                                           */
//        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
////        AHRS::BoardYawAxis yaw_axis = ahrs->GetBoardYawAxis();
////        SmartDashboard::PutString(  " YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
////        Robot.ss_Logging.addColumn(desc + " YawAxis",              yaw_axis.board_axis );
//
//        /* Sensor Board Information                                                 */
//        Robot.ss_Logging.addColumn(desc + " FirmwareVersion");
//
//        /* Quaternion Data                                                          */
//        /* Quaternions are fascinating, and are the most compact representation of  */
//        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
//        /* from the Quaternions.  If interested in motion processing, knowledge of  */
//        /* Quaternions is highly recommended.                                       */
//        Robot.ss_Logging.addColumn(desc + " QuaternionW");
//        Robot.ss_Logging.addColumn(desc + " QuaternionX");
//        Robot.ss_Logging.addColumn(desc + " QuaternionY");
//        Robot.ss_Logging.addColumn(desc + " QuaternionZ");
//
//    	return startIndex;
//    }
//
//    private void logNAVX(int index){
//    	boolean smart = true;
//    	boolean log = false;
//
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.isConnected());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getYaw());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getPitch());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRoll());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getCompassHeading());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getUpdateCount());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getByteCount());
////
////        /* These functions are compatible w/the WPI Gyro Class */
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getAngle());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRate());
////
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getWorldLinearAccelX());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getWorldLinearAccelY());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.isMoving());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getTempC());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.isCalibrating());
////
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getVelocityX() );
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getVelocityY() );
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getDisplacementX() );
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getDisplacementY() );
//
//        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
//        /* NOTE:  These values are not normally necessary, but are made available   */
//        /* for advanced users.  Before using this data, please consider whether     */
//        /* the processed data (see above) will suit your needs.                     */
//
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawGyroX());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawGyroY());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawGyroZ());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawAccelX());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawAccelY());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawAccelZ());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawMagX());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawMagY());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getRawMagZ());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getTempC());
//        /* Omnimount Yaw Axis Information                                           */
//        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
////        AHRS::BoardYawAxis yaw_axis = ahrs->GetBoardYawAxis();
////        SmartDashboard::PutString(  "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
////        SmartDashboard::PutNumber(  "YawAxis",              yaw_axis.board_axis );
//
//        /* Sensor Board Information                                                 */
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getFirmwareVersion());
//
//        /* Quaternion Data                                                          */
//        /* Quaternions are fascinating, and are the most compact representation of  */
//        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
//        /* from the Quaternions.  If interested in motion processing, knowledge of  */
//        /* Quaternions is highly recommended.                                       */
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getQuaternionW());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getQuaternionX());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getQuaternionY());
////		smartLog(smart, log, index++, "" + Robot.ss_NAVX.navX.getQuaternionZ());
//    }
}

