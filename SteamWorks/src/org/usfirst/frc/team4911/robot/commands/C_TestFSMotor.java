package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_TestFSMotor extends Command {

    public C_TestFSMotor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_TestFreeSpinMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.ss_TestFreeSpinMotor.lMotor.zeroEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.ss_TestFreeSpinMotor.lMotor.spin(0.5, 2000.0, 0.0);
//    	Robot.ss_TestFreeSpinMotor.fsMotor.spin(0.5);
    	Robot.ss_TestFreeSpinMotor.pMotor.spin(-0.5);
    	//Robot.ss_TestFreeSpinMotor.rDTMotorPair.spin(0.5);
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
    	Robot.ss_TestFreeSpinMotor.pMotor.stop();
    }
}
