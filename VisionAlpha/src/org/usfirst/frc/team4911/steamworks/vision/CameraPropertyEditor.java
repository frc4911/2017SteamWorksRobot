package org.usfirst.frc.team4911.steamworks.vision;

import java.awt.FlowLayout;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.opencv.videoio.VideoCapture;

public class CameraPropertyEditor extends JFrame {
	Logger log = Logger.getLogger(CameraPropertyEditor.class.getName());

	private static final long serialVersionUID = -1389496302357000704L;

	private VideoCapture camera;

	public CameraPropertyEditor(VideoCapture camera) {
		super("Camera");
		this.camera = camera;
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		setContentPane(content);
		createSlider("AutoExposure", VideoConstants.CV_CAP_PROP_AUTO_EXPOSURE, 0, 0, 1, .25);
		createSlider("Exposure", VideoConstants.CV_CAP_PROP_EXPOSURE, 0, .5, 1, .05);
		createSlider("Gain", VideoConstants.CV_CAP_PROP_GAIN, 0, .5, 1, .05);
		createSlider("Brightness", VideoConstants.CV_CAP_PROP_BRIGHTNESS, 0, .5, 1, .05);
		createSlider("Contrast", VideoConstants.CV_CAP_PROP_CONTRAST, 0, .5, 1, .05);

		pack();
		setVisible(true);
	}

	private void createSlider(String label, int cvCapProp, double min, double value, double max, double d) {
		int imin = (int) (min / d);
		int imax = (int) (max / d);
		int ivalue = (int) (value / d);
		JSlider slider = new JSlider(imin, imax, ivalue);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		int range = imax - imin;
		if (range < 20) {
			slider.setMajorTickSpacing(1);
		} else {
			slider.setMinorTickSpacing(1);
			slider.setMajorTickSpacing(range / 20);
		}

		log.info(String.format("imin=%s, imax=%s, value=%s, delta=%s", imin, imax, ivalue,
				slider.getMajorTickSpacing()));
		slider.addChangeListener(e -> {
			double newValue = ((JSlider) e.getSource()).getValue() * d;
			log.info(String.format("%s=%s", label, newValue));
			camera.set(cvCapProp, newValue);
		});
		addLabeled(label, slider);
	}

	private void createSlider(String label, int cvCapProp, int min, int value, int max) {
		JSlider slider = new JSlider(min, max, value);
		slider.addChangeListener(e -> {
			int newValue = ((JSlider) e.getSource()).getValue();
			log.info(String.format("%s=%s", label, newValue));
			camera.set(cvCapProp, newValue);
		});
		slider.setMajorTickSpacing(1);
		slider.setSnapToTicks(true);
		addLabeled(label, slider);
	}

	private void addLabeled(String label, JSlider slider) {
		JPanel panel = new JPanel(new FlowLayout());

		JLabel jlabel = new JLabel(label);
		panel.add(jlabel);

		panel.add(slider);
		getContentPane().add(panel);
	}

}
