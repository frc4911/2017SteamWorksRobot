package org.usfirst.frc.team4911.robot.commands;

import java.util.Objects;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_AutoTest extends CommandGroup {
    public CG_AutoTest() {
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
    	
    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainLeft, true, 10500, 2));
    	addSequential(new C_TestMotorEncoder(Robot.ss_DriveTrain, Robot.ss_DriveTrain.driveTrainRight, true, 10500, 2));
    	
    }
}
