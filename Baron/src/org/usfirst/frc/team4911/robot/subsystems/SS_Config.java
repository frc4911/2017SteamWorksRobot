package org.usfirst.frc.team4911.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_UpdateConst;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Config extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Scanner read;
	public String configFilepath = "/config.txt";
	
	public double driveMotorConstFL;
    public double driveMotorConstFR;
    public double driveMotorConstRL;
    public double driveMotorConstRR;
    
    public double driveEncoderConstL;
    public double driveEncoderConstR;
    
    public double getEncoderValue(CANTalon encoder, double constant) {
    	return (encoder.getEncPosition() * constant);
    }
    
    public Scanner setupScanner(String filepath) {
		boolean throwError = true;
		Scanner read = new Scanner("");
		while(throwError) {
			try {
				read = new Scanner(new File(filepath));
				throwError = false;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				File file = new File(filepath);
				throwError = true;
			}
		}
		
		return read;
	}
	
	public double findInfoDouble(String filepath, String tag) {
		String[] temp = new String[2];
		Scanner read = setupScanner(filepath);
		
		while(read.hasNextLine()) {
			temp = read.nextLine().split(" ");
			
			if(Objects.equals(tag, temp[0])) {
				return Double.parseDouble(temp[1]);
			}
		}
		
		return (double)1;
	}
	
	public int findInfoInt(String filepath, String tag) {
		String[] temp = new String[2];
		Scanner read = setupScanner(filepath);
		
		while(read.hasNextLine()) {
			temp = read.nextLine().split(" ");
			
			if(Objects.equals(tag, temp[0])) {
				return Integer.parseInt(temp[1]);
			}
		}
		
		return (int)1;
	}
    
    public void updateInfo() {
    	Robot.ss_Config.driveMotorConstFL = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstFL");
    	Robot.ss_Config.driveMotorConstFR = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstFR");
    	Robot.ss_Config.driveMotorConstRL = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstRL");
    	Robot.ss_Config.driveMotorConstRR = findInfoDouble(Robot.ss_Config.configFilepath, "driveMotorConstRR");
    	
    	Robot.ss_Config.driveEncoderConstL = findInfoDouble(Robot.ss_Config.configFilepath, "driveEncoderConstL");
    	Robot.ss_Config.driveEncoderConstR = findInfoDouble(Robot.ss_Config.configFilepath, "driveEncoderConstR");
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateConst());
    }
}

