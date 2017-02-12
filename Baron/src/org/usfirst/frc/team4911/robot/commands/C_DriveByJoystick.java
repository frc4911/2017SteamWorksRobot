package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DriveByJoystick extends Command {

    public C_DriveByJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("input", Robot.oi.stickL.getY());
    	Robot.ss_DriveTrain.driveTrainLeft.spin(Robot.oi.stickL.getY(), Robot.ss_Config.driveEncoderConstL); //	-1
    	Robot.ss_DriveTrain.driveTrainRight.spin(Robot.oi.stickR.getY(), Robot.ss_Config.driveEncoderConstR); //  1
    	
    	SmartDashboard.putNumber("left cur", Robot.ss_DriveTrain.driveTrainLeft.getCurrent(false));
    	SmartDashboard.putNumber("right cur", Robot.ss_DriveTrain.driveTrainRight.getCurrent(false));
    	
    	SmartDashboard.putNumber("left volt", Robot.ss_DriveTrain.driveTrainLeft.getBusVoltage(false));
    	SmartDashboard.putNumber("right volt", Robot.ss_DriveTrain.driveTrainRight.getBusVoltage(false));
    	
    	SmartDashboard.putNumber("left enc pos", Robot.ss_DriveTrain.driveTrainLeft.getEncPos());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
