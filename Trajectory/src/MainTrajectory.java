import java.io.File;

import jaci.pathfinder.Pathfinder;

public class MainTrajectory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Trajectory");
		Tank tank = new Tank();
		
		tank.mainTank();

		File myFile = new File("myfile.csv");
		Pathfinder.writeToCSV(myFile, tank.trajectory);
	}

}
