package Pacman;
import java.io.File;
import java.io.FileReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Coords.MyCoords;
import Geom.Point3D;

import java.lang.Object;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;

public class Map {
	public BufferedImage map;
	public BufferedImage pacman;
	public int width=1433;
	public int height=642;
	public final double north=32.106046;
	public final double south=32.101858;
	public final double west=35.202574;
	public final double east=35.212405;

	public Map() {
		try
		{
			map = ImageIO.read(new File("C:\\temp\\ariel1.png"));

		}
		catch (IOException e)
		{
			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			e.printStackTrace();
		}
	}

	//this method converting gps coordinates to pixels
	public int[] gps2pixels(Point3D a) {
		//initializing array to store altitude and longtitude
		int result[]=new int[3];
		//calculating the altitude
		// the location of the "y" relatively to the picture span
		//(zero to map.hight), is the same location relatively to the 
		//map gps borders (north/south)

		//get the span of the altitude of the map
		double span=north-south;
		//substruct, in order to locate the s relatively to the span
		double temp=a.getx()-south;
		temp=temp/span;
		// store the result
		result[0]=(int)(height-temp*height);
		//same shit, just with longtitude
		span=east-west;
		temp=a.gety()-west;
		temp=temp/span;
		result[1]=(int)(width-temp*width);
		//collect the altitude
		result[2]=(int)a.getz();
		//return the results
		return result;
	}
	//this method convert pixels to coordinates  
	public Point3D pixels2gps(int x,int y) {
		// initializing Point3D to restore the result
		Point3D temp = new Point3D(0,0,0);
		// get the span of the pixels 
		// of the map and store the relative location	
		double fragment=(double)y/width;
		// get the longtitude span of the map
		double discount=east-west;
		// claculate the relative size of longtitude
		temp.setLon(east-discount*fragment);
		//same with the lontitude
		fragment=(double)x/height;
		discount=north-south;
		temp.setAlt(north-fragment*discount);
		temp.setLat(0);
		return temp;
	}
	//this method returns distance between two pixels
	public double distance(int x1,int y1,int x2,int y2) {
		MyCoords object=new MyCoords();
		//converting 2 location to gps coordinates
		Point3D	firstpoint= pixels2gps(x1,y1);
		Point3D	Secondpoint= pixels2gps(x2,y2);
		//return the distance
		return(object.distance3d(firstpoint,Secondpoint));
	}
	//this method calculate the angle between 2 pixels
	public double angle(int x1,int y1,int x2,int y2) {
		//object to use angle calculate method
		MyCoords object= new MyCoords();
		//converting 2 location to gps coordinates
		Point3D	firstpoint= pixels2gps(x1,y1);
		Point3D	Secondpoint= pixels2gps(x2,y2);
        return (object.azimuth_elevation_dist(firstpoint, Secondpoint)[0])%360;
	}




	public static void main(String[] args) {
		Map a = new Map();
        Point3D r= new Point3D(352,185,0);
        System.out.println(a.pixels2gps(352,185));

	}

}
