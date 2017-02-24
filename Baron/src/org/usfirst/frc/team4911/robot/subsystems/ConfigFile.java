package org.usfirst.frc.team4911.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConfigFile extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public final String configFilepath = "/c/config.txt";
	
	public double driveMotorsLeftConst = 1;
    public double driveMotorsRightConst = 1;
    public double gearIntakeConst = 1;
    public double gearLiftConst = 1;
    public double hopperConst = 1;
    public double climberConst = 1;
    public double shooterFlywheelConst = 1;
    public double shooterFeederConst = 1;
    public double fuelCollectorConst = 1;
    
    public double driveEncoderLeftConst = 1;
    public double driveEncoderRightConst = 1;
    public double climberEncoderConst = 1;
    public double shooterEncoderConst = 1;
    
    public ConfigFile() {
    	Scanner read = setUpScanner(configFilepath);

    	if(read != null) {
	    	updateInfo(readConfigFile(read));
    	}
    }
    
    public boolean ScannerSetup = false;
    public Scanner setUpScanner(String filepath) {
		Scanner read = new Scanner("");
		try {
			read = new Scanner(new File(filepath));
		} catch (FileNotFoundException e) {
			ScannerSetup = false;
			return null;
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
					return (double)1;
				}
			}
    	}
    	return (double)1;
    }
    
    public void updateConfigFile(String filepath) {
    	try {
			PrintStream write = new PrintStream(new File(filepath));
			write.println("driveMotorsLeftConst 1");
			write.println("driveMotorsRightConst 1");
			write.println("gearIntakeConst 1");
			write.println("driveEncoderLeftConst 1");
			write.println("driveEncoderRightConst 1");
			write.println("gearLiftConst 1");
			write.println("hopperConst 1");
			write.println("climberConst 1");
			write.println("shooterFlywheelConst 1");
			write.println("shooterFeederConst 1");
			write.println("fuelCollectorConst 1");
			write.println("climberEncoderConst 1");
			write.println("shooterEncoderConst 1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void updateInfo(ArrayList<String> contents) {
    	driveMotorsLeftConst = findInfoDouble(contents, "driveMotorsLeftConst");
    	driveMotorsRightConst = findInfoDouble(contents, "driveMotorsRightConst");

    	gearIntakeConst = findInfoDouble(contents, "gearIntakeConst");
    	
    	driveEncoderLeftConst = findInfoDouble(contents, "driveEncoderLeftConst");
    	driveEncoderRightConst = findInfoDouble(contents, "driveEncoderRightConst");
    	
    	
        gearLiftConst = findInfoDouble(contents, "gearLiftConst");
        hopperConst = findInfoDouble(contents, "hopperConst");
        climberConst = findInfoDouble(contents, "climberConst");
        shooterFlywheelConst = findInfoDouble(contents, "shooterFlywheelConst");
        shooterFeederConst = findInfoDouble(contents, "shooterFeederConst");
        fuelCollectorConst = findInfoDouble(contents, "fuelCollectorConst");
        
        climberEncoderConst = findInfoDouble(contents, "climberEncoderConst");
        shooterEncoderConst = findInfoDouble(contents, "shooterEncoderConst");

    }
    
    public String getTalonConstants(String divider) {
    	return "driveMotorsLeftConst" + driveMotorsLeftConst + divider +
    			"driveMotorsRightConst" + driveMotorsRightConst + divider +
    			"gearIntakeConst" + gearIntakeConst + divider +
    			"driveEncoderLeftConst" + driveEncoderLeftConst + divider +
    			"driveEncoderRightConst" + driveEncoderRightConst + divider +
    			"gearLiftConst" + gearLiftConst + divider +
    			"hopperConst" + hopperConst + divider +
    			"climberConst" + climberConst + divider +
    			"shooterFlywheelConst" + shooterFlywheelConst + divider +
    			"shooterFeederConst" + shooterFeederConst + divider +
    			"fuelCollectorConst" + fuelCollectorConst + divider +
    			"climberEncoderConst" + climberEncoderConst + divider +
    			"shooterEncoderConst" +shooterEncoderConst;
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_UpdateConst());
    }
}

