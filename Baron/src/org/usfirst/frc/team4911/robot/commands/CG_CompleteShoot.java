package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_CompleteShoot extends CommandGroup {

    public CG_CompleteShoot() {
        addParallel(new C_SpinFlywheel());
        addSequential(new C_Delay(1.0));
        addSequential(new CG_FeedFuel(true));
    }
}
