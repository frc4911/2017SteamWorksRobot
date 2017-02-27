
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.Font;
import javax.swing.Timer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;

public class AutoTestData {
	final int delay = 20; //milliseconds

	
	private JFrame frame;
	private JTextField txtCurrsub;
	private JTextField txtDTLDir;
	private JTextField txtTalonval;
	private JTextField txtStickyfau;
	private JTextField txtOutcur;
	private JTextField txtOutvol;

	private NetworkTable table;
	private JTextField txtFstickyfau;
	private JTextField txtFoutcur;
	private JTextField txtFoutvol;
	private JTextField txtTargetpos;
	private JTextField txtEncerr_1;
	private JTextField txtEncpos;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			AutoTestData window = new AutoTestData();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public AutoTestData() {
		netTable();

		initialize();
		
	}
	
	public void netTable() {
		String ip = "10.49.11.84";
		String tableName = "AutoTest";

		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		table = NetworkTable.getTable(tableName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAutoTest = new JLabel("Auto Test");
		lblAutoTest.setBounds(105, 21, 305, 87);
		lblAutoTest.setFont(new Font("Tahoma", Font.PLAIN, 72));
		
		JLabel lblCurrentSubsystem = new JLabel("Current Subsystem");
		lblCurrentSubsystem.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCurrentSubsystem.setBounds(422, 32, 186, 26);
		
		JLabel lblDTLDirection = new JLabel("Direction");
		lblDTLDirection.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDTLDirection.setBounds(612, 32, 186, 26);
		
		JLabel lblTalonvalue = new JLabel("TalonValue");
		lblTalonvalue.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTalonvalue.setBounds(21, 149, 182, 26);
		
		JLabel lblStickyfaults = new JLabel("StickyFaults");
		lblStickyfaults.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblStickyfaults.setBounds(21, 214, 182, 26);
		
		JLabel lblOutcurrent = new JLabel("OutCurrent");
		lblOutcurrent.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOutcurrent.setBounds(21, 258, 182, 26);
		
		JLabel lblOutvolts = new JLabel("OutVolts");
		lblOutvolts.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOutvolts.setBounds(21, 302, 182, 26);
		
		txtCurrsub = new JTextField();
		txtCurrsub.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtCurrsub.setBounds(422, 65, 186, 32);
		txtCurrsub.setText("currSub");
		txtCurrsub.setColumns(10);
		
		txtDTLDir = new JTextField();
		txtDTLDir.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtDTLDir.setBounds(612, 65, 186, 32);
		txtDTLDir.setText("dir");
		txtDTLDir.setColumns(10);
		
		txtTalonval = new JTextField();
		txtTalonval.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtTalonval.setBounds(224, 146, 186, 32);
		txtTalonval.setText("talonVal");
		txtTalonval.setColumns(10);
		
		txtStickyfau = new JTextField();
		txtStickyfau.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtStickyfau.setBounds(224, 211, 186, 32);
		txtStickyfau.setText("stickyFau");
		txtStickyfau.setColumns(10);
		
		txtOutcur = new JTextField();
		txtOutcur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtOutcur.setBounds(224, 255, 186, 32);
		txtOutcur.setText("outCur");
		txtOutcur.setColumns(10);
		
		txtOutvol = new JTextField();
		txtOutvol.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtOutvol.setBounds(224, 299, 186, 32);
		txtOutvol.setText("outVol");
		txtOutvol.setColumns(10);
		
		JLabel lblFstickyfaults = new JLabel("fStickyFaults");
		lblFstickyfaults.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFstickyfaults.setBounds(431, 214, 177, 26);
		
		JLabel lblFoutcurrent = new JLabel("fOutCurrent");
		lblFoutcurrent.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFoutcurrent.setBounds(431, 261, 177, 26);
		
		JLabel lblFoutvolts = new JLabel("fOutVolts");
		lblFoutvolts.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFoutvolts.setBounds(431, 302, 177, 26);
		
		txtFstickyfau = new JTextField();
		txtFstickyfau.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFstickyfau.setBounds(612, 211, 186, 32);
		txtFstickyfau.setText("fStickyFau");
		txtFstickyfau.setColumns(10);
		
		txtFoutcur = new JTextField();
		txtFoutcur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFoutcur.setBounds(612, 258, 186, 32);
		txtFoutcur.setText("fOutCur");
		txtFoutcur.setColumns(10);
		
		txtFoutvol = new JTextField();
		txtFoutvol.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFoutvol.setBounds(612, 299, 186, 32);
		txtFoutvol.setText("fOutVol");
		txtFoutvol.setColumns(10);
		
		JLabel lblEncposition = new JLabel("EncPosition");
		lblEncposition.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblEncposition.setBounds(21, 378, 182, 26);
		
		JLabel lblTargetposition = new JLabel("TargetPosition");
		lblTargetposition.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTargetposition.setBounds(21, 422, 182, 26);
		
		JLabel lblEncerror = new JLabel("EncError");
		lblEncerror.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblEncerror.setBounds(21, 466, 182, 26);
		
		txtTargetpos = new JTextField();
		txtTargetpos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtTargetpos.setBounds(224, 419, 186, 32);
		txtTargetpos.setText("targetPos");
		txtTargetpos.setColumns(10);
		
		txtEncerr_1 = new JTextField();
		txtEncerr_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEncerr_1.setBounds(224, 463, 186, 32);
		txtEncerr_1.setText("encErr");
		txtEncerr_1.setColumns(10);
		
		txtEncpos = new JTextField();
		txtEncpos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEncpos.setBounds(224, 375, 186, 32);
		txtEncpos.setText("EncPos");
		txtEncpos.setColumns(10);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblFoutcurrent);
		frame.getContentPane().add(lblFoutvolts);
		frame.getContentPane().add(lblFstickyfaults);
		frame.getContentPane().add(lblOutvolts);
		frame.getContentPane().add(lblOutcurrent);
		frame.getContentPane().add(lblStickyfaults);
		frame.getContentPane().add(lblTalonvalue);
		frame.getContentPane().add(txtTalonval);
		frame.getContentPane().add(txtStickyfau);
		frame.getContentPane().add(txtOutcur);
		frame.getContentPane().add(txtOutvol);
		frame.getContentPane().add(txtFstickyfau);
		frame.getContentPane().add(txtFoutcur);
		frame.getContentPane().add(txtFoutvol);
		frame.getContentPane().add(txtEncerr_1);
		frame.getContentPane().add(txtEncpos);
		frame.getContentPane().add(txtTargetpos);
		frame.getContentPane().add(lblAutoTest);
		frame.getContentPane().add(lblCurrentSubsystem);
		frame.getContentPane().add(txtCurrsub);
		frame.getContentPane().add(txtDTLDir);
		frame.getContentPane().add(lblDTLDirection);
		frame.getContentPane().add(lblEncposition);
		frame.getContentPane().add(lblTargetposition);
		frame.getContentPane().add(lblEncerror);
		
		JButton btnButton1 = new JButton("Button 1");
		btnButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.putBoolean("start", true);
			}
		});
		btnButton1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnButton1.setBounds(422, 145, 186, 35);
		frame.getContentPane().add(btnButton1);
		
		JButton btnButton2 = new JButton("Button 2");
		btnButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.putString("done", "complete");
			}
		});
		btnButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnButton2.setBounds(612, 145, 186, 35);
		frame.getContentPane().add(btnButton2);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setBounds(21, 109, 777, 19);
		frame.getContentPane().add(progressBar);
		
		final String TALONVALUE = "TalonValue ";
		final String STICKY = "StickyFaults ";
		final String VOLT = "OutVolt ";
		final String CURR = "OutCurr ";
		
		final String ENCPOS = "EncPos ";
		final String TARGET = "TargetPos ";
		final String ERROR = "EncError ";
		
//		final int PROGRESS = 13;
		final int PROGRESS = 6;

		
		ActionListener taskPerformer = new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  	String currSub = table.getString("currSub", null);
					boolean direction = table.getBoolean("Direction", true);
					
					txtCurrsub.setText(currSub);
					txtDTLDir.setText("" + direction);

					String desc = table.getString("Descrition", null);
					
					// Power Input
					txtTalonval.setText(table.getString(TALONVALUE + desc, null));
					
					// Controlled
					txtStickyfau.setText(table.getString(STICKY + desc, null));
					txtOutcur.setText(table.getString(VOLT + desc, null));
					txtOutvol.setText(table.getString(CURR + desc, null));
					
					// Follower
					txtFstickyfau.setText(table.getString(STICKY + "f" + desc, null));
					txtFoutcur.setText(table.getString(VOLT + "f" + desc, null));
					txtFoutvol.setText(table.getString(CURR + "f" + desc, null));
					
					// Encoder
					txtTargetpos.setText(table.getString(ENCPOS + desc, null));
					txtEncerr_1.setText(table.getString(TARGET + desc, null));						
					txtEncpos.setText(table.getString(ERROR + desc, null));	
					
					if(table.getBoolean("start", true)) {
						progressBar.setValue(0);
						table.putBoolean("start",  false);
					} else if(Objects.equals(table.getString("done", "incomplete"), "complete")) {
						progressBar.setValue(progressBar.getValue() + PROGRESS);
						table.putString("done", "incomplete");
					}
		      }
		  };
		  new Timer(delay, taskPerformer).start();
	}
	
}

