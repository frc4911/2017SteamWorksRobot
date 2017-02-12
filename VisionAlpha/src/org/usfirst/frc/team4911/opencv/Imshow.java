package org.usfirst.frc.team4911.opencv;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

/**
 * Utility class to show an OpenCV matrix in a GUI window.
 */
public class Imshow {
	/**
	 * Converts an opencv matrix into a buffered image for AWT/Swing.
	 * 
	 * @param m
	 *            matrix to convert
	 * @return buffered image representing the input matrix
	 */
	public static Image toBufferedImage(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}

	/**
	 * Show an opencv matrix as an image in its own window
	 * 
	 * @param img
	 *            the matrix to show
	 */
	public static void show(Mat img) {
		show("Image", img);
	}

	/**
	 * Show an opencv matrix as an image in its own window
	 * 
	 * @param title
	 *            a title for the window
	 * @param img
	 *            the matrix to show
	 */
	public static void show(String title, Mat img) {
		JFrame frame = new JFrame(title);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(toBufferedImage(img))));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
