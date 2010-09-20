/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shape;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.geotools.data.DataStore;
import org.geotools.data.DefaultRepository;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.DefaultMapLayer;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class demo extends JMapFrame {

    private MapContext context;
    private String title;
    private DefaultRepository repository = new DefaultRepository();

    static StyleFactory styleFactory = CommonFactoryFinder
            .getStyleFactory(null);
    static FilterFactory filterFactory = CommonFactoryFinder
            .getFilterFactory(null);

    public void loadShapefilesfromXMLFile(String xmlfileabsolutepath) {
        List elements;
        try {
            SAXBuilder saxBuilder = new SAXBuilder();

            //
saxBuilder.setFeature("http://apache.org/xml/features/validation/schema",true);
            // true);
            Document doc = saxBuilder.build(xmlfileabsolutepath);
            Element project = doc.getRootElement();
            elements = project.getChildren();
            for (int i = 0; i < elements.size(); ++i) {
                Element currentElement = (Element) elements.get(i);
                if (currentElement.getName().equals("ShapeLayer")) {
                    //
//System.out.println(cwd.getAbsolutePath()+currentElement.getChild("Path").getText()+currentElement.getChild("Name").getText());
                    File file = new File("E:/GISTools/Martins_Gis/src/"
                            + currentElement.getChild("Path").getText()
                            + currentElement.getChild("Name").getText());

                    addShapefile(file.toURL(), true);

                }
            }

            this.repaint();

        } catch (Exception e) {
            // System.out.println(e);
            e.printStackTrace();

        }
    }

    public void loadShapefile() throws IOException {

        File file = JFileDataStoreChooser.showOpenFile("shp", this);
        if (file != null) {
            addShapefile(file.toURL(), true);

        }
    }

    public demo(String frameTitle) {
        this.title = frameTitle;
        enableLayerTable(true);
        enableStatusBar(true);
        enableToolBar(true);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        JMenuItem item = new JMenuItem("Open...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    loadShapefile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menu.add(item);

        item = new JMenuItem("Get scale");
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JMapPane mapPane = getMapPane();
                if (mapPane != null) {
                    AffineTransform tr =
mapPane.getScreenToWorldTransform();
                    if (tr != null) {
                        System.out.println("x scale: " + tr.getScaleX());
                        System.out.println("Y scale: " + tr.getScaleY());
                        System.out.println("pane area: "
                                + mapPane.getVisibleRect());
                    }
                }
            }
        });
        menu.add(item);

        item = new JMenuItem("Load...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

loadShapefilesfromXMLFile(demo.class.getResource("/Ressources")
                        .getPath()
                        + "/airport.xml");
            }
        });
        menu.add(item);

        menuBar.add(menu);

        initComponents();
    }

    public static void main(String[] args) throws IOException {

        // Creating a new JMapFrame
        final demo viewer = new demo("Shapefile viewer");
        viewer.setSize(800, 800);
        viewer.setVisible(true);

    }

    public boolean addShapefile(URL shapefileURL, boolean defaultStyle)
            throws IOException {
        if (shapefileURL == null) {
            throw new IllegalArgumentException("shapefileURL must not me null");
        }
        ShapefileDataStore dstore = null;

        DataStore found = repository.dataStore(shapefileURL.toString());
        if (found != null && found instanceof ShapefileDataStore) {
            dstore = (ShapefileDataStore) found;
        } else {
            try {
                dstore = new ShapefileDataStore(shapefileURL);
            } catch (MalformedURLException urlEx) {
                throw new RuntimeException(urlEx);
            }
            repository.register(shapefileURL.toString(), dstore);
        }

        /*
         * We assume from this point that the shapefile exists and is
accessible
         */
        String typeName = dstore.getTypeNames()[0];

        // Create a basic Style to render the features
        Style style = null;

        SimpleFeatureType schema = dstore.getSchema();
        Class geomType =
schema.getGeometryDescriptor().getType().getBinding();

        if (Polygon.class.isAssignableFrom(geomType)
                || MultiPolygon.class.isAssignableFrom(geomType)) {
            style = createPolygonStyle();

        } else if (LineString.class.isAssignableFrom(geomType)
                || MultiLineString.class.isAssignableFrom(geomType)) {
            style = createLineStyle();

        } else {
            style = createPointStyle();
        }

        MapLayer layer = new
DefaultMapLayer(dstore.getFeatureSource(typeName),
                style);
        addLayer(layer);

        return true;
    }

    /**
     * Add a map layer to those displayed. If no
     * {...@linkplain org.geotools.map.MapContext} has been set explicitly,
a new
     * instance of {...@linkplain org.geotools.map.DefaultMapContext} will be
     * created.
     */
    public void addLayer(MapLayer layer) {
        if (context == null) {
            CoordinateReferenceSystem crs = layer.getBounds()
                    .getCoordinateReferenceSystem();
            if (crs == null) {
                crs = DefaultGeographicCRS.WGS84;
            }
            context = new DefaultMapContext(crs);
            context.setTitle(title);
            setMapContext(context);
            setRenderer(new StreamingRenderer());
        }

        context.addLayer(layer);
    }

    // Extension with Styles

    /**
     * Create a Style to draw polygon features with a thin blue outline
and a
     * cyan fill
     */
    private static Style createPolygonStyle() {

        // create a partially opaque outline stroke
        Stroke stroke = styleFactory.createStroke(filterFactory
                .literal(Color.BLUE), filterFactory.literal(1),
filterFactory
                .literal(0.5));

        // create a partial opaque fill
        Fill fill =
styleFactory.createFill(filterFactory.literal(Color.CYAN),
                filterFactory.literal(0.5));

        /*
         * Setting the geometryPropertyName arg to null signals that we
want to
         * draw the default geomettry of features
         */
        PolygonSymbolizer sym = styleFactory.createPolygonSymbolizer(stroke,
                fill, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory
                .createFeatureTypeStyle(new Rule[] { rule });
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

    /**
     * Create a Style to draw line features as thin blue lines
     */
    private static Style createLineStyle() {
        Stroke stroke = styleFactory.createStroke(filterFactory
                .literal(Color.BLUE), filterFactory.literal(1));

        /*
         * Setting the geometryPropertyName arg to null signals that we
want to
         * draw the default geomettry of features
         */
        LineSymbolizer sym = styleFactory.createLineSymbolizer(stroke,
null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory
                .createFeatureTypeStyle(new Rule[] { rule });
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

    /**
     * Create a Style to draw point features as circles with blue
outlines and
     * cyan fill
     */
    private static Style createPointStyle() {
        Graphic gr = styleFactory.createDefaultGraphic();

        Mark mark = styleFactory.getCircleMark();

        mark.setStroke(styleFactory.createStroke(filterFactory
                .literal(Color.BLUE), filterFactory.literal(1)));

        mark
                .setFill(styleFactory.createFill(filterFactory
                        .literal(Color.CYAN)));

        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);
        gr.setSize(filterFactory.literal(5));

        /*
         * Setting the geometryPropertyName arg to null signals that we
want to
         * draw the default geomettry of features
         */
        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory
                .createFeatureTypeStyle(new Rule[] { rule });
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

}

