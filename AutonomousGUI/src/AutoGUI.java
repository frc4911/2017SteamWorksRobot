import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class AutoGUI {
	final int DELAY = 100;  //20; // time between tics time in milliseconds
	
	NetworkTable table;
	
	private JFrame frame;
	Random rnd = new Random(0);
	double minBattery = 100;
	double time = 0;
	CurrentTracker[] ct;


	String[] currentKeys = {
	"Climber current leader", 
	"Climber current follower",
	"DriveTrainLeft current leader",
	"DriveTrainLeft current follower",
	"DriveTrainRight current leader",
	"DriveTrainRight current follower",
	"FuelCollector current",
	"ShooterFeeder current",
	"Hopper current",
	"ShooterFlywheel current leader",
	"ShooterFlywheel current follower",
	"GearIntake current",
	"GearLift current",
	"Battery amps",
	};
			
	String[] encoderKeys = {
	"DriveTrainLeft encoder",
	"DriveTrainRight encoder",
	"ShooterFlywheel encoder",
	"GearLift encoder",
	"Battery volts",
	};
	
	String[] currentDesc = {
	"Climber      ", 
	"Climber f    ",
	"Drive Left   ",
	"Drive Left f ",
	"Drive Right  ",
	"Drive Right f",
	"Collector    ",
	"Feeder       ",
	"Hopper       ",
	"Flywheel     ",
	"Flywheel f   ",
	"GearIntake   ",
	"GearLift     ",
	"Total        ",
	 };
	
	String[] encoderDesc = {
	"Drive Left",
	"Drive Right",
	"Flywheel",
	"GearLift",
	};
	
	/**
	 * auto select
	 * gear in/out
	 * match time
	 * motor currents stats
	 * motor encoders
	 * camera
	 * pid progress
	 * csv logging
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (InstantiationException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IllegalAccessException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	            try {
//	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//	                ex.printStackTrace();
//	            }

	            //setDefaultSize(24);
//
//	            JFrame frame = new JFrame("Testing frame");
//	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	            frame.add(new JLabel("Hello world"));
//	            frame.pack();
//	            frame.setLocationRelativeTo(null);
//	            frame.setVisible(true);
				
				try {
					AutoGUI window = new AutoGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public static void setDefaultSize(int size) {
//
//	    Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
//	    Object[] keys = keySet.toArray(new Object[keySet.size()]);
//
//	    for (Object key : keys) {
//
//	        if (key != null && key.toString().toLowerCase().contains("font")) {
//
//	            System.out.println(key);
//	            Font font = UIManager.getDefaults().getFont(key);
//	            if (font != null) {
//	                font = font.deriveFont((float)size);
//	                UIManager.put(key, font);
//	            }
//
//	        }
//
//	    }
//	}

	/**
	 * Create the application.
	 */
	public AutoGUI() {
		netTable();
	
		initialize();
	}
	
	public void netTable() {
		String ip = "roborio-4911-frc.local";//10.49.11.84";
		String tableName = "SmartDashboard";

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		table = NetworkTable.getTable(tableName);
	}

	double scale = 1.5;

	int c(int v){
		
		return (int)(v*scale);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ct = new CurrentTracker[currentDesc.length];
		
		for (int i=0; i<currentDesc.length; i++){
			ct[i] = new CurrentTracker(currentDesc[i]); 
		}	
				
		// window size and position
		frame = new JFrame();
		frame.setBounds(0, c(160), c(780), c(700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		int gap = c(5);
		int theX = gap;
		int theY = 0;
		int theWidth = 0;
		int theHeight = 0;

		theWidth = c(290);
		theHeight = c(72);
	    JLabel lblTime = new JLabel("Match Time");
	    lblTime.setFont(new Font("Tahoma", Font.PLAIN, c(72)));
	    lblTime.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTime.setBounds(theX, theY, theWidth+c(440), theHeight);
	    frame.getContentPane().add(lblTime);
		
	    theY += theHeight + c(5)*gap;
	    theHeight = c(50);
		JLabel lblAutonomous = new JLabel("Autonomous");
		lblAutonomous.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutonomous.setVerticalAlignment(SwingConstants.TOP);
		lblAutonomous.setFont(new Font("Tahoma", Font.PLAIN, c(48)));
		lblAutonomous.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(lblAutonomous);
		
	    theY += theHeight + gap;
	    theHeight = c(65);
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, c(24)));
		textPane.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(textPane);
		
	    theY += theHeight + gap;
	    theHeight = c(35);
		JRadioButton tglbtnAuto_0 = new JRadioButton("Auto 0");
		tglbtnAuto_0.setSelected(true);
		tglbtnAuto_0.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_0.setBounds(theX, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_0);

		JRadioButton tglbtnAuto_5 = new JRadioButton("Auto 5");
		tglbtnAuto_5.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_5.setBounds(theX+theWidth/2, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_5);

	    theY += theHeight + gap;
	    theHeight = c(35);
		JRadioButton tglbtnAuto_6 = new JRadioButton("Auto 6");
		tglbtnAuto_6.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_6.setBounds(theX, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_6);
		
		JRadioButton tglbtnAuto_7 = new JRadioButton("Auto 7");
		tglbtnAuto_7.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_7.setBounds(theX+theWidth/2, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_7);
		
	    theY += theHeight + gap;
	    theHeight = c(35);
		JRadioButton tglbtnAuto_30 = new JRadioButton("Auto 30");
		tglbtnAuto_30.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_30.setBounds(theX, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_30);
		
		JRadioButton tglbtnAuto_32 = new JRadioButton("Auto 32");
		tglbtnAuto_32.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_32.setBounds(theX+theWidth/2, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_32);

	    theY += theHeight + gap;
	    theHeight = c(35);		
		JRadioButton tglbtnAuto_40 = new JRadioButton("Auto 40");
		tglbtnAuto_40.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_40.setBounds(theX, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_40);
		
		JRadioButton tglbtnAuto_42 = new JRadioButton("Auto 42");
		tglbtnAuto_42.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
		tglbtnAuto_42.setBounds(theX+theWidth/2, theY, theWidth/2, theHeight);
		frame.getContentPane().add(tglbtnAuto_42);
				
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(tglbtnAuto_0);
	    group.add(tglbtnAuto_5);
	    group.add(tglbtnAuto_6);
	    group.add(tglbtnAuto_7);
	    group.add(tglbtnAuto_30);
	    group.add(tglbtnAuto_32);
	    group.add(tglbtnAuto_40);
	    group.add(tglbtnAuto_42);
	    
	    theY += theHeight + 10*gap;
	    theHeight = c(52);
	    JLabel lblGearIn_1 = new JLabel("Gear Out");
	    lblGearIn_1.setFont(new Font("Tahoma", Font.PLAIN, c(28)));
	    lblGearIn_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblGearIn_1.setBounds(theX, theY, theWidth, theHeight);
	    lblGearIn_1.setBackground(Color.RED);
	    lblGearIn_1.setOpaque(true);
	    frame.getContentPane().add(lblGearIn_1);
		
	    theY += theHeight + 10*gap;
	    theHeight = c(52);
	    JButton btn = new JButton("Reset Stats");
	    btn.setBounds(theX+theWidth/4, theY, theWidth/2, theHeight);
	    btn.setVisible(true);
	    btn.addActionListener(new ButtonListener());
	    frame.getContentPane().add(btn);
	    
	    // reset for 2nd column
	    theY = c(97);
	    theX += c(300)+gap;
	    theWidth = c(440);
	    theHeight = c(50);
		JLabel lblCurrents = new JLabel("Motor Current");
		lblCurrents.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrents.setVerticalAlignment(SwingConstants.TOP);
		lblCurrents.setFont(new Font("Tahoma", Font.PLAIN, c(48)));
		lblCurrents.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(lblCurrents);
		
	    theY += theHeight + gap;
	    theHeight = c(340);
		JTextPane currentReport = new JTextPane();
		currentReport.setFont(new Font("Courier New", Font.BOLD, c(18)));
		currentReport.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(currentReport);

	    theY += theHeight + gap;
	    theHeight = c(54);
		JLabel lblEncoders = new JLabel("Encoders/Battery");
		lblEncoders.setHorizontalAlignment(SwingConstants.LEFT);
		lblEncoders.setVerticalAlignment(SwingConstants.TOP);
		lblEncoders.setFont(new Font("Tahoma", Font.PLAIN, c(48)));
		lblEncoders.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(lblEncoders);
		
	    theY += theHeight + gap;
	    theHeight = c(90);
		JTextPane encodersReport = new JTextPane();
		encodersReport.setFont(new Font("Courier New", Font.BOLD, c(18)));
		encodersReport.setBounds(theX, theY, theWidth, theHeight);
		frame.getContentPane().add(encodersReport);

		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				  	String autoDescription = "";
				  	int autoNumber = 0;
				  	
					if(tglbtnAuto_0.isSelected()) {
						autoNumber = 0;
						autoDescription = "none";
					} else { 
						tglbtnAuto_0.setSelected(false);
					}
					
					if(tglbtnAuto_5.isSelected()) {
						autoNumber = 5;
						autoDescription = "Gear only left\nbumper 69\" from wall";
					} else { 
						tglbtnAuto_5.setSelected(false);
					}
					
					if(tglbtnAuto_6.isSelected()) {
						autoNumber = 6;
						autoDescription = "Gear only center\ngear aligned with spring";
					} else { 
						tglbtnAuto_6.setSelected(false);
					}
					
					if(tglbtnAuto_7.isSelected()) {
						autoNumber = 7;
						autoDescription = "Gear only right\nbumper 69\" from wall";
					} else { 
						tglbtnAuto_7.setSelected(false);
					}
					
					if(tglbtnAuto_30.isSelected()) {
						autoNumber = 30;
						autoDescription = "Shoot only right\nbumper at corner of boiler";
					} else { 
						tglbtnAuto_30.setSelected(false);
					}
					
					if(tglbtnAuto_32.isSelected()) {
						autoNumber = 32;
						autoDescription = "Gear and shoot right\nbumper 69\" from wall";
					} else { 
						tglbtnAuto_32.setSelected(false);
					}
					
					if(tglbtnAuto_40.isSelected()) {
						autoNumber = 40;
						autoDescription = "Shoot only left\nbumper at corner of boiler";
					} else { 
						tglbtnAuto_40.setSelected(false);
					}
					
					if(tglbtnAuto_42.isSelected()) {
						autoNumber = 42;
						autoDescription = "Gear and shoot left\nbumper 69\" from wall";
					} else { 
						tglbtnAuto_42.setSelected(false);
					}
					
					textPane.setText(table.getString("Auto", autoDescription));
					table.putNumber("Auto choice", autoNumber);
					
					double autoChoice = table.getNumber("Auto choice", -1);
					
					if (autoChoice != autoNumber){
						textPane.setBackground(Color.RED);
					}
					else {
						textPane.setBackground(Color.WHITE);
					}
					
					if(table.getBoolean("GearIn", false)) {
						lblGearIn_1.setText("Gear Out");
						lblGearIn_1.setBackground(Color.RED);
					} else {
						lblGearIn_1.setText("Gear In");
						lblGearIn_1.setBackground(Color.GREEN);
					}
					
					
					String currents = "               now   max   ave   max\n                                 ave\n";
					
					for (int i=0; i<currentKeys.length;i++){
						currents += ct[i].update(table.getNumber(currentKeys[i], -i))+"\n";
						//currents += ct[i].update(table.getNumber("test",-i))+"\n";
					}
					//table.putNumber("test", rnd.nextDouble());
					currentReport.setText(currents);


					String encoders = "";
					encoders += encoderDesc[0]+"/"+"right  " + String.format("%6d  %6d\n",(int)table.getNumber(encoderKeys[0],-1),(int)table.getNumber(encoderKeys[1],-1));
					encoders += encoderDesc[2]+"/"+encoderDesc[3] + " "  + String.format("%6d  %6d\n",(int)table.getNumber(encoderKeys[2],-1),(int)table.getNumber(encoderKeys[3],-1));
					
					double battery = table.getNumber("Battery volts",100);
					if (battery < minBattery){
						minBattery = battery;
					}
					
					encoders += "                              min\n";
					encoders += "Battery volts      " + String.format("%4.1f      %4.1f\n", battery, minBattery);
					encodersReport.setText(encoders);
					
					time += .1;

					lblTime.setText(String.format("%4.1f", table.getNumber("Match time",time)));

			}
		  };
		  new Timer(DELAY, taskPerformer).start();
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			minBattery = 100;
			for (int i=0; i<currentKeys.length;i++){
				ct[i].clear();
			}
			
		}
		
	}

}

class CurrentTracker {
	public String name;
	double max=0;
	double now=0;
	double sum=0;
	double maxSum=0;
	int index = 0;
	
	double[] history = new double[3*10];
	
	public CurrentTracker(String name){
		this.name = name;
	}
	
	public void clear(){
		index = 0;
		max = 0;
		maxSum = 0;
		sum = 0;
		for (int i=0; i<history.length; i++){
			history[i] = 0;
		}
	}
	public String update(double newValue){
		now = newValue;

		index++;
		sum -= history[index%history.length];
		history[index%history.length] = now;
		sum += now;
		
		if (sum > maxSum){
			maxSum = sum;
		}
		if (now > max){
			max = now;
		}
		
		return name + " " + String.format("%4.1f, %4.1f, %4.1f, %4.1f",(float)now, (float)max, (float)(sum/history.length), (float)(maxSum/history.length));
	}	
}