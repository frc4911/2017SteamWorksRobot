package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Lidar extends SensorBase {
	I2C i2c;
	int LIDAR_CONFIG_REGISTER = 0x00;
	int LIDAR_DISTANCE_REGISTER = 0x8f;
	byte[] distance;
	int[] dataSet = new int[3];
	

	public int getDistanceOld() {

	byte[] buffer; 
	buffer = new byte[2];

	i2c.write(0x00, 0x04);
	Timer.delay(0.04);
	i2c.read(0x8f, 2, buffer); 


	return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);	
	}
	
	public SS_Lidar(boolean old){
		//i2c = new I2C(I2C.Port.kOnboard,0x62); // this one does not work
		i2c = new I2C(I2C.Port.kMXP,0x62); //this one works

		if (!old){
			i2c.write(0x45, 0x04);
			i2c.write(0x04, 0x20);
			i2c.write(0x11, 0xff);
			i2c.write(0x00, 0x04);
			//SmartDashboard.putNumber("LidarValuesThrownOut:", 0);
			//I don't remember what these are
		}
	}
	public int getDistance(){
		byte[] distanceArray = new byte[2];
		byte[] thisThingy = new byte[1];
		thisThingy[0] = (byte)0x8f;
		i2c.writeBulk(thisThingy);
		i2c.readOnly(distanceArray, 2);
//			SmartDashboard.putNumber("distanceArray[0]", distanceArray[0]);
//			SmartDashboard.putNumber("distanceArray[1]", distanceArray[1]);
		int temp = distanceArray[1] & 0xFF;
		int num = distanceArray[0]<<8;
		//int distance = (distanceArray[0] << 8) + temp;
		int distance = num+temp;
		addDataPoint(distance);
		if(isDataArrayFilled()){
			if((Math.abs(getDataAverage() - distance) > 50)){
				distance = dataSet[1];
				SmartDashboard.putNumber("LidarValuesThrownOut:", SmartDashboard.getNumber("LidarValuesThrownOut:",-20)+1);
			}
		}
		return distance;
	}
    //puts new data point at pos 0, shifts everything up
    void addDataPoint(int data){
    	for(int i=dataSet.length-1;i>0 ;i--){
    		dataSet[i] = dataSet[i-1];
    	}
    	dataSet[0] = data;
    }
    int getDataAverage(){
    	int cumulative = 0;
    	for(int i=0;i<dataSet.length;i++){
    		cumulative += dataSet[0];
    	}
    	return cumulative/dataSet.length;
    }
    boolean isDataArrayFilled(){
    	for(int i=0;i<dataSet.length;i++){
    		if(dataSet[i] == 0){
    			return false;
    		}
    	}
    	return true;
    }
	public void update(){
		int distance = getDistance();
		SmartDashboard.putNumber("Lidar:", distance);
		SmartDashboard.putNumber("Lidar (in):", distance/2.75);
		//int feet = (int)(distance/2.75)/12;
		//SmartDashboard.putString("sensor/Lidar(ft-in)", feet + "\"" + (int)Math.round(distance/2.75 - feet*12) + "'");
		//SmartDashboard.putString("sensor/Lidar", ""+distance);
	}
    
}


