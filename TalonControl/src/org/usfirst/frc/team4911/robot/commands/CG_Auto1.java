package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto1 extends CommandGroup {

    public CG_Auto1() {
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
    	int ta = 5300;
    	addSequential(new C_DrivetrainPID(0,0,0)); //setup pid
    	addSequential(new C_DrivetrainPID(3,10700,10700)); // drive straight
    	addSequential(new C_DrivetrainPID(3,-7846,-7846)); // backup
    	addSequential(new C_DrivetrainPID(3,2170,-2170)); // turn
    	addSequential(new C_DrivetrainPID(3,18000,18000)); // turn
    	addSequential(new C_DrivetrainPID(2,0,0)); // disable PID
    }
}
