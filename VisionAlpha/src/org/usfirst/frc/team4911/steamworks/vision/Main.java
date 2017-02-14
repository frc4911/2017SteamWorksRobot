package org.usfirst.frc.team4911.steamworks.vision;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4911.opencv.Imshow;
import org.usfirst.frc.team4911.opencv.Imshow.ImshowJFrame;

public class Main {
	private static final Logger log = Logger.getLogger("Main");

	private static String R_LEFT_L = "/gear-fake-retro-samples/sample-rotate/left_large.jpg";
	private static String R_LEFT_M = "/gear-fake-retro-samples/sample-rotate/left_med.jpg";
	private static String R_LEFT_S = "/gear-fake-retro-samples/sample-rotate/left_small.jpg";
	private static String R_STRAIGHT = "/gear-fake-retro-samples/sample-rotate/straight.jpg";
	private static String R_RIGHT_S = "/gear-fake-retro-samples/sample-rotate/right_small.jpg";
	private static String R_RIGHT_M = "/gear-fake-retro-samples/sample-rotate/right_med.jpg";
	private static String R_RIGHT_L = "/gear-fake-retro-samples/sample-rotate/right_large.jpg";

	public static void main(String[] args) {
		openCVInstallCheck();

		// new Main().runStaticImages();
		new Main().runCameraCapture();
	}

	private BoilerVision vision;
	private List<Mat> rawImages;

	public Main() {
		this.vision = new BoilerVision(false);
		this.rawImages = Arrays.asList(R_LEFT_L, R_LEFT_M, R_LEFT_S, R_STRAIGHT, R_RIGHT_S, R_RIGHT_M, R_RIGHT_L)
				.stream().map(path -> Highgui.imread(Main.class.getResource(path).getPath()))
				.collect(Collectors.toList());
	}

	public void runStaticImages() {
		Mat image = new Mat();
		for (Mat rawImage : rawImages) {
			Imgproc.resize(rawImage, image, new Size(640, 480));
			Point error = vision.calculateError(image, true);
			Point target = new Point(image.width() / 2, image.height() / 2);
			// Core.arrowedLine(image, target, new Point(target.x + error.x,
			// target.y + error.y), new Scalar(0, 255, 0));
			Core.arrowedLine(image, new Point(target.x - error.x, target.y - error.y), target, new Scalar(0, 255, 0));
			Imgproc.resize(image, image, new Size(320, 240));

			Imshow.show(image);
		}
	}

	public void runCameraCapture() {
		Mat image = new Mat();
		VideoCapture videoCapture = new VideoCapture(0);
		ImshowJFrame frame = null;

		while (true) {
			if (!videoCapture.read(image)) {
				continue;
			}
			
			long timer = System.currentTimeMillis();
			Imgproc.resize(image, image, new Size(640, 480));
			Point error = vision.calculateError(image, true);
			 timer = System.currentTimeMillis() - timer;
			Point target = new Point(image.width() / 2, image.height() / 2);
			// Core.arrowedLine(image, target, new Point(target.x + error.x,
			// target.y + error.y), new Scalar(0, 255, 0));
			Core.arrowedLine(image, new Point(target.x - error.x, target.y - error.y), target, new Scalar(0, 255, 0));

			// TODO make imshow accept an empty image...
			if (frame == null) {
				frame = Imshow.show(image);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else {
				frame.update("Targeting Error: elapsed=" + timer + "ms", image);
			}
		}
	}



	/**
	 * Simply load up some of the opencv packages and verify that things are
	 * installed correctly.
	 */
	public static void openCVInstallCheck() {
		System.out.println("Welcome to OpenCV " + Core.VERSION);
		try {
			Mat m = new Mat();
		} catch (UnsatisfiedLinkError e) {
			System.err.println(
					"OpenCV Native Libs were not linked yet. Calling: System.loadLibrary(Core.NATIVE_LIBRARY_NAME);");
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		}
		try {
			Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
			Mat mr1 = m.row(1);
			mr1.setTo(new Scalar(1));
			Mat mc5 = m.col(5);
			mc5.setTo(new Scalar(5));
		} catch (Exception e) {
			System.err.println("Failed to load opencv correctly.");
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
