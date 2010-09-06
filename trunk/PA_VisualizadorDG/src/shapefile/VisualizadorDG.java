
package shapefile;

import javax.swing.JFrame;

/**
 *
 * @author Carlos
 */
public class VisualizadorDG {

    public static void main(String[] args) {

		DesktopFrame desktop = new DesktopFrame();//cria Janela principal
		desktop.setDefaultCloseOperation(DesktopFrame.EXIT_ON_CLOSE); //Sai do programa ao fechar a janela principal
		desktop.setLocation(0, 0);//defini localização da janela
                //desktop.setExtendedState(JFrame.MAXIMIZED_BOTH);
                desktop.setLocationRelativeTo(null);    // centraliza o formulário na área visível
                desktop.setLocationByPlatform(true);    /* regula formulário com o padrão do sistema. Ex: não printa
                                                           a tela em cima da barra de tarefas do windows & linux. */
                desktop.setVisible(true);               // Mostra a janela principal.
	}

}
