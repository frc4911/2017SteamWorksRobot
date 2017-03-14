package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_FeedFuel extends CommandGroup {

    public CG_FeedFuel(boolean reverseHopper) {
    	addParallel(new C_ShooterFeeder());
    	addParallel(new C_HopperSpin(reverseHopper));
    }
}
