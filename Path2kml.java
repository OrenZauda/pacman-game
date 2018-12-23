package Pacman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;

public class Path2kml {
	// this field contain the path we working on
	public path path;
	public int u;
	double longitude,latitude;
	String time;
	//simple constructor
	public BufferedImage pacman;
	public BufferedImage apple;


	// field to set kml location
	File kmlLoc; 
	public Path2kml(path x) throws IOException {
		path=x;	
		kmlLoc=new File("C:\\temp\\a.kml");
		apple=ImageIO.read(new File("C:\\temp\\apple.png"));
		pacman = ImageIO.read(new File("C:\\temp\\iconfinder_Cute Ball - Games_32132.png"));


	}
	//this function converting path to kml file
	public void tokml() throws IOException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName("csvToKml "+u).withOpen(true);		
		String line = "";
		String cvsSplitBy = ",";
		String csvFile;
		Iterator <Point3D> here = path.path.iterator();
		Iterator  there = path.timedelay.iterator();
		while (here.hasNext()) {
			Point3D temp= here.next();
			longitude=temp.getx();
			latitude=temp.gety();
			time= (String)there.next();	
		}
		Folder folder = doc.createAndAddFolder();
		folder.withName("CSV to KML").withOpen(true);
		createPlacemark(doc, folder,time,longitude,latitude);
		// print and save
		kml.marshal(kmlLoc);

	}
	private void createPlacemark(Document doc, Folder folder, String time2, double longtitude, double latitude) {
		//set the placemark icon
		Icon icon = new Icon()
				.withHref("http://maps.google.com/mapfiles/ms/icons/blue-dot.png");
		Style style = doc.createAndAddStyle();
		// set the stylename to use this style from the placemark
		style.withId("style_") 
		// set size and icon
		.createAndSetIconStyle().withScale(0.8).withIcon(icon); 
		// set color and size for the name
		style.createAndSetLabelStyle().withColor("5000FF14").withScale(1.0); 
		Placemark placemark = folder.createAndAddPlacemark();
		placemark.withName("dope")
		.withStyleUrl("#style_")
		.withDescription(
				"Time: <b>"+ time +"</b><br/>longtitude: <b>"+ longtitude +"</b><br/>latitude:"
						+ " <b>"+ latitude +"</b><br/>")
		// coordinates and distance (zoom level) of the viewer
		.createAndSetLookAt().withLongitude(longitude).withLatitude(latitude).withAltitude(0);

		//Add TimeStamp
		placemark.createAndSetTimeStamp().setWhen(time.replace(" ", "T")+"Z");

		placemark.createAndSetPoint().addToCoordinates(longitude, latitude); // set coordinates

	}
	public static void main(String[] args) throws IOException {
		path b= new path();
		b.add(new Point3D(35.202574,32.106046,600.0),"the time is *");
		Path2kml a= new Path2kml(b);
		a.tokml();
	}

}
