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
		double [][] time = timepath(game.myplayer,game.fruits,game.pacmans);
		//variables to save the shortest time between pacman and fruit
		int v=0;
		//find the shortest time
		double min=time[0][0];
		for (int i = 0; i < time.length; i++) {
			for (int j = 0; j < time[0].length; j++) {
				if(time[i][j]<min) {
					min=time[i][j];
					 v=j;
				}
			}
		}

		//in case the current chased object is fruit
		if(v<game.fruits.size()) {
			double [] result= {0,game.fruits.get(v).id,0};
			return result;
		}
		//in case the current chased object is pacman
		else {
			double [] result= {0,game.pacmans.get(v-game.fruits.size()).id,1};
			return result;

		}

	}





	public double[][] timepath(pacman a,ArrayList <fruit>b,ArrayList <pacman>c) {
		//		System.out.println(Arrays.toString(b));
		//		System.out.println(Arrays.toString(a));
		MyCoords object=new MyCoords();
		double [][] temp = new double [1][b.size()+c.size()];

		for (int j =0; j < temp[0].length; j++) {
			if(j<b.size()) {
				temp[0][j]=(object.distance3d(a.location,b.get(j).location)-a.Eatradius)
						/a.mps;
			}
			else {
				temp[0][j]=(object.distance3d(a.location,c.get(j-b.size()).location)-a.Eatradius)
						/a.mps;
			}
		}

		//        System.out.println(temp.length+" and "+temp[0].length);
		return temp;
	}
}
