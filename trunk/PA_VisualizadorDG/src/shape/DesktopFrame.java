/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import com.svcon.jdbf.DBFReader;
import com.svcon.jdbf.JDBFException;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import dbf.DBFdata;
import dbf.ModeloTabela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.tool.CursorTool;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;

import javax.swing.*;

import com.adobe.acrobat.*;
import java.awt.*;
import java.io.*;

public class DesktopFrame extends shape.JMapFrame {

    private Color LINE_COLOUR = Color.BLACK;
    private Color FILL_COLOUR = new Color(160, 255, 255);
    private Color SELECTEDFILL_COLOUR = Color.YELLOW;
    private Color SELECTEDLINE_COLOUR = Color.BLACK;
    private static final float OPACITY = 1.0f;
    private static final float LINE_WIDTH = 1.0f;
    private static final float POINT_SIZE = 10.0f;
    private FeatureSource<SimpleFeatureType, SimpleFeature> featureSource;
    private FeatureSource<SimpleFeatureType, SimpleFeature> featureSource2;
    private String geometryAttributeName;
    private GeomType geometryType;
    private StyleFactory sf = CommonFactoryFinder.getStyleFactory(null);
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
    static final DesktopFrame viewer = new DesktopFrame();
    static private MapContext map;
    static Style style;
    static Style style2;
    static File file;
    private PrintUtilities print;
    static private String URLDBF;
    private JTable tabelaDBF = new JTable();
    private int regiaoSelecTabela;

    public DesktopFrame() {

        //this.enableLayerTable(true);

        setTitle("Visualizador de dados geoespaciais");

        try {
            Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("../imagens/logo.png"));
            this.setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        enableStatusBar(true);

        enableToolBar(true);
        enableInputMethods(true);
        //enableLayerTable(true);

        JToolBar toolBar = this.getToolBar();
        JButton btn_selecionar = new JButton("");
        toolBar.setOrientation(JToolBar.VERTICAL);
        btn_selecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/selecionar.png")));
        btn_selecionar.setToolTipText("Click this button to select region.");

        toolBar.addSeparator();
        toolBar.add(btn_selecionar);

        JButton btn_dbf = new JButton("");
        btn_dbf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tabela.png")));
        btn_dbf.setToolTipText("Click this button to open table in the DBF file.");

        toolBar.addSeparator();
        toolBar.add(btn_dbf);

        JButton btn_salvar = new JButton("");
        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png")));
        btn_salvar.setToolTipText("Click this button to save in jpg.");

        toolBar.addSeparator();
        toolBar.add(btn_salvar);

        JButton btn_imprimir = new JButton("");
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/imprimir.png")));
        btn_imprimir.setToolTipText("Click this button to print.");

        toolBar.addSeparator();
        toolBar.add(btn_imprimir);

        JButton btn_ahuda = new JButton("");
        btn_ahuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ajuda.png")));
        btn_ahuda.setToolTipText("Click this button to open help.");

        toolBar.addSeparator();
        toolBar.add(btn_ahuda);

        btn_ahuda.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                try {

                    System.out.println("entrou aqui");
                    JFrame frame = new JFrame("Help");

                    frame.setLayout(new BorderLayout());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Viewer viewer = new Viewer();
                    frame.add(viewer, BorderLayout.CENTER);
                    InputStream input = new FileInputStream(new File("C:\\trunk\\PA_VisualizadorDG\\src\\help\\help.pdf"));
                    viewer.setDocumentInputStream(input);
                    viewer.setProperty("Default_Page_Layout", "SinglePage");
                    viewer.setProperty("Default_Zoom_Type", "FitPage");
                    viewer.setProperty("Default_Magnification", "100");

                    System.out.println("Num paginas: " + viewer.getPageCount());
                    System.out.println("Pagina atual: " + viewer.getCurrentPage());

                    viewer.zoomTo(1.0);
                    viewer.activate();

                    frame.setSize(400, 500);
                    frame.setLocationRelativeTo(null);
                    //frame.pack();
                    frame.show(true);

                } catch (Exception ex) {
                    System.out.println("deu erro aqui");
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_imprimir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {

                print.printComponent((Component) map);
            }
        });

        /*
         * Criando o JPanel para a alocação da tabela DBF.
         */
        final JPanel painelDBF = new JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(painelDBF);
        painelDBF.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 315, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 96, Short.MAX_VALUE));

        /*
         * Action Listener para abrir o arquivo dbf
         * Quando se abre um arquivo ahape (*.shp) o mesmo pega o mesmo endereço utilizado
         * e já procura um arquivo de mesmo nome e de extensão (*.dbf).
         */
        btn_dbf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {

                //aqui a chamada
                painelDBF.removeAll();
                try {
                    //enderecoDBF = "C:/Users/André/Desktop/Visualizador/PA_VisualizadorDG/BACIAS~1/agua.dbf";
                    int numeroLinhasDBF = DBFdata.numeroLinhas(DesktopFrame.URLDBF);
                    int numeroColunasDBF = DBFdata.numeroColunas(DesktopFrame.URLDBF);

                    tabelaDBF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ativa barra de rolagem horizontal
                    tabelaDBF.setModel(new ModeloTabela(DBFdata.cabecalhoTabela(DesktopFrame.URLDBF), numeroLinhasDBF, numeroColunasDBF));
                    if (numeroColunasDBF < 19) {
                        //define o tamanho das colunas em tabelas com numero pequeno de colunas
                        for (int i = 0; i < numeroColunasDBF; i++) {
                            tabelaDBF.getColumnModel().getColumn(i).setPreferredWidth((painelDBF.getWidth() / numeroColunasDBF) - 7);
                        }
                    }

                    InputStream stream = new BufferedInputStream(new FileInputStream(DesktopFrame.URLDBF));
                    DBFReader dbf = new DBFReader(stream);

                    for (int i = 1; i < numeroLinhasDBF; i++) {
                        Object aobj[] = dbf.nextRecord();
                        for (int j = 0; j < aobj.length; j++) {
                            if (aobj[j] == null) {
                                aobj[j] = "";
                            }
                            //System.out.println("TESTE DO OBJECT na linha: " + i + " com valor: " + aobj[j]);
                            tabelaDBF.setValueAt(aobj[j].toString(), (i - 1), j);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDBFException ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                JScrollPane scrollPane = new JScrollPane(tabelaDBF);
                scrollPane.setLocation(0, 0);
                scrollPane.setSize(painelDBF.getWidth() - 2, 97);
                //Dimension dimensao = new Dimension(400, 98);       //Usado para definir o tamanho visível (sem uso do scroll) da tabela
                //scrollPane.setPreferredSize(dimensao);
                painelDBF.add(scrollPane);
                painelDBF.setVisible(true);

            }
        });

        //acao no botao selecionar
        btn_selecionar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                getMapPane().setCursorTool(
                        new CursorTool() {

                            @Override
                            public void onMouseClicked(MapMouseEvent ev) {

//                                Toolkit kit = Toolkit.getDefaultToolkit();
//                                Image image = kit.createImage(getClass().getResource("/imagens/selecionar.png"));
//                                Point point = new Point(16, 16); // Coordenada do clique em relação à imagem
//                                String nameCursor = "Image Cursor";
//                                Cursor cursor = kit.createCustomCursor(image, point, nameCursor);
                                selectFeatures(ev);


                                /*
                                 * Aqui vai o event para seleção de região dentro do determinado mapa
                                 * e devido selecionamento de linha dentro da tabela DBF.
                                 */
                                tabelaDBF.setRowSelectionInterval(regiaoSelecTabela - 1, regiaoSelecTabela - 1);
                                tabelaDBF.setRequestFocusEnabled(rootPaneCheckingEnabled);
                            }
                        });
            }
        });

        btn_salvar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.setDialogTitle("Salvar como ...");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "jpg");
                fileChooser.setSelectedFile(new File("shape.jpg"));
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                String diretorio;
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {

                    //File diretorio = fileChooser.getCurrentDirectory();//nome da pasta
                    diretorio = fileChooser.getSelectedFile().toString();
                    if (diretorio.endsWith(".jpg")) {

                        saveImage(map, diretorio);
                        System.out.println(diretorio);
                    } else {

                        diretorio = diretorio + ".jpg";
                        saveImage(map, fileChooser.getSelectedFile().toString() + ".jpg");
                        System.out.println(diretorio);
                    }

                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);
        this.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Arquivo");
        JMenuItem item = new JMenuItem("Abrir");
        JMenuItem item2 = new JMenuItem("Sair");


        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    loadloadShapeFile();

                    /*
                     * O "mapPane.reset();" abaixo foi adicionado para eliminar a demora na pintura
                     * do shape na tela, que anteriormente a ele estava acontecendo.
                     */
                    mapPane.reset();

                } catch (Exception ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        item2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    viewer.dispose();
                } catch (Throwable ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        menu.add(item);
        menu.add(item2);
        menuBar.add(menu);

        initComponents();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                addGroup(layout.createSequentialGroup().
                addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE).
                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE).
                addComponent(painelDBF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).
                addComponent(mapPane, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                addGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().
                addComponent(mapPane, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE).
                addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE).
                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                addComponent(painelDBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).
                addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                )));

    }

    private enum GeomType {

        POINT, LINE, POLYGON
    };

    public static void main(String[] args) throws Exception {


        //obtendo dimensos da tela


        viewer.setSize(800, 500);
        viewer.setLocationRelativeTo(null);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setVisible(true);

    }

    public void displayShapefile(File file) throws Exception {


        System.out.println(file + " ::: imprimindo file");
        //File testeF2 = new File("C:\\SHAPES\\BRASIL\\PORTO.shp");//**********
        //System.out.println(testeF2+" ::: imprimindo file 2");

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        // FileDataStore store2 = FileDataStoreFinder.getDataStore(testeF2);

        // featureSource2 = store2.getFeatureSource();
        //style2 = createDefaultStyle();
        //FileDataStore store2 = FileDataStoreFinder.getDataStore(testeF2);//**********
        featureSource = store.getFeatureSource();
        //featureSource2 = store2.getFeatureSource();//**********
        setGeometry();
        map = new DefaultMapContext();
        style = createDefaultStyle();
        //style2 = createDefaultStyle();
        map.addLayer(featureSource, style);
        //map.addLayer(featureSource2, style2);
        //map.addLayer(featureSource2,style2);
        DesktopFrame.viewer.setMapContext(map);

    }

    void selectFeatures(MapMouseEvent ev) {

        //System.out.println("Mouse click at: " + ev.getMapPosition());

        Point screenPos = ev.getPoint();
        Rectangle screenRect = new Rectangle(screenPos.x - 2, screenPos.y - 2, 5, 5);


        AffineTransform screenToWorld = DesktopFrame.viewer.getMapPane().getScreenToWorldTransform();
        Rectangle2D worldRect = screenToWorld.createTransformedShape(screenRect).getBounds2D();
        ReferencedEnvelope bbox = new ReferencedEnvelope(
                worldRect,
                DesktopFrame.viewer.getMapContext().getCoordinateReferenceSystem());


        Filter filter = ff.intersects(ff.property(geometryAttributeName), ff.literal(bbox));

        try {
            FeatureCollection<SimpleFeatureType, SimpleFeature> selectedFeatures =
                    featureSource.getFeatures(filter);

            FeatureIterator<SimpleFeature> iter = selectedFeatures.features();
            Set<FeatureId> IDs = new HashSet<FeatureId>();
            try {
                while (iter.hasNext()) {
                    SimpleFeature feature = iter.next();
                    IDs.add(feature.getIdentifier());

                    /*
                     * Trecho de código responsável pela leitura da região selecionada
                     * O mesmo captura o numero da regiao selecionada com base no
                     *      posicionamento do mesmo nas tabelas DBF.
                     * E logo após o armazena dentro da variável:
                     *
                     * regiaoSelecTabela
                     */
                    int k, tamanhoVariavel = feature.getIdentifier().toString().length();
                    for (k = 0; k < tamanhoVariavel; k++) {
                        if (isNumeric(feature.getIdentifier().toString().charAt(tamanhoVariavel-1))) {
                            tamanhoVariavel--;
                        }
                        else
                            break;
                    }
                    regiaoSelecTabela = Integer.parseInt(feature.getIdentifier().toString().substring(feature.getIdentifier().toString().length() - k, feature.getIdentifier().toString().length()));
                    /*
                     * Fim da leitura da região selecionada
                     */
                }

            } finally {
                iter.close();
            }

            if (IDs.isEmpty()) {
                System.out.println("   no feature selected");
            }

            displaySelectedFeatures(IDs);

        } catch (Exception ex) {
            System.out.println("AQUIIIII");
            ex.printStackTrace();
            return;
        }
    }

    public void displaySelectedFeatures(Set<FeatureId> IDs) {
        Style style;

        if (IDs.isEmpty()) {
            style = createDefaultStyle();

        } else {
            style = createSelectedStyle(IDs);
        }

        DesktopFrame.viewer.getMapContext().getLayer(0).setStyle(style);
        DesktopFrame.viewer.getMapPane().repaint();
    }

    private Style createDefaultStyle() {
        Rule rule = createRule(LINE_COLOUR, FILL_COLOUR);

        FeatureTypeStyle fts = sf.createFeatureTypeStyle();
        fts.rules().add(rule);

        Style style = sf.createStyle();
        style.featureTypeStyles().add(fts);
        return style;
    }

    private Style createSelectedStyle(Set<FeatureId> IDs) {
        Rule selectedRule = createRule(SELECTEDLINE_COLOUR, SELECTEDFILL_COLOUR);
        selectedRule.setFilter(ff.id(IDs));

        Rule otherRule = createRule(LINE_COLOUR, FILL_COLOUR);
        otherRule.setElseFilter(true);

        FeatureTypeStyle fts = sf.createFeatureTypeStyle();
        fts.rules().add(selectedRule);
        fts.rules().add(otherRule);

        Style style = sf.createStyle();
        style.featureTypeStyles().add(fts);
        return style;
    }

    private Rule createRule(Color outlineColor, Color fillColor) {
        Symbolizer symbolizer = null;
        Fill fill = null;
        Stroke stroke = sf.createStroke(ff.literal(outlineColor), ff.literal(LINE_WIDTH));

        switch (geometryType) {
            case POLYGON:
                fill = sf.createFill(ff.literal(fillColor), ff.literal(OPACITY));
                symbolizer = sf.createPolygonSymbolizer(stroke, fill, geometryAttributeName);
                break;

            case LINE:
                symbolizer = sf.createLineSymbolizer(stroke, geometryAttributeName);
                break;

            case POINT:
                fill = sf.createFill(ff.literal(fillColor), ff.literal(OPACITY));

                Mark mark = sf.getCircleMark();
                mark.setFill(fill);
                mark.setStroke(stroke);

                Graphic graphic = sf.createDefaultGraphic();
                graphic.graphicalSymbols().clear();
                graphic.graphicalSymbols().add(mark);
                graphic.setSize(ff.literal(POINT_SIZE));

                symbolizer = sf.createPointSymbolizer(graphic, geometryAttributeName);
        }

        Rule rule = sf.createRule();
        rule.symbolizers().add(symbolizer);
        return rule;
    }

    private void setGeometry() {
        GeometryDescriptor geomDesc = featureSource.getSchema().getGeometryDescriptor();
        geometryAttributeName = geomDesc.getLocalName();

        Class<?> clazz = geomDesc.getType().getBinding();

        if (Polygon.class.isAssignableFrom(clazz)
                || MultiPolygon.class.isAssignableFrom(clazz)) {
            geometryType = GeomType.POLYGON;

        } else if (LineString.class.isAssignableFrom(clazz)
                || MultiLineString.class.isAssignableFrom(clazz)) {

            geometryType = GeomType.LINE;

        } else {
            geometryType = GeomType.POINT;
        }

    }

    static public void loadloadShapeFile() throws Exception {

        file = JFileDataStoreChooser.showOpenFile("shp", null);

        setURLDBF(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + ".dbf");

        if (file == null) {
            System.out.println("erro aquui");
            return;
        }
        DesktopFrame.viewer.displayShapefile(file);
    }

        public void saveImage(MapContext map, String file) {

        GTRenderer renderer = new StreamingRenderer();
        renderer.setContext(map);

        Rectangle imageBounds = null;
        try {
            ReferencedEnvelope mapBounds = map.getLayerBounds();
            double heightToWidth = mapBounds.getSpan(1) / mapBounds.getSpan(0);
            int imageWidth = 1000;
            imageBounds = new Rectangle(
                    0, 0, imageWidth, (int) Math.round(0.51*imageWidth * heightToWidth));
        } catch (Exception e) {
        }

        //Rectangle imageSize = new Rectangle(600,600);

        BufferedImage image = new BufferedImage(imageBounds.width, imageBounds.height, BufferedImage.TYPE_INT_RGB); //darker red fill
        Graphics2D gr = image.createGraphics();
        gr.setPaint(Color.WHITE);
        gr.fill(imageBounds);

        try {
            renderer.paint(gr, imageBounds, map.getAreaOfInterest());

            File fileToSave = new File(file);
            ImageIO.write(image, "jpeg", fileToSave);
        } catch (IOException e) {
        }

    }

    static public String getURLDBF() {
        return URLDBF;
    }

    static public void setURLDBF(String URLDBF) {

        DesktopFrame.URLDBF = URLDBF;
    }

    public boolean isNumeric(char s) {
        String teste = "" + s;
        try {
            Long.parseLong(teste);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
