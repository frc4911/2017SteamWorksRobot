package org.usfirst.frc.team4911.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Config extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public final String configFilepath = "/c/config.txt";
	
	public double driveMotorConstL = 0;
    public double driveMotorConstR = 0;
    public double gearCollectorConst = 0;
    
    public double driveEncoderConstL = 0;
    public double driveEncoderConstR = 0;
    
    public SS_Config() {
    	Scanner read = setUpScanner(configFilepath);

    	if(read != null) {
	    	updateInfo(readConfigFile(read));
    	}
    }
    
    public Scanner setUpScanner(String filepath) {
    	Scanner read = null;
		try {
			read = new Scanner(new File(filepath));
		} catch (FileNotFoundException e) {
		}
		return read;
	}
    
    public ArrayList<String> readConfigFile(Scanner read) {
    	ArrayList<String> contents = new ArrayList<String>(24);
    	
    	while(read.hasNextLine()) {
    		contents.add(read.nextLine());
    	}
    	
    	return contents;
    }
    
    public double findInfoDouble(ArrayList<String> contents, String tag) {
    	for(int i = 0; i < contents.size(); i++) {
    		String[] tmp = contents.get(i).split(" ");
    		if(Objects.equals(tag, tmp[0])) {
				if(Objects.equals(tmp[1], "-1")) {
					return (double)-1;
				} else {
					return (double)9001;
				}
			}
    	}
    	return (double)2;
    }
    
    public void updateInfo(ArrayList<String> contents) {
    	driveMotorConstL = findInfoDouble(contents, "driveMotorConstL");
    	driveMotorConstR = findInfoDouble(contents, "driveMotorConstR");

    	gearCollectorConst = findInfoDouble(contents, "gearCollectorConst");
    	
    	driveEncoderConstL = findInfoDouble(contents, "driveEncoderConstL");
    	driveEncoderConstR = findInfoDouble(contents, "driveEncoderConstR");
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

