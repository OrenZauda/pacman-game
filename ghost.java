package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;

public class ghost {
	public Point3D location;
	public double mps;
	public double Eatradius;
	public int id;//id
    public ImageIcon icon=new ImageIcon("C:\\temp\\2.png");

	public ghost(Point3D r,double a,double b,int c) {
		mps=a;
		Eatradius=b;
		id=c;
		location=r;
	}
	public ghost() {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
