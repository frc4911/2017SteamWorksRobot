import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class AutoGUI {
	final int DELAY = 20; // time between tics time in miliseconds
	
	NetworkTable table;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoGUI window = new AutoGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutoGUI() {
		netTable();
		
		initialize();
	}
	
	public void netTable() {
		String ip = "10.49.11.84";
		String tableName = "Autonomous";

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		table = NetworkTable.getTable(tableName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAutonomous = new JLabel("Autonomous");
		lblAutonomous.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutonomous.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblAutonomous.setBounds(21, 21, 572, 87);
		frame.getContentPane().add(lblAutonomous);
		
		JRadioButton tglbtnPreset = new JRadioButton("Preset 1");
		tglbtnPreset.setSelected(true);
		tglbtnPreset.setFont(new Font("Tahoma", Font.PLAIN, 28));
		tglbtnPreset.setBounds(21, 129, 207, 35);
		frame.getContentPane().add(tglbtnPreset);
		
		JRadioButton tglbtnPreset_1 = new JRadioButton("Preset 2");
		tglbtnPreset_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		tglbtnPreset_1.setBounds(21, 185, 207, 35);
		frame.getContentPane().add(tglbtnPreset_1);
		
		JRadioButton tglbtnPreset_2 = new JRadioButton("Preset 3");
		tglbtnPreset_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		tglbtnPreset_2.setBounds(21, 241, 207, 35);
		frame.getContentPane().add(tglbtnPreset_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane.setBounds(249, 185, 344, 94);
		frame.getContentPane().add(textPane);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(249, 133, 344, 26);
		frame.getContentPane().add(lblDescription);
		
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(tglbtnPreset);
	    group.add(tglbtnPreset_1);
	    group.add(tglbtnPreset_2);
	    
	    JToggleButton lblGearIn = new JToggleButton("");
	    lblGearIn.setForeground(Color.BLACK);
	    lblGearIn.setEnabled(false);
	    lblGearIn.setBackground(Color.WHITE);
	    lblGearIn.setHorizontalAlignment(SwingConstants.CENTER);
	    lblGearIn.setFont(new Font("Tahoma", Font.PLAIN, 28));
	    lblGearIn.setBounds(614, 185, 290, 91);
	    frame.getContentPane().add(lblGearIn);
	    
	    JLabel lblGearIn_1 = new JLabel("Gear In");
	    lblGearIn_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
	    lblGearIn_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblGearIn_1.setBounds(614, 136, 290, 26);
	    frame.getContentPane().add(lblGearIn_1);
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					// NetworkTable IP
//				  	table.setIPAddress(lblIP.getText() + txtIP.getText());
				  	
					if(tglbtnPreset.isSelected()) {
						tglbtnPreset_1.setSelected(false);
						tglbtnPreset_2.setSelected(false);
						
						table.putNumber("preset", 1);
					} else if(tglbtnPreset_1.isSelected()) {
						tglbtnPreset.setSelected(false);
						tglbtnPreset_2.setSelected(false);
						
						table.putNumber("preset", 5);
					} else if(tglbtnPreset_2.isSelected()) {
						tglbtnPreset.setSelected(false);
						tglbtnPreset_1.setSelected(false);
						
						table.putNumber("preset", 6);
					} else {
						table.putString("preset", "no preset selected");
					}
					
//					textPane.setText(table.getString("description", null));
					textPane.setText(table.getString("preset", "no preset selected"));
					
					if(table.getBoolean("GearIn", false)) {
						lblGearIn.setBackground(Color.GREEN);
					} else {
						lblGearIn.setBackground(Color.RED);
					}
		      }
		  };
		  new Timer(DELAY, taskPerformer).start();
	}
}
