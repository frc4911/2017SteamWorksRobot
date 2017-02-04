import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CollectData {

	public CollectData(){
		NetworkTable table = initNetworkTable("10.36.63.25","SmartDashboard");
		collect(table);
	}
	
	public NetworkTable initNetworkTable(String ip, String tableName){
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress(ip);
		NetworkTable table = NetworkTable.getTable(tableName);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return table;
	}
	
	public void collect(NetworkTable table){
		
		
		//table.
	}

}
