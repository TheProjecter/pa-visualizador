package shapefile;

/**
 *
 * @author Carlos
 */

import java.awt.*;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.swing.JComponent;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.map.*;
import org.geotools.renderer.shape.ShapefileRenderer;
import org.geotools.styling.*;
import com.vividsolutions.jts.geom.*;
/**
* Esta classe representa um componente gr�fico capaz de desenhar o conte�do
* (geometria) de uma inst�ncia de ShapefileDataStore.
*/
public class ShapefileComponent extends JComponent
{
	private ShapefileDataStore sds;
	private ShapefileRenderer renderer;
	private Envelope envelope;
	private final Coordinate center;
	private double zoom;
	
	
	public ShapefileComponent(ShapefileDataStore sds) throws IOException
	{
		this.sds = sds;
		// Criamos um Style
		StyleBuilder sb = new StyleBuilder();
		// Criamos s�mbolos para a linha
		LineSymbolizer lineSymb = (LineSymbolizer) sb.createLineSymbolizer(Color.RED, 1);
		Style style = sb.createStyle(lineSymb);
		// Criamos um MapContext com os dados e um estilo
		MapContext mc = new DefaultMapContext();
		mc.addLayer(sds.getFeatureSource(),style);
		// Agora com o contexto criamos um ShapefileRenderer
		renderer = new ShapefileRenderer(mc);
		envelope = mc.getLayerBounds();
		center = new Coordinate((envelope.getMinX()+envelope.getMaxX())/2,
		(envelope.getMinY()+envelope.getMaxY())/2);
		zoom = 0.5;
	}
	
//	public Dimension getMaximumSize() { return getPreferredSize(); }
//	public Dimension getMinimumSize() { return getPreferredSize(); }
//	public Dimension getPreferredSize() { return new Dimension(width,height); }
	
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,0,getPreferredSize().width,getPreferredSize().height);
		// Uma AffineTransform que mapeie dados no shapefile com a �rea para plotagem
		AffineTransform at = new AffineTransform();
		// Calculamos a escala
		double escala = (Math.min(getWidth()/envelope.getWidth(),
		getHeight()/envelope.getHeight())
		*zoom);
		// Fazemos a transla��o para o centro do componente.
		at.translate (getWidth()/2,getHeight()/2);
		// Mudamos a escala vertical para corrigir a orienta��o.
		at.scale(escala,-escala);
		// Fazemos a transla��o para o centro da geometria.
		at.translate(-center.x,-center.y);
		// Pintamos a geometria no componente.
		renderer.paint(g2d,getBounds(),at);
	}

}

