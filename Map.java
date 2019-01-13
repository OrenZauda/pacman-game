package Pacman;
import java.io.File;
import java.io.FileReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.util.ArrayList;

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
	public final ImageIcon icon= new ImageIcon("C:\\temp\\ariel1.png");;
	public ImageIcon pacman=new ImageIcon("C:\\temp\\pac.png");
	public int width=1433;
	public int height=642;
	public final double north=32.106046;
	public final double south=32.101858;
	public final double west=35.202574;
	public final double east=35.212405;

	public Map() {	
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
		result[1]=(int)(height-temp*height);
		//same shit, just with longtitude
		span=east-west;
		temp=a.gety()-west;
		temp=temp/span;
		result[0]=(int)(temp*width);
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

	public int[] gps2pixels2(Point3D a) {
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
		result[1]=(int)(height-temp*height);
		//same shit, just with longtitude
		span=east-west;
		temp=a.gety()-west;
		temp=temp/span;
		result[0]=(int)(width-temp*width);
		//collect the altitude
		result[2]=(int)a.getz();
		//return the results
		return result;
	}

   /**
    * this method convert array list from the server
    * to game
    * @param x represts arraylist
    * @return game with new data
    */

	public game togame(ArrayList<String> x) {
		// new game to restore the data and return it
		game game=new game();
		// moving row by row and recive data from the array list
		for(int i=0;i<x.size();i++) {
			String line= x.get(i);
			String[] a= line.split(",");
			// if the current object is Myplayer, build and add proper Myplayer
			if(a[0].equals("M")) {
				Point3D temp =new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),0);
				game.setmyplayer(new pacman(temp,Double.parseDouble(a[5]),Double.parseDouble(a[6]),true));
			}
			// if the current object is pacman, build and add proper pacman
			if(a[0].equals("P")) {
				Point3D temp =new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),0);
				game.pacmans.add(new pacman(temp,Double.parseDouble(a[5]),Double.parseDouble(a[6]),Integer.parseInt(a[1])));
			} 
			// if the current object is ghost, build and add proper ghost
			if(a[0].equals("G")) {
				Point3D temp =new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),0);
				game.ghosts.add(new ghost(temp,Double.parseDouble(a[5]),Double.parseDouble(a[6]),Integer.parseInt(a[1])));

			} 
			// if the current object is fruit, build and add proper fruit

			if(a[0].equals("F")) {
				Point3D temp =new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),0);
				game.fruits.add(new fruit(temp,Double.parseDouble(a[5]),Integer.parseInt(a[1])));

			} 
	    	// if the current object is box, build and add proper box

			if(a[0].equals("B")) {
				Point3D temp =new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),0);
				Point3D temp2 =new Point3D(Double.parseDouble(a[5]),Double.parseDouble(a[6]),0);
				game.boxes.add(new box(temp,temp2,Integer.parseInt(a[1])));

			} 
		}
		return game;
	}
	// main class for testing
	public static void main(String[] args) {
		Map f=new Map();
		String s="M,1,32.10363077147885,35.20559945080251,0.0,20.0,1.0, P,1,32.103891008542526,35.20700986344075,0.0,10.0,1.0, F,0,32.10447009936512,35.20799300018584,0.0,1.0, F,1,32.104762143527395,35.20826330026482,0.0,1.0,";
		ArrayList <String> v=new ArrayList <String>() ;
		v.add(s);
		game t= f.togame(v);
		System.out.println(t.myplayer);
	}
	
	
	

	

}