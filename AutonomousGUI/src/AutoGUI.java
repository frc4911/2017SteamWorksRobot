import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;

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
		frame.setBounds(100, 100, 640, 640);
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
	    
	    JLabel lblCustomCode = new JLabel("Custom Code");
	    lblCustomCode.setFont(new Font("Tahoma", Font.PLAIN, 28));
	    lblCustomCode.setBounds(21, 339, 207, 26);
	    frame.getContentPane().add(lblCustomCode);
	    
	    JButton btnCommand = new JButton("Command 1");
	    btnCommand.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnCommand.setBounds(21, 402, 207, 35);
	    frame.getContentPane().add(btnCommand);
	    
	    JButton btnCommand_1 = new JButton("Command 2");
	    btnCommand_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnCommand_1.setBounds(21, 458, 207, 35);
	    frame.getContentPane().add(btnCommand_1);
	    
	    JButton btnCommand_2 = new JButton("Command 3");
	    btnCommand_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnCommand_2.setBounds(21, 514, 207, 35);
	    frame.getContentPane().add(btnCommand_2);
	    
	    JButton btnRemove = new JButton("Revert");
	    btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnRemove.setBounds(249, 338, 191, 35);
	    frame.getContentPane().add(btnRemove);
	    
	    JToggleButton tglbtnClear = new JToggleButton("Clear");
	    tglbtnClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    tglbtnClear.setBounds(461, 338, 132, 35);
	    frame.getContentPane().add(tglbtnClear);
	    
	    TextArea textArea = new TextArea();
	    textArea.setBounds(249, 402, 344, 145);
	    frame.getContentPane().add(textArea);
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					// NetworkTable IP
//				  	table.setIPAddress(lblIP.getText() + txtIP.getText());
				  	
					if(tglbtnPreset.isSelected()) {
						tglbtnPreset_1.setSelected(false);
						tglbtnPreset_2.setSelected(false);
						
						table.putString("preset", "preset1");
					} else if(tglbtnPreset_1.isSelected()) {
						tglbtnPreset.setSelected(false);
						tglbtnPreset_2.setSelected(false);
						
						table.putString("preset", "preset2");
					} else if(tglbtnPreset_2.isSelected()) {
						tglbtnPreset.setSelected(false);
						tglbtnPreset_1.setSelected(false);
						
						table.putString("preset", "preset3");
					} else {
						table.putString("preset", "no preset selected");
					}
					
//					textPane.setText(table.getString("description", null));
					textPane.setText(table.getString("preset", "no preset selected"));
		      }
		  };
		  new Timer(DELAY, taskPerformer).start();
	}
}
