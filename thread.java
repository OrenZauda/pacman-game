package Pacman;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Coords.MyCoords;
import Geom.Point3D;
import Robot.Play;

public class thread extends Thread {
	myFrame myFrame;
	Play play;
	private double azimuth;
	public thread(myFrame myFrame,Play play) {
		this.myFrame=myFrame;
		this.play=play;
	}
	@Override
	/**
	 * this method implemnts the whole program.
	 * the play program is here
	 */
	public void run() {

		ArrayList<String> board_data=new ArrayList<String>();
		double a=0;
		//printing myplayer location for inner testing
		play.setIDs(205603426,302864731);
		//set initial location
		//play.setInitLocation(myFrame.game.myplayer.location.getx(), myFrame.game.myplayer.location.gety());
		//object to use azimuth method
		MyCoords o=new MyCoords();
		//start the game
		play.start();
		while(play.isRuning()) {

			if(myFrame.automat) {
				// object to use cal method which finding closest fruit
				ShortestPathAlgo obj=new ShortestPathAlgo(myFrame.game) ;
				double[] set= obj.cal();
				fruit currentchasedf = new fruit();
				pacman currentchasedp=new pacman();
				//case we chasing fruit
				if(set[2]==0) {
					// finding the closest fruit by id
					for (int i = 0; i < myFrame.game.fruits.size(); i++) {
						if(set[1]==myFrame.game.fruits.get(i).id) {
							currentchasedf=myFrame.game.fruits.get(i);
						}
					}

					// store the angle between myplayer and closest fruit
					a=o.azimuth_elevation_dist(myFrame.game.myplayer.location,
							currentchasedf.location)[0];
				}

				//case we chasing pacmans
				else {
					// finding the closest pacman by id
					for (int i = 0; i < myFrame.game.pacmans.size(); i++) {
						if(set[1]==myFrame.game.pacmans.get(i).id) {
							currentchasedp=myFrame.game.pacmans.get(i);

						}
						// store the angle between myplayer and closest pacman
						a=o.azimuth_elevation_dist(myFrame.game.myplayer.location,
								currentchasedp.location)[0];
						
					}
				}
			}
			else{
				a=azimuth;}
			// tell the server to go in specific way 
			play.rotate(a);
			// save new data
			board_data = play.getBoard();

			//			System.out.println(play.getBoard());
			// update the game to new locations
			myFrame.game=myFrame.object.togame(board_data);

			myFrame.repaint();
			// printing results for inner testing 
			//			System.out.println("********************");
			String info = play.getStatistics();
			String [] rr=info.split(",");

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myFrame.repaint();
			//location=myFrame.game.myplayer.location;
		}


	}

	/**
	 * this method collect the x,y of mouse click
	 * @param azimuth
	 */
	public void setazimuth(double azimuth) {
		this.azimuth = azimuth;
	}



}
//	public static void main(String[] args) {
//		thread t= new thread("")
//	}

