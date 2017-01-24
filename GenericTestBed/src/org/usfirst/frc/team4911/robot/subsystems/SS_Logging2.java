package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SS_Logging2 {

//	final String path1 = "c:\\workspace\\";
//	final String path2 = "c:\\workspace\\";
	final String path1 = "/u/";
	final String path2 = "/v/";
	final String path3 = "/w/";
	final String path4 = "/x/";
	final String path5 = "/y/";
	final String path6 = "/z/";
	
	double currentTime (){
		return Timer.getFPGATimestamp();
	}
	String[] headers = {
			"running time",       // 0
			"delta time",         // 1
			"index",              // 2
			"motor1 power",
			"motor2 power",
			"motor3 power",
			"motor4 power",
			};
	Key[] keys = new Key[headers.length];
	
	final int KEYINDEX0 = 0;
	final int KEYINDEX1 = 1;
	final int KEYINDEX2 = 2;
	final int KEYINDEX3 = 3;
	final int KEYINDEX4 = 4;
	final int KEYINDEX5 = 5;
	final int KEYINDEX6 = 6;
	final int KEYINDEX7 = 7;
	final int KEYINDEX8 = 8;
	final int KEYINDEX9 = 9;
	final int KEYINDEX10 = 10;
	final int KEYINDEX11 = 11;
	final int KEYINDEX12 = 12;
	final int KEYINDEX13 = 13;
	final int KEYINDEX14 = 14;
	final int KEYINDEX15 = 15;
	final int KEYINDEX16 = 16;
	final int KEYINDEX17 = 17;
	final int KEYINDEX18 = 18;
	final int KEYINDEX19 = 19;
	final int KEYINDEX20 = 20;
	final int KEYINDEX21 = 21;
	final int KEYINDEX22 = 22;
	final int KEYINDEX23 = 23;
	final int KEYINDEX24 = 24;
	final int KEYINDEX25 = 25;
	final int KEYINDEX26 = 26;
	final int KEYINDEX27 = 27;
	final int KEYINDEX28 = 28;
	final int KEYINDEX29 = 29;
	final int KEYINDEX30 = 30;
	
	double startTime;
	int counter = 0;
    RandomAccessFile raf = null;
    boolean headersUpdated;
    boolean fileCreationFailure = false;
    String logfileName;
	final String MODE = "rw";
	final int MINFILENUM = 10;
	final int MAXFILENUM = 30;
	final String EXTENSION = ".csv";

    public SS_Logging2(){
    	for (int i=0; i<keys.length; i++){
    		keys[i] = new Key(headers[i]);
    	}
    	lastTime = startTime = currentTime();
    	headersUpdated = true;
    	systemLogger();
    }
    
    int lineCount = 0;
    double lastTime;
    void systemLogger(){
    	double currentTime = currentTime();
    	
    	logKeyOutput(KEYINDEX0,""+(currentTime-startTime));
    	logKeyOutput(KEYINDEX1,""+(currentTime-lastTime));
    	logKeyOutput(KEYINDEX2,""+lineCount++);
    	
    	//SmartDashboard.putNumber("lineCount", lineCount);
    	lastTime = currentTime;
    }
    
    public void logKeyOutputDouble(int index, double num, int trim) {
    	String value = "" + Math.floor(num*trim)/trim; 
    	logKeyOutput(index, value);
    }

    public void logKeyOutput(int index, String value) {
		if (value.equals(keys[index].value) == false){
			keys[index].value = value;
			keys[index].flushed = false;
		}
    }

    String findNewFile(String path){
    	
    	int fileNum = MINFILENUM;
    	String baseFilename = path+"logfile";
    	String newFilename;
    	String nextFilename;
    	boolean fileFound = false;
    	File file;
    	
    	file = new File(path);
    	if (file.isDirectory()==false)
			return null;
		
		do {
    		newFilename = baseFilename+fileNum+EXTENSION;
    		if (++fileNum == MAXFILENUM)
    			fileNum = MINFILENUM;
    		nextFilename = baseFilename+fileNum+EXTENSION;
    		
    		file = new File(newFilename);
    		if (file.exists() == false){
    			file = new File(nextFilename);
    			if (file.exists())
    				file.delete();
    			fileFound = true;
    		}
    	}while (fileNum > MINFILENUM && fileFound == false);
    	
    	if (fileFound == false){
    		newFilename = baseFilename+(MINFILENUM+1)+EXTENSION;
    		file = new File(newFilename);
    		file.delete();
    		newFilename = baseFilename+MINFILENUM+EXTENSION;
    		file = new File(newFilename);
    		file.delete();
    	}
    	
    	return newFilename;
    }
    
    void writeKeyHeaders(){
    	if (raf == null)
    		return;
    	
    	String tmpHeaders = "";
    	
    	for (int i=0; i<headers.length; i++){
    		tmpHeaders+= headers[i]+",";
    	}
    	tmpHeaders += "\r\n";
    	
		try {
//			long currentFilePos = raf.getFilePointer();
//			raf.seek(0);
			raf.write(tmpHeaders.getBytes());
		} catch (IOException x) {
		    System.out.println("I/O Exception: " + x);
		}
		headersUpdated = false;
    }    

    void writeKeyKeys(){
    	if (raf == null)
    		return;
    	
    	String line = "";
    	
    	for (int i=0; i<keys.length; i++){
    		if (!keys[i].flushed)
    		{
    			line += keys[i].value+",";
    			keys[i].flushed = true;
    		}
    		else
    			line += ",";
    	}
    	line += "\r\n";
    	
		try {
			raf.write(line.getBytes());
		} catch (IOException x) {
		    System.out.println("I/O Exception: " + x);
		}
    }

    boolean initLogFile(){
    	fileCreationFailure = false;
    	
    	logfileName = findNewFile(path1); 

    	if (logfileName == null){
    		logfileName = findNewFile(path2);
    	}
    	
    	if (logfileName == null){
    		logfileName = findNewFile(path3);
    	}
    	
    	if (logfileName == null){
    		logfileName = findNewFile(path4);
    	}
    	
    	if (logfileName == null){
    		logfileName = findNewFile(path5);
    	}
    	
    	if (logfileName == null){
    		logfileName = findNewFile(path6);
    	}
    	
		if (logfileName == null){
			//SmartDashboard.putBoolean("fileCreationFailure", true);
			fileCreationFailure = true;
			return fileCreationFailure;
		}
		
		if (logfileName != null){
			//SmartDashboard.putBoolean("NewFileCreated", true);
		}

		File file = new File(logfileName);

		try {
			raf = new RandomAccessFile(file, MODE);
			//raf.seek(0);
		} catch (IOException x) {
		    System.out.println("I/O Exception: " + x);
		}
		
    	if (raf == null){
    		fileCreationFailure = true;
    	}
    	
    	return fileCreationFailure;
    }

    public void logFlush() {
    	
		if (fileCreationFailure)
			return;
		
		if (raf == null){
			if (initLogFile() == false)
				return;
		}
		
		if (headersUpdated){
			writeKeyHeaders();
		}
		systemLogger();
		writeKeyKeys();
    }
}

class Key{
	String key;
	String value;
	boolean flushed;
	
	Key (String pKey){
		key = pKey;
		value = "uninitialized";
		flushed = true;
	}
}