package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_DriveToAndFrom extends CommandGroup {

    public CG_DriveToAndFrom() {
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
    	
    	addSequential(new C_Drive(true, 2.2));
    	addSequential(new C_Drive(false, 0.35));
    	addSequential(new C_Turn(true, 0.7));
    	addSequential(new C_Drive(true, 1.5));
    	addSequential(new C_Turn(true, 0.15));
    	addSequential(new C_Drive(true, 2.5));
    	addSequential(new C_Turn(true, 0.75));
    }
}
