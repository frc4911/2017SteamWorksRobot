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
	public final String configFilepath = "/C/config.txt";
	
	public double driveMotorConstL;
    public double driveMotorConstR;
    
    public double driveEncoderConstL;
    public double driveEncoderConstR;
    
    public double getEncoderValue(CANTalon encoder, double constant) {
    	return (encoder.getEncPosition() * constant);
    }
    
    public boolean ScannerSetup = false;
    public Scanner setupScanner(String filepath) {
		Scanner read = new Scanner("");
		try {
			read = new Scanner(new File(filepath));
			ScannerSetup = true;
			return read;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			File file = new File(filepath);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
					return Double.parseDouble(temp[1]);
				}
			}
		}
		
		return (double)1;
	}
    
    public void updateInfo() {
    	Robot.ss_Config.driveMotorConstL = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstFL");
    	Robot.ss_Config.driveMotorConstR = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstFR");
    	
    	Robot.ss_Config.driveEncoderConstL = findInfoDouble(Robot.ss_Config.configFilepath, "driveEncoderConstL");
    	Robot.ss_Config.driveEncoderConstR = findInfoDouble(Robot.ss_Config.configFilepath, "driveEncoderConstR");
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new C_UpdateConst());
    }
}

