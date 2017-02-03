//package org.usfirst.frc.team4911.robot.commands;
//
//import org.usfirst.frc.team4911.robot.Robot;
//
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// *
// */
//public class C_DriveByPID extends Command {
//	double pos;
//	
//	double curTime;
//	double endTime;
//
//    public C_DriveByPID() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.ss_DriveTrain);
//        SmartDashboard.putNumber("Code Target Pos", -1);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	pos = SmartDashboard.getNumber("Target Position", 0);
//    	Robot.ss_DriveTrain.startClosedLoop();
//    	SmartDashboard.putNumber("Code Target Pos", pos);
//    	Robot.ss_DriveTrain.driveByPID(pos);
//    	
//    	endTime = Timer.getFPGATimestamp() + 10;
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	SmartDashboard.putNumber("Cur Pos", (Robot.ss_DriveTrain.DriveMotorFrontLeft.getPosition() * 1440));
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        if(((Robot.ss_DriveTrain.DriveMotorFrontLeft.getPosition() * 1440) >= pos) || (Timer.getFPGATimestamp() >= endTime)) {
//        	SmartDashboard.putBoolean("End Command", true);
//        	return true;
//        } else {
//        	SmartDashboard.putBoolean("End Command", false);
//        	return false;
//        }
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.ss_DriveTrain.endClosedLoop();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
