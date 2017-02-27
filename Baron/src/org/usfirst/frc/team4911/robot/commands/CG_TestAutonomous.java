package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_TestAutonomous extends CommandGroup {

    public CG_TestAutonomous() {
    	//double targetDist, int ticksPerRev, int encoderTicksPerRev, double kp, double kd, double ki, double kf, int iZone, double peakOutputVoltage
        addSequential(new C_MoveToPosInInches(71.5, 1024, 256, 0.5, 0.0, 0.0, 0.0, 0, 12.0));
    }
}
