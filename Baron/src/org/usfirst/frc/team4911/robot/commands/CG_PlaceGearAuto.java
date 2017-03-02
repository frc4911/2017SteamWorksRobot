package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_PlaceGearAuto extends CommandGroup {
	// gear lift
	final double LIFT_RUNTIME = 1;
	final double LIFT_SPEED = 0.4;
	
	// gear intake
	final double INTAKE_RUNTIME = 0.75;
	final double INTAKE_SPEED = 0.4;
	
    public CG_PlaceGearAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addParallel(new C_MoveMotorByTime(Robot.ss_GearLift, Robot.ss_GearLift.gearLiftMotor, false, LIFT_RUNTIME, LIFT_SPEED));
    	addParallel(new C_MoveMotorByTime(Robot.ss_GearIntake, Robot.ss_GearIntake.gearIntakeMotor, false, INTAKE_RUNTIME, INTAKE_SPEED));
    }
}
