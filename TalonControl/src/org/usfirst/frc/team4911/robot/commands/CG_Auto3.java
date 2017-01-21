package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto3 extends CommandGroup {

    public CG_Auto3() {
    	int ta = 5300;
    	addSequential(new C_DrivetrainPID(0,0,0)); //setup pid
    	addSequential(new C_DrivetrainPID(3,25000,25000)); // drive straight
    	addSequential(new C_DrivetrainPID(3,-ta,ta)); // turn around
    	addSequential(new C_DrivetrainPID(3,5000,5000)); // drive forward
    	addSequential(new C_DrivetrainPID(3,ta,-ta)); // turn around
    	addSequential(new C_DrivetrainPID(3,-20000,-20000)); // drive forward
    	addSequential(new C_DrivetrainPID(2,0,0)); // disable PID
    }
}
