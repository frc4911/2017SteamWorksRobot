package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_UpdateLog;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_UpdateLog extends Subsystem {
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateLog());
    }
    
    public void log() {
    	if (Robot.ss_Logging != null){
        	SmartDashboard.putBoolean("SS_Logging present", true);
// PDP
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX3, "" + pdp.getVoltage());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX4, "" + pdp.getTotalCurrent());
    	
// joystick Y values
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX5, "" + Robot.oi.stickL.getY());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX6, "" + Robot.oi.stickR.getY());
    		
// current running command    		
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX9, "" + Robot.ss_DriveTrain.getCurrentCommand());
    		
// driveTrainLeft
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX10, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.get());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX11, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.getOutputVoltage());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX12, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.getOutputCurrent());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX13, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.getSpeed());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX14, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.getEncVelocity());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX15, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.getEncPosition());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX16, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.get());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX17, "" + Robot.ss_DriveTrain.driveTrainLeft.talon.get());
    		
// driveTrainRight
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX18, "" + Robot.ss_DriveTrain.driveTrainRight.talon.get());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX19, "" + Robot.ss_DriveTrain.driveTrainRight.talon.getOutputVoltage());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX20, "" + Robot.ss_DriveTrain.driveTrainRight.talon.getOutputCurrent());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX21, "" + Robot.ss_DriveTrain.driveTrainRight.talon.getSpeed());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX22, "" + Robot.ss_DriveTrain.driveTrainRight.talon.getEncVelocity());
    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX23, "" + Robot.ss_DriveTrain.driveTrainRight.talon.getEncPosition());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX24, "" + Robot.ss_DriveTrain.driveTrainRight.talon.get());
//    		Robot.ss_Logging.logKeyOutput(Robot.ss_Logging.KEYINDEX25, "" + Robot.ss_DriveTrain.driveTrainRight.talon.get());

// logFlush
    		Robot.ss_Logging.logFlush();
    	}
    	else {
    		SmartDashboard.putBoolean("SS_Logging present", false);
    	}
    }
}

