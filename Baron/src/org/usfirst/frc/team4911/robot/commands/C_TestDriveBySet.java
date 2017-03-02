package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_TestDriveBySet extends Command {

    public C_TestDriveBySet() {
        requires(Robot.ss_Climber);
        requires(Robot.ss_DriveTrainLeft);
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
    	
    	Robot.ss_TestMotor.runMotor();
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