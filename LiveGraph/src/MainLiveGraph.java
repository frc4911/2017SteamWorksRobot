
public class MainLiveGraph {

	static CollectData cd;
	static MinimalStaticChart msc;
	
	public static void main(String[] args) {

		(new Thread(new CollectData())).start();
		
		msc = new MinimalStaticChart();
		msc.init();
	}

}
