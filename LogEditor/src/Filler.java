import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Filler {
	public static BufferedReader reader = null;
	public static BufferedWriter writer;
	public static boolean makeWriter = true;
	
	public static void main(String[] args) {
		String currDir = System.getProperty("user.dir");
		String fileExtension = ".csv";
		File directory = new File(currDir);
		
		if(directory.exists()) {
			File[] files = findFiles(currDir, fileExtension);
			
			for(int i = 0; i < files.length; i++) {
				File file = findCounterpart(files[i], currDir);
				if(file != null) {
					if(!(file.exists())) {
						changeFile(files[i], file);
					}
				}
			}
		}
	}
	
	public static File[] findFiles(String dirName, String fileExtension){
        File dir = new File(dirName);
        
        if(dir.exists()) {
	        File[] files = dir.listFiles();
	        int numFiles = files.length;
	        
	        String[] tmp = new String[numFiles];
	        int numCSV = 0;
	        for(int i = 0; i < numFiles; i++) {
	        	tmp[i] = files[i].toString();
	        	if(tmp[i].toLowerCase().contains(fileExtension)) {
	        		numCSV++;
	        	}
	        }
	        
	        File[] csvFiles = new File[numCSV];
	        int j = 0;
	        for(int i = 0; i < numFiles; i++) {
	        	tmp[i] = files[i].toString();
	        	if(tmp[i].toLowerCase().contains(fileExtension)) {
	        		csvFiles[j] = new File(tmp[i]);
	        		j++;
	        	}
	        }
	     
	        return csvFiles;
        }
        System.out.println(dir + " does not exist");
        
        return null;
    }
	
	public static File findCounterpart(File file1, String dir) {
		File file2 = null;
		Boolean full = false;
		if(!file1.toString().contains("full")) {//else {
			String tmp = "";
			String[] temp;
			
			tmp = changeChar(file1.toString(), '\\', '-');
			dir = changeChar(dir, '\\', '-');
			
			temp = tmp.split(dir);
			
			String newFile;
			newFile = dir + '\\' + "full" + arrayToString(temp).substring(1);

			file2 = new File(changeChar(newFile, '-', '\\'));
		}
		if (file2 != null) {
			if (!(file1.exists() && file2.exists()) && full == false) {
				if(file1.exists()) {
					return file2;
				}
			}
		}
		return null;
	}

	public static String changeChar(String str, char oldChar, char newChar) {
		char[] charArray = str.toCharArray();
		str = "";
		
		for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] == oldChar) {
				charArray[i] = newChar;
			}
			str += charArray[i];
		}
		
		return str;
	}

	public static void changeFile(File oldFile, File newFile) {		
		try {
			reader = new BufferedReader(new FileReader(oldFile));
			writer = new BufferedWriter(new FileWriter(newFile));
			fillLines();
		} 
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void fillLines() {
		String tmp = "";
		
		tmp = readNextLine();
		writeNewFile(tmp);
		int numCol = tmp.split(",").length;
		
		String[] line = validateLength(readNextLine().split(","), numCol);
		
		for(int i = 0; i < numCol; i++) {
			if(Objects.equals(line[i], "")) {
				line[i] = "null";
			}			
			if(line[i].length() > 0) {
				if(!(Objects.equals(line[i].substring(line[i].length() - 1), ","))) {
					line[i] += ",";
				}
			}
		}
		writeNewFile(arrayToString(line));
		
		String[] nextLine;
		while((tmp = readNextLine()) != null) {
			nextLine = validateLength(tmp.split(","), line.length);
			
			for(int i = 0; i < numCol; i++) {
				if(Objects.equals(nextLine[i], "")) {
					nextLine[i] = line[i];
				}				
				if(line[i].length() > 0) {
					if(!(Objects.equals(nextLine[i].substring(nextLine[i].length() - 1), ","))) {
						nextLine[i] += ",";
					}
				}
			}
			
			line = nextLine;
			writeNewFile(arrayToString(line));
		}
		
		try {
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeNewFile(String line) {
		try {
			if(line != null) {
				writer.write(line);
				writer.newLine();
			}
			writer.flush();
		} 
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readNextLine() {
		String tmp = "";
		
		try {
			tmp = reader.readLine();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
		return tmp;
	}
	
 	public static String[] validateLength(String[] str, int numCol) {
		// checks to see if the array is
		// a valid lengths
		if(str.length < numCol) {
			String[] tmpArray = str;
			str = new String[numCol];
			
			for(int i = 0; i < numCol; i++) {
				if(i < tmpArray.length) {
					str[i] = tmpArray[i];
				}
				else {
					str[i] = "";
				}
			}
		}
		return str;
	}

 	public static String arrayToString(String[] array) {
 		String str = "";
 		for(int i = 0; i < array.length; i++) {
 			str += array[i];
 		}
 		return str;
 	}
}
