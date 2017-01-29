package org.usfirst.frc.team4911.robot.commands;

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
    	
    	addSequential(new C_Drive(true, 2));
    	addSequential(new C_TestDriveTrain("forward"));
    	addSequential(new C_Drive(false, 2));
    	addSequential(new C_TestDriveTrain("backwards"));
    	addSequential(new C_MoveShooter(false));
    	addSequential(new C_TestShooter("forwards"));
    	addSequential(new C_MoveArm(false, SmartDashboard.getBoolean("Shooter Test forwards", false)));
//    	addSequential(new C_MoveShooter(true));
//    	addSequential(new C_MoveArm(true));
//    	addSequential(new C_TriggerShooterPlunger(true));
//    	addSequential(new C_Delay(1));
//    	addSequential(new C_TriggerShooterPlunger(false));
    }
}
