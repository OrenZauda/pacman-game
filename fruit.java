package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;

public class fruit {
	public Point3D location;
	public double weight;
	public int id;//id
    public ImageIcon icon=new ImageIcon("C:\\temp\\apple.png");
 
	public fruit(Point3D x,double y,int z) {
		location=x;
		weight=y;
		id=z;
	}

	public fruit() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String result;
        result= "fruit "+id+" in location "+location.toString()+" weight "+weight;
        return result;
	}

}
