package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

public class pacman {

	public Point3D location;
	public double mps;
	public double Eatradius;
	public int z;//id
	public File pictrue = new File("C:\\Users\\orenz\\Desktop\\‏‏תיקיה חדשה\\pacman 2.png");

	public pacman(Point3D x, double y,double radius,int i) {
		location=x;
		mps=y;
		Eatradius=radius;
		z=i;
	}

	public String toString() {
		String result;
		result= "PACMAN "+z+" in location "+location.toString()+" with eat radius "+Eatradius;
		return result;
	}

}
