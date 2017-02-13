//package src;

import java.util.ArrayList;

public class MassObjectHolder {
	//eventually create in CameraRun a method that goes and makes and separates the masses
	
	ArrayList<GreenMass> pointer = new ArrayList<GreenMass>();
	
	public MassObjectHolder()
	{
	}
	
	public void GreenMassInit(int objectNum, int xStart, int yStart, int lineLength, int xEnd)
	{
//		System.out.println("init new GreenMass");
		pointer.add(objectNum, new GreenMass(xStart,yStart, lineLength,xEnd));
	}
	public GreenMass getGPiece(int key)
	{
		return pointer.get(key);
	}
	public void gPieceAddToMass(int key, int xStart, int yStart, int lineLength, int xEnd)
	{
		pointer.get(key).addToMass(xStart, yStart, lineLength, xEnd);
	}
	public void gPieceCombineMass(int keyA, int keyB)
	{
		//combine two masses
	    pointer.get(keyA).combineWith(pointer.get(keyB));
		//remove second mass
	    pointer.remove(keyB);
	}
	
	public void putMaskOverlap(int key, double input)
	{
		pointer.get(key).putMaskOverlap(input);
	}
	public double getMaskOverlap(int key)
	{
		return pointer.get(key).getMaskOverlap();
	}
	
	public void removeMass(int key)
	{
		pointer.remove(key);
	}
	
	public void dumpPast()
	{
		for (int i = 0; i < pointer.size(); i++)
		{
			pointer.remove(0);
		}
	}
}
