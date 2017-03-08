package org.usfirst.frc.team4911.steamworks.vision;

import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team4911.opencv.Imshow;
import org.usfirst.frc.team4911.opencv.Imshow.ImshowJFrame;

/**
 * Used for local testing.
 * 
 * @author nathanieltroutman
 */
public class LocalMain {
	private static final Logger log = Logger.getLogger("Main");

	private static String R_LEFT_L = "/gear-fake-retro-samples/sample-rotate/left_large.jpg";
	private static String R_LEFT_M = "/gear-fake-retro-samples/sample-rotate/left_med.jpg";
	private static String R_LEFT_S = "/gear-fake-retro-samples/sample-rotate/left_small.jpg";
	private static String R_STRAIGHT = "/gear-fake-retro-samples/sample-rotate/straight.jpg";
	private static String R_RIGHT_S = "/gear-fake-retro-samples/sample-rotate/right_small.jpg";
	private static String R_RIGHT_M = "/gear-fake-retro-samples/sample-rotate/right_med.jpg";
	private static String R_RIGHT_L = "/gear-fake-retro-samples/sample-rotate/right_large.jpg";

	public static void main(String[] args) throws Exception {
		openCVInstallCheck();

		// new Main().runStaticImages();

		Properties p = new Properties(System.getProperties());
		if (args.length > 0) {
			log.info("Reading properties from: " + args[0]);
			// p.load(Main.class.getResourceAsStream(args[0]));
			// System.out.println(Arrays.toString(new File(".").listFiles()));
			p.load(new FileInputStream(args[0]));
			p.list(System.out);
		}

		InetAddress broadcastAddress = InetAddress.getByName(p.getProperty("broadcastAddress", "10.49.11.5"));
		int broadcastPort = Integer
				.parseInt(p.getProperty("broadcastPort", Integer.toString(RemoteTrackingSender.DEFAULT_PORT)));
		new LocalMain().runCameraCapture(broadcastAddress, broadcastPort);
	}

	private BoilerVision vision;
	private List<Mat> rawImages;

	public LocalMain() {
		this.vision = new BoilerVision(1);
	}

	public void runStaticImages() {
		this.rawImages = // Arrays.asList(R_LEFT_L, R_LEFT_M, R_LEFT_S,
							// R_STRAIGHT, R_RIGHT_S, R_RIGHT_M, R_RIGHT_L)
				Arrays.asList("/foo.jpg", "/pi-sample.jpg").stream()
						.map(path -> Imgcodecs.imread(LocalMain.class.getResource(path).getPath()))
						.collect(Collectors.toList());

		Mat image = new Mat();
		for (Mat rawImage : rawImages) {
			// Imgproc.resize(rawImage, image, new Size(640, 480));
			// Imgproc.resize(rawImage, image, new Size((int) (640 * .75), (int)
			// (480 * .75)));
			image = rawImage;
			Point error = vision.calculateError(image);
			Point target = new Point(image.width() / 2, image.height() / 2);
			Imgproc.arrowedLine(image, target, new Point(target.x + error.x, target.y + error.y), new Scalar(0, 255, 0),
					5, 8, 0, .1);
			// Core.arrowedLine(image, new Point(target.x - error.x, target.y -
			// error.y), target, new Scalar(0, 255, 0));

			Imshow.show("Error", image);
		}
	}

	public void runCameraCapture(InetAddress broadcastAddress, int broadcastPort) throws InterruptedException {
		Mat image = new Mat();
		VideoCapture videoCapture = new VideoCapture(0);
		if (!GraphicsEnvironment.isHeadless()) {
			new CameraPropertyEditor(videoCapture);
		}

		videoCapture.set(VideoConstants.CV_CAP_PROP_EXPOSURE, 0);
		ImshowJFrame frame = null;

		RemoteTrackingSender tracker = new RemoteTrackingSender(broadcastAddress, broadcastPort);

		while (true) {
			long captureTimer = System.currentTimeMillis();
			if (!videoCapture.read(image)) {
				System.out.println("Failed to capture image");
				Thread.sleep(500);
				continue;
			}
			captureTimer = System.currentTimeMillis() - captureTimer;

			long processTimer = System.currentTimeMillis();
			Imgproc.resize(image, image, new Size(800, 600));
			Point error = vision.calculateError(image);
			processTimer = System.currentTimeMillis() - processTimer;
			Point target = new Point(image.width() / 2, image.height() / 2);
			tracker.sendError(error);
			System.out.println("Targeting Error: capture=" + captureTimer + "ms" + ", process=" + processTimer
					+ "ms, error=" + error);
			// Core.arrowedLine(image, new Point(target.x - error.x, target.y -
			// error.y), target, new Scalar(0, 255, 0));

			// Arrow from center to target, shows the direction to move the
			// bot/center
			Imgproc.arrowedLine(image, target, new Point(target.x + error.x, target.y + error.y), new Scalar(0, 255, 0),
					3, 8, 0, .1);

			// TODO make imshow accept an empty image...
			if (!GraphicsEnvironment.isHeadless()) {
				if (frame == null) {
					frame = Imshow.show(image);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					frame.update("Targeting Error: capture=" + captureTimer + "ms" + ", process=" + processTimer + "ms",
							image);
				}
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
