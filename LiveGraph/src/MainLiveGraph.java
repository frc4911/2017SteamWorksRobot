
public class MainLiveGraph {

	static CollectData cd;
	static MinimalStaticChart msc;
	static CameraRun camRun;
	static GUI gui;
	
	public static void main(String[] args) {

//		cd = new CollectData();
//		(new Thread(cd)).start();
//		
//		msc = new MinimalStaticChart();
//		msc.init();
//		(new Thread(msc)).start();
		
//		camRun = new CameraRun();
//		camRun.CameraFirstInit();
//		camRun.CameraInit();
//		camRun.run();
		
		gui = new GUI();
		gui.openWindow();
	}
}
