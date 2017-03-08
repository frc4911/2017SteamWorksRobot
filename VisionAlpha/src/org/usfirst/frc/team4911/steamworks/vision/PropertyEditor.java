package org.usfirst.frc.team4911.steamworks.vision;

import java.awt.FlowLayout;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PropertyEditor extends JFrame {
	Logger log = Logger.getLogger(PropertyEditor.class.getName());

	private static final long serialVersionUID = -1389496302357000704L;

	public PropertyEditor() {
		super("Properties");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		setContentPane(content);
		pack();
		setVisible(true);
	}
	
	public void addSlider(String label, int min, int value, int max, int delta, IntConsumer action) {
		addSlider(label, min, value, max, delta, (double d) -> action.accept((int) d));
	}

	public void addSlider(String label, double min, double value, double max, double delta, DoubleConsumer action) {
		int imin = (int) (min / delta);
		int imax = (int) (max / delta);
		int ivalue = (int) (value / delta);
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
			double newValue = ((JSlider) e.getSource()).getValue() * delta;
			log.info(String.format("%s=%s", label, newValue));
			action.accept(newValue);
			;
		});
		addLabeled(label, slider, value, delta);
		pack();
	}

	private void addLabeled(String label, JSlider slider, double current, double delta) {
		JPanel panel = new JPanel(new FlowLayout());

		JLabel jlabel = new JLabel(label);
		panel.add(jlabel);

		JLabel valueLabel = new JLabel(String.valueOf(current));
		panel.add(valueLabel);
		slider.addChangeListener(e -> valueLabel.setText(String.valueOf(((JSlider) e.getSource()).getValue() * delta)));

		panel.add(slider);
		getContentPane().add(panel);
	}

	public void display() {
		// pack();
		// setVisible(true);
	}

}
