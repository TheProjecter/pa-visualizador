/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author nephila
 */
public class Test {

    private int parametro1;
    private int parametro2;
    private int resultado;

    /**
     * Função Soma
     * TODO: retornar a soma dos dois parâmetros
     */
    public int soma(int parametro11, int parametro12){
        parametro1 = parametro11;
        parametro2 = parametro12;
        resultado = parametro1 + parametro2;
        return resultado;

    }

    /*
     * Função Juntar
     * TODO: retornar um número composto pela junção do primeiro parâmetro
     * com o segundo
     */
    public int juntar(int parametro11, int parametro12){

        parametro1 = parametro11;
        parametro2 = parametro12;
        String valor1 = ""+parametro1;
        String valor2 = ""+parametro2;
        String valorR = valor1 + valor2;
        
        return Integer.parseInt(valorR);

    }

    // Puro mangue!!!

}
