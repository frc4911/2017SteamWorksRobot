package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_DriveCaleb extends CommandGroup {

    public CG_DriveCaleb() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
    	
        addSequential(new C_DriveToChairsCaleb(1.6, true));
        addSequential(new C_DelayCaleb(1));
        addSequential(new C_TurnCaleb(0.25, false));
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
    }
}