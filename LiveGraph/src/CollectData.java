import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CollectData implements Runnable {

	public boolean keepGoing = true;
	public double speedRPMFlywheel = 0;
	public double speedRPMFeeder = 0;
	public double DriveTrainLeft = -999;
	public double DriveTrainRight = -999;
	public double current = 0;
	public double voltage = 0;
	public double position = 0;
	public double get = 0;
	public double lineCount = 0;
	
	public CollectData(){
	}
	
	public void run(){
		String ip = "10.49.11.84";// eli "10.49.11.34"; // silver "10.49.11.84";
		String tableName = "SmartDashboard";
		
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		NetworkTable table = NetworkTable.getTable(tableName);
		double last = 0;
		int counter=0;
		
		while (keepGoing){
//			System.out.println(table.isConnected());
			
			try { Thread.sleep(100); } catch (InterruptedException e) {}
//			speedRPM = table.getNumber("I am alive",-999);
			//speedRPMFlywheel = Double.parseDouble(table.getString("ShooterFlywheel getSpeed","-999"));
			DriveTrainLeft = table.getNumber("DriveTrainLeft encoder", -999);
			DriveTrainRight = table.getNumber("DriveTrainRight encoder",-999);
			//System.out.println(speedRPM);
			
//			if (speedRPM == last){
//				counter++;
//			}
//			else{
//				System.out.println(counter);
//				counter = 0;
//				last = speedRPM;
//			}
//			lineCount = table.getNumber("lineCount",-999);
//			if (lineCount == last){
//				counter++;
//			}
//			else{
//				System.out.println(counter);
//				counter = 0;
//				last = lineCount;
//			}
//			current = table.getNumber("current2",-999);
//			voltage = table.getNumber("voltage2",-999);
//			position = table.getNumber("getEncPosition2",-999);
//			get = table.getNumber("get2",-999);
//			
			//System.out.println(speedTmp);
		}
		
	}
}
