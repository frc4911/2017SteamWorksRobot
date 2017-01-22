package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoAll extends CommandGroup {

    public CG_AutoAll() {
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
    	
    	addSequential(new C_DrivetrainPID(0,0,0)); //setup pid
    	addSequential(new C_DrivetrainPID(3, 11070, 10920));
    	addSequential(new C_DrivetrainPID(3, -2930, -3000));
    	addSequential(new C_DrivetrainPID(3, 1990, -1130));
    	addSequential(new C_DrivetrainPID(3, 8030, 8100));
    	addSequential(new C_DrivetrainPID(3, 1900, -345));
    	addSequential(new C_DrivetrainPID(3, 12100, 12050));
    	addSequential(new C_DrivetrainPID(3, -3050, -3400));
    	addSequential(new C_DrivetrainPID(3, 2355, -2170));
    	addSequential(new C_DrivetrainPID(2,0,0)); // disable PID
    }
}
