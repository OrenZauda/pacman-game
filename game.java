package Pacman;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import Geom.Point3D;
import java.util.Iterator;

import javax.swing.ImageIcon;

import java.io.FileNotFoundException;

public class game extends ArrayList {
	@Override
	public String toString() {
		return "game [pacmans=" + pacmans + ", fruits=" + fruits + '\n'
				+ " boxes "+boxes+" ghosts "+ ghosts;
	}
	//arraylist to store pamans and fruits
	public ArrayList <pacman> pacmans = new ArrayList();
	public ArrayList <fruit> fruits = new ArrayList();
	public ArrayList <ghost> ghosts = new ArrayList();
	public ArrayList <box> boxes = new ArrayList();
	pacman myplayer = new pacman();

	public int fruitcounter=0;
	public int pacmancounter=0; 

	//simple constructor
	public game() {	
	}

	//add method
	public void addfruit(fruit x) {
		fruits.add(x);
		fruitcounter++;
	}	
	//add method
	public void addpacman(pacman x) {
		pacmans.add(x);
		pacmancounter++;
	}
	// this method convers arraylist to array
	public Point3D[] ptoarray(ArrayList x) {
		Iterator <pacman> here = pacmans.iterator();
		Point3D[] results= new Point3D[x.size()];
		int i=0;
		while(here.hasNext()) {
			pacman temp=here.next();
			results[i++]=temp.location;
		}
		return results;
	}
	public Point3D[] ftoarray(ArrayList x) {
		Iterator <fruit> here = fruits.iterator();
		Point3D[] results= new Point3D[x.size()];
		int i=0;
		while(here.hasNext()) {
			fruit temp=here.next();
			results[i++]=temp.location;
		}
		return results;
	}

	public void setmyplayer(pacman p) {
		myplayer.location.setAlt( p.location.x());
		myplayer.location.setLon( p.location.y());
		myplayer.Eatradius=p.Eatradius;
		myplayer.id=p.id;
		myplayer.mps=p.mps;
		myplayer.icon=new ImageIcon("C:\\temp\\rick.png");
	}
	//construcot use csv file to create a game!

	public game(File csvfile) throws IOException {
		// reader to read the csv file
		BufferedReader read = new BufferedReader(new FileReader(csvfile));	
		//start with the second line, because the first is headline
		String line=read.readLine();
		String cvsSplitBy= ",";
		line=read.readLine();
		while(line!=null) {
			//store any detail in a cell in the array column
			String[] column = line.split(",");
			// recieving data from the array
			String type= column[0];
			int id= Integer.parseInt((column[1]));
			double lat=Double.parseDouble(column [2]);
			double lon=Double.parseDouble(column[3]);
			double alt=Double.parseDouble(column[4]);
			double spdwt= Double.parseDouble(column[5]);
            System.out.println(lat);
            System.out.println(lon);
			//if the current line represents pacman, create proper pacman 
			if(type.equals("P")) {
				double radius= Double.parseDouble(column[6]);
				pacman temp= new pacman((new Point3D(lat,lon,alt)),spdwt,radius,id);
				//add the pacman to the array
				pacmans.add(temp);
			}
			//if the current line represents fruit, create proper fruit 
			if(type.equals("F")) {
				fruit temp= new fruit((new Point3D(lat,lon,alt)),spdwt,id);
				//add the fruit to the array
				fruits.add(temp);

			}
			//if the current line represents box, create proper box 

			if(type.equals("B")) {
				box temp= new box();
				temp.location1=new Point3D(lat,lon,0);
				temp.location2=new Point3D(Double.parseDouble(column[5]),Double.parseDouble(column[6]));

				//add the fruit to the array
				boxes.add(temp);

			}
			if(type.equals("G")) {
				double radius= Double.parseDouble(column[6]);
				ghost temp= new ghost();
				temp.location=new Point3D(lat,lon,0);
				temp.Eatradius=radius;
				temp.mps=spdwt;
				//add the pacman to the array
				ghosts.add(temp);
			}


			// go to the next line and repeat adding items
			line="";
			line=read.readLine();
		}
		read.close();

	}
	////////////////////////////////////////////////
	// function which cultivate csv file from our game
	public void tocsv(String s) throws IOException {
		//string to store the result
		String result="Type,id,lat,lon,alt,speed/weight,radius,"+pacmancounter+","+fruitcounter+'\n';
		// iterator to go thru the pacman array
		Iterator <pacman> here = pacmans.iterator();
		int i=0;
		// turn any pacman to string, seperated with ",", and with /n at the end
		while(here.hasNext()) {
			pacman temp= here.next();
			result+="P,"+i+","+temp.location.getx()+","+temp.location.gety()+","
					+temp.location.getz()+","+temp.mps+","+temp.Eatradius+'\n';
			// increase i which represent the id
			i++;
		}
		// iterator to go thru the fruit array
		Iterator <fruit> there= fruits.iterator();
		//turn any fruit to string, seperated with ",", and with /n at the end
		while(there.hasNext()) {
			fruit temp= there.next();
			result+="F,"+i+","+temp.location.getx()+","+temp.location.gety()+","
					+temp.location.getz()+","+temp.weight+'\n';
			// increase i which represent the id
			i++;
		}
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new File(s));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		pw.write(result);
		pw.close();
		System.out.println("done!");
		//		//save the file in location "s", the user gave us
		//		try (PrintWriter out = new PrintWriter(s)) {
		//			out.println(result);
		//		}

	}
	public static void main(String[] args) throws IOException {

		//////////// checking tocsv method//////////////////////////
//		game da = new game();
//		Point3D x= new Point3D(1,2,3);
//		Point3D y= new Point3D(1,2,3);
//		pacman a=new pacman(x,1,1,1);
//		fruit b= new fruit(y,2,2);
//		da.fruits.add(b);
//		da.pacmans.add(a);
//        da.tocsv("C:\\temp\\a.csv");
     	game da = new game(new File("C:\\Users\\orenz\\Desktop\\EX4\\Ex4_OOP\\data\\Ex4_OOP_example8.csv"));

	}
}
