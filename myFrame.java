package Pacman;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class myFrame extends Canvas implements Runnable {
	private boolean isRunning=false;
	private static final int width= 600;
	private static final int height= 400;
	private static final String title= "Pac-man";
	private Thread thread;

	public myFrame() {
		Dimension dimension= new Dimension();
		dimension.setSize(width, height);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);

	}
	private void start() {
    
	}
	@Override
	public void run() {
		while (isRunning) {

		}
	}
	
	public static void main(String[] args) {
		myFrame a= new myFrame();
		JFrame frame= new JFrame();
		frame.setTitle(title);
		frame.add(a);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		a.start();
	}



}
