package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LoggingEngine {

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
	
	public String getHeader(int keyIndex) {
		return indivHeaders.get(keyIndex);
	}
	
	String allHeaders = "";
	int headerCount = 0;
	ArrayList<String> indivHeaders = new ArrayList(100);
	ArrayList<Key> indivKeys = new ArrayList(100); 
	public int addColumn(String header) {
		allHeaders += header + ",";
		indivHeaders.add(header);
		indivKeys.add(new Key());
		return headerCount++;
	}
	
	double startTime;
	int counter = 0;
    RandomAccessFile raf = null;
    boolean headersWritten;
    boolean fileCreationFailure = false;
    String logfileName;
	final String MODE = "rw";
	final int MINFILENUM = 10;
	final int MAXFILENUM = 30;
	final String EXTENSION = ".csv";

	int lineCountIndex = 0;
	int runningTimeIndex = 0;
	int deltaTimeIndex = 0;
	int matchTimeIndex = 0;
    public LoggingEngine(){
    	lastTime = startTime = currentTime();
    	headersWritten = false;
    	lineCountIndex = addColumn("lineCount");
    	runningTimeIndex = addColumn("runningTime");
    	deltaTimeIndex = addColumn("deltaTime");
    	matchTimeIndex = addColumn("matchTime");
    	systemLogger();
    }
    
    int lineCount = 0;
    double lastTime;
    void systemLogger(){
    	double currentTime = currentTime();
    	
       	logKeyOutput(lineCountIndex,""+lineCount++);
    	logKeyOutput(runningTimeIndex,""+(currentTime-startTime));
    	logKeyOutput(deltaTimeIndex,""+(currentTime-lastTime));
    	logKeyOutput(matchTimeIndex,""+Timer.getMatchTime());
    	lastTime = currentTime;
    }
    
    public void logKeyOutputDouble(int index, double num, int trim) {
    	String value = "" + Math.floor(num*trim)/trim; 
    	logKeyOutput(index, value);
    }

    public void logKeyOutput(int index, String value) {
    	Key key = indivKeys.get(index);
		if (!value.equals(key.value)){
			key.value = value;
			key.needsToBeFlushed = true;
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
    
    void writeHeaders(){
    	if (raf == null)
    		return;
    	
    	String tmpHeaders = allHeaders + "\r\n";
    	
		try {
			raf.write(tmpHeaders.getBytes());
		} catch (IOException x) {
		    System.out.println("I/O Exception: " + x);
		}
		headersWritten = true;
    }    

    void writeLine(){
    	if (raf == null)
    		return;
    	
    	String line = "";
    	
    	for (int i=0; i<indivKeys.size(); i++){
    		Key key = indivKeys.get(i);
    		if (key.needsToBeFlushed)
    		{
    			line += key.value+",";
    			key.needsToBeFlushed = false;
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

    boolean createLogFile(){
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
			SmartDashboard.putBoolean("fileCreationFailure", true);
			fileCreationFailure = true;
			return fileCreationFailure;
		}
		
		if (logfileName != null){
			SmartDashboard.putBoolean("fileCreationFailure", false);
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
			if (createLogFile() == false)
				return;
		}
		
		if (!headersWritten){
			writeHeaders();
		}
		systemLogger();
		writeLine();
    }
}

class Key{
	String value;
	boolean needsToBeFlushed;
	
	Key (){
		value = "uninitialized";
		needsToBeFlushed = false;
	}
}