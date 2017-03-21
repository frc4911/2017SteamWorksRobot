package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.commands.C_DriveByJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrain3 extends Subsystem {
    
    final int TPortDriveMotorFrontLeft = 9; // has encoder
    //final int TPortDriveMotorMidLeft = 8; motor no longer installed
    final int TPortDriveMotorRearLeft = 7;
    
    final int TPortDriveMotorFrontRight = 10; // has encoder
    //final int TPortDriveMotorMidRight = 11;  motor no longer installed
    final int TPortDriveMotorRearRight = 12;
    
    CANTalon DriveMotorFrontLeft = new CANTalon(TPortDriveMotorFrontLeft);
    CANTalon DriveMotorRearLeft = new CANTalon(TPortDriveMotorRearLeft);
    
    CANTalon DriveMotorFrontRight = new CANTalon(TPortDriveMotorFrontRight);
    CANTalon DriveMotorRearRight = new CANTalon(TPortDriveMotorRearRight);
	
    CANTalon.TalonControlMode mode = CANTalon.TalonControlMode.PercentVbus; 
    MotionProfiler mpLeft;
    MotionProfiler mpRight;
    
    CANTalonEncoderControl ctrlLeft;
    CANTalonEncoderControl ctrlRight;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new C_DriveByJoystick());
    }
    
    public SS_DriveTrain3(){
    	// causes problems and cannot find desc in manual
//    	DriveMotorFrontLeft.reset();
//    	DriveMotorRearLeft.reset();
//    	DriveMotorFrontRight.reset();
//    	DriveMotorRearRight.reset();
    	
    	ctrlLeft = new CANTalonEncoderControl(DriveMotorFrontLeft,CANTalon.FeedbackDevice.QuadEncoder,1440,360,false);
    	ctrlLeft.setEncoderPosition(0);
    	ctrlRight = new CANTalonEncoderControl(DriveMotorFrontRight,CANTalon.FeedbackDevice.QuadEncoder,1440,360,false);
    	ctrlRight.setEncoderPosition(0);

    	DriveMotorRearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
    	DriveMotorRearLeft.set(TPortDriveMotorFrontLeft);
    	
  	
    	DriveMotorRearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
    	DriveMotorRearRight.set(TPortDriveMotorFrontRight);
    	
    	setBrakeMode(true);
    }
    
    public void updateLog(){
    	SmartDashboard.putNumber("drive left encoder", DriveMotorFrontLeft.getEncPosition());
		SmartDashboard.putNumber("drive right encoder", DriveMotorFrontRight.getEncPosition());
		
    	SmartDashboard.putNumber("drive left set", DriveMotorFrontLeft.get());
		SmartDashboard.putNumber("drive right set", DriveMotorFrontRight.get());
		
		SmartDashboard.putNumber("PIDPosition", PIDPosition());
		
//		SmartDashboard.putNumber("kp", 0);
//		SmartDashboard.putNumber("kd", 0);
//		SmartDashboard.putNumber("ki", 0);
//		SmartDashboard.putNumber("kf", 0);
//		SmartDashboard.putNumber("ramprate", 0);
//		SmartDashboard.putNumber("izone", 0);
//		SmartDashboard.putNumber("peak V", 0);
//		SmartDashboard.putNumber("nominal V", 0);
//		
//		SmartDashboard.putNumber("kpR", 0);
//		SmartDashboard.putNumber("kdR", 0);
//		SmartDashboard.putNumber("kiR", 0);
//		SmartDashboard.putNumber("kfR", 0);
//		SmartDashboard.putNumber("ramprateR", 0);
//		SmartDashboard.putNumber("izoneR", 0);
//		SmartDashboard.putNumber("peak VR", 0);
//		SmartDashboard.putNumber("nominal VR", 0);
		
//		kp = 4.0
//		ramprate = 0.6
//		peak V = 4.0
//		nominal V = 0.9
//		Rest is 0
		
    }
    public void setBrakeMode(boolean mode){
    	DriveMotorFrontLeft.enableBrakeMode(mode);
    	DriveMotorRearLeft.enableBrakeMode(mode);
    	
    	DriveMotorFrontRight.enableBrakeMode(mode);
    	DriveMotorRearRight.enableBrakeMode(mode); 
    }
    
    public void setupPIDMode(){
    	// kp, kd, ki, kf, ramprate, izone, peak voltage, nominal voltage
    	
    	double kp = SmartDashboard.getNumber("kp", 0);
    	double kd = SmartDashboard.getNumber("kd", 0);
    	double ki = SmartDashboard.getNumber("ki", 0);
    	int iZone = (int)SmartDashboard.getNumber("izone", 0);
    	double ramprate = SmartDashboard.getNumber("ramprate", 0);
    	double peakV = SmartDashboard.getNumber("peak V", 0);
    	double nominalV = SmartDashboard.getNumber("nominal V", 0);
    	
    	double kpR = SmartDashboard.getNumber("kpR", 0);
    	double kdR = SmartDashboard.getNumber("kdR", 0);
    	double kiR = SmartDashboard.getNumber("kiR", 0);
    	int iZoneR = (int)SmartDashboard.getNumber("izoneR", 0);
    	double ramprateR = SmartDashboard.getNumber("ramprateR", 0);
    	double peakVR = SmartDashboard.getNumber("peak VR", 0);
    	double nominalVR = SmartDashboard.getNumber("nominal VR", 0);
    	
    	ctrlLeft.setupPIDMode(kp, kd, ki, 0.0, ramprate, iZone, peakV, nominalV);
    	ctrlRight.setupPIDMode(kpR, kdR, kiR, 0.0, ramprateR, iZoneR, peakVR, nominalVR);
    	
    	SmartDashboard.putNumber("kp actual", kp);
    	SmartDashboard.putNumber("kd actual", kd);
    	SmartDashboard.putNumber("ki actual", ki);
    	SmartDashboard.putNumber("izone actual", iZone);
    	SmartDashboard.putNumber("ramprate actual", ramprate);
    	SmartDashboard.putNumber("peak V actual", peakV);
    	SmartDashboard.putNumber("nominal V actual", nominalV);
    	
    	SmartDashboard.putNumber("kpR actual", kpR);
    	SmartDashboard.putNumber("kdR actual", kdR);
    	SmartDashboard.putNumber("kiR actual", kiR);
    	SmartDashboard.putNumber("izoneR actual", iZoneR);
    	SmartDashboard.putNumber("ramprateR actual", ramprateR);
    	SmartDashboard.putNumber("peak VR actual", peakVR);
    	SmartDashboard.putNumber("nominal VR actual", nominalVR);
    }
    
    public void startPIDMode(){
    	ctrlLeft.startPIDMode();
    	ctrlRight.startPIDMode();

    	clearEncoders();
    	setPIDEncoderGoal(0,0);
    }
    
    public void clearEncoders(){
    	ctrlLeft.setEncoderPosition(0);
    	ctrlRight.setEncoderPosition(0);
    }

    int leftTicksPIDGoal = 0;
    int rightTicksPIDGoal = 0;
    
    public void setPIDEncoderGoal(int leftTicks, int rightTicks){
    	leftTicksPIDGoal = -leftTicks;
    	ctrlLeft.setPIDEncoderGoal(-leftTicks);
    	
    	rightTicksPIDGoal = rightTicks;
    	ctrlRight.setPIDEncoderGoal(rightTicks);
    }
    
    public int PIDPosition(){
    	return Math.abs(leftTicksPIDGoal-DriveMotorFrontLeft.getEncPosition())+
    			Math.abs(rightTicksPIDGoal-DriveMotorFrontRight.getEncPosition());
    }

    public void stopPIDMode(){
    	ctrlLeft.stopPIDMode();
    	ctrlRight.stopPIDMode();
    }

    //Joystick forward returns negative value, twist clockwise returns positive value
    public void driveByJoystick(double yInput, double xInput) {
    	
    	double leftInput = yInput - xInput;
    	double rightInput = yInput + xInput;
    	
		DriveMotorFrontLeft.set(leftInput);
		DriveMotorFrontRight.set(-rightInput);		
    }
    		public static double [][]Points = new double[][]{		
    			{0,	0	,10},
    			{0.00019047619047619,	2.285714286	,10},
    			{0.000857142857142857,	5.714285714	,10},
    			{0.00219047619047619,	10.28571429	,10},
    			{0.00438095238095238,	16	,10},
    			{0.00761904761904762,	22.85714286	,10},
    			{0.0120952380952381,	30.85714286	,10},
    			{0.018,	40	,10},
    			{0.0255238095238095,	50.28571429	,10},
    			{0.0348571428571429,	61.71428571	,10},
    			{0.0461904761904762,	74.28571429	,10},
    			{0.0597142857142857,	88	,10},
    			{0.0756190476190476,	102.8571429	,10},
    			{0.0940952380952381,	118.8571429	,10},
    			{0.115333333333333,	136	,10},
    			{0.13952380952381,	154.2857143	,10},
    			{0.166857142857143,	173.7142857	,10},
    			{0.19752380952381,	194.2857143	,10},
    			{0.231714285714286,	216	,10},
    			{0.269619047619048,	238.8571429	,10},
    			{0.311428571428571,	262.8571429	,10},
    			{0.357238095238095,	286.8571429	,10},
    			{0.407047619047619,	310.8571429	,10},
    			{0.460857142857143,	334.8571429	,10},
    			{0.518666666666667,	358.8571429	,10},
    			{0.58047619047619,	382.8571429	,10},
    			{0.646285714285714,	406.8571429	,10},
    			{0.716095238095238,	430.8571429	,10},
    			{0.789904761904762,	454.8571429	,10},
    			{0.867714285714285,	478.8571429	,10},
    			{0.949523809523809,	502.8571429	,10},
    			{1.03533333333333,	526.8571429	,10},
    			{1.12514285714286,	550.8571429	,10},
    			{1.21895238095238,	574.8571429	,10},
    			{1.3167619047619,	598.8571429	,10},
    			{1.41857142857143,	622.8571429	,10},
    			{1.52438095238095,	646.8571429	,10},
    			{1.63419047619048,	670.8571429	,10},
    			{1.748,	694.8571429	,10},
    			{1.86580952380952,	718.8571429	,10},
    			{1.98761904761905,	742.8571429	,10},
    			{2.11342857142857,	766.8571429	,10},
    			{2.2432380952381,	790.8571429	,10},
    			{2.37704761904762,	814.8571429	,10},
    			{2.51485714285714,	838.8571429	,10},
    			{2.65666666666667,	862.8571429	,10},
    			{2.80247619047619,	886.8571429	,10},
    			{2.95228571428572,	910.8571429	,10},
    			{3.10609523809524,	934.8571429	,10},
    			{3.26390476190476,	958.8571429	,10},
    			{3.42571428571429,	982.8571429	,10},
    			{3.59133333333334,	1004.571429	,10},
    			{3.76047619047619,	1025.142857	,10},
    			{3.93295238095238,	1044.571429	,10},
    			{4.10857142857143,	1062.857143	,10},
    			{4.28714285714286,	1080	,10},
    			{4.46847619047619,	1096	,10},
    			{4.65238095238095,	1110.857143	,10},
    			{4.83866666666667,	1124.571429	,10},
    			{5.02714285714286,	1137.142857	,10},
    			{5.21761904761905,	1148.571429	,10},
    			{5.40990476190476,	1158.857143	,10},
    			{5.60380952380953,	1168	,10},
    			{5.79914285714286,	1176	,10},
    			{5.99571428571429,	1182.857143	,10},
    			{6.19333333333334,	1188.571429	,10},
    			{6.39180952380953,	1193.142857	,10},
    			{6.59095238095238,	1196.571429	,10},
    			{6.79057142857143,	1198.857143	,10},
    			{6.99047619047619,	1200	,10},
    			{7.19047619047619,	1200	,10},
    			{7.39047619047619,	1200	,10},
    			{7.59047619047619,	1200	,10},
    			{7.79047619047619,	1200	,10},
    			{7.99047619047619,	1200	,10},
    			{8.19047619047619,	1200	,10},
    			{8.39047619047619,	1200	,10},
    			{8.59047619047619,	1200	,10},
    			{8.79047619047619,	1200	,10},
    			{8.99047619047619,	1200	,10},
    			{9.19047619047619,	1200	,10},
    			{9.39047619047619,	1200	,10},
    			{9.59047619047619,	1200	,10},
    			{9.79047619047619,	1200	,10},
    			{9.99047619047619,	1200	,10},
    			{10.1904761904762,	1200	,10},
    			{10.3904761904762,	1200	,10},
    			{10.5904761904762,	1200	,10},
    			{10.7904761904762,	1200	,10},
    			{10.9904761904762,	1200	,10},
    			{11.1904761904762,	1200	,10},
    			{11.3904761904762,	1200	,10},
    			{11.5904761904762,	1200	,10},
    			{11.7904761904762,	1200	,10},
    			{11.9904761904762,	1200	,10},
    			{12.1904761904762,	1200	,10},
    			{12.3904761904762,	1200	,10},
    			{12.5904761904762,	1200	,10},
    			{12.7904761904762,	1200	,10},
    			{12.9904761904762,	1200	,10},
    			{13.1904761904762,	1200	,10},
    			{13.3904761904762,	1200	,10},
    			{13.5904761904762,	1200	,10},
    			{13.7904761904762,	1200	,10},
    			{13.9904761904762,	1200	,10},
    			{14.1904761904762,	1200	,10},
    			{14.3904761904762,	1200	,10},
    			{14.5904761904762,	1200	,10},
    			{14.7904761904762,	1200	,10},
    			{14.9904761904762,	1200	,10},
    			{15.1904761904762,	1200	,10},
    			{15.3904761904762,	1200	,10},
    			{15.5904761904762,	1200	,10},
    			{15.7904761904762,	1200	,10},
    			{15.9904761904762,	1200	,10},
    			{16.1904761904762,	1200	,10},
    			{16.3904761904762,	1200	,10},
    			{16.5904761904762,	1200	,10},
    			{16.7904761904762,	1200	,10},
    			{16.9904761904762,	1200	,10},
    			{17.1904761904762,	1200	,10},
    			{17.3904761904762,	1200	,10},
    			{17.5904761904762,	1200	,10},
    			{17.7904761904762,	1200	,10},
    			{17.9904761904762,	1200	,10},
    			{18.1904761904762,	1200	,10},
    			{18.3904761904762,	1200	,10},
    			{18.5904761904762,	1200	,10},
    			{18.7904761904762,	1200	,10},
    			{18.9904761904762,	1200	,10},
    			{19.1904761904762,	1200	,10},
    			{19.3904761904762,	1200	,10},
    			{19.5904761904762,	1200	,10},
    			{19.7904761904762,	1200	,10},
    			{19.9904761904762,	1200	,10},
    			{20.1904761904762,	1200	,10},
    			{20.3904761904762,	1200	,10},
    			{20.5904761904761,	1200	,10},
    			{20.7904761904761,	1200	,10},
    			{20.9904761904761,	1200	,10},
    			{21.1904761904761,	1200	,10},
    			{21.3904761904761,	1200	,10},
    			{21.5904761904761,	1200	,10},
    			{21.7904761904761,	1200	,10},
    			{21.9904761904761,	1200	,10},
    			{22.1904761904761,	1200	,10},
    			{22.3904761904761,	1200	,10},
    			{22.5904761904761,	1200	,10},
    			{22.7904761904761,	1200	,10},
    			{22.9904761904761,	1200	,10},
    			{23.1904761904761,	1200	,10},
    			{23.3904761904761,	1200	,10},
    			{23.5904761904761,	1200	,10},
    			{23.7904761904761,	1200	,10},
    			{23.9904761904761,	1200	,10},
    			{24.1904761904761,	1200	,10},
    			{24.3904761904761,	1200	,10},
    			{24.5904761904761,	1200	,10},
    			{24.7904761904761,	1200	,10},
    			{24.9904761904761,	1200	,10},
    			{25.1904761904761,	1200	,10},
    			{25.3904761904761,	1200	,10},
    			{25.5904761904761,	1200	,10},
    			{25.7904761904761,	1200	,10},
    			{25.9904761904761,	1200	,10},
    			{26.1904761904761,	1200	,10},
    			{26.3904761904761,	1200	,10},
    			{26.5904761904761,	1200	,10},
    			{26.7904761904761,	1200	,10},
    			{26.9904761904761,	1200	,10},
    			{27.1904761904761,	1200	,10},
    			{27.3904761904761,	1200	,10},
    			{27.5904761904761,	1200	,10},
    			{27.7904761904761,	1200	,10},
    			{27.9904761904761,	1200	,10},
    			{28.1904761904761,	1200	,10},
    			{28.3904761904761,	1200	,10},
    			{28.5904761904761,	1200	,10},
    			{28.7904761904761,	1200	,10},
    			{28.9904761904761,	1200	,10},
    			{29.1904761904761,	1200	,10},
    			{29.3904761904761,	1200	,10},
    			{29.5904761904761,	1200	,10},
    			{29.7904761904761,	1200	,10},
    			{29.9904761904761,	1200	,10},
    			{30.1904761904761,	1200	,10},
    			{30.3904761904761,	1200	,10},
    			{30.5904761904761,	1200	,10},
    			{30.7904761904761,	1200	,10},
    			{30.9904761904761,	1200	,10},
    			{31.1904761904761,	1200	,10},
    			{31.3904761904761,	1200	,10},
    			{31.5904761904761,	1200	,10},
    			{31.7904761904761,	1200	,10},
    			{31.9904761904761,	1200	,10},
    			{32.1904761904761,	1200	,10},
    			{32.3904761904761,	1200	,10},
    			{32.5904761904761,	1200	,10},
    			{32.7904761904761,	1200	,10},
    			{32.9904761904761,	1200	,10},
    			{33.1904761904761,	1200	,10},
    			{33.3904761904761,	1200	,10},
    			{33.5904761904761,	1200	,10},
    			{33.7904761904761,	1200	,10},
    			{33.9904761904761,	1200	,10},
    			{34.1904761904761,	1200	,10},
    			{34.3904761904761,	1200	,10},
    			{34.5904761904761,	1200	,10},
    			{34.7904761904761,	1200	,10},
    			{34.9904761904762,	1200	,10},
    			{35.1904761904762,	1200	,10},
    			{35.3904761904762,	1200	,10},
    			{35.5904761904762,	1200	,10},
    			{35.7904761904762,	1200	,10},
    			{35.9904761904762,	1200	,10},
    			{36.1904761904762,	1200	,10},
    			{36.3904761904762,	1200	,10},
    			{36.5904761904762,	1200	,10},
    			{36.7904761904762,	1200	,10},
    			{36.9904761904762,	1200	,10},
    			{37.1904761904762,	1200	,10},
    			{37.3904761904762,	1200	,10},
    			{37.5904761904762,	1200	,10},
    			{37.7904761904762,	1200	,10},
    			{37.9904761904762,	1200	,10},
    			{38.1904761904762,	1200	,10},
    			{38.3904761904762,	1200	,10},
    			{38.5904761904762,	1200	,10},
    			{38.7904761904762,	1200	,10},
    			{38.9904761904762,	1200	,10},
    			{39.1904761904762,	1200	,10},
    			{39.3904761904762,	1200	,10},
    			{39.5904761904762,	1200	,10},
    			{39.7904761904762,	1200	,10},
    			{39.9904761904762,	1200	,10},
    			{40.1904761904762,	1200	,10},
    			{40.3904761904762,	1200	,10},
    			{40.5904761904762,	1200	,10},
    			{40.7904761904762,	1200	,10},
    			{40.9904761904762,	1200	,10},
    			{41.1904761904762,	1200	,10},
    			{41.3904761904762,	1200	,10},
    			{41.5904761904762,	1200	,10},
    			{41.7904761904762,	1200	,10},
    			{41.9904761904763,	1200	,10},
    			{42.1904761904763,	1200	,10},
    			{42.3904761904763,	1200	,10},
    			{42.5904761904763,	1200	,10},
    			{42.7904761904763,	1200	,10},
    			{42.9904761904763,	1200	,10},
    			{43.1904761904763,	1200	,10},
    			{43.3902857142858,	1197.714286	,10},
    			{43.5896190476191,	1194.285714	,10},
    			{43.7882857142858,	1189.714286	,10},
    			{43.9860952380953,	1184	,10},
    			{44.1828571428572,	1177.142857	,10},
    			{44.378380952381,	1169.142857	,10},
    			{44.5724761904763,	1160	,10},
    			{44.7649523809525,	1149.714286	,10},
    			{44.9556190476191,	1138.285714	,10},
    			{45.1442857142858,	1125.714286	,10},
    			{45.330761904762,	1112	,10},
    			{45.5148571428572,	1097.142857	,10},
    			{45.696380952381,	1081.142857	,10},
    			{45.8751428571429,	1064	,10},
    			{46.0509523809525,	1045.714286	,10},
    			{46.2236190476191,	1026.285714	,10},
    			{46.3929523809525,	1005.714286	,10},
    			{46.558761904762,	984	,10},
    			{46.7208571428572,	961.1428571	,10},
    			{46.8790476190477,	937.1428571	,10},
    			{47.0332380952382,	913.1428571	,10},
    			{47.1834285714287,	889.1428571	,10},
    			{47.3296190476191,	865.1428571	,10},
    			{47.4718095238096,	841.1428571	,10},
    			{47.6100000000001,	817.1428571	,10},
    			{47.7441904761906,	793.1428571	,10},
    			{47.874380952381,	769.1428571	,10},
    			{48.0005714285715,	745.1428571	,10},
    			{48.122761904762,	721.1428571	,10},
    			{48.2409523809525,	697.1428571	,10},
    			{48.3551428571429,	673.1428571	,10},
    			{48.4653333333334,	649.1428571	,10},
    			{48.5715238095239,	625.1428571	,10},
    			{48.6737142857144,	601.1428571	,10},
    			{48.7719047619048,	577.1428571	,10},
    			{48.8660952380953,	553.1428571	,10},
    			{48.9562857142858,	529.1428571	,10},
    			{49.0424761904763,	505.1428571	,10},
    			{49.1246666666668,	481.1428571	,10},
    			{49.2028571428572,	457.1428571	,10},
    			{49.2770476190477,	433.1428571	,10},
    			{49.3472380952382,	409.1428571	,10},
    			{49.4134285714287,	385.1428571	,10},
    			{49.4756190476191,	361.1428571	,10},
    			{49.5338095238096,	337.1428571	,10},
    			{49.5880000000001,	313.1428571	,10},
    			{49.6381904761906,	289.1428571	,10},
    			{49.684380952381,	265.1428571	,10},
    			{49.7265714285715,	241.1428571	,10},
    			{49.764761904762,	217.1428571	,10},
    			{49.7991428571429,	195.4285714	,10},
    			{49.8300000000001,	174.8571429	,10},
    			{49.8575238095239,	155.4285714	,10},
    			{49.8819047619048,	137.1428571	,10},
    			{49.9033333333334,	120	,10},
    			{49.9220000000001,	104	,10},
    			{49.9380952380953,	89.14285714	,10},
    			{49.9518095238096,	75.42857143	,10},
    			{49.9633333333334,	62.85714286	,10},
    			{49.9728571428572,	51.42857143	,10},
    			{49.9805714285715,	41.14285714	,10},
    			{49.9866666666667,	32	,10},
    			{49.9913333333334,	24	,10},
    			{49.994761904762,	17.14285714	,10},
    			{49.9971428571429,	11.42857143	,10},
    			{49.9986666666668,	6.857142857	,10},
    			{49.9995238095239,	3.428571429	,10},
    			{49.9999047619048,	1.142857143	,10},
    			{50.0000000000001,	0	,10}};

    public void enterMPMode() {
    	SmartDashboard.putString("status", "in ss_driveTrain.enterMPMode");
    	mpLeft = new MotionProfiler(DriveMotorFrontLeft,Points,.44,1,0,0,"drive left encoder"); 
    	mpRight = new MotionProfiler(DriveMotorFrontRight,Points,.6,1,0,0,"drive right encoder"); 
    	mpLeft.enterMPMode();
    	mpRight.enterMPMode();
//		_notifier.startPeriodic(0.005);
//    	enterMPModeSub(DriveMotorFrontLeft);
//    	enterMPModeSub(DriveMotorFrontRight);
	}
    
//    public void enterMPModeSub(CANTalon talon) {
//    	talon.setPosition(0);
//    	talon.clearMotionProfileTrajectories();
//    	talon.clearMotionProfileHasUnderrun();
//    	talon.changeControlMode(CANTalon.TalonControlMode.MotionProfile);
//    	talon.setF(.44);// brian see calculation in section 6 of ctre motion profile doc
//    	// 1024 encoder ticks/rev, max vel = 1351 rpm 
//    	talon.setP(1);
//    	talon.setD(0);
//    	talon.setI(0);
//    	
//    	CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
//    	int totalPoints = Points.length;
//    	
//    	for (int i = 0; i < totalPoints; ++i) {
//			/* for each point, fill our structure and pass it to API */
//			point.position = Points[i][0];
//			point.velocity = Points[i][1];
//			point.timeDurMs = (int) Points[i][2];
//			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
//			point.velocityOnly = false; /* brian set true to not do any position
//										 * servo, just velocity feedforward
//										 */
//			point.zeroPos = false;
//			if (i == 0)
//				point.zeroPos = true; /* set this to true on the first point */
//
//			point.isLastPoint = false;
//			if ((i + 1) == totalPoints)
//				point.isLastPoint = true; /* set this to true on the last point  */
//
//			talon.pushMotionProfileTrajectory(point);
//		}
//    	
//    	talon.changeMotionControlFramePeriod(5);
//    	talon.processMotionProfileBuffer();
//    	talon.processMotionProfileBuffer();
//    	talon.set(CANTalon.SetValueMotionProfile.Enable.value);
//	}
    
    public void exitMPMode() {
    	mpLeft.exitMPMode();
    	mpRight.exitMPMode();

    	SmartDashboard.putString("status", "in ss_driveTrain.exitMPMode");
//    	_notifier.stop();
//    	exitMPModeSub(DriveMotorFrontLeft);
//    	exitMPModeSub(DriveMotorFrontRight);
    }
    
//    void exitMPModeSub(CANTalon talon) {
//    	talon.set(CANTalon.SetValueMotionProfile.Disable.value);
//    	talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//    	talon.clearMotionProfileTrajectories();
//    	
//    }

    void displayMotionProfileStatus(CANTalon.MotionProfileStatus mps){
    	
    	CANTalon.TrajectoryPoint point = mps.activePoint;
    	if (point != null){
        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_isLastPoint", point.isLastPoint);
        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_velocityOnly", point.velocityOnly);
        	SmartDashboard.putBoolean("MotionProfileStatus_activePoint_zeroPos", point.zeroPos);
        	SmartDashboard.putString("MotionProfileStatus_activePoint_position", ""+point.position);
        	SmartDashboard.putString("MotionProfileStatus_activePoint_profileSlotSelect", ""+point.profileSlotSelect);
        	SmartDashboard.putString("MotionProfileStatus_activePoint_timeDurMs", ""+point.timeDurMs);
        	SmartDashboard.putString("MotionProfileStatus_activePoint_velocity", ""+point.velocity);    		
    	}
    	SmartDashboard.putBoolean("MotionProfileStatus_activePointValid", mps.activePointValid);
    	SmartDashboard.putString("MotionProfileStatus_btmBufferCnt", ""+mps.btmBufferCnt);
    	SmartDashboard.putBoolean("MotionProfileStatus_hasUnderrun", mps.hasUnderrun);
    	SmartDashboard.putBoolean("MotionProfileStatus_isUnderrun", mps.isUnderrun);
    	
    	CANTalon.SetValueMotionProfile svmp = mps.outputEnable;
    	SmartDashboard.putString("MotionProfileStatus_outputEnable", ""+svmp.value);
    	SmartDashboard.putString("MotionProfileStatus_topBufferCnt", ""+mps.topBufferCnt);
    	SmartDashboard.putString("MotionProfileStatus_topBufferRem", ""+mps.topBufferRem);
    }
    


	CANTalon.MotionProfileStatus mps = new CANTalon.MotionProfileStatus();
	
    public void driveByMP() {
//    	DriveMotorFrontLeft.getMotionProfileStatus(mps);
//    	displayMotionProfileStatus(mps);
    }
    
//    class PeriodicRunnable implements java.lang.Runnable {
//	    public void run() {  
//	    	DriveMotorFrontLeft.processMotionProfileBuffer();    
//	    	DriveMotorFrontRight.processMotionProfileBuffer();
//	    }
//	}
//	Notifier _notifier = new Notifier(new PeriodicRunnable());
}