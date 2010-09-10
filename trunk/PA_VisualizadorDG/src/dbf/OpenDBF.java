/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbf;

/**
 *
 * @author Jaizo // André
 */
import com.svcon.jdbf.JDBFException;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OpenDBF extends JFrame {

    private JTable table;
    private JScrollPane jsp;
    private JPanel tableDados;
    private JPanel main;
    private String[][] teste;
    public String endereco;
    

    public void openDBF(String url) throws FileNotFoundException, JDBFException {

        endereco = url;
        table = new JTable();
        jsp = new JScrollPane(table);
        table.setDefaultRenderer(Object.class, new CellRenderer());             //Centraliza os dados da tabela
        tableDados = new JPanel();
        main = new JPanel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ativa barra de rolagem horizontal

        table.setModel(new ModeloTabela(DBFdata.cabecalhoTabela(url), DBFdata.numeroLinhas(url), DBFdata.numeroColunas(url)));
        
        //-DEFININDO O TAMANHO DA TABELA---------------------------------------------------------------------------------
        
        Dimension a = new Dimension(400,98);       //Usado para definir o tamanho visível (sem uso do scroll) da tabela
        jsp.setPreferredSize(a);
        jsp.setHorizontalScrollBar(new JScrollBar(0));

        table.getColumnModel().getColumn(0).setPreferredWidth(100);     //define o tamanho da primeira coluna
        table.getColumnModel().getColumn(1).setPreferredWidth(225);     //define o tamanho da segunda coluna
        table.getColumnModel().getColumn(2).setPreferredWidth(75);      //define o tamanho da terceira coluna

        //---------------------------------------------------------------------------------------------------------------

        jsp.setViewportView(table);
        tableDados.add(jsp);
        
        main.add(tableDados);

        teste = DBFdata.matrizDados(url);

        for(int i=1; i<DBFdata.numeroLinhas(url); i++) {
            for (int j=0; j<DBFdata.numeroColunas(url); j++) {
                table.setValueAt(teste[i][j], (i-1), j);
            }
        }

        this.setContentPane(main);
        this.setSize(600, 700);
        this.setVisible(true);
    }

}