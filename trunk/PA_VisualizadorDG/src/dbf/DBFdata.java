/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf;

/**
 *
 * @author André
 */
import com.svcon.jdbf.DBFReader;
import com.svcon.jdbf.JDBFException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DBFdata {

    private static int linhasDBF;
    /*
     * Método básico criado para retornar o número de colunas existentes nas tabelas do arquivo dbf.
     */
    public static int numeroColunas(String url) throws FileNotFoundException, JDBFException {

        InputStream stream = new BufferedInputStream(new FileInputStream(url));
        DBFReader dbf = new DBFReader(stream);

        return dbf.getFieldCount();
    }

    /*
     * Fez-se necessário este método porque o modelo JTable precisa de um vetor com o título de todas
     * colunas a serem utilizadas na tabela. Assim, esse método retira do dbf somente os campos da
     * primeira linha do mesmo e os agrupo em um vetor de dimensão igual ao número de colunas do
     * próprio arquivo dbf.
     */
    public static String[] cabecalhoTabela(String url) throws FileNotFoundException, JDBFException {

        InputStream stream = new BufferedInputStream(new FileInputStream(url));
        DBFReader dbf = new DBFReader(stream);

        String[] cabecalho = new String[dbf.getFieldCount()];

        cabecalho[0] = dbf.getField(0).getName();

        for (int i = 1; i < dbf.getFieldCount(); i++) {
            cabecalho[i] = dbf.getField(i).getName();
        }

        return cabecalho;
    }


    /* FATOS:
     * A biblioteca "jbdf.jar" possui um método contador de colunas, sendo ele: "dbf.getFieldCount()"
     * A biblioteca "jbdf.jar" NÃO possui um método contador de linhas, sendo assim, a construção
     *      abaixo foi criada para servir como um contador de linhas da maneira mais simples possível;
     * O valor do número de linhas será o próprio contador do laço em questão.
     */
    public static int numeroLinhas(String url) throws FileNotFoundException, JDBFException {

        InputStream stream = new BufferedInputStream(new FileInputStream(url));
        DBFReader dbf = new DBFReader(stream);

        int numeroDeLinhasDoDBF;
        for (numeroDeLinhasDoDBF = 0; dbf.hasNextRecord(); numeroDeLinhasDoDBF++) {
            dbf.nextRecord();
        }
        numeroDeLinhasDoDBF++;      //Necessário porque o contador do laço começou em "0" e não em "1";

        return numeroDeLinhasDoDBF;
    }

    public static String[][] matrizDados(String url) throws FileNotFoundException, JDBFException {

        InputStream stream = new BufferedInputStream(new FileInputStream(url));
        DBFReader dbf = new DBFReader(stream);

        linhasDBF = DBFdata.numeroLinhas(url);

        String[][] teste = new String[linhasDBF][dbf.getFieldCount()];

        //primeira linha
        for (int i = 0; i < dbf.getFieldCount(); i++) {
            teste[0][i] = dbf.getField(i).getName().toString();
        }

        //todas as linha
        for (int i = 1; i<linhasDBF; i++) {
            //System.out.println("TESTE LINHA : "+ i);
            Object aobj[] = dbf.nextRecord();
            for (int j = 0; j < aobj.length; j++) {
                if(aobj[j]==null)
                    aobj[j] = "";
                //System.out.println("TESTE DO OBJECT na linha: " + i + " com valor: " + aobj[j]);
                teste[i][j] = aobj[j].toString();
            }
        }
        return teste;
    }
}
