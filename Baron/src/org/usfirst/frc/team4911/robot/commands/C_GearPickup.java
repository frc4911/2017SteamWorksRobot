package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class C_GearPickup extends Command {

    public C_GearPickup() {
        requires(Robot.ss_DriveTrainLeft);
        requires(Robot.ss_DriveTrainRight);
        requires(Robot.ss_GearIntake);
    }
    
    double drivetrainSpeed = -.774; // -.3	   -.774	-.9677
    double gearIntakeSpeed = .8; 	// .31 	   .8		1.0

    protected void initialize() {
    	/*double tmp = SmartDashboard.getNumber("gear pickup drivetrain speed",-999);
    	if (tmp == -999){
    		SmartDashboard.putNumber("gear pickup drivetrain speed",drivetrainSpeed);
    	}
    	else {
    		drivetrainSpeed = tmp;
    	}
    	
    	tmp = SmartDashboard.getNumber("gear pickup intake speed",-999);
    	if (tmp == -999){
    		SmartDashboard.putNumber("gear pickup intake speed",gearIntakeSpeed);
    	}
    	else {
    		gearIntakeSpeed = tmp;
    	}*/
    }

    protected void execute() {
    	Robot.ss_DriveTrainLeft.leftMotors.spin(drivetrainSpeed);
    	Robot.ss_DriveTrainRight.rightMotors.spin(drivetrainSpeed);
    	Robot.ss_GearIntake.gearIntakeMotor.spin(gearIntakeSpeed);
    }

    protected boolean isFinished() {
    	if(Robot.ss_GearIntake.gearIntakeMotor.getOutputCurrent(false) > Robot.ss_GearIntake.currentThreshold) {
    		Robot.ss_GearIntake.gearIn = true;
        	SmartDashboard.putBoolean("gearIn", Robot.ss_GearIntake.gearIn);
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.ss_DriveTrainLeft.leftMotors.stop();;
    	Robot.ss_DriveTrainRight.rightMotors.stop();
    	Robot.ss_GearIntake.gearIntakeMotor.stop();
    }

    protected void interrupted() {
    	end();
    }
}
