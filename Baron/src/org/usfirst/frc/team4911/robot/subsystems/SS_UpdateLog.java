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
public class SS_UpdateLog extends Subsystem {
	// a divider for splitting up strings
	public String div = "*";
	
	private DriverStation ds = DriverStation.getInstance();
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateLog());
    }
    
    private String driveMode() {
    	String mode = "";
    	
    	if(ds.isOperatorControl()) {
    		mode = "teleOp";
    	}
    	else if(ds.isAutonomous()) {
    		mode = "auto";
    	}
    	else if(ds.isTest()) {
    		mode = "test";
    	}
    	else {
    		mode = "disabled";
    	}
    	
    	return mode;
    }
    
    int talonConstIndex = 0;
    
    int runningCommandsIndex = 0;
    int stoppedCommandsIndex = 0;
    
    int driveModeIndex = 0;
    int fmsConnectionIndex = 0;
    int brownoutIndex = 0;
    
    int pdpVoltIndex = 0;
    int pdpCurrentIndex = 0;
    
    int leftJoystickYIndex = 0;
    int rightJoystickYIndex = 0;
    
    int dtLStartIndex = 0;
    int dtRStartIndex = 0;
    
//    int fCollStartIndex = 0;
//    int fHopStartIndex = 0;
    
//    int fFeederStartIndex = 0;
//    int fShooterStartIndex = 0;
    
    int gCollLimitSwitchIndex = 0;
    int gCollStartIndex = 0;
    
//    int hangerStartIndex = 0;
    
    public SS_UpdateLog() {
    	// talon constants
    	talonConstIndex = Robot.ss_Logging.addColumn("talonConstants");
    	
    	// commands
    	runningCommandsIndex = Robot.ss_Logging.addColumn("currCommands");
    	stoppedCommandsIndex = Robot.ss_Logging.addColumn("stoppedCommands");
    	
    	// DriverStation
    	driveModeIndex = Robot.ss_Logging.addColumn("driveMode");
    	fmsConnectionIndex = Robot.ss_Logging.addColumn("fmsConnection");
    	brownoutIndex = Robot.ss_Logging.addColumn("brownout");
    	
    	// PDP
    	pdpVoltIndex = Robot.ss_Logging.addColumn("pdpVolt");
    	pdpCurrentIndex = Robot.ss_Logging.addColumn("pdpCurr");
    	
    	// joystickY values
    	leftJoystickYIndex = Robot.ss_Logging.addColumn("leftStickY");
    	rightJoystickYIndex = Robot.ss_Logging.addColumn("rightStickY");
    	
    	// driveTrainLeft
    	dtLStartIndex = addMotorIndices(Robot.ss_DriveTrain.driveTrainLeft.getDescription(), true, true);
    	
    	// driveTrainRight
    	dtRStartIndex = addMotorIndices(Robot.ss_DriveTrain.driveTrainRight.getDescription(), true, true);
    	
    	// fuelCollector
//		fCollStartIndex = addMotorIndices();
    	
    	// fuelHopper
//    	fHopStartIndex = addMotorIndices();
    	
		// fuelShooter
//    	fFeederStartIndex = addMotorIndices();
//		fShooterStartIndex = addMotorIndices();
    	
		// gear assembly
    	gCollLimitSwitchIndex = Robot.ss_Logging.addColumn(Robot.ss_GearHandler.gearCollector.getDescription() + " limitSwitch");
		gCollStartIndex = addMotorIndices(Robot.ss_GearHandler.gearCollector.getDescription(), false , false);
    	
		// hanger
//    	hangerStartIndex = addMotorIndices();
    }

    boolean logConstants = true;
    public void log() {
    	if (Robot.ss_Logging != null){

    		SmartDashboard.putBoolean("SS_Logging present", true);

    		// talon constants
    		if(logConstants) {
	    		smartLog(false, true, talonConstIndex, Robot.ss_Config.getTalonConstants(div));
	    		logConstants = false;
    		}
    		
    		// current running command 
    		boolean cmdSmart = false;
    		boolean cmdLog = true;
    		smartLog(cmdSmart, cmdLog, runningCommandsIndex, runningCommands);
    		smartLog(cmdSmart, cmdLog, stoppedCommandsIndex, stoppedCommands);
    		
    		// DriverStation
    		boolean dsSmart = false;
    		boolean dsLog = true;
    		smartLog(dsSmart, dsLog, driveModeIndex, driveMode());
    		smartLog(dsSmart, dsLog, fmsConnectionIndex, "" + ds.isFMSAttached());
    		smartLog(dsSmart, dsLog, brownoutIndex, "" + ds.isBrownedOut());
    		
    		// PDP
    		boolean pdpSmart = false;
    	    boolean pdpLog = true;
    		smartLog(pdpSmart, pdpLog, pdpVoltIndex, "" + pdp.getVoltage());
    		smartLog(pdpSmart, pdpLog, pdpCurrentIndex, "" + pdp.getTotalCurrent());
    	
    		// joystickY values
    		boolean joySmart = false;
    		boolean joyLog = true;
    		smartLog(joySmart, joyLog, leftJoystickYIndex, "" + Robot.oi.stickL.getY());
    		smartLog(joySmart, joyLog, rightJoystickYIndex, "" + Robot.oi.stickR.getY());
    		
    		// driveTrainLeft
    		logDefaultMotor(Robot.ss_DriveTrain.driveTrainLeft, true, true, dtLStartIndex);
    		
    		// driveTrainRight
    		logDefaultMotor(Robot.ss_DriveTrain.driveTrainRight, true, true, dtRStartIndex);

    		// fuelCollector
//    		logDefaultMotor(null, false, fCollStartIndex);
    		
    		// fuelHopper
//    		logDefaultMotor(null, false, fHopStartIndex);
    		
    		// fuelShooter
//    		logDefaultMotor(null, false, fFeederStartIndex);
//    		logDefaultMotor(null, true, fShooterStartIndex);
    		
    		// gear assembly
    		logDefaultMotor(Robot.ss_GearHandler.gearCollector, false, false, gCollStartIndex);
    		
    		// hanger
//    		logDefaultMotor(null, false, hangerStartIndex);
    		
    		// flush
    		Robot.ss_Logging.logFlush();
    	}
    	else {
    		SmartDashboard.putBoolean("SS_Logging present", false);
    	}
    	
    	runningCommands = "";
    	stoppedCommands = "";
    }
    
    private int addMotorIndices(String desc, boolean hasFollower, boolean hasEncoder) {
    	int startIndex = Robot.ss_Logging.addColumn(desc + " speed");
    	Robot.ss_Logging.addColumn(desc + " TalonStickyFaultsUnderVolt");
    	Robot.ss_Logging.addColumn(desc + " TalonVoltage");
    	Robot.ss_Logging.addColumn(desc + " TalonCurrent");
    	if(hasFollower) {
	    	Robot.ss_Logging.addColumn(desc + " FTalonStickyFaultsUnderVolt");
	    	Robot.ss_Logging.addColumn(desc + " FTalonVoltage");
	    	Robot.ss_Logging.addColumn(desc + " FTalonCurrrent");
    	}
    	Robot.ss_Logging.addColumn(desc + " RPM");
    	if(hasEncoder) {
    		Robot.ss_Logging.addColumn(desc + " currEncPos");
    	}
    	return startIndex;
    }
    
    private void logDefaultMotor(DefaultMotor motor, boolean hasFollower, boolean hasEncoder, int index) {
    	boolean smart = false;
		boolean log = true;
		smartLog(smart, log, index++, 
				"" + motor.getTalonValue(false));
		smartLog(smart, log, index++,
				motor.checkStickyFaults(motor, false));
		smartLog(smart, log, index++, 
				"" + motor.getOutputVoltage(false));
		smartLog(smart, log, index++, 
				"" + motor.getOutputCurrent(false));
		if(hasFollower) {
			smartLog(smart, log, index++,
					motor.checkStickyFaults(motor, true));
			smartLog(smart, log, index++, 
					"" + motor.getOutputVoltage(true));
			smartLog(smart, log, index++, 
					"" + motor.getOutputCurrent(true));
		}
		smartLog(smart, log, index++, 
				"" + motor.getTalonSpeed());
		if(hasEncoder) {
			smartLog(smart, log, index++, 
					"" + motor.getEncPos());
		}
    }
    
    private void smartLog(boolean smart, boolean log, int keyIndex, String value) {
    	if(smart) {
    		SmartDashboard.putString(Robot.ss_Logging.getHeader(keyIndex), value);
    	}
    	if(log) {
    		Robot.ss_Logging.logKeyOutput(keyIndex, value);
    	}
    }
    
    public String runningCommands = "";
    public void logRunningCommands(String commandName) {
    	runningCommands += commandName + div;
    }
    
    public String stoppedCommands = "";
    // hammer is the command that is stopping the stoppedCommand
    public void logStoppedCommands(String hammer, String stoppedCommand) {
    	stoppedCommands += hammer + "STOPPED" + stoppedCommand + div;
    }
}

