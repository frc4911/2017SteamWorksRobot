import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class MinimalStaticChart {

	public MinimalStaticChart() {
		//super();
		System.out.println("-1");
	}
	  
	public void init(){

		System.out.println("0");
		// Create a chart:  
		Chart2D chart = new Chart2D();
		// Create an ITrace: 
		ITrace2D trace = new Trace2DSimple(); 
		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		chart.addTrace(trace);    
		// Add all points, as it is static: 
		Random random = new Random();
		for(int i=100;i>=0;i--){
			trace.addPoint(i,random.nextDouble()*10.0+i);
		}
		System.out.println("1");
		// Make it visible:
		// Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");
		// add the chart to the frame: 
		System.out.println("2");
		frame.getContentPane().add(chart);
		System.out.println("3");
		frame.setSize(400,300);
		// Enable the termination button [cross on the upper right edge]: 
		System.out.println("4");
		frame.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				}
		);
		System.out.println("5");
		frame.setVisible(true);
	}
}
