package org.usfirst.frc.team4911.opencv;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

/**
 * Utility class to show an OpenCV matrix in a GUI window.
 */
public class Imshow {
	public static class ImshowJFrame extends JFrame implements MouseListener {

		private ImageIcon icon;
		private JLabel label;
		private Mat rawImage;
		private String rawTitle;
		private String givenTitle = "";
		private String debugTitle = "";

		public ImshowJFrame(String title, Mat img) {
			super(title);
			this.rawTitle = title;
			this.getContentPane().setLayout(new FlowLayout());
			this.rawImage = img.clone();
			this.icon = new ImageIcon(toBufferedImage(img));
			this.label = new JLabel(icon);
			this.getContentPane().add(label);
			this.pack();
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			this.addMouseListener(this);
		}

		public void update(Mat img) {
			icon.setImage(toBufferedImage(img));
			repaint();
		}

		public void update(String title, Mat img) {
			this.givenTitle = title;
			this.setTitle(title + debugTitle);
			update(img);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Point point = label.getMousePosition(true);
			double[] pixel = rawImage.get(point.y, point.x);
			debugTitle = String.format(" - %s: x=%s, y=%s (%s)", rawTitle, point.x, point.y, Arrays.toString(pixel));
			this.setTitle(givenTitle + debugTitle);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	private static List<JFrame> windows = new ArrayList<>();

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
	 * @return
	 */
	public static ImshowJFrame show(Mat img) {
		return show("Image", img);
	}

	/**
	 * Show an opencv matrix as an image in its own window
	 * 
	 * @param title
	 *            a title for the window
	 * @param img
	 *            the matrix to show
	 */
	public static ImshowJFrame show(String title, Mat img) {
		ImshowJFrame frame = new ImshowJFrame(title, img);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		for (JFrame f : windows) {
			int newX = f.getX() + f.getWidth();
			int newY = f.getY();
			if (newX + frame.getWidth() <= screen.getWidth() && newY <= screen.getHeight()) {
				frame.setLocation(newX, newY);
			} else if (newX + frame.getWidth() > screen.getWidth()) {
				frame.setLocation(0, newY + f.getHeight());
			}
		}
		windows.add(frame);
		return frame;
	}
}
