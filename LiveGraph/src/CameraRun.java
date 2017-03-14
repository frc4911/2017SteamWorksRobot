//package src;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

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
	
	private PrintStream out;

	
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
		int imgWidth = 120;
		int imgHeight = 160;
		
		if (camera.isOpened())
		{
			System.out.println("Yay! I see something  ");
			camera.read(mat);
			updateJFrame(mat);

//			netTable();
			
//			try
//			{
//				new File("C:\\2016CameraImages").mkdir();
//				File outputfile = new File("C:\\2016CameraImages\\START-Img" + System.currentTimeMillis() + ".jpg");
//				ImageIO.write(buffImg, "jpg", outputfile);
//			} catch (IOException e)
//			{
//			}
			
//			frame.setSize(imgWidth, imgHeight);
			frame.setSize(mat.width()+20,mat.height()+45);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
			
			try {
				out = new PrintStream(new File("C:\\Test\\log.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
//			boolean badMat = false;
			int i = 0;
//			while (true)
			while (i < 0)
			{
				/* try {
					img = ImageIO.read(new File("C:\\2016CameraImages\\START-Img1489115487089.jpg"));
					updateJFrame(img);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} */
				if (camera.isOpened())
				{
					try
					{
						camera.read(mat);
						updateJFrame(mat);
					}
					catch (Exception e)
					{
						e.printStackTrace();
//						System.out.println("e1 " + e.getMessage());
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
	
	public void updateJFrame(BufferedImage img)
	{
//		frame.remove(label);//not sure this is needed?
		buffImg = modifyImage(img);

		image = new ImageIcon(buffImg);
		label.setIcon(image);
		frame.add(label);
		frame.repaint();
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
	final int RED = 80; 	// 80
	final int GREEN = 245;	// 245
	final int BLUE = 125;	// 125
	
	final int THRESHOLD = 10;
	
	final int NUM_BOXES= 1;
	
	//	ArrayList<Box> boxes = new ArrayList(10);
	
	private BufferedImage modifyImage(BufferedImage img) {	
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[] rgbArray = new int[width * height];
		rgbArray = img.getRGB(0, 0, width, height, rgbArray, 0, width);	

		for(int numBoxes = 0; numBoxes < this.NUM_BOXES; numBoxes++) {
						
			Box currBox = new Box(width, height);
			int validStartY = -1;
				
			for(int x = width - 1; x >= 0; x--) {
				
				currBox.setCurrentBoxWidth(0);
				validStartY = -1;
				
				for(int y =0; y < height; y++) {
					int rgb = rgbArray[y*width+x];
					int redC = (rgb & 0x00ff0000) >> 16;
					int greenC = (rgb & 0x0000ff00) >> 8;
					int blueC = (rgb & 0x000000ff);
					
					if((redC > RED) && (greenC > GREEN) && (blueC > BLUE))
					{	
						img.setRGB(x, y, Color.BLACK.getRGB());
						if(validStartY == -1) {
							validStartY = y;
						}
						currBox.addToCurrWidth(1); // increments the box with
						
					} else if(currBox.getCurrentBoxWidth() < THRESHOLD) {
						currBox.setCurrentBoxWidth(0);
						validStartY = -1;
						
					} else { //if(validWidth > threshold) {
						int leftY = validStartY;
						int rightY = validStartY + currBox.getCurrentBoxWidth();
						
						currBox.setLeftX(leftY, currBox.getValidLines());
						currBox.setRightX(rightY, currBox.getValidLines());
						
						currBox.addToValidLines(1);
						
						// set the top y axis of the box
						if(currBox.getTopY() == -1) {
							currBox.setTopY(y);
						}
								
						break;
					}
				}
				
				// set the bottom y axis of the box
				if(		   (currBox.getTopY() > -1) 
						&& ((currBox.getCurrentBoxWidth() < THRESHOLD) || (x >= width - 1)) 
						&& (currBox.getBottomY() == -1)) {
					currBox.setBottomY(x);

					break;
				}
			}
			
			System.out.println("topY: " + currBox.getTopY());
			System.out.println("bottomY: " + currBox.getBottomY());
			
			System.out.println("validLines: " + currBox.getValidLines());
			
			for(int i = 0; i < currBox.getValidLines(); i++) {
				System.out.print(currBox.getLeftX(i) + ", ");
			}
			System.out.println();
			for(int i = 0; i < currBox.getValidLines(); i++) {
				System.out.print(currBox.getRightX(i) + ", ");
			}
			System.out.println();
			
			// draws the box to the image
			img = currBox.drawBox(img);
			img = currBox.drawRect(img);
			
			// Eliminates the box from rgbArray so that the
			// box wont be scanned again.
			drawBox(rgbArray, currBox, width);	
			
//			Point[] pts = currBox.getCorners();
//			System.out.println("TopLeft :" + pts[0]);
//			System.out.println("TopRight :" + pts[1]);
//			System.out.println("BottomLeft :" + pts[2]);
//			System.out.println("BottomRight :" + pts[3]);	
			
		}
		
		return img;
	}
	
	/* private BufferedImage modifyImage(BufferedImage img) {	
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[] rgbArray = new int[width * height];
		rgbArray = img.getRGB(0, 0, width, height, rgbArray, 0, width);	

		for(int numBoxes = 0; numBoxes < this.NUM_BOXES; numBoxes++) {
						
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
					
					if((redC > RED) && (greenC > GREEN) && (blueC > BLUE))
					{	
						if(validStartX == -1) {
							validStartX = x;
						}
	
						currBox.addToCurrWidth(1); // increments the box with
					} else if(currBox.getCurrentBoxWidth() < THRESHOLD) {
						currBox.setCurrentBoxWidth(0);
						validStartX = -1;
						
					} else { //if(validWidth > threshold) {
						int leftX = validStartX;
						int rightX = validStartX + currBox.getCurrentBoxWidth();
								
						currBox.setLeftX(leftX, currBox.getValidLines());
						currBox.setRightX(rightX, currBox.getValidLines());
						
						currBox.addToValidLines(1);
						
						// set the top y axis of the box
						if(currBox.getTopY() == -1) {
							currBox.setTopY(y);
						}
						
						break;
					}
				}
					
				// set the bottom y axis of the box
				if(		   (currBox.getTopY() > -1) 
						&& ((currBox.getCurrentBoxWidth() < THRESHOLD) || (y >= width - 1)) 
						&& (currBox.getBottomY() == -1)) {
					currBox.setBottomY(y);
					
					break;
				}
			}
			
//			System.out.println("topY: " + currBox.getTopY());
//			System.out.println("bottomY: " + currBox.getBottomY());
//			
//			System.out.println("validLines: " + currBox.getValidLines());
//			
//			for(int i = 0; i < currBox.getValidLines(); i++) {
//				System.out.print(currBox.getLeftX(i) + ", ");
//			}
//			System.out.println();
//			for(int i = 0; i < currBox.getValidLines(); i++) {
//				System.out.print(currBox.getRightX(i) + ", ");
//			}
//			System.out.println();
			
			// draws the box to the image
			img = currBox.drawBox(img);
//			img = currBox.drawRect(img);
			
			// Eliminates the box from rgbArray so that the
			// box wont be scanned again.
			drawBox(rgbArray, currBox, width);	
			
//			Point[] pts = currBox.getCorners();
//			System.out.println("TopLeft :" + pts[0]);
//			System.out.println("TopRight :" + pts[1]);
//			System.out.println("BottomLeft :" + pts[2]);
//			System.out.println("BottomRight :" + pts[3]);	
			
		}
		
		return img;
	} */
		
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
	
	public double findAngle(Point left, Point right) {
		double angle = -1;
		
		if(left.getY() >= right.getY()) {
			// tape is to the right
			angle = findAngle(left, right, true);

		} else if(left.getY() < right.getY()) {
			// tape is to the left
			angle = findAngle(right, left, false);	
		}
		
		return angle;
	}
	
	private double findAngle(Point a, Point b, boolean hasLeftSide) {
		double opp = a.getY() - b.getY();
		double adj = a.getX() - b.getX();
		double hyp = Math.hypot(opp, adj);
		
		double tan = Math.tanh(hyp / adj);
		double oppAngle;
		if(hasLeftSide)
			oppAngle = Math.sinh(adj / hyp);
		else
			oppAngle = Math.cosh(adj/ hyp);
			
		if((89.8 < tan + oppAngle) && (tan + oppAngle <= 90.2)) {
			return 0;
		} else {
			return tan;
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