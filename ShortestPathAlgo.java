package Pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;

public class ShortestPathAlgo {
	public game game=new game();
	public path b;
	// insert the locations of fruits to array
	// array to store the locations of pacmans


	public ShortestPathAlgo(game x) {
		game=x;
		int i=0;
		}
	

	public double[] cal() {
		//get the time matrix
		double [][] time = timepath(game.pacmans,game.fruits);
		//variables to save the shortest time between pacman and fruit
		int u=0,v=0;
		//find the shortest time
		double min=time[v][u];
		for (int i = 0; i < time.length; i++) {
			for (int j = 0; j < time[0].length; j++) {
				if(time[i][j]<min) {
					min=time[i][j];
					u=i; v=j;
				}
			}
		}
		// change location of the pacman to the location of the fruit
		game.pacmans.get(u).location.setAlt(game.fruits.get(v).location.getx());

		double [] result= {game.pacmans.get(u).id,game.fruits.get(v).id,time[u][v]};
		return result;

	}

	



	public double[][] timepath(ArrayList <pacman>a,ArrayList <fruit>b) {
//		System.out.println(Arrays.toString(b));
//		System.out.println(Arrays.toString(a));
		MyCoords object=new MyCoords();
		double [][] temp = new double [a.size()][b.size()];
		for (int i = 0; i < temp.length; i++) {
			for (int j =0; j < temp[0].length; j++) {
				temp[i][j]=object.distance3d(a.get(i).location,b.get(j).location)
						/a.get(i).mps-a.get(i).Eatradius;
			}
		}
//        System.out.println(temp.length+" and "+temp[0].length);
		return temp;
	}
}
