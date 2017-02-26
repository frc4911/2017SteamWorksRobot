//package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;

import org.opencv.core.Core;
//import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
//import edu.wpi.first.wpilibj.networktables.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

// be sure to copy opencv_ffmpegxxx_64 to c:\windows\system32 or "new VideoCapture()" will fail for ip cameras (local webcam works fine w/o it).

public class CameraRun {
	final int camNum = 0;
	
	VideoCapture camera;
	Mat mat;
	JFrame frame;
	//String videoStreamAddress = "http://FRC:FRC@10.49.11.11/mjpg/video.mjpg";//"http://root:password@10.36.63.100/mjpg/video.mjpg";
	String videoStreamAddress = "http://roborio-4911-frc.local:1181/?action=stream";
	//String videoStreamAddressUSB = something "http://roborio-3663FRC.local";
//	String videoStreamAddress2 = "http://root:password@10.36.63.100/mjpg/video.mjpg";
//	String videoStreamAddress3 = "http://root:password@169.254.199.100/mjpg/video.mjpg";
	String streamAddress = videoStreamAddress;
	
	ImageIcon image;//, imageTest;
	BufferedImage buffImg;
	JLabel label;
//	NetworkTable table;
			
	public void CameraFirstInit()
	{		
		frame = new JFrame("Dog's Eyes <o> . <o>");
		label = new JLabel();
		buffImg = null;
	}
	
	public void CameraInit()
	{		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		System.out.println(streamAddress);
		
//		camera = new VideoCapture(streamAddress);//0 - USB Cam....?
//		camera = new VideoCapture(camNum);
		camera = new VideoCapture(0);
		if (camera.isOpened())
			System.out.println("found camera");
		else
			System.out.println("did not find camera");
		
		mat = new Mat();
		
//		NetworkTable.setClientMode();
//		NetworkTable.setIPAddress("10.49.11.25");//"169.254.199.6");//"10.36.63.20");//78");
//		table = NetworkTable.getTable("SmartDashboard");
//		if (table.isConnected())
//			System.out.println("found SmartDashboard");
//		else
//			System.out.println("did not find SmartDashboard");
	}

	NetworkTable table;
	
	public void netTable() {
		String ip = "10.49.11.84";
		String tableName = "SmartDashboard";

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		table = NetworkTable.getTable(tableName);
	}
	
	public void run()
	{
		if (camera.isOpened())
		{
			System.out.println("Yay! I see something  ");
			camera.read(mat);
			updateJFrame(mat);

			netTable();
			
//			try
//			{
//				new File("C:\\2016CameraImages").mkdir();
//				File outputfile = new File("C:\\2016CameraImages\\START-Img" + System.currentTimeMillis() + ".jpg");
//				ImageIO.write(buffImg, "jpg", outputfile);
//			} catch (IOException e)
//			{
//			}
			
			frame.setSize(mat.width()+20,mat.height()+45);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			int calebNum = 0;
			
			boolean badMat = false;
			while (true)
			{
				table.putNumber("Calebs Number", calebNum++);
				
				if (camera.isOpened())
				{
					try
					{
						camera.read(mat);
						updateJFrame(mat);
					}
					catch (Exception e)
					{
						System.out.println("e " + e.getMessage());
					}					
				}
				else
				{
					System.out.println("cant find it.... 0.0");
					break;
				}
			}
		}
		else
		{
			System.out.println("camera not found!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateJFrame(Mat Mat)
	{
//		frame.remove(label);//not sure this is needed?
		buffImg = getUsableImage(Mat);

		image = new ImageIcon(buffImg);
		label.setIcon(image);
		frame.add(label);
		frame.repaint();
	}
	
	private BufferedImage getUsableImage(Mat input)
	{
		BufferedImage image = null;
		image = new BufferedImage(input.width(), input.height(), BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        input.get(0, 0, data);
        
	    return modifyImage(image);
	}
	
	//Constants for image converting	
	final int red = 80;
	final int green = 245;
	final int blue = 125;
	
	final int threshold = 10;
	
	final int numBoxes = 12;
	
	//	ArrayList<Box> boxes = new ArrayList(10);
	
	public static BufferedImage testBox(BufferedImage img) {
		for(int y = 20; y < 30; y++) {
			for(int x = 20; x < 30; x++) {
				img.setRGB(x, y, new Color(200, 0, 200).getRGB());
			}
		}
		
		return img;
	}
		
	private BufferedImage modifyImage(BufferedImage img) {	
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[] rgbArray = new int[width * height];
		rgbArray = img.getRGB(0, 0, width, height, rgbArray, 0, width);	

		for(int numBoxes = 0; numBoxes < numBoxes; numBoxes++) {
			
			Box currBox = new Box(width, height);
			int validStartX = -1;
					
			for(int y = 0; y < height; y++) {
				
				currBox.setCurrentBoxWidth(0);
				validStartX = -1;
				
				for(int x = 0; x < width; x++) {
					
					int rgb = rgbArray[y*width+x];
					int redC = (rgb & 0x00ff0000) >> 16;
					int greenC = (rgb & 0x0000ff00) >> 8;
					int blueC = (rgb & 0x000000ff);
					
					if((redC > red) && (greenC > green) && (blueC > blue))
					{	
						if(validStartX == -1) {
							validStartX = x;
						}
	
						currBox.addToCurrWidth(1); // increments the box with
					} else if(currBox.getCurrentBoxWidth() < threshold) {
						currBox.setCurrentBoxWidth(0);
						validStartX = -1;
						
					} else { //if(validWidth > threshold) {
						currBox.setLeftX(validStartX, currBox.getValidLines());
						currBox.setRightX(validStartX + currBox.getCurrentBoxWidth(), currBox.getValidLines());
						
						currBox.addToValidLines(1);
						
						if(currBox.getTopY() == -1) {
							currBox.setTopY(y);;
						}
						
						break;
					}
				}
					
					if((currBox.getTopY() > -1) && 
							((currBox.getCurrentBoxWidth() < threshold) || (y >= width - 1)) && 
							(currBox.getBottomY() == -1)) {
						
						currBox.setBottomY(y);
						break;
				}
			}
			
			img = currBox.drawBox(img);
			drawBox(rgbArray, currBox, width);	
			
		}
		
		return img;
	}
		
	public void drawBox(int[] rgbArray, Box box, int imgWidth) {
		int i = 0;
		
		int top = box.getTopY();
		int bottom = box.getBottomY();
		
		int[] leftX = box.getLeftX();
		int[] rightX = box.getRightX();
		
		for(int y = top; y < bottom; y++) {
			int row = y * imgWidth;
			for(int x = leftX[i]; x < rightX[i]; x++) {
				rgbArray[row + x] = 0;
			}
			i++;
		}
	}
	
	public double findDistance(int pxHeight) {
		// height measured in pixels
		double kHeight = 340;
//		double kHeight = 169;
//		double kHeight = 112;
//		double kHeight = 81;
	
		// distance measure in inches
		double kDistance = 6;
//		double kDistance = 12;
//		double kDistance = 18;
//		double kDistance = 25;
		
		double scale = pxHeight / kHeight;

		double newDistance = kDistance / scale;
		// distances closer than 5.5 inches return infinity inches
		
		// note: image get wider at longer distances
		
		// Accuray: 
		// +- 0.1 inches at 6 inches
		// +- 0.1 inches at 12 inches
		// +- 0.2 inches at 18 inches
		// +- 0.4 inches at 25 inches
		
		return newDistance;
	}
}