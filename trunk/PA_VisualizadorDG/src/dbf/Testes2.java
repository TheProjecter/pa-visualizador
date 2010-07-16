/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbf;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

 public class Testes2 {

     public static void main(String args[])
     {
           String[][] dados = new String [][]{
                                 {"SP","Sao Paulo"},
                                 {"RJ","Rio de Janeiro"},
                                 {"RN","Rio Grande do Norte"},
                                 {"PR","Parana"}
                   };
                  String[] colunas = new String[2];//new String []{"Estado","Cidade"};
                  colunas[0] = "andre";
                  colunas[1] = "jaizo";

                   // Ao inves de passar direto, colocamos os dados em um modelo
                   DefaultTableModel modelo = new DefaultTableModel(dados, colunas);


                   // e passamos o modelo para criar a jtable
                   JTable jtable = new JTable( modelo );
                   jtable.setSize(100, 50);
                   JFrame a = new JFrame();
                   a.setSize(300,200);
                   a.add(jtable);
                   a.setVisible(true);


                   //JOptionPane.showMessageDialog(null,jtable);
     }
   }
