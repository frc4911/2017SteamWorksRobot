//package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

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

// be sure to copy opencv_ffmpegxxx_64 to c:\windows\system32 or "new VideoCapture()" will fail for ip cameras (local webcam works fine w/o it).

public class CameraRun {
	
	VideoCapture camera;
	Mat mat;
	JFrame frame;
	String videoStreamAddress = "http://FRC:FRC@10.49.11.11/mjpg/video.mjpg";//"http://root:password@10.36.63.100/mjpg/video.mjpg";
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

	public void run()
	{
		if (camera.isOpened())
		{
			System.out.println("Yay! I see something  ");
			camera.read(mat);
			updateJFrame(mat);

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
			
			boolean badMat = false;
			while (true)
			{
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
	Color c;
	Color newColor = new Color(200,0,200);
	
	int[] leftX = new int[720];
	int[] rightX = new int[720];
	
	int threshold = 10;
	
	private BufferedImage modifyImage(BufferedImage img)
	{
		int width = img.getWidth();
		int height = img.getHeight();
		
		int leftXAvg = 0;
		int rightXAvg = 0;
		
		int topRow = -1;
		int bottomRow = -1;
		
		int validLine = 0;
		int validWidth = 0;
		int validStartX = -1;
		
		for(int y = 0; y < height; y++)
		{	
			validWidth = 0;
			validStartX = -1;
			
			for(int x=0; x < width; x++)
			{
				c = new Color(img.getRGB(x,y));
				if((c.getRed()>80) && (c.getGreen()>245) && (c.getBlue()>125))
				{	
					if(validStartX == -1) {
						validStartX = x;
					}
					validWidth++; 
					
					img.setRGB(x, y, newColor.getRGB());
				}
				else if(validWidth < threshold) {
//					validWidth = 0;
					validStartX = -1;
				}
			}
			
			if(validWidth >= threshold) {
				leftX[validLine] = validStartX;
				rightX[validLine] = validStartX + validWidth;
				validLine++;
				
				if(topRow == -1) {
					topRow = y;
				}	
			}
			if((topRow > -1) && (validWidth < threshold) && (bottomRow == -1)) {
				bottomRow = y - 1;
			}
		}
		
		for(int y = 0; y < validLine--; y++) {
			leftXAvg += leftX[y];
			rightXAvg += rightX[y];
		}
		
		leftXAvg = leftXAvg / validLine;
		rightXAvg = rightXAvg / validLine;
		
//		img = drawBox(img);
		
		System.out.println("topLeft(" + leftXAvg + ", " + topRow + ") bottomRight(" + rightXAvg + ", " + bottomRow + ")");
//		System.out.println("topLeft(" + leftX[10] + ", " + topRow + ") bottomRight(" + rightX[10] + ", " + bottomRow + ")");
		return img;
	}
}