package dbf;


import com.svcon.jdbf.DBFReader;
import com.svcon.jdbf.JDBFException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.xy.Vector;




/**
 *
 * @author Jaizo
 */
public class OpenDBF {


    

    public static void main(String[] args) throws FileNotFoundException, JDBFException{


        //modelo da tabela a ser criada
        DefaultTableModel modelo = null;// = new DefaultTableModel();
        



        String url = "Feira_de_Santana/hd_linha_1904_V1.dbf";


        InputStream stream = new BufferedInputStream(new FileInputStream(url));
        DBFReader dbf = new DBFReader(stream);
        
        int numColuna = dbf.getFieldCount();

        
        String [] tabelaNMColuna = new String[numColuna];
        
        for(int j = 0; j < numColuna; j++){
            
            tabelaNMColuna[j] = dbf.getField(j).getName();
            
            System.out.println(tabelaNMColuna[j]);

            
        }
        //String [][] tabelaDeDados = null;
                //String [][] tabelaDeDados = null;
        Object[] w;
        int count = 0;
        boolean flag = true;
       
            while(flag){

                try {
                    w = dbf.nextRecord();
                   // System.out.println("--------------");
                    for(int i = 0; i<w.length;i++){

                        if(w[i]==null){

                            w[i] = "";
                        }

                       // System.out.println(w[i].toString());
                    }//fim do for



                    count++;


                } catch (JDBFException ex) {

                    //Logger.getLogger(OpenDBF.class.getName()).log(Level.SEVERE, null, ex);
                    flag = false;
                }
           }//fim do while
        System.out.println(numColuna+","+count);

        //MO9NTANDO TABELA [][] DE DADOS

       
        
    }//fim da main

}
