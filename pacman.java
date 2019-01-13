package Pacman;
import javax.swing.ImageIcon;

import Geom.Point3D;

public class pacman {

	public Point3D location = new Point3D(0,0,0);
	public double mps=1;
	public double Eatradius;
	public boolean myplayer=false;
	public int id;//id
	public double rate=0;
    public ImageIcon icon=new ImageIcon("C:\\temp\\iconfinder_Cute Ball - Games_32132.png");
    
	public pacman(Point3D x, double y,double radius,int i) {
		location=x;
		mps=y; 
		Eatradius=radius;
		id=i; 
	}
	public pacman(Point3D x, double y,double radius,boolean a) {
		location=x;
		mps=y; 
		Eatradius=radius;
		id=0; 
		if (a) {
			icon=new ImageIcon("C:\\temp\\ironz.png");
		}
		myplayer=true;
	}


	public pacman() {
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		String result;
		result= "PACMAN "+id+" in location "+location.toString()+" with eat radius "+Eatradius;
		return result;
	}

}
