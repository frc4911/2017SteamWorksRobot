package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearPlaceOnSpring extends CommandGroup {
	// gear lift
	final double LIFT_RUNTIME = .5;
	final double LIFT_SPEED = 0.4;
	
	// gear intake
	final double INTAKE_RUNTIME = 0.5;
	final double INTAKE_SPEED = -0.4;
	
    public CG_GearPlaceOnSpring() {
    	addParallel(new C_MoveMotorByTime(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, false, LIFT_RUNTIME, LIFT_SPEED));
    	addParallel(new C_MoveMotorByTime(Robot.ss_GearIntake, Robot.ss_GearIntake.gearIntakeMotor, true, INTAKE_RUNTIME, INTAKE_SPEED));
    }
}
