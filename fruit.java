package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

public class fruit {
	public Point3D location;
	public double weight;
	public int id;//id
	public File picture= new File("C:\\Users\\orenz\\Desktop\\‏‏תיקיה חדשה\\pacman.png");
 
	public fruit(Point3D x,double y,int z) {
		location=x;
		weight=y;
		id=z;
	}

	public String toString() {
		String result;
        result= "fruit "+id+" in location "+location.toString()+" weight "+weight;
        return result;
	}

}
