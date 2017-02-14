package org.usfirst.frc.team4911.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

import org.usfirst.frc.team4911.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Config extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Scanner read;
	public final String configFilepath = "/c/config.txt";
	
	public double driveMotorConstL;
    public double driveMotorConstR;
    public double gearCollectorConst;
    
    public double driveEncoderConstL;
    public double driveEncoderConstR;
    
    public SS_Config() {
    	updateInfo();
    }
    
    public boolean ScannerSetup = false;
    public Scanner setupScanner(String filepath) {
		Scanner read = new Scanner("");
		try {
			read = new Scanner(new File(filepath));
			ScannerSetup = true;
			return read;
		} catch (FileNotFoundException e) {
			ScannerSetup = false;
			return null;
		}
	}
    
	public double findInfoDouble(String filepath, String tag) {
		String[] temp = new String[2];
		Scanner read = setupScanner(filepath);
		
		if(ScannerSetup) {
			while(read.hasNextLine()) {
				temp = read.nextLine().split(" ");
				if(Objects.equals(tag, temp[0])) {
					if(Objects.equals(temp[1], "-1")) {
						return (double)-1;
					} else {
						return (double)1;
					}
				}
			}
		}
		return (double)1;
	}
    
    public void updateInfo() {
    	driveMotorConstL = findInfoDouble(configFilepath, "driveMotorConstL");
    	driveMotorConstR = findInfoDouble(configFilepath, "driveMotorConstR");
    	gearCollectorConst = findInfoDouble(configFilepath, "gearCollectorConst");
    	
    	driveEncoderConstL = findInfoDouble(configFilepath, "driveEncoderConstL");
    	driveEncoderConstR = findInfoDouble(configFilepath, "driveEncoderConstR");
    }
    
    public String getTalonConstants(String divider) {
    	return "driveMotorConstL" + driveMotorConstL + divider +
    			"driveMotorConstR" + driveMotorConstR + divider +
    			"gearCollectorConst" + gearCollectorConst + divider +
    			"driveEncoderConstL" + driveEncoderConstL + divider +
    			"driveEncoderConstR" + driveEncoderConstR;
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_UpdateConst());
    }
}

