
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.Font;
import javax.swing.Timer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.SwingConstants;

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
	private JTextField txtEncerr;
	private JTextField txtEncpos;
	private JTextField txtDrivetrainleftForward;
	private JTextField txtDrivetrainleftBackward;
	private JTextField txtDrivetrainrightForward;
	private JTextField txtDrivetrainrightBackward;
	private JTextField txtIP;
	private JTextField txtFuelcollectorIn;
	private JTextField txtFuelcollectorOut;
	private JTextField txtHopperOut;
	private JTextField txtHopperIn;
	private JTextField txtShooterfeederIn;
	private JTextField txtShooterfeederOut;
	private JTextField txtShooterflywheelOut;
	private JTextField txtShooterflywheelIn;
	private JLabel lblClimber;
	private JTextField txtClimberUp;
	private JLabel lblGearintake;
	private JTextField txtGearintakeIn;
	private JTextField txtGearintakeOut;
	private JLabel lblGearlift;
	private JTextField txtGearliftUp;
	private JTextField txtGearliftDown;
	private JLabel lblIp;

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
		frame.setBounds(100, 100, 845, 1160);
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
		lblFstickyfaults.setBounds(431, 217, 177, 26);
		
		JLabel lblFoutcurrent = new JLabel("fOutCurrent");
		lblFoutcurrent.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFoutcurrent.setBounds(431, 261, 177, 26);
		
		JLabel lblFoutvolts = new JLabel("fOutVolts");
		lblFoutvolts.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFoutvolts.setBounds(431, 302, 177, 26);
		
		txtFstickyfau = new JTextField();
		txtFstickyfau.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFstickyfau.setBounds(612, 215, 186, 32);
		txtFstickyfau.setText("fStickyFau");
		txtFstickyfau.setColumns(10);
		
		txtFoutcur = new JTextField();
		txtFoutcur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFoutcur.setBounds(612, 258, 186, 32);
		txtFoutcur.setText("fOutCur");
		txtFoutcur.setColumns(10);
		
		txtFoutvol = new JTextField();
		txtFoutvol.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFoutvol.setBounds(612, 300, 186, 32);
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
		
		txtEncerr = new JTextField();
		txtEncerr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEncerr.setBounds(224, 463, 186, 32);
		txtEncerr.setText("encErr");
		txtEncerr.setColumns(10);
		
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
		frame.getContentPane().add(txtEncerr);
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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setBounds(21, 109, 777, 19);
		frame.getContentPane().add(progressBar);
		
		JLabel lbltheGearLift = new JLabel("**The gear lift MUST be placed in\r\n");
		lbltheGearLift.setHorizontalAlignment(SwingConstants.LEFT);
		lbltheGearLift.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbltheGearLift.setForeground(new Color(255, 51, 51));
		lbltheGearLift.setBounds(431, 142, 367, 31);
		frame.getContentPane().add(lbltheGearLift);
		
		JLabel lblInTheLowered = new JLabel("the lowered position for auto test**\r\n");
		lblInTheLowered.setForeground(new Color(255, 51, 51));
		lblInTheLowered.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInTheLowered.setBounds(431, 169, 367, 32);
		frame.getContentPane().add(lblInTheLowered);
		
		JLabel lblDrivetrainleft = new JLabel("DriveTrainLeft");
		lblDrivetrainleft.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDrivetrainleft.setBounds(21, 584, 204, 26);
		frame.getContentPane().add(lblDrivetrainleft);
		
		JLabel lblForward = new JLabel("Forward");
		lblForward.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblForward.setBounds(224, 535, 186, 26);
		frame.getContentPane().add(lblForward);
		
		JLabel lblBackward = new JLabel("Backward");
		lblBackward.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBackward.setBounds(431, 535, 177, 26);
		frame.getContentPane().add(lblBackward);
		
		txtDrivetrainleftForward = new JTextField();
		txtDrivetrainleftForward.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtDrivetrainleftForward.setText("DriveTrainLeft Forward");
		txtDrivetrainleftForward.setBounds(224, 582, 186, 32);
		frame.getContentPane().add(txtDrivetrainleftForward);
		txtDrivetrainleftForward.setColumns(10);
		
		txtDrivetrainleftBackward = new JTextField();
		txtDrivetrainleftBackward.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtDrivetrainleftBackward.setText("DriveTrainLeft Backward");
		txtDrivetrainleftBackward.setBounds(431, 582, 186, 32);
		frame.getContentPane().add(txtDrivetrainleftBackward);
		txtDrivetrainleftBackward.setColumns(10);
		
		JLabel lblDrivetrainright = new JLabel("DriveTrainRight");
		lblDrivetrainright.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDrivetrainright.setBounds(21, 631, 204, 26);
		frame.getContentPane().add(lblDrivetrainright);
		
		txtDrivetrainrightForward = new JTextField();
		txtDrivetrainrightForward.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtDrivetrainrightForward.setText("DriveTrainRight Forward");
		txtDrivetrainrightForward.setBounds(224, 629, 186, 32);
		frame.getContentPane().add(txtDrivetrainrightForward);
		txtDrivetrainrightForward.setColumns(10);
		
		txtDrivetrainrightBackward = new JTextField();
		txtDrivetrainrightBackward.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtDrivetrainrightBackward.setText("DriveTrainRight Backward");
		txtDrivetrainrightBackward.setBounds(431, 631, 186, 32);
		frame.getContentPane().add(txtDrivetrainrightBackward);
		txtDrivetrainrightBackward.setColumns(10);
		
		JLabel lblIP = new JLabel("10.49.11.");
		lblIP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIP.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblIP.setBounds(431, 380, 177, 26);
		frame.getContentPane().add(lblIP);
		
		txtIP = new JTextField();
		txtIP.setText("84");
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtIP.setColumns(10);
		txtIP.setBounds(612, 377, 186, 32);
		frame.getContentPane().add(txtIP);
		
		JLabel lblFuelcollector = new JLabel("FuelCollector");
		lblFuelcollector.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblFuelcollector.setBounds(21, 678, 204, 26);
		frame.getContentPane().add(lblFuelcollector);
		
		txtFuelcollectorIn = new JTextField();
		txtFuelcollectorIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFuelcollectorIn.setText("FuelCollector In");
		txtFuelcollectorIn.setBounds(224, 676, 186, 32);
		frame.getContentPane().add(txtFuelcollectorIn);
		txtFuelcollectorIn.setColumns(10);
		
		txtFuelcollectorOut = new JTextField();
		txtFuelcollectorOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtFuelcollectorOut.setText("FuelCollector Out");
		txtFuelcollectorOut.setBounds(431, 676, 186, 32);
		frame.getContentPane().add(txtFuelcollectorOut);
		txtFuelcollectorOut.setColumns(10);
		
		JLabel lblHopper = new JLabel("Hopper");
		lblHopper.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblHopper.setBounds(21, 725, 204, 26);
		frame.getContentPane().add(lblHopper);
		
		txtHopperOut = new JTextField();
		txtHopperOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtHopperOut.setText("Hopper Out");
		txtHopperOut.setBounds(224, 725, 186, 32);
		frame.getContentPane().add(txtHopperOut);
		txtHopperOut.setColumns(10);
		
		txtHopperIn = new JTextField();
		txtHopperIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtHopperIn.setText("Hopper In");
		txtHopperIn.setBounds(431, 725, 186, 32);
		frame.getContentPane().add(txtHopperIn);
		txtHopperIn.setColumns(10);
		
		JLabel lblShooterfeeder = new JLabel("ShooterFeeder");
		lblShooterfeeder.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblShooterfeeder.setBounds(21, 772, 204, 26);
		frame.getContentPane().add(lblShooterfeeder);
		
		txtShooterfeederIn = new JTextField();
		txtShooterfeederIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtShooterfeederIn.setText("ShooterFeeder In");
		txtShooterfeederIn.setBounds(431, 772, 186, 32);
		frame.getContentPane().add(txtShooterfeederIn);
		txtShooterfeederIn.setColumns(10);
		
		txtShooterfeederOut = new JTextField();
		txtShooterfeederOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtShooterfeederOut.setText("ShooterFeeder Out");
		txtShooterfeederOut.setBounds(224, 772, 186, 32);
		frame.getContentPane().add(txtShooterfeederOut);
		txtShooterfeederOut.setColumns(10);
		
		JLabel lblShooterflywheel = new JLabel("ShooterFlywheel");
		lblShooterflywheel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblShooterflywheel.setBounds(21, 819, 204, 26);
		frame.getContentPane().add(lblShooterflywheel);
		
		txtShooterflywheelOut = new JTextField();
		txtShooterflywheelOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtShooterflywheelOut.setText("ShooterFlywheel Out");
		txtShooterflywheelOut.setBounds(224, 816, 186, 32);
		frame.getContentPane().add(txtShooterflywheelOut);
		txtShooterflywheelOut.setColumns(10);
		
		txtShooterflywheelIn = new JTextField();
		txtShooterflywheelIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtShooterflywheelIn.setText("ShooterFlywheel In");
		txtShooterflywheelIn.setBounds(431, 816, 186, 32);
		frame.getContentPane().add(txtShooterflywheelIn);
		txtShooterflywheelIn.setColumns(10);
		
		lblClimber = new JLabel("Climber");
		lblClimber.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblClimber.setBounds(21, 866, 204, 26);
		frame.getContentPane().add(lblClimber);
		
		txtClimberUp = new JTextField();
		txtClimberUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtClimberUp.setText("Climber Up");
		txtClimberUp.setBounds(224, 863, 186, 32);
		frame.getContentPane().add(txtClimberUp);
		txtClimberUp.setColumns(10);
		
		lblGearintake = new JLabel("GearIntake");
		lblGearintake.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGearintake.setBounds(21, 913, 204, 26);
		frame.getContentPane().add(lblGearintake);
		
		txtGearintakeIn = new JTextField();
		txtGearintakeIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtGearintakeIn.setText("GearIntake In");
		txtGearintakeIn.setBounds(224, 913, 186, 32);
		frame.getContentPane().add(txtGearintakeIn);
		txtGearintakeIn.setColumns(10);
		
		txtGearintakeOut = new JTextField();
		txtGearintakeOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtGearintakeOut.setText("GearIntake Out");
		txtGearintakeOut.setBounds(431, 913, 186, 32);
		frame.getContentPane().add(txtGearintakeOut);
		txtGearintakeOut.setColumns(10);
		
		lblGearlift = new JLabel("GearLift");
		lblGearlift.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGearlift.setBounds(21, 960, 204, 26);
		frame.getContentPane().add(lblGearlift);
		
		txtGearliftUp = new JTextField();
		txtGearliftUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtGearliftUp.setText("GearLift Up");
		txtGearliftUp.setBounds(224, 960, 186, 32);
		frame.getContentPane().add(txtGearliftUp);
		txtGearliftUp.setColumns(10);
		
		txtGearliftDown = new JTextField();
		txtGearliftDown.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtGearliftDown.setText("GearLift Down");
		txtGearliftDown.setBounds(431, 960, 186, 32);
		frame.getContentPane().add(txtGearliftDown);
		txtGearliftDown.setColumns(10);
		
		lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblIp.setBounds(430, 380, 92, 26);
		frame.getContentPane().add(lblIp);
		
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
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent evt) {
				  	// NetworkTable IP
				  	table.setIPAddress(lblIP.getText() + txtIP.getText());
				  
				  	String currSub = table.getString("currSub", null);
					boolean direction = table.getBoolean("Direction", true);
					
					txtCurrsub.setText(currSub);
					txtDTLDir.setText("" + direction);

					String desc = table.getString("Description", null);
					
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
					txtEncpos.setText(table.getString(ENCPOS + desc, null));
					txtTargetpos.setText(table.getString(TARGET + desc, null));						
					txtEncerr.setText(table.getString(ERROR + desc, null));	
				
					/*******Completion*******/
					// DriveTrainLeft
					txtDrivetrainleftForward.setText(table.getString("HitTarget " + "DriveTrainLeft " + true, null));
					txtDrivetrainleftBackward.setText(table.getString("HitTarget " + "DriveTrainLeft " + false, null));

					// DriveTrainRight
					txtDrivetrainrightForward.setText(table.getString("HitTarget " + "DriveTrainRight " + true, null));
					txtDrivetrainrightBackward.setText(table.getString("HitTarget " + "DriveTrainRight " + false, null));
					
					// FuelCollector
					txtFuelcollectorIn.setText(table.getString("HitTarget " + "FuelCollector " + true, null));
					txtFuelcollectorOut.setText(table.getString("HitTarget " + "FuelCollector " + false, null));					
					
					// FuelHopper
					txtHopperOut.setText(table.getString("HitTarget " + "FuelHopper " + true, null));
					txtHopperIn.setText(table.getString("HitTarget " + "FuelHopper" + false, null));
					
					// ShooterFeeder
					txtShooterfeederOut.setText(table.getString("HitTarget " + "ShooterFeeder " + true, null));
					txtShooterfeederIn.setText(table.getString("HitTarget " + "ShooterFeeder " + false, null));
					
					// ShooterFlywheel
					txtShooterflywheelOut.setText(table.getString("HitTarget " + "ShooterFlywheel " + true, null));
					txtShooterflywheelIn.setText(table.getString("HitTarget " + "ShooterFlywheel " + false, null));
					
					// Climber
					txtClimberUp.setText(table.getString("HitTarget " + "Climber " + true, null));

					// GearIntake
					txtGearintakeIn.setText(table.getString("HitTarget " + "GearIntake " + true, null));
					txtGearintakeOut.setText(table.getString("HitTarget " + "GearIntake " + false, null));

					// GearLift
					txtGearliftUp.setText(table.getString("HitTarget " + "GearLift " + true, null));
					txtGearliftDown.setText(table.getString("HitTarget " + "GearLift " + false, null));
					
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

