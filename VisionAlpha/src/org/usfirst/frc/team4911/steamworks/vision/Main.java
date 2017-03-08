package org.usfirst.frc.team4911.steamworks.vision;

import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team4911.opencv.Imshow;
import org.usfirst.frc.team4911.opencv.Imshow.ImshowJFrame;

public class Main {
	private static final Logger log = Logger.getLogger("Main");

	public static void main(String[] args) throws Exception {
		openCVInstallCheck();

		Properties p = new Properties(System.getProperties());
		if (args.length > 0) {
			log.info("Reading properties from: " + args[0]);
			p.load(new FileInputStream(args[0]));
		}

		InetAddress broadcastAddress = InetAddress.getByName(p.getProperty("broadcastAddress", "10.49.11.5"));
		int broadcastPort = Integer
				.parseInt(p.getProperty("broadcastPort", Integer.toString(RemoteTrackingSender.DEFAULT_PORT)));
		new Main().runCameraCapture(broadcastAddress, broadcastPort);
	}

	private BoilerVision vision;
	private List<Mat> rawImages;

	public Main() {
		this.vision = new BoilerVision(1);
	}

	public void runCameraCapture(InetAddress broadcastAddress, int broadcastPort) throws InterruptedException {
		Mat image = new Mat();
		VideoCapture videoCapture = new VideoCapture(0);
		Debugger debugger = new Debugger(videoCapture, vision);

		RemoteTrackingSender tracker = new RemoteTrackingSender(broadcastAddress, broadcastPort);

		while (true) {
			debugger.captureTimer.start();
			if (!videoCapture.read(image)) {
				System.out.println("Failed to capture image");
				Thread.sleep(500);
				continue;
			}
			debugger.captureTimer.stop();

			debugger.processingTimer.start();
			Imgproc.resize(image, image, new Size(640, 480));
			Point error = vision.calculateError(image);
			debugger.processingTimer.stop();

			tracker.sendError(error);

			debugger.update(image, error);

		}
	}

	private static class Debugger {
		ImshowJFrame rawFrame = null;
		ImshowJFrame debugFrame = null;

		Timer captureTimer = new Timer();
		Timer processingTimer = new Timer();
		PropertyEditor propertyEditor;
		Mat rawImage = new Mat();
		Mat debugImage = new Mat();
		Mat debugChannel1 = new Mat();
		Mat debugChannel2 = new Mat();
		BoilerVision vision;

		public Debugger(VideoCapture videoCapture, BoilerVision vision) {
			this.vision = vision;
			if (!GraphicsEnvironment.isHeadless()) {
				// new CameraPropertyEditor(videoCapture);
				this.propertyEditor = new PropertyEditor();
				this.propertyEditor.addSlider("Hue Min", 0d, 25d, 255d, 1d, vision::setHueMin);
				this.propertyEditor.addSlider("Hue Max", 0d, 35d, 255d, 1d, vision::setHueMax);
				this.propertyEditor.addSlider("Sat Min", 0d, 25d, 255d, 1d, vision::setSatMin);
				this.propertyEditor.addSlider("Sat Max", 0d, 95d, 255d, 1d, vision::setSatMax);
				this.propertyEditor.addSlider("Dilate", 1, 12, 20, 1, vision::setDilateSize);
				this.propertyEditor.addSlider("Errode", 1, 6, 20, 1, vision::setErrodeSize);

				this.propertyEditor.display();
			}
		}

		public void update(Mat image, Point error) {
			// System.out.println("Targeting Error: capture=" +
			// captureTimer.elappsed() + "ms" + ", process="
			// + processingTimer.elappsed() + "ms, error=" + error);

			Point target = new Point(image.width() / 2, image.height() / 2);

			Imgproc.arrowedLine(image, target, new Point(target.x + error.x, target.y + error.y), new Scalar(0, 255, 0),
					3, 8, 0, .1);

			if (!GraphicsEnvironment.isHeadless()) {
				if (rawFrame == null) {
					rawFrame = Imshow.show(image);
					rawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					makeDebugImage();
					debugFrame = Imshow.show(debugImage);
					debugFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					rawFrame.update("Targeting Error: capture=" + captureTimer.elappsed() + "ms" + ", process="
							+ processingTimer.elappsed() + "ms", image);

					makeDebugImage();					
					debugFrame.update(debugImage);
				}
			}
		}

		private void makeDebugImage() {
			Core.extractChannel(vision.colorProcessedImage, debugChannel1, 0);
			Core.extractChannel(vision.colorProcessedImage, debugChannel2, 1);
			List<Mat> imgs = Arrays.asList(debugChannel1, debugChannel2, vision.thresholdedImage, vision.sizeAdjustedImage);
			for (Mat img : imgs) {
				Imgproc.resize(img, img, new Size(320, 240));
			}
			Core.hconcat(imgs, debugImage);
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
