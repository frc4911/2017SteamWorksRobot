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
import edu.wpi.first.wpilibj.networktables.*;

public class CameraRun {
	
	MassObjectHolder massObjectPointer;
	VideoCapture camera;
	Mat mat;
	JFrame frame;
	Component com;
	String videoStreamAddress = "http://root:team3663@10.36.63.15/mjpg/video.mjpg";//"http://root:password@10.36.63.100/mjpg/video.mjpg";
	//String videoStreamAddressUSB = something "http://roborio-3663FRC.local";
	String videoStreamAddress2 = "http://root:password@10.36.63.100/mjpg/video.mjpg";
	String videoStreamAddress3 = "http://root:password@169.254.199.100/mjpg/video.mjpg";
	String streamAddress = videoStreamAddress;
	
	ImageIcon image;//, imageTest;
	BufferedImage buffImg;
	JLabel label;
	NetworkTable table;
	
	//int[] rows, colmns;
	int fixWidth = 640;
	int fixHeight = 480;
	double resolutionRatio = 640/640;//new resolution/640
	int[][] pic;
	int gPieceKey = -1;
	int bestPieceKey;
	int[][] U = new int[fixWidth][fixHeight];//size[196][149];
	double maxDistortedAngle = 20;
	int goalCenterX = 325/*-1*/,goalCenterY;
	double angle, distance;//angle called in comment in findCenterGoal()
	
	
	boolean hardChooseLeft = true;//will choose leftgoal if close enough
	
	
//	boolean foundObject = false;
	
	boolean autoFindLeft,centeringGoal/*,movingWithRadius*/,okayToShoot,centeringY;
	
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
		
		camera = new VideoCapture(streamAddress);//0 - USB Cam....?
		System.out.println("found camera");
		mat = new Mat();
		
		massObjectPointer = new MassObjectHolder();
		
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.36.63.2");//"169.254.199.6");//"10.36.63.20");//78");
		table = NetworkTable.getTable("Dog-NT");
		System.out.println("found NT");
		//setRedU();
		
		table.putBoolean("autoInitFindLeft: ",true);//remove when auto switches are made
		
		//table.putBoolean("Mode/commandRunning: ",false);//may not need
		//table.putBoolean("Mode/inAutonomous: ",false);//may not need
		table.putBoolean("C_/centeringGoal: ",true);
		//table.putBoolean("C_/movingWithRadius: ",false);
		table.putBoolean("C_/okayToShoot: ",false);
		
		//-------------------------------------------//may need to be placed in a place where camera can be disconnected?
		if (table.getBoolean("autoInitFindLeft: ",true))//found from gettingBoolean
		{
			table.putNumber("cameraMoveAngle: ", -360);//may not need
			table.putBoolean("turnLeft: ",true);
		}
		else
		{
			table.putNumber("cameraMoveAngle: ", 360);//may not need
			table.putBoolean("turnLeft: ", false);
		}
		//--------------------------------------------
	}

	int buffingCounter = 0;
	boolean cameraFound = false;
	
	private boolean checkCameraStillFound()
	{
		//if (buffingCounter++%100 == 99)
		/*{
			if (camera.isOpened())
			{
				cameraFound = true;
			}
			else 
			{
				cameraFound = false;
			}
		}*/
		return camera.isOpened();
	}
	
	public void run()
	{
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//CameraInit();

		/*while (!camera.isOpened())
		{
			System.out.println("camera not on yet");
		}*/
	//	do
		{
			//------------=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		//Image i = File("C:\Users\angel_000\Pictures\Screenshots\.");
		
			if (camera.isOpened())
			{
				cameraFound = true;
				System.out.println("Yay! I see something  ");
				camera.read(mat);
				updateJFrame(mat);
	
				try
				{
					new File("C:\\2016CameraImages").mkdir();
					File outputfile = new File("C:\\2016CameraImages\\START-Img" + System.currentTimeMillis() + ".jpg");
					ImageIO.write(buffImg, "jpg", outputfile);
				} catch (IOException e)
				{
				}
				
				frame.setSize(mat.width()+20,mat.height()+45);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				boolean badMat = false;
				while (true)
				{
					cameraFound = checkCameraStillFound();
					if (cameraFound)
					{
						int badMatCounter = 0;
						do 
						{
							badMat = false;
							try
							{
								camera.read(mat);
							}
							catch (Exception e)
							{
								badMat = true;
								System.out.println(e.getMessage());
							}
							if (!badMat)
							{
								if (mat.width() == 0)
								{
									System.out.println("can't see mat!" + mat.width());
									badMat = true;
								}
							}
							if (badMat)
							{
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							badMatCounter++;
							if (badMatCounter > 15)
							{
								break;
							}
						}while (badMat);
						if (badMatCounter > 15)
						{
							break;
						}
						updateJFrame(mat);
						
				//		table.putBoolean("foundObject: ",(gPieceKey>-1));//may not need
						//make a put in Camera Init
						autoFindLeft = table.getBoolean("autoInitFindLeft: ",true);
						//alreadyInCommand = table.getBoolean("Mode/commandRunning: ",false);//may not need
						//inAutonomous = table.getBoolean("Mode/inAutonomous: ",false);//may not need
						centeringGoal = table.getBoolean("C_/centeringGoal: ",false);
						//movingWithRadius = table.getBoolean("C_/movingWithRadius: ",false);
						okayToShoot = table.getBoolean("C_/okayToShoot: ",false);
						
						//if (gPieceKey>-1)//moved if statement within methods
						{
							findCenterGoal();
							//if (!movingWithRadius)
							{
								centeringGoal = centeringGoal();
							}
							//if (!centeringGoal)
							{
								okayToShoot = isFineAdjustedGoal();
							}
							{
								centeringY = centeringY();
							}
							table.putBoolean("C_/centeringGoal: ", centeringGoal);
							//table.putBoolean("C_/movingWithRadius: ", movingWithRadius);
							table.putBoolean("C_/okayToShoot: ", okayToShoot);
							table.putBoolean("C_/centeringY: ", centeringY);
						}
						
						if (table.getBoolean("ShooterShot: ",false))
						{
							try
							{
								File outputfile = new File("C:\\2016CameraImages\\SHOOT-Img" + System.currentTimeMillis() + ".jpg");
								ImageIO.write(buffImg, "jpg", outputfile);
							} catch (IOException e)
							{
								System.out.println("ERROR: " + e.getMessage());
							}
							table.putBoolean("ShooterShot: ",false);
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
		}// while (true);
	}
	public void resetVariables()
	{
	//	foundObject = false;
		gPieceKey = -1;
		massObjectPointer.dumpPast();
	}
	public void updateJFrame(Mat Mat)
	{
//		frame.remove(label);//not sure this is needed?
		buffImg = getUsableImage(Mat);
		///*
				//cleanImageToMass(buffImg);
		//pic = newImgArray(buffImg);
		//findRectangle(buffImg);//good for if tilted with right side higher, but will need to change findU to get that (overall better)
		//findU(buffImg);
		
		//System.out.println("--------------------printing New Image");
		resetVariables();
		separateObjects();
		removeSmallObjects();//remember later to get rid of extra removeSmallObject() methods
	//	table.putNumber("gPieceKey: ", gPieceKey);
		table.putBoolean("foundObject: ", (gPieceKey > -1));
		if (gPieceKey>-1) ///To help Delay, lower res or slow down frames per sec!!!!!!!!!!!!!!!!!!!!!!!
		{
	//		getMostMassObject();
			getBestObjectMask();
		}
		
		//*/
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
        
	    return convertToBlackGreenImage(image);
	}
	//Constants for image converting
	Color c;
	int g = Color.PINK.getRGB();//Color.GREEN.getRGB();
	int cy = Color.CYAN.getRGB();
	int bl = Color.BLUE.getRGB();
	int r = Color.RED.getRGB();
	int gg;
	
	int b = Color.ORANGE.getRGB();//= Color.black.getRGB();
	//int p = Color.PINK.getRGB();
	private BufferedImage convertToBlackGreenImage(BufferedImage img)
	{
		int width = img.getWidth();
		int height = img.getHeight();
		pic = new int[width][height];
		boolean dartBlwThrsh = table.getBoolean("dartBelowThreshold: ",false);
	/*	rows = new int[img.getHeight()];
		colmns = new int[img.getWidth()];*/
		for(int y = 0; y<height; y++)
		{
			for(int x = (int)(85)/**resolutionRatio)0*/; x<width; x++)//make it ignore left corner!!!!!!!!!!!
			{
				if (/*!(x < 104/* && y  < 377*./) && */(!dartBlwThrsh || (dartBlwThrsh && y < 358/**resolutionRatio*/)))
				{
					c = new Color(img.getRGB(x,y));
					gg = c.getGreen();
					if (c.getRed()<60 && /*c.getBlue()<210 &&*/ (gg>=210 || gg>=c.getBlue()+35))//c.HSBtoRGB(hue, saturation, 100) greater than 100 luminosity
					{
						if (okayToShoot && !centeringGoal)
						{
							img.setRGB(x, y, g);
						}
						else if (centeringGoal)
						{
							img.setRGB(x, y, b);//cy);
						}
						else
						{
							img.setRGB(x, y, bl);
						}
						pic[x][y] = 1;				
					}
					else if (isRedLine(x))//isRedU(x,y))
					{
						img.setRGB(x, y, r);
					}
					else
					{
				//		img.setRGB(x, y, b);
					}
				}
			}
		//	for (double counter = -9.0; counter < 9999.0; counter+=0.1)
			{
				
			}
		}
		return img;
	}
	
	
/*	public BufferedImage convertToBlackGreenImage(BufferedImage img)
	{
	/*	rows = new int[img.getHeight()];
		colmns = new int[img.getWidth()];*./
		Color c;
		int width = img.getWidth();
		int height = img.getHeight();
		pic = new int[width][height];
		int g = Color.GREEN.getRGB();
		int r = Color.RED.getRGB();
	//	int b = Color.black.getRGB();
		for(int y = 0; y<height; y++)
		{
			for(int x = 0; x<width; x++)
			{
				c = new Color(img.getRGB(x,y));
				if (c.getRed()<90/* && c.getBlue()<190*./ && c.getGreen()>=170)
				//if (x == 1)
				{
					img.setRGB(x, y, g);
					pic[x][y] = 1;				
				}
				else if (isRedU(x,y))
				{
					img.setRGB(x, y, r);
				}
				else
				{
			//		img.setRGB(x, y, b);
				}
			}
		}
		return img;
	}*/

	public boolean moveWithAngleRadius(int key)
	{
		double angle = getAngleTilt(key);
		double distance = getDistanceMass(key);
		if (distance > 130)
		{
			angle*=2;
		}
		else if (distance < 65)
		{
			angle*=2/3;
		}
		
		table.putNumber("Moving/MoveAngle: ", angle);
		table.putNumber("Moving/MoveRadius: ", distance);
		
		if (angle < maxDistortedAngle && angle > -maxDistortedAngle)
		{
			//table.putBoolean("Moving/MoveSideways: ", false);//may not need
			return false;
		}
		//table.putBoolean("Moving/MoveSideways: ", true);//may not need
		return true;
	}
	
/*	public void findU(BufferedImage img)//in progress...
	{
		int xCornerT = 1, xCornerB = 638;
		int yCornerT = 1, yCornerB = 478;
		boolean firstGreen = true;
		boolean sawGreen = false;
		for (int y = 1; y < pic[0].length-1; y++)//img.getHeight()-1
		{
			for (int x = xCornerT; x < pic.length-1; x++)//img.getWidth()-1
			{
				boolean alreadyGreenStreak = false;
				if (pic[x][y] > 0)//(img.getRGB(x,y) == Color.green.getRGB())
				{
					alreadyGreenStreak = true;
					if (firstGreen)
					{
						xCornerT = x;
						yCornerT = y;
						firstGreen=!firstGreen;
						sawGreen = true;
					}
				//	else if (x>xCornerB)
					{
						xCornerB = x;
					}
					yCornerB = y;
				}
				else if (alreadyGreenStreak)
				{
					break;
				}
			}
		}
	//	if (Math.abs(xCornerT-xCornerB)<)//adding looking <---o for box that has right side higher than left
		colorSquare(img,xCornerT, yCornerT, Color.ORANGE);
		colorSquare(img,xCornerB, yCornerB, Color.ORANGE);
		int avgX = (xCornerB+xCornerT)/2;
		int avgY = (yCornerB+yCornerT)/2;
		if (!sawGreen)
		{
			avgX = 1;
		}
		colorSquare(img, avgX, avgY, Color.MAGENTA);
		table.putNumber("gMass.Middle X: ", avgX);
		table.putNumber("gMass.Middle Y: ", avgY);
	}*/
	
	/*	public void findRectangle(BufferedImage img)
	{
		int xCornerT = 1, xCornerB = 638;
		int yCornerT = 1, yCornerB = 478;
		boolean firstGreen = true;
		for (int y = 1; y < img.getHeight()-1; y++)
		{
			for (int x = 1; x < img.getWidth()-1; x++)
			{
				boolean alreadyGreenStreak = false;
				if (img.getRGB(x,y) == Color.green.getRGB())
				{
					alreadyGreenStreak = true;
					if (firstGreen)
					{
						xCornerT = x;
						yCornerT = y;
						firstGreen=!firstGreen;
					}
				//	else if (x>xCornerB)
					{
						xCornerB = x;
					}
					yCornerB = y;
				}
				else if (alreadyGreenStreak)
				{
					break;
				}
			}
		}
		colorSquare(img,xCornerT, yCornerT, Color.YELLOW);
		colorSquare(img,xCornerT, yCornerB, Color.YELLOW);
		colorSquare(img,xCornerB, yCornerT, Color.YELLOW);
		colorSquare(img,xCornerB, yCornerB, Color.YELLOW);
	//	System.out.println("Top: " + xCornerT + ", " + yCornerT);
	//	System.out.println("Bottom: " + xCornerB + ", " + yCornerB);
		int avgX = (xCornerB+xCornerT)/2;
		int avgY = (yCornerB+yCornerT)/2;
		colorSquare(img, avgX, avgY, Color.RED);
		table.putNumber("gMass.Middle X: ", avgX);
		table.putNumber("gMass.Middle Y: ", avgY);
	}*/
	
	public void separateObjects()//BufferedImage img)//if 5 or 3 pixels of black, then keep scanning
	{
		int lineLength = 0;
		int startX = -1,endX = startX, startY = 0, compStartX, compStartY, compEndX;
		boolean beganLine = false;
		boolean alreadyAdded;
		int alreadyAddedKey;
		
		for (int y = 0; y < pic[0].length; y++)
		{
			for (int x = 0; x < pic.length; x++)
			{
				if (pic[x][y] > 0)
				{
					if (!beganLine)
					{
						beganLine = true;
						startX = x;
						startY = y;
					}
					endX = x;
					lineLength++;
				}
				if(beganLine && (x == fixWidth-1 || (pic[x][y] == 0)))
				{ 
					alreadyAdded = false;
					alreadyAddedKey = -1;
					if (gPieceKey>-1)
					{
						for (int i = 0; i <= gPieceKey; i++)
						{
							compStartX = massObjectPointer.getGPiece(i).xStart;
							compStartY = massObjectPointer.getGPiece(i).yStart + massObjectPointer.getGPiece(i).height;
//							System.out.println("startY: " + startY);
//							System.out.println("compStartY: yStart " + massObjectPointer.getGPiece(i).yStart  + " + height " + massObjectPointer.getGPiece(i).height  + "= "+ compStartY);
							compEndX = massObjectPointer.getGPiece(i).xEnd;
//							System.out.println((boolean)(startY == compStartY) + "		" + (boolean)((startX <= compStartX && endX >= compStartX) || (endX >= compEndX && startX <= compEndX)) + "          " + alreadyAdded);
							if ((startY == compStartY)
									&& ((startX >= compStartX && endX <= compEndX) ||
										 (startX <= compStartX && endX >= compStartX) || 
										 (endX >= compEndX && startX <= compEndX)))
							{
								if (!alreadyAdded)
								{
									//....
	//								System.out.println("=====================addToMass Called");
									massObjectPointer.gPieceAddToMass(i,startX,startY,lineLength,endX);
									alreadyAdded = true;
									alreadyAddedKey = i;
								}
								else
								{
									//need to finish code to add two masses
//									System.out.println("--------------------=========combining Masses");
									massObjectPointer.gPieceCombineMass(alreadyAddedKey, i);
									gPieceKey--;
									i--;
								}
							}
							else if (i == gPieceKey)//if not && gone through whole array of objects
							{
		//						System.out.println("========" + gPieceKey + "===============newGreenMassInit");
								massObjectPointer.GreenMassInit(++gPieceKey, startX, startY,lineLength, endX);
								break;//need to break because if not, increased gPieceKey meaning when back at beginning, for loop is true and continue again
							}
						}
					}
					else
					{
	//					System.out.println("===========================================firstObject=======================" + gPieceKey + "===============================");
						massObjectPointer.GreenMassInit(++gPieceKey, startX, startY, lineLength, endX);
					}
					beganLine = false;//then add or make greenmass
					lineLength = 0;
				}
			}
		}
	}
	
	public void removeSmallObjects()
	{
		for (int o = 0; o <= gPieceKey; o++)
		{
			if (massObjectPointer.getGPiece(o).mass > 600)
			{
				massObjectPointer.putMaskOverlap(o, percentMaskOverlap(o));
				table.putNumber("mask overlap not removing yet", massObjectPointer.getMaskOverlap(o));
			}
			if (massObjectPointer.getGPiece(o).mass < 600)// || massObjectPointer.getMaskOverlap(o) < 62)
			{
				//System.out.println("mass: " + massObjectPointer.getGPiece(o).mass);
				massObjectPointer.removeMass(o);
		//		System.out.println("removing object " + o);
				gPieceKey--;
				o--;
			}
		}
	}
	
/*	public void checkWithObjectRatio(int keyNum)
	{
		getAngleTilt(keyNum);
		if (massObjectPointer.getGPiece(keyNum).width/massObjectPointer.getGPiece(keyNum).height > 1.67)
		{
			//good thing
			table.putBoolean("ShootingMin: ", true);
			table.putNumber("x: ", massObjectPointer.getGPiece(keyNum).xStart+(massObjectPointer.getGPiece(keyNum).width/2));
			table.putNumber("y: ", massObjectPointer.getGPiece(keyNum).yStart+(massObjectPointer.getGPiece(keyNum).height/8));
		}
		
	}*/
	private double getAngleTilt(int keyNum)
	{
		double angle = 1;
		int w = massObjectPointer.getGPiece(keyNum).width, h = 0;
		boolean leftSeen = false, rightSeen = false;
		int xstart = massObjectPointer.getGPiece(keyNum).xStart;
		int ystart = massObjectPointer.getGPiece(keyNum).yStart;
		
		for (int y = massObjectPointer.getGPiece(keyNum).height-1; y > 0 && !(leftSeen && rightSeen); y--)
		{
			for (int x = 0; x < w; x++)
			{
				if (pic[xstart+x][ystart+y] > 0)
				{
					if (x > massObjectPointer.getGPiece(keyNum).width-15)
					{
						rightSeen = true;
					}
					if (x < 15)
					{
						if (!rightSeen)
						{
							angle = -1;
						}
						leftSeen = true;
					}
				}
			}
			if (leftSeen || rightSeen)
			{
				h++;
			}
		}
		
		angle*=((Math.atan2(h,w))*180/(3.141593));
		
		//System.out.println("angle: " + angle);//max should be 60 degrees typically...for best goal
		
		return angle;
	}
	
	boolean firstTime;
	double[] massArr = new double[5];
	int counter = 0;
	double distAtCompDiff = 0;
	private double getDistanceMass(int keyNum)
	{
		double d = 0;
		
		massArr[counter%5] = (double)massObjectPointer.getGPiece(keyNum).mass;
		//table.putNumber("massB: ",massArr[counter%5]);
		if (firstTime)
		{
			for (int i = 0; i < 5; i++)
			{
				massArr[i] = massArr[0];
			}
		}counter++;
		
		double mass = ((massArr[0]+massArr[1]+massArr[2]+massArr[3]+massArr[4])/5)*27.5/21.0;//*((480*640)/(320*240));//if we want to use resolution ratio: ((480*640)/(320*240))
		//table.putNumber("mass: ",mass);
		
		//@120, is 150 (-30); @52, is 15 (+35)//when distAtCompDiff = 25 +(d*81/30)
		d = (-93.5*Math.log(mass)) + 858.41   +distAtCompDiff;//+distAtCompDiff is a temp fix to mass
		//distance dog d = ((-0.067*mass)+351.24)*mass/4000;//4048.36;
		//distance direct real time d = 0.000003*mass^2 - 0.0374*mass + 201.84
		
		return d;
	}
	
/*	private double getDistanceTape(int keyNum)
	{
		double d = 0;
		
		int xstart = massObjectPointer.getGPiece(keyNum).xStart;
		int ystart = massObjectPointer.getGPiece(keyNum).yStart;
		int h = massObjectPointer.getGPiece(keyNum).height;
		int w = massObjectPointer.getGPiece(keyNum).width;
		for (int x = 0; x < w/10; x++)
		{
			if (pic[(xstart+x)][(ystart+h/2)] > 0)
			{
				d++;
			}
		}
		
		switch ((int)d)
		{
			case 10:
				d = 190;
				break;
			case 17:
				d = 59;
				break;
			case 20:
				d = 54;
				break;
			default:
				d = (-14.6*d)+336;
				break;
		}
		if (d < 0)
		{
			d = -1;
		}
		table.putNumber("d: ",d);
		
		return d;
	}*/
	
/*	public void getMostMassObject()
	{
		int bestPiece = 0;

		//removeSmallObjects();

		if (gPieceKey > 0)
		{
			for (int o = 0; o <= gPieceKey; o++)
			{
				if (massObjectPointer.getGPiece(bestPiece).mass < massObjectPointer.getGPiece(o).mass)
				{
					bestPiece =o;
				}
			}
		}	
		int xCenter = (massObjectPointer.getGPiece(bestPiece).xEnd+massObjectPointer.getGPiece(bestPiece).xStart)/2;
		int ycenter = ((massObjectPointer.getGPiece(bestPiece).yStart)+(massObjectPointer.getGPiece(bestPiece).height)/2);
		colorSquare(buffImg,xCenter,ycenter,Color.RED);
		
		table.putNumber("gMass.Middle X: ", xCenter);
		table.putNumber("gMass.Middle Y: ", ycenter);//yCenter);
	}*/
	
	public void getBestObjectMask()//actual ratio 1.42 (20x14) and general average ratio 1.63 (w x h)
	{
		int bestPiece = 0;
		double bestMaskOverlap = massObjectPointer.getMaskOverlap(bestPiece);

	//	removeSmallObjects();
/*
		int x = massObjectPointer.getGPiece(bestPiece).xStart,y = massObjectPointer.getGPiece(bestPiece).yStart,
			w = massObjectPointer.getGPiece(bestPiece).width,h = massObjectPointer.getGPiece(bestPiece).height;
		bestMaskOverlap = percentMaskOverlap(x,y,w,h);*/
		
		if (gPieceKey > 0)
		{
			boolean bestPieceChanged = false;
			
			for (int o = 1; o <= gPieceKey; o++)
			{
	//			System.out.println("checking mass #: " + o);
/*				if (bestPieceChanged)
				{
					x = massObjectPointer.getGPiece(bestPiece).xStart;
					y = massObjectPointer.getGPiece(bestPiece).yStart;
					w = massObjectPointer.getGPiece(bestPiece).width;
					h = massObjectPointer.getGPiece(bestPiece).height;
				}
				
				int cX = massObjectPointer.getGPiece(o).xStart,cY = massObjectPointer.getGPiece(o).yStart,
						cW = massObjectPointer.getGPiece(o).width,cH = massObjectPointer.getGPiece(o).height;
*/				
				//if (massObjectPointer.getGPiece(o).mass > 50 || cW > 5 || cH > 5)
				{
					double cMaskOverlap = massObjectPointer.getMaskOverlap(o);//percentMaskOverlap(o);
					double maskOverlap = massObjectPointer.getMaskOverlap(bestPiece);//percentMaskOverlap(bestPiece);
					bestMaskOverlap = maskOverlap;
//					System.out.println("cPercentOverlap: " + cMaskOverlap);
	//				System.out.println("percentOverlap: " + maskOverlap);
		//			System.out.println("x: " + cX + ", y: " + cY + ", w: " + cW + ", h: " + cH);
					//		System.out.println("pO: " + maskOverlap);
					table.putNumber("cMaskOverlap: ", cMaskOverlap);
					table.putNumber("MaskOverlap: ", maskOverlap);
				//	if (Math.abs(a))
		/*			if (cMaskOverlap < 66)
					{
						massObjectPointer.removeMass(o);
						gPieceKey--;
						if (o < bestPieceKey)
						{
							bestPieceKey--;
						}
					}
					else if (maskOverlap < 66)
					{
						massObjectPointer.removeMass(bestPiece);
						gPieceKey--;
					}
					else
					{
			*/			if (Math.abs(cMaskOverlap - maskOverlap) < 10)
						{
							if ((Math.abs(getAngleTilt(o)) <= Math.abs(getAngleTilt(bestPiece))-5)
									|| ((hardChooseLeft) && ((massObjectPointer.getGPiece(o).xStart < massObjectPointer.getGPiece(bestPiece).xStart))))
							{
								/*if (Math.abs(lastPieceX-massObjectPointer.getGPiece(o).xStart) < 9
										&& Math.abs(lastPieceX2-massObjectPointer.getGPiece(o).xStart) < 9
										&& Math.abs(lastPieceX3-massObjectPointer.getGPiece(o).xStart) < 9)*/
								{
									bestPiece = o;
									bestPieceChanged = true;
									bestMaskOverlap = cMaskOverlap;
								}
							}
						}
						else if (cMaskOverlap > maskOverlap)
						{
							bestPiece = o;
							bestPieceChanged = true;
							bestMaskOverlap = cMaskOverlap;
						}
						else
						{
							bestPieceChanged = false;
						}
			//		}
				}
			}

		}
		if (gPieceKey > -1)// && bestMaskOverlap > 69)//may not need bestMaskOverlap comparison
		{
			bestPieceKey = bestPiece;
	//		checkWithObjectRatio(bestPiece);
			
			int xCenter = (massObjectPointer.getGPiece(bestPiece).xEnd+massObjectPointer.getGPiece(bestPiece).xStart)/2;
	//		int ycenter = ((massObjectPointer.getGPiece(bestPiece).yStart)+(massObjectPointer.getGPiece(bestPiece).height/2));
			int yCenter = ((massObjectPointer.getGPiece(bestPiece).yStart)+(massObjectPointer.getGPiece(bestPiece).height/8));
		//	int yCenter = ((massObjectPointer.getGPiece(bestPiece).yStart)+(massObjectPointer.getGPiece(bestPiece).height/2));
			//use coordinates of best object and get center
			
		//	colorSquare(buffImg,massObjectPointer.getGPiece(bestPiece).xStart,massObjectPointer.getGPiece(bestPiece).yStart, bl);
		//	colorSquare(buffImg,massObjectPointer.getGPiece(bestPiece).xStart+massObjectPointer.getGPiece(bestPiece).width,massObjectPointer.getGPiece(bestPiece).yStart+massObjectPointer.getGPiece(bestPiece).height,bl);
			
			colorSquare(buffImg, xCenter, yCenter, r);
			
			table.putNumber("bestGoal X: ", xCenter);
			table.putNumber("bestGoal Y: ", yCenter);
		//	massObjectPointer.dumpPast();
			//	gPieceKey = -1;
		}
	}

	private double percentMaskOverlap(int key)//int xStart, int yStart, int width, int height)
	{
		int xStart = massObjectPointer.getGPiece(key).xStart;
		int yStart = massObjectPointer.getGPiece(key).yStart;
		int width = massObjectPointer.getGPiece(key).width;
		int height = massObjectPointer.getGPiece(key).height;
		
		table.putNumber("height: ", height);
		table.putNumber("width: ", width);
		
		double percentOverlap;
		double tapeDepth = ((((double)height/6.0)+((4.0*(double)width)/10.0))/5.0);
		
		table.putNumber("tapeDepth: ", tapeDepth);
		
	//	int[][] mask = createMask(width, height);//index: 0-width; 1-height; 2-tapeDepth
		double overlapMask = 0, totalGreen = 1;
		
		for (int y = yStart; y < yStart + height; y++)
		{
			for (int x = xStart; x < xStart + width; x++)
			{
				if ((x <= xStart+tapeDepth) //left vertical block
						|| (x >= xStart+width-tapeDepth)//right vertical block
						|| (y >= yStart+height-tapeDepth))
				{
					if (pic[x][y] > 0)
					{
						overlapMask++;
						totalGreen++;
					}
					else
					{
						//overlapMask-=0.025;
					}
				}
				else if (pic[x][y] > 0)
				{
					totalGreen++;
					overlapMask-=0.05;//later try commenting out to see if affect percentages
				}
		/*		if (mask[x-xStart][y-yStart] == pic[x][y])
				{
					overlapMask++;
					totalGreen++;
				}
				else if (pic[x][y] > 0)
				{
					totalGreen++;
					overlapMask-=0.5;
				}
				else if (mask[x-xStart][y-yStart] == 1 && pic[x][y] == 0)
				{
					overlapMask-=0.5;
				}*/
			}
		}
		percentOverlap = overlapMask/totalGreen*100.0;//for reading. may remove variable later
		
		return percentOverlap;
	}

	private void findCenterGoal()//range of x: 308/310-361/360 through 30"(2'6")-204"(17')
	{
		if (gPieceKey > -1)
		{
			distance = getDistanceMass(bestPieceKey);
			table.putNumber("distanceByMass: ", distance);
		}
		//Frodo:
		//angle = getAngleTilt(bestPieceKey);
		/*
		
		if (distance < 64)
		{
			goalCenterX = (int)(356);//*resolutionRatio);
			//goalCenterY = 
		}
		else if (distance < 78)
		{
			goalCenterX = (int)(347);//*resolutionRatio);
			//goalCenterY = 
		}
		else if (distance < 97)
		{
			goalCenterX = (int)(340);//*resolutionRatio);
			//goalCenterY = 
		}
		else if (distance < 160)
		{
			goalCenterX = (int)(330);//*resolutionRatio);
			//goalCenterY = 
		}
		else if (distance < 186)
		{
			goalCenterX = (int)(325);//*resolutionRatio);
			//goalCenterY = 
		}
		else//321//322
		{
			goalCenterX = (int)(315);//*resolutionRatio);
			//goalCenterY = 
		}*/
		///===============================================
		//for final bot===================================
		goalCenterX = (int)(280);//5);//(300);//(325);//*resolutionRatio);
		if (distance > 140)
		{
			goalCenterX = (int)(282);//7);//(310);//(305);
		}
		else if (distance > 160)
		{
			//goalCenterX = (int)(306);//(310);//308
		}
		else if (distance < 50)
		{
			goalCenterX = (int)(283);//8);
		}
		/*
		if (distance < 64)
		{
			goalCenterX = 640-(int)(356);//*resolutionRatio);
		}
		else if (distance < 78)
		{
			goalCenterX = 640-(int)(347);//*resolutionRatio);
		}
		else if (distance < 97)
		{
			goalCenterX = 640-(int)(340);//*resolutionRatio);
		}
		else if (distance < 160)
		{
			goalCenterX = 640-(int)(330);//*resolutionRatio);
		}
		else if (distance < 186)
		{
			goalCenterX = 640-(int)(325);//*resolutionRatio);
		}
		else//321//322
		{
			goalCenterX = 640-(int)(315);//*resolutionRatio);
		}*/
	}
	
	private boolean centeringGoal()
	{//can compress alot more later
		if (gPieceKey > -1)
		{
			int xCenter = massObjectPointer.getGPiece(bestPieceKey).xStart + (massObjectPointer.getGPiece(bestPieceKey).width/2);
			double buffer = (double)(massObjectPointer.getGPiece(bestPieceKey).width*3.5/20.0);
			//int yCenter = massObjectPointer.getGPiece(bestPieceKey).yStart + (massObjectPointer.getGPiece(bestPieceKey).height/8);
			
			double moveAngle = 45.0*((double)xCenter-goalCenterX/*320.0*/)/320.0;//-160)/160;
		
			if (xCenter <= goalCenterX-buffer)//10)
			{
				table.putNumber("cameraMoveAngle: ", moveAngle);
				//table.putBoolean("turnLeft: ", true);
				table.putString("needsTurning: ", "TurnLeft");
				table.putBoolean("turnLeft: ",true);
				return true;
			}
			else if (xCenter >= goalCenterX+buffer)//10)
			{
				table.putNumber("cameraMoveAngle: ", moveAngle);
				//table.putBoolean("turnLeft: ", false);
				table.putString("needsTurning: ", "TurnRight");
				table.putBoolean("turnLeft: ", false);
				return true;
			}
			else if (xCenter > goalCenterX-buffer && xCenter < goalCenterX+buffer)
			{
				table.putNumber("cameraMoveAngle: ", 0);
				table.putString("needsTurning: ", "StopTurning");
				return false;
			}
		}
		else
		{
			double angle = table.getNumber("cameraMoveAngle: ", -45);
			if (angle < 0)
			{
				table.putNumber("cameraMoveAngle: ", -45);
			}
			else if (angle > 0)
			{
				table.putNumber("cameraMoveAngle: ", 45);
			}
		}
	/*	else
		{
			if (autoFindLeft)
			{
				table.putNumber("cameraMoveAngle: ", -360);
				table.putBoolean("turnLeft: ",true);
			}
			else
			{
				table.putNumber("cameraMoveAngle: ", 360);
				table.putBoolean("turnLeft: ", false);
			}
			return true;
		}*/return true;/*
		else if (autoFindLeft)//maybe put something that saved if seen object and which way it disappeared
		{
			table.putBoolean("leftTurn: ", true);
			return true;
		}
		else
		{
			table.putBoolean("leftTurn: ", false);
			return true;
		}*/
	}
	
	private boolean isFineAdjustedGoal()
	{
/*		boolean raiseShooterArm = true;
		boolean moveShooterArm = false;
	//	int xCenter = massObjectPointer.getGPiece(bestPieceKey).xStart + (massObjectPointer.getGPiece(bestPieceKey).width/2);
		int yCenter = massObjectPointer.getGPiece(bestPieceKey).yStart + (massObjectPointer.getGPiece(bestPieceKey).height/8);

		if (yCenter < goalCenterY-15)
		{
			moveShooterArm = true;
			raiseShooterArm = true;
		}
		else if (yCenter > goalCenterY+15)
		{
			moveShooterArm = true;
			raiseShooterArm = false;
		}

		if (moveShooterArm)
		{
			table.putBoolean("ShooterArm/raiseShooterArm: ", raiseShooterArm);
		}
		table.putBoolean("ShooterArm/moveShooterArm: ", moveShooterArm);
		*/
		/*double dist = getDistanceMass(bestPieceKey);
		double angle = getAngleTilt(bestPieceKey);*/
		return (/*(Math.abs(angle) < maxDistortedAngle) && !moveShooterArm &&*/table.getBoolean("finishedMovingPot: ",false) && !centeringGoal && distance < 15*12);
	}
	
	private boolean centeringY()
	{
		int yCenter = (int)table.getNumber("bestGoal Y: ",240);
		if (yCenter > 240+120)
		{
			table.putBoolean("raiseDart: ", true);
			return true;
		}
		else if (yCenter < 240-120)
		{
			table.putBoolean("raiseDart: ", false);
			return true;
		}
		return false;
	}
	
	private void colorSquare(BufferedImage img, int x, int y, int color)
	{
		int width = 11, height = 11;
		//changed from 3 to 5 so if broken, then fix
		int x2 = x-5,y2 = y-5;

		if (x > fixWidth-1)
		{
			x = fixWidth-1;
		}
		else if (x < 0)
		{
			x = 0;
		}
		if (y > fixHeight-1)
		{
			y = fixHeight-1;
		}
		else if (y < 0)
		{
			y = 0;
		}
		//-------------
		if (x2+((width/2)+3) > fixWidth-1)
		{
			width-=(x-x2);
			//x2 = fixWidth-1;
		}
		else if (x2 < 0)
		{
			width-=(x-x2);
			x2 = 0;
		}
		if (y2+((height/2)+3) > fixHeight-1)
		{
			height-=(y-y2);
			//y2 = fixHeight-1;
		}
		else if (y2 < 0)
		{
			height-=(y-y2);
			y2 = 0;
		}
		
		img.getGraphics().setColor(Color.RED);
		img.getGraphics().fillRect(x2, y2, width, height);
		//------
		color = Color.HSBtoRGB((float)Math.random(), (float)1.0, (float)0.6);
												img.setRGB(x, y, color);//.getRGB());
		for (int rad = 1; rad <= 4; rad++)
		{
		if (x < fixWidth-rad)			   		img.setRGB(x+rad, y, color);//.getRGB());
		if (x > rad-1)			   			img.setRGB(x-rad, y, color);//.getRGB());
		if (y < fixHeight-rad)			   		img.setRGB(x, y+rad, color);//.getRGB());//out of bounds exception
		if (x < fixWidth-rad && y < fixHeight-rad)		img.setRGB(x+rad, y+rad, color);//.getRGB());
		if (x > rad-1 && y < fixHeight-rad)  		img.setRGB(x-rad, y+rad, color);//.getRGB());
		if (y > rad-1)			   			img.setRGB(x, y-rad, color);//.getRGB());
		if (x < fixWidth-rad && y > rad-1)  		img.setRGB(x+rad, y-rad, color);//.getRGB());
		if (x > rad-1 && y > rad-1)    		img.setRGB(x-rad, y-rad, color);//.getRGB());
		}
		
		
		
		/*if (x < fixWidth-2)			   		img.setRGB(x+2, y, color);
		if (x > 1)			   			img.setRGB(x-2, y, color);
		if (y < fixHeight-2)			   		img.setRGB(x, y+2, color);
		if (x < fixWidth-2 && y < fixHeight-2)		img.setRGB(x+2, y+2, color);
		if (x > 1 && y < fixHeight-2)  		img.setRGB(x-2, y+2, color);
		if (y > 1)			   			img.setRGB(x, y-2, color);
		if (x < fixWidth-2 && y > 1)  		img.setRGB(x+2, y-2, color);
		if (x > 1 && y > 1)    		img.setRGB(x-2, y-2, color);
		*/
		
	}
	//(226,171) (395,171)
	//x: 196, y: 149, 22
	private boolean isRedLine(int x)
	{
		return (x == goalCenterX);
	}
	//not need????
/*	private boolean isRedU(int X, int Y)//overlapOnly? or all mask red?
	{
		return (U[X][Y]==1);
	}
	private void setRedU()
	{
		//for (int y = 170; y < 170+149; y++)
		{
			for (int x = 225; x < 225+22; x++)
			{
				U[x][170] = 1;
				U[x][171] = 1;
			//	img.setRGB(x+225, y+170, Color.RED.getRGB());
				
			}
			for (int x = 421; x > 421-22; x--)
			{
				U[x][170] = 1;
				U[x][171] = 1;
			//	img.setRGB(x+225, y+170, Color.RED.getRGB());
			}
		//	if (y > 297)
			{
				for (int x = 225; x < 422; x++)
				{
					U[x][318] = 1;
					U[x][319] = 1;
					U[x][297] = 1;
					U[x][298] = 1;
				//	img.setRGB(x+225, y+170, Color.RED.getRGB());
				}
			}
			for (int y = 171; y < 318; y++)
			{
				U[225][y] = 1;
				U[226][y] = 1;
				U[421][y] = 1;
				U[420][y] = 1;
				U[247][y] = 1;
				U[246][y] = 1;
				U[398][y] = 1;
				U[399][y] = 1;
			}
		}
	}*/
}
