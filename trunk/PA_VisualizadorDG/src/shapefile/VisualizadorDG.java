
package shapefile;

/**
 *
 * @author Carlos
 */
public class VisualizadorDG {

    public static void main(String[] args) {

		Desktop desktop = new Desktop();//cria Janela principal
		desktop.setDefaultCloseOperation(Desktop.EXIT_ON_CLOSE); //Sai do programa ao fechar a janela principal
		desktop.setLocation(50, 50);//defini localização da janela
		desktop.setSize(650, 650);// defini tamanho da janela
		desktop.setVisible(true);//mostra Janela Principal

	}

}
