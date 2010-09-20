/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbf;

/**
 *
 * @author André
 */
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {
	private String[] tituloColunas;
	private Object[][] data;
	private int linhas, colunas;

	/**
	 * Construtor que recebe o número de linhas e colunas e um vetor com o nome das colunas
	 * @param tituloColunas vetor com o título das colunas
	 * @param numLinhas número de linhas da table
	 * @param numColunas número de colunas da table
	 */
	public ModeloTabela(String[] tituloColunas, int numLinhas, int numColunas){
		this.tituloColunas= tituloColunas;
		this.data= new Object[numLinhas][numColunas];
		linhas=numLinhas;
		colunas=numColunas;
	}
	/**
	 * @param col indíce da coluna a ser retornado o título da coluna
	 * @return Retorna o nome da coluna no index col
	 */
	public String getColumnName(int col){
		return tituloColunas[col];
	}

	/**
	 * @return retorna o número de colunas da table
	 */
	@Override
	public int getColumnCount() {
		return colunas;
	}

	/**
	 * @return retorna o número de linhas da table
	 */
	@Override
	public int getRowCount() {
		return linhas;
	}

	/**
	 * @return retorna o objeto na que está na posição linha x coluna passados como parâmetro
	 */
	@Override
	public Object getValueAt(int linha, int coluna) {
		return data[linha][coluna];
	}

	/**
	 * seta o valor o da linha x coluna com o objeto passado
	 */
	public void setValueAt(Object valor, int linha, int coluna) {
		data[linha][coluna]=valor.toString();
		fireTableCellUpdated(linha, coluna);
	}
}