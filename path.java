package Pacman;

import java.util.ArrayList;
import java.util.Iterator;
import Coords.MyCoords;
import Geom.Point3D;

public class path {
	// arraylist to store the locations
	ArrayList <Point3D> path = new ArrayList();
	// MyCoords object to use the method "distanse3d"
	public MyCoords object;
	//counter to know how many locations we have
	public int counter=0;
	//add method
	public void add(Point3D x) {
		path.add(x);
		counter++;
	}
	// this method store the locations in array
	
	public Point3D[] toarray() {
		// this array is the result
		Point3D s[]=new Point3D [counter];
		// iterator to pass thru the arraylist
		Iterator <Point3D> here= path.iterator();
		// variable to move on in the array
		int i=0;
		// fulfilling the array
		while (here.hasNext()) {
			s[i++]=here.next();
		}
		//return results
		return s;
	}
	// this method calculate the length of the path
	// note: the length is not the shortest possible, its only
	// the length between the points according to the time they store in the array
	public double length() {
		// variable to return the results
		double result=0;
		// having array from the arraylist
		Point3D temp[]=this.toarray();
		//calculating the line between the points
		for(int i=0;i<temp.length-1;i++) {
			result+= object.distance3d(temp[i], temp[i+1]);	
		}

		return result;
	}
}
