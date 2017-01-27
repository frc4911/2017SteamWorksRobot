package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.commands.C_CurCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Commands extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public int numCommand = 0;
	public String[] commandNames = new String[20];
	public String curCommand = "";
	
	public void startCommand(String name) {
		curCommand = name;
		SmartDashboard.putBoolean(name, true);
	}
	
	public void endCommand() {
		curCommand = "null";
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_CurCommand());
    }
}

