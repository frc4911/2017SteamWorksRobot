package org.usfirst.frc.team4911.robot.subsystems;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.text.html.parser.AttributeList;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_UpdateLog;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_UpdateLog extends Subsystem {
	private DriverStation ds = DriverStation.getInstance();
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateLog());
    }
    
    public String driveMode() {
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
    
    
    int driveModeIndex = 0;
    int fmsConnectionIndex = 0;
    int brownoutIndex = 0;
    
    int pdpVoltIndex = 0;
    int pdpCurrentIndex = 0;
    int leftJoystickYIndex = 0;
    int rightJoystickYIndex = 0;
    
    int dtLSpeedIndex = 0;
    int dtLTalonVoltageIndex = 0;
    int dtLTalonCurrentIndex = 0;
    int dtLFTalonVoltageIndex = 0;
    int dtLFTalonCurrentIndex = 0;
    int dtLRPMIndex = 0;
    int dtLEncVelIndex = 0;
    int dtLEncPosIndex = 0;
    
    int dtRSpeedIndex = 0;
    int dtRTalonVoltageIndex = 0;
    int dtRTalonCurrentIndex = 0;
    int dtRFTalonVoltageIndex = 0;
    int dtRFTalonCurrentIndex = 0;
    int dtRRPMIndex = 0;
    int dtREncVelIndex = 0;
    int dtREncPosIndex = 0;
    
    public SS_UpdateLog() {
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
    	dtLSpeedIndex = addMotorIndices(Robot.ss_DriveTrain.driveTrainLeft.getDescription());
    	
// driveTrainRight
    	dtRSpeedIndex = addMotorIndices(Robot.ss_DriveTrain.driveTrainRight.getDescription());
    }

    public void log() {
    	if (Robot.ss_Logging != null){

    		SmartDashboard.putBoolean("SS_Logging present", true);

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
    		
// current running command    		
//    		smartLog(false, true, Robot.ss_Logging.KEYINDEX9, "" + Robot.ss_DriveTrain.getCurrentCommand());
    		
// driveTrainLeft
    		logDefaultMotor(Robot.ss_DriveTrain.driveTrainLeft, dtLSpeedIndex);
    		
// driveTrainRight
    		logDefaultMotor(Robot.ss_DriveTrain.driveTrainRight, dtRSpeedIndex);

// flush
    		Robot.ss_Logging.logFlush();
    	}
    	else {
    		SmartDashboard.putBoolean("SS_Logging present", false);
    	}
    }
    
    public int addMotorIndices(String desc) {
    	int speedIndex = Robot.ss_Logging.addColumn(desc + " speed");
    	Robot.ss_Logging.addColumn(desc + " TalonVoltage");
    	Robot.ss_Logging.addColumn(desc + " TalonCurrent");
    	Robot.ss_Logging.addColumn(desc + " FTalonVoltage");
    	Robot.ss_Logging.addColumn(desc + " FTalonCurrrent");
    	Robot.ss_Logging.addColumn(desc + " RPM");
    	Robot.ss_Logging.addColumn(desc + " encVel");
    	Robot.ss_Logging.addColumn(desc + " currEncPos");
    	return speedIndex;
    }
    
    public void logDefaultMotor(DefaultMotor motor, int index) {
    	boolean smart = false;
		boolean log = true;
		smartLog(smart, log, index++, 
				"" + motor.getTalonValue(false));
		smartLog(smart, log, index++, 
				"" + motor.getOutputVoltage(false));
		smartLog(smart, log, index++, 
				"" + motor.getOutputCurrent(false));
		smartLog(smart, log, index++, 
				"" + motor.getOutputVoltage(true));
		smartLog(smart, log, index++, 
				"" + motor.getOutputCurrent(true));
		smartLog(smart, log, index++, 
				"" + motor.getTalonSpeed());
		smartLog(smart, log, index++, 
				"" + motor.getEncVelocity());
		smartLog(smart, log, index++, 
				"" + motor.getEncPos());
    }
    
    public void smartLog(boolean smart, boolean log, int keyIndex, String value) {
    	if(smart) {
    		SmartDashboard.putString(Robot.ss_Logging.getHeader(keyIndex), value);
    	}
    	if(log) {
    		Robot.ss_Logging.logKeyOutput(keyIndex, value);
    	}
    }
}

