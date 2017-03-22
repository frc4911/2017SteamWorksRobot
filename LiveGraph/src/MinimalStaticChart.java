import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class MinimalStaticChart implements Runnable {

	Chart2D chart = null;
	ITrace2D traceFlywheel = null;
	ITrace2D traceFeeder = null;
	ITrace2D traceDriveTrainRight = null;
	ITrace2D traceDriveTrainLeft = null;
	
	public MinimalStaticChart() {
		//super();
	}
	
	Color red = new Color(255,0,0);
	  
	public void init(){

		// Create a chart:  
		chart = new Chart2D();
		// Create an ITrace: 
		//traceFlywheel = new Trace2DSimple(); 
		traceDriveTrainRight = new Trace2DSimple(); 
		traceDriveTrainLeft = new Trace2DSimple();
		
		traceDriveTrainRight.setColor(red);
		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		//chart.addTrace(traceFlywheel);    
		chart.addTrace(traceDriveTrainLeft);    
		chart.addTrace(traceDriveTrainRight);    
		//chart.addTrace(traceFeeder);    
		// Add all points, as it is static: 
//		for(int i=100;i>=0;i--){
//			//trace.addPoint(i,random.nextDouble()*10.0+i);
//			trace.addPoint(i,MainLiveGraph.cd.graphValue);
//		}
		// Make it visible:
		// Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");

		JButton clearButton = new JButton("clear");
		clearButton.setSize(30, 30);
		clearButton.setLocation(0, 0);
		frame.getContentPane().add(clearButton);
		clearButton.addActionListener(new ButtonListener());

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
	double lastValueLeft = -999;
	double lastValueRight = -999;
	int sameCount = 5;
	
	@Override
	public void run() {
		
		double pt, pt2;
		while (true){

			int choice = 1;
			switch (choice){
				case 0:
					pt = MainLiveGraph.cd.speedRPMFlywheel;
					if (pt >= 0){ //5500
						traceFlywheel.addPoint(counter,pt);
						//traceFeeder.addPoint(counter,MainLiveGraph.cd.speedRPMFeeder);
						counter++;
						if (counter > 5500){
							traceFlywheel.addPoint(counter++,pt);
						}
//						else if (counter == 0)
//							traceFlywheel.addPoint(counter++,5500);
//						else
//							traceFlywheel.addPoint(counter++,6250);
					}
					break;
				case 1:
					pt = MainLiveGraph.cd.DriveTrainLeft;
					pt2 = MainLiveGraph.cd.DriveTrainRight;
					
					if ((pt == lastValueLeft) && (pt2 == lastValueRight)){
						sameCount--;
					}
					else{
						sameCount = 5;
						lastValueLeft = pt;
						lastValueRight = pt2;
					}
					if (sameCount>0){
						traceDriveTrainLeft.addPoint(counter,pt);
						traceDriveTrainRight.addPoint(counter,pt2);
						counter++;
					}
					break;
				case 2:
					pt = MainLiveGraph.cd.voltage;
					if (pt != -999)
						traceFlywheel.addPoint(counter++,pt);
					break;
				case 3:
					pt = MainLiveGraph.cd.position;
					if (pt != -999)
						traceFlywheel.addPoint(counter++,pt);
					break;
				case 4:
					pt = MainLiveGraph.cd.get;
					if (pt != -999)
						traceFlywheel.addPoint(counter++,pt);
					break;
				case 5:
					pt = MainLiveGraph.cd.lineCount;
					if (pt != -999)
						traceFlywheel.addPoint(counter++,pt);
					break;
			}
			
			try { Thread.sleep(100); } catch (InterruptedException e) {}
		}
	}
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//traceFlywheel.removeAllPoints();
			traceDriveTrainLeft.removeAllPoints();
			traceDriveTrainRight.removeAllPoints();
			counter =0;
			
		}
		
	}
	
}
