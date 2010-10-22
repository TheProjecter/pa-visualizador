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
import java.awt.event.ItemListener;
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
import java.awt.event.*;
import javax.swing.*;
import com.adobe.acrobat.*;
import java.awt.*;
import java.io.*;

public class DesktopFrame extends shape.JMapFrame {

    private  Color LINE_COLOUR = Color.BLACK;
    private  Color FILL_COLOUR = Color.WHITE;
    private  Color SELECTED_COLOUR = Color.LIGHT_GRAY;
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
    PrintUtilities print;
    static private String URLDBF;
    JTable tabelaDBF = new JTable();

     public class Selecao_cor extends JFrame implements ActionListener{
     private JButton azul_botao;
     private JButton azul_claro_botao;
     private JButton verde_botao;
     private JButton preto_botao;
     private JButton cinza_botao;
     private JButton cinza_claro_botao;
     private JButton cinza_escuro_botao;
     private JButton rosa_botao;
     private JButton vermelho_botao;
     private JButton branco_botao;
     private JButton magenta_botao;
     private JButton amarelo_botao;
     private JButton laranja_botao;
     private int a2=0;
     private JRadioButton linha_mapa;
     private JRadioButton preenchimento_selecao;
     public JRadioButton preenchimento_mapa;
     private JPanel painel, painel2;
     private ButtonGroup grupo;

     public Selecao_cor(String string) {
                super("Seletor de cores");
                addWindowListener(new WindowAdapter()
                {
                   // public void windowClosing(WindowEvent e)
                    //{System.exit(0);}


                });
                Container contentPane = getContentPane();
               // contentPane.setLayout(new FlowLayout());
                contentPane.setLayout(new BorderLayout());
                painel = new JPanel();
                painel2 = new JPanel();
                preenchimento_mapa = new JRadioButton("Preenchimento", false);
                painel.add(preenchimento_mapa);

                linha_mapa = new JRadioButton("Linha", false);
                painel.add(linha_mapa);

                preenchimento_selecao = new JRadioButton("Preenchimento da Seleção", false);
                painel.add(preenchimento_selecao);

                preto_botao = new JButton();
                preto_botao.setBackground(Color.BLACK);
                preto_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(preto_botao);

                azul_botao = new JButton();
                azul_botao.setBackground(Color.BLUE);
                azul_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(azul_botao);

                azul_claro_botao = new JButton();
                azul_claro_botao.setBackground(Color.CYAN);
                azul_claro_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(azul_claro_botao);

                verde_botao = new JButton();
                verde_botao.setBackground(Color.GREEN);
                verde_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(verde_botao);

                cinza_botao = new JButton();
                cinza_botao.setBackground(Color.GRAY);
                cinza_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(cinza_botao);

                cinza_claro_botao = new JButton();
                cinza_claro_botao.setBackground(Color.LIGHT_GRAY);
                cinza_claro_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(cinza_claro_botao);

                cinza_escuro_botao = new JButton();
                cinza_escuro_botao.setBackground(Color.DARK_GRAY);
                cinza_escuro_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(cinza_escuro_botao);

                rosa_botao = new JButton();
                rosa_botao.setBackground(Color.PINK);
                rosa_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(rosa_botao);

                vermelho_botao = new JButton();
                vermelho_botao.setBackground(Color.RED);
                vermelho_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(vermelho_botao);

                branco_botao = new JButton();
                branco_botao.setBackground(Color.WHITE);
                branco_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(branco_botao);

                magenta_botao = new JButton();
                magenta_botao.setBackground(Color.MAGENTA);
                magenta_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(magenta_botao);

                amarelo_botao = new JButton();
                amarelo_botao.setBackground(Color.YELLOW);
                amarelo_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(amarelo_botao);

                laranja_botao = new JButton();
                laranja_botao.setBackground(Color.ORANGE);
                laranja_botao.setPreferredSize(new Dimension(30, 30));
                painel2.add(laranja_botao);

                contentPane.add(painel, "Center");
                contentPane.add(painel2, "South");
                grupo = new ButtonGroup();
                grupo.add(preenchimento_mapa);
                grupo.add(linha_mapa);
                grupo.add(preenchimento_selecao);

                Gerenciador gerente = new Gerenciador();
                preenchimento_mapa.addItemListener(gerente);
                linha_mapa.addItemListener(gerente);
                preenchimento_selecao.addItemListener(gerente);

                 preto_botao.addActionListener(this);
                 azul_botao.addActionListener(this);
                 azul_claro_botao.addActionListener(this);
                 verde_botao.addActionListener(this);

                 cinza_botao.addActionListener(this);
                 cinza_claro_botao.addActionListener(this);
                 cinza_escuro_botao.addActionListener(this);
                 rosa_botao.addActionListener(this);
                 vermelho_botao.addActionListener(this);
                 branco_botao.addActionListener(this);
                 magenta_botao.addActionListener(this);
                 amarelo_botao.addActionListener(this);
                 laranja_botao.addActionListener(this);

                setSize(500, 100);
                setVisible(true);

    }
     //////////////////////////////////////////
     public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();
            if(source == preto_botao)
            {
                if(a2==1){FILL_COLOUR = Color.BLACK;}
                else if(a2 == 3){SELECTED_COLOUR = Color.BLACK;}
                else if(a2 == 2){LINE_COLOUR = Color.BLACK;}
            }
            else if(source == azul_botao)
            {
                if(a2==1){FILL_COLOUR = Color.BLUE;}
                else if(a2 == 3){SELECTED_COLOUR = Color.BLUE;}
                else if(a2 == 2){LINE_COLOUR = Color.BLUE;}
            }
            else if(source == verde_botao)
            {
                if(a2==1){FILL_COLOUR = Color.GREEN;}
                else if(a2 == 3){SELECTED_COLOUR = Color.GREEN;}
                else if(a2 == 2){LINE_COLOUR = Color.GREEN;}
            }
             else if(source == azul_claro_botao)
            {
                if(a2==1){FILL_COLOUR = Color.CYAN;}
                else if(a2 == 3){SELECTED_COLOUR = Color.CYAN;}
                else if(a2 == 2){LINE_COLOUR = Color.CYAN;}
            }
          else if(source == cinza_claro_botao)
            {
                if(a2==1){FILL_COLOUR = Color.LIGHT_GRAY;}
                else if(a2 == 3){SELECTED_COLOUR = Color.LIGHT_GRAY;}
                else if(a2 == 2){LINE_COLOUR = Color.LIGHT_GRAY;}
            }
             else if(source == cinza_botao)
            {
                if(a2==1){FILL_COLOUR = Color.GRAY;}
                else if(a2 == 3){SELECTED_COLOUR = Color.GRAY;}
                else if(a2 == 2){LINE_COLOUR = Color.GRAY;}
            }
             else if(source == cinza_escuro_botao)
            {
                if(a2==1){FILL_COLOUR = Color.DARK_GRAY;}
                else if(a2 == 3){SELECTED_COLOUR = Color.DARK_GRAY;}
                else if(a2 == 2){LINE_COLOUR = Color.DARK_GRAY;}
            }
             else if(source == rosa_botao)
            {
                if(a2==1){FILL_COLOUR = Color.PINK;}
                else if(a2 == 3){SELECTED_COLOUR = Color.PINK;}
                else if(a2 == 2){LINE_COLOUR = Color.PINK;}
            }
             else if(source == vermelho_botao)
            {
                if(a2==1){FILL_COLOUR = Color.RED;}
                else if(a2 == 3){SELECTED_COLOUR = Color.RED;}
                else if(a2 == 2){LINE_COLOUR = Color.RED;}
            }
             else if(source == branco_botao)
            {
                if(a2==1){FILL_COLOUR = Color.WHITE;}
                else if(a2 == 3){SELECTED_COLOUR = Color.WHITE;}
                else if(a2 == 2){LINE_COLOUR = Color.WHITE;}
            }
             else if(source == magenta_botao)
            {
                if(a2==1){FILL_COLOUR = Color.MAGENTA;}
                else if(a2 == 3){SELECTED_COLOUR = Color.MAGENTA;}
                else if(a2 == 2){LINE_COLOUR = Color.MAGENTA;}
            }
             else if(source == amarelo_botao)
            {
                if(a2==1){FILL_COLOUR = Color.YELLOW;}
                else if(a2 == 3){SELECTED_COLOUR = Color.YELLOW;}
                else if(a2 == 2){LINE_COLOUR = Color.YELLOW;}
            }
             else if(source == laranja_botao)
            {
                if(a2==1){FILL_COLOUR = Color.ORANGE;}
                else if(a2 == 3){SELECTED_COLOUR = Color.ORANGE;}
                else if(a2 == 2){LINE_COLOUR = Color.ORANGE;}
            }

            try {
                        loadloadShapeFile2();
                        mapPane.reset();

                    } catch (Exception ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }


       }
     ///////////////////////////
     private class Gerenciador implements ItemListener{
         public void itemStateChanged(ItemEvent event){
             if(event.getSource() == preenchimento_mapa){
                  a2=1;
                  linha_mapa.isEnabled();
                 preenchimento_selecao.isEnabled();

                }
                else if(event.getSource() == linha_mapa)
                {
                    a2=2;
                    preenchimento_mapa.isEnabled();// = new JRadioButton("Preenchimento da Seleção", false);
                    preenchimento_selecao.isEnabled();
                }
                else if(event.getSource() == preenchimento_selecao)
                {
                     a2=3;
                     linha_mapa.isEnabled();
                    preenchimento_mapa.isEnabled();
                }

         }
     }
     //////
    }
    public DesktopFrame(){

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
        ////////////////////////////////////////////////
         JButton btn_cor = new JButton("");
        btn_cor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cor.png")));
        btn_cor.setToolTipText("Click para a seleção de cor.");


        toolBar.addSeparator();
        toolBar.add(btn_cor);

        btn_cor.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent a)  {
                try {

                    //System.out.println("entrou aqui");
                    Selecao_cor frame2 = new Selecao_cor("");
                    frame2.show();
                    //frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);


                } catch (Exception ex) {
                    Logger.getLogger(DesktopFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
             }


         });
//////////////////////////////////////////////////////////////
        btn_ahuda.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a)  {
                try {

                    System.out.println("entrou aqui");
                    JFrame frame = new JFrame("Help");

                    frame.setLayout(new BorderLayout());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Viewer viewer = new Viewer();
                    frame.add(viewer, BorderLayout.CENTER);
                    InputStream input = new FileInputStream(new File("C:\\trunk\\PA_VisualizadorDG\\src\\help\\help.pdf"));
                    viewer.setDocumentInputStream(input);
                    viewer.setProperty("Default_Page_Layout","SinglePage");
                    viewer.setProperty("Default_Zoom_Type","FitPage");
                    viewer.setProperty("Default_Magnification","100");

                    System.out.println("Num paginas: "+viewer.getPageCount());
                    System.out.println("Pagina atual: "+viewer.getCurrentPage());

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
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        /*
         * Action Listener para abrir o arquivo dbf
         * Quando se abre um arquivo ahape (*.shp) o mesmo pega o mesmo endereço utilizado
         * e já procura um arquivo de mesmo nome e de extensão (*.dbf).
         */
        btn_dbf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {

                //aqui a chamada
                try {
                    //enderecoDBF = "C:/Users/André/Desktop/Visualizador/PA_VisualizadorDG/BACIAS~1/agua.dbf";
                    int numeroLinhasDBF = DBFdata.numeroLinhas(DesktopFrame.URLDBF);
                    int numeroColunasDBF = DBFdata.numeroColunas(DesktopFrame.URLDBF);

                    tabelaDBF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ativa barra de rolagem horizontal
                    tabelaDBF.setModel(new ModeloTabela(DBFdata.cabecalhoTabela(DesktopFrame.URLDBF), numeroLinhasDBF, numeroColunasDBF));
                    if(numeroColunasDBF < 19) {
                        //define o tamanho das colunas em tabelas com numero pequeno de colunas
                        for(int i=0; i< numeroColunasDBF; i++) {
                        tabelaDBF.getColumnModel().getColumn(i).setPreferredWidth((painelDBF.getWidth()/numeroColunasDBF)-7);
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
                    scrollPane.setSize(painelDBF.getWidth()-2, 97);
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
                                tabelaDBF.setRowSelectionInterval(2, 3);
                            }
                        });
            }
        });

        btn_salvar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.setDialogTitle("Salvar como ...");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg","jpg");
                fileChooser.setSelectedFile(new File("shape.jpg"));
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                String diretorio;
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {

                    //File diretorio = fileChooser.getCurrentDirectory();//nome da pasta
                    diretorio = fileChooser.getSelectedFile().toString();
                    if(diretorio.endsWith(".jpg")){

                        saveImage(map, diretorio);
                        System.out.println(diretorio);
                    }else{

                        diretorio = diretorio+".jpg";
                        saveImage(map, fileChooser.getSelectedFile().toString()+".jpg");
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelDBF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mapPane, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mapPane, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelDBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                )
        ));

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


        System.out.println(file+" ::: imprimindo file");
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

                    System.out.println("   " + feature.getIdentifier());
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
        Rule selectedRule = createRule(SELECTED_COLOUR, SELECTED_COLOUR);
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

      static public void loadloadShapeFile2() throws Exception {

        //file = JFileDataStoreChooser.showOpenFile("shp", null);

        //setURLDBF(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + ".dbf");

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
            int imageWidth = 600;
            imageBounds = new Rectangle(
                    0, 0, imageWidth, (int) Math.round(imageWidth * heightToWidth));
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
}
