package org.usfirst.frc.team4911.steamworks.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class VisionBase {

	protected void drawRect(Mat image, Rect rect) {
		if (rect == null) {
			return;
		}

		Core.rectangle(image, rect.br(), rect.tl(), new Scalar(255, 0, 0), 2);
	}

	protected Rect findBoundingRect(Rect a, Rect b) {
		if (a == null && b == null) {
			return new Rect(0, 0, 0, 0);
		} else if (a == null) {
			return b;
		} else if (b == null) {
			return a;
		}

		int x = Math.min(a.x, b.x);
		int y = Math.min(a.y, b.y);
		int x2 = Math.max(a.x + a.width, b.x + b.width);
		int y2 = Math.max(a.y + a.height, b.y + b.height);
		return new Rect(x, y, x2 - x, y2 - y);
	}

	protected Rect findBoundingRect(MatOfPoint contour) {
		if (contour == null) {
			return null;
		}
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		Imgproc.approxPolyDP(new MatOfPoint2f(contour.toArray()), approxCurve, 3, true);
		return Imgproc.boundingRect(new MatOfPoint(approxCurve.toArray()));
	}

}
