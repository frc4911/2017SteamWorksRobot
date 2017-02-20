package org.usfirst.frc.team4911.steamworks.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * This class is not thread-safe!
 */
public class GearVision {
	private static Logger log = Logger.getLogger(GearVision.class.getName());

	static {
		// Ensure the native library gets loaded
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private Mat image;
	private Mat dilateElement;
	private Mat erodeElement;
	private ArrayList<MatOfPoint> contours;
	private boolean debug;

	public GearVision(boolean enableDebug) {
		this.image = new Mat(); // this is not threadsafe!
		this.contours = new ArrayList<>();
		this.dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		this.erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));
		this.debug = enableDebug;
	}

	public List<MatOfPoint> detectMarkers(Mat cameraImage) {
		List<MatOfPoint> contours = new ArrayList<>();
		Mat image = new Mat();

		Imgproc.cvtColor(cameraImage, image, Imgproc.COLOR_RGB2GRAY);

		Imgproc.threshold(image, image, 155, 255, Imgproc.THRESH_BINARY);
		Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

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
				Imgproc.putText(cameraImage, info, p, Core.FONT_HERSHEY_PLAIN, 1, new Scalar(0, 0, 255));
			}
		}

		return contours;
	}

	public List<MatOfPoint> detectMarkersFast(Mat cameraImage) {
		contours.clear();

		Imgproc.cvtColor(cameraImage, image, Imgproc.COLOR_RGB2GRAY);

		Imgproc.threshold(image, image, 155, 255, Imgproc.THRESH_BINARY);

		Imgproc.erode(image, image, erodeElement);
		// Imgproc.erode(image, image, erodeElement);
		Imgproc.dilate(image, image, dilateElement);
		// Imgproc.dilate(image, image, dilateElement);

		Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

		return contours;
	}

}
