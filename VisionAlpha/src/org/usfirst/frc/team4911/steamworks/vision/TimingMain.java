package org.usfirst.frc.team4911.steamworks.vision;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TimingMain {
	private static String R_LEFT_L = "/retro-samples/sample-rotate/left_large.jpg";
	private static String R_LEFT_M = "/retro-samples/sample-rotate/left_med.jpg";
	private static String R_LEFT_S = "/retro-samples/sample-rotate/left_small.jpg";
	private static String R_STRAIGHT = "/retro-samples/sample-rotate/straight.jpg";
	private static String R_RIGHT_S = "/retro-samples/sample-rotate/right_small.jpg";
	private static String R_RIGHT_M = "/retro-samples/sample-rotate/right_med.jpg";
	private static String R_RIGHT_L = "/retro-samples/sample-rotate/right_large.jpg";

	public static void main(String[] args) {
		new TimingMain().run();
	}

	private GearVision vision;
	private List<Mat> rawImages;
	private List<Mat> rawPresizedImages;

	public TimingMain() {
		this.vision = new GearVision(false);
		this.rawImages = Arrays.asList(R_LEFT_L, R_LEFT_M, R_LEFT_S, R_STRAIGHT, R_RIGHT_S, R_RIGHT_M, R_RIGHT_L)
				.stream().map(path -> Imgcodecs.imread(TimingMain.class.getResource(path).getPath()))
				.collect(Collectors.toList());

		this.rawPresizedImages = this.rawImages.stream().map(mat -> {
			Mat resized = new Mat();
			Imgproc.resize(mat, resized, new Size(640, 480), 0, 0, Imgproc.INTER_NEAREST);
			return resized;
		}).collect(toList());

	}

	public void run() {
		// calculateMovement(vision, rawImages);
		timing();
	}

	private class CalculateMovementNaive implements Detector {
		Mat image = new Mat();

		@Override
		public Object run(Mat rawImage) {
			Rect boundingRect = null;

			Imgproc.resize(rawImage, image, new Size(640, 480));

			List<MatOfPoint> contours = vision.detectMarkers(image);

			double max1area = 0d, max2area = 0d;
			MatOfPoint max1 = null, max2 = null;
			for (MatOfPoint c : contours) {
				double area = Imgproc.contourArea(c);
				if (area > max1area) {
					max1area = area;
					max1 = c;
				} else if (area > max2area) {
					max2area = area;
					max2 = c;
				}
			}

			MatOfPoint2f approxCurve = new MatOfPoint2f();
			Imgproc.approxPolyDP(new MatOfPoint2f(max1.toArray()), approxCurve, 3, true);
			boundingRect = Imgproc.boundingRect(new MatOfPoint(approxCurve.toArray()));
			return boundingRect;
		}
	}

	private class CalculateMovementFast implements Detector {
		Mat image = new Mat();
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		MatOfPoint intApproxCurve = new MatOfPoint();
		MatOfPoint2f max1_2f = new MatOfPoint2f();

		final int intBuff[] = new int[1024 * 2];
		final float floatBuff[] = new float[1024 * 2];

		@Override
		public Object run(Mat rawImage) {
			Rect boundingRect = null;

			Imgproc.resize(rawImage, image, new Size(640, 480));

			List<MatOfPoint> contours = vision.detectMarkers(image);

			double max1area = 0d, max2area = 0d;
			MatOfPoint max1 = null, max2 = null;
			for (MatOfPoint c : contours) {
				double area = Imgproc.contourArea(c);
				if (area > max1area) {
					max1area = area;
					max1 = c;
				} else if (area > max2area) {
					max2area = area;
					max2 = c;
				}
			}

			copy(max1, max1_2f);
			Imgproc.approxPolyDP(max1_2f, approxCurve, 3, true);
			copy(approxCurve, intApproxCurve);
			boundingRect = Imgproc.boundingRect(intApproxCurve);
			return boundingRect;
		}

		public void copy(MatOfPoint src, MatOfPoint2f dest) {
			final int num = (int) src.total();

			dest.alloc(num);
			src.get(0, 0, intBuff);
			for (int i = 0; i < num; i++) {
				floatBuff[2 * i] = intBuff[i * 2];
				floatBuff[2 * i + 1] = intBuff[i * 2 + 1];
			}
			dest.put(0, 0, floatBuff);
		}

		public void copy(MatOfPoint2f src, MatOfPoint dest) {
			final int num = (int) src.total();

			dest.alloc(num);
			src.get(0, 0, floatBuff);
			for (int i = 0; i < num; i++) {
				intBuff[2 * i] = (int) floatBuff[i * 2];
				intBuff[2 * i + 1] = (int) floatBuff[i * 2 + 1];
			}
			dest.put(0, 0, intBuff);
		}
	}

	private class FastContours implements Detector {
		Mat image = new Mat(); // use same target matrix for resized image

		@Override
		public Object run(Mat rawImage) {
			double max = 0;

			Imgproc.resize(rawImage, image, new Size(640, 480), 0, 0, Imgproc.INTER_NEAREST);
			List<MatOfPoint> contours = vision.detectMarkersFast(image);

			for (MatOfPoint c : contours) {
				max = Imgproc.contourArea(c);
			}
			return max;
		}
	}

	private void timing() {
		Detector[] detectors = {
				// new FastContours(),
				new CalculateMovementNaive(), new CalculateMovementFast() };
		for (int n = 0; n < 5; n++) {
			System.out.println(n);
			for (Detector detector : detectors) {
				time(detector);
			}
		}
	}

	private static interface Detector {
		public Object run(Mat rawImage);
	}

	private void time(Detector detector) {
		final int count = 1000;

		long totalEllapsed = 0;
		for (int i = 0; i < count; i++) {
			long start = System.nanoTime();
			for (Mat rawImage : rawImages) {
				detector.run(rawImage);
			}
			long stop = System.nanoTime();
			// System.out.print(".");
			totalEllapsed += (stop - start);
		}

		double avg = (totalEllapsed / rawImages.size() / count) / (1000_000d);
		System.out.println(
				"  " + detector.getClass().getSimpleName() + ": Avg (ms): " + avg + ", FPS: " + 1 / (avg / 1000d));
	}

	private Object naiveContours() {
		double max = 0;
		for (Mat rawImage : rawImages) {
			Mat image = new Mat();
			Imgproc.resize(rawImage, image, new Size(640, 480));

			List<MatOfPoint> contours = vision.detectMarkers(image);

			for (MatOfPoint c : contours) {
				max = Imgproc.contourArea(c);
			}
		}
		return max;
	}

	private Object contoursPresized() {
		double max = 0;
		Rect boundingRect = null;
		for (Mat image : rawPresizedImages) {
			List<MatOfPoint> contours = vision.detectMarkersFast(image);

			for (MatOfPoint c : contours) {
				max = Imgproc.contourArea(c);
			}

			MatOfPoint2f approxCurve = new MatOfPoint2f();
			Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(0).toArray()), approxCurve, 3, true);
			boundingRect = Imgproc.boundingRect(new MatOfPoint(approxCurve.toArray()));

		}
		return boundingRect;

	}

	/**
	 * Simply load up some of the opencv packages and verify that things are
	 * installed correctly.
	 */
	public static void openCVInstallCheck() {
		System.out.println("Welcome to OpenCV " + Core.VERSION);
		Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
		System.out.println("OpenCV Mat: " + m);
		Mat mr1 = m.row(1);
		mr1.setTo(new Scalar(1));
		Mat mc5 = m.col(5);
		mc5.setTo(new Scalar(5));
		System.out.println("OpenCV Mat data:\n" + m.dump());
	}
}
