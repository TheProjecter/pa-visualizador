package shapefile;

/**
 *
 * @author Carlos
 */

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;



public class Desktop extends JFrame{

	/*A classe Desktop eh a Janela principal do programa,
	 * a qual tem uma barra de Menus, onde podemos escolher entre: 
	 * - Abrir um arquivo Shape File.
	 * - Sair do programa
	 */
	
	
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	
	public Desktop() {
		super("Visualizador de Dados Geoespaciais");
		
		desktopPane = new JDesktopPane()/*{
			private static final long serialVersionUID = 1L;
			Image im = (new ImageIcon("desktop.png")).getImage();   
      		public void paintComponent(Graphics g){           
        	 g.drawImage(im,0,0,this);            }   
                                    }*/;
	                                     
		add(desktopPane);
		
		JMenu fileMenu = new JMenu("Arquivo");
		fileMenu.setMnemonic('A');
		
		
		JMenuItem abrir = new JMenuItem("Abrir");
		abrir.setMnemonic('b');

              //  JMenuItem abrir = new JMenuItem("Zoom");
	//	abrir.setMnemonic('c');
		
		fileMenu.add(abrir);
		abrir.addActionListener(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent register) 
				{
					File shapeFile = getFile();
                                        URL s;
                                        try {
                                            s = shapeFile.toURL();
                                            DisplayShapefile shapeView = new DisplayShapefile(s);
                                            shapeView.setSize(640, 640);
					    desktopPane.add(shapeView);
                                            shapeView.setVisible(true);
                                        } catch (IOException ex) {
                                            //Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    

				}
			}
		);	
	
		
		JMenuItem sair = new JMenuItem("Sair");
		sair.setMnemonic('S');
		
		fileMenu.add(sair);
		sair.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent register){
						Object[] options = {"Sim",
	                    "Nao"};
						int n = JOptionPane.showOptionDialog(desktopPane,
						    "Deseja realmente sair?",
						    "Sair",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,     
						    options,  
						    options[0]); 
						if (n == 0)
							System.exit(0);
					}
				}
			);
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
		
	}
	
	//Metodo para selecionar o arquivo SHAPE
	private File getFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new ShapeFileFilter());
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.CANCEL_OPTION)
		{
			return null;
		}
		
		File fileName = fileChooser.getSelectedFile();
		
		if ( (fileName == null) || (fileName.getName().equals("")))
		{
			JOptionPane.showMessageDialog(this, "Nome Invalido", "ERRO", 0);
		}
	
		return fileName;
	}
	
	
}
		
		