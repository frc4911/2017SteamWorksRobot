import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class MinimalStaticChart implements Runnable {

	Chart2D chart = null;
	ITrace2D trace = null;
	
	public MinimalStaticChart() {
		//super();
	}
	  
	public void init(){

		// Create a chart:  
		chart = new Chart2D();
		// Create an ITrace: 
		trace = new Trace2DSimple(); 
		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		chart.addTrace(trace);    
		// Add all points, as it is static: 
//		for(int i=100;i>=0;i--){
//			//trace.addPoint(i,random.nextDouble()*10.0+i);
//			trace.addPoint(i,MainLiveGraph.cd.graphValue);
//		}
		// Make it visible:
		// Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");
		// add the chart to the frame: 
		frame.getContentPane().add(chart);
		frame.setSize(400,300);
//		Random random = new Random();
//		for(int i=100;i>=0;i--){
//			trace.addPoint(i,random.nextDouble()*10.0+i);
//			//trace.addPoint(i,MainLiveGraph.cd.graphValue);
//		}
		// Enable the termination button [cross on the upper right edge]: 
		frame.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				}
		);
		frame.setVisible(true);
	}

	int counter = 0;
	Random random = new Random();
	@Override
	public void run() {
		while (true){
			//trace.addPoint((counter++)%100,random.nextDouble()*10.0+counter);
			double pt = MainLiveGraph.cd.graphValue;
			if (pt > 2000)
				trace.addPoint(counter++,pt);
			
			if (counter >= 1000){
				trace.removeAllPoints();
				counter =0;
			}
			//trace.removePoint(counter-200);
			try { Thread.sleep(100); } catch (InterruptedException e) {}
		}
	}
}
