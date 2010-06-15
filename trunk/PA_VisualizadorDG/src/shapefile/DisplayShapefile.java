package shapefile;

/**
 *
 * @author Carlos
 */

import java.io.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import org.geotools.data.shapefile.ShapefileDataStore;

public class DisplayShapefile extends JInternalFrame
{
	public DisplayShapefile(URL s) throws IOException
	{
		super("Shapefile", true, true, true, true);
		ShapefileDataStore data = new ShapefileDataStore(s);
		ShapefileComponent sc = new ShapefileComponent(data);
		this.getContentPane().add(sc);
		pack();
	}
	
}

