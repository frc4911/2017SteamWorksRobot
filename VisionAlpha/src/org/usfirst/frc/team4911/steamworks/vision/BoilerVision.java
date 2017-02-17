package org.usfirst.frc.team4911.steamworks.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * This class is not thread-safe!
 */
public class BoilerVision extends VisionBase {
	private static Logger log = Logger.getLogger(BoilerVision.class.getName());

	static {
		// Ensure the native library gets loaded
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private Mat image;
	private Mat dilateElement;
	private Mat erodeElement;
	private boolean debug;

	public BoilerVision(boolean enableDebug) {
		this.image = new Mat(); // this is not threadsafe!
		this.dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		this.erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));
		this.debug = enableDebug;
	}
	
	/**
	 * Calculate the error between the given image and the target. A postive
	 * value means increase the current value to close in on the target.
	 * 
	 * @return the error in the x and y direction
	 */
	public Point calculateError(Mat image, boolean debug) {
		List<MatOfPoint> contours = detectMarkers(image);

		// find the two largest blobs, assume they are the two
		// target stripes we want to center on.
		double max1area = 0d, max2area = 0d;
		MatOfPoint max1 = null, max2 = null;
		for (int i = 0; i < contours.size(); i++) {
			MatOfPoint c = contours.get(i);
			
			// we are looking for horizontal stripes on the boiler, reject
			// targets that are not wider than tall
			Rect bounds = findBoundingRect(c);
			if (bounds.width < bounds.height) {
				continue;
			}
			
			double area = Imgproc.contourArea(c);
			
			if (area > max1area) {
				// the new countour is larger than the old largest, this means
				// the old largest becomes the new second largest
				max2area = max1area;
				max2 = max1;

				max1area = area;
				max1 = c;
			} else if (area > max2area) {
				max2area = area;
				max2 = c;
			}
		}
		if (max1 == null)  {
			return new Point(0, 0);
		}

		Rect maxBounds1 = findBoundingRect(max1);
		Rect maxBounds2 = findBoundingRect(max2);
		Rect combinedBounds = findBoundingRect(maxBounds1, maxBounds2);
		if (debug) {
			drawRect(image, maxBounds1);
			drawRect(image, maxBounds2);
			drawRect(image, combinedBounds);
		}

		// get the a vector from the center of the screen (where we want the
		// center of the bounds)
		// to the actual current center of the bounds
		int cx = (image.width() / 2) - (combinedBounds.x + (combinedBounds.width / 2));
		int cy = (image.height() / 2) - (combinedBounds.y + (combinedBounds.height / 2));
		return new Point(cx, cy);
	}
	
	

	public List<MatOfPoint> detectMarkers(Mat cameraImage) {
		List<MatOfPoint> contours = new ArrayList<>();

		Imgproc.cvtColor(cameraImage, image, Imgproc.COLOR_RGB2GRAY);

		Imgproc.threshold(image, image, 155, 255, Imgproc.THRESH_BINARY);
		Imgproc.erode(image, image, erodeElement);
		// Imgproc.erode(image, image, erodeElement);
		Imgproc.dilate(image, image, dilateElement);
		// Imgproc.dilate(image, image, dilateElement);

		Mat cMat = new Mat();
		image.copyTo(cMat);
		Imgproc.findContours(cMat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

		if (debug) {
			for (int i = 0; i < contours.size(); i++) {
				Imgproc.drawContours(cameraImage, contours, i, new Scalar(255, 0, 255), 2);
				Point p = new Point(contours.get(i).get(0, 0));
				String info = "idx: " + i + ", area=" + Imgproc.contourArea(contours.get(i));
				Core.putText(cameraImage, info, p, Core.FONT_HERSHEY_PLAIN, 1, new Scalar(0, 0, 255));
			}
		}

		return contours;
	}
	
	public List<MatOfPoint> detectColorMarkers(Mat cameraImage) {
		List<MatOfPoint> contours = new ArrayList<>();

		Imgproc.cvtColor(cameraImage, image, Imgproc.COLOR_RGB2HSV);

		Imgproc.threshold(image, image, 155, 255, Imgproc.THRESH_BINARY);
		Imgproc.erode(image, image, erodeElement);
		// Imgproc.erode(image, image, erodeElement);
		Imgproc.dilate(image, image, dilateElement);
		// Imgproc.dilate(image, image, dilateElement);

		Mat cMat = new Mat();
		image.copyTo(cMat);
		Imgproc.findContours(cMat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

		if (debug) {
			for (int i = 0; i < contours.size(); i++) {
				Imgproc.drawContours(cameraImage, contours, i, new Scalar(255, 0, 255), 2);
				Point p = new Point(contours.get(i).get(0, 0));
				String info = "idx: " + i + ", area=" + Imgproc.contourArea(contours.get(i));
				Core.putText(cameraImage, info, p, Core.FONT_HERSHEY_PLAIN, 1, new Scalar(0, 0, 255));
			}
		}

		return contours;
	}

}
