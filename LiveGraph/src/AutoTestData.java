
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextField;
import javax.swing.JTextPane;

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
		frame.setBounds(100, 100, 840, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAutoTest = new JLabel("Auto Test");
		lblAutoTest.setBounds(105, 21, 305, 87);
		lblAutoTest.setFont(new Font("Tahoma", Font.PLAIN, 72));
		
		JLabel lblCurrentSubsystem = new JLabel("Current Subsystem");
		lblCurrentSubsystem.setBounds(422, 21, 178, 26);
		
		JLabel lblDTLDirection = new JLabel("Direction");
		lblDTLDirection.setBounds(612, 21, 83, 26);
		
		JLabel lblTalonvalue = new JLabel("TalonValue");
		lblTalonvalue.setBounds(21, 170, 103, 26);
		
		JLabel lblStickyfaults = new JLabel("StickyFaults");
		lblStickyfaults.setBounds(21, 214, 109, 26);
		
		JLabel lblOutcurrent = new JLabel("OutCurrent");
		lblOutcurrent.setBounds(21, 258, 105, 26);
		
		JLabel lblOutvolts = new JLabel("OutVolts");
		lblOutvolts.setBounds(21, 302, 79, 26);
		
		txtCurrsub = new JTextField();
		txtCurrsub.setBounds(422, 65, 186, 32);
		txtCurrsub.setText("currSub");
		txtCurrsub.setColumns(10);
		
		txtDTLDir = new JTextField();
		txtDTLDir.setBounds(612, 65, 186, 32);
		txtDTLDir.setText("dir");
		txtDTLDir.setColumns(10);
		
		txtTalonval = new JTextField();
		txtTalonval.setBounds(224, 167, 186, 32);
		txtTalonval.setText("talonVal");
		txtTalonval.setColumns(10);
		
		txtStickyfau = new JTextField();
		txtStickyfau.setBounds(224, 211, 186, 32);
		txtStickyfau.setText("stickyFau");
		txtStickyfau.setColumns(10);
		
		txtOutcur = new JTextField();
		txtOutcur.setBounds(224, 255, 186, 32);
		txtOutcur.setText("outCur");
		txtOutcur.setColumns(10);
		
		txtOutvol = new JTextField();
		txtOutvol.setBounds(224, 299, 186, 32);
		txtOutvol.setText("outVol");
		txtOutvol.setColumns(10);
		
		JLabel lblFstickyfaults = new JLabel("fStickyFaults");
		lblFstickyfaults.setBounds(21, 346, 116, 26);
		
		JLabel lblFoutcurrent = new JLabel("fOutCurrent");
		lblFoutcurrent.setBounds(21, 390, 112, 26);
		
		JLabel lblFoutvolts = new JLabel("fOutVolts");
		lblFoutvolts.setBounds(21, 434, 86, 26);
		
		txtFstickyfau = new JTextField();
		txtFstickyfau.setBounds(224, 343, 186, 32);
		txtFstickyfau.setText("fStickyFau");
		txtFstickyfau.setColumns(10);
		
		txtFoutcur = new JTextField();
		txtFoutcur.setBounds(224, 387, 186, 32);
		txtFoutcur.setText("fOutCur");
		txtFoutcur.setColumns(10);
		
		txtFoutvol = new JTextField();
		txtFoutvol.setBounds(224, 431, 186, 32);
		txtFoutvol.setText("fOutVol");
		txtFoutvol.setColumns(10);
		
		JLabel lblEncposition = new JLabel("EncPosition");
		lblEncposition.setBounds(21, 478, 106, 26);
		
		JLabel lblTargetposition = new JLabel("TargetPosition");
		lblTargetposition.setBounds(21, 519, 133, 26);
		
		JLabel lblEncerror = new JLabel("EncError");
		lblEncerror.setBounds(21, 569, 81, 26);
		
		txtTargetpos = new JTextField();
		txtTargetpos.setBounds(224, 519, 186, 32);
		txtTargetpos.setText("targetPos");
		txtTargetpos.setColumns(10);
		
		txtEncerr_1 = new JTextField();
		txtEncerr_1.setBounds(224, 566, 186, 32);
		txtEncerr_1.setText("encErr");
		txtEncerr_1.setColumns(10);
		
		txtEncpos = new JTextField();
		txtEncpos.setBounds(224, 475, 186, 32);
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
		
		String dtl = "DriveTrainLeft ";
		String dtr = "DriveTrainRight ";
		
		
		String talonVal = "TalonValue ";
		String sticky = "StickyFaults ";
		String volt = "OutVolt ";
		String curr = "OutCurr ";
		
		String encPos = "EncPos ";
		String target = "TargetPos ";
		String error = "EncError ";
		
		ActionListener taskPerformer = new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  	String currSub = table.getString("currSub", "null");
					boolean direction = table.getBoolean("Direction", true);
				  	
					txtCurrsub.setText(currSub);
					txtDTLDir.setText("" + direction);

					String desc = dtl + direction;
					
					// Power Input
					txtTalonval.setText(table.getString(talonVal + desc, "null"));
					
					// Controlled
					txtStickyfau.setText(table.getString(sticky + desc, "null"));
					txtOutcur.setText(table.getString(volt + desc, "null"));
					txtOutvol.setText(table.getString(curr + desc, "null"));
					
					// Follower
					txtFstickyfau.setText(table.getString(sticky + "f" + desc, "null"));
					txtFoutcur.setText(table.getString(volt + "f" + desc, "null"));
					txtFoutvol.setText(table.getString(curr + "f" + desc, "null"));
					
					// Encoder
					txtTargetpos.setText(table.getString(encPos + desc, "null"));
					txtEncerr_1.setText(table.getString(target + desc, "null"));						
					txtEncpos.setText(table.getString(error + desc, "null"));	
		      }
		  };
		  new Timer(delay, taskPerformer).start();
	}
}

