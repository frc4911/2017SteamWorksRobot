import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CollectData implements Runnable {

	public boolean keepGoing = true;
	public CollectData(){
	}
	
	public void run(){
		String ip = "10.49.11.25";
		String tableName = "SmartDashboard";
		
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		NetworkTable table = NetworkTable.getTable(tableName);
		
		while (keepGoing){
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			double speedTmp = table.getNumber("speedRPM",-999);
			System.out.println(speedTmp);
		}
		
	}
}
