package Pacman;
import Geom.Point3D;
import java.io.File;
import java.io.FileReader;

public class fruit {
	public Point3D location;
	public double weight;
	public int i;//id
	public File picture= new File("C:\\Users\\orenz\\Desktop\\������� ����\\pacman.png");

	public fruit(Point3D x,double y,int z) {
		location=x;
		weight=y;
		i=z;
	}

	public String toString() {
		String result;
        result= "fruit "+i+" in location "+location.toString()+" weight "+weight;
        return result;
	}

}
