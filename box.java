package Pacman;
import Geom.Point3D;
import javax.swing.ImageIcon;

import Coords.MyCoords;

public class box {
	//two variables of points to bound the box
	public Point3D location1=new Point3D(0,0,0);
	public Point3D location2=new Point3D(0,0,0);
	// icon of lava to show on the scrreen
	public ImageIcon icon=new ImageIcon();
	// 4 points of the corners
	public Point3D leftdown=new Point3D(0,0,0);
	public Point3D rightdown=new Point3D(0,0,0);
	public Point3D rightup=new Point3D(0,0,0);
	public Point3D leftup=new Point3D(0,0,0);
	// object to use map methods
	public Map object=new Map();
	// 4 point to for the far away corners
	public Point3D ldgps=new Point3D(0,0,0);
	public Point3D lugps=new Point3D(0,0,0);
	public Point3D rdgps=new Point3D(0,0,0);
	public Point3D rugps=new Point3D(0,0,0);

	public int id;
	public box() {
		icon=new ImageIcon("C:\\temp\\lava.jpg");
	}
	// constructor to build a box 
	public box(Point3D a,Point3D b,int c) {
		//set bounds
		location1=a;
		location2=b;
		// id of box
		id=c;
		// set icon
		icon=new ImageIcon("C:\\temp\\lava.jpg");
		// collect bounds
		double xmin=Math.min(location1.getx(),location2.getx());
		double ymin=Math.min(location1.gety(),location2.gety());
		double xmax=Math.max(location1.getx(),location2.getx());
		double ymax=Math.max(location1.gety(),location2.gety());
		// make 4 corners
		lugps=new Point3D(xmin,ymax);
		rugps=new Point3D(xmax,ymax);
		rdgps=new Point3D(xmax,ymin);
		ldgps=new Point3D(xmin,ymin);
		// increase the box -wide
       	leftup=new Point3D(object.gps2pixels(leftup)[0]-25,object.gps2pixels(leftup)[1]+25);
       	rightup=new Point3D(object.gps2pixels(rightup)[0]+25,object.gps2pixels(rightup)[1]+25);
       	rightdown=new Point3D(object.gps2pixels(rightdown)[0]+25,object.gps2pixels(rightdown)[1]-25);
       	leftdown=new Point3D(object.gps2pixels(leftdown)[0]-25,object.gps2pixels(leftdown)[1]-25);
       	//convert to gps
       	leftup=object.pixels2gps((int)leftup.getx(),(int)leftup.gety());
       	rightup=object.pixels2gps((int)rightup.getx(),(int)rightup.gety());
       	rightdown=object.pixels2gps((int)rightdown.getx(),(int)rightdown.gety());
       	leftdown=object.pixels2gps((int)leftdown.getx(),(int)leftdown.gety());

	}
	
	
  /**
   * this method convert box to string
   * @return string which represnt box
   */
	public String toString() {
		String result;
		result= "box "+" in location "+location1.toString()+'\n'+location2.toString();
		return result;
	}
}
