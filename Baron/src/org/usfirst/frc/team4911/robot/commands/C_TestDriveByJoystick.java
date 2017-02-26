package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_TestDriveByJoystick extends Command {

    public C_TestDriveByJoystick() {
        requires(Robot.ss_Climber);
        requires(Robot.ss_DriveTrain);
        requires(Robot.ss_FuelCollector);
        requires(Robot.ss_FuelHopper);
        requires(Robot.ss_FuelShooter);
        requires(Robot.ss_GearIntake);
        requires(Robot.ss_GearLift);
        requires(Robot.ss_Camera);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.ss_UpdateLog.logRunningCommands(this.getName());
    	
    	//invert Y so forward is positive
    	Robot.ss_TestMotor.runMotor(-Robot.oi.autoTestStick.getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ss_TestMotor.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}