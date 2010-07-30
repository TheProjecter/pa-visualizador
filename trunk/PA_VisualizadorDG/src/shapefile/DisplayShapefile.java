package shapefile;

import javax.swing.JInternalFrame;
import org.geotools.data.shapefile.ShapefileDataStore;
import java.io.*;
import java.net.URL;
/**
 *
 * @author Carlos
 */



public class DisplayShapefile extends JInternalFrame
{
       ShapefileComponent sc;

	public DisplayShapefile(URL s) throws IOException
	{
		super("Shapefile", true, true, true, true);
		ShapefileDataStore data = new ShapefileDataStore(s);
		sc = new ShapefileComponent(data);
		this.getContentPane().add(sc);
		pack();
	}

        public void increaseZoom(){
            double zoom = sc.getZoom() + 0.25;
            sc.setZoom(zoom);
            sc.repaint();
        }

        public void decreaseZoom(){
            double zoom = sc.getZoom() - 0.25;
            sc.setZoom(zoom);
            sc.repaint();
        }

        public double getShapeScale(){
            return sc.getEscala();
        }

        public void printShape(){
            PrintUtilities.printComponent(sc);
        }



 }






