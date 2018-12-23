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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class myFrame extends JFrame /*implements MouseListener*/ {
	public boolean isRunning=true;
	public static final int width= 1433;
	public static final int height= 642;
	public static final String title= "Pac-man";
	JMenuBar menuBar=new JMenuBar();
	JMenu Run=new JMenu("Run");
	JMenu submenu=new JMenu("Help");
	JMenuItem pac=new JMenuItem("Pacman");
	JMenuItem start=new JMenuItem("EAT!");
	JMenuItem clear=new JMenuItem("Clear");
	JMenuItem fruit= new JMenuItem("Fruit");
	JMenuItem open= new JMenuItem("open");
	JMenuItem save= new JMenuItem("save");
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	public ImageIcon map;
	public BufferedImage pacman;
	public BufferedImage apple;
	public JPanel mouse;
	public JLabel statusbar=new JLabel();
	int x=-1,y=-1;
	public game game=new game();
	boolean f=false,p=false,c=false;
	public int pacid=0;
	public int fruitid=0;
	public int stop=0;
	Point3D [] result=new Point3D[14];

	public myFrame() { 

		try
		{
			map = new ImageIcon("C:\\temp\\ariel1.png");
			apple=ImageIO.read(new File("C:\\temp\\apple.png"));
			pacman = ImageIO.read(new File("C:\\temp\\iconfinder_Cute Ball - Games_32132.png"));

		}
		catch (IOException e)
		{ 
			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			e.printStackTrace();
		}	
		Run.add(pac);
		Run.add(fruit);
		Run.add(save);
		Run.add(open);
		Run.add(start);
		Run.add(clear);
		Run.setVisible(true);
		menuBar.add(Run);
		menuBar.setVisible(true);
		setJMenuBar(menuBar);
		setSize(1500, 1500);
		setTitle("Pacman Game!!!");
		setIconImage(pacman);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser a= new JFileChooser();
				a.setCurrentDirectory(new File ("c:\\"));
				a.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
				a.setDialogTitle("Open");
				int t=a.showOpenDialog(null);

				repaint();
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser a= new JFileChooser();
				a.setDialogTitle("save");
				a.setSelectedFile(new File ("ss"));
				int t=a.showSaveDialog(null);

			}
		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c=true;
				if(c) {
					game=new game();
					System.out.println(game.fruits.toString());
					System.out.println(game.pacmans.toString());
					pacid=0;
					fruitid=0;
				}

				c=false;
				repaint();

			}
		});









		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.fruits.size()>0&&game.pacmans.size()>0) {
					
					ShortestPathAlgo object= new ShortestPathAlgo(game);
					fruit eaten=new fruit(new Point3D(1,1,1),1,1);
					fruit foo=new fruit(new Point3D(1,1,1),1,1);
					pacman temp2=new pacman(new Point3D(1,1,1),1,1,1);
					pacman temp=new pacman(new Point3D(1,1,1),1,1,1);
					int u,v=0;
					double set[]=object.cal();
					Iterator <fruit> there= game.fruits.iterator();
					while(there.hasNext()) {
						foo=there.next();
						if(set[1]==foo.id) {
							eaten=foo;
						}
					}
					
					Iterator <pacman> here= game.pacmans.iterator();
					while(here.hasNext()) {
						temp= here.next();
						if(temp.id==set[0]) {
							temp2=temp;
						}
				
					}
					System.out.println(temp.toString());
					System.out.println(eaten.toString());
					
					Point3D[] line = drawline(temp2.location,eaten.location);
					for(int i=0;i<14;i++) {
					pacman ln=new pacman (line[i],1,1,700);
					game.pacmans.add(ln);
					repaint();
					}
					for (int i=0;i<game.pacmans.size();i++) {
						System.out.println(game.pacmans.get(i));
					}
					game.fruits.remove(eaten);
					temp2.location.setLon(eaten.location.gety());
					temp2.location.setAlt(eaten.location.getx());

					repaint();
				}


				repaint();

			}

			private Point3D[] drawline(Point3D a, Point3D b)
			{
				MyCoords ob= new MyCoords();
				
					Point3D dis= new Point3D(1,1,1);
					dis.setAlt(b.getx()-a.getx());
					dis.setLon(b.gety()-a.gety());
					dis.setAlt(dis.getx()/15);
					dis.setLon(dis.gety()/15);
					for (int i = 0; i < result.length; i++) {
Point3D t=new Point3D(a.getx()+(dis.getx()*(i+1)),a.gety()+dis.gety()*(i+1),0);
						result[i]=t;
					
				}
				return result;
			}

		});

						pac.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								f=false;
								p=true;
								c=false;
								addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent event) {

										//TODO Auto-generated method stub
										if(p&&!f) {
											x=event.getX();
											y=event.getY();
											System.out.println("pacman " +x+" and y "+y);
											Map object = new Map();		
											Point3D s=object.pixels2gps(x, y);
											pacman temp=new pacman(s,1,1,pacid++); 
											game.pacmans.add(temp);
											repaint();

										}

									}
								});



							}
						});
						fruit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								f=true;
								p=false;
								c=false;
								addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent event) {					
										// TODO Auto-generated method stub
										if(f&&!p) {
											x=event.getX();
											y=event.getY();
											
											System.out.println("fru "+x+" "+y);
											Map object = new Map();		
											Point3D s=object.pixels2gps(x, y);
											fruit temp=new fruit(s,1,fruitid++);                     	
											game.fruits.add(temp);
											repaint();						
										}
									}
								});
							}
						});
	}

	public void paint(Graphics g) {
		this.setJMenuBar(menuBar);
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;

		// draw the image
		g2.drawImage(map.getImage(),0,0, getWidth(), getHeight(),null);
		Map object = new Map();	
		if(game.fruits!=null&&game.pacmans!=null) {
			//draw all pacmans in the array
			Iterator <pacman> here =game.pacmans.iterator();
			//draw all fruits in the array
			Iterator <fruit> there =game.fruits.iterator();	
			for(int i=0;i<game.pacmans.size();i++){

				pacman temp =here.next();
				
				Point3D r=new Point3D(temp.location.getx(),temp.location.gety(),0);
				double g1,s,x=object.gps2pixels(r)[0],y=object.gps2pixels(r)[1];
				x=(double)x/width;
				y=(double)y/height;
				g1=(double)x*getWidth();
				s=(double)y*getHeight();
				if(x>0.25&&x<0.5) {g1=g1+13;}
				if(x>0.5) {g1=g1+20;}
				if(x>0.6) {g1=g1+5;}
				if(x>0.75) {g1=g1+7;}
				g2.drawImage(pacman,(int)g1,(int)s,15,15,null);
//				if(temp.id==700) {
//					game.pacmans.remove(i);
//				}

			}
			for(int i=0;i<game.fruits.size();i++) {
				fruit temp2 =there.next();
				Point3D r=new Point3D(temp2.location.getx(),temp2.location.gety(),0);
				double g1,s,x=object.gps2pixels(r)[0],y=object.gps2pixels(r)[1];
				x=(double)x/width;
				y=(double)y/height;
				g1=(double)x*getWidth();
				s=(double)y*getHeight();
				if(x>0.3) {g1=g1+10;}
				if(x>0.5) {g1=g1+15;}
				if(x>0.6) {g1=g1+7;}
				if(x>0.75) {g1=g1+7;}
				g2.drawImage(apple,(int)g1,(int)s,15,15,null);
			}
			
		}
	}






	//clicked==press and release
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		x=event.getX();
		y=event.getY();
		System.out.println("("+ event.getX() + "," + event.getY() +")");

		repaint();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	//press
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	public static void main(String[] args) {
		myFrame a= new myFrame();
		a.setSize(a.width,a.height);

	}



}

