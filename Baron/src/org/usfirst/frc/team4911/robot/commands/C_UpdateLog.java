package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_UpdateLog extends Command {

	PowerDistributionPanel pdp;
	DriverStation ds;
	
    public C_UpdateLog() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_UpdateLog);
        pdp = new PowerDistributionPanel();
        ds = DriverStation.getInstance();
        
    }

    int counter = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    }

    final String CURR = " current";
    final String ENC = " encoder";
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if((counter++ % 5) == 0) {
  		
    		SmartDashboard.putNumber("Battery volts", pdp.getVoltage());
    		SmartDashboard.putNumber("Battery amps", pdp.getTotalCurrent());
    		SmartDashboard.putNumber("Match time", ds.getMatchTime());
    		
    		// Climber
    		SmartDashboard.putNumber(Robot.ss_Climber.climberMotors.getDescription() + CURR + " leader", Robot.ss_Climber.climberMotors.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_Climber.climberMotors.getDescription() + CURR + " follower", Robot.ss_Climber.climberMotors.getOutputCurrent(true));
    		
    		// DriveTrainLeft
    		SmartDashboard.putNumber(Robot.ss_DriveTrainLeft.leftMotors.getDescription() + CURR + " leader", Robot.ss_DriveTrainLeft.leftMotors.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_DriveTrainLeft.leftMotors.getDescription() + CURR + " follower", Robot.ss_DriveTrainLeft.leftMotors.getOutputCurrent(true));

    		SmartDashboard.putNumber(Robot.ss_DriveTrainLeft.leftMotors.getDescription() + ENC, Robot.ss_DriveTrainLeft.leftMotors.getEncPos());
    		
    		// DriveTrainRight
    		SmartDashboard.putNumber(Robot.ss_DriveTrainRight.rightMotors.getDescription() + CURR + " leader", Robot.ss_DriveTrainRight.rightMotors.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_DriveTrainRight.rightMotors.getDescription() + CURR + " follower", Robot.ss_DriveTrainRight.rightMotors.getOutputCurrent(true));
    		SmartDashboard.putNumber(Robot.ss_DriveTrainRight.rightMotors.getDescription() + ENC, Robot.ss_DriveTrainRight.rightMotors.getEncPos());
    		
    		// Fuel Collector
    		SmartDashboard.putNumber(Robot.ss_FuelCollector.collectorMotors.getDescription() + CURR, Robot.ss_FuelCollector.collectorMotors.getOutputCurrent(false));
    		
    		// Fuel Feeder
    		SmartDashboard.putNumber(Robot.ss_FuelFeeder.feederMotor.getDescription() + CURR, Robot.ss_FuelFeeder.feederMotor.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_FuelFeeder.feederMotor.getDescription() + ENC, Robot.ss_FuelFeeder.feederMotor.getEncPos());
    		
    		// Fuel Hopper
    		SmartDashboard.putNumber(Robot.ss_FuelHopper.hopperMotor.getDescription() + CURR, Robot.ss_FuelHopper.hopperMotor.getOutputCurrent(false));
    		
    		// Fuel Shooter
    		SmartDashboard.putNumber(Robot.ss_FuelShooter.shooterMotors.getDescription() + CURR, Robot.ss_FuelShooter.shooterMotors.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_FuelShooter.shooterMotors.getDescription() + ENC, Robot.ss_FuelShooter.shooterMotors.getEncPos());
    		
    		// Gear Intake
    		SmartDashboard.putNumber(Robot.ss_GearIntake.gearIntakeMotor.getDescription() + CURR, Robot.ss_GearIntake.gearIntakeMotor.getOutputCurrent(false));
    		
    		// Gear Lift
    		SmartDashboard.putNumber(Robot.ss_GearLift.gearLiftMotor.getDescription() + CURR, Robot.ss_GearLift.gearLiftMotor.getOutputCurrent(false));
    		SmartDashboard.putNumber(Robot.ss_GearLift.gearLiftMotor.getDescription() + ENC, Robot.ss_GearLift.gearLiftMotor.getEncPos());
    	}
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
