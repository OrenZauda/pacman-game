package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

public class pacman {

	public Point3D location;
	public double mps=1;
	public double Eatradius;
	public int id;//id
	public double rate=0;
	public File pictrue = new File("C:\\Users\\orenz\\Desktop\\‏‏תיקיה חדשה\\pacman 2.png");

	public pacman(Point3D x, double y,double radius,int i) {
		location=x;
		mps=y; 
		Eatradius=radius;
		id=i; 
	}

	public String toString() {
		String result;
		result= "PACMAN "+id+" in location "+location.toString()+" with eat radius "+Eatradius;
		return result;
	}

}
