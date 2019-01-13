package Pacman;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import Coords.MyCoords;
import Geom.Point3D;
import Robot.Play;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class myFrame extends JFrame  /*implements MouseListener*/ {
	//MAP OBJECT for using its methods
	public Map object= new Map();
	// title for design
	public static final String title= "Pac-man";
	// button for choosing options
	JMenuBar menuBar=new JMenuBar();
	JMenu Run=new JMenu("Run");
	JMenu submenu=new JMenu("Help");
	JMenuItem pac=new JMenuItem("Pacman");
	JMenuItem my=new JMenuItem("MyPlayer");
	JMenuItem start=new JMenuItem("STRAT!");
	JMenuItem manual=new JMenuItem("manual");
	JMenuItem clear=new JMenuItem("Clear");
	JMenuItem fruit= new JMenuItem("Fruit");
	JMenuItem open= new JMenuItem("open");
	JMenuItem save= new JMenuItem("save");
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	// initial play object to contact with the server
	Play play = new Play();
	// boolean to separate between automatic play
	// and mouseclicked- based play
	public boolean automat=false;
	public JLabel statusbar=new JLabel();
	// variabkes to catch x,y of clicks (in pixels)
	int x=-1,y=-1;
	// game to store all the data
	public game game=new game();
	// booleans to separate between the mouse lisneres
	boolean f=false,p=false,c=false,m=false;
	// variable to build items with (pacman and fruits)
	public int pacid=1;
	public int fruitid=0;
	public double angle=0;
	/**
	 * this method is a constructor.
	 * its includes all the buttons  
	 * @throws IOException
	 */

	public myFrame() throws IOException { 
		//adding buttons to menubar
		Run.add(pac);
		Run.add(my);
		Run.add(fruit);
		Run.add(save);
		Run.add(open);
		Run.add(start);
		Run.add(clear);
		Run.add(manual);
		//set vision on 
		Run.setVisible(true);
		menuBar.add(Run);
		menuBar.setVisible(true);
		setJMenuBar(menuBar);
		//set size of window
		setSize(1500, 1500);
		//set title
		setTitle("Pacman Game!!!");
		//set icon on the window
		setIconImage(object.pacman.getImage());
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	    repaint();
	    // open button, open a window in which the user can find his file
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// file chooser to open window of file search
				JFileChooser a= new JFileChooser();
				a.setCurrentDirectory(new File ("C:\\Users\\orenz\\Desktop\\EX4\\Ex4_OOP\\data"));
				a.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
				a.setDialogTitle("Open");
				// new file to store the selection
				File selectedFile=new File("");
				int t=a.showOpenDialog(null);
				if (t == JFileChooser.APPROVE_OPTION) {
					selectedFile = a.getSelectedFile();
				}
				// tell the server about the file we chose
				play=new Play(selectedFile.getAbsolutePath());
				try {
					game=new game(selectedFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				repaint();
			}
		});
		//save button, to save the game
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//file chooser to chose the location of file
				JFileChooser a= new JFileChooser();
				a.setDialogTitle("save");
				a.setSelectedFile(new File ("ss"));
				int t=a.showSaveDialog(null);

			}
		});
		// clear button used to clear the whole game and start over from the begining
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// boolean to avoid othe buttons to clear the game
				c=true;
				if(c) {
					//turn game to be empty
					game=new game();
					// zero this veriables to start the id count from the begining
					pacid=0;
					fruitid=0;
				}
				// set boolean false
				c=false;
				repaint();

			}
		});







		/** this button-method purpose is start the game when
		 * the start button is pressed
		 * @param pacman mine used to capture "myPlayer"
		 * @param o is instance of Mycoords, its used to start the a
		 * azimuth method, in order to find the angle between 
		 * myPlayer and the current chased fruit.
		 * @param currentchased store current chased fruit.
		 */

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// set automat boolean true
				automat=true;
				//start the game
				thread thread =new thread(myFrame.this,play);
				thread.start();
			}

		});
		// manual button to use mouse for state angle
		manual.addActionListener(new ActionListener() {
			@Override
		
			public void actionPerformed(ActionEvent e) {
				thread thread =new thread(myFrame.this,play);
				//set boolean false, to tell the program to use the mouse
				automat=false;
				
				thread.start();
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
//						System.out.println(event.getX()+","+event.getY());

						if(play.isRuning()) {
						// recieve x and y from the mouse listener
						y=object.width-event.getX();
						x=event.getY();
						Map object = new Map();		
						Point3D s=object.pixels2gps(x, y);
						MyCoords o=new MyCoords();
						// send correct angle to thread
						thread.setazimuth(o.azimuth_elevation_dist(game.myplayer.location, s)[0]);
						}

					}
				});



			}
		});
				
		//my button to create myplayer  
		my.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// set all booleans false, to shut down othe mouse listener
				f=false;
				p=false;
				c=false;
				m=true;
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						// collect c,y from mouse listener
						//TODO Auto-generated method stub
						if(m) {
							y=object.width-event.getX();
							x=event.getY();
							Map object = new Map();		
							Point3D s=object.pixels2gps(x, y);
							//create new pacman with boolean true, to //
							//to tell game its a "myplayer"
							pacman temp=new pacman(s,1,1,true); 
							game.setmyplayer(temp);
//							System.out.println(game.myplayer.toString());
							play.setInitLocation(temp.location.getx(),temp.location.gety());
							repaint();

						}
						//shut down this mouse listener
						m=false;


					}
				});



			}
		});
		//pac button to create new pacmans
		pac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//set all boolean to false to shut down all listener
				//except our lusterner
				// TODO Auto-generated method stub
				f=false;
				p=true;
				c=false;
				m=false;
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {

						//TODO Auto-generated method stub
						if(p) {
							//collect x and y to know the location
							y=object.width-event.getX();
							x=event.getY();
							//create point3d for the new pacman
							Point3D s=object.pixels2gps(x, y);
							//new pacman
							pacman temp=new pacman(s,1,1,pacid++); 
							///add pacman to game 
							game.pacmans.add(temp);
							//repaint the updated game
							repaint();

						}

					}
				});



			}
		});
		// fruit button to create fruits items
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//shut down all mouse listenners except this one 
				f=true;
				p=false;
				c=false;
				m=false;
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {					
						// TODO Auto-generated method stub
						
						if(f) {
							// store x,y of click
							y=object.width-event.getX();
							x=event.getY();
							// new button to create fruit
							Point3D s=object.pixels2gps(x, y);
							//new fruit
							fruit temp=new fruit(s,1,fruitid++);  
							// add this fruit to game
							game.fruits.add(temp);
							// paint updated game
							repaint();


						}
					}
				});
			}
			
		});
}
	/**
	 * this methodd paint all the data on window
	 */

	public void paint(Graphics g) {
		this.setJMenuBar(menuBar);
		
		// draw the image
		g.drawImage(object.icon.getImage(),0,0, getWidth(), getHeight(),null);
		Map object = new Map();	
		if(game.fruits!=null&&game.pacmans!=null) {
			//draw all pacmans in the array
			Iterator <pacman> here =game.pacmans.iterator();
			//draw all fruits in the array
			Iterator <fruit> there =game.fruits.iterator();
			//draw pacmans
			for(int i=0;i<game.pacmans.size();i++){
				// store the current pacman
				pacman temp =here.next();
				// turn location to pixels view
				Point3D r=new Point3D(temp.location.getx(),temp.location.gety(),0);
				double g1,s,x=object.gps2pixels(r)[0],y=object.gps2pixels(r)[1];
				// make sure the location steack to a given size of window in any time
				x=(double)x/object.width;
				y=(double)y/object.height;
				g1=(double)x*getWidth();
				s=(double)y*getHeight();
				// couple fixes
				if(x>0.25&&x<0.5) {g1=g1+13;}
				if(x>0.5) {g1=g1+20;}
				if(x>0.6) {g1=g1+5;}
				if(x>0.75) {g1=g1+7;}
				g.drawImage(game.pacmans.get(i).icon.getImage(),(int)g1,(int)s,15,15,null);
//				
			}
			if(game.myplayer!=null) {
			//draw myplayer
			Point3D r=new Point3D(game.myplayer.location.getx(),game.myplayer.location.gety(),0);
			double g1,s,x=object.gps2pixels(r)[0],y=object.gps2pixels(r)[1];
			// make sure the location steack to a given size of window in any time

			x=(double)x/object.width;
			y=(double)y/object.height;
			g1=(double)x*getWidth();
			s=(double)y*getHeight();
			//couple fixes
			if(x>0.25&&x<0.5) {g1=g1+13;}
			if(x>0.5) {g1=g1+20;}
			if(x>0.6) {g1=g1+5;}
			if(x>0.75) {g1=g1+7;}
			//draw my player on screen
			g.drawImage(game.myplayer.icon.getImage(),(int)g1,(int)s,35,35,null);
			
			}
			
			//draw fruits
			for(int i=0;i<game.fruits.size();i++) {
				//store the current fruit
				fruit temp2 =there.next();
				Point3D rr=new Point3D(temp2.location.getx(),temp2.location.gety(),0);
				// turn x,y to pixels
				x=object.gps2pixels(rr)[0];
				y=object.gps2pixels(rr)[1];
				// make sure the location steack to a given size of window in any time
				double u=(double)x/object.width;
				double v=(double)y/object.height;
				double e,d;
				e=(double)u*getWidth();
				d=(double)v*getHeight();
				//couple fixes
				if(u>0.25) {e=e+10;}
				if(u>0.5) {e=e+20;}
				if(u>0.75) {e=e+7;}
				//draw the fruits
				g.drawImage(game.fruits.get(i).icon.getImage(),(int)e,(int)d,15,15,null);
			}
			//draw boxes
			for(int i=0;i<game.boxes.size();i++) {
				//get current box by two points
				Point3D a=game.boxes.get(i).location1;
				Point3D b=game.boxes.get(i).location2;
				// get x;s and y's in pixels
				// make sure the location steack to a given size of window in any time
				double x1,y1,x2,y2;
				x1=object.gps2pixels(a)[0];
				x1=(double)(x1/object.width*getWidth());
				y1=object.gps2pixels(a)[1];
				y1=(double)(y1/object.height*getHeight());
				x2=object.gps2pixels(b)[0];
				x2=(double)(x2/object.width*getWidth());
				y2=object.gps2pixels(b)[1];
				y2=(double)(y2/object.height*getHeight());
				// draw image
				g.drawImage(game.boxes.get(i).icon.getImage(),(int)Math.min(x1,x2),(int)Math.min(y1,y2),(int)Math.abs(x1-x2),(int)Math.abs(y1-y2),null);
			}	
			//draw ghosts
			for(int i=0;i<game.ghosts.size();i++) {
				// save current location
				Point3D a=game.ghosts.get(i).location;
				double x1,y1,x2,y2,u,v;
				u=object.gps2pixels(a)[0];
				v=object.gps2pixels(a)[1];
				// get x's and y's  in the right location
				// make sure the location steack to a given size of window in any time
				x1=(double)(u/object.width);
				y1=(double)(v/object.height);
				x2=(double)(x1*getWidth());
				y2=(double)(y1*getHeight());
				// draw ghost
				g.drawImage(game.ghosts.get(i).icon.getImage(),(int)x2,(int)y2,50,50,null);
			}
		}
	}
	/**
	 *this method listen to mouse clicks and save there coordinates;
	 * @param event represnt any click
	 */
	//clicked==press and release
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		//save the location of clicking
		x=event.getX();
		y=event.getY();
	
	}


     /**
      * main method for testing
      * @param args do somethond
      * @throws IOException
      */
	public static void main(String[] args) throws IOException {
		myFrame a= new myFrame();
		a.setSize(a.object.width,a.object.height);

	}


	



}
