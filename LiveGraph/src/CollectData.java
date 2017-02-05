import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CollectData implements Runnable {

	public boolean keepGoing = true;
	public double graphValue = 0;
	
	public CollectData(){
	}
	
	public void run(){
		String ip = "10.49.11.25";
		String tableName = "SmartDashboard";
		
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		NetworkTable table = NetworkTable.getTable(tableName);
		
		while (keepGoing){
			try { Thread.sleep(100); } catch (InterruptedException e) {}
			double speedTmp = table.getNumber("encoder speed (RPM)2",-999);
			
			if (speedTmp != 999){
				graphValue = speedTmp;
				System.out.println(speedTmp);
			}
			//System.out.println(speedTmp);
		}
		
	}
}
